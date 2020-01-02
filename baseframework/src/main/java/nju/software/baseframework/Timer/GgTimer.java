package nju.software.baseframework.Timer;

import nju.software.baseframework.data.dao.AJlbDao;
import nju.software.baseframework.data.dao.GgDao;
import nju.software.baseframework.data.dataobject.Ggb;
import nju.software.baseframework.data.dynamicdDatabases.DynamicDataSource;
import nju.software.baseframework.data.vo.FYEnum;
import nju.software.baseframework.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
public class GgTimer {

    @Autowired
    private AJlbDao aJlbDao;

    @Autowired
    private GgDao ggDao;

    private static Logger logger = Logger.getLogger(GgTimer.class.getName());

    @Scheduled(cron = "0 0 20 * * ?")
    public void UpdateGgzt(){
        String lfsj = DateUtil.format(new Date(),DateUtil.shortFormat);
        logger.info("查询出到期的公告，并重置其状态为已到期");
        String sql = "select ggb from Ggb ggb where ggb.status=2 and" +
                " ggb.dqsj<='"+lfsj+"'";
        DynamicDataSource.router("50");
        List<Ggb> _list = aJlbDao.getList(sql,Ggb.class,null);
        for (int i = 0; i <_list.size() ; i++) {
            Ggb ggb = _list.get(i);
            ggb.setStatus(4);
            ggDao.save(ggb);
        }
    }
}
