package nju.software.baseframework.service.impl;

import nju.software.baseframework.data.dao.XycjSxbzxrDao;
import nju.software.baseframework.data.dataobject.Ggb;
import nju.software.baseframework.data.dataobject.Screen;
import nju.software.baseframework.data.dataobject.XycjSxbzxr;
import nju.software.baseframework.data.vo.GGLXEnum;
import nju.software.baseframework.data.vo.GgxxModel;
import nju.software.baseframework.data.vo.KtggModel;
import nju.software.baseframework.service.ScreenService;
import onbon.bx05.Bx5GEnv;
import onbon.bx05.Bx5GScreenClient;
import onbon.bx05.area.TextCaptionBxArea;
import onbon.bx05.area.page.TextBxPage;
import onbon.bx05.file.ProgramBxFile;
import onbon.bx05.utils.DisplayStyleFactory;
import onbon.bx05.utils.TextBinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by johnl on 2018/12/24.
 */
@Service
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private XycjSxbzxrDao xycjSxbzxrDao;

    public void putScreen(Screen screen,List<KtggModel> ktggList,List<List<Ggb>> ggbList) {

        try {
            Bx5GEnv.initial("config/log.properties", 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bx5GScreenClient client = new Bx5GScreenClient("Screen");
        if (!client.connect(screen.getIp(), 5005)) {
            System.out.println("connect failed");
            return;
        }
        String ktggtitle = "开庭排期公告";
        StringBuilder ktggText = new StringBuilder();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        for (KtggModel ktgg : ktggList) {
            ktggText.append("    本院定于"+sdf.format(ktgg.getKtrq())+"在"+ktgg.getFt()+"公开审理"+ktgg.getAjmc()+"一案。\n");
            ktggText.append("\n");
        }
        String ktggProgramName = "P001";
        ProgramBxFile p001 = getProgram(screen,client,ktggtitle,ktggText.toString(),ktggProgramName);

        List<ProgramBxFile> programList = new ArrayList<ProgramBxFile>();
        programList.add(p001);
        for (int i = 0; i < ggbList.size(); i++) {
            int gglx = ggbList.get(i).get(0).getGglx();
            String title = GGLXEnum.getExplainByNumber(Integer.toString(gglx));
            StringBuilder text = new StringBuilder();
            for (Ggb ggb : ggbList.get(i)) {
                text.append("    "+ggb.getGgnr()+"\n");
                text.append("\n");
            }
            String programName = "P00"+(i+2);
            ProgramBxFile programBxFile = getProgram(screen,client,title,text.toString(),programName);
            programList.add(programBxFile);
        }

        client.deletePrograms();
        client.deleteAllDynamic();

        try {
            client.writePrograms(programList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        client.disconnect();
    }

    @Override
    public void fgPutScreen(Screen screen, String title, String text) {
        try {
            Bx5GEnv.initial("log.properties", 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bx5GScreenClient client = new Bx5GScreenClient("Screen");
        if (!client.connect(screen.getIp(), 5005)) {
            System.out.println("connect screen failed");
            return;
        }

        client.deletePrograms();
        client.deleteAllDynamic();
        try {
            if (title.equals("")){
                ProgramBxFile programBxFile = getNoTitleProgram(screen,client,text,"fgPutProgram");
                client.writeProgram(programBxFile);
            }else {
                ProgramBxFile programBxFile = getProgram(screen,client,title,text,"fgPutProgram");
                client.writeProgram(programBxFile);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void fgPutScreen(Screen screen,  Map<Integer,List<Ggb>> ggbList) {
        try {
            Bx5GEnv.initial("log.properties", 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bx5GScreenClient client = new Bx5GScreenClient("Screen");
        if (!client.connect(screen.getIp(), 5005)) {
            System.out.println("connect failed");
            return;
        }

        List<ProgramBxFile> programList = new ArrayList<ProgramBxFile>();
        String ktggtitle = "开庭排期公告";
        StringBuilder ktggText = new StringBuilder();

        for (Ggb ggb: ggbList.get(2)){
            ktggText.append("    "+ggb.getGgnr()+"\n");
            ktggText.append("\n");
        }
        ProgramBxFile p001 = getProgram(screen,client,ktggtitle,ktggText.toString(),"P001");
        programList.add(p001);
        int i = 2;

        for (Integer key: ggbList.keySet()){
            String title = GGLXEnum.getExplainByNumber(Integer.toString(key));
            StringBuilder ggnr = new StringBuilder();
            for (Ggb ggb:ggbList.get(key)){
                ggnr.append("    "+ggb.getGgnr()+"\n");
                ggnr.append("\n");
            }
            String programName = "P00"+i;
            i += 1;
            ProgramBxFile programBxFile = getProgram(screen,client,title,ggnr.toString(),programName);
            programList.add(programBxFile);
        }
        client.deletePrograms();
        client.deleteAllDynamic();

        //写入
        try {
            client.writePrograms(programList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        client.disconnect();
    }

    /**
     * 有标题的节目
     * @param screen
     * @param client
     * @param title
     * @param text
     * @param programName
     * @return
     */
    public ProgramBxFile getProgram(Screen screen,Bx5GScreenClient client,String title,String text,String programName){
        //区域的位置
        int posX1 = 0;
        int posY1 = 0;
        int posX2 = 0;
        int posY2 = 30;

        // client.getProfile()此方式，是通过从控制器回读来自动创建profile的，使用方便
        // 但增加了ＰＣ与控制器之前的交互，从而使得发送效率降低
        ProgramBxFile programBxFile = new ProgramBxFile(programName, client.getProfile());
        // 是否显示节目边框
        programBxFile.setFrameShow(false);
        // 创建一个文本区
        // 分别输入其X,Y,W,H
        // 屏幕左上角坐标为 (0, 0)
        // 注意区域坐标和宽高，不要越界
        TextCaptionBxArea tArea1 = new TextCaptionBxArea(posX1, posY1, screen.getWidth(), 30, client.getProfile());
        TextCaptionBxArea tArea2 = new TextCaptionBxArea(posX2, posY2, screen.getWidth(), screen.getHeight()-30, client.getProfile());
        // 使能区域边框
        tArea1.setFrameShow(false);
        tArea2.setFrameShow(false);

        // 创建一个数据页
        TextBxPage page_1 = new TextBxPage(title);
        // 是否换行即换页
        page_1.setLineBreak(false);
        // 设置文本水平对齐方式
        page_1.setHorizontalAlignment(TextBinary.Alignment.CENTER);
        // 设置文本垂直居中方式
        page_1.setVerticalAlignment(TextBinary.Alignment.CENTER);
        // 设置文本字体
        page_1.setFont(new Font("宋体", Font.BOLD, 16));         // 字体
        // 设置文本颜色
        page_1.setForeground(Color.red);
        // 设置区域背景色，默认为黑色
        page_1.setBackground(Color.darkGray);
        // 调整特技方式
        page_1.setDisplayStyle(DisplayStyleFactory.getStyle(1));

        // 创建一个数据页
        TextBxPage page_2 = new TextBxPage(text);
        // 是否换行即换页
        page_2.setLineBreak(false);
        // 设置文本水平对齐方式
        page_2.setHorizontalAlignment(TextBinary.Alignment.NEAR);
        // 设置文本垂直居中方式
        page_2.setVerticalAlignment(TextBinary.Alignment.CENTER);
        // 设置文本字体
        page_2.setFont(new Font("宋体", Font.PLAIN, 12));         // 字体
        // 设置文本颜色
        page_2.setForeground(Color.red);
        // 设置区域背景色，默认为黑色
        page_2.setBackground(Color.darkGray);
        // 调整特技方式
        page_2.setDisplayStyle(DisplayStyleFactory.getStyle(6));


        // 调整特技速度
        page_2.setSpeed(8);
        page_2.setHeadTailInterval(0);
        // 调整停留时间, 单位 10ms
        page_2.setStayTime(0);

        // 将前面创建的 page_1 添加到 area 中
        tArea1.addPage(page_1);
        tArea2.addPage(page_2);

        programBxFile.addArea(tArea1);
        programBxFile.addArea(tArea2);

        if (programBxFile.validate() != null) {
            System.out.println("Program out of range");
        }

        return programBxFile;
    }

    /**
     * 没有标题的节目
     * @param screen
     * @param client
     * @param text
     * @param programName
     * @return
     */
    public ProgramBxFile getNoTitleProgram(Screen screen,Bx5GScreenClient client,String text,String programName){
        //区域的位置
        int posX1 = 0;
        int posY1 = 0;

        // client.getProfile()此方式，是通过从控制器回读来自动创建profile的，使用方便
        // 但增加了ＰＣ与控制器之前的交互，从而使得发送效率降低
        ProgramBxFile programBxFile = new ProgramBxFile(programName, client.getProfile());
        // 是否显示节目边框
        programBxFile.setFrameShow(false);
        // 创建一个文本区
        // 分别输入其X,Y,W,H
        // 屏幕左上角坐标为 (0, 0)
        // 注意区域坐标和宽高，不要越界
        TextCaptionBxArea tArea1 = new TextCaptionBxArea(posX1, posY1, screen.getWidth(), screen.getHeight(), client.getProfile());
        // 使能区域边框
        tArea1.setFrameShow(false);

        // 创建一个数据页
        TextBxPage page_1 = new TextBxPage(text);
        // 是否换行即换页
        page_1.setLineBreak(false);
        // 设置文本水平对齐方式
        page_1.setHorizontalAlignment(TextBinary.Alignment.NEAR);
        // 设置文本垂直居中方式
        page_1.setVerticalAlignment(TextBinary.Alignment.CENTER);
        // 设置文本字体
        page_1.setFont(new Font("宋体", Font.PLAIN, 12));         // 字体
        // 设置文本颜色
        page_1.setForeground(Color.red);
        // 设置区域背景色，默认为黑色
        page_1.setBackground(Color.darkGray);
        // 调整特技方式
        page_1.setDisplayStyle(DisplayStyleFactory.getStyle(6));


        // 调整特技速度
        page_1.setSpeed(8);
        page_1.setHeadTailInterval(0);
        // 调整停留时间, 单位 10ms
        page_1.setStayTime(0);

        // 将前面创建的 page_1 添加到 area 中
        tArea1.addPage(page_1);

        programBxFile.addArea(tArea1);

        if (programBxFile.validate() != null) {
            System.out.println("Program out of range");
        }

        return programBxFile;
    }

    @Override
    public List<List<GgxxModel>> partGgxxList(List<GgxxModel> ggxxModelList) {
        List<List<GgxxModel>> res = new ArrayList<>();
        //起诉状、上诉状副本
        List<GgxxModel> qszsszfbList = new ArrayList<>();
        //开庭传票
        List<GgxxModel> ktcpList = new ArrayList<>();
        //裁判文书
        List<GgxxModel> cpwsList = new ArrayList<>();
        //公示催告
        List<GgxxModel> gscgList = new ArrayList<>();
        //破产文书
        List<GgxxModel> pcwsList = new ArrayList<>();
        //宣告失踪、死亡
        List<GgxxModel> xgszswList = new ArrayList<>();
        //执行文书
        List<GgxxModel> zxwsList = new ArrayList<>();
        //无主财产认领公告
        List<GgxxModel> wzccrlList = new ArrayList<>();
        //起诉状副本及开庭传票
        List<GgxxModel> qszfbktcpList = new ArrayList<>();
        //更正
        List<GgxxModel> gzList = new ArrayList<>();
        //其他
        List<GgxxModel> qtList = new ArrayList<>();
        //工作证丢失
        List<GgxxModel> gzzdsList = new ArrayList<>();

        for (GgxxModel ggxx : ggxxModelList) {
            if (ggxx.getGgmc().equals("起诉状、上诉状副本")) {
                qszsszfbList.add(ggxx);
            } else if (ggxx.getGgmc().equals("开庭传票")) {
                ktcpList.add(ggxx);
            } else if (ggxx.getGgmc().equals("裁判文书")) {
                cpwsList.add(ggxx);
            } else if (ggxx.getGgmc().equals("公示催告")) {
                gscgList.add(ggxx);
            } else if (ggxx.getGgmc().equals("宣告失踪、死亡")) {
                xgszswList.add(ggxx);
            } else if (ggxx.getGgmc().equals("破产文书")) {
                pcwsList.add(ggxx);
            } else if (ggxx.getGgmc().equals("执行文书")) {
                zxwsList.add(ggxx);
            } else if (ggxx.getGgmc().equals("无主财产认领公告")) {
                wzccrlList.add(ggxx);
            } else if (ggxx.getGgmc().equals("起诉状副本及开庭传票")) {
                qszfbktcpList.add(ggxx);
            } else if (ggxx.getGgmc().equals("更正")) {
                gzList.add(ggxx);
            } else if (ggxx.getGgmc().equals("其他")) {
                qtList.add(ggxx);
            } else if (ggxx.getGgmc().equals("工作证丢失")) {
                gzzdsList.add(ggxx);
            }
        }
        return res;
    }

    @Override
    public List<List<Ggb>> partGgbList(List<Ggb> ggbList) {
        List<List<Ggb>> res = new ArrayList<>();
        //送达起诉状副本及开庭传票公告
        List<Ggb> sdqszfbktcpList = new ArrayList<>();
        //送达判决书公告
        List<Ggb> sdpjsList = new ArrayList<>();
        //执行公告
        List<Ggb> zxList = new ArrayList<>();
        //失信被执行人公告
        List<Ggb> sxbzxrList = new ArrayList<>();
        //送达裁定书公告
        List<Ggb> sdcdsList = new ArrayList<>();
        //自定义公告
        List<Ggb> zdyList = new ArrayList<>();
        for (Ggb ggb : ggbList) {
            if (ggb.getGglx() == 1) {
                ggb.setGgmc(GGLXEnum.getExplainByNumber("1"));
                sdqszfbktcpList.add(ggb);
            } else if (ggb.getGglx() == 3) {
                ggb.setGgmc(GGLXEnum.getExplainByNumber("3"));
                sdpjsList.add(ggb);
            } else if (ggb.getGglx() == 4) {
                ggb.setGgmc(GGLXEnum.getExplainByNumber("4"));
                zxList.add(ggb);
            } else if (ggb.getGglx() == 5) {
                ggb.setGgmc(GGLXEnum.getExplainByNumber("5"));
                sxbzxrList.add(ggb);
            } else if (ggb.getGglx() == 6) {
                ggb.setGgmc(GGLXEnum.getExplainByNumber("6"));
                sdcdsList.add(ggb);
            } else if (ggb.getGglx() == 7) {
                ggb.setGgmc(GGLXEnum.getExplainByNumber("7"));
                zdyList.add(ggb);
            }
        }
        return res;
    }

    @Override
    @Cacheable(cacheNames = "sxbzxr")
    public List<XycjSxbzxr> getSxbzxr(String fydm, String bzlxmc) {
        return xycjSxbzxrDao.findAllByFydmAndBzxrlxmc(fydm,bzlxmc);
    }
}
