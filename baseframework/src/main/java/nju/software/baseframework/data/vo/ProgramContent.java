package nju.software.baseframework.data.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Jingjing
 * @date 2019/12/17
 */
@Data
public class ProgramContent {
    private String startDate;
    private String endDate;
    private List<Integer> selectedweek;
    private List<PageContent> pageContents;
    private int type = 0;
}
