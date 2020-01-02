package nju.software.baseframework.Timer;

import nju.software.baseframework.convertor.DmbConvertor;
import nju.software.baseframework.data.dataobject.PubDmb;
import nju.software.baseframework.data.dynamicdDatabases.DynamicDataSource;
import nju.software.baseframework.data.vo.AJZTEnum;
import nju.software.baseframework.data.vo.GGLXEnum;
import nju.software.baseframework.service.Enum.GgZTEnum;
import nju.software.baseframework.service.Enum.YYZTEnum;
import nju.software.baseframework.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class AppInitializer implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(AppInitializer.class);
    @Resource
    private DmbConvertor dmbConvertor;

    public static List<PubDmb> ajxz ;
    public static Map<String,List<PubDmb>> spt;
    public static List<PubDmb> sycx;
    public static List<PubDmb> gglx;
    public static Map<String,String> jafs = new HashMap<>();
    public static List<PubDmb> ggzt;
    public static List<PubDmb> yyzt;
    public static List<PubDmb> ajzt;

    /**
     * 系统初始化就需要执行的数据库查询工作
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("系统初始化:执行数据库查询");
        /**
         * 获得页面所需的查询信息
         */
        gglx = GGLXEnum.getGglx();
        ggzt = GgZTEnum.getGgzt();
        yyzt = YYZTEnum.getYyzt();
        ajzt = AJZTEnum.getAjzt();

        DynamicDataSource.router("120207 226");
        ajxz = dmbConvertor.getDmInfo("ajxz");
        sycx = dmbConvertor.getDmInfo("sycx");
        Map<String,List<PubDmb>> map = dmbConvertor.getAllDmInfo("jafs");
        for(String key: map.keySet()){
            jafs.put(key,JsonUtil.toJson(map.get(key)));
        }
        spt = dmbConvertor.getSpt();
    }
}
