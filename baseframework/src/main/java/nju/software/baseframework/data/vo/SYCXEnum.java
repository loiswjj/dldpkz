package nju.software.baseframework.data.vo;

public enum SYCXEnum {
    JY("1","简易"),
    PT("2","普通"),
    SC("3","速裁");

    private String bh;
    private String mc;

    SYCXEnum(String bh,String mc){
        this.bh = bh;
        this.mc = mc;
    }

    public String getMc() {
        return mc;
    }

    public String getBh() {
        return bh;
    }

    public static String getMcByBh(String bh){
        for(SYCXEnum sycxEnum:SYCXEnum.values()){
            if(sycxEnum.getBh().equals(bh))
                return sycxEnum.getMc();
        }
        return null;
    }
}
