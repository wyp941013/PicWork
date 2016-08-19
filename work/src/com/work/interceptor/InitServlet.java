package com.work.interceptor;

import java.io.File;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServlet;

import com.work.util.IndexHelper;

public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				System.out.println("自动加载图像索引......");

				String rootPath = getServletContext().getRealPath("/");
				String dllPath = rootPath + "WEB-INF" + File.separator + "dll";
				//添加DLL路径到java.library.path中
				addDir(dllPath);
				System.out.println(System.getProperty(("java.library.path")));
				File file = new File(rootPath + "upload" + File.separator + "feature.des"); // 判断索引文件是否存在，若不存在则更新索引
				if (!file.exists()) {
					System.out.println("系统中无索引文件！正在自动生成索引文件!");
					IndexHelper.updateIndex(rootPath + "upload");
				}
				int result = IndexHelper.loadIndex(rootPath + "upload");
				if (result == 0){
					System.out.println("索引加载成功......");
					getServletContext().setAttribute("isIndexLatest", true);
				}
				else
					System.out.println("索引加载失败......");
			}
		};
		Thread thread = new Thread(task);
		thread.start();

	}

	public static void addDir(String s) {
		try {
			Field field = ClassLoader.class.getDeclaredField("usr_paths");
			field.setAccessible(true);
			String[] paths = (String[]) field.get(null);
			for (int i = 0; i < paths.length; i++) {
				if (s.equals(paths[i])) {
					return;
				}
			}
			String[] tmp = new String[paths.length + 1];
			System.arraycopy(paths, 0, tmp, 0, paths.length);
			tmp[paths.length] = s;
			field.set(null, tmp);
			paths = (String[]) field.get(null);
			System.setProperty("java.library.path", join(paths, ";"));
		} catch (IllegalAccessException e) {
			// throw new IOException(
			// "Failed to get permissions to set library path");
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// throw new IOException(
			// "Failed to get field handle to set library path");
			e.printStackTrace();
		}
	}

	private static String join(String[] paths, String separator) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < paths.length; i++) {
			sb.append(paths[i] + separator);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
