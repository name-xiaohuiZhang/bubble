package com.homework3.ylc.kafka_producer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
public class FileOperator {

	private final String filename;
	private final BufferedReader br;
	
	public FileOperator(String filename)
	{
		this.filename = filename;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.filename)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br = reader;
	}
	
	public String readLine()
	{
		String line = null;
		try {
			line = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}
	
	public void close() throws Exception
	{
		if (br != null)
		{
			br.close();
		}
	}
	
	public static void main(String []argv) throws Exception
	{
		String filename = "data/userbehavior.txt";
		
		FileOperator fo = new FileOperator(filename);
		System.out.println(fo.readLine());
		fo.close();
	}
}
