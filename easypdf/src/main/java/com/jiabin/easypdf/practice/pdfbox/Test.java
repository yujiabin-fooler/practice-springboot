package com.jiabin.easypdf.practice.pdfbox;

import java.awt.Color;

import org.dromara.pdf.pdfbox.core.base.Document;
import org.dromara.pdf.pdfbox.core.base.Page;
import org.dromara.pdf.pdfbox.core.component.Table;
import org.dromara.pdf.pdfbox.core.component.TableCell;
import org.dromara.pdf.pdfbox.core.component.TableRow;
import org.dromara.pdf.pdfbox.core.component.Textarea;
import org.dromara.pdf.pdfbox.handler.PdfHandler;

public class Test {

  public static void main(String[] args) {
    // 定义pdf输出路径
    String outputPath = "E:\\pdf\\demo.pdf";
    // 创建文档
    Document document = PdfHandler.getDocumentHandler().create();
    // 设置字体
    document.setFontName("微软雅黑");
    // 创建页面
    Page page = new Page(document);
//    // 创建文本域
//    Textarea textarea = new Textarea(page);
//    // 设置文本
//    textarea.setText("Hello World!");
//    // 渲染文本
//    textarea.render();
    Table table = new Table(page) ;
    table.setBackgroundColor(Color.BLUE) ;
    table.setBorderColor(Color.RED) ;
    table.setBorderLineWidth(2) ;
    table.setCellWidths(50f) ;
    TableRow row = new TableRow(table) ;
    TableCell name = new TableCell(row) ;
    Textarea data = new Textarea(page) ;
    data.setText("Pack") ;
    name.addComponents(data) ;
    row.addCells(name) ;
    table.render() ;
    // 添加页面
    document.appendPage(page);
    // 保存文档
    document.save(outputPath);
    // 关闭文档
    document.close();
  }
  
}
