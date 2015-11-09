// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: clapi.proto

package com.clapi;

/**
 * Protobuf type {@code clapi.AddPhoneRequest}
 *
 * <pre>
 * The request/response for addPhone
 * </pre>
 */
public  final class AddPhoneRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:clapi.AddPhoneRequest)
    AddPhoneRequestOrBuilder {
  // Use AddPhoneRequest.newBuilder() to construct.
  private AddPhoneRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private AddPhoneRequest() {
    uidPerson_ = "";
    phone_ = "";
    type_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AddPhoneRequest(
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
            uidPerson_ = bs;
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            phone_ = bs;
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            type_ = input.readInt32();
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
    return com.clapi.CLApiProtos.internal_static_clapi_AddPhoneRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.clapi.CLApiProtos.internal_static_clapi_AddPhoneRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.clapi.AddPhoneRequest.class, com.clapi.AddPhoneRequest.Builder.class);
  }

  private int bitField0_;
  public static final int UID_PERSON_FIELD_NUMBER = 1;
  private volatile java.lang.Object uidPerson_;
  /**
   * <code>required string uid_person = 1;</code>
   */
  public boolean hasUidPerson() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required string uid_person = 1;</code>
   */
  public java.lang.String getUidPerson() {
    java.lang.Object ref = uidPerson_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        uidPerson_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string uid_person = 1;</code>
   */
  public com.google.protobuf.ByteString
      getUidPersonBytes() {
    java.lang.Object ref = uidPerson_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      uidPerson_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PHONE_FIELD_NUMBER = 2;
  private volatile java.lang.Object phone_;
  /**
   * <code>required string phone = 2;</code>
   */
  public boolean hasPhone() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required string phone = 2;</code>
   */
  public java.lang.String getPhone() {
    java.lang.Object ref = phone_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        phone_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string phone = 2;</code>
   */
  public com.google.protobuf.ByteString
      getPhoneBytes() {
    java.lang.Object ref = phone_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      phone_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TYPE_FIELD_NUMBER = 3;
  private int type_;
  /**
   * <code>required int32 type = 3;</code>
   */
  public boolean hasType() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>required int32 type = 3;</code>
   */
  public int getType() {
    return type_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasUidPerson()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasPhone()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, uidPerson_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, phone_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, type_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, uidPerson_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, phone_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, type_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.clapi.AddPhoneRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.clapi.AddPhoneRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.clapi.AddPhoneRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.clapi.AddPhoneRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.clapi.AddPhoneRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.clapi.AddPhoneRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.clapi.AddPhoneRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.clapi.AddPhoneRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.clapi.AddPhoneRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.clapi.AddPhoneRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.clapi.AddPhoneRequest prototype) {
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
   * Protobuf type {@code clapi.AddPhoneRequest}
   *
   * <pre>
   * The request/response for addPhone
   * </pre>
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:clapi.AddPhoneRequest)
      com.clapi.AddPhoneRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.clapi.CLApiProtos.internal_static_clapi_AddPhoneRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.clapi.CLApiProtos.internal_static_clapi_AddPhoneRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.clapi.AddPhoneRequest.class, com.clapi.AddPhoneRequest.Builder.class);
    }

    // Construct using com.clapi.AddPhoneRequest.newBuilder()
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
      uidPerson_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      phone_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      type_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.clapi.CLApiProtos.internal_static_clapi_AddPhoneRequest_descriptor;
    }

    public com.clapi.AddPhoneRequest getDefaultInstanceForType() {
      return com.clapi.AddPhoneRequest.getDefaultInstance();
    }

    public com.clapi.AddPhoneRequest build() {
      com.clapi.AddPhoneRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.clapi.AddPhoneRequest buildPartial() {
      com.clapi.AddPhoneRequest result = new com.clapi.AddPhoneRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.uidPerson_ = uidPerson_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.phone_ = phone_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.type_ = type_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.clapi.AddPhoneRequest) {
        return mergeFrom((com.clapi.AddPhoneRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.clapi.AddPhoneRequest other) {
      if (other == com.clapi.AddPhoneRequest.getDefaultInstance()) return this;
      if (other.hasUidPerson()) {
        bitField0_ |= 0x00000001;
        uidPerson_ = other.uidPerson_;
        onChanged();
      }
      if (other.hasPhone()) {
        bitField0_ |= 0x00000002;
        phone_ = other.phone_;
        onChanged();
      }
      if (other.hasType()) {
        setType(other.getType());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasUidPerson()) {
        return false;
      }
      if (!hasPhone()) {
        return false;
      }
      if (!hasType()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.clapi.AddPhoneRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.clapi.AddPhoneRequest) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object uidPerson_ = "";
    /**
     * <code>required string uid_person = 1;</code>
     */
    public boolean hasUidPerson() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string uid_person = 1;</code>
     */
    public java.lang.String getUidPerson() {
      java.lang.Object ref = uidPerson_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          uidPerson_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string uid_person = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUidPersonBytes() {
      java.lang.Object ref = uidPerson_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uidPerson_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string uid_person = 1;</code>
     */
    public Builder setUidPerson(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      uidPerson_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string uid_person = 1;</code>
     */
    public Builder clearUidPerson() {
      bitField0_ = (bitField0_ & ~0x00000001);
      uidPerson_ = getDefaultInstance().getUidPerson();
      onChanged();
      return this;
    }
    /**
     * <code>required string uid_person = 1;</code>
     */
    public Builder setUidPersonBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      uidPerson_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object phone_ = "";
    /**
     * <code>required string phone = 2;</code>
     */
    public boolean hasPhone() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string phone = 2;</code>
     */
    public java.lang.String getPhone() {
      java.lang.Object ref = phone_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          phone_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string phone = 2;</code>
     */
    public com.google.protobuf.ByteString
        getPhoneBytes() {
      java.lang.Object ref = phone_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        phone_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string phone = 2;</code>
     */
    public Builder setPhone(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      phone_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string phone = 2;</code>
     */
    public Builder clearPhone() {
      bitField0_ = (bitField0_ & ~0x00000002);
      phone_ = getDefaultInstance().getPhone();
      onChanged();
      return this;
    }
    /**
     * <code>required string phone = 2;</code>
     */
    public Builder setPhoneBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      phone_ = value;
      onChanged();
      return this;
    }

    private int type_ ;
    /**
     * <code>required int32 type = 3;</code>
     */
    public boolean hasType() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 type = 3;</code>
     */
    public int getType() {
      return type_;
    }
    /**
     * <code>required int32 type = 3;</code>
     */
    public Builder setType(int value) {
      bitField0_ |= 0x00000004;
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 type = 3;</code>
     */
    public Builder clearType() {
      bitField0_ = (bitField0_ & ~0x00000004);
      type_ = 0;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:clapi.AddPhoneRequest)
  }

  // @@protoc_insertion_point(class_scope:clapi.AddPhoneRequest)
  private static final com.clapi.AddPhoneRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.clapi.AddPhoneRequest();
  }

  public static com.clapi.AddPhoneRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AddPhoneRequest>
      PARSER = new com.google.protobuf.AbstractParser<AddPhoneRequest>() {
    public AddPhoneRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new AddPhoneRequest(input, extensionRegistry);
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

  public static com.google.protobuf.Parser<AddPhoneRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AddPhoneRequest> getParserForType() {
    return PARSER;
  }

  public com.clapi.AddPhoneRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

