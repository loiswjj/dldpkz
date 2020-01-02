package nju.software.baseframework.data.vo;

public enum LBBHEnum {
    AJXZ("FBS0021-97","ajxz"),
    SYCX("FBS0042-97","sycx"),
    JAFS("11FBS0048-97,1*FBS0049-97,1AUSR0023-99,21FBS0087-97,2*FBS0088-97,27USR706-02,2AUSR0023-99,24USR707,25USR707,26USR707,2BFBS0087-97,29FBS0087-97,61FBS0117-97,6*FBS0118-97,6AUSR0023-99,7*FBS0133-97,8*FBS0144-97,8AUSR0023-99,A*USR0026-98",
            "jafs"),
    SSDW("13FBSSSDW-XZ,1AFBSSSDW-SSFC,15FBSSSDW-JXJS,1*FBS0040-97,23FBS0096-97,2AFBSSSDW-SSFC,27FBSSSDW-MSPC,2BFBSSSDW-SB,24USR708-02,25FBSDCAJ-97,26USR708-02,2*FBS0095-97,16FBSSSDW-JXJS,6*FBS0123-97,63FBS0124-97,6AFBSSSDW-SSFC,7*FBS0132-97,8*FBS0147-97,A*FBSSSDW-QS,1EFBSSSDW-JXJS,E*FBS0095-97,C*FBSSSDW-GJ,B*FBSSSDW-GJ,F*FBSSSDW-SFJZ,2GUSR708-02,D*FBSSSDW-SFZC,B2FBSSSDW-QJSFXZSDWS,B4FBSSSDW-QJSFXZPXRYG,B5FBSSSDW-QJSFXZZZYJ,1ZFBSXSQTGX-97," +
            "1FFBSXSQTGX-97,2DFBSDSRCXZS-97,1CUSR708-02,6DUSR708-02","ssdw"),
    BASPT("USR206-99","bm");

    private String lbbh;
    private String lbmc;

    LBBHEnum(String lbbh,String lbmc){
        this.lbbh = lbbh;
        this.lbmc = lbmc;
    }

    private String getLbmc(){
        return lbmc;
    }

    private String getLbbh(){
        return lbbh;
    }

    public static String getLbmcbyLbbh(String lbbh){
        for(LBBHEnum lb:LBBHEnum.values()){
            if(lb.getLbbh().equals(lbbh))
                return lb.getLbmc();
        }
        return null;
    }

    public static String getLbbhbyLbmc(String lbmc){
        for (LBBHEnum lb:LBBHEnum.values()) {
            if(lb.getLbmc().equals(lbmc)){
                return lb.getLbbh();
            }
        }
        return null;
    }
}
