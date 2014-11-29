package com.scu.secreat;

import java.io.InputStreamReader;  
import java.io.LineNumberReader;  

//import org.junit.Test;
  
  
public class MacUtils {  
  
//	@Test
	public void TestGetMal(){
		String mac = MacUtils.getMac();
		System.out.println(mac);
		
	}
    public static String  getMac(){  
    try {  
  
        Process process = Runtime.getRuntime().exec("ipconfig /all");  
  
        InputStreamReader ir = new InputStreamReader(process.getInputStream());  
  
        LineNumberReader input = new LineNumberReader(ir);  
  
        String line;  
  
        while ((line = input.readLine()) != null)  
              
  
        if (line.indexOf("Physical Address") > 0 || line.indexOf("ÎïÀíµØÖ·")> 0) {  
  
            String MACAddr = line.substring(line.indexOf("-") - 2);  
  
            System.out.println("MAC address = [" + MACAddr + "]");  
            return MACAddr;
  
        }  
  
        } catch (java.io.IOException e) {  
  
           e.printStackTrace();
  
        }  
    return null;
    }
}  