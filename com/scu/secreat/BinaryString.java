package com.scu.secreat;
public class BinaryString {
	public void TestGetBinary(){
		String temp = BinaryString.getBinary("AE");
		System.out.println(temp);
	}
	public static String getBinary(String mac){
		StringBuffer buffer = new StringBuffer();
		char[] array  = mac.toCharArray();
		for(char tmp: array){
			switch(tmp){
			case '0':
				buffer.append("0000");
				break;
			case '1':
				buffer.append("0001");
				break;
			case '2':
				buffer.append("0010");
				break;
			case '3':
				buffer.append("0011");
				break;
			case '4':
				buffer.append("0100");
				break;
			case '5':
				buffer.append("0101");
				break;
			case '6':
				buffer.append("0110");
				break;
			case '7':
				buffer.append("0111");
				break;
			case '8':
				buffer.append("1000");
				break;
			case '9':
				buffer.append("1001");
				break;
			case 'A':
				buffer.append("1010");
				break;
			case 'B':
				buffer.append("1011");
				break;
			case 'C':
				buffer.append("1100");
				break;
			case 'D':
				buffer.append("1101");
				break;
			case 'E':
				buffer.append("1110");
				break;
			case 'F':
				buffer.append("1111");
				break;
			default:
				break;
			}
		}
		return buffer.toString();
	}

}
