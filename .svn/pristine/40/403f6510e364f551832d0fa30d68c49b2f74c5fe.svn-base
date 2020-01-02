package nju.software.baseframework.configuration;

import nju.software.baseframework.data.dynamicdDatabases.DataSourceEnum;
import nju.software.baseframework.data.dynamicdDatabases.DynamicDataSource;
import nju.software.baseframework.data.dynamicdDatabases.EncryptDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.*;


/**
 * sybase数据库配置
 */
@Configuration
@EnableJpaRepositories(basePackages = "nju.software.baseframework.data.dao")
@PropertySource({"classpath:config/jdbc.yml"})
public class DataConfig {

    @Autowired
    private Environment env;

    /**
     * jpa相关
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dynamicDataSource, JpaVendorAdapter jpaVendorAdapter){
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean() ;
        emfb.setDataSource(dynamicDataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("nju.software.baseframework.data.dataobject");
        return emfb;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.SYBASE);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("nju.software.baseframework.configuration.MYSybaseDialect");
        return adapter ;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter2(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        return adapter ;
    }

    /**
     * 事务相关
     * @param
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory) ;
    }
    /**
     *  将jdbc相关的异常转换为spring的异常类型
     */
    @Bean
    public BeanPostProcessor persistenceTransLation(){
        return new PersistenceExceptionTranslationPostProcessor() ;
    }

    /**
     * 多数据源
     * @return
     */
    @Bean
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object,Object> sourceMap = new HashMap<>();
        //取得所有的datasource
        EnumSet<DataSourceEnum> enums = EnumSet.allOf(DataSourceEnum.class);
        for(DataSourceEnum dataSource:enums){
            sourceMap.put(dataSource.getKey(),generateDataSource(dataSource.getKey()));
        }
        dynamicDataSource.setTargetDataSources(sourceMap);
        dynamicDataSource.setDefaultTargetDataSource(sourceMap.get(DataSourceEnum.LOCAL.getKey()));
        return dynamicDataSource;
    }

    private Object generateDataSource(String key){
        EncryptDataSource dataSource
                = new EncryptDataSource();
        key = key.toLowerCase() ;
        String url = "jdbc.url."+key;
        String username = "jdbc.username."+key;
        String password = "jdbc.password."+key;
        String driver = "jdbc.driverclassname."+key;
        dataSource.setDriverClassName(env.getProperty(driver));
        dataSource.setUrl(env.getProperty(url));
        dataSource.setUsername(env.getProperty(username));
        dataSource.setPassword(env.getProperty(password));

        //配置连接池
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("jdbc.initialSize")));
        dataSource.setMaxIdle(Integer.parseInt(env.getProperty("jdbc.maxIdle")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("jdbc.minIdle")));
        dataSource.setValidationQuery(env.getProperty("jdbc.validation-query"));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("jdbc.test-on-borrow")));
        dataSource.setTestWhileIdle(Boolean.parseBoolean(env.getProperty("jdbc.test-while-idle")));
        dataSource.setTimeBetweenEvictionRunsMillis(
                Integer.parseInt(env.getProperty("jdbc.time-between-eviction-runs-millis"))
        );
        return dataSource;
    }
}
