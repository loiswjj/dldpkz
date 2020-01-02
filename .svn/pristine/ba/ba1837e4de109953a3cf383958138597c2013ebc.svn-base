package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.PubSpry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SpryDao extends JpaRepository<PubSpry,Integer> {
    public List<PubSpry> findAllByAjxh(Integer ajxh);

    List<PubSpry> findBySfspzAndXm(String y, String spz);

}
