package com.homework3.ylc.kafka_consumer_storm;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName; 
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
//import org.slf4j.impl.Log4jLoggerFactory;


public class HbaseWriter {
//	private static org.apache.log4j.Logger logger = Logger
//            .getLogger(HbaseWriter.class);
	
    static Configuration configuration = null;  
    static Connection connection = null;
    public static String ZKconnect = "172.31.42.120:2181,172.31.42.98:2181,172.31.42.202:2181";
    static{
//    	logger.setLevel(Level.ERROR);
        configuration = HBaseConfiguration.create(); //会加载hbase-site.xml配置文件
        configuration.set("hbase.zookeeper.quorum", ZKconnect);  
        try {  
            connection = ConnectionFactory.createConnection(configuration);  //连接集群
        } catch (Exception e) {  
            e.printStackTrace();
        }
    }
    
    public HbaseWriter()
    {
    	
    }
    
    public void getAllTables(){
        try {  
        	Admin admin = connection.getAdmin(); 
        	TableName[] tableNames = admin.listTableNames();
        	System.out.println("TABLE");
            for(int i=0;i<tableNames.length;i++){
                System.out.println(tableNames[i].toString());
            }
            admin.close();
        } catch (Exception e) {  
            e.printStackTrace();
        }
    }
	
    public void addRecord(String tableName, String row, String[] fields, String[] values)
    {
    	try {
    		Table table = connection.getTable(TableName.valueOf(tableName));
    		int count = values.length;
    		for(int i = 0;i < count;i++)
    		{
    			String field = fields[i];
    			String value = values[i];
    			String family = field;
    			String qualifier = new String("");
    			if (field.indexOf(":") != -1) {
	    			String[] temp = field.split(":");
	    			family = temp[0];
	    			qualifier = temp[1];
    			}
    			System.out.println("insert {" + tableName + "} column " + family + ":" + qualifier + ", value=" + value);
        		Put put = new Put(row.getBytes());
    			put.addColumn(family.getBytes(), qualifier.getBytes(), value.getBytes()); //列族名，列名，值
    			
    			table.put(put);
    		}
    		table.close();
    		
    	}catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public static void main(String[] argv)
    {
    	HbaseWriter hw = new HbaseWriter();
    	hw.getAllTables();
    }
    
}
