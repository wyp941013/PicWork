package com.work.util;

public class ImageNameGenerator {

	public static String generator(Integer id) throws Exception{
		
		String result;
		switch(id.toString().length()){
			case 1:
				result="000000"+id;
				break;
			case 2:
				result="00000"+id;
				break;
			case 3:
				result="0000"+id;
				break;
			case 4:
				result="000"+id;
				break;
			case 5:
				result="00"+id;
				break;
			case 6:
				result="0"+id;
				break;
			case 7:
				result=id.toString();
				break;
			 default:
	                throw new Exception("超过最大值");
				
		}
		return result;
	}

}
