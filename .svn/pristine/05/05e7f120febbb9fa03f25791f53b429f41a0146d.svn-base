package nju.software.baseframework.service;

import nju.software.baseframework.data.dataobject.Ggb;
import nju.software.baseframework.data.dataobject.Screen;
import nju.software.baseframework.data.dataobject.XycjSxbzxr;
import nju.software.baseframework.data.vo.GgxxModel;
import nju.software.baseframework.data.vo.KtggModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by johnl on 2018/12/24.
 */
@Service
public interface ScreenService {
    /**
     * 向大屏推送公告
     * @param screen 根据表screen的ip，1代表第一块屏幕
     * @param ktggList
     * @param ggbList
     */
    void putScreen(Screen screen,List<KtggModel> ktggList, List<List<Ggb>> ggbList);

    /**
     * 推送欢迎词之类的
     * @param screen
     * @param title
     * @param text
     */
    void fgPutScreen(Screen screen,String title,String text);

    /**
     * 按公告名称分别存放
     * @param ggxxModelList
     * @return
     */
    List<List<GgxxModel>> partGgxxList(List<GgxxModel> ggxxModelList);

    /**
     * 按公告类型分别存放
     * @param ggbList
     * @return
     */
    List<List<Ggb>> partGgbList(List<Ggb> ggbList);

    List<XycjSxbzxr> getSxbzxr(String fydm,String bzlxmc);

    void fgPutScreen(Screen screen, Map<Integer,List<Ggb>> ggbList);
}
