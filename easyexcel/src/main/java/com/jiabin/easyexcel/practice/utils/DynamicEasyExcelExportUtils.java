package com.jiabin.easyexcel.practice.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.jiabin.easyexcel.practice.context.EasyExcelContext;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author jiabin.yu
 * @since 2021-04-15
 */
public class DynamicEasyExcelExportUtils {

    private static final Logger log = LoggerFactory.getLogger(DynamicEasyExcelExportUtils.class);

    private static final String DEFAULT_SHEET_NAME = "sheet1";

    /**
     * 动态生成模板文件流(多级表头，用英文逗号隔开)
     * @param headColumns
     * @return
     */
    public static byte[] exportTemplateExcelFileStream(List<String> headColumns){
        return exportTemplateExcelFileStream(headColumns, DEFAULT_SHEET_NAME);
    }

    /**
     * 动态生成模板文件流，支持指定工作表名称(多级表头，用英文逗号隔开)
     * @param headColumns
     * @param sheetName
     * @return
     */
    public static byte[] exportTemplateExcelFileStream(List<String> headColumns, String sheetName){
        List<List<String>> excelHead = Lists.newArrayList();
        headColumns.forEach(columnName -> excelHead.add(Lists.newArrayList(StringUtils.split(columnName, ","))));
        byte[] stream = createFileStreamByDefaultStrategy(excelHead, new ArrayList<>(), sheetName);
        return stream;
    }

    /**
     * 动态导出文件流
     * @param headColumnMap  有序列头部
     * @param dataList       数据体
     * @return
     */
    public static byte[] exportExcelFileStream(LinkedHashMap<String, String> headColumnMap, List<Map<String, Object>> dataList){
        return exportExcelFileStream(headColumnMap, dataList, DEFAULT_SHEET_NAME);
    }


    /**
     * 动态导出文件流，支持指定工作表名称
     * @param headColumnMap  有序列头部
     * @param dataList       数据体
     * @param sheetName      工作表名称
     * @return
     */
    public static byte[] exportExcelFileStream(LinkedHashMap<String, String> headColumnMap, List<Map<String, Object>> dataList, String sheetName){
        List<List<String>> excelHead = new ArrayList<>();
        if(MapUtils.isNotEmpty(headColumnMap)){
            //key作为匹配符，value作为列名称，多个行头用[,]隔开
            headColumnMap.entrySet().forEach(entry -> {
                excelHead.add(Lists.newArrayList(StringUtils.split(entry.getValue(), ",")));
            });
        }
        List<List<Object>> excelRows = new ArrayList<>();
        if(MapUtils.isNotEmpty(headColumnMap) && CollectionUtils.isNotEmpty(dataList)){
            for (Map<String, Object> dataMap : dataList) {
                List<Object> rows = new ArrayList<>();
                headColumnMap.entrySet().forEach(headColumnEntry -> {
                    if(dataMap.containsKey(headColumnEntry.getKey())){
                        Object data = dataMap.get(headColumnEntry.getKey());
                        rows.add(data);
                    }
                });
                excelRows.add(rows);
            }
        }
        return createFileStreamByDefaultStrategy(excelHead, excelRows, sheetName);
    }


    /**
     * 根据约定的自定义头部，导出文件流
     * @param rowHeads
     * @param excelRows
     * @param sheetName
     * @return
     */
    public static byte[] customerExportExcelFileStream(List<List<String>> rowHeads, List<List<Object>> excelRows, String sheetName){
        //将行头部转成easyexcel能识别的部分
        List<List<String>> realHead = transferEasyExcelHead(rowHeads);
        byte[] stream = createFileStreamByDefaultStrategy(realHead, excelRows, sheetName);
        return stream;
    }

    /**
     * 自定义策略，生成文件流
     * @param rowHeads
     * @param excelRows
     * @param sheetName
     * @param customerWriteHandlers
     * @return
     */
    public static byte[] customerStrategyExportExcelFileStream(List<List<String>> rowHeads, List<List<Object>> excelRows, String sheetName, List<WriteHandler> customerWriteHandlers){
        //将行头部转成easyexcel能识别的部分
        List<List<String>> realHead = transferEasyExcelHead(rowHeads);
        byte[] stream = writeExcelFile(realHead, excelRows,sheetName, customerWriteHandlers);
        if(Objects.isNull(stream)){
            throw new RuntimeException("生成目标文件异常");
        }
        return stream;
    }

    /**
     * 根据默认策略配置，生成文件流
     * @param excelHead
     * @param excelRows
     * @param sheetName
     * @return
     */
    private static byte[] createFileStreamByDefaultStrategy(List<List<String>> excelHead, List<List<Object>> excelRows, String sheetName){
        List<WriteHandler> customerWriteHandlers = Lists.newArrayList(new LongestMatchColumnWidthStyleStrategy(), EasyExcelContext.EXPORT_STYLE_STRATEGY);
        byte[] stream = writeExcelFile(excelHead,excelRows,sheetName, customerWriteHandlers);
        if(Objects.isNull(stream)){
            throw new RuntimeException("生成目标文件异常");
        }
        return stream;
    }


    /**
     * 生成excel文件流
     * @param excelHead
     * @param excelRows
     * @param sheetName
     * @param customerWriteHandlers
     * @return
     */
    private static byte[] writeExcelFile(List<List<String>> excelHead, List<List<Object>> excelRows, String sheetName, List<WriteHandler> customerWriteHandlers){
        try {
            if(CollectionUtils.isNotEmpty(excelHead)){
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ExcelWriterBuilder writerBuilder = EasyExcel.write(outputStream);
                //添加拦截器
                if(CollectionUtils.isNotEmpty(customerWriteHandlers)){
                    for (WriteHandler customerWriteHandler : customerWriteHandlers) {
                        writerBuilder.registerWriteHandler(customerWriteHandler);
                    }
                }
                //添加头部
                writerBuilder.head(excelHead);
                //写入内容数据
                writerBuilder.sheet(StringUtils.isNotBlank(sheetName) ? sheetName : DEFAULT_SHEET_NAME).doWrite(excelRows);
                return outputStream.toByteArray();
            }
        } catch (Exception e) {
            log.warn("动态生成excel文件失败，headColumns：{}, dataList：{}，sheetName：{}，错误原因：{}", JSON.toJSONString(excelHead), JSON.toJSONString(excelRows), sheetName, ExceptionUtils.getStackTrace(e));
        }
        return null;
    }


    /**
     * 将行头部转成easyexcel能识别的头部
     * @param rowHeads
     * @return
     */
    private static List<List<String>> transferEasyExcelHead(List<List<String>> rowHeads){
        //将头部列进行反转
        List<List<String>> realHead = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(rowHeads)){
            Map<Integer, List<String>> cellMap = new LinkedHashMap<>();
            //遍历行
            for (List<String> cells : rowHeads) {
                //遍历列
                for (int i = 0; i < cells.size(); i++) {
                    if(cellMap.containsKey(i)){
                        cellMap.get(i).add(cells.get(i));
                    } else {
                        cellMap.put(i, Lists.newArrayList(cells.get(i)));
                    }
                }
            }
            //将列一行一行加入realHead
            cellMap.entrySet().forEach(item -> realHead.add(item.getValue()));
        }
        return realHead;
    }

    public static void main(String[] args) throws IOException {
        //自由导出模版文件案例
        //头部，第一层
        List<String> head1 = new ArrayList<>();
        head1.add("");
        head1.add("");
        head1.add("");
        head1.add("");
        head1.add("统采销售额指标");// 统采销售额指标
        head1.add("统采销售额指标");// 统采销售额指标
        head1.add("统采销售额指标");// 统采销售额指标
        head1.add("统采销售额指标");// 统采销售额指标
        head1.add("批发指标");
        head1.add("批发指标");
        head1.add("批发指标");
        head1.add("批发指标");
        head1.add("批发指标");
        head1.add("毛利指标");
        head1.add("毛利指标");
        head1.add("毛利指标");
        head1.add("毛利指标");


        //头部，第二层
        List<String> head2 = new ArrayList<>();
        head2.add("");
        head2.add("");
        head2.add("目标类型");
        head2.add("");
        head2.add("休百业务,全品类县乡业务,啤饮休百业务,啤饮业务,酒专员,全品类市区业务");// 统采销售额指标
        head2.add("全品类县乡业务,酒专员,全品类市区业务");// 统采销售额指标
        head2.add("全品类县乡业务,全品类市区业务,啤饮休百业务,休百业务");// 统采销售额指标
        head2.add("全品类县乡业务,全品类市区业务,啤饮休百业务,啤饮业务");// 统采销售额指标
        head2.add("全品类县乡业务,全品类市区业务,酒专员");// 批发指标
        head2.add("全品类市区业务,全品类县乡业务,休百业务,啤饮休百业务");// 批发指标
        head2.add("全品类市区业务,全品类县乡业务,啤饮休百业务,啤饮业务");// 批发指标
        head2.add("全品类市区业务,全品类县乡业务,啤饮休百业务,休百业务");// 批发指标
        head2.add("休百业务,全品类县乡业务,啤饮休百业务,酒专员,全品类市区业务,啤饮业务");// 批发指标
        head2.add("休百业务,全品类县乡业务,啤饮休百业务,全品类市区业务,啤饮业务,酒专员");// 毛利指标
        head2.add("休百业务,全品类县乡业务,啤饮休百业务,全品类市区业务,酒专员,啤饮业务");// 毛利指标
        head2.add("休百业务,全品类县乡业务,啤饮休百业务,啤饮业务,全品类市区业务,酒专员");// 毛利指标
        head2.add("休百业务,全品类县乡业务,啤饮休百业务,全品类市区业务,啤饮业务,酒专员");// 毛利指标


        //头部，第三层
        List<String> head3 = new ArrayList<>();
        head3.add("城市名称");
        head3.add("经纪人姓名");
        head3.add("经纪人电话");
        head3.add("经纪人类型");
        head3.add("统采全品类销售额目标(万元)");// 统采销售额指标
        head3.add("统采酒除啤酒销售额目标(万元)");// 统采销售额指标
        head3.add("统采休百调味销售额目标(万元)");// 统采销售额指标
        head3.add("统采啤饮副食销售额目标(万元)");// 统采销售额指标
        head3.add("批发酒除啤酒件数目标(件)");// 批发指标
        head3.add("批发休百调味销售额目标(万元)");// 批发指标
        head3.add("批发啤饮副食件数目标(件)");// 批发指标
        head3.add("批发休百调味件数目标(件)");// 批发指标
        head3.add("批发总件数目标(件)");// 批发指标
        head3.add("批发统采全品类毛利目标(万元)");// 毛利指标
        head3.add("批发全品类毛利目标(万元)");// 毛利指标
        head3.add("统采全品类毛利目标(万元)");// 毛利指标
        head3.add("全业务模式全品类客户数目标(个)");// 毛利指标


        //封装头部
        List<List<String>> allHead = new ArrayList<>();
        allHead.add(head1);
        allHead.add(head2);
        allHead.add(head3);

        //封装数据体
        List<Object> data1 = Lists.newArrayList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
        List<Object> data2 = Lists.newArrayList(2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2);
        List<List<Object>> allData = Lists.newArrayList(data1, data2);
        List<WriteHandler> customerWriteHandlers = Lists.newArrayList(new LongestMatchColumnWidthStyleStrategy(), EasyExcelContext.EXPORT_STYLE_STRATEGY);
        byte[] stream = DynamicEasyExcelExportUtils.customerStrategyExportExcelFileStream(allHead, allData, "文件自由导出", customerWriteHandlers);

        String targetPath = System.getProperty("user.dir") + "/spring-boot-example-excel/src/main/resources/file/exportexcel1.xlsx";


        FileOutputStream outputStream = new FileOutputStream(new File(targetPath));
        outputStream.write(stream);
        outputStream.close();
    }
}
