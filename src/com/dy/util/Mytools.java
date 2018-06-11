package com.dy.util;

import java.net.URLDecoder;

public class Mytools {
	public static String getNewString(String str){
		String newString="";
		try {
			newString=URLDecoder.decode(str, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return newString;
	}
}
