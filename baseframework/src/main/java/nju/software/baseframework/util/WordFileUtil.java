package nju.software.baseframework.util;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * @author Jingjing
 * @date 2019/6/22
 * @function word转html
 */
public class WordFileUtil {
    private static Logger logger = Logger.getLogger(WordFileUtil.class);
    private static byte[] bytes = new byte[1024];

    public static String readWord(File file,String filename,String baseUrl){
        String str ="word 转 html失败";
        try {
            FileInputStream fis = new FileInputStream(file);
            File imageFolderFile = new File(baseUrl);
            if(!imageFolderFile.exists()){
                imageFolderFile.mkdirs();
            }
            String fileName = file.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            if(suffix.equals(".doc")){
                HWPFDocument hwpfDocument = new HWPFDocument(fis);
                WordToHtmlConverter wordToHtmlConverter =
                        new WordToHtmlConverter(DocumentBuilderFactory.
                                newInstance().newDocumentBuilder().newDocument());
                //设置图片存放位置
                wordToHtmlConverter.setPicturesManager(new PicturesManager() {
                    @Override
                    public String savePicture(byte[] bytes, PictureType pictureType, String s, float v, float v1) {
                        File _file = new File(baseUrl+s);
                        try {
                            OutputStream outputStream = new FileOutputStream(_file);
                            outputStream.write(bytes);
                            outputStream.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return baseUrl+s;
                    }
                });
                //解析word
                wordToHtmlConverter.processDocument(hwpfDocument);
                Document document = wordToHtmlConverter.getDocument();

                File htmlFile = new File(filename);
                OutputStream outputStream = new FileOutputStream(htmlFile);

                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(outputStream);

                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer serializer = factory.newTransformer();
                serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
                serializer.setOutputProperty(OutputKeys.INDENT, "yes");
                serializer.setOutputProperty(OutputKeys.METHOD, "html");

                serializer.transform(domSource, streamResult);
                outputStream.close();
            }else if (suffix.equals(".docx")){
                //加载word文档生成word Document对象
                XWPFDocument xwpfDocument = new XWPFDocument(fis);
                //解析成xhtml
                XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
                options.setExtractor(new FileImageExtractor(imageFolderFile));
                options.setIgnoreStylesIfUnused(false);
                options.setFragment(true);
                //将document对象转为XHTML
                OutputStream outputStream = new FileOutputStream(new File(filename));
                XHTMLConverter.getInstance().convert(xwpfDocument,outputStream,options);
                outputStream.close();
            }
            fis.close();
            return "success";
        } catch (FileNotFoundException e) {
            logger.error("word转换str："+e);
        } catch (IOException e) {
            logger.error("word转换str："+e);
        } catch (ParserConfigurationException e) {
            logger.error("word转换str："+e);
        } catch (TransformerConfigurationException e) {
            logger.error("word转换str："+e);
        } catch (TransformerException e) {
            logger.error("word转换str："+e);
        }
        return str;
    }

    public static String readFile(String filepath){
        String str = "";
        try {
            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while (bufferedReader.readLine()!=null){
                str += bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
