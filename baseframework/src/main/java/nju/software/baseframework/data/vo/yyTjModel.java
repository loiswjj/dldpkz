package nju.software.baseframework.data.vo;

public class yyTjModel {
    private String jfr;
    private String yysj;
    private long count;

    public long getCount() {
        return count;
    }

    public String getJfr() {
        return jfr;
    }

    public String getYysj() {
        return yysj;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setJfr(String jfr) {
        this.jfr = jfr;
    }

    public void setYysj(String yysj) {
        this.yysj = yysj;
    }

    public yyTjModel(String jfr,String yysj,long count){
        this.jfr = jfr;
        this.yysj = yysj;
        this.count = count;
    }
}
