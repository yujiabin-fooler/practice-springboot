package com.jiabin.mysql.binlog.practice.toredis;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.BinaryLogClient.EventListener;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.EventHeader;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import com.jiabin.mysql.binlog.practice.client.DBComponent;
import com.jiabin.mysql.binlog.practice.domain.Person;

@Component
public class MySQLToRedisComponent implements CommandLineRunner {
  
  private final Map<Long, Db> MAPPING = new ConcurrentHashMap<>() ;
  
  private final StringRedisTemplate stringRedisTemplate ;
  private final BinaryLogClient client ;
  private final DBComponent dbc ;
  private final ObjectMapper mapper ;
  public MySQLToRedisComponent(StringRedisTemplate stringRedisTemplate, 
      BinaryLogClient client, DBComponent dbc,
      ObjectMapper mapper) {
    this.stringRedisTemplate = stringRedisTemplate ;
    this.client = client ;
    this.dbc = dbc ;
    this.mapper = mapper ;
  }

  public void listener() {
    client.registerEventListener(new EventListener() {
        @Override
        public void onEvent(Event event) {
          try {
            EventHeader header = event.getHeader() ;
            switch(header.getEventType()) {
              case EXT_WRITE_ROWS:
                WriteRowsEventData writeData = event.getData() ;
                List<Serializable[]> rows = writeData.getRows() ;
                for (Serializable[] row : rows) {
                  if (row.getClass().isArray()) {
                    printRow(row);
                    Person person = getData(MAPPING.get(writeData.getTableId()).table(), row, writeData.getIncludedColumns()) ;
                    stringRedisTemplate.opsForValue().set("data:" + person.getId(), mapper.writeValueAsString(person)) ;
                  }
                }
                break ;
              case EXT_UPDATE_ROWS:
                UpdateRowsEventData updateData = event.getData() ;
                List<Entry<Serializable[], Serializable[]>> updateRows = updateData.getRows() ;
                for (Entry<Serializable[], Serializable[]> entry : updateRows) {
                  Person person = getData(MAPPING.get(updateData.getTableId()).table(), entry.getValue(), updateData.getIncludedColumns()) ;
                  stringRedisTemplate.opsForValue().set("data:" + person.getId(), mapper.writeValueAsString(person)) ;
                }
                break ;
              case EXT_DELETE_ROWS:
                DeleteRowsEventData deleteData = event.getData() ;
                List<Serializable[]> deleteRow = deleteData.getRows() ;
                for (Serializable[] row : deleteRow) {
                  if (row.getClass().isArray()) {
                    Person person = getData(MAPPING.get(deleteData.getTableId()).table(), row, deleteData.getIncludedColumns()) ;
                    stringRedisTemplate.delete("data:" + person.getId()) ;
                  }
                }
                break ;
              case TABLE_MAP:
                TableMapEventData data = event.getData() ;
                MAPPING.computeIfAbsent(data.getTableId(), key -> new Db(data.getDatabase(), data.getTable())) ;
                break ;
              default:
                break ;
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
    });
    try {
      client.connect();
    } catch (IllegalStateException | IOException e) {
      e.printStackTrace();
    }
  }
  
  private void printRow(Serializable[] ss) {
    for (Serializable s : ss) {
      if (s.getClass().isArray()) {
        System.out.print(new String((byte[])s) + "\t") ;
      } else {
        System.out.print(s + "\t") ;
      }
    }
    System.out.println() ;
  }
  private Person getData(String table, Serializable[] ss, BitSet columns) {
    List<String> fields = this.dbc.fields(table) ;
    int len = columns.length() ;
    Person person = new Person() ;
    for (int i = 0; i < len; i++) {
      try {
        Class<Person> clazz = Person.class;
        String str = fields.get(i) ;
        Field field = clazz.getDeclaredField(str) ;
        field.setAccessible(true) ;
        if (ss[i].getClass().isArray()) {
          field.set(person, new String((byte[])ss[i])) ;
        } else {
          field.set(person, ss[i]) ;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return person ;
  }

  @Override
  public void run(String... args) throws Exception {
    this.listener() ;
  }
  
  private static record Db(String database, String table) {}
}
