package nju.software.baseframework.service;

import nju.software.baseframework.data.dataobject.GnbDO;
import nju.software.baseframework.data.dataobject.Screen;
import nju.software.baseframework.data.dataobject.Wjb;
import nju.software.baseframework.data.vo.TableModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    /**
     * 获得所有的功能列表
     * @return
     */
    Page<GnbDO> getAllGn(PageRequest pageRequest);

    void UpdateGnxx(Integer bh,Integer zt);

    /**
     * @return
     * 0 发布前需审核
     * 1 发布后审核
     */
    Integer getGgfblx();

    Screen getScreen(Integer lx);

    void UpdateScreenLb(Screen screen);

    /**
     * 获得所有的允许播放的视频文件
     * @param pageRequest
     * @return
     */
    TableModel<Wjb> getAllVideo(PageRequest pageRequest,Integer lx);

    /**
     * 获得所有上传的文本文件
     * @param pageRequest
     * @return
     */
    TableModel<Wjb> getAllDoc(PageRequest pageRequest);

    void UpdateWjb(int id,int zt);
}
