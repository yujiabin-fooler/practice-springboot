package com.jiabin.mysql.binlog.practice.test;

import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.BinaryLogClient.EventListener;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.EventHeader;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.github.shyiko.mysql.binlog.event.TableMapEventMetadata;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;

public class ListenerMySQLTestCloud3307 {

  public static void main(String[] args) throws Exception {
    BinaryLogClient client = new BinaryLogClient("118.24.111.33", 3307, "test", "root", "123123");
    EventDeserializer eventDeserializer = new EventDeserializer();
    eventDeserializer.setCompatibilityMode(
        EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
        EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
    );
    client.setEventDeserializer(eventDeserializer);
    client.registerEventListener(new EventListener() {
        @Override
        public void onEvent(Event event) {
          EventHeader header = event.getHeader() ;
          switch(header.getEventType()) {
            case EXT_WRITE_ROWS:
              WriteRowsEventData writeData = event.getData() ;
              List<Serializable[]> rows = writeData.getRows() ;
              for (Serializable row : rows) {
                if (row.getClass().isArray()) {
                  printRow(row);
                }
              }
              break ;
            case EXT_UPDATE_ROWS:
              System.err.println(header.getServerId() + "@" + header.getTimestamp()) ;
              UpdateRowsEventData updateData = event.getData() ;
              List<Entry<Serializable[], Serializable[]>> updateRows = updateData.getRows() ;
              for (Entry<Serializable[], Serializable[]> entry : updateRows) {
                printRow(entry.getKey()) ;
                System.out.println(">>>>>>>>>>>>>>>>>>>>>before") ;
                printRow(entry.getValue()) ;
                System.out.println(">>>>>>>>>>>>>>>>>>>>>after") ;
              }
              break ;
            case EXT_DELETE_ROWS:
              DeleteRowsEventData deleteData = event.getData() ;
              List<Serializable[]> deleteRow = deleteData.getRows() ;
              for (Serializable row : deleteRow) {
                if (row.getClass().isArray()) {
                  printRow(row);
                }
              }
              break ;
            case TABLE_MAP:
              TableMapEventData data = event.getData() ;
              System.err.println(header.getServerId() + "@" + header.getTimestamp()) ;
              System.out.printf("变更数据库: %s, 表: %s%n", data.getDatabase(), data.getTable()) ;
              TableMapEventMetadata metadata = data.getEventMetadata() ;
              System.err.printf("columns: %s, primary keys: %s%n", metadata.getColumnNames(), metadata.getSimplePrimaryKeys()) ;
              break ;
            default:
              break ;
          }
        }
        private void printRow(Serializable row) {
          Serializable[] ss = (Serializable[]) row ;
          for (Serializable s : ss) {
            if (s.getClass().isArray()) {
              System.out.print(new String((byte[])s) + "\t") ;
            } else {
              System.out.print(s + "\t") ;
            }
          }
          System.out.println() ;
        }
    });
    client.connect();
  }
  
}
