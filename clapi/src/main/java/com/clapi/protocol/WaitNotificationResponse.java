// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: clapi.proto

package com.clapi.protocol;

/**
 * Protobuf type {@code clapi.WaitNotificationResponse}
 */
public  final class WaitNotificationResponse extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:clapi.WaitNotificationResponse)
    WaitNotificationResponseOrBuilder {
  // Use WaitNotificationResponse.newBuilder() to construct.
  private WaitNotificationResponse(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private WaitNotificationResponse() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private WaitNotificationResponse(
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
            com.clapi.protocol.Notification.Builder subBuilder = null;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
              subBuilder = notification_.toBuilder();
            }
            notification_ = input.readMessage(com.clapi.protocol.Notification.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(notification_);
              notification_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000001;
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
    return com.clapi.protocol.CLApiProtos.internal_static_clapi_WaitNotificationResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.clapi.protocol.CLApiProtos.internal_static_clapi_WaitNotificationResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.clapi.protocol.WaitNotificationResponse.class, com.clapi.protocol.WaitNotificationResponse.Builder.class);
  }

  private int bitField0_;
  public static final int NOTIFICATION_FIELD_NUMBER = 1;
  private com.clapi.protocol.Notification notification_;
  /**
   * <code>required .clapi.Notification notification = 1;</code>
   */
  public boolean hasNotification() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required .clapi.Notification notification = 1;</code>
   */
  public com.clapi.protocol.Notification getNotification() {
    return notification_ == null ? com.clapi.protocol.Notification.getDefaultInstance() : notification_;
  }
  /**
   * <code>required .clapi.Notification notification = 1;</code>
   */
  public com.clapi.protocol.NotificationOrBuilder getNotificationOrBuilder() {
    return notification_ == null ? com.clapi.protocol.Notification.getDefaultInstance() : notification_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasNotification()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getNotification().isInitialized()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeMessage(1, getNotification());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getNotification());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.clapi.protocol.WaitNotificationResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.clapi.protocol.WaitNotificationResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.clapi.protocol.WaitNotificationResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.clapi.protocol.WaitNotificationResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.clapi.protocol.WaitNotificationResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.clapi.protocol.WaitNotificationResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.clapi.protocol.WaitNotificationResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.clapi.protocol.WaitNotificationResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.clapi.protocol.WaitNotificationResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.clapi.protocol.WaitNotificationResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.clapi.protocol.WaitNotificationResponse prototype) {
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
   * Protobuf type {@code clapi.WaitNotificationResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:clapi.WaitNotificationResponse)
      com.clapi.protocol.WaitNotificationResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.clapi.protocol.CLApiProtos.internal_static_clapi_WaitNotificationResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.clapi.protocol.CLApiProtos.internal_static_clapi_WaitNotificationResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.clapi.protocol.WaitNotificationResponse.class, com.clapi.protocol.WaitNotificationResponse.Builder.class);
    }

    // Construct using com.clapi.protocol.WaitNotificationResponse.newBuilder()
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
        getNotificationFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (notificationBuilder_ == null) {
        notification_ = null;
      } else {
        notificationBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.clapi.protocol.CLApiProtos.internal_static_clapi_WaitNotificationResponse_descriptor;
    }

    public com.clapi.protocol.WaitNotificationResponse getDefaultInstanceForType() {
      return com.clapi.protocol.WaitNotificationResponse.getDefaultInstance();
    }

    public com.clapi.protocol.WaitNotificationResponse build() {
      com.clapi.protocol.WaitNotificationResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.clapi.protocol.WaitNotificationResponse buildPartial() {
      com.clapi.protocol.WaitNotificationResponse result = new com.clapi.protocol.WaitNotificationResponse(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      if (notificationBuilder_ == null) {
        result.notification_ = notification_;
      } else {
        result.notification_ = notificationBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.clapi.protocol.WaitNotificationResponse) {
        return mergeFrom((com.clapi.protocol.WaitNotificationResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.clapi.protocol.WaitNotificationResponse other) {
      if (other == com.clapi.protocol.WaitNotificationResponse.getDefaultInstance()) return this;
      if (other.hasNotification()) {
        mergeNotification(other.getNotification());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasNotification()) {
        return false;
      }
      if (!getNotification().isInitialized()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.clapi.protocol.WaitNotificationResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.clapi.protocol.WaitNotificationResponse) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.clapi.protocol.Notification notification_ = null;
    private com.google.protobuf.SingleFieldBuilder<
        com.clapi.protocol.Notification, com.clapi.protocol.Notification.Builder, com.clapi.protocol.NotificationOrBuilder> notificationBuilder_;
    /**
     * <code>required .clapi.Notification notification = 1;</code>
     */
    public boolean hasNotification() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .clapi.Notification notification = 1;</code>
     */
    public com.clapi.protocol.Notification getNotification() {
      if (notificationBuilder_ == null) {
        return notification_ == null ? com.clapi.protocol.Notification.getDefaultInstance() : notification_;
      } else {
        return notificationBuilder_.getMessage();
      }
    }
    /**
     * <code>required .clapi.Notification notification = 1;</code>
     */
    public Builder setNotification(com.clapi.protocol.Notification value) {
      if (notificationBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        notification_ = value;
        onChanged();
      } else {
        notificationBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>required .clapi.Notification notification = 1;</code>
     */
    public Builder setNotification(
        com.clapi.protocol.Notification.Builder builderForValue) {
      if (notificationBuilder_ == null) {
        notification_ = builderForValue.build();
        onChanged();
      } else {
        notificationBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>required .clapi.Notification notification = 1;</code>
     */
    public Builder mergeNotification(com.clapi.protocol.Notification value) {
      if (notificationBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001) &&
            notification_ != null &&
            notification_ != com.clapi.protocol.Notification.getDefaultInstance()) {
          notification_ =
            com.clapi.protocol.Notification.newBuilder(notification_).mergeFrom(value).buildPartial();
        } else {
          notification_ = value;
        }
        onChanged();
      } else {
        notificationBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>required .clapi.Notification notification = 1;</code>
     */
    public Builder clearNotification() {
      if (notificationBuilder_ == null) {
        notification_ = null;
        onChanged();
      } else {
        notificationBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }
    /**
     * <code>required .clapi.Notification notification = 1;</code>
     */
    public com.clapi.protocol.Notification.Builder getNotificationBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getNotificationFieldBuilder().getBuilder();
    }
    /**
     * <code>required .clapi.Notification notification = 1;</code>
     */
    public com.clapi.protocol.NotificationOrBuilder getNotificationOrBuilder() {
      if (notificationBuilder_ != null) {
        return notificationBuilder_.getMessageOrBuilder();
      } else {
        return notification_ == null ?
            com.clapi.protocol.Notification.getDefaultInstance() : notification_;
      }
    }
    /**
     * <code>required .clapi.Notification notification = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilder<
        com.clapi.protocol.Notification, com.clapi.protocol.Notification.Builder, com.clapi.protocol.NotificationOrBuilder> 
        getNotificationFieldBuilder() {
      if (notificationBuilder_ == null) {
        notificationBuilder_ = new com.google.protobuf.SingleFieldBuilder<
            com.clapi.protocol.Notification, com.clapi.protocol.Notification.Builder, com.clapi.protocol.NotificationOrBuilder>(
                getNotification(),
                getParentForChildren(),
                isClean());
        notification_ = null;
      }
      return notificationBuilder_;
    }

    // @@protoc_insertion_point(builder_scope:clapi.WaitNotificationResponse)
  }

  // @@protoc_insertion_point(class_scope:clapi.WaitNotificationResponse)
  private static final com.clapi.protocol.WaitNotificationResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.clapi.protocol.WaitNotificationResponse();
  }

  public static com.clapi.protocol.WaitNotificationResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<WaitNotificationResponse>
      PARSER = new com.google.protobuf.AbstractParser<WaitNotificationResponse>() {
    public WaitNotificationResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new WaitNotificationResponse(input, extensionRegistry);
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

  public static com.google.protobuf.Parser<WaitNotificationResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<WaitNotificationResponse> getParserForType() {
    return PARSER;
  }

  public com.clapi.protocol.WaitNotificationResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
