package nju.software.baseframework.Test;

import nju.software.baseframework.util.WordFileUtil;
import onbon.bx05.Bx5GCommException;
import onbon.bx05.Bx5GEnv;
import onbon.bx05.Bx5GException;
import onbon.bx05.Bx5GScreenClient;
import onbon.bx05.area.TextCaptionBxArea;
import onbon.bx05.area.page.ImageBxPage;
import onbon.bx05.file.ProgramBxFile;
import onbon.bx05.message.area.TextCaptionArea;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class test {

    /**
     * 测试大屏是否能够发送图片
     */
    public void SendImage(){
        try {
            Bx5GEnv.initial("config/log.properties", 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bx5GScreenClient client = new Bx5GScreenClient("Screen");
        //填入IP
        if (!client.connect("", 5005)) {
            System.out.println("connect failed");
            return;
        }
        client.deletePrograms();
        client.deleteAllDynamic();

        File imageFile = new File("");
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);

            ImageBxPage imageBxPage = new ImageBxPage();
            imageBxPage.addImage(bufferedImage);
            TextCaptionArea imageArea = new TextCaptionArea();
            imageBxPage.accept(imageArea,client.getProfile());

            TextCaptionBxArea area = new TextCaptionBxArea(imageArea,client.getProfile());

            ProgramBxFile programBxFile = new ProgramBxFile("P001",client.getProfile());
            programBxFile.addArea(area);
            client.writeProgram(programBxFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Bx5GCommException e) {
            e.printStackTrace();
        } catch (Bx5GException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
//        String sfzh = "120112196306112554";
////        String xssfzh = sfzh.substring(0,10)+"****"+sfzh.substring(14,18);
////        System.out.println(xssfzh);
//        int[] arr = new int[]{1,2,3,4};
//        System.out.println(ArrayUtil.TransArr(arr));
        String path = "D:\\上传文件\\Files\\2019-06-24\\1561339310219.doc";
        File file = new File(path);
//        String str = WordFileUtil.readWord(file,"D:\\test.html");
//        System.out.println(str);
    }
}
