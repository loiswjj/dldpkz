package nju.software.baseframework.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import nju.software.baseframework.configuration.FilterConfig;
import nju.software.baseframework.data.dao.NavDao;
import nju.software.baseframework.data.dao.ScreenDao;
import nju.software.baseframework.data.dao.WelcomeTxtDao;
import nju.software.baseframework.data.dao.WjbDao;
import nju.software.baseframework.data.dataobject.*;
import nju.software.baseframework.data.dynamicdDatabases.DynamicDataSource;
import nju.software.baseframework.data.vo.*;
import nju.software.baseframework.service.*;
import nju.software.baseframework.service.impl.ScreenServiceImpl;
import nju.software.baseframework.util.ScreenUtil;
import novj.publ.api.NovaOpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class AdminController {

    @Resource
    private YyService yyService;

    @Resource
    private GgService ggService;

    @Resource
    private AjlbService ajlbService;

    @Resource
    private YhService yhService;

    @Resource
    private AdminService adminService;

    @Resource
    private ScreenServiceImpl screenService;

    @Autowired
    private WelcomeTxtDao welcomeTxtDao;

    @Autowired
    private WjbDao wjbDao;

    @Autowired
    private ScreenDao screenDao;

    @Autowired
    private NavDao navDao;

    private Gson gson = new GsonBuilder().create();

    @RequestMapping(value = "/dptb",method = RequestMethod.GET)
    public String dptb(@RequestParam String lx, ModelMap modelMap,HttpSession session){
        String fydm = (String) session.getAttribute("fydm");
        List<PubDmb> gglxList = GGLXEnum.getGglx();
        String fybh = FYEnum.getFybhByFydm(fydm);
        PubDmb sxbzxr = new PubDmb();
        sxbzxr.setDmbh("8");
        sxbzxr.setDmms("失信被执行人");
        gglxList.add(sxbzxr);
        PubDmb sp = new PubDmb();
        sp.setDmbh("9");
        sp.setDmms("上传文件");
        gglxList.add(sp);
        modelMap.addAttribute("fybh",fybh);
        modelMap.addAttribute("gglx",gglxList);
        modelMap.addAttribute("lx",lx);
        return "admin/dptb";
    }

    @RequestMapping(value = "/hyc",method = RequestMethod.GET)
    public String hyc(@RequestParam String lx, ModelMap modelMap,HttpSession session){
        String fydm = (String) session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        //找到所有的模板
//        modelMap.addAttribute("lx",lx);
//        modelMap.addAttribute("ImageDataList",Image2Base64.getImageStrList(welcomeTxtDao.getAllByLb(1)));
//        modelMap.addAttribute("fybh",FYEnum.getFybhByFydm(fydm));
//        modelMap.addAttribute("href","hyc_sn");
//        return "admin/hyc";
        Screen screen = screenDao.findByLx(Integer.parseInt(lx));
        if (screen.getWidth()==0) {
            ScreenUtil controller = new ScreenUtil();
            controller.init();
            if (controller.connectWithLed(screen.getIp())){
                screen = controller.getScreenParam(screen);
                controller.logout();
                screenDao.save(screen);
            }else {
                log.error("连接大屏错误，无法获得屏体参数！");
            }
        }
        modelMap.addAttribute("screen",screen);
        return "admin/ContentManager";
    }

    @GetMapping("dpjk")
    public String dpjk(@RequestParam String lx,ModelMap map){
        Screen screen = screenDao.findByLx(Integer.parseInt(lx));
        // 获取大屏截图
        ScreenUtil screenUtil = new ScreenUtil();
        screenUtil.init();
        String res ;
        if (screenUtil.connectWithLed(screen.getIp())){
            if (screenUtil.getScreenshot("D:\\Upload\\Files",lx+"png")){
                res = "D:\\Upload\\Files\\"+lx+"png";
            }else {
                log.error("获取监控信息失败");
                res = "2";
            }
        }else {
            log.error("连接大屏失败");
            res = "3";
        }
        map.addAttribute("screen",screen);
        map.addAttribute("res",res);
        return "admin/dpjk";
    }

    @RequestMapping(value = "/sh",method = RequestMethod.GET)
    public String sh(){
        return "admin/sh";
    }

    @RequestMapping(value = "/dwzz",method = RequestMethod.GET)
    public String dwzz(){
        return "admin/dwzz";
    }

    @RequestMapping(value = "xtgl",method = RequestMethod.GET)
    public String xtgl(){
        return "admin/xtgl";
    }

    /**
     * 上传文件管理 文本文件+视频文件
     * @return
     */
    @RequestMapping(value = "splist",method = RequestMethod.GET)
    public String splist(@RequestParam String lx,ModelMap modelMap){
        modelMap.addAttribute("lx",lx);
        return "admin/sp";
    }

    /**
     * 预约表的审核
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/yysh",method = RequestMethod.POST)
    @ResponseBody
    public TableModel<YyxqModel> yySh(@RequestParam String page,
                                @RequestParam String rows,HttpSession session){
        String fydm = (String) session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        int curPage = Integer.parseInt(page) -1;
        int pageSize = Integer.parseInt(rows);
        PageRequest pageRequest = PageRequest.of(curPage,pageSize);
        Page<YyxqModel> _list = yyService.getYySh(pageRequest);
        TableModel<YyxqModel> tab = new TableModel<>(_list.getTotalElements(),
                _list.getContent());
        return tab;
    }

    /**
     * 公告审核表
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "ggsh",method = RequestMethod.POST)
    public TableModel<GgxqModel> GgSh(@RequestParam String page,
                                      @RequestParam String rows,
                                      HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        Yhb yh = (Yhb)session.getAttribute(FilterConfig.SESSION_KEY);
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        int curPage = Integer.parseInt(page) -1;
        int pageSize = Integer.parseInt(rows);
        PageRequest pageRequest = PageRequest.of(curPage,pageSize);
        Page<GgxqModel> _list = ggService.getGgSh(pageRequest,yh);
        TableModel<GgxqModel> tab = new TableModel<>(_list.getTotalElements(),
                _list.getContent());
        return tab;
    }

    @RequestMapping(value = "sh_gg",method = RequestMethod.GET)
    public String sh_gg(HttpServletRequest request,
                        ModelMap map, HttpSession session){
        String fydm = (String) session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        Integer bh = Integer.valueOf(request.getParameter("bh"));
        String fy = (String) session.getAttribute("fy");
        map.addAttribute("fy",fy);
        GgxqModel ggxqModel = ggService.findGgbByBh(bh);

        DynamicDataSource.router(fydm);
        AjxqModel ajxqModel = ajlbService.getAjxxByAh(ggxqModel.getAh());
        map.addAttribute("ggb",ggxqModel);
        map.addAttribute("ajxq",ajxqModel);
        return "admin/ggxq";
    }

    @ResponseBody
    @RequestMapping(value = "Yhb",method = RequestMethod.POST)
    public TableModel<Yhb> yhb(@RequestParam String page,
                               @RequestParam String rows,
                               HttpSession session){
        int curPage = Integer.parseInt(page) -1;
        int pageSize = Integer.parseInt(rows);
        PageRequest pageRequest = PageRequest.of(curPage,pageSize);
        String fydm = (String) session.getAttribute("fydm");
        return yhService.getAllYh(fydm,pageRequest);
    }

    @ResponseBody
    @RequestMapping(value = "UpdateYhxx",method = RequestMethod.POST)
    public void UpdateYhxx(HttpServletRequest request,HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        Integer yhbh = Integer.valueOf(request.getParameter("bh"));
        Integer sfyx = Integer.valueOf(request.getParameter("sfyx"));
        Yhb yh = yhService.getYhByYhbh(yhbh);
        yh.setSfyx(sfyx);
        yhService.UpdateYhxx(yh);
    }

    @ResponseBody
    @RequestMapping(value = "gnlb",method = RequestMethod.POST)
    public TableModel<GnbDO> getGnlb(@RequestParam String page,
                                     @RequestParam String rows,
                                     HttpSession session){
        int curPage = Integer.parseInt(page) -1;
        int pageSize = Integer.parseInt(rows);
        PageRequest pageRequest = PageRequest.of(curPage,pageSize);
        String fydm = (String) session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        Page<GnbDO> gnlb = adminService.getAllGn(pageRequest);
        TableModel<GnbDO> tab = new TableModel<>(gnlb.getTotalElements()
                ,gnlb.getContent());
        return tab;
    }

    @ResponseBody
    @RequestMapping(value = "getSp",method = RequestMethod.POST)
    public TableModel<Wjb> splist(@RequestParam String page,
                                     @RequestParam String rows,
                                  @RequestParam String lx){
        DynamicDataSource.router("50");
        int curPage = Integer.parseInt(page) -1;
        int pageSize = Integer.parseInt(rows);
        PageRequest pageRequest = PageRequest.of(curPage,pageSize);
        TableModel<Wjb> tab = adminService.getAllVideo(pageRequest,Integer.parseInt(lx));
        return tab;
    }

    @ResponseBody
    @RequestMapping(value = "getAllDoc",method = RequestMethod.POST)
    public TableModel<Wjb> docList(@RequestParam String page,@RequestParam String rows){
        DynamicDataSource.router("50");
        int curPage = Integer.parseInt(page)-1;
        int pageSize = Integer.parseInt(rows);
        PageRequest pageRequest = PageRequest.of(curPage,pageSize);
        TableModel<Wjb> docs = adminService.getAllDoc(pageRequest);
        return docs;
    }

    @ResponseBody
    @RequestMapping(value = "UpdateGnxx",method = RequestMethod.POST)
    public void UpdateGnxx(HttpServletRequest request,HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        Integer bh = Integer.valueOf(request.getParameter("bh"));
        Integer status = Integer.valueOf(request.getParameter("zt"));
        adminService.UpdateGnxx(bh,status);
    }

    @ResponseBody
    @RequestMapping(value = "UpdateWjb",method = RequestMethod.POST)
    public void UpdateWjb(@RequestParam Integer id,@RequestParam Integer zt){
        DynamicDataSource.router("50");
        adminService.UpdateWjb(id,zt);
    }

    /**
     * 删除没有用的视频
     */
    @ResponseBody
    @RequestMapping(value = "deleteSp",method = RequestMethod.POST)
    public void deleteSp(@RequestParam Integer id){
        try {
            Wjb wjb = wjbDao.findWjbById(id);
            //删除视频
            String filepath = "D:/上传文件/"+wjb.getBcwz();
            File file = new File(filepath);
            file.delete();
            wjbDao.delete(wjb);
        }catch (Exception e){
            log.error("删除视频失败"+e);
        }
    }

    /**
     * 管理公告是否播放
     */
    @ResponseBody
    @RequestMapping(value = "ControlGgBf",method = RequestMethod.POST)
    public TableModel<GgxqModel> ControlGgBf(@RequestParam String page,
                                       @RequestParam String rows,
                                             HttpSession session){
        int curPage = Integer.parseInt(page) -1;
        int pageSize = Integer.parseInt(rows);
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        Page<Ggb> ggbs = ggService.getGgControllist(PageRequest.of(curPage,pageSize));
        List<GgxqModel> ggxqModels = new ArrayList<>();
        for (int i = 0; i <ggbs.getContent().size() ; i++) {
            ggxqModels.add(new GgxqModel(ggbs.getContent().get(i)));
        }
        TableModel<GgxqModel> tab = new TableModel<>(ggbs.getTotalElements(),ggxqModels);
        return tab;
    }

    @RequestMapping(value = "sendWelcomeTxt",method = RequestMethod.POST)
    public void sendWelcomeTxt(@RequestParam String txt,@RequestParam Integer lx, HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        Screen screen = adminService.getScreen(lx);
        screenService.fgPutScreen(screen,"",txt);
        WelcomeTxt _txt = new WelcomeTxt(txt,0);
        welcomeTxtDao.save(_txt);
        screen.setNrlb(_txt.getId());
        adminService.UpdateScreenLb(screen);
    }

    /**
     * 手动编辑节目并发送
     * @param screen_id
     * @return
     */
    @ResponseBody
    @PostMapping("sendEditContent")
    public String sendEditContent(Integer screen_id,@RequestParam String str){
        ScreenUtil screenUtil = new ScreenUtil();
        screenUtil.init();
        DynamicDataSource.router("50");
        Screen screen = screenDao.findByLx(screen_id);
        if (screenUtil.connectWithLed(screen.getIp())){
            screenUtil.initScreen("manual");
            ProgramContent programContent = gson.fromJson(str,ProgramContent.class);
            screenUtil.addManualPage(programContent);
            if (programContent.getType()==1){
                screenUtil.addTimingBean(programContent);
                if (screenUtil.sendTimingProgram("manual")) return "true";
            }else {
                if (screenUtil.sendProgram("manual")) return "true";
            }
            return "发布信息失败";
        }else return "连接大屏失败";
    }

    @ResponseBody
    @RequestMapping(value = "sendGgList",method = RequestMethod.POST)
    public String sendGgList(@RequestParam int[] arr,@RequestParam Integer lx,HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        Screen screen = adminService.getScreen(lx);
        //获得所有的可播放的公告列表 -- 按类别分别播放
        Map<Integer,List<Ggb>> ggMap = ggService.getGgnrList();
        ScreenUtil controller = new ScreenUtil();
        controller.init();
        if (controller.connectWithLed(screen.getIp())){
            //连接大屏控制器成功
            controller.initScreen("admin_send");
            int pageId ;
            for (int item : arr){
                if (item == 8) {
                    // 失信被执行人
                    DynamicDataSource.router(fydm);
                    FYEnum fyEnum = FYEnum.getFyByFydm(fydm);
                    List<XycjSxbzxr> zrlList = screenService.getSxbzxr(fyEnum.getName(),"自然人");
                    for (XycjSxbzxr sxbzxr: zrlList){
                        if(sxbzxr.getZjhm()!=null&&sxbzxr.getZjhm()!=""
                                &&sxbzxr.getZjhm().length()>=18){
                            String sfzh = sxbzxr.getZjhm().substring(0,10)+"****"
                                    +sxbzxr.getZjhm().substring(14,18);
                            sxbzxr.setZjhm(sfzh);
                        }
                    }
                    //如果是法人
                    List<XycjSxbzxr> zzjgList = screenService.getSxbzxr(fyEnum.getName(),"法人");
                    for (XycjSxbzxr sxbzxr: zzjgList){
                        if(sxbzxr.getZzjgdm()!=null&&sxbzxr.getZzjgdm()!=""
                                &&sxbzxr.getZzjgdm().length()>=18){
                            String zzjgdm = sxbzxr.getZzjgdm().substring(0,10)+"****"
                                    +sxbzxr.getZzjgdm().substring(14,18);
                            sxbzxr.setZzjgdm(zzjgdm);
                        }
                    }
                    pageId = controller.addPage();
                    controller.createSxbzxrProgram(zrlList,zzjgList,pageId);
                }
                else if (item == 10){
                    //暂时没想好应该怎么弄
                    List<Wjb> wjbs = wjbDao.findBcwzList(lx);
                    controller.UploadFiles(wjbs);
                }else {
                    // 公告类
                    pageId = controller.addPage();
                    controller.createKTGGProgram(ggMap.get(item),pageId);
                }
            }
            long timeMillis = System.currentTimeMillis();
            if (controller.sendProgram("admin_send"+timeMillis)){
                return "true";
            }else return "发布信息失败！";
        }else return "连接大屏失败！";
    }

    @ResponseBody
    @PostMapping(value = "addScreen")
    @Transactional
    public boolean addScreen(@RequestParam String name, @RequestParam String ip){
        try {
            DynamicDataSource.router("50");
            ScreenUtil screenUtil = new ScreenUtil();
            screenUtil.init();
            // 首先需要检测是否能够搜索到此块大屏
            if (screenUtil.search(ip)){
                Screen screen = new Screen();
                screen = screenUtil.getScreenParam(screen);
                screen.setIp(ip);
                screen = screenDao.save(screen);
                Nav nav = new Nav(name,"","icon-desktop",3,0);
                nav = navDao.save(nav);
                Nav fnav1 = new Nav("大屏同步","dptb?lx="+screen.getId(),"icon-desktop",3,nav.getId());
                Nav fnav2 = new Nav("内容编辑","hyc?lx="+screen.getId(),"",3,nav.getId());
                Nav fnav3 = new Nav("文件管理","splist?lx="+screen.getId(),"",3,nav.getId());
                Nav fnav4 = new Nav("大屏监控","dpjk?lx="+screen.getId(),"",3,nav.getId());
                List<Nav> list = new ArrayList<>();
                list.add(fnav1);list.add(fnav2);list.add(fnav3);list.add(fnav4);
                navDao.saveAll(list);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
