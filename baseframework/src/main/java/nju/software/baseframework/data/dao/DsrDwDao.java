package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.DsrDw;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames = "dsr")
public interface DsrDwDao extends JpaRepository<DsrDw,Integer> {
    @Cacheable(cacheNames = "getDwmc")
    DsrDw getByAjxhAndDwmc(Integer ajxh,String dwmc);
}
