// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: demo.proto

package com.grpc.dto;

public final class DemoServerRpc {
  private DemoServerRpc() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_DemoResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_DemoResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_DemoRquest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_DemoRquest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\ndemo.proto\022\nhelloworld\"7\n\014DemoResponse" +
      "\022\014\n\004code\030\001 \001(\005\022\013\n\003msg\030\002 \001(\t\022\014\n\004data\030\003 \001(" +
      "\t\"5\n\nDemoRquest\022\014\n\004code\030\001 \001(\005\022\013\n\003msg\030\002 \001" +
      "(\t\022\014\n\004data\030\003 \001(\t2I\n\nDemoServer\022;\n\005hello\022" +
      "\026.helloworld.DemoRquest\032\030.helloworld.Dem" +
      "oResponse\"\000B#\n\020com.my.com.grpc.demoB\rDemoSer" +
      "verRpcP\001b\006proto3"
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
    internal_static_helloworld_DemoResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_helloworld_DemoResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_DemoResponse_descriptor,
        new String[] { "Code", "Msg", "Data", });
    internal_static_helloworld_DemoRquest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_helloworld_DemoRquest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_DemoRquest_descriptor,
        new String[] { "Code", "Msg", "Data", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
