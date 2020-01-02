package nju.software.baseframework.convertor;

import nju.software.baseframework.data.dataobject.Yhb;
import nju.software.baseframework.data.vo.AJZTEnum;

public class SearchConverter {
    /**
     * ajlb
     * @param ah
     * @param ajmc
     * @param dsr
     * @param spz
     * @param spy
     * @param ajxz
     * @param ajzt
     * @param spt
     * @param jafs
     * @param sfgd
     * @param sycx
     * @param yh
     * @param lx
     * @return
     */
    public static String getSearchSql(String ah, String ajmc, String dsr, String spz, String spy,
                               String ajxz, String ajzt, String spt, String jafs, String sfgd,
                               String sycx, Yhb yh,Integer lx){
        String sql = "";
        if((dsr==null||dsr.equals(""))&&(spz==null||dsr.equals(""))
                &&(spy==null||spy.equals(""))){
            if(lx==1){
                sql = "select ajjb from AJjb ajjb where ajjb.larq!=null and ajjb.ajxh in (" +
                        "select ajxh from PubSpry where (sfcbr='Y' or fg='0') and xm='"+yh.getYhmc()+"') ";
            }else {
                sql = "select count(ajjb) from AJjb ajjb where ajjb.larq!=null and ajjb.ajxh in (" +
                        "select ajxh from PubSpry where (sfcbr='Y' or fg='0') and xm='"+yh.getYhmc()+"') ";
            }
            if(ah!=null&&!ah.equals("")){
                String str = ah.substring(0,1);
                if(str.equals("(")){
                    sql += " and ajjb.ah like "+"'"+ah+"%'";
                }else {
                    sql += " and ajjb.ah like "+"'%"+ah+"%'";
                }
            }
            if(ajmc!=null&&!ajmc.equals("")){
                sql += " and ajjb.ajmc like "+"'%"+ajmc+"%'";
            }
            if(ajxz!=null&&!ajxz.equals("")){
                sql+= " and ajjb.ajxz="+"'"+ajxz+"'";
            }
            if(ajzt!=null&&!ajzt.equals("")){
                sql+= AJZTEnum.getSqlByNumber(ajzt);
            }
            if(spt!=null&&!spt.equals("")){
                sql+= " and ajjb.baspt="+"'"+spt+"'";
            }
            if(jafs!=null&&jafs.equals("")){
                sql += " and ajjb.jafs="+"'"+jafs+"'";
            }
            if(sfgd!=null&&sfgd.equals("1")){
                sql+=" and ajjb.gdrq!=null";
            }
            if(sycx!=null&&!sycx.equals("")){
                sql+=" and ajjb.sycx="+"'"+sycx+"'";
            }

            if(lx==1){
                sql+=" order by ajjb.larq desc ";
            }
        }else {
            if(lx==1){
                sql = "select ajjb from AJjb ajjb,DsrJb dsr, PubSpry spry " +
                        "where ajjb.ajxh=dsr.ajxh and ajjb.ajxh = spry.ajxh and ajjb.larq!=null " +
                        "and ajjb.ajxh in (select ajxh " +
                        "from PubSpry where (sfcbr='Y' or fg='0') and xm='"+yh.getYhmc()+"') ";
            }else {
                sql = "select count(ajjb) from AJjb ajjb,DsrJb dsr, PubSpry spry " +
                        "where ajjb.ajxh=dsr.ajxh and ajjb.ajxh = spry.ajxh and ajjb.larq!=null " +
                        "and ajjb.ajxh in (select ajxh " +
                        "from PubSpry where (sfcbr='Y' or fg='0') and xm='"+yh.getYhmc()+"') ";
            }
            if(ah!=null&&!ah.equals("(")){
                String str = ah.substring(0,1);
                //模糊查找
                if(str.equals("(")){
                    sql += " and ajjb.ah like "+"'"+ah+"%'";
                }else {
                    sql += " and ajjb.ah like "+"'%"+ah+"%'";
                }
            }
            if(ajmc!=null&&!ajmc.equals("")){
                sql+=" and ajjb.ajmc like"+"'%"+ajmc+"%'";
            }
            if(ajxz!=null&&!ajxz.equals("")){
                sql+= " and ajjb.ajxz="+"'"+ajxz+"'";
            }
            if(ajzt!=null&&!ajzt.equals("")){
                sql+= AJZTEnum.getSqlByNumber(ajzt);
            }
            if(spt!=null&&!spt.equals("")){
                sql+= " and ajjb.baspt="+"'"+spt+"'";
            }
            if(jafs!=null&&jafs.equals("")){
                sql += " and ajjb.jafs="+"'"+jafs+"'";
            }
            if(sfgd.equals("1")){
                sql+=" and ajjb.gdrq!=null";
            }
            if(sycx!=null&&!sycx.equals("")){
                sql+=" and ajjb.sycx="+"'"+sycx+"'";
            }
            if((spz!=null&&!spz.equals(""))||(spy!=null&&!spy.equals(""))){
                if(spy!=null){
                    sql+=" and spry.xm"+"'"+spz+"'";
                }else {
                    sql+=" and spry.xm"+"'"+spy+"'";
                }
            }
            if (dsr!=null&&!dsr.equals("")){
                sql+=" and dsr.xm like "+"'%"+dsr+"%'";
            }
            if(lx==1){
                sql += " order by ajjb.larq desc";
            }
        }
        return sql;
    }

    /**
     * 获取搜索的公告的sql语句
     * @param ah
     * @param ajmc
     * @param fbsj
     * @param gglx
     * @param ggzt
     * @param yh
     * @param lx
     * @return
     */
    public static String getGgSql(String ah,String ajmc,String fbsj,Integer gglx,
                           Integer ggzt,Yhb yh,Integer lx){
        String sql = "select ggb from Ggb ggb where fbr='"+yh.getYhmc()+"'";
        if(yh.getYhmc().equals("系统管理员")){
            sql = "select ggb from Ggb ggb where fbr!=null";
        }
        if(lx==1){
            sql = "select count(ggb) from Ggb ggb where fbr='"+yh.getYhmc()+"'";
            if(yh.getYhmc().equals("系统管理员")){
                sql = "select count(ggb) from Ggb ggb where fbr!=null";
            }
        }
        if(ah!=null){
            sql+=" and ggb.ah='"+ah+"'";
        }
        if(ajmc!=null){
            sql+=" and ggb.ajmc='"+ajmc+"'";
        }
        if(fbsj!=null){
            sql+=" and ggb.fbsj='"+fbsj+"'";
        }
        if(gglx!=null){
            sql+=" and ggb.gglx="+gglx;
        }
        if(ggzt!=null){
            sql+=" and ggb.status="+ggzt;
        }
        return sql;
    }

    public static String getYySql(String ah,String ajmc,String lfr,String jfdd,
                                  String lxfs,String lfsj,Integer yyzt,
                                  Yhb yh,Integer lx){
        String sql = "select yyb from Yyb yyb where yyb.jfr='"+yh.getYhmc()+"'";
        if(yh.getYhmc().equals("系统管理员")){
            sql = "select yyb from Yyb yyb where yyb.jfr!=null";
        }
        if(lx==1){
            sql = "select count(yyb) from Yyb yyb where yyb.jfr='"+yh.getYhmc()+"'";
            if(yh.getYhmc().equals("系统管理员")){
                sql = "select count(yyb) from Yyb yyb where yyb.jfr!=null";
            }
        }
        if(ah!=null){
            sql+=" and yyb.ah='"+ah+"'";
        }
        if(ajmc!=null){
            sql+=" and yyb.ajmc='"+ajmc+"'";
        }
        if(lfr!=null){
            sql+=" and yyb.lfr='"+lfr+"'";
        }
        if(jfdd!=null){
            sql+=" and yyb.jfdd="+jfdd+"'";
        }
        if(lxfs!=null){
            sql+=" and yyb.lfrlxfs='"+lxfs+"'";
        }
        if(lfsj!=null){
            sql+= " and yyb.lfsj='"+lfsj+"'";
        }
        if(yyzt!=null){
            sql+=" and yyb.yyzt="+yyzt;
        }
        return sql;
    }

    public static String sjtjSql(String spt,String ajxz,String jafs
            ,String cbr,String begin,String last,Integer lx ){
        String sql_yy = "select yyb.yyzt,count(yyb.bh) as num from " +
                "Yyb yyb,AJjb ajjb where (ajjb.ah=yyb.ah or ajjb.ah=null)";
        String sql_gg = "select ggb.gglx,count(ggb.bh) as num from Ggb ggb," +
                "AJjb ajjb where ajjb.ajxh=ggb.ajxh ";
        if(spt!=null){
            sql_yy+=" and ajjb.baspt='"+spt+"'";
            sql_gg+=" and ajjb.spt='"+spt+"'";
        }
        if(ajxz!=null){
            sql_yy+=" and ajjb.ajxz='"+ajxz+"'";
            sql_gg+=" and ajjb.ajxz='"+ajxz+"'";
        }
        if(jafs!=null){
            sql_yy+=" and ajjb.jafs='"+jafs+"'";
            sql_gg+=" and ajjb.jafs='"+jafs+"'";
        }
        if(cbr!=null){
            sql_yy+=" and yyb.jfr='"+cbr+"'";
            sql_gg+=" and ggb.fbr='"+cbr+"'";
        }
        if(begin!=null){
            sql_yy+=" and yyb.yysj>='"+begin+"'";
            sql_gg+=" and ggb.fbsj>='"+begin+"'";
        }
        if(last!=null){
            sql_yy+=" and yyb.yysj<='"+last+"'";
            sql_gg+=" and ggb.fbsj<='"+last+"'";
        }
        sql_yy+=" group by yyb.yyzt";
        sql_gg+=" group by yyb.gglx";
        if(lx==1){
            return sql_yy;
        }else {
            return sql_gg;
        }
    }
}
