package nju.software.baseframework.data.dao;

import nju.software.baseframework.data.dataobject.Ftnq;
import nju.software.baseframework.data.vo.KtggModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by johnl on 2019/1/17.
 */
@Repository
public interface KtggDao extends JpaRepository<Ftnq,Integer> {
    @Query(value = "SELECT new nju.software.baseframework.data.vo.KtggModel(ftnq.ajxh,ajjb.ah,ftnq.ft,ftnq.ktrq,ajjb.ajmc) " +
            " FROM Ftnq ftnq, AJjb ajjb WHERE ftnq.ajxh = ajjb.ajxh AND ftnq.sfktsl='Y' AND ftnq.ktrq BETWEEN ?1 AND ?2")
    List<KtggModel> getKtggs(Date startTime,Date endTime);
}
