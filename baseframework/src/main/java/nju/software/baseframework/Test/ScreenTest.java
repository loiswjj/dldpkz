package nju.software.baseframework.Test;

import nju.software.baseframework.data.dataobject.Ggb;
import nju.software.baseframework.util.ScreenUtil;
import nju.software.baseframework.data.vo.TextModel;
import novj.platform.vxkit.common.bean.search.SearchResult;
import novj.publ.api.actions.ProgramManager;
import novj.publ.api.beans.NormalTextBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jingjing
 * @date 2019/11/14
 */
public class ScreenTest {
    static SearchResult currentSearchResult = null;
    public static void main(String[] args){
        String remoteIp = "130.1.67.186";
        ScreenUtil controller = new ScreenUtil();
        controller.init();
        System.out.println(controller.connectWithLed(remoteIp));
        List<Ggb> ggbs = new ArrayList<>();
        ggbs.add(new Ggb("测试测试"));
        ggbs.add(new Ggb("测试测试测试测测试啛啛喳喳错错存储"));
//        controller.createKTGGProgram(ggbs);
    }
}
