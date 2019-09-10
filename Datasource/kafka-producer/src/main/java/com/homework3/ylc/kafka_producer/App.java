package com.homework3.ylc.kafka_producer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println(System.getProperty("java.class.path"));
    	String filename = "/home/hadoop/userbehavior.txt";
        MyProducer producer1 = new MyProducer(MyProperties.topic, 0, filename, "MyProducer");
        producer1.start();
    }
}
