package nju.software.baseframework.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import nju.software.baseframework.Timer.AppInitializer;
import nju.software.baseframework.configuration.FilterConfig;
import nju.software.baseframework.data.dao.*;
import nju.software.baseframework.data.dataobject.Ggb;
import nju.software.baseframework.data.dataobject.Yhb;
import nju.software.baseframework.data.dataobject.Yyb;
import nju.software.baseframework.data.dynamicdDatabases.DynamicDataSource;
import nju.software.baseframework.data.vo.*;
import nju.software.baseframework.service.AjlbService;
import nju.software.baseframework.service.YyService;
import nju.software.baseframework.util.ArrayUtil;
import nju.software.baseframework.util.DateUtil;
import nju.software.baseframework.util.JsonUtil;
import nju.software.baseframework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 28643 on 2018/12/7.
 */
@Controller
public class YYController {
    @Autowired
    YyService yyService;

    @Autowired
    AjlbService ajlbService;

    @Autowired
    YyDao yyDao;

    @Autowired
    AJlbDao aJlbDao;

    @Autowired
    GgDao ggDao;

    @Autowired
    NavDao navDao;
    @Autowired
    KtggDao ktggDao;
    @Autowired
    GgxxDao ggxxDao;

    @RequestMapping(value = "/yycx",method = RequestMethod.GET)
    public String yycx(ModelMap modelMap,HttpSession session){
        modelMap.addAttribute("yyzt",AppInitializer.yyzt);
        Yhb yh = (Yhb) session.getAttribute(FilterConfig.SESSION_KEY);
        modelMap.addAttribute("jfr",yh.getYhmc());
        return "yycx";
    }

    @RequestMapping(value = "/yyxq",method = RequestMethod.GET)
    public String yyxq(HttpServletRequest request,
                       HttpSession session, ModelMap modelMap){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        Yhb yh = (Yhb)session.getAttribute(FilterConfig.SESSION_KEY);
        String bh = request.getParameter("bh");
        YyxqModel yyxq = null;
        String ah = null;
        String lx = "1";
        if(bh!=null){
            Integer id = Integer.parseInt(bh);
            yyxq = yyService.getYyxxByBhAndAJXH(id);   //可能展示的模型要发生变化；
            ah = yyxq.getAh();
            if(ah==null) lx="3";
        }else {
            ah = request.getParameter("ah");
            yyxq = yyService.getYyxxByBhAndAJXH(yyService.getYybByAh(ah));
            lx = "2";
        }
        if(ah!=null){
            DynamicDataSource.router(fydm);
            AjxqModel ajxq = ajlbService.getAjxxByAh(ah);
            modelMap.addAttribute("ajxq",ajxq);
        }
        modelMap.addAttribute("lx",lx);
        modelMap.addAttribute("yyxq",yyxq);
        modelMap.addAttribute("jfr",yh.getYhmc());
        return "yyxq";
    }

    @RequestMapping(value = "/yyTable",method = RequestMethod.POST)
    @ResponseBody
    public TableModel<YyxqModel> yyTable(@RequestParam String page,
                                   @RequestParam String rows,
                                   HttpServletRequest request,
                                   HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        int curPage = Integer.parseInt(page) -1;
        int pageSize = Integer.parseInt(rows);

        String ah = StringUtil.Normalize(request.getParameter("ah"));
        String ajmc = StringUtil.Normalize(request.getParameter("ajmc"));
        String lfr = StringUtil.Normalize(request.getParameter("lfr"));
        String jfdd = StringUtil.Normalize(request.getParameter("jfdd"));
        String lxfs = StringUtil.Normalize(request.getParameter("lxfs"));
        String lfsj = StringUtil.Normalize(request.getParameter("lfsj"));
        Integer yyzt = null;
        String zt = request.getParameter("yyzt");
        if(zt!=null&&!zt.equals("")){
            yyzt = Integer.parseInt(zt);
        }
        Yhb yh = (Yhb)session.getAttribute(FilterConfig.SESSION_KEY);

        PageRequest pageRequest = new PageRequest(curPage,pageSize);
        return yyService.SearchYylb(ah,ajmc,lfr,jfdd,lxfs,lfsj,yyzt,yh,pageRequest);

    }


    @RequestMapping(value = "/yylb",method = RequestMethod.POST)
    @ResponseBody
    public List<YyxqModel> yyTable(HttpServletRequest request,
                                         HttpSession session){
        String fydm = (String)session.getAttribute("Fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        String ah = StringUtil.Normalize(request.getParameter("ah"));
        String ajmc = StringUtil.Normalize(request.getParameter("ajmc"));
        String lfr = StringUtil.Normalize(request.getParameter("lfr"));
        String jfdd = StringUtil.Normalize(request.getParameter("jfdd"));
        String lxfs = StringUtil.Normalize(request.getParameter("lxfs"));
        String lfsj = StringUtil.Normalize(request.getParameter("lfsj"));
        Integer yyzt = null;
        String zt = request.getParameter("yyzt");
        if(zt!=null&&!zt.equals("")){
            yyzt = Integer.parseInt(zt);
        }
        Yhb yh = (Yhb)session.getAttribute(FilterConfig.SESSION_KEY);

        List<YyxqModel> list = yyService.getYylb(ah,ajmc,lfr,jfdd,lxfs,lfsj,yyzt,yh);
        session.setAttribute("table",list);
        return list;
    }

    /*预约统计*/
    @RequestMapping(value = "/yyTJ",method = RequestMethod.POST)
    @ResponseBody
    public TableModel<yyTjModel> yyTJ(@RequestParam String page,
                                @RequestParam String rows,
                                HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        List<yyTjModel> pag = (List<yyTjModel>) session.getAttribute("yyTj");
        if(pag.size()==0){
            return new TableModel<>(pag.size(),pag);
        }
        int curPage = Integer.parseInt(page) -1;
        int pageSize = Integer.parseInt(rows);
        int total = pag.size();
        int fromIndex = curPage*pageSize;
        int toIndex = (curPage+1)*pageSize-1;
        toIndex = toIndex>total?total:toIndex;
        pag = pag.subList(fromIndex,toIndex);
        TableModel<yyTjModel> tab = new TableModel<>(total,pag);
        return tab;
    }

    @RequestMapping(value = "/addYy",method = RequestMethod.POST)
    @ResponseBody
    public Yyb AddYy(@RequestBody String yyxx,
                     HttpSession session) throws UnsupportedEncodingException {
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        yyxx = URLDecoder.decode(yyxx,"utf-8").replaceAll("=","");
        MultiYyModel yyInfo = JsonUtil.fromJson(yyxx,MultiYyModel.class);
        List<Yyb> yybs = ArrayUtil.getYybList(yyInfo);
        List<Yyb> _list = new ArrayList<>();
        for (Yyb yyb : yybs){
            _list.add(yyDao.save(yyb));
        }
        return _list.get(0);
    }

    @ResponseBody
    @RequestMapping(value = "/updateYy",method = RequestMethod.POST)
    public void updateYy(HttpServletRequest request,
                         HttpSession session) {
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        String bh = request.getParameter("bh");
        String yyzt = request.getParameter("zt");
        Yyb yyb = yyService.findByBh(Integer.parseInt(bh));
        yyb.setYyzt(Integer.parseInt(yyzt));
        yyDao.save(yyb);
    }

    /*今日预约*/
    @RequestMapping(value = "/jryy",method = RequestMethod.POST)
    @ResponseBody
    public TableModel<YyxqModel> getJryy(@RequestParam String page,
                                   @RequestParam String rows,HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        int curPage = Integer.parseInt(page) - 1;
        int pageSize = Integer.parseInt(rows);
        Yhb yh = (Yhb)session.getAttribute(FilterConfig.SESSION_KEY);
        PageRequest pageRequest = new PageRequest(curPage,pageSize);
        TableModel<YyxqModel> tab = yyService.getTodayData(yh.getYhmc(),pageRequest);
        return tab;
    }

    /*获得数据统计中chart的数据*/
    @RequestMapping(value = "/yyChart",method = RequestMethod.POST)
    @ResponseBody
    public String getYyChart(HttpServletRequest request,HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        String spt = StringUtil.Normalize(request.getParameter("spt"));
        String ajxz = StringUtil.Normalize(request.getParameter("ajxz"));
        String jafs = StringUtil.Normalize(request.getParameter("jafs"));
        String cbr = StringUtil.Normalize(request.getParameter("cbr"));
        String begin = StringUtil.Normalize(request.getParameter("begin"));
        String last = StringUtil.Normalize(request.getParameter("last"));
        List<YyChartModel> yyChartModels = new ArrayList<>();
       //         yyService.getYyChart(spt,ajxz,jafs,cbr,begin,last);
        List _list = yyDao.getYyChart();
        for (Object row : _list) {
            Object[] objects = (Object[])row;
            YyChartModel yyChartModel = new YyChartModel();
            yyChartModel.setYyzt((int)objects[0]);
            BigInteger t = (BigInteger)objects[1];
            String s = t.toString();
            yyChartModel.setNum(Integer.parseInt(s));
            yyChartModels.add(yyChartModel);
        }
        return JsonUtil.toJson(yyChartModels);
    }

    /*获得数据统计中chart的数据*/
    @RequestMapping(value = "/ggChart",method = RequestMethod.POST)
    @ResponseBody
    public String getGgChart(HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        List<GgChartModel> ggChartModels = new ArrayList<>();
        List _list = ggDao.getGgChart();
        for (Object row : _list) {
            Object[] objects = (Object[])row;
            GgChartModel ggChartModel = new GgChartModel();
            ggChartModel.setGglx((int)objects[0]);
            BigInteger t = (BigInteger)objects[1];
            String s = t.toString();
            ggChartModel.setNum(Integer.parseInt(s));
            ggChartModels.add(ggChartModel);
        }
        return JsonUtil.toJson(ggChartModels);
    }

    @ResponseBody
    @RequestMapping(value = "/ajyy",method = RequestMethod.POST)
    public TableModel<YyxqModel> getAjyy(HttpServletRequest request,
                                         HttpSession session){
        String fydm = (String)session.getAttribute("fydm");
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        String zt = request.getParameter("yyzt");
        Integer yyzt = null;
        if(zt!=null){
             yyzt = Integer.parseInt(zt);
        }
        String ah = request.getParameter("ah");
        List<Yyb> list = yyService.getAjYy(ah,yyzt);
        List<YyxqModel> yyxqModels = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            yyxqModels.add(new YyxqModel(list.get(i)));
        }
        TableModel<YyxqModel> tab = new TableModel<>(yyxqModels.size(),yyxqModels);
        return tab;
    }

    @ResponseBody
    @RequestMapping(value = "TransYyxx",method = RequestMethod.POST)
    public String TransYyxx(@RequestBody String requestStr){
        Gson gson = new Gson();
        try {
            requestStr = URLDecoder.decode(requestStr,"utf-8").replaceAll("=","");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JsonObject obj = new JsonParser().parse(requestStr).getAsJsonObject();
        String fydm = obj.get("fydm").getAsString();
        DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
        String str = "";
        String yysj = DateUtil.format(new Date(),DateUtil.shortFormat);
        String sql = "select yyb from Yyb yyb where yyb.yysj='"+yysj+"'";
        List<Yyb> yybs = aJlbDao.getList(sql,Yyb.class,null);
        List<Yyxx> _list = new ArrayList<>();
        for (Yyb yyb : yybs){
            if (yyb.getLfrsfzh()==null||yyb.getLfrsfzh().equals("")){
                DynamicDataSource.router(fydm);
                String sfzh = aJlbDao.getSfzh(yyb.getAh(),yyb.getLfr());
                yyb.setLfrsfzh(sfzh);
                DynamicDataSource.router(FYEnum.getFybhByFydm(fydm));
                yyDao.save(yyb);
            }
            _list.add(new Yyxx(yyb));
        }

        String s = gson.toJson(_list);
        JsonArray jsonArray = new JsonParser().parse(s).getAsJsonArray();
        JsonObject object = new JsonObject();
        object.addProperty("fydm",fydm);
        object.add("data",jsonArray);
        try {
            str = new String(object.toString().getBytes(),"UTF-8");
        }catch (UnsupportedEncodingException e){
            System.out.println(e);
        }
        return str;
    }
}
