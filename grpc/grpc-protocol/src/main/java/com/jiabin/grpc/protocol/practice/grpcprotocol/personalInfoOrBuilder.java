// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpcprotocol/firstTest.proto

package com.jiabin.grpc.protocol.practice.grpcprotocol;

public interface personalInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:grpcprotocol.personalInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *身份证号
   * </pre>
   *
   * <code>int64 identityNumber = 1;</code>
   * @return The identityNumber.
   */
  long getIdentityNumber();

  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();
}
