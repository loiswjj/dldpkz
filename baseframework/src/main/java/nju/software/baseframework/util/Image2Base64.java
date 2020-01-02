package nju.software.baseframework.util;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Image2Base64 {
    /**
     * 已知图片地址 将图片转换为base64编码
     * @param path
     * @return
     */
    public static String getImageStr(String path){
        InputStream stream = null;
        byte[] data = null;
        try{
            stream = new FileInputStream(path);
            data = new byte[stream.available()];
            stream.read(data);
            stream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static List<String> getImageStrList(List<String> _list){
        List<String> imageDataList = new ArrayList<>();
        for (int i = 0; i < _list.size(); i++) {
            imageDataList.add("data:image/png;base64,"+Image2Base64.getImageStr(_list.get(i)));
        }
        return imageDataList;
    }
}
