package com.homework3.ylc.kafka_producer;

public class MyProperties {
	// 订阅主题列表topic
    public final static String topic = "mykafka";
    //zookeeper
    public final static String zk_host = "172.31.42.120:2181,172.31.42.98:2181,172.31.42.202:2181";
    // producer properties
    public final static String bootstrap_servers = "172.31.42.120:9092,172.31.42.98:9092,172.31.42.202:9092";
    //The "all" setting we have specified will result in blocking on the full commit of the record, the slowest but most durable setting.
    //“所有”设置将导致记录的完整提交阻塞，最慢的，但最持久的设置。
    public final static String acks = "all";
	//如果请求失败，生产者也会自动重试，即使设置成０ the producer can automatically retry.
    public final static int retries = 0;
	//The producer maintains buffers of unsent records for each partition. 
    public final static int batch_size = 16384;
	//发送延时毫秒数
    public final static int linger_ms = 1;
	//生产者缓冲大小，当缓冲区耗尽后，额外的发送调用将被阻塞。时间超过max.block.ms将抛出TimeoutException
    public final static int buffer_memory = 33554432;
  //The key.serializer and value.serializer instruct how to turn the key and value objects the user provides with their ProducerRecord into bytes.
    public final static String key_serializer = "org.apache.kafka.common.serialization.StringSerializer";
    public final static String value_serializer = "org.apache.kafka.common.serialization.StringSerializer";
    //消费者的组id
    public final static String group_id = "0";
    public final static String enable_auto_commit = "true";
    public final static String auto_commit_interval_ms = "1000";
    //从poll(拉)的回话处理时长
    public final static String session_timeout_ms = "30000";
    public final static String key_deserializer = "org.apache.kafka.common.serialization.StringDeserializer";
    public final static String value_deserializer = "org.apache.kafka.common.serialization.StringDeserializer";
    //生产者字段之间的分割字符
    public final static String line_data_field_sep = "|";
}
