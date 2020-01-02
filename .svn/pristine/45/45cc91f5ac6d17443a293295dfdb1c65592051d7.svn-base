package nju.software.baseframework.util;

import nju.software.baseframework.data.dataobject.Screen;
import nju.software.baseframework.data.dataobject.Yyb;
import nju.software.baseframework.data.vo.MultiYyModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {
    /**
     * 用于将一个List对象转换为一个二维数组
     * @param object
     * @param title
     * @return
     */
    public static String[][] getArray(List<Object> object,String[] title){
        String[][] content = new String[object.size()][title.length];

        for (int i = 0; i < object.size(); i++) {
            Object obj = object.get(i);
            int j = 0;
            for (Field field:obj.getClass().getDeclaredFields()){
                if(!field.getName().equals("bh")&&!field.getName().equals("id")
                        &&!field.getName().equals("glr")
                        &&!field.getName().equals("yysjStr")
                        &&!field.getName().equals("dsr")){
                    field.setAccessible(true);
                    try {
                        content[i][j++] = String.valueOf(field.get(obj));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return content;
    }

    public static <T> List<Object> toObject(List<T> list){
        List<Object> objects = new ArrayList<>();
        for (Object e:list){
            Object obj = e;
            objects.add(obj);
        }
        return objects;
    }

    public static List<Yyb> getYybList(MultiYyModel multiYyModel){
        List<Yyb> _list = new ArrayList<>();
        String[] lfr = multiYyModel.getLfr();

        for (int i = 0; i <lfr.length ; i++) {
            Yyb yyb = new Yyb();
            yyb.setAh(multiYyModel.getAh());
            yyb.setAjmc(multiYyModel.getAjmc());
            yyb.setGldsr(multiYyModel.getGldsr()[i]);
            yyb.setJfbm(multiYyModel.getJfbm());
            yyb.setJfdd(multiYyModel.getJfdd());
            yyb.setJfr(multiYyModel.getJfr());
            yyb.setJfrlxfs(multiYyModel.getJfrlxfs());
            yyb.setLfr(lfr[i]);
            yyb.setLfrsfzh(multiYyModel.getLfrsfzh()[i]);
            yyb.setLfrlxfs(multiYyModel.getLfrlxfs()[i]);
            yyb.setLfsj(multiYyModel.getLfsj());
            yyb.setYbagx(multiYyModel.getYbagx()[i]);
            yyb.setYysj(multiYyModel.getYysj());
            _list.add(yyb);
        }
        return _list;
    }

    /**
     * 获得screen的保存显示状态信息
     * @param screen
     * @return
     */
    public static int[] getSelectedArr(String str){
        String[] Arr = str.split(";");
        int[] arr = new int[Arr.length];
        for (int i =0;i<Arr.length;i++){
            arr[i] = Integer.valueOf(Arr[i]);
        }
        return arr;
    }

    public static String TransArr(int[] arr){
        String res = "";
        for (int i=0;i<arr.length;i++){
            if(i!=arr.length-1){
                res += arr[i]+";";
            }else {
                res += arr[i];
            }
        }
        return res;
    }
}
