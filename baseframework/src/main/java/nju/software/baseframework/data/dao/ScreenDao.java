package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by johnl on 2019/1/21.
 */
@Repository
public interface ScreenDao extends JpaRepository<Screen,Integer>{
    List<Screen> findAll();
    @Query("from Screen where id=?1")
    Screen findByLx(Integer lx);
}
