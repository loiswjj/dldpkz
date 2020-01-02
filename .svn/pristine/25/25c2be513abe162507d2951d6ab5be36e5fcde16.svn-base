package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.Nav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NavDao extends JpaRepository<Nav,Integer> {
    /**
     * 按照优先级查找侧边栏情况
     * @param level
     * @return
     */
    @Query(nativeQuery = true,value = "select * from nav where level <= ?;")
    List<Nav> findAllByLevel(Integer level);

    @Query(nativeQuery = true,value = "select * from test.nav as nav where nav.id= ?1 or nav.level < ?2")
    List<Nav> findSpeciNav(Integer picid,Integer piclevel);

    @Query(value = "select nav.dqfy from Nav nav where nav.id = 1")
    String  findDqfy();
}
