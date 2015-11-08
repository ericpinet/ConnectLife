// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: clapi.proto

package com.clapi;

public final class CLApiProtos {
  private CLApiProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_GetVersionRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_GetVersionRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_GetVersionResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_GetVersionResponse_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_WaitNotificationRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_WaitNotificationRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_WaitNotificationResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_WaitNotificationResponse_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_AddPersonRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_AddPersonRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_AddPersonResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_AddPersonResponse_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_UpdatePersonRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_UpdatePersonRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_UpdatePersonResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_UpdatePersonResponse_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_DeletePersonRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_DeletePersonRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_DeletePersonResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_DeletePersonResponse_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_DeleteAllPersonRequest_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_DeleteAllPersonRequest_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_DeleteAllPersonResponse_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_DeleteAllPersonResponse_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_Email_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_Email_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_Phone_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_Phone_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_Address_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_Address_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_Person_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_Person_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_Characteristic_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_Characteristic_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_Service_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_Service_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_Accessory_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_Accessory_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_Room_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_Room_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_Zone_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_Zone_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_Home_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_Home_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_Data_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_Data_fieldAccessorTable;
  static com.google.protobuf.Descriptors.Descriptor
    internal_static_clapi_Notification_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_clapi_Notification_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013clapi.proto\022\005clapi\"\023\n\021GetVersionReques" +
      "t\"%\n\022GetVersionResponse\022\017\n\007version\030\001 \002(\t" +
      "\"\031\n\027WaitNotificationRequest\"E\n\030WaitNotif" +
      "icationResponse\022)\n\014notification\030\001 \002(\0132\023." +
      "clapi.Notification\"1\n\020AddPersonRequest\022\035" +
      "\n\006person\030\001 \002(\0132\r.clapi.Person\"\023\n\021AddPers" +
      "onResponse\"4\n\023UpdatePersonRequest\022\035\n\006per" +
      "son\030\001 \002(\0132\r.clapi.Person\"\026\n\024UpdatePerson" +
      "Response\"4\n\023DeletePersonRequest\022\035\n\006perso" +
      "n\030\001 \002(\0132\r.clapi.Person\"\026\n\024DeletePersonRe",
      "sponse\"\030\n\026DeleteAllPersonRequest\"\031\n\027Dele" +
      "teAllPersonResponse\"v\n\005Email\022\r\n\005email\030\001 " +
      "\002(\t\022.\n\004type\030\002 \001(\0162\026.clapi.Email.EmailTyp" +
      "e:\010PERSONAL\".\n\tEmailType\022\014\n\010PERSONAL\020\000\022\010" +
      "\n\004WORK\020\001\022\t\n\005OTHER\020\002\"v\n\005Phone\022\r\n\005phone\030\001 " +
      "\002(\t\022.\n\004type\030\002 \001(\0162\026.clapi.Phone.PhoneTyp" +
      "e:\010PERSONAL\".\n\tPhoneType\022\014\n\010PERSONAL\020\000\022\010" +
      "\n\004WORK\020\001\022\t\n\005OTHER\020\002\"\277\001\n\007Address\0222\n\004type\030" +
      "\001 \001(\0162\032.clapi.Address.AddressType:\010PERSO" +
      "NAL\022\016\n\006street\030\002 \002(\t\022\014\n\004city\030\003 \002(\t\022\016\n\006reg",
      "ion\030\004 \002(\t\022\017\n\007zipcode\030\005 \002(\t\022\017\n\007country\030\006 " +
      "\002(\t\"0\n\013AddressType\022\014\n\010PERSONAL\020\000\022\010\n\004WORK" +
      "\020\001\022\t\n\005OTHER\020\002\"\253\001\n\006Person\022\013\n\003uid\030\001 \002(\t\022\021\n" +
      "\tfirstname\030\002 \002(\t\022\020\n\010lastname\030\003 \002(\t\022\034\n\006em" +
      "ails\030\004 \003(\0132\014.clapi.Email\022\034\n\006phones\030\005 \003(\013" +
      "2\014.clapi.Phone\022!\n\taddresses\030\006 \003(\0132\016.clap" +
      "i.Address\022\020\n\010imageurl\030\007 \001(\t\"\300\003\n\016Characte" +
      "ristic\022\013\n\003uid\030\001 \002(\t\022<\n\004mode\030\002 \002(\0162..clap" +
      "i.Characteristic.CharacteristicAccessMod" +
      "e\0226\n\004type\030\003 \002(\0162(.clapi.Characteristic.C",
      "haracteristicType\022<\n\005event\030\004 \002(\0162-.clapi" +
      ".Characteristic.CharacteristicEventType\"" +
      "I\n\030CharacteristicAccessMode\022\r\n\tREAD_ONLY" +
      "\020\001\022\016\n\nWRITE_ONLY\020\002\022\016\n\nREAD_WRITE\020\003\"n\n\022Ch" +
      "aracteristicType\022\013\n\007BOOLEAN\020\001\022\010\n\004ENUM\020\002\022" +
      "\t\n\005FLOAT\020\003\022\013\n\007INTEGER\020\004\022\021\n\rSTATIC_STRING" +
      "\020\005\022\026\n\022WRITE_ONLY_BOOLEAN\020\006\"2\n\027Characteri" +
      "sticEventType\022\014\n\010NO_EVENT\020\001\022\t\n\005EVENT\020\002\"F" +
      "\n\007Service\022\013\n\003uid\030\001 \002(\t\022.\n\017characteristic" +
      "s\030\002 \003(\0132\025.clapi.Characteristic\"\337\002\n\tAcces",
      "sory\022\013\n\003uid\030\001 \002(\t\022\r\n\005label\030\002 \002(\t\022\024\n\014manu" +
      "facturer\030\003 \002(\t\022\r\n\005model\030\004 \002(\t\022\024\n\014serialn" +
      "umber\030\005 \002(\t\022 \n\010services\030\006 \003(\0132\016.clapi.Se" +
      "rvice\022\020\n\010imageurl\030\007 \001(\t\"\306\001\n\rAccessoryTyp" +
      "e\022\t\n\005LIGHT\020\001\022\022\n\016LIGHT_DIMMABLE\020\002\022\021\n\rLIGH" +
      "T_COLORED\020\003\022\032\n\026LIGHT_COLORED_DIMMABLE\020\004\022" +
      "\007\n\003FAN\020\005\022\022\n\016AUTOMATIC_DOOR\020\006\022\022\n\016LOCK_MEC" +
      "HANISM\020\007\022\016\n\nTHERMOSTAT\020\010\022\n\n\006SWITCH\020\t\022\021\n\r" +
      "CONTROL_BOARD\020\n\022\007\n\003CAM\020\013\"[\n\004Room\022\013\n\003uid\030" +
      "\001 \002(\t\022\r\n\005label\030\002 \002(\t\022%\n\013accessories\030\003 \003(",
      "\0132\020.clapi.Accessory\022\020\n\010imageurl\030\004 \001(\t\"P\n" +
      "\004Zone\022\013\n\003uid\030\001 \002(\t\022\r\n\005label\030\002 \002(\t\022\032\n\005roo" +
      "ms\030\003 \003(\0132\013.clapi.Room\022\020\n\010imageurl\030\004 \001(\t\"" +
      "P\n\004Home\022\013\n\003uid\030\001 \002(\t\022\r\n\005label\030\002 \002(\t\022\032\n\005z" +
      "ones\030\003 \003(\0132\013.clapi.Zone\022\020\n\010imageurl\030\004 \001(" +
      "\t\"S\n\004Data\022\017\n\007version\030\001 \002(\005\022\036\n\007persons\030\002 " +
      "\003(\0132\r.clapi.Person\022\032\n\005homes\030\003 \003(\0132\013.clap" +
      "i.Home\"\202\001\n\014Notification\0222\n\004type\030\001 \002(\0162$." +
      "clapi.Notification.NotificationType\022\014\n\004d" +
      "ata\030\002 \001(\t\"0\n\020NotificationType\022\017\n\013ENV_UPD",
      "ATED\020\000\022\013\n\007MESSAGE\020\0012\317\003\n\005CLApi\022C\n\ngetVers" +
      "ion\022\030.clapi.GetVersionRequest\032\031.clapi.Ge" +
      "tVersionResponse\"\000\022U\n\020waitNotification\022\036" +
      ".clapi.WaitNotificationRequest\032\037.clapi.W" +
      "aitNotificationResponse\"\000\022@\n\taddPerson\022\027" +
      ".clapi.AddPersonRequest\032\030.clapi.AddPerso" +
      "nResponse\"\000\022I\n\014updatePerson\022\032.clapi.Upda" +
      "tePersonRequest\032\033.clapi.UpdatePersonResp" +
      "onse\"\000\022I\n\014deletePerson\022\032.clapi.DeletePer" +
      "sonRequest\032\033.clapi.DeletePersonResponse\"",
      "\000\022R\n\017deleteAllPerson\022\035.clapi.DeleteAllPe" +
      "rsonRequest\032\036.clapi.DeleteAllPersonRespo" +
      "nse\"\000B\032\n\tcom.clapiB\013CLApiProtosP\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_clapi_GetVersionRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_clapi_GetVersionRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_GetVersionRequest_descriptor,
        new java.lang.String[] { });
    internal_static_clapi_GetVersionResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_clapi_GetVersionResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_GetVersionResponse_descriptor,
        new java.lang.String[] { "Version", });
    internal_static_clapi_WaitNotificationRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_clapi_WaitNotificationRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_WaitNotificationRequest_descriptor,
        new java.lang.String[] { });
    internal_static_clapi_WaitNotificationResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_clapi_WaitNotificationResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_WaitNotificationResponse_descriptor,
        new java.lang.String[] { "Notification", });
    internal_static_clapi_AddPersonRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_clapi_AddPersonRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_AddPersonRequest_descriptor,
        new java.lang.String[] { "Person", });
    internal_static_clapi_AddPersonResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_clapi_AddPersonResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_AddPersonResponse_descriptor,
        new java.lang.String[] { });
    internal_static_clapi_UpdatePersonRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_clapi_UpdatePersonRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_UpdatePersonRequest_descriptor,
        new java.lang.String[] { "Person", });
    internal_static_clapi_UpdatePersonResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_clapi_UpdatePersonResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_UpdatePersonResponse_descriptor,
        new java.lang.String[] { });
    internal_static_clapi_DeletePersonRequest_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_clapi_DeletePersonRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_DeletePersonRequest_descriptor,
        new java.lang.String[] { "Person", });
    internal_static_clapi_DeletePersonResponse_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_clapi_DeletePersonResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_DeletePersonResponse_descriptor,
        new java.lang.String[] { });
    internal_static_clapi_DeleteAllPersonRequest_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_clapi_DeleteAllPersonRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_DeleteAllPersonRequest_descriptor,
        new java.lang.String[] { });
    internal_static_clapi_DeleteAllPersonResponse_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_clapi_DeleteAllPersonResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_DeleteAllPersonResponse_descriptor,
        new java.lang.String[] { });
    internal_static_clapi_Email_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_clapi_Email_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_Email_descriptor,
        new java.lang.String[] { "Email", "Type", });
    internal_static_clapi_Phone_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_clapi_Phone_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_Phone_descriptor,
        new java.lang.String[] { "Phone", "Type", });
    internal_static_clapi_Address_descriptor =
      getDescriptor().getMessageTypes().get(14);
    internal_static_clapi_Address_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_Address_descriptor,
        new java.lang.String[] { "Type", "Street", "City", "Region", "Zipcode", "Country", });
    internal_static_clapi_Person_descriptor =
      getDescriptor().getMessageTypes().get(15);
    internal_static_clapi_Person_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_Person_descriptor,
        new java.lang.String[] { "Uid", "Firstname", "Lastname", "Emails", "Phones", "Addresses", "Imageurl", });
    internal_static_clapi_Characteristic_descriptor =
      getDescriptor().getMessageTypes().get(16);
    internal_static_clapi_Characteristic_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_Characteristic_descriptor,
        new java.lang.String[] { "Uid", "Mode", "Type", "Event", });
    internal_static_clapi_Service_descriptor =
      getDescriptor().getMessageTypes().get(17);
    internal_static_clapi_Service_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_Service_descriptor,
        new java.lang.String[] { "Uid", "Characteristics", });
    internal_static_clapi_Accessory_descriptor =
      getDescriptor().getMessageTypes().get(18);
    internal_static_clapi_Accessory_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_Accessory_descriptor,
        new java.lang.String[] { "Uid", "Label", "Manufacturer", "Model", "Serialnumber", "Services", "Imageurl", });
    internal_static_clapi_Room_descriptor =
      getDescriptor().getMessageTypes().get(19);
    internal_static_clapi_Room_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_Room_descriptor,
        new java.lang.String[] { "Uid", "Label", "Accessories", "Imageurl", });
    internal_static_clapi_Zone_descriptor =
      getDescriptor().getMessageTypes().get(20);
    internal_static_clapi_Zone_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_Zone_descriptor,
        new java.lang.String[] { "Uid", "Label", "Rooms", "Imageurl", });
    internal_static_clapi_Home_descriptor =
      getDescriptor().getMessageTypes().get(21);
    internal_static_clapi_Home_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_Home_descriptor,
        new java.lang.String[] { "Uid", "Label", "Zones", "Imageurl", });
    internal_static_clapi_Data_descriptor =
      getDescriptor().getMessageTypes().get(22);
    internal_static_clapi_Data_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_Data_descriptor,
        new java.lang.String[] { "Version", "Persons", "Homes", });
    internal_static_clapi_Notification_descriptor =
      getDescriptor().getMessageTypes().get(23);
    internal_static_clapi_Notification_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_clapi_Notification_descriptor,
        new java.lang.String[] { "Type", "Data", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
