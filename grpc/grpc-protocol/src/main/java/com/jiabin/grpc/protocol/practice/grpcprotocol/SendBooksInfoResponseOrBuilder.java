// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpcprotocol/firstTest.proto

package com.jiabin.grpc.protocol.practice.grpcprotocol;

public interface SendBooksInfoResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:grpcprotocol.SendBooksInfoResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>int32 code = 1;</code>
   * @return The code.
   */
  int getCode();

  /**
   * <pre>
   *返回消息
   * </pre>
   *
   * <code>string note = 2;</code>
   * @return The note.
   */
  java.lang.String getNote();
  /**
   * <pre>
   *返回消息
   * </pre>
   *
   * <code>string note = 2;</code>
   * @return The bytes for note.
   */
  com.google.protobuf.ByteString
      getNoteBytes();
}
