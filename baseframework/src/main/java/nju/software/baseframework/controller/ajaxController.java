package nju.software.baseframework.controller;

import nju.software.baseframework.configuration.FilterConfig;
import nju.software.baseframework.data.dao.GgDao;
import nju.software.baseframework.data.dao.ScreenDao;
import nju.software.baseframework.data.dataobject.Ggb;
import nju.software.baseframework.data.dataobject.Screen;
import nju.software.baseframework.data.dataobject.Wjb;
import nju.software.baseframework.data.dataobject.Yhb;
import nju.software.baseframework.data.dynamicdDatabases.DynamicDataSource;
import nju.software.baseframework.data.vo.*;
import nju.software.baseframework.service.AdminService;
import nju.software.baseframework.service.AjlbService;
import nju.software.baseframework.service.GgService;
import nju.software.baseframework.util.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by 28643 on 2018/12/1.
 */
@Controller
public class ajaxController {
    @Autowired
    GgDao ggDao;

    @Autowired
    AjlbService ajlbService;

    @Resource
    GgService ggService;

    @Resource
    private AdminService adminService;

    @Autowired
    ScreenDao screenDao;

    /**
     * 今日公告
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/jrgg",method = RequestMethod.POST)
    @ResponseBody
    public TableModel<GgxqModel> getJrgg(@RequestParam String page,
                                   @RequestParam String rows,HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        int curPage = Integer.parseInt(page) -1;
        int pageSize = Integer.parseInt(rows);
        Yhb yh = (Yhb)session.getAttribute(FilterConfig.SESSION_KEY);
        PageRequest pageRequest = new PageRequest(curPage,pageSize);
        TableModel<GgxqModel> tableModel = ggService.getTodayGG(yh.getYhmc(),pageRequest);
        return tableModel;
    }


    /**
     * 案件列表显示
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @RequestMapping(value = "/ajlbTable",method = RequestMethod.POST)
    @ResponseBody
    public TableModel<AjlbModel> ajlbTable(@RequestParam String page,
                                           @RequestParam String rows,
                                           HttpServletRequest request,
                                           HttpSession session){
        String fydm = (String) session.getAttribute("fydm");
        DynamicDataSource.router(fydm);
        int curPage = Integer.parseInt(page) -1;
        int pageSize = Integer.parseInt(rows);

        String ah  = StringUtil.Normalize(request.getParameter("ah"));
        String ajmc= StringUtil.Normalize(request.getParameter("ajmc"));
        String dsr = StringUtil.Normalize(request.getParameter("dsr"));
        String spz = StringUtil.Normalize(request.getParameter("spz"));
        String spy = StringUtil.Normalize(request.getParameter("spy"));
        String ajxz= StringUtil.Normalize(request.getParameter("ajxz"));
        String ajzt= StringUtil.Normalize(request.getParameter("ajzt"));
        String spt = StringUtil.Normalize(request.getParameter("spt"));
        String jafs= StringUtil.Normalize(request.getParameter("jafs"));
        String sfgd= StringUtil.Normalize(request.getParameter("sfgd"));
        String sycx= StringUtil.Normalize(request.getParameter("sycx"));

        Yhb yh = (Yhb) session.getAttribute(FilterConfig.SESSION_KEY);
        PageRequest pageRequest = new PageRequest(curPage,pageSize);
        return ajlbService.searchAjlb(ah,ajmc,dsr,spz,spy,ajxz,ajzt,spt,jafs,
                sfgd,sycx,yh,pageRequest,fydm);
    }

    @ResponseBody
    @RequestMapping(value = "/ajlb",method = RequestMethod.POST)
    List<AjlbModel> getAjlb(HttpServletRequest request,HttpSession session){
        String fydm = (String) session.getAttribute("fydm");
        DynamicDataSource.router(fydm);
        String ah  =StringUtil.Normalize(request.getParameter("ah"));
        String ajmc=StringUtil.Normalize(request.getParameter("ajmc"));
        String dsr = StringUtil.Normalize(request.getParameter("dsr"));
        String spz = StringUtil.Normalize(request.getParameter("spz"));
        String spy = StringUtil.Normalize(request.getParameter("spy"));
        String ajxz= StringUtil.Normalize(request.getParameter("ajxz"));
        String ajzt= StringUtil.Normalize(request.getParameter("ajzt"));
        String spt = StringUtil.Normalize(request.getParameter("spt"));
        String jafs = StringUtil.Normalize(request.getParameter("jafs"));
        String sfgd = StringUtil.Normalize(request.getParameter("sfgd"));
        String sycx = StringUtil.Normalize(request.getParameter("sycx"));

        Yhb yh = (Yhb) session.getAttribute(FilterConfig.SESSION_KEY);
        List<AjlbModel> res =  ajlbService.getAllAjsj(ah,ajmc,dsr,
                spz,spy,ajxz,ajzt,spt,jafs,sfgd,sycx,yh,fydm);
        session.setAttribute("table",res);
        return res;
    }


    /**
     * 公告表显示
     * @param page
     * @param rows
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/ggTable",method = RequestMethod.POST)
    @ResponseBody
    public TableModel<GgxqModel> ggTable(@RequestParam String page,
                                         @RequestParam String rows,
                                         HttpServletRequest request,
                                         HttpSession session){
        int curPage = Integer.parseInt(page) -1;
        int pageSize = Integer.parseInt(rows);
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        String ah = StringUtil.Normalize(request.getParameter("ah"));
        String ajmc = StringUtil.Normalize(request.getParameter("ajmc"));
        String fbsj = StringUtil.Normalize(request.getParameter("fbsj"));
        Integer gglx = null;
        Integer ggzt = null;
        String lx = request.getParameter("gglx");
        String zt = request.getParameter("ggzt");
        if(lx!=null&&lx!=""){
            gglx = Integer.parseInt(lx);
        }
        if(zt!=null&&!zt.equals("")){
            ggzt = Integer.parseInt(zt);
        }

        Yhb yh = (Yhb)session.getAttribute(FilterConfig.SESSION_KEY);
        PageRequest pageRequest = new PageRequest(curPage,pageSize);
        return ggService.searchGglb(ah,ajmc,fbsj,gglx,ggzt,yh,pageRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/gglb",method = RequestMethod.POST)
    public List<GgxqModel> ggList(HttpServletRequest request,HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        String ah = StringUtil.Normalize(request.getParameter("ah"));
        String ajmc = StringUtil.Normalize(request.getParameter("ajmc"));
        String fbsj = StringUtil.Normalize(request.getParameter("fbsj"));
        Integer gglx = null;
        Integer ggzt = null;
        String lx = request.getParameter("gglx");
        String zt = request.getParameter("ggzt");
        if(lx!=null&&lx!=""){
            gglx = Integer.parseInt(lx);
        }
        if(zt!=null&&!zt.equals("")){
            ggzt = Integer.parseInt(zt);
        }

        Yhb yh = (Yhb)session.getAttribute(FilterConfig.SESSION_KEY);
        List<GgxqModel> res = ggService.searchGg(ah,ajmc,fbsj,gglx,ggzt,yh);
        session.setAttribute("table",res);
        return res;
    }

    /**
     * 公告数量统计
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/ggTJ",method = RequestMethod.POST)
    @ResponseBody
    public TableModel<GgTjModel> ggTJ(@RequestParam String page,
                                   @RequestParam String rows,
                                   HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        int curPage = Integer.parseInt(page) -1;
        int pageSize = Integer.parseInt(rows);
        List<GgTjModel> ggTjModels = (List)session.getAttribute("ggTj");
        if(ggTjModels.size()==0){
            return new TableModel<>(ggTjModels.size(),ggTjModels);
        }
        int total = ggTjModels.size();
        int fromIndex = curPage*pageSize;
        int toIndex = (curPage+1)*pageSize-1;
        toIndex = toIndex>total?total:toIndex;
        ggTjModels = ggTjModels.subList(fromIndex,toIndex);
        TableModel<GgTjModel> tab = new TableModel<GgTjModel>(total,ggTjModels);
        return tab;
    }

    /**
     *
     * 新增公告
     * @param ggInfo
     */
    @ResponseBody
    @RequestMapping(value = "/addgg",method = RequestMethod.POST)
    public Integer AddGg(@RequestBody String ggInfo, HttpSession session)
            throws UnsupportedEncodingException {
        ggInfo = URLDecoder.decode(ggInfo,"utf-8").replaceAll("=","");
        Ggb ggb = JsonUtil.fromJson(ggInfo,Ggb.class);
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        Integer ggfblx = adminService.getGgfblx();
        if(ggfblx==0){
            if(fydm.equals("120101 211")){
                ggb.setStatus(6);
            }
        }
        Yhb yh = (Yhb)session.getAttribute(FilterConfig.SESSION_KEY);
        ggb.setFbr(yh.getYhmc());
        ggb.setDqsj(DateUtil.getGgDqsj(ggb.getFbsj(),ggb.getGgsc()));
        Ggb res = ggDao.save(ggb);
        return res.getBh();
    }

    /**
     * 更新公告状态
     * @param ggzt
     * @param id
     */
    @ResponseBody
    @RequestMapping(value = "/updategg",method = RequestMethod.POST)
    public void UpdateGgzt(@RequestParam Integer ggzt,
                           @RequestParam Integer id,HttpSession session){
        String fydm = (String) session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        Ggb ggb = ggDao.findBybh(id);
        if(ggb.getStatus()==5) {
            ggb.setStatus(1);
        }
        ggb.setStatus(ggzt);
        ggDao.save(ggb);
    }

    /**
     * 获取案件当事人
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAjDsr",method = RequestMethod.POST)
    public Map<String,List<String>> getAJDsr(HttpServletRequest request, HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(fydm);
        return ggService.getAjDsr(request.getParameter("ah"));
    }

    /**
     * excel导出
     * @param titles
     * @param lx
     * @param response
     */
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    public void export(@RequestParam String[] titles,
                       @RequestParam String lx,
                       @RequestParam String[][] content,
                       HttpServletResponse response){
        String filename = "";
        String sheetName = "Sheet1";
        switch (lx){
            case "ajlb":
                filename = "案件列表"+System.currentTimeMillis()+".xls";
                break;
            case "ggb":
                filename = "公告列表"+System.currentTimeMillis()+".xls";
                break;
            case "yyb":
                filename = "预约列表"+System.currentTimeMillis()+".xls";
                break;
        }
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName,titles,content,null);

        try{
            this.setResponseHeader(response,filename);
            OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送响应流的方法
     * @param response
     * @param fileName
     */
    public void setResponseHeader(HttpServletResponse response,String fileName){
        try{
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename="+URLEncoder.encode(fileName,"utf-8"));
            response.addHeader("Pargam","no-cache");
            response.addHeader("Cache-Control","no-cache");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/ajgg",method = RequestMethod.POST)
    public TableModel<GgxqModel> getAjgg(HttpServletRequest request,
                                         HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        String ah = request.getParameter("ah");
        Integer ggzt = null;
        String zt = request.getParameter("ggzt");
        if(zt!=null){
            ggzt = Integer.parseInt(zt);
        }
        List<GgxqModel> list = ggService.getAjgg(ah,ggzt);
        TableModel<GgxqModel> tab = new TableModel<>(list.size(),list);
        return tab;
    }

    @ResponseBody
    @RequestMapping(value = "UpdateBfStatus",method = RequestMethod.POST)
    public void UpdateBfStatus(HttpServletRequest request,HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        Integer bh = Integer.valueOf(request.getParameter("bh"));
        Ggb ggb = ggDao.findBybh(bh);
        Integer status = Integer.valueOf(request.getParameter("zt"));
        ggb.setSfbf(status);
        ggDao.save(ggb);
    }

    @ResponseBody
    @RequestMapping(value = "UpdateScreenStatus",method = RequestMethod.POST)
    public void UpdateScreenStatus(@RequestParam Integer lx,
                                   @RequestParam Integer status){
        DynamicDataSource.router("50");
        Screen screen = screenDao.findByLx(lx);
        screen.setStatus(status);
        screenDao.save(screen);
    }

    @ResponseBody
    @RequestMapping(value = "getScreenStatus",method = RequestMethod.POST)
    public List<Integer> getStatus(@RequestParam Integer lx){
        DynamicDataSource.router("50");
        Screen screen = screenDao.findByLx(lx);
        List<Integer> res = new ArrayList<>();
        res.add(screen.getStatus());
        res.add(screen.getNrlb());
        return res;
    }
}
