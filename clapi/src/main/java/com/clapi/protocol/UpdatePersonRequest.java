// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: clapi.proto

package com.clapi.protocol;

/**
 * Protobuf type {@code clapi.UpdatePersonRequest}
 *
 * <pre>
 * The request/response for updatePerson
 * </pre>
 */
public  final class UpdatePersonRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:clapi.UpdatePersonRequest)
    UpdatePersonRequestOrBuilder {
  // Use UpdatePersonRequest.newBuilder() to construct.
  private UpdatePersonRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private UpdatePersonRequest() {
    uid_ = "";
    firstname_ = "";
    lastname_ = "";
    imageurl_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UpdatePersonRequest(
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
            uid_ = bs;
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            firstname_ = bs;
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            lastname_ = bs;
            break;
          }
          case 34: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000008;
            imageurl_ = bs;
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
    return com.clapi.protocol.CLApiProtos.internal_static_clapi_UpdatePersonRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.clapi.protocol.CLApiProtos.internal_static_clapi_UpdatePersonRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.clapi.protocol.UpdatePersonRequest.class, com.clapi.protocol.UpdatePersonRequest.Builder.class);
  }

  private int bitField0_;
  public static final int UID_FIELD_NUMBER = 1;
  private volatile java.lang.Object uid_;
  /**
   * <code>required string uid = 1;</code>
   */
  public boolean hasUid() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required string uid = 1;</code>
   */
  public java.lang.String getUid() {
    java.lang.Object ref = uid_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        uid_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string uid = 1;</code>
   */
  public com.google.protobuf.ByteString
      getUidBytes() {
    java.lang.Object ref = uid_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      uid_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int FIRSTNAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object firstname_;
  /**
   * <code>required string firstname = 2;</code>
   */
  public boolean hasFirstname() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required string firstname = 2;</code>
   */
  public java.lang.String getFirstname() {
    java.lang.Object ref = firstname_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        firstname_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string firstname = 2;</code>
   */
  public com.google.protobuf.ByteString
      getFirstnameBytes() {
    java.lang.Object ref = firstname_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      firstname_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int LASTNAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object lastname_;
  /**
   * <code>optional string lastname = 3;</code>
   */
  public boolean hasLastname() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>optional string lastname = 3;</code>
   */
  public java.lang.String getLastname() {
    java.lang.Object ref = lastname_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        lastname_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string lastname = 3;</code>
   */
  public com.google.protobuf.ByteString
      getLastnameBytes() {
    java.lang.Object ref = lastname_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      lastname_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int IMAGEURL_FIELD_NUMBER = 4;
  private volatile java.lang.Object imageurl_;
  /**
   * <code>optional string imageurl = 4;</code>
   */
  public boolean hasImageurl() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>optional string imageurl = 4;</code>
   */
  public java.lang.String getImageurl() {
    java.lang.Object ref = imageurl_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        imageurl_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string imageurl = 4;</code>
   */
  public com.google.protobuf.ByteString
      getImageurlBytes() {
    java.lang.Object ref = imageurl_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      imageurl_ = b;
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

    if (!hasUid()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasFirstname()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, uid_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, firstname_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 3, lastname_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 4, imageurl_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, uid_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, firstname_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(3, lastname_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(4, imageurl_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.clapi.protocol.UpdatePersonRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.clapi.protocol.UpdatePersonRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.clapi.protocol.UpdatePersonRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.clapi.protocol.UpdatePersonRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.clapi.protocol.UpdatePersonRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.clapi.protocol.UpdatePersonRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.clapi.protocol.UpdatePersonRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.clapi.protocol.UpdatePersonRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.clapi.protocol.UpdatePersonRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.clapi.protocol.UpdatePersonRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.clapi.protocol.UpdatePersonRequest prototype) {
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
   * Protobuf type {@code clapi.UpdatePersonRequest}
   *
   * <pre>
   * The request/response for updatePerson
   * </pre>
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:clapi.UpdatePersonRequest)
      com.clapi.protocol.UpdatePersonRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.clapi.protocol.CLApiProtos.internal_static_clapi_UpdatePersonRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.clapi.protocol.CLApiProtos.internal_static_clapi_UpdatePersonRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.clapi.protocol.UpdatePersonRequest.class, com.clapi.protocol.UpdatePersonRequest.Builder.class);
    }

    // Construct using com.clapi.protocol.UpdatePersonRequest.newBuilder()
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
      uid_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      firstname_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      lastname_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      imageurl_ = "";
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.clapi.protocol.CLApiProtos.internal_static_clapi_UpdatePersonRequest_descriptor;
    }

    public com.clapi.protocol.UpdatePersonRequest getDefaultInstanceForType() {
      return com.clapi.protocol.UpdatePersonRequest.getDefaultInstance();
    }

    public com.clapi.protocol.UpdatePersonRequest build() {
      com.clapi.protocol.UpdatePersonRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.clapi.protocol.UpdatePersonRequest buildPartial() {
      com.clapi.protocol.UpdatePersonRequest result = new com.clapi.protocol.UpdatePersonRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.uid_ = uid_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.firstname_ = firstname_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.lastname_ = lastname_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.imageurl_ = imageurl_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.clapi.protocol.UpdatePersonRequest) {
        return mergeFrom((com.clapi.protocol.UpdatePersonRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.clapi.protocol.UpdatePersonRequest other) {
      if (other == com.clapi.protocol.UpdatePersonRequest.getDefaultInstance()) return this;
      if (other.hasUid()) {
        bitField0_ |= 0x00000001;
        uid_ = other.uid_;
        onChanged();
      }
      if (other.hasFirstname()) {
        bitField0_ |= 0x00000002;
        firstname_ = other.firstname_;
        onChanged();
      }
      if (other.hasLastname()) {
        bitField0_ |= 0x00000004;
        lastname_ = other.lastname_;
        onChanged();
      }
      if (other.hasImageurl()) {
        bitField0_ |= 0x00000008;
        imageurl_ = other.imageurl_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasUid()) {
        return false;
      }
      if (!hasFirstname()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.clapi.protocol.UpdatePersonRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.clapi.protocol.UpdatePersonRequest) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object uid_ = "";
    /**
     * <code>required string uid = 1;</code>
     */
    public boolean hasUid() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string uid = 1;</code>
     */
    public java.lang.String getUid() {
      java.lang.Object ref = uid_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          uid_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string uid = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUidBytes() {
      java.lang.Object ref = uid_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string uid = 1;</code>
     */
    public Builder setUid(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      uid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string uid = 1;</code>
     */
    public Builder clearUid() {
      bitField0_ = (bitField0_ & ~0x00000001);
      uid_ = getDefaultInstance().getUid();
      onChanged();
      return this;
    }
    /**
     * <code>required string uid = 1;</code>
     */
    public Builder setUidBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      uid_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object firstname_ = "";
    /**
     * <code>required string firstname = 2;</code>
     */
    public boolean hasFirstname() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string firstname = 2;</code>
     */
    public java.lang.String getFirstname() {
      java.lang.Object ref = firstname_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          firstname_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string firstname = 2;</code>
     */
    public com.google.protobuf.ByteString
        getFirstnameBytes() {
      java.lang.Object ref = firstname_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        firstname_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string firstname = 2;</code>
     */
    public Builder setFirstname(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      firstname_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string firstname = 2;</code>
     */
    public Builder clearFirstname() {
      bitField0_ = (bitField0_ & ~0x00000002);
      firstname_ = getDefaultInstance().getFirstname();
      onChanged();
      return this;
    }
    /**
     * <code>required string firstname = 2;</code>
     */
    public Builder setFirstnameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      firstname_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object lastname_ = "";
    /**
     * <code>optional string lastname = 3;</code>
     */
    public boolean hasLastname() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string lastname = 3;</code>
     */
    public java.lang.String getLastname() {
      java.lang.Object ref = lastname_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          lastname_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string lastname = 3;</code>
     */
    public com.google.protobuf.ByteString
        getLastnameBytes() {
      java.lang.Object ref = lastname_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        lastname_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string lastname = 3;</code>
     */
    public Builder setLastname(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      lastname_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string lastname = 3;</code>
     */
    public Builder clearLastname() {
      bitField0_ = (bitField0_ & ~0x00000004);
      lastname_ = getDefaultInstance().getLastname();
      onChanged();
      return this;
    }
    /**
     * <code>optional string lastname = 3;</code>
     */
    public Builder setLastnameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      lastname_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object imageurl_ = "";
    /**
     * <code>optional string imageurl = 4;</code>
     */
    public boolean hasImageurl() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional string imageurl = 4;</code>
     */
    public java.lang.String getImageurl() {
      java.lang.Object ref = imageurl_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          imageurl_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string imageurl = 4;</code>
     */
    public com.google.protobuf.ByteString
        getImageurlBytes() {
      java.lang.Object ref = imageurl_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        imageurl_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string imageurl = 4;</code>
     */
    public Builder setImageurl(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      imageurl_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string imageurl = 4;</code>
     */
    public Builder clearImageurl() {
      bitField0_ = (bitField0_ & ~0x00000008);
      imageurl_ = getDefaultInstance().getImageurl();
      onChanged();
      return this;
    }
    /**
     * <code>optional string imageurl = 4;</code>
     */
    public Builder setImageurlBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      imageurl_ = value;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:clapi.UpdatePersonRequest)
  }

  // @@protoc_insertion_point(class_scope:clapi.UpdatePersonRequest)
  private static final com.clapi.protocol.UpdatePersonRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.clapi.protocol.UpdatePersonRequest();
  }

  public static com.clapi.protocol.UpdatePersonRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<UpdatePersonRequest>
      PARSER = new com.google.protobuf.AbstractParser<UpdatePersonRequest>() {
    public UpdatePersonRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new UpdatePersonRequest(input, extensionRegistry);
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

  public static com.google.protobuf.Parser<UpdatePersonRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UpdatePersonRequest> getParserForType() {
    return PARSER;
  }

  public com.clapi.protocol.UpdatePersonRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

