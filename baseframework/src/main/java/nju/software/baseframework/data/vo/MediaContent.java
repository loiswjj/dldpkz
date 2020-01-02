package nju.software.baseframework.data.vo;

import lombok.Data;

/**
 * @author Jingjing
 * @date 2019/12/16
 */
@Data
public class MediaContent {
    private int x;
    private int y;
    private int width;
    private int height;
    private String filepath = "";  // 创建的时候就实现将文件传输至服务器
    private String text_align;
    private String vertial_align;
    private String mediaName;
    private String playAttr;
    private int speed;
    private int duration = 10;
    private String type;
    private String content = "";

    public MediaContent(int x, int y, int width, int height, String filepath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.filepath = filepath;
    }
}
