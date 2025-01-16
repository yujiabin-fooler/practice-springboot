package com.jiabin.mysql.binlog.practice.test;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import com.github.shyiko.mysql.binlog.BinaryLogFileReader;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.QueryEventData;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;

public class ReadBinlogFileTest {

  public static void main(String[] args) throws Exception {

    File binlogFile = new File("C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Data\\mysql-bin.000032");
    EventDeserializer eventDeserializer = new EventDeserializer();
    // 设置兼容性模式
    eventDeserializer.setCompatibilityMode(
          EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
          EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
    BinaryLogFileReader reader = new BinaryLogFileReader(binlogFile, eventDeserializer);
    try {
      for (Event event; (event = reader.readEvent()) != null;) {
        EventData data = event.getData() ;
        if (data instanceof WriteRowsEventData ed) {
          List<Serializable[]> rows = ed.getRows() ;
          rows.forEach(row -> {
            for (Serializable s : row) {
              if (s instanceof byte[] bs) {
                System.err.print(new String(bs) + "\t") ;
              } else {
                System.err.print(s + "\t") ;
              }
            }
            System.out.println() ;
          });
        } else if (data instanceof QueryEventData ed) {
          System.out.printf("查询事件：%s%n", ed.getSql()) ;
        } else if (data instanceof DeleteRowsEventData ed) {
          System.err.println("删除事件") ;
        } else if (data instanceof TableMapEventData ed) {
          String database = ed.getDatabase() ;
          String table = ed.getTable() ;
          System.out.printf("数据库: %s, 表名: %s%n", database, table) ;
        }
        
      }
    } finally {
      reader.close();
    }
  }

}
