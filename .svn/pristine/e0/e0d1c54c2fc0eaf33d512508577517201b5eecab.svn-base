package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.PubLaAy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaAyDao extends JpaRepository<PubLaAy,Integer> {
    @Cacheable(cacheNames = "getAy")
    PubLaAy getByAjxh(Integer ajxh);
}
