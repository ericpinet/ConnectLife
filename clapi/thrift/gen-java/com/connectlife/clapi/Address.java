/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.connectlife.clapi;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
/**
 * Address
 */
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-10-18")
public class Address implements org.apache.thrift.TBase<Address, Address._Fields>, java.io.Serializable, Cloneable, Comparable<Address> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Address");

  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField STREET_FIELD_DESC = new org.apache.thrift.protocol.TField("street", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField CITY_FIELD_DESC = new org.apache.thrift.protocol.TField("city", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField REGION_FIELD_DESC = new org.apache.thrift.protocol.TField("region", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField ZIPCODE_FIELD_DESC = new org.apache.thrift.protocol.TField("zipcode", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField COUNTRY_FIELD_DESC = new org.apache.thrift.protocol.TField("country", org.apache.thrift.protocol.TType.STRING, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new AddressStandardSchemeFactory());
    schemes.put(TupleScheme.class, new AddressTupleSchemeFactory());
  }

  /**
   * 
   * @see AddressType
   */
  public AddressType type; // required
  public String street; // required
  public String city; // optional
  public String region; // optional
  public String zipcode; // optional
  public String country; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see AddressType
     */
    TYPE((short)1, "type"),
    STREET((short)2, "street"),
    CITY((short)3, "city"),
    REGION((short)4, "region"),
    ZIPCODE((short)5, "zipcode"),
    COUNTRY((short)6, "country");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // TYPE
          return TYPE;
        case 2: // STREET
          return STREET;
        case 3: // CITY
          return CITY;
        case 4: // REGION
          return REGION;
        case 5: // ZIPCODE
          return ZIPCODE;
        case 6: // COUNTRY
          return COUNTRY;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.CITY,_Fields.REGION,_Fields.ZIPCODE,_Fields.COUNTRY};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, AddressType.class)));
    tmpMap.put(_Fields.STREET, new org.apache.thrift.meta_data.FieldMetaData("street", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CITY, new org.apache.thrift.meta_data.FieldMetaData("city", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.REGION, new org.apache.thrift.meta_data.FieldMetaData("region", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ZIPCODE, new org.apache.thrift.meta_data.FieldMetaData("zipcode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.COUNTRY, new org.apache.thrift.meta_data.FieldMetaData("country", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Address.class, metaDataMap);
  }

  public Address() {
  }

  public Address(
    AddressType type,
    String street)
  {
    this();
    this.type = type;
    this.street = street;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Address(Address other) {
    if (other.isSetType()) {
      this.type = other.type;
    }
    if (other.isSetStreet()) {
      this.street = other.street;
    }
    if (other.isSetCity()) {
      this.city = other.city;
    }
    if (other.isSetRegion()) {
      this.region = other.region;
    }
    if (other.isSetZipcode()) {
      this.zipcode = other.zipcode;
    }
    if (other.isSetCountry()) {
      this.country = other.country;
    }
  }

  public Address deepCopy() {
    return new Address(this);
  }

  @Override
  public void clear() {
    this.type = null;
    this.street = null;
    this.city = null;
    this.region = null;
    this.zipcode = null;
    this.country = null;
  }

  /**
   * 
   * @see AddressType
   */
  public AddressType getType() {
    return this.type;
  }

  /**
   * 
   * @see AddressType
   */
  public Address setType(AddressType type) {
    this.type = type;
    return this;
  }

  public void unsetType() {
    this.type = null;
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  public String getStreet() {
    return this.street;
  }

  public Address setStreet(String street) {
    this.street = street;
    return this;
  }

  public void unsetStreet() {
    this.street = null;
  }

  /** Returns true if field street is set (has been assigned a value) and false otherwise */
  public boolean isSetStreet() {
    return this.street != null;
  }

  public void setStreetIsSet(boolean value) {
    if (!value) {
      this.street = null;
    }
  }

  public String getCity() {
    return this.city;
  }

  public Address setCity(String city) {
    this.city = city;
    return this;
  }

  public void unsetCity() {
    this.city = null;
  }

  /** Returns true if field city is set (has been assigned a value) and false otherwise */
  public boolean isSetCity() {
    return this.city != null;
  }

  public void setCityIsSet(boolean value) {
    if (!value) {
      this.city = null;
    }
  }

  public String getRegion() {
    return this.region;
  }

  public Address setRegion(String region) {
    this.region = region;
    return this;
  }

  public void unsetRegion() {
    this.region = null;
  }

  /** Returns true if field region is set (has been assigned a value) and false otherwise */
  public boolean isSetRegion() {
    return this.region != null;
  }

  public void setRegionIsSet(boolean value) {
    if (!value) {
      this.region = null;
    }
  }

  public String getZipcode() {
    return this.zipcode;
  }

  public Address setZipcode(String zipcode) {
    this.zipcode = zipcode;
    return this;
  }

  public void unsetZipcode() {
    this.zipcode = null;
  }

  /** Returns true if field zipcode is set (has been assigned a value) and false otherwise */
  public boolean isSetZipcode() {
    return this.zipcode != null;
  }

  public void setZipcodeIsSet(boolean value) {
    if (!value) {
      this.zipcode = null;
    }
  }

  public String getCountry() {
    return this.country;
  }

  public Address setCountry(String country) {
    this.country = country;
    return this;
  }

  public void unsetCountry() {
    this.country = null;
  }

  /** Returns true if field country is set (has been assigned a value) and false otherwise */
  public boolean isSetCountry() {
    return this.country != null;
  }

  public void setCountryIsSet(boolean value) {
    if (!value) {
      this.country = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((AddressType)value);
      }
      break;

    case STREET:
      if (value == null) {
        unsetStreet();
      } else {
        setStreet((String)value);
      }
      break;

    case CITY:
      if (value == null) {
        unsetCity();
      } else {
        setCity((String)value);
      }
      break;

    case REGION:
      if (value == null) {
        unsetRegion();
      } else {
        setRegion((String)value);
      }
      break;

    case ZIPCODE:
      if (value == null) {
        unsetZipcode();
      } else {
        setZipcode((String)value);
      }
      break;

    case COUNTRY:
      if (value == null) {
        unsetCountry();
      } else {
        setCountry((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TYPE:
      return getType();

    case STREET:
      return getStreet();

    case CITY:
      return getCity();

    case REGION:
      return getRegion();

    case ZIPCODE:
      return getZipcode();

    case COUNTRY:
      return getCountry();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TYPE:
      return isSetType();
    case STREET:
      return isSetStreet();
    case CITY:
      return isSetCity();
    case REGION:
      return isSetRegion();
    case ZIPCODE:
      return isSetZipcode();
    case COUNTRY:
      return isSetCountry();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Address)
      return this.equals((Address)that);
    return false;
  }

  public boolean equals(Address that) {
    if (that == null)
      return false;

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    boolean this_present_street = true && this.isSetStreet();
    boolean that_present_street = true && that.isSetStreet();
    if (this_present_street || that_present_street) {
      if (!(this_present_street && that_present_street))
        return false;
      if (!this.street.equals(that.street))
        return false;
    }

    boolean this_present_city = true && this.isSetCity();
    boolean that_present_city = true && that.isSetCity();
    if (this_present_city || that_present_city) {
      if (!(this_present_city && that_present_city))
        return false;
      if (!this.city.equals(that.city))
        return false;
    }

    boolean this_present_region = true && this.isSetRegion();
    boolean that_present_region = true && that.isSetRegion();
    if (this_present_region || that_present_region) {
      if (!(this_present_region && that_present_region))
        return false;
      if (!this.region.equals(that.region))
        return false;
    }

    boolean this_present_zipcode = true && this.isSetZipcode();
    boolean that_present_zipcode = true && that.isSetZipcode();
    if (this_present_zipcode || that_present_zipcode) {
      if (!(this_present_zipcode && that_present_zipcode))
        return false;
      if (!this.zipcode.equals(that.zipcode))
        return false;
    }

    boolean this_present_country = true && this.isSetCountry();
    boolean that_present_country = true && that.isSetCountry();
    if (this_present_country || that_present_country) {
      if (!(this_present_country && that_present_country))
        return false;
      if (!this.country.equals(that.country))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_type = true && (isSetType());
    list.add(present_type);
    if (present_type)
      list.add(type.getValue());

    boolean present_street = true && (isSetStreet());
    list.add(present_street);
    if (present_street)
      list.add(street);

    boolean present_city = true && (isSetCity());
    list.add(present_city);
    if (present_city)
      list.add(city);

    boolean present_region = true && (isSetRegion());
    list.add(present_region);
    if (present_region)
      list.add(region);

    boolean present_zipcode = true && (isSetZipcode());
    list.add(present_zipcode);
    if (present_zipcode)
      list.add(zipcode);

    boolean present_country = true && (isSetCountry());
    list.add(present_country);
    if (present_country)
      list.add(country);

    return list.hashCode();
  }

  @Override
  public int compareTo(Address other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetType()).compareTo(other.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, other.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStreet()).compareTo(other.isSetStreet());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStreet()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.street, other.street);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCity()).compareTo(other.isSetCity());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCity()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.city, other.city);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRegion()).compareTo(other.isSetRegion());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRegion()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.region, other.region);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetZipcode()).compareTo(other.isSetZipcode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetZipcode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.zipcode, other.zipcode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCountry()).compareTo(other.isSetCountry());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCountry()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.country, other.country);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Address(");
    boolean first = true;

    sb.append("type:");
    if (this.type == null) {
      sb.append("null");
    } else {
      sb.append(this.type);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("street:");
    if (this.street == null) {
      sb.append("null");
    } else {
      sb.append(this.street);
    }
    first = false;
    if (isSetCity()) {
      if (!first) sb.append(", ");
      sb.append("city:");
      if (this.city == null) {
        sb.append("null");
      } else {
        sb.append(this.city);
      }
      first = false;
    }
    if (isSetRegion()) {
      if (!first) sb.append(", ");
      sb.append("region:");
      if (this.region == null) {
        sb.append("null");
      } else {
        sb.append(this.region);
      }
      first = false;
    }
    if (isSetZipcode()) {
      if (!first) sb.append(", ");
      sb.append("zipcode:");
      if (this.zipcode == null) {
        sb.append("null");
      } else {
        sb.append(this.zipcode);
      }
      first = false;
    }
    if (isSetCountry()) {
      if (!first) sb.append(", ");
      sb.append("country:");
      if (this.country == null) {
        sb.append("null");
      } else {
        sb.append(this.country);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (type == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'type' was not present! Struct: " + toString());
    }
    if (street == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'street' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class AddressStandardSchemeFactory implements SchemeFactory {
    public AddressStandardScheme getScheme() {
      return new AddressStandardScheme();
    }
  }

  private static class AddressStandardScheme extends StandardScheme<Address> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Address struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.type = com.connectlife.clapi.AddressType.findByValue(iprot.readI32());
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // STREET
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.street = iprot.readString();
              struct.setStreetIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CITY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.city = iprot.readString();
              struct.setCityIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // REGION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.region = iprot.readString();
              struct.setRegionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // ZIPCODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.zipcode = iprot.readString();
              struct.setZipcodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // COUNTRY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.country = iprot.readString();
              struct.setCountryIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Address struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.type != null) {
        oprot.writeFieldBegin(TYPE_FIELD_DESC);
        oprot.writeI32(struct.type.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.street != null) {
        oprot.writeFieldBegin(STREET_FIELD_DESC);
        oprot.writeString(struct.street);
        oprot.writeFieldEnd();
      }
      if (struct.city != null) {
        if (struct.isSetCity()) {
          oprot.writeFieldBegin(CITY_FIELD_DESC);
          oprot.writeString(struct.city);
          oprot.writeFieldEnd();
        }
      }
      if (struct.region != null) {
        if (struct.isSetRegion()) {
          oprot.writeFieldBegin(REGION_FIELD_DESC);
          oprot.writeString(struct.region);
          oprot.writeFieldEnd();
        }
      }
      if (struct.zipcode != null) {
        if (struct.isSetZipcode()) {
          oprot.writeFieldBegin(ZIPCODE_FIELD_DESC);
          oprot.writeString(struct.zipcode);
          oprot.writeFieldEnd();
        }
      }
      if (struct.country != null) {
        if (struct.isSetCountry()) {
          oprot.writeFieldBegin(COUNTRY_FIELD_DESC);
          oprot.writeString(struct.country);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AddressTupleSchemeFactory implements SchemeFactory {
    public AddressTupleScheme getScheme() {
      return new AddressTupleScheme();
    }
  }

  private static class AddressTupleScheme extends TupleScheme<Address> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Address struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.type.getValue());
      oprot.writeString(struct.street);
      BitSet optionals = new BitSet();
      if (struct.isSetCity()) {
        optionals.set(0);
      }
      if (struct.isSetRegion()) {
        optionals.set(1);
      }
      if (struct.isSetZipcode()) {
        optionals.set(2);
      }
      if (struct.isSetCountry()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetCity()) {
        oprot.writeString(struct.city);
      }
      if (struct.isSetRegion()) {
        oprot.writeString(struct.region);
      }
      if (struct.isSetZipcode()) {
        oprot.writeString(struct.zipcode);
      }
      if (struct.isSetCountry()) {
        oprot.writeString(struct.country);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Address struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.type = com.connectlife.clapi.AddressType.findByValue(iprot.readI32());
      struct.setTypeIsSet(true);
      struct.street = iprot.readString();
      struct.setStreetIsSet(true);
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.city = iprot.readString();
        struct.setCityIsSet(true);
      }
      if (incoming.get(1)) {
        struct.region = iprot.readString();
        struct.setRegionIsSet(true);
      }
      if (incoming.get(2)) {
        struct.zipcode = iprot.readString();
        struct.setZipcodeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.country = iprot.readString();
        struct.setCountryIsSet(true);
      }
    }
  }

}

