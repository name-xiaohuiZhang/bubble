package com.homework3.ylc.kafka_consumer_storm;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		KafkaProcessing kp = new KafkaProcessing();
		kp.runCluster();
    }
}
