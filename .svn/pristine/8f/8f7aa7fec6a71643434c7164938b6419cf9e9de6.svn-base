package nju.software.baseframework.util;

import org.apache.poi.hssf.usermodel.*;

/**
 * ExcelUtil 操作工具类
 */
public class ExcelUtil {

    /**
     * 导出excel表格
     * @param sheetName
     * @param titles ---列名
     * @param values
     * @param wb
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String[] titles,
                                               String[][] values,HSSFWorkbook wb){
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        //column name
        HSSFCell cell = null;
        for (int i = 0; i <titles.length ; i++) {
            cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(style);
        }

        //value
        for (int i = 0; i <values.length ; i++) {
            row = sheet.createRow(i+1);
            for (int j = 0; j < values[i].length; j++) {
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }
}
