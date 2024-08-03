package com.jiabin.easyexcel.practice.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.util.IoUtils;
import com.alibaba.fastjson.JSONArray;
import com.jiabin.easyexcel.practice.entity.CommonEasyExcelImportDTO;
import com.jiabin.easyexcel.practice.entity.TableColumnDTO;
import com.jiabin.easyexcel.practice.listener.DynamicEasyExcelListener;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态导入excel
 * @author jiabin.yu
 * @since 2021-04-15
 */
public class DynamicEasyExcelImportUtils {

    /**
     * 默认从第一行开始读取数据
     */
    private static final Integer DEFAULT_HEAD_ROW_NUMBER = 1;


    /**
     * 读取文件流获取全部列和数据体
     * @param stream          文件流
     * @param headColumnMap   支持自定义key
     * @return
     */
    public static CommonEasyExcelImportDTO<Map<String,String>> parseExcelToViewByStream(byte[] stream, Map<String,String> headColumnMap) {
        return parseExcelToView(stream, headColumnMap, DEFAULT_HEAD_ROW_NUMBER);
    }

    /**
     * 读取文件流获取全部列和数据体
     * @param stream          文件流
     * @param headColumnMap   支持自定义key
     * @param headRowNumber   支持指定开始读取的行数
     * @return
     */
    public static CommonEasyExcelImportDTO<Map<String,String>> parseExcelToViewByStream(byte[] stream, Map<String,String> headColumnMap, Integer headRowNumber) {
        return parseExcelToView(stream, headColumnMap, headRowNumber);
    }


    /**
     * 动态获取全部列和数据体
     * @param stream
     * @param headColumnMap
     * @return
     */
    private static CommonEasyExcelImportDTO<Map<String,String>> parseExcelToView(byte[] stream, Map<String,String> headColumnMap, Integer headRowNumber){
        DynamicEasyExcelListener readListener = new DynamicEasyExcelListener();
        EasyExcelFactory.read(new ByteArrayInputStream(stream)).registerReadListener(readListener).headRowNumber(headRowNumber).sheet(0).doRead();
        List<Map<Integer, String>> headList = readListener.getHeadList();
        if(CollectionUtils.isEmpty(headList)){
            throw new RuntimeException("Excel未包含表头");
        }
        List<Map<Integer, String>> dataRows = readListener.getDataList();
        if(CollectionUtils.isEmpty(dataRows)){
            throw new RuntimeException("Excel未包含数据");
        }
        //定义视图
        CommonEasyExcelImportDTO<Map<String,String>> excelImportResult = new CommonEasyExcelImportDTO<>();
        excelImportResult.setHeadList(headList);
        //获取头部,取最后一次解析的列头数据
        Map<Integer, String> excelHeadIdxNameMap = headList.get(headList.size() -1);
        List<TableColumnDTO> tableColumnsDtoList = buildExcelHead(headColumnMap, excelHeadIdxNameMap);
        excelImportResult.setColumnList(tableColumnsDtoList);
        //封装数据体
        List<Map<String,String>> dataList = Lists.newArrayList();
        for (Map<Integer, String> dataRow : dataRows) {
            Map<String,String> rowData = new LinkedHashMap<>();
            tableColumnsDtoList.forEach(columnHead ->{
                rowData.put(columnHead.getKey(), StringUtils.defaultString(dataRow.get(columnHead.getIndex())));
            });
            dataList.add(rowData);
        }
        excelImportResult.setColumnDataList(dataList);
        return excelImportResult;
    }

    /**
     * 封装表头信息
     * @param headColumnMap       自定义头部转化map
     * @param excelHeadIdxNameMap excel数据头部
     * @return
     */
    private static List<TableColumnDTO> buildExcelHead(Map<String,String> headColumnMap, Map<Integer, String> excelHeadIdxNameMap){
        List<TableColumnDTO> tableColumnsDtoList = Lists.newArrayList();
        for (Map.Entry<Integer, String> allHeadMap : excelHeadIdxNameMap.entrySet()) {
            //获取表头名称和列下标，根据需要进行转化
            Integer index = allHeadMap.getKey();
            String excelHeadValue = allHeadMap.getValue().trim();
            if(StringUtils.isBlank(excelHeadValue)){
                throw new RuntimeException("第" + index + "列，列名不能为空");
            }
            TableColumnDTO columnsDto = new TableColumnDTO().setIndex(index).setName(excelHeadValue).setKey(excelHeadValue);
            if(MapUtils.isNotEmpty(headColumnMap)){
                //自定义key
                if(headColumnMap.containsKey(excelHeadValue)){
                    columnsDto.setKey(headColumnMap.get(excelHeadValue));
                }
            }
            tableColumnsDtoList.add(columnsDto);
        }
        return tableColumnsDtoList;
    }

    public static void main(String[] args) throws IOException {
        String targetPath = System.getProperty("user.dir") + "/spring-boot-example-excel/src/main/resources/file/exportexcel1.xlsx";

        FileInputStream inputStream = new FileInputStream(new File(targetPath));
        byte[] stream = IoUtils.toByteArray(inputStream);
        CommonEasyExcelImportDTO<Map<String,String>> dataList = parseExcelToViewByStream(stream, null,3);
        System.out.println(JSONArray.toJSONString(dataList));
        inputStream.close();
    }
}
