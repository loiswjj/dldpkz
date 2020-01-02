package nju.software.baseframework.data.vo;

public class GgTjModel {
    private String fbr;
    private String fbsj;
    private String  gglx;
    private long count;

    public void setFbr(String fbr) {
        this.fbr = fbr;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setGglx(String gglx) {
        this.gglx = gglx;
    }

    public void setFbsj(String fbsj) {
        this.fbsj = fbsj;
    }

    public String getFbr() {
        return fbr;
    }

    public long getCount() {
        return count;
    }

    public String getFbsj() {
        return fbsj;
    }

    public String getGglx() {
        return gglx;
    }

    public GgTjModel(String fbr,String fbsj,String gglx,long count){
        this.fbr = fbr;
        this.fbsj = fbsj;
        this.gglx = gglx;
        this.count =count;
    }
}
