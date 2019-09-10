package com.homework3.ylc.kafka_producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.*;

public class MyProducer extends Thread {
	 	private final Producer<String, String> producer;
	    private final String topic;
	    private final String filename;
	    private final FileOperator fo;
	    private final Integer partition;
	    // 用于区分自身，以打印生成者身份
	    private final String id;
	    
	    public MyProducer(String topic, Integer partition, String filename, String id)
	    {
	    	Properties props = MyUtil.get_producer_props();
	        producer = new KafkaProducer<String, String>(props);
	        this.topic = topic;
	        this.partition = partition;
	        this.filename = filename;
	        fo = new FileOperator(this.filename);
	        this.id = id;
	    }
	    @Override
	    public void run() {
	        while (true)
	        {
	        	String lineData = fo.readLine();
	        	if(lineData == null)
	        	{
	        		break;
	        	}
	            String date = MyUtil.get_timestamp();
	            //ProducerRecord(topic, partition, key, value)
	            //ProducerRecord(topic, key, value)
	            //ProducerRecord(topic, value)
	            try {
            		System.out.println(id + " send " + lineData);
	            	producer.send(new ProducerRecord<String, String>(topic, partition, date, lineData));
	            }catch (Exception ecp)
	            {
	            	ecp.printStackTrace();
	            }
	        }
	        // 所有数据已发送
	        System.err.println("All data is produced....");
	    }
	    
	    public static void main(String[] args)
	    {
			String filename = "/home/hadoop/userbehavior.txt";
	        MyProducer producer1 = new MyProducer(MyProperties.topic, 0, filename, "MyProducer");
	        producer1.start();
	    }
}
