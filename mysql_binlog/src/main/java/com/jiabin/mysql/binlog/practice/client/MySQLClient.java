package com.jiabin.mysql.binlog.practice.client;//package com.jiabin.mysql.binlog.practice.client;
//
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//
//import com.github.shyiko.mysql.binlog.io.BufferedSocketInputStream;
//import com.github.shyiko.mysql.binlog.io.ByteArrayInputStream;
//import com.github.shyiko.mysql.binlog.io.ByteArrayOutputStream;
//
//public class MySQLClient {
//
//  private static final String MYSQL_HOST = "118.24.111.33";
//  private static final int MYSQL_PORT = 3307;
//  private static final String MYSQL_USER = "root";
//  private static final String MYSQL_PASSWORD = "123123";
//
//  public static void main(String[] args) {
//    int packetNumber = 0;
//    try (Socket socket = new Socket(MYSQL_HOST, MYSQL_PORT);
//        ByteArrayInputStream input = new ByteArrayInputStream(new BufferedSocketInputStream(socket.getInputStream()));
//        ByteArrayOutputStream output = new ByteArrayOutputStream(socket.getOutputStream())) {
//      // 读取初始握手包
//      byte[] handshakePacket = readPacket(input);
//      
//      
//      parseHandshake(handshakePacket);
//      // 发送认证信息
//      sendAuthentication(output, MYSQL_USER, MYSQL_PASSWORD);
//      // 发送 SQL 命令
//      String sql = "SELECT * FROM test.t_person";
//      sendQuery(output, sql);
//      // 读取结果
//      byte[] resultPacket = readPacket(input);
//      parseResult(resultPacket);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  private static byte[] readPacket(ByteArrayInputStream inputStream) throws IOException {
//    int length = inputStream.readInteger(3);
//    int sequence = inputStream.read(); // sequence
//    if ( sequence != packetNumber++ ) {
//        throw new IOException("unexpected sequence #" + sequence);
//    }
//    return inputStream.read(length);
//  }
//
//  private static void parseHandshake(byte[] handshakePacket) {
//    // 解析握手包，这里只是一个简单的示例，实际解析需要更复杂的逻辑
//    System.out.println("Handshake packet received");
//  }
//
//  private static void sendAuthentication(DataOutputStream output, String user, String password) throws IOException {
//    // 发送认证信息，这里只是一个简单的示例，实际认证需要更复杂的逻辑
//    output.writeByte(1); // Command: COM_AUTHENTICATE
//    output.writeUTF(user);
//    output.writeUTF(password);
//    output.flush();
//  }
//
//  private static void sendQuery(DataOutputStream output, String sql) throws IOException {
//    // 发送 SQL 命令
//    output.writeByte(3); // Command: COM_QUERY
//    output.writeUTF(sql);
//    output.flush();
//  }
//
//  private static void parseResult(byte[] resultPacket) {
//    // 解析结果包，这里只是一个简单的示例，实际解析需要更复杂的逻辑
//    System.out.println("Result packet received: " + new String(resultPacket));
//  }
//}