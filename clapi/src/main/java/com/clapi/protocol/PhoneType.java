// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: clapi.proto

package com.clapi.protocol;

/**
 * Protobuf enum {@code clapi.PhoneType}
 *
 * <pre>
 * Phone type
 * </pre>
 */
public enum PhoneType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>PHONE_HOME = 0;</code>
   */
  PHONE_HOME(0, 0),
  /**
   * <code>PHONE_WORK = 1;</code>
   */
  PHONE_WORK(1, 1),
  /**
   * <code>PHONE_CELL = 2;</code>
   */
  PHONE_CELL(2, 2),
  /**
   * <code>PHONE_OTHER = 3;</code>
   */
  PHONE_OTHER(3, 3),
  ;

  /**
   * <code>PHONE_HOME = 0;</code>
   */
  public static final int PHONE_HOME_VALUE = 0;
  /**
   * <code>PHONE_WORK = 1;</code>
   */
  public static final int PHONE_WORK_VALUE = 1;
  /**
   * <code>PHONE_CELL = 2;</code>
   */
  public static final int PHONE_CELL_VALUE = 2;
  /**
   * <code>PHONE_OTHER = 3;</code>
   */
  public static final int PHONE_OTHER_VALUE = 3;


  public final int getNumber() {
    return value;
  }

  public static PhoneType valueOf(int value) {
    switch (value) {
      case 0: return PHONE_HOME;
      case 1: return PHONE_WORK;
      case 2: return PHONE_CELL;
      case 3: return PHONE_OTHER;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<PhoneType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      PhoneType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<PhoneType>() {
          public PhoneType findValueByNumber(int number) {
            return PhoneType.valueOf(number);
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
        .getEnumTypes().get(1);
  }

  private static final PhoneType[] VALUES = values();

  public static PhoneType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    return VALUES[desc.getIndex()];
  }

  private final int index;
  private final int value;

  private PhoneType(int index, int value) {
    this.index = index;
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:clapi.PhoneType)
}
