package nju.software.baseframework.Timer;

import nju.software.baseframework.data.dao.*;
import nju.software.baseframework.data.dataobject.Ggb;
import nju.software.baseframework.data.dataobject.Screen;
import nju.software.baseframework.data.dynamicdDatabases.DataSourceEnum;
import nju.software.baseframework.data.dynamicdDatabases.DynamicDataSource;
import nju.software.baseframework.data.vo.FYEnum;
import nju.software.baseframework.data.vo.GgxxModel;
import nju.software.baseframework.data.vo.KtggModel;
import nju.software.baseframework.service.ScreenService;
import nju.software.baseframework.util.DateUtil;
import nju.software.baseframework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by johnl on 2019/1/17.
 */
@Component
public class GgGetAndScreenPutTimer {
    @Autowired
    KtggDao ktggDao;
    @Autowired
    GgxxDao ggxxDao;
    @Autowired
    GgDao ggDao;
    @Autowired
    ScreenService screenService;
    @Autowired
    NavDao navDao;
    @Autowired
    ScreenDao screenDao;

    @Autowired
    WelcomeTxtDao welcomeTxtDao;

    @PostConstruct
    @Scheduled(cron = "0 0 1 * * ?")
    public void getGgAndPutScreen(){
        Date taday = new Date();
        //30天内的开庭公告
        Date startTime = DateUtil.getDayStart(taday,0);
        Date endTime = DateUtil.getDayEnd(taday,30);
        String sybasedb = navDao.findDqfy();
        DynamicDataSource.router(sybasedb);
        String fymc = FYEnum.getNameByFYDM(sybasedb);
        List<KtggModel> ktggModelList = ktggDao.getKtggs(startTime,endTime);

        DynamicDataSource.router("50");
        for (KtggModel ktgg : ktggModelList) {
            if (ggDao.findByAjxh(ktgg.getAjxh()) == null){
                ggDao.save(new Ggb(ktgg,fymc));
            }
        }

        //只有和平法院需要信访库的公告信息
        if (fymc.equals("天津市和平区人民法院")){
            DynamicDataSource.router("0001");
            Date start = DateUtil.getDayStart(taday,0);
            Date end = DateUtil.getDayEnd(taday,0);
            List<GgxxModel> ggxxModelList = ggxxDao.getGgxxs(sybasedb,start,end);

            DynamicDataSource.router("50");
            for (GgxxModel ggxx : ggxxModelList){
                if (ggDao.findByAjxh(ggxx.getAjxh()) == null){
                    ggDao.save(new Ggb(ggxx));
                }
            }
        }

        //获得法官发布的公告
        DynamicDataSource.router("50");
        List<Ggb> fgGgList = ggDao.getFgGgbs();
        for (Ggb gg : fgGgList) {
            gg.setStatus(2);
            ggDao.save(gg);
        }
    }
}
