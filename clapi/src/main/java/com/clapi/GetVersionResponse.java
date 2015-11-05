// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: clapi.proto

package com.clapi;

/**
 * Protobuf type {@code clapi.GetVersionResponse}
 *
 * <pre>
 * The response message containing the version number of the server.
 * </pre>
 */
public  final class GetVersionResponse extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:clapi.GetVersionResponse)
    GetVersionResponseOrBuilder {
  // Use GetVersionResponse.newBuilder() to construct.
  private GetVersionResponse(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private GetVersionResponse() {
    version_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetVersionResponse(
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
            version_ = bs;
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
    return com.clapi.CLApiProtos.internal_static_clapi_GetVersionResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.clapi.CLApiProtos.internal_static_clapi_GetVersionResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.clapi.GetVersionResponse.class, com.clapi.GetVersionResponse.Builder.class);
  }

  private int bitField0_;
  public static final int VERSION_FIELD_NUMBER = 1;
  private volatile java.lang.Object version_;
  /**
   * <code>required string version = 1;</code>
   */
  public boolean hasVersion() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required string version = 1;</code>
   */
  public java.lang.String getVersion() {
    java.lang.Object ref = version_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        version_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string version = 1;</code>
   */
  public com.google.protobuf.ByteString
      getVersionBytes() {
    java.lang.Object ref = version_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      version_ = b;
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

    if (!hasVersion()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, version_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, version_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.clapi.GetVersionResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.clapi.GetVersionResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.clapi.GetVersionResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.clapi.GetVersionResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.clapi.GetVersionResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.clapi.GetVersionResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.clapi.GetVersionResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.clapi.GetVersionResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.clapi.GetVersionResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.clapi.GetVersionResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.clapi.GetVersionResponse prototype) {
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
   * Protobuf type {@code clapi.GetVersionResponse}
   *
   * <pre>
   * The response message containing the version number of the server.
   * </pre>
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:clapi.GetVersionResponse)
      com.clapi.GetVersionResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.clapi.CLApiProtos.internal_static_clapi_GetVersionResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.clapi.CLApiProtos.internal_static_clapi_GetVersionResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.clapi.GetVersionResponse.class, com.clapi.GetVersionResponse.Builder.class);
    }

    // Construct using com.clapi.GetVersionResponse.newBuilder()
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
      version_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.clapi.CLApiProtos.internal_static_clapi_GetVersionResponse_descriptor;
    }

    public com.clapi.GetVersionResponse getDefaultInstanceForType() {
      return com.clapi.GetVersionResponse.getDefaultInstance();
    }

    public com.clapi.GetVersionResponse build() {
      com.clapi.GetVersionResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.clapi.GetVersionResponse buildPartial() {
      com.clapi.GetVersionResponse result = new com.clapi.GetVersionResponse(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.version_ = version_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.clapi.GetVersionResponse) {
        return mergeFrom((com.clapi.GetVersionResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.clapi.GetVersionResponse other) {
      if (other == com.clapi.GetVersionResponse.getDefaultInstance()) return this;
      if (other.hasVersion()) {
        bitField0_ |= 0x00000001;
        version_ = other.version_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasVersion()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.clapi.GetVersionResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.clapi.GetVersionResponse) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object version_ = "";
    /**
     * <code>required string version = 1;</code>
     */
    public boolean hasVersion() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string version = 1;</code>
     */
    public java.lang.String getVersion() {
      java.lang.Object ref = version_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          version_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string version = 1;</code>
     */
    public com.google.protobuf.ByteString
        getVersionBytes() {
      java.lang.Object ref = version_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        version_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string version = 1;</code>
     */
    public Builder setVersion(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      version_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string version = 1;</code>
     */
    public Builder clearVersion() {
      bitField0_ = (bitField0_ & ~0x00000001);
      version_ = getDefaultInstance().getVersion();
      onChanged();
      return this;
    }
    /**
     * <code>required string version = 1;</code>
     */
    public Builder setVersionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      version_ = value;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:clapi.GetVersionResponse)
  }

  // @@protoc_insertion_point(class_scope:clapi.GetVersionResponse)
  private static final com.clapi.GetVersionResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.clapi.GetVersionResponse();
  }

  public static com.clapi.GetVersionResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GetVersionResponse>
      PARSER = new com.google.protobuf.AbstractParser<GetVersionResponse>() {
    public GetVersionResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new GetVersionResponse(input, extensionRegistry);
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

  public static com.google.protobuf.Parser<GetVersionResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetVersionResponse> getParserForType() {
    return PARSER;
  }

  public com.clapi.GetVersionResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

