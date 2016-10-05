// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: clapi.proto

package com.clapi.protocol;

/**
 * Protobuf enum {@code clapi.AssetType}
 *
 * <pre>
 * Asset type
 * </pre>
 */
public enum AssetType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>ASSET_IMAGE = 0;</code>
   */
  ASSET_IMAGE(0, 0),
  /**
   * <code>ASSET_FILE = 1;</code>
   */
  ASSET_FILE(1, 1),
  /**
   * <code>ASSET_OTHER = 2;</code>
   */
  ASSET_OTHER(2, 2),
  ;

  /**
   * <code>ASSET_IMAGE = 0;</code>
   */
  public static final int ASSET_IMAGE_VALUE = 0;
  /**
   * <code>ASSET_FILE = 1;</code>
   */
  public static final int ASSET_FILE_VALUE = 1;
  /**
   * <code>ASSET_OTHER = 2;</code>
   */
  public static final int ASSET_OTHER_VALUE = 2;


  public final int getNumber() {
    return value;
  }

  public static AssetType valueOf(int value) {
    switch (value) {
      case 0: return ASSET_IMAGE;
      case 1: return ASSET_FILE;
      case 2: return ASSET_OTHER;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<AssetType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      AssetType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<AssetType>() {
          public AssetType findValueByNumber(int number) {
            return AssetType.valueOf(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(index);
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.clapi.protocol.CLApiProtos.getDescriptor()
        .getEnumTypes().get(3);
  }

  private static final AssetType[] VALUES = values();

  public static AssetType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }

  private final int index;
  private final int value;

  private AssetType(int index, int value) {
    this.index = index;
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:clapi.AssetType)
}

