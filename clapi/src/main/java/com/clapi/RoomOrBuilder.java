// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: clapi.proto

package com.clapi;

public interface RoomOrBuilder extends
    // @@protoc_insertion_point(interface_extends:clapi.Room)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string uid = 1;</code>
   *
   * <pre>
   * UID of the room
   * </pre>
   */
  boolean hasUid();
  /**
   * <code>required string uid = 1;</code>
   *
   * <pre>
   * UID of the room
   * </pre>
   */
  java.lang.String getUid();
  /**
   * <code>required string uid = 1;</code>
   *
   * <pre>
   * UID of the room
   * </pre>
   */
  com.google.protobuf.ByteString
      getUidBytes();

  /**
   * <code>required string label = 2;</code>
   *
   * <pre>
   * Label of the room
   * </pre>
   */
  boolean hasLabel();
  /**
   * <code>required string label = 2;</code>
   *
   * <pre>
   * Label of the room
   * </pre>
   */
  java.lang.String getLabel();
  /**
   * <code>required string label = 2;</code>
   *
   * <pre>
   * Label of the room
   * </pre>
   */
  com.google.protobuf.ByteString
      getLabelBytes();

  /**
   * <code>repeated .clapi.Accessory accessories = 3;</code>
   *
   * <pre>
   * List accessory in this room
   * </pre>
   */
  java.util.List<com.clapi.Accessory> 
      getAccessoriesList();
  /**
   * <code>repeated .clapi.Accessory accessories = 3;</code>
   *
   * <pre>
   * List accessory in this room
   * </pre>
   */
  com.clapi.Accessory getAccessories(int index);
  /**
   * <code>repeated .clapi.Accessory accessories = 3;</code>
   *
   * <pre>
   * List accessory in this room
   * </pre>
   */
  int getAccessoriesCount();
  /**
   * <code>repeated .clapi.Accessory accessories = 3;</code>
   *
   * <pre>
   * List accessory in this room
   * </pre>
   */
  java.util.List<? extends com.clapi.AccessoryOrBuilder> 
      getAccessoriesOrBuilderList();
  /**
   * <code>repeated .clapi.Accessory accessories = 3;</code>
   *
   * <pre>
   * List accessory in this room
   * </pre>
   */
  com.clapi.AccessoryOrBuilder getAccessoriesOrBuilder(
      int index);

  /**
   * <code>optional string imageurl = 4;</code>
   *
   * <pre>
   * Image url for this room
   * </pre>
   */
  boolean hasImageurl();
  /**
   * <code>optional string imageurl = 4;</code>
   *
   * <pre>
   * Image url for this room
   * </pre>
   */
  java.lang.String getImageurl();
  /**
   * <code>optional string imageurl = 4;</code>
   *
   * <pre>
   * Image url for this room
   * </pre>
   */
  com.google.protobuf.ByteString
      getImageurlBytes();
}
