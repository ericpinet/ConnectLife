// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: clapi.proto

package com.clapi.protocol;

/**
 * Protobuf type {@code clapi.AddRoomRequest}
 *
 * <pre>
 * The request/response for addRoom
 * </pre>
 */
public  final class AddRoomRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:clapi.AddRoomRequest)
    AddRoomRequestOrBuilder {
  // Use AddRoomRequest.newBuilder() to construct.
  private AddRoomRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private AddRoomRequest() {
    uidZone_ = "";
    label_ = "";
    imageuid_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AddRoomRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry) {
    this();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownField(input, unknownFields,
                                   extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000001;
            uidZone_ = bs;
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            label_ = bs;
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            imageuid_ = bs;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw new RuntimeException(e.setUnfinishedMessage(this));
    } catch (java.io.IOException e) {
      throw new RuntimeException(
          new com.google.protobuf.InvalidProtocolBufferException(
              e.getMessage()).setUnfinishedMessage(this));
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.clapi.protocol.CLApiProtos.internal_static_clapi_AddRoomRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.clapi.protocol.CLApiProtos.internal_static_clapi_AddRoomRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.clapi.protocol.AddRoomRequest.class, com.clapi.protocol.AddRoomRequest.Builder.class);
  }

  private int bitField0_;
  public static final int UID_ZONE_FIELD_NUMBER = 1;
  private volatile java.lang.Object uidZone_;
  /**
   * <code>required string uid_zone = 1;</code>
   */
  public boolean hasUidZone() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required string uid_zone = 1;</code>
   */
  public java.lang.String getUidZone() {
    java.lang.Object ref = uidZone_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        uidZone_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string uid_zone = 1;</code>
   */
  public com.google.protobuf.ByteString
      getUidZoneBytes() {
    java.lang.Object ref = uidZone_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      uidZone_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int LABEL_FIELD_NUMBER = 2;
  private volatile java.lang.Object label_;
  /**
   * <code>required string label = 2;</code>
   */
  public boolean hasLabel() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required string label = 2;</code>
   */
  public java.lang.String getLabel() {
    java.lang.Object ref = label_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        label_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string label = 2;</code>
   */
  public com.google.protobuf.ByteString
      getLabelBytes() {
    java.lang.Object ref = label_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      label_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int IMAGEUID_FIELD_NUMBER = 3;
  private volatile java.lang.Object imageuid_;
  /**
   * <code>optional string imageuid = 3;</code>
   */
  public boolean hasImageuid() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>optional string imageuid = 3;</code>
   */
  public java.lang.String getImageuid() {
    java.lang.Object ref = imageuid_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        imageuid_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string imageuid = 3;</code>
   */
  public com.google.protobuf.ByteString
      getImageuidBytes() {
    java.lang.Object ref = imageuid_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      imageuid_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasUidZone()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasLabel()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, uidZone_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, label_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 3, imageuid_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, uidZone_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, label_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(3, imageuid_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.clapi.protocol.AddRoomRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.clapi.protocol.AddRoomRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.clapi.protocol.AddRoomRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.clapi.protocol.AddRoomRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.clapi.protocol.AddRoomRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.clapi.protocol.AddRoomRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.clapi.protocol.AddRoomRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.clapi.protocol.AddRoomRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.clapi.protocol.AddRoomRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.clapi.protocol.AddRoomRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.clapi.protocol.AddRoomRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code clapi.AddRoomRequest}
   *
   * <pre>
   * The request/response for addRoom
   * </pre>
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:clapi.AddRoomRequest)
      com.clapi.protocol.AddRoomRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.clapi.protocol.CLApiProtos.internal_static_clapi_AddRoomRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.clapi.protocol.CLApiProtos.internal_static_clapi_AddRoomRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.clapi.protocol.AddRoomRequest.class, com.clapi.protocol.AddRoomRequest.Builder.class);
    }

    // Construct using com.clapi.protocol.AddRoomRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      uidZone_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      label_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      imageuid_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.clapi.protocol.CLApiProtos.internal_static_clapi_AddRoomRequest_descriptor;
    }

    public com.clapi.protocol.AddRoomRequest getDefaultInstanceForType() {
      return com.clapi.protocol.AddRoomRequest.getDefaultInstance();
    }

    public com.clapi.protocol.AddRoomRequest build() {
      com.clapi.protocol.AddRoomRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.clapi.protocol.AddRoomRequest buildPartial() {
      com.clapi.protocol.AddRoomRequest result = new com.clapi.protocol.AddRoomRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.uidZone_ = uidZone_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.label_ = label_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.imageuid_ = imageuid_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.clapi.protocol.AddRoomRequest) {
        return mergeFrom((com.clapi.protocol.AddRoomRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.clapi.protocol.AddRoomRequest other) {
      if (other == com.clapi.protocol.AddRoomRequest.getDefaultInstance()) return this;
      if (other.hasUidZone()) {
        bitField0_ |= 0x00000001;
        uidZone_ = other.uidZone_;
        onChanged();
      }
      if (other.hasLabel()) {
        bitField0_ |= 0x00000002;
        label_ = other.label_;
        onChanged();
      }
      if (other.hasImageuid()) {
        bitField0_ |= 0x00000004;
        imageuid_ = other.imageuid_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasUidZone()) {
        return false;
      }
      if (!hasLabel()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.clapi.protocol.AddRoomRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.clapi.protocol.AddRoomRequest) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object uidZone_ = "";
    /**
     * <code>required string uid_zone = 1;</code>
     */
    public boolean hasUidZone() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string uid_zone = 1;</code>
     */
    public java.lang.String getUidZone() {
      java.lang.Object ref = uidZone_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          uidZone_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string uid_zone = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUidZoneBytes() {
      java.lang.Object ref = uidZone_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uidZone_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string uid_zone = 1;</code>
     */
    public Builder setUidZone(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      uidZone_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string uid_zone = 1;</code>
     */
    public Builder clearUidZone() {
      bitField0_ = (bitField0_ & ~0x00000001);
      uidZone_ = getDefaultInstance().getUidZone();
      onChanged();
      return this;
    }
    /**
     * <code>required string uid_zone = 1;</code>
     */
    public Builder setUidZoneBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      uidZone_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object label_ = "";
    /**
     * <code>required string label = 2;</code>
     */
    public boolean hasLabel() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string label = 2;</code>
     */
    public java.lang.String getLabel() {
      java.lang.Object ref = label_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          label_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string label = 2;</code>
     */
    public com.google.protobuf.ByteString
        getLabelBytes() {
      java.lang.Object ref = label_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        label_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string label = 2;</code>
     */
    public Builder setLabel(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      label_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string label = 2;</code>
     */
    public Builder clearLabel() {
      bitField0_ = (bitField0_ & ~0x00000002);
      label_ = getDefaultInstance().getLabel();
      onChanged();
      return this;
    }
    /**
     * <code>required string label = 2;</code>
     */
    public Builder setLabelBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      label_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object imageuid_ = "";
    /**
     * <code>optional string imageuid = 3;</code>
     */
    public boolean hasImageuid() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string imageuid = 3;</code>
     */
    public java.lang.String getImageuid() {
      java.lang.Object ref = imageuid_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          imageuid_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string imageuid = 3;</code>
     */
    public com.google.protobuf.ByteString
        getImageuidBytes() {
      java.lang.Object ref = imageuid_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        imageuid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string imageuid = 3;</code>
     */
    public Builder setImageuid(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      imageuid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string imageuid = 3;</code>
     */
    public Builder clearImageuid() {
      bitField0_ = (bitField0_ & ~0x00000004);
      imageuid_ = getDefaultInstance().getImageuid();
      onChanged();
      return this;
    }
    /**
     * <code>optional string imageuid = 3;</code>
     */
    public Builder setImageuidBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      imageuid_ = value;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:clapi.AddRoomRequest)
  }

  // @@protoc_insertion_point(class_scope:clapi.AddRoomRequest)
  private static final com.clapi.protocol.AddRoomRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.clapi.protocol.AddRoomRequest();
  }

  public static com.clapi.protocol.AddRoomRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AddRoomRequest>
      PARSER = new com.google.protobuf.AbstractParser<AddRoomRequest>() {
    public AddRoomRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new AddRoomRequest(input, extensionRegistry);
      } catch (RuntimeException e) {
        if (e.getCause() instanceof
            com.google.protobuf.InvalidProtocolBufferException) {
          throw (com.google.protobuf.InvalidProtocolBufferException)
              e.getCause();
        }
        throw e;
      }
    }
  };

  public static com.google.protobuf.Parser<AddRoomRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AddRoomRequest> getParserForType() {
    return PARSER;
  }

  public com.clapi.protocol.AddRoomRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

