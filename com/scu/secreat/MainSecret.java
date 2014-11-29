package com.scu.secreat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MainSecret {
	public  String test() {
		String secreat="";
		try {
			String Mac = MacUtils.getMac();
			String[] macString = Mac.split("-");
			StringBuffer resultNum = new StringBuffer();
			for(String temp: macString){
				String tempNum = BinaryString.getBinary(temp);
				resultNum.append(tempNum);
			}
			secreat=resultNum.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("secreat-------------"+secreat);
		return secreat;
	}
}
