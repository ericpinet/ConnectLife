// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: clapi.proto

package com.clapi.protocol;

public interface UpdateRoomResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:clapi.UpdateRoomResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string uid = 1;</code>
   */
  boolean hasUid();
  /**
   * <code>required string uid = 1;</code>
   */
  java.lang.String getUid();
  /**
   * <code>required string uid = 1;</code>
   */
  com.google.protobuf.ByteString
      getUidBytes();

  /**
   * <code>optional string error = 2;</code>
   */
  boolean hasError();
  /**
   * <code>optional string error = 2;</code>
   */
  java.lang.String getError();
  /**
   * <code>optional string error = 2;</code>
   */
  com.google.protobuf.ByteString
      getErrorBytes();
}