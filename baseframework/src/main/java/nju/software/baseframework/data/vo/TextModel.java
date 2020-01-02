package nju.software.baseframework.data.vo;

import lombok.Data;

/**
 * @author Jingjing
 * @date 2019/11/15
 * @description: 用于设置文本
 */
@Data
public class TextModel {
    private String content;
    private String align_x;
    private String align_y;
//    private Integer fontsize = 68;
    private Integer fontsize = 48;
    private String text_color = "#ff0000";
    private String  offset_x;
    private String offset_y;
    private String width;
    private String height;
    private String type = "PAGE_SWITCH";
    private float speed = 2.0f;
    private int duration = 10000;

    public TextModel(String content, String offset_x, String offset_y, String width, String height) {
        this.content = content;
        this.offset_x = offset_x;
        this.offset_y = offset_y;
        this.width = width;
        this.height = height;
    }

    public TextModel(MediaContent mediaContent) {
        this.content = mediaContent.getContent();
        this.offset_x = String.valueOf(mediaContent.getX());
        this.offset_y = String.valueOf(mediaContent.getY());
        this.width = String.valueOf(mediaContent.getWidth());
        this.height = String.valueOf(mediaContent.getHeight());
        this.align_x = mediaContent.getText_align();
        this.align_y = mediaContent.getVertial_align();
        this.speed = mediaContent.getSpeed();
        this.duration = mediaContent.getDuration();
    }
}
