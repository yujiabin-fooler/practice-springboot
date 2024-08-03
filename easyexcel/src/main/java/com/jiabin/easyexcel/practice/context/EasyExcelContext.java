package com.jiabin.easyexcel.practice.context;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @author jiabin.yu
 * @since 2021-04-15
 */
public class EasyExcelContext {

    public static final HorizontalCellStyleStrategy EXPORT_STYLE_STRATEGY = new

            HorizontalCellStyleStrategy(getHeadWriteCellStyle(), getContentWriteCellStyle());

    /**
     * 内容的策略
     *
     * @return
     */
    private static WriteCellStyle getContentWriteCellStyle() {
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        return contentWriteCellStyle;
    }

    /**
     * 头的策略
     *
     * @return
     */
    private static WriteCellStyle getHeadWriteCellStyle() {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 10);
        headWriteCellStyle.setWriteFont(headWriteFont);
        return headWriteCellStyle;
    }
}
