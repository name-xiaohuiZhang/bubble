package com.homework3.ylc.kafka_producer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MyUtil {
	public static Properties get_producer_props()
	{
		Properties props = new Properties();
        props.put("bootstrap.servers", MyProperties.bootstrap_servers);
        props.put("acks", MyProperties.acks);
        props.put("retries", MyProperties.retries);
        props.put("batch.size", MyProperties.batch_size);
        props.put("linger.ms", MyProperties.linger_ms);
        props.put("buffer.memory", MyProperties.buffer_memory);
        props.put("key.serializer", MyProperties.key_serializer);
        props.put("value.serializer", MyProperties.value_serializer);
        return props;
	}
	public static Properties get_consumer_props()
	{
    	Properties props = new Properties();
    	props.put("bootstrap.servers", MyProperties.bootstrap_servers);
        props.put("group.id", MyProperties.group_id);    
        props.put("enable.auto.commit", MyProperties.enable_auto_commit);    
        props.put("auto.commit.interval.ms", MyProperties.auto_commit_interval_ms);    
        props.put("session.timeout.ms", MyProperties.session_timeout_ms);    
        props.put("key.deserializer", MyProperties.key_deserializer);    
        props.put("value.deserializer", MyProperties.value_deserializer);
        return props;
	}
	public static String get_timestamp()
	{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        return date;
	}
}
