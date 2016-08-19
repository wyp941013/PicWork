package com.work.util;


public class IndexHelper {
	
	static{
		System.out.println("加载DLL文件----");
		System.loadLibrary("Multi-index_hash");
		System.out.println("加载DLL文件成功----");
	}
	private IndexHelper(){}
	
	//strpath存储索引的根目录
	public static native int loadIndex(String strPath);
		
	// strPath保存索引的根目录，与第一个接口的strPath相同
	public static native int updateIndex(String strPath);
	
	//strPath是与索引相同的根目录，该目录下//有queryImages文件夹，该文件夹是用来存储查询图像的。strName是该查询图像的名称。strDetection是该检索操的ID。
	public static native int query(String strPath, String strName, String strDetection);
	
}
