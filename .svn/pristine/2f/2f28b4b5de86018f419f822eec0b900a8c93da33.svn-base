package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.PubDmb;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
public interface DmbDao extends JpaRepository<PubDmb,String> {

    @Query(value = "select a from PubDmb a where a.lbbh=?1")
    List<PubDmb> findAllByLbbh(String lbbh);
}