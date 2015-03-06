package com.pactera.tech.assessment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TestClass {

	public static void main(String[] args) {
		String filenames[] = new String[2];
	Scanner scan = new Scanner(System.in);
	System.out.println("Enter the file");
		filenames[0] = scan.nextLine();
		System.out.println(filenames[0]);
		
		 try{
				File file = new File(filenames[0]);
	            InputStream inStream = new FileInputStream(file); 
	            InputStreamReader ipStreamReader = new InputStreamReader(inStream);
	            BufferedReader br=new BufferedReader(ipStreamReader);
	            String line;
	            while ((line=br.readLine())!=null){
	                System.out.println(line);
	               // string+=line+"\n";
	            }
	            br.close(); 
	        }       
	        catch (Exception e){
	            System.out.println(e.toString());
	        }
		
	}
	
}
