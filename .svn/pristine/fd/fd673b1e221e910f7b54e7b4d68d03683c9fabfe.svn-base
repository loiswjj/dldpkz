package nju.software.baseframework.Timer;

import nju.software.baseframework.data.dao.AJlbDao;
import nju.software.baseframework.data.dao.YyDao;
import nju.software.baseframework.data.dataobject.Yyb;
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
public class YyTimer {

    @Autowired
    private AJlbDao aJlbDao;

    @Autowired
    private YyDao yyDao;

    private static Logger logger = Logger.getLogger(YyTimer.class.getName());

    @Scheduled(cron = "0 0 20 * * ?")
    public void UpdateYyzt(){
        String lfsj = DateUtil.format(new Date(),DateUtil.shortFormat);
        logger.info("用于寻找预约表里面过期的公告，并重置其状态为已过期");
        String sql = "select yyb from Yyb yyb where yyb.yyzt=1 and " +
                "yyb.lfsj<='"+lfsj+"'";
        DynamicDataSource.router("50");
        List<Yyb> _list = aJlbDao.getList(sql,Yyb.class,null);
        for (int i = 0; i < _list.size(); i++) {
            Yyb yyb = _list.get(i);
            yyb.setYyzt(3);
            yyDao.save(yyb);
        }
    }
}
