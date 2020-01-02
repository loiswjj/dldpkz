package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.PubXtglXxxgl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PubXtgl extends JpaRepository<PubXtglXxxgl,Integer> {
    @Query(value = "select sjxx from PubXtglXxxgl where szb=?1 and szl=?2")
    String findBySzbAndSzl(String szb,String szl);
}
