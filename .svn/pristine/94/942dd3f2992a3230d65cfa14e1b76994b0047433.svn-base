package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.WelcomeTxt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WelcomeTxtDao extends JpaRepository<WelcomeTxt,Integer> {

    @Query(value = "select path from WelcomeTxt where lb=?1")
    List<String> getAllByLb(Integer lb);

    @Query(value = "select path from WelcomeTxt where id=(select max(id) from WelcomeTxt)")
    String getByMaxBh();

    @Query(value = "select nr from WelcomeTxt where id=?1")
    String getWelcomeTxtById(Integer id);
}
