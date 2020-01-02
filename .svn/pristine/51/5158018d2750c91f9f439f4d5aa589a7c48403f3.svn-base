package nju.software.baseframework.data.dynamicdDatabases;

import nju.software.baseframework.util.StringUtil;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * @author 13314
 * @date 2018/7/26
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<>() ;
    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceHolder.get();
    }
    public static void router(String targetFy){
        if(StringUtil.isEmpty(targetFy)){
            return;
        }
        if(DataSourceEnum.getSourceByFydm(targetFy)!=null){
            //根据法院代码切换
            dataSourceHolder.set(DataSourceEnum.getSourceByFydm(targetFy));
        }else{
            for(DataSourceEnum dse:DataSourceEnum.values()){
                //根据法院简称切换
                if(StringUtil.equals(dse.getKey(),targetFy)){
                    dataSourceHolder.set(targetFy);
                    break;
                }
            }
        }
    }

    public static void router(DataSourceEnum sourceEnum){
        dataSourceHolder.set(sourceEnum.getKey());
    }
    public static String getCurrentDB(){
        return dataSourceHolder.get();
    }
}
