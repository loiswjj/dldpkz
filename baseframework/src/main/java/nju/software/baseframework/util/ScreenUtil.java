package nju.software.baseframework.util;

import lombok.extern.slf4j.Slf4j;
import nju.software.baseframework.data.dataobject.Ggb;
import nju.software.baseframework.data.dataobject.Screen;
import nju.software.baseframework.data.dataobject.Wjb;
import nju.software.baseframework.data.dataobject.XycjSxbzxr;
import nju.software.baseframework.data.vo.MediaContent;
import nju.software.baseframework.data.vo.PageContent;
import nju.software.baseframework.data.vo.ProgramContent;
import nju.software.baseframework.data.vo.TextModel;
import novj.platform.vxkit.common.bean.login.LoginResultBean;
import novj.platform.vxkit.common.bean.monitor.DiskSizeBean;
import novj.platform.vxkit.common.bean.programinfo.*;
import novj.platform.vxkit.common.bean.search.SearchResult;
import novj.platform.vxkit.common.result.DefaultOnResultListener;
import novj.platform.vxkit.common.result.OnResultListenerN;
import novj.platform.vxkit.handy.api.ProgramSendManager;
import novj.platform.vxkit.handy.api.SearchManager;
import novj.platform.vxkit.handy.net.transfer.OnFileTransferListener;
import novj.publ.api.ErrorCode;
import novj.publ.api.NovaOpt;
import novj.publ.api.actions.ProgramManager;
import novj.publ.api.beans.NormalTextBean;
import novj.publ.api.beans.TimingParamBean;
import novj.publ.net.exception.ErrorDetail;
import novj.publ.net.svolley.Request.IRequestBase;
import novj.publ.util.FileUtils;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jingjing
 * @date 2019/11/15
 * @description 海事大屏控制器
 */
@Slf4j
public class ScreenUtil {
    private static SearchResult searchResult = null;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private static Integer code = 0;
    private static double rate;

    private String programDir = "D://";

    public void init() {
        // windows
        NovaOpt.GetInstance().initialize(2);
    }

    /**
     * 与大屏建立连接
     *
     * @param remoteIp
     * @return
     */
    public boolean connectWithLed(String remoteIp) {
        if (search(remoteIp)) {
            if (0 == connect()) {
                return login();
            }
        }
        return false;
    }

    /**
     * 搜索指定IP大屏
     *
     * @param remoteIp
     * @return
     */
    public boolean search(String remoteIp) {
        try {
            NovaOpt.GetInstance().searchScreen(new SearchManager.OnScreenSearchListener() {
                @Override
                public void onSuccess(SearchResult searchResult) {
                    ScreenUtil.searchResult = searchResult;
                    lock.lock();
                    try {
                        condition.signal();
                    } finally {
                        lock.unlock();
                    }
                }

                @Override
                public void onError(ErrorDetail errorDetail) {
                    lock.lock();
                    try {
                        condition.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            }, remoteIp);
        } catch (Exception e) {
            // 啥都不做
        }

        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        if (searchResult == null) return false;
        else return true;
    }

    /**
     * 建立连接
     */
    private int connect() {
        if (searchResult != null) {
            // 正式建立连接
            NovaOpt.GetInstance().connectDevice(searchResult, new DefaultOnResultListener() {
                @Override
                public void onSuccess(Integer integer) {
                    code = integer;
                    lock.lock();
                    try {
                        condition.signal();
                    } finally {
                        lock.unlock();
                    }
                }

                @Override
                public void onError(ErrorDetail errorDetail) {
                    code = errorDetail.errorCode;
                    lock.lock();
                    try {
                        condition.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            }, wrapper -> {

            });
        }
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return code;
    }

    /**
     * 登录系统
     *
     * @return
     */
    private boolean login() {
        String username = "admin";
        String password = "123456";
        if (!searchResult.logined) {
            // 如果为登录设备需要登录
            NovaOpt.GetInstance().login(searchResult.sn, username, password, new OnResultListenerN<LoginResultBean, ErrorDetail>() {
                @Override
                public void onSuccess(IRequestBase iRequestBase, LoginResultBean loginResultBean) {
                    searchResult.logined = true;
                    lock.lock();
                    try {
                        condition.signal();
                    } finally {
                        lock.unlock();
                    }
                }

                @Override
                public void onError(IRequestBase iRequestBase, ErrorDetail errorDetail) {
                    lock.lock();
                    try {
                        condition.signal();
                    } finally {
                        lock.unlock();
                    }
                }
            });
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        return searchResult.logined;
    }

    /**
     * 退出登录
     */
    public void logout() {
        NovaOpt.GetInstance().logOut(searchResult.sn);
    }

    public Screen getScreenParam(Screen screen) {
        screen.setWidth(searchResult.width);
        screen.setHeight(searchResult.height);
        return screen;
    }

    /**
     * 获取终端设备可用内存率（百分比）
     */
    public double getAvailableMemoryData() {
        NovaOpt.GetInstance().getAvailableMemoryData(searchResult.sn, new OnResultListenerN<Float, ErrorDetail>() {
            @Override
            public void onSuccess(IRequestBase iRequestBase, Float aFloat) {
                rate = aFloat;
                lock.lock();
                try {
                    condition.signal();
                } finally {
                    lock.unlock();
                }
            }

            @Override
            public void onError(IRequestBase iRequestBase, ErrorDetail errorDetail) {
                lock.lock();
                try {
                    condition.signal();
                } finally {
                    lock.unlock();
                }
            }
        });
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return rate;
    }

    /**
     * 获取终端设备已用硬盘空间所占比例
     *
     * @return
     */
    public double getAvailableStorageData() {
        NovaOpt.GetInstance().getAvailableStorageData(searchResult.sn, new OnResultListenerN<DiskSizeBean, ErrorDetail>() {
            @Override
            public void onSuccess(IRequestBase iRequestBase, DiskSizeBean diskSizeBean) {
                rate = (diskSizeBean.getDiskTotalSize() - diskSizeBean.getDiskAvailableSize()) * 100 /
                        diskSizeBean.getDiskTotalSize();
                lock.lock();
                try {
                    condition.signal();
                } finally {
                    lock.unlock();
                }
            }

            @Override
            public void onError(IRequestBase iRequestBase, ErrorDetail errorDetail) {

            }
        });
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return rate;
    }

    /**
     * 将获取到的大屏终端播放图片存至本地
     *
     * @param fileDir
     * @param fileName
     * @return
     */
    public boolean getScreenshot(String fileDir, String fileName) {
        NovaOpt.GetInstance().downLoadScreenshot(searchResult.sn, searchResult.width, searchResult.height,
                0, fileDir, fileName, new OnFileTransferListener() {
                    @Override
                    public void onTransferred(long l) {

                    }

                    @Override
                    public void onStartTransfer() {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        code = 1;
                        lock.lock();
                        try {
                            condition.signal();
                        } finally {
                            lock.unlock();
                        }
                    }

                    @Override
                    public void onError(ErrorDetail errorDetail) {
                        code = 0;
                        lock.lock();
                        try {
                            condition.signal();
                        } finally {
                            lock.unlock();
                        }
                    }
                });
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return code == 1;
    }

    /**
     * 大屏终端信息初始化
     *
     * @param ProgramName
     * @return
     */
    public void initScreen(String ProgramName) {
        NovaOpt.GetInstance().createProgram(ProgramName, searchResult.width, searchResult.height);
    }

    public int addPage() {
        return NovaOpt.GetInstance().addPage();
    }

    /**
     * 添加不同类型的多媒体文件
     *
     * @param pageId
     * @param mediaType
     * @param param
     * @return 返回值为当前媒体ID （唯一标识）
     */
    public int addWidget(int pageId, ProgramManager.WidgetMediaType mediaType, Object param) {
        return NovaOpt.GetInstance().addWidget(pageId, mediaType, param);
    }

    /**
     * 发送文本信息时的相关配置
     *
     * @param textStyle
     */
    public Widget setTextStyle(TextModel textStyle, Widget widget) {
        Layout layout = new Layout(textStyle.getOffset_x(), textStyle.getOffset_y(),
                textStyle.getWidth(), textStyle.getHeight());
        widget.setDisplayRatio("CUSTOM");
        widget.setLayout(layout);
        widget.setEnable(true);

        // 设置文本状态
        MetaDataArchText metaDataArchText = (MetaDataArchText) widget.getMetadata();
        metaDataArchText.getContent().getDisplayStyle().setType(textStyle.getType());
        // 设置滚动速度 2.0
        metaDataArchText.getContent().getDisplayStyle().getScrollAttributes().getEffects().setSpeed(textStyle.getSpeed());

        ArchTextAttribute archTextAttribute = metaDataArchText.getContent().getTextAttributes().get(0);
        // 设置字体大小
        archTextAttribute.getAttributes().getFont().setSize(textStyle.getFontsize());
        // 设置字体颜色 - 默认红色
        archTextAttribute.getAttributes().setTextColor(textStyle.getText_color());

        List<ArchLine> lines = new ArrayList<>();
        ArchLine line = new ArchLine();
        List<ArchSeg> segs = new ArrayList<>();
        String content = textStyle.getContent();
        if (null != content && !content.isEmpty()) {
            String[] arry = content.split("\r\n");
            for (String str : arry) {
                ArchSeg seg = new ArchSeg();
                seg.setAttributeKey(0);
                seg.setContent(str);
                segs.add(seg);
            }
        }
        //endregion
        line.setSegs(segs);
        lines.add(line);
        int count = content.split("\n").length;
        metaDataArchText.getContent().getParagraphs().get(0).setLines(lines);
        metaDataArchText.getContent().getParagraphs().get(0).setHorizontalAlignment(textStyle.getAlign_x());
        metaDataArchText.getContent().getParagraphs().get(0).setVerticalAlignment(textStyle.getAlign_y());
        widget.setMetadata(metaDataArchText);
        widget.setDuration(count * textStyle.getDuration());  // 以ms为单位
        return widget;
    }

    private void setPageTitle(int pageId, String title, int width) {
        TextModel textModel = new TextModel(title, "0%", "0%",
                "100%", width * 100 / searchResult.height + "%");
        textModel.setType("STATIC");
        textModel.setAlign_x("CENTER");
        textModel.setAlign_y("CENTER");
        int widget_id = addWidget(pageId, ProgramManager.WidgetMediaType.ARCH_TEXT, new NormalTextBean(""));
        Widget widget = NovaOpt.GetInstance().getWidgetParam(pageId, widget_id);
        widget = setTextStyle(textModel, widget);
        NovaOpt.GetInstance().setWidgetParam(pageId, widget_id, widget);
    }

    private void setPageContent(int pageId, StringBuffer content, int width, int height, int left) {
        TextModel textModel = new TextModel(content.toString(), left + "%", height * 100 / searchResult.height + "%",
                width + "%", (searchResult.height - height) * 100 / searchResult.height + "%");
        textModel.setFontsize(36);
        textModel.setAlign_x("LEFT");
        textModel.setAlign_y("TOP");
        textModel.setType("SCROLL");
        int widget_id = addWidget(pageId, ProgramManager.WidgetMediaType.ARCH_TEXT, new NormalTextBean(""));
        Widget widget = NovaOpt.GetInstance().getWidgetParam(pageId, widget_id);
        widget = setTextStyle(textModel, widget);
        NovaOpt.GetInstance().setWidgetParam(pageId, widget_id, widget);
    }

    public void createKTGGProgram(List<Ggb> list, int pageId) {
        // Title 设置
        setPageTitle(pageId, "天津东丽法院\n公告", 200);

        // 设置主体部分
        StringBuffer stringBuffer = new StringBuffer();
        for (Ggb gg : list) {
            stringBuffer.append(getDealStr(gg.getAh(), 36, searchResult.width) + "      " + gg.getGgnr() + "\n"
                    + getDealStr(DateUtil.format(gg.getFbsj(), DateUtil.chineseDtFormat), 36, searchResult.width) + "\n");
        }
        setPageContent(pageId, stringBuffer, 100, 200, 0);
    }

    private String getDealSfzh(XycjSxbzxr sxbzxr) {
        if (sxbzxr.getZjhm() != null && sxbzxr.getZjhm() != ""
                && sxbzxr.getZjhm().length() >= 18) {
            String sfzh = sxbzxr.getZjhm().substring(0, 10) + "****"
                    + sxbzxr.getZjhm().substring(14, 18);
            return sfzh;
        }
        return null;
    }

    /**
     * 创建失信被执行人
     *
     * @param zrlList
     * @param zzjgList
     * @return
     */
    public void createSxbzxrProgram(List<XycjSxbzxr> zrlList, List<XycjSxbzxr> zzjgList, int pageId) {
        // Title 设置
        setPageTitle(pageId, "失信被执行人公示", 200);

        // 设置主体部分
        // 各占50%
        StringBuffer zrlString = new StringBuffer();
        for (XycjSxbzxr zrl : zrlList) {
            String sfzh = getDealSfzh(zrl);
            if (sfzh != null) zrlString.append(zrl.getBzxrmc() + "         " + sfzh + "\n");
        }
        StringBuffer frString = new StringBuffer();
        for (XycjSxbzxr fr : zzjgList) {
            String sfzh = getDealSfzh(fr);
            if (sfzh != null) zrlString.append(fr.getBzxrmc() + "         " + sfzh + "\n");
        }
        setPageContent(pageId, zrlString, 50, 200, 0);
        setPageContent(pageId, frString, 50, 200, 50);
    }

    private Widget saveMediaParams(Widget widget, MediaContent mediaContent) {
        File file = new File(mediaContent.getFilepath());
//         设置内容
        widget.setOriginalDataSource(file.getAbsolutePath());
        widget.setFilesize(file.length());
        widget.setName(mediaContent.getMediaName());
        String md5 = FileUtils.getMD5(file);
        String suffix = file.getName().substring(file.getName().lastIndexOf("."));
        widget.setDataSource(md5 + suffix);

        // 设置位置信息
        Layout layout = new Layout(mediaContent.getX() * 100 / searchResult.width + "%",
                mediaContent.getY() * 100 / searchResult.height + "%",
                mediaContent.getWidth() * 100 / searchResult.width + "%",
                mediaContent.getHeight() * 100 / searchResult.height + "%");
        widget.setDisplayRatio("CUSTOM");
        widget.setLayout(layout);
        // 设置重复次数
        widget.setRepeatCount(1);
        if (mediaContent.getType().equals("image")) {
            DisplayStyle dis=new DisplayStyle();
            DisplayStyle.ScrollAttributes style=new DisplayStyle.ScrollAttributes();
            style.setEffects(new Effect(5f,"MARQUEE_LEFT"));
            dis.setScrollAttributes(style);
            widget.setMetadata(dis);
            widget.setDuration(mediaContent.getDuration());
        }
        return widget;
    }

    private void saveClockParams(int pageId, int widget_id, MediaContent mediaContent) {
        Widget widget = NovaOpt.GetInstance().getWidgetParam(pageId, widget_id);
        // 设置位置信息
        Layout layout = new Layout(mediaContent.getX() + "%", mediaContent.getY() + "%",
                mediaContent.getWidth() + "%", mediaContent.getHeight() + "%");
        widget.setLayout(layout);
        widget.setDuration(mediaContent.getDuration());
        // 设置内容
        MetaDataDigitalClockV2 metaDataDigitalClockV2 = (MetaDataDigitalClockV2) widget.getMetadata();
        String dateRegular = "$MM/$dd/$yyyy";
        String weekRegular = "$E";
        String timeRegular = "$hh:$mm:$ss";

        metaDataDigitalClockV2.setRegular(dateRegular + "&#160;" + weekRegular + "&#160;" + timeRegular);
        widget.setMetadata(metaDataDigitalClockV2);
        NovaOpt.GetInstance().setWidgetParam(pageId, widget_id, widget);
    }

    /**
     * 计算如果靠右需要左边空多少个空格
     *
     * @param s
     * @param fontsize
     * @return
     */
    private StringBuffer getDealStr(String s, int fontsize, int width) {
        Font font = new Font("Microsoft YaHei", Font.PLAIN, fontsize);
        FontMetrics fontMetrics = FontDesignMetrics.getMetrics(font);
        int strWidth = fontMetrics.stringWidth(s);
        int charWidth = fontMetrics.charWidth(' '); // 计算空格的长度
        int count = (width - strWidth) / charWidth;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < count; i++) {
            stringBuffer.append(" ");
        }
        stringBuffer.append(s + "\n");
        return stringBuffer;
    }


    /**
     * 获取设备MAC地址
     *
     * @param ia
     * @return
     * @throws SocketException
     */
    private static String getLocalMac(InetAddress ia) throws SocketException {
        //获取网卡，获取地址
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                //字节转换为整数
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                if (str.length() == 1) {
                    sb.append("0" + str);
                } else {
                    sb.append(str);
                }
            }
            return sb.toString().toUpperCase();
        } else if (osName.startsWith("Linux")) {
            String command = "/bin/sh -c ifconfig -a";
            try {
                StringBuilder sBuilder = new StringBuilder();
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader br = new BufferedReader(new InputStreamReader(process
                        .getInputStream()));
                String line;
                while (null != (line = br.readLine())) {
                    if (line.indexOf("ether") > 0) {
                        String address = line.split("ether")[1].trim()
                                .split("txqueuelen")[0].trim();
                        String[] macSplit = address.split(":");
                        for (int i = 0; i < macSplit.length; i++) {
                            if (0 != i) {
                                sBuilder.append("-");
                            }
                            if (macSplit[i].length() == 1) {
                                sBuilder.append("0" + macSplit[i]);
                            } else {
                                sBuilder.append(macSplit[i]);
                            }
                        }
                        break;
                    }
                }
                br.close();
                return sBuilder.toString().toUpperCase();
            } catch (Exception e) {

            }
        }
        return "";
    }

    /**
     * 发送节目至控制器
     *
     * @param programName
     * @return
     */
    public boolean sendProgram(String programName) {
        File dir = new File(programDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (0 == NovaOpt.GetInstance().makeProgram(programDir)) {
            InetAddress inetAddress;
            try {
                inetAddress = InetAddress.getLocalHost();
                NovaOpt.GetInstance().startTransfer(searchResult.sn, programDir, programName,
                        getLocalMac(inetAddress), true,
                        new ProgramSendManager.OnProgramTransferListener() {
                            @Override
                            public void onStarted() {
                                log.info("发送任务开始");
                            }

                            @Override
                            public void onTransfer(long l, long l1) {
                                System.out.println("已传送: " + l + "/" + l1);
                            }

                            @Override
                            public void onError(ErrorDetail errorDetail) {
                                code = 1;
                                lock.lock();
                                try {
                                    condition.signal();
                                } finally {
                                    lock.unlock();
                                }
                            }

                            @Override
                            public void onCompleted() {
                                code = 0;
                                lock.lock();
                                try {
                                    condition.signal();
                                } finally {
                                    lock.unlock();
                                }
                            }

                            @Override
                            public void onAborted() {

                            }
                        });
                lock.lock();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                logout();
                return code == 0 ? true : false;
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 添加定时bean
     *
     * @param pageContent
     * @return
     */
    public String addTimingBean(ProgramContent pageContent) {
        TimingParamBean bean = NovaOpt.GetInstance().createDefaultTimingParam();
        bean.setProgramType(TimingParamBean.TIMING_PROGRAM);
        bean.setStartDate(pageContent.getStartDate());
        bean.setEndDate(pageContent.getEndDate());
        if (pageContent.getSelectedweek().size() == 0) {
            log.error("no selected time");
            return "no selected time";
        }
        HashSet<Integer> days = new HashSet<>(pageContent.getSelectedweek());
        bean.setWeekDays(days);
        bean.setId(NovaOpt.GetInstance().getTimingPlayParams().size() + 1);
        int tag = NovaOpt.GetInstance().addTimingPlayParam(bean);
        if (tag != ErrorCode.Err_None) {
            log.error("error_code: {}", tag);
            return "add timing bean error!";
        }
        return "success";
    }

    /**
     * 发布定时任务
     *
     * @param programName
     * @return
     */
    public boolean sendTimingProgram(String programName) {
        if (0 == NovaOpt.GetInstance().makeTimingProgram(programName, programDir)) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                NovaOpt.GetInstance().startTransferTimingProgram(searchResult.sn, programDir, programName,
                        getLocalMac(inetAddress), true,
                        new ProgramSendManager.OnProgramTransferListener() {
                            @Override
                            public void onStarted() {
                                log.info("transfer task started");
                            }

                            @Override
                            public void onTransfer(long l, long l1) {
                                System.out.println("已传送: " + l + "/" + l1);
                            }

                            @Override
                            public void onError(ErrorDetail errorDetail) {
                                log.error(errorDetail.description);
                                code = 1;
                                lock.lock();
                                try {
                                    condition.signal();
                                } finally {
                                    lock.unlock();
                                }
                            }

                            @Override
                            public void onCompleted() {
                                log.info("completed");
                                code = 0;
                                lock.lock();
                                try {
                                    condition.signal();
                                } finally {
                                    lock.unlock();
                                }
                            }

                            @Override
                            public void onAborted() {

                            }
                        });
                lock.lock();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                logout();
                return code == 0 ? true : false;
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 手动新增
     *
     * @param content
     */
    public void addManualPage(ProgramContent content) {
        for (PageContent pageContent : content.getPageContents()) {
            int page_id = addPage();
            PageItem pageItem = NovaOpt.GetInstance().getPageItem(page_id);
            pageItem.setName(pageContent.getPagename());
            // 添加
            int widget_id;
            Widget widget ;
            for (MediaContent mediaContent : pageContent.getMediaContents()) {
                String type = mediaContent.getType();
                switch (type) {
                    case "PICTURE":
                        widget_id = addWidget(page_id, ProgramManager.WidgetMediaType.PICTURE,
                                new File(mediaContent.getFilepath()));
                        widget = NovaOpt.GetInstance().getWidgetParam(page_id, widget_id);
                        widget = saveMediaParams(widget, mediaContent);
                        NovaOpt.GetInstance().setWidgetParam(page_id, widget_id, widget);
                        break;
                    case "VIDEO":
                        widget_id = addWidget(page_id, ProgramManager.WidgetMediaType.VIDEO,
                                new File(mediaContent.getFilepath()));
                        widget = NovaOpt.GetInstance().getWidgetParam(page_id, widget_id);
                        saveMediaParams(widget, mediaContent);
                        NovaOpt.GetInstance().setWidgetParam(page_id, widget_id, widget);
                        break;
                    case "GIF":
                        widget_id = addWidget(page_id, ProgramManager.WidgetMediaType.GIF,
                                new File(mediaContent.getFilepath()));
                        widget = NovaOpt.GetInstance().getWidgetParam(page_id, widget_id);
                        saveMediaParams(widget, mediaContent);
                        NovaOpt.GetInstance().setWidgetParam(page_id, widget_id, widget);
                        break;
                    case "ARCH_TEXT":
                        widget_id = addWidget(page_id, ProgramManager.WidgetMediaType.ARCH_TEXT, new NormalTextBean(""));
                        widget = NovaOpt.GetInstance().getWidgetParam(page_id, widget_id);
                        widget = setTextStyle(new TextModel(mediaContent), widget);
                        NovaOpt.GetInstance().setWidgetParam(page_id, widget_id, widget);
                        break;
                    case "DIGITAL_CLOCK":
                        widget_id = addWidget(page_id, ProgramManager.WidgetMediaType.DIGITAL_CLOCK,
                                new MetaDataDigitalClockV2());
                        saveClockParams(page_id, widget_id, mediaContent);
                        break;
                }
            }
        }
    }

    /**
     * 轮播仅限于公告与上传文件之间
     */
    public void UploadFiles(List<Wjb> wjbs) {
        int widget_id;
        Widget widget;
        for (Wjb wjb : wjbs) {
            int pageId = addPage();
            MediaContent mediaContent = new MediaContent(0, 0, searchResult.width,
                    searchResult.height, "D:\\Upload\\" + wjb.getBcwz());
            mediaContent.setType(wjb.getType());
            if (wjb.getType().equals("image")) {
                // 文件
                widget_id = addWidget(pageId, ProgramManager.WidgetMediaType.PICTURE, "D:\\Upload\\" + wjb.getBcwz());
            } else {
                widget_id = addWidget(pageId, ProgramManager.WidgetMediaType.VIDEO, "D:\\Upload\\" + wjb.getBcwz());
            }
            widget = NovaOpt.GetInstance().getWidgetParam(pageId, widget_id);
            widget = saveMediaParams(widget, mediaContent);
            NovaOpt.GetInstance().setWidgetParam(pageId,widget_id,widget);
        }
    }
//     public void Carousel(int[] arr, Map<Integer,List<Ggb>> ggMap, List<Wjb> wjbs,int pageId){
//         PageItem pageItem = NovaOpt.GetInstance().getPageItem(pageId);
//         WidgetContainer widgetContainer = new WidgetContainer();
//         //首先需要创建一个100%的窗口
//         widgetContainer.setLayout(new Layout("0%","0%","100%","100%"));
//         Contents contents = new Contents();
//         Widget widget = null;
//         int widget_id = 0;
//         //这里仅设计了开庭公告与上传文件之间的轮播
//         // 设置主体部分
//         List<Widget> widgets = new ArrayList<>();
//         for (int item : arr) {
//             if (item != 8 && item != 10) {
//                 StringBuffer stringBuffer = new StringBuffer();
//                 for (Ggb gg: ggMap.get(item)){
//                     stringBuffer.append(getDealStr(gg.getAh(),36,searchResult.width) +"      "+gg.getGgnr()+"\n"
//                             +getDealStr(DateUtil.format(gg.getFbsj(),DateUtil.chineseDtFormat),36,searchResult.width)+"\n");
//                 }
//                 TextModel textModel = new TextModel(stringBuffer.toString(),"0%","0%", "100%","100%");
//                 textModel.setFontsize(36);
//                 textModel.setAlign_x("LEFT");
//                 textModel.setType("SCROLL");
//                 widget_id = addWidget(pageId, ProgramManager.WidgetMediaType.ARCH_TEXT,new NormalTextBean(""));
//                 widget = NovaOpt.GetInstance().getWidgetParam(pageId,widget_id);
//                 widget.setMetadata(new NormalTextBean(""));
//                 widget = setTextStyle(textModel,widget);
//                 widgets.add(widget);
//             }
//             else if (item == 10) {
//                 // 添加所有的文件
//                 for (Wjb wjb:wjbs){
//                     MediaContent mediaContent = new MediaContent(0,0,searchResult.width,
//                             searchResult.height,"D:\\Upload\\"+wjb.getBcwz());
//                     if (wjb.getType().equals("image")){
//                         // 文件
//                         widget_id = addWidget(pageId, ProgramManager.WidgetMediaType.PICTURE,"D:\\Upload\\"+wjb.getBcwz());
//                         widget = NovaOpt.GetInstance().getWidgetParam(pageId,widget_id);
//                         widget = saveMediaParams(widget,mediaContent);
//                         widgets.add(widget);
//                     }else {
//                         widget_id = addWidget(pageId, ProgramManager.WidgetMediaType.VIDEO,"D:\\Upload\\"+wjb.getBcwz());
//                         widget = NovaOpt.GetInstance().getWidgetParam(pageId,widget_id);
//                         widget = saveMediaParams(widget,mediaContent);
//                         widgets.add(widget);
//                     }
//                 }
//             }
//         }
//         contents.setWidgets(widgets);
//         widgetContainer.setContents(contents);
//         widgetContainer.setEnable(true);
//         List<WidgetContainer> widgetContainers = new ArrayList<>();
//         widgetContainers.add(widgetContainer);
//         pageItem.setWidgetContainers(widgetContainers);
//     }

    public static void main(String[] args) {
        String content = "(2019)津0110民初5268号\n      本院定于2020年01月13日09时00分在第十四法庭公开审理天津市东丽区民用公房管理所与张淼宗房屋租赁合同纠纷一案。\n            2019年12月16日\n\n(2019)津0110民初9935号\n      本院定于2020年01月08日09时00分在第九法庭公开审理周春华与上海尼雅孜信息技术有限公司买卖合同纠纷一案。\n            2019年12月20日\n\n(2019)津0110民初9918号\n      本院定于2020年01月06日09时00分在第一中法庭公开审理天津跨越物流有限公司与殷明刚经济补偿金纠纷一案。\n            2019年12月27日\n\n(2019)津0110民初6766号\n      本院定于2020年01月10日09时00分在第九法庭公开审理王金珍、王金梅与王仁增、王思明、韩金昌、韩勇继承纠纷一案。\n            2020年01月03日\n\n(2019)津0110民初8644号\n      本院定于2020年01月06日14时00分在第十五法庭公开审理李雪与金融街东丽湖（天津）置业有限公司、北京金融街物业管理有限责任公司天津分公司、何大双财产损害赔偿纠纷一案。\n            2020年01月03日\n\n(2019)津0110民初8985号\n      本院定于2020年01月08日09时00分在第十四法庭公开审理黄俊峰与天津房摆渡网络科技有限公司返还原物纠纷一案。\n            2020年01月03日\n\n(2019)津0110民初9653号\n      本院定于2020年01月09日14时00分在第十三法庭公开审理张长玲与孙福晶,刘廷玺,张文兰申请执行人执行异议之诉一案。\n            2020年01月03日\n\n(2019)津0110民初9862号\n      本院定于2020年01月06日09时00分在第十五法庭公开审理天津市詹鑫工贸有限公司与孙敬如排除妨害纠纷一案。\n            2020年01月03日\n\n(2019)津0110民初9886号\n      本院定于2020年01月14日10时00分在第二十一法庭公开审理天津市金隅混凝土有限公司与天津市龙盛祥地基基础工程有限公司买卖合同纠纷一案。\n            2020年01月03日\n\n(2019)津0110民初9899号\n      本院定于2020年01月09日09时00分在第十三法庭公开审理吴国喜、施淑敏与吴同健案外人执行异议之诉一案。\n            2020年01月03日\n\n(2019)津0110民初9922号\n      本院定于2020年01月30日14时00分在第二十一法庭公开审理天津保益丰达建材销售有限公司与河南圣锦建设工程有限公司买卖合同纠纷一案。\n            2020年01月03日\n\n(2019)津0110民初9925号\n      本院定于2020年01月14日14时00分在第二十一法庭公开审理天津市西青区华泰兴旺石材经营部与河南圣锦建设工程有限公司买卖合同纠纷一案。\n            2020年01月03日\n\n(2019)津0110民初9942号\n      本院定于2020年01月15日14时00分在第二十一法庭公开审理天津方方正正钢管有限公司与天津市华春管道工程技术有限公司买卖合同纠纷一案。\n            2020年01月03日\n\n(2019)津0110民初9945号\n      本院定于2020年01月14日09时00分在第二十一法庭公开审理天津金隅混凝土有限公司与天津第三市政公路工程有限公司买卖合同纠纷一案。\n            2020年01月03日\n\n(2019)津0110民初9946号\n      本院定于2020年01月15日09时00分在第二十一法庭公开审理川铁电气（天津）股份有限公司与青岛天安鲁兴电力工程有限公司、青岛新光耀电气集团有限公司买卖合同纠纷一案。\n            2020年01月03日\n\n     (2020)津0110民初4号\n      本院定于2020年01月17日09时03分在第十法庭公开审理张长召、靳焕文与杨玉纯、利宝保险有限公司天津分公司机动车交通事故责任纠纷一案。\n            2020年01月03日\n\n   (2020)津0110民初59号\n      本院定于2020年01月09日10时09分在第十法庭公开审理田林与刘杰、天津市公交集团第四客运有限公司、中国人民财产保险股份有限公司天津市分公司机动车交通事故责任纠纷一案。\n            2020年01月03日\n\n   (2020)津0110民初61号\n      本院定于2020年01月17日09时34分在第十法庭公开审理徐凯、万金和与曹秀梅、胡波、中国平安财产保险股份有限公司天津分公司机动车交通事故责任纠纷一案。\n            2020年01月03日\n\n   (2020)津0110民初81号\n      本院定于2020年01月07日09时12分在第十法庭公开审理刘东权与王振军、张子丽、中国人民财产保险股份有限公司天津市河西支公司机动车交通事故责任纠纷一案。\n            2020年01月03日\n\n";
        System.out.println(content.split("\n").length);
//         screenUtil.logout();
    }
}
