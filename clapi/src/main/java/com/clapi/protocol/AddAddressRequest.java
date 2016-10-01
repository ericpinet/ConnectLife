// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: clapi.proto

package com.clapi.protocol;

/**
 * Protobuf type {@code clapi.AddAddressRequest}
 *
 * <pre>
 * The request/response for addAddress
 * </pre>
 */
public  final class AddAddressRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:clapi.AddAddressRequest)
    AddAddressRequestOrBuilder {
  // Use AddAddressRequest.newBuilder() to construct.
  private AddAddressRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private AddAddressRequest() {
    uidPerson_ = "";
    street_ = "";
    city_ = "";
    region_ = "";
    zipcode_ = "";
    country_ = "";
    type_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AddAddressRequest(
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
            street_ = bs;
            break;
          }
          case 34: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            city_ = bs;
            break;
          }
          case 42: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000008;
            region_ = bs;
            break;
          }
          case 50: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000010;
            zipcode_ = bs;
            break;
          }
          case 58: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000020;
            country_ = bs;
            break;
          }
          case 64: {
            int rawValue = input.readEnum();
            com.clapi.protocol.AddressType value = com.clapi.protocol.AddressType.valueOf(rawValue);
            if (value == null) {
              unknownFields.mergeVarintField(8, rawValue);
            } else {
              bitField0_ |= 0x00000040;
              type_ = rawValue;
            }
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
    return com.clapi.protocol.CLApiProtos.internal_static_clapi_AddAddressRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.clapi.protocol.CLApiProtos.internal_static_clapi_AddAddressRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.clapi.protocol.AddAddressRequest.class, com.clapi.protocol.AddAddressRequest.Builder.class);
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

  public static final int STREET_FIELD_NUMBER = 2;
  private volatile java.lang.Object street_;
  /**
   * <code>required string street = 2;</code>
   */
  public boolean hasStreet() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required string street = 2;</code>
   */
  public java.lang.String getStreet() {
    java.lang.Object ref = street_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        street_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string street = 2;</code>
   */
  public com.google.protobuf.ByteString
      getStreetBytes() {
    java.lang.Object ref = street_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      street_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CITY_FIELD_NUMBER = 4;
  private volatile java.lang.Object city_;
  /**
   * <code>optional string city = 4;</code>
   */
  public boolean hasCity() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>optional string city = 4;</code>
   */
  public java.lang.String getCity() {
    java.lang.Object ref = city_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        city_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string city = 4;</code>
   */
  public com.google.protobuf.ByteString
      getCityBytes() {
    java.lang.Object ref = city_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      city_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int REGION_FIELD_NUMBER = 5;
  private volatile java.lang.Object region_;
  /**
   * <code>optional string region = 5;</code>
   */
  public boolean hasRegion() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>optional string region = 5;</code>
   */
  public java.lang.String getRegion() {
    java.lang.Object ref = region_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        region_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string region = 5;</code>
   */
  public com.google.protobuf.ByteString
      getRegionBytes() {
    java.lang.Object ref = region_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      region_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ZIPCODE_FIELD_NUMBER = 6;
  private volatile java.lang.Object zipcode_;
  /**
   * <code>optional string zipcode = 6;</code>
   */
  public boolean hasZipcode() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <code>optional string zipcode = 6;</code>
   */
  public java.lang.String getZipcode() {
    java.lang.Object ref = zipcode_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        zipcode_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string zipcode = 6;</code>
   */
  public com.google.protobuf.ByteString
      getZipcodeBytes() {
    java.lang.Object ref = zipcode_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      zipcode_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int COUNTRY_FIELD_NUMBER = 7;
  private volatile java.lang.Object country_;
  /**
   * <code>optional string country = 7;</code>
   */
  public boolean hasCountry() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <code>optional string country = 7;</code>
   */
  public java.lang.String getCountry() {
    java.lang.Object ref = country_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        country_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string country = 7;</code>
   */
  public com.google.protobuf.ByteString
      getCountryBytes() {
    java.lang.Object ref = country_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      country_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TYPE_FIELD_NUMBER = 8;
  private int type_;
  /**
   * <code>required .clapi.AddressType type = 8;</code>
   */
  public boolean hasType() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <code>required .clapi.AddressType type = 8;</code>
   */
  public com.clapi.protocol.AddressType getType() {
    com.clapi.protocol.AddressType result = com.clapi.protocol.AddressType.valueOf(type_);
    return result == null ? com.clapi.protocol.AddressType.ADDRESS_HOME : result;
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
    if (!hasStreet()) {
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
      com.google.protobuf.GeneratedMessage.writeString(output, 2, street_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 4, city_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 5, region_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 6, zipcode_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 7, country_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeEnum(8, type_);
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
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, street_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(4, city_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(5, region_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(6, zipcode_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(7, country_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(8, type_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.clapi.protocol.AddAddressRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.clapi.protocol.AddAddressRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.clapi.protocol.AddAddressRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.clapi.protocol.AddAddressRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.clapi.protocol.AddAddressRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.clapi.protocol.AddAddressRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.clapi.protocol.AddAddressRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.clapi.protocol.AddAddressRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.clapi.protocol.AddAddressRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.clapi.protocol.AddAddressRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.clapi.protocol.AddAddressRequest prototype) {
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
   * Protobuf type {@code clapi.AddAddressRequest}
   *
   * <pre>
   * The request/response for addAddress
   * </pre>
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:clapi.AddAddressRequest)
      com.clapi.protocol.AddAddressRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.clapi.protocol.CLApiProtos.internal_static_clapi_AddAddressRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.clapi.protocol.CLApiProtos.internal_static_clapi_AddAddressRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.clapi.protocol.AddAddressRequest.class, com.clapi.protocol.AddAddressRequest.Builder.class);
    }

    // Construct using com.clapi.protocol.AddAddressRequest.newBuilder()
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
      street_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      city_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      region_ = "";
      bitField0_ = (bitField0_ & ~0x00000008);
      zipcode_ = "";
      bitField0_ = (bitField0_ & ~0x00000010);
      country_ = "";
      bitField0_ = (bitField0_ & ~0x00000020);
      type_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.clapi.protocol.CLApiProtos.internal_static_clapi_AddAddressRequest_descriptor;
    }

    public com.clapi.protocol.AddAddressRequest getDefaultInstanceForType() {
      return com.clapi.protocol.AddAddressRequest.getDefaultInstance();
    }

    public com.clapi.protocol.AddAddressRequest build() {
      com.clapi.protocol.AddAddressRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.clapi.protocol.AddAddressRequest buildPartial() {
      com.clapi.protocol.AddAddressRequest result = new com.clapi.protocol.AddAddressRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.uidPerson_ = uidPerson_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.street_ = street_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.city_ = city_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.region_ = region_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.zipcode_ = zipcode_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.country_ = country_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.type_ = type_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.clapi.protocol.AddAddressRequest) {
        return mergeFrom((com.clapi.protocol.AddAddressRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.clapi.protocol.AddAddressRequest other) {
      if (other == com.clapi.protocol.AddAddressRequest.getDefaultInstance()) return this;
      if (other.hasUidPerson()) {
        bitField0_ |= 0x00000001;
        uidPerson_ = other.uidPerson_;
        onChanged();
      }
      if (other.hasStreet()) {
        bitField0_ |= 0x00000002;
        street_ = other.street_;
        onChanged();
      }
      if (other.hasCity()) {
        bitField0_ |= 0x00000004;
        city_ = other.city_;
        onChanged();
      }
      if (other.hasRegion()) {
        bitField0_ |= 0x00000008;
        region_ = other.region_;
        onChanged();
      }
      if (other.hasZipcode()) {
        bitField0_ |= 0x00000010;
        zipcode_ = other.zipcode_;
        onChanged();
      }
      if (other.hasCountry()) {
        bitField0_ |= 0x00000020;
        country_ = other.country_;
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
      if (!hasStreet()) {
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
      com.clapi.protocol.AddAddressRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.clapi.protocol.AddAddressRequest) e.getUnfinishedMessage();
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

    private java.lang.Object street_ = "";
    /**
     * <code>required string street = 2;</code>
     */
    public boolean hasStreet() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string street = 2;</code>
     */
    public java.lang.String getStreet() {
      java.lang.Object ref = street_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          street_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string street = 2;</code>
     */
    public com.google.protobuf.ByteString
        getStreetBytes() {
      java.lang.Object ref = street_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        street_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string street = 2;</code>
     */
    public Builder setStreet(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      street_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string street = 2;</code>
     */
    public Builder clearStreet() {
      bitField0_ = (bitField0_ & ~0x00000002);
      street_ = getDefaultInstance().getStreet();
      onChanged();
      return this;
    }
    /**
     * <code>required string street = 2;</code>
     */
    public Builder setStreetBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      street_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object city_ = "";
    /**
     * <code>optional string city = 4;</code>
     */
    public boolean hasCity() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string city = 4;</code>
     */
    public java.lang.String getCity() {
      java.lang.Object ref = city_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          city_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string city = 4;</code>
     */
    public com.google.protobuf.ByteString
        getCityBytes() {
      java.lang.Object ref = city_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        city_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string city = 4;</code>
     */
    public Builder setCity(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      city_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string city = 4;</code>
     */
    public Builder clearCity() {
      bitField0_ = (bitField0_ & ~0x00000004);
      city_ = getDefaultInstance().getCity();
      onChanged();
      return this;
    }
    /**
     * <code>optional string city = 4;</code>
     */
    public Builder setCityBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      city_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object region_ = "";
    /**
     * <code>optional string region = 5;</code>
     */
    public boolean hasRegion() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional string region = 5;</code>
     */
    public java.lang.String getRegion() {
      java.lang.Object ref = region_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          region_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string region = 5;</code>
     */
    public com.google.protobuf.ByteString
        getRegionBytes() {
      java.lang.Object ref = region_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        region_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string region = 5;</code>
     */
    public Builder setRegion(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      region_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string region = 5;</code>
     */
    public Builder clearRegion() {
      bitField0_ = (bitField0_ & ~0x00000008);
      region_ = getDefaultInstance().getRegion();
      onChanged();
      return this;
    }
    /**
     * <code>optional string region = 5;</code>
     */
    public Builder setRegionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      region_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object zipcode_ = "";
    /**
     * <code>optional string zipcode = 6;</code>
     */
    public boolean hasZipcode() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional string zipcode = 6;</code>
     */
    public java.lang.String getZipcode() {
      java.lang.Object ref = zipcode_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          zipcode_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string zipcode = 6;</code>
     */
    public com.google.protobuf.ByteString
        getZipcodeBytes() {
      java.lang.Object ref = zipcode_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        zipcode_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string zipcode = 6;</code>
     */
    public Builder setZipcode(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      zipcode_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string zipcode = 6;</code>
     */
    public Builder clearZipcode() {
      bitField0_ = (bitField0_ & ~0x00000010);
      zipcode_ = getDefaultInstance().getZipcode();
      onChanged();
      return this;
    }
    /**
     * <code>optional string zipcode = 6;</code>
     */
    public Builder setZipcodeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      zipcode_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object country_ = "";
    /**
     * <code>optional string country = 7;</code>
     */
    public boolean hasCountry() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>optional string country = 7;</code>
     */
    public java.lang.String getCountry() {
      java.lang.Object ref = country_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          country_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string country = 7;</code>
     */
    public com.google.protobuf.ByteString
        getCountryBytes() {
      java.lang.Object ref = country_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        country_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string country = 7;</code>
     */
    public Builder setCountry(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
      country_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string country = 7;</code>
     */
    public Builder clearCountry() {
      bitField0_ = (bitField0_ & ~0x00000020);
      country_ = getDefaultInstance().getCountry();
      onChanged();
      return this;
    }
    /**
     * <code>optional string country = 7;</code>
     */
    public Builder setCountryBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
      country_ = value;
      onChanged();
      return this;
    }

    private int type_ = 0;
    /**
     * <code>required .clapi.AddressType type = 8;</code>
     */
    public boolean hasType() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <code>required .clapi.AddressType type = 8;</code>
     */
    public com.clapi.protocol.AddressType getType() {
      com.clapi.protocol.AddressType result = com.clapi.protocol.AddressType.valueOf(type_);
      return result == null ? com.clapi.protocol.AddressType.ADDRESS_HOME : result;
    }
    /**
     * <code>required .clapi.AddressType type = 8;</code>
     */
    public Builder setType(com.clapi.protocol.AddressType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000040;
      type_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>required .clapi.AddressType type = 8;</code>
     */
    public Builder clearType() {
      bitField0_ = (bitField0_ & ~0x00000040);
      type_ = 0;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:clapi.AddAddressRequest)
  }

  // @@protoc_insertion_point(class_scope:clapi.AddAddressRequest)
  private static final com.clapi.protocol.AddAddressRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.clapi.protocol.AddAddressRequest();
  }

  public static com.clapi.protocol.AddAddressRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AddAddressRequest>
      PARSER = new com.google.protobuf.AbstractParser<AddAddressRequest>() {
    public AddAddressRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new AddAddressRequest(input, extensionRegistry);
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

  public static com.google.protobuf.Parser<AddAddressRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AddAddressRequest> getParserForType() {
    return PARSER;
  }

  public com.clapi.protocol.AddAddressRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

