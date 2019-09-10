package com.homework3.ylc.kafka_consumer_storm;

import java.util.Map;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;


public class MyBolt extends BaseRichBolt{
	
	private static final long serialVersionUID = 1L; 
	private OutputCollector collector;
	// 定义hbasewriter
	private HbaseWriter hwriter = null;
	// 定义hbase中的表名
	private String tableName = new String("userbehavior");
	// 定义hbase中的表的字段
	private String[] fields = new String[] {"Uid", "Behavior", "Aid", "BehaviorTime"};
	// 定义具有列限定符的列的索引
	private int qualifierColumnIndex = 1;
	// 处理原始值
	public String[] processOriginValue(String value)
	{
		// 移除.0，即毫秒
		String line = value.replace(".0", "");
		String []list = line.split("\\|");
		// 移除首尾的空格
		for(String v:list) {
			v = v.trim();
//			System.out.println(v);
		}
		
		return list;
	}
	// 根据字段值确定列限定符
	public String getQualifierByValue(String value)
	{
		String ret = null;
		int valueInt = Integer.valueOf(value);
		if(valueInt == 0)
		{
			ret = new String("publish");
		}else if(valueInt == 1)
		{
			ret = new String("view");
		}else if(valueInt == 2)
		{
			ret = new String("comment");
		}
		return ret;
	}
	
	public static void main(String []argv)
	{
//		MyBolt myBolt = new MyBolt();
//		myBolt.processOriginValue("");
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		System.out.println(System.currentTimeMillis());
	}
	
	public void execute(Tuple arg0) {
		// TODO Auto-generated method stub
        try {  
            // 接收KafkaSpout的数据  
            byte[] bytes = arg0.getBinaryByField("bytes");  
            String origin = new String(bytes);
            // 处理原始值
            String[] values = processOriginValue(origin);
            // 得到列限定符
            String qualifier = getQualifierByValue(values[qualifierColumnIndex]);
        	String[] columns = fields.clone();
            // 修改列名
            if(qualifier != null)
            {
            	columns[qualifierColumnIndex] = columns[qualifierColumnIndex] + ":" + qualifier;
            	System.err.println(columns[qualifierColumnIndex]);
            }
            // 添加rowkey，使用当前时间戳
            String row = String.valueOf(System.currentTimeMillis());
            hwriter.addRecord(tableName, row, columns, values);
            
            this.collector.ack(arg0);  
       } catch (Exception e) {  
            this.collector.fail(arg0);
            e.printStackTrace();
       }  
	}

	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		// TODO Auto-generated method stub
		this.collector = arg2;
		
		hwriter = new HbaseWriter();
	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		
	}

}
