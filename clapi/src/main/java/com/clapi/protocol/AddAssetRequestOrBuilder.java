// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: clapi.proto

package com.clapi.protocol;

public interface AddAssetRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:clapi.AddAssetRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string label = 1;</code>
   */
  boolean hasLabel();
  /**
   * <code>required string label = 1;</code>
   */
  java.lang.String getLabel();
  /**
   * <code>required string label = 1;</code>
   */
  com.google.protobuf.ByteString
      getLabelBytes();

  /**
   * <code>required .clapi.AssetType type = 2;</code>
   */
  boolean hasType();
  /**
   * <code>required .clapi.AssetType type = 2;</code>
   */
  com.clapi.protocol.AssetType getType();

  /**
   * <code>required .clapi.AssetMode mode = 3;</code>
   */
  boolean hasMode();
  /**
   * <code>required .clapi.AssetMode mode = 3;</code>
   */
  com.clapi.protocol.AssetMode getMode();

  /**
   * <code>required bytes data = 4;</code>
   */
  boolean hasData();
  /**
   * <code>required bytes data = 4;</code>
   */
  com.google.protobuf.ByteString getData();
}
