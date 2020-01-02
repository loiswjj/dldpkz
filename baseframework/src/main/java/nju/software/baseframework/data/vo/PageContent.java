package nju.software.baseframework.data.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Jingjing
 * @date 2019/12/16
 */
@Data
public class PageContent {
    private String pagename;
    private List<MediaContent> mediaContents;
    private int repeatcount = 1;
}
