package com.work.util;

import java.lang.reflect.Field;

public class StaticResourcesParser {

	public static final String JS = "/js/";
	public static final String CSS = "/css/";
	public static final String IMG = "/img/";
	public static final String UPLOAD = "/upload/";
	public static final String FONT = "/font/";
	public static final String SHEARPHOTO = "/shearphoto/";
	public static final String ZOOMIMAGE = "/zoomimage/";

	public static boolean parseUrl(String url) {
		// 通过反射得到此类所配置的url地址的字段
		Field[] fields = StaticResourcesParser.class.getDeclaredFields();
		String value;
		for (Field f : fields) {
			try {
				value = (String) f.get(StaticResourcesParser.class);
				// 如果url中有此类字符串，则放行
				if (url.indexOf(value) >= 0) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
}
