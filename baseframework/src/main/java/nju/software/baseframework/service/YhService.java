package nju.software.baseframework.service;

import nju.software.baseframework.data.dataobject.Nav;
import nju.software.baseframework.data.dataobject.Yhb;
import nju.software.baseframework.data.vo.TableModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * 与用户信息相关的服务类
 */
public interface YhService {
    /**
     * 检测用户是否存在以及用户密码是否正确
     * @param yh
     * @param yhkl
     * @return
     */
    String checkYhPwd(Yhb yh,String yhkl);

    Yhb getYh(String yhmc);

    Yhb getYhByYhbh(Integer yhbh);

    Map<Integer,List<Nav>> getNav(Integer level);

    /**
     * 常用信息项获取
     */
    String getMs(String lbmc,String bh);

    TableModel<Yhb> getAllYh(String fydm,PageRequest pageRequest);

    void UpdateYhxx(Yhb yhb);

    /**
     * 用户权限
     * @param yhdm
     * @return
     */
    Integer getYhqx(String yhdm);
}
