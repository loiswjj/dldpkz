package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.AJjb;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface AJjbDao extends JpaRepository<AJjb,Integer>,JpaSpecificationExecutor<AJjb>{
    @Cacheable(cacheNames = "findAjxx")
    AJjb findByAh(String ah);
}
