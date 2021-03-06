package nju.software.baseframework.service.impl;

import nju.software.baseframework.data.dao.GnbDao;
import nju.software.baseframework.data.dao.ScreenDao;
import nju.software.baseframework.data.dao.WjbDao;
import nju.software.baseframework.data.dataobject.GnbDO;
import nju.software.baseframework.data.dataobject.Screen;
import nju.software.baseframework.data.dataobject.Wjb;
import nju.software.baseframework.data.vo.TableModel;
import nju.software.baseframework.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private GnbDao gnbDao;

    @Autowired
    private ScreenDao screenDao;

    @Autowired
    private WjbDao wjbDao;

    @Autowired
    private AdminService adminService;

    @Override
    public Page<GnbDO> getAllGn(PageRequest pageRequest) {
        return gnbDao.getAll(pageRequest);
    }

    @Override
    public void UpdateGnxx(Integer bh, Integer zt) {
        GnbDO gnbDO = gnbDao.getGnbDOByBh(bh);
        gnbDO.setStatus(zt);
        gnbDao.save(gnbDO);
    }

    @Override
    public Integer getGgfblx() {
        String gnms = gnbDao.getGgGnms();
        if(gnms.equals("公告发布前经过审批程序")){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public Screen getScreen(Integer lx) {
        return screenDao.findByLx(lx);
    }

    @Override
    public void UpdateScreenLb(Screen screen) {
        screenDao.save(screen);
    }

    @Override
    public TableModel<Wjb> getAllVideo(PageRequest pageRequest,Integer lx) {
        Page<Wjb> wjbs =  wjbDao.findByType(pageRequest,lx);
        TableModel<Wjb> tableModel = new TableModel<>(wjbs.getTotalElements(),
                wjbs.getContent());
        return tableModel;
    }

    @Override
    public TableModel<Wjb> getAllDoc(PageRequest pageRequest) {
        Page<Wjb> wjbs = wjbDao.findWjbByType(pageRequest);
        TableModel<Wjb> tableModel = new TableModel<>(wjbs.getTotalElements(),wjbs.getContent());
        return tableModel;
    }

    @Override
    public void UpdateWjb(int id,int zt) {
        Wjb wjb = wjbDao.findWjbById(id);
        if(wjb.getZt()!=zt){
            wjb.setZt(zt);
            wjbDao.save(wjb);
        }
    }
}
