package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.XycjSxbzxr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface XycjSxbzxrDao extends JpaRepository<XycjSxbzxr,String> {

    /**
     * 获得不同法院不同类型的失信被执行人信息
     * @param fydm
     * @param Bzxrlxmc
     * @return
     */
    List<XycjSxbzxr> findAllByFydmAndBzxrlxmc(String fydm,String Bzxrlxmc);
}
