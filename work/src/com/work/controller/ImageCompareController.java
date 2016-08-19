package com.work.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.work.entity.ImageInfo;
import com.work.entity.ImageQueryInfo;
import com.work.entity.ImageResultInfo;
import com.work.entity.UserInfo;
import com.work.service.ImageInfoService;
import com.work.service.ImageQueryInfoService;
import com.work.service.ImageResultInfoService;
import com.work.util.IndexHelper;
import com.work.util.Pager;

//图片对比操作类

@Controller
@RequestMapping("/compare")
public class ImageCompareController {
	@Autowired
	private ImageQueryInfoService imageQueryInfoService;
	@Autowired
	private ImageInfoService imageInfoService;
	@Autowired
	private ImageResultInfoService imageResultInfoService;

	public ImageResultInfoService getImageResultInfoService() {
		return imageResultInfoService;
	}

	public void setImageResultInfoService(
			ImageResultInfoService imageResultInfoService) {
		this.imageResultInfoService = imageResultInfoService;
	}

	public ImageQueryInfoService getImageQueryInfoService() {
		return imageQueryInfoService;
	}

	public void setImageQueryInfoService(
			ImageQueryInfoService imageQueryInfoService) {
		this.imageQueryInfoService = imageQueryInfoService;
	}

	public ImageInfoService getImageInfoService() {
		return imageInfoService;
	}

	public void setImageInfoService(ImageInfoService imageInfoService) {
		this.imageInfoService = imageInfoService;
	}

	// 多图检索视图
	@RequestMapping("/compare")
	public ModelAndView imageCompare() {
		ModelAndView mav = new ModelAndView("compare/imageCompare");
		return mav;
	}
	// 单图检索视图
	@RequestMapping("/compareSingle")
	public ModelAndView compareSingle() {
		ModelAndView mav = new ModelAndView("compare/single");
		return mav;
	}

	
//	@RequestMapping("/test")
//	public ModelAndView test() {
//		ModelAndView mav = new ModelAndView("compare/demo");
//		return mav;
//	}

	@RequestMapping("/singleUpload")
	public @ResponseBody String testUpload(HttpServletRequest request,@RequestParam("UpFile") CommonsMultipartFile file) throws IllegalStateException, IOException {
		
		String queryId = null;
		if(!file.isEmpty()){
			String path = request.getServletContext().getRealPath("/");
			path = path.replace("\\", "/");
			UserInfo user = (UserInfo) request.getSession().getAttribute("user");
			String format = ".jpg";// 图片格式
			Integer maxId = imageQueryInfoService.findMaxId();// 获取当前数据库中的最大ID
			String filename = "";
			if (maxId != null)
				filename = (maxId + 1) + format;// 最大ID存在时，完整图片名
			String address = "/upload/queryImages/" + filename;// 用于在搜索保存内容中存储
			// 保存搜索记录
			Date date = new Date();
			ImageQueryInfo imageQueryInfo = new ImageQueryInfo();
			imageQueryInfo.setCreateTime(date);
			imageQueryInfo.setEditorUserId(user.getUserId());
			imageQueryInfo.setImageUploadingAddress(address);
			imageQueryInfoService.save(imageQueryInfo);
			if (maxId == null) {
				maxId = imageQueryInfo.getQueryId();
				filename = maxId + format;
				address = "/upload/queryImages/" + filename;
				imageQueryInfo.setImageUploadingAddress(address);
				imageQueryInfoService.update(imageQueryInfo);
			}
			
			file.transferTo(new File(path+address));
			int re = IndexHelper.query(path+"upload", imageQueryInfo.getQueryId()+format, imageQueryInfo.getQueryId().toString());
			queryId = imageQueryInfo.getQueryId().toString();
		}
		return queryId;
	}
	

	// 检索历史
	@RequestMapping(value = "/searchHistory")
	public String searchHistory(Integer page, ModelMap modelMap)
			throws ParseException {
		if (page == null)
			page = 1;
		int pageSize = 15;
		Pager<ImageQueryInfo> pager = imageInfoService.findQueryList(page,
				pageSize);
		modelMap.put("lists", pager.getReusltLists());
		modelMap.put("page", page);
		modelMap.put("lastPage", pager.getTotalPage());
		return "compare/imageSearchHistoryList";
	}

	@RequestMapping("/deleteSearchHistory")
	public String deleteSearchHistory(Integer id, HttpServletRequest request,
			HttpSession session) throws IOException {

		if (((UserInfo) session.getAttribute("user")).getPermission() == 2) { // 权限为2即admin级别的才能执行删除操作
			String path = request.getServletContext().getRealPath("/");
			try {
				imageQueryInfoService.deleteImageAndFile(id, path);
				imageResultInfoService.deleteByQueryId(id);
			} catch (Exception e) {
				System.out.println("检索记录删除出现错误！");
			}
			return "redirect:searchHistory";
		}
		return null;
	}

	@RequestMapping("/multiQueryDelete")
	public String multiQueryDelete(Integer ids[],HttpServletRequest request,HttpSession session){
		if(((UserInfo)session.getAttribute("user")).getPermission()==2){	//只有权限为2(admin)级别的才能执行删除操作
			String path = request.getServletContext().getRealPath("/");
			try{
					imageQueryInfoService.deleteImagesAndFiles(ids,path);
					for(int queryId : ids){
						imageResultInfoService.deleteByQueryId(queryId);
					}
				}catch(Exception e){
					System.out.println("检索记录删除出现错误！");
				}
			return "redirect:searchHistory";
		}
		return null;
	}
	
//	@RequestMapping("/saveUpImage")
//	public void saveUpImage(HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		
//		PrintWriter out = response.getWriter();
//		String path = request.getServletContext().getRealPath("/");
//		path = path.replace("\\", "/");
//		
//		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
//		
//		String format = ".jpg";// 图片格式
//		Integer maxId = imageQueryInfoService.findMaxId();// 获取当前数据库中的最大ID
//		String filename = "";
//		if (maxId != null)
//			filename = (maxId + 1) + format;// 最大ID存在时，完整图片名
//		String address = "/upload/queryImages/" + filename;// 用于在搜索保存内容中存储
//
//		// 保存搜索记录
//		Date date = new Date();
//		ImageQueryInfo imageQueryInfo = new ImageQueryInfo();
//		imageQueryInfo.setCreateTime(date);
//		imageQueryInfo.setEditorUserId(user.getUserId());
//		imageQueryInfo.setImageUploadingAddress(address);
//		imageQueryInfoService.save(imageQueryInfo);
//
//		if (maxId == null) {
//			maxId = imageQueryInfo.getQueryId();
//			filename = maxId + format;
//			address = "/upload/queryImages/" + filename;
//			imageQueryInfo.setImageUploadingAddress(address);
//			imageQueryInfoService.update(imageQueryInfo);
//		}
//
//		String file_src = path + address; // 保存路径
//
//		String pic = request.getParameter("pic1");
//
//		if (!pic.equals("") && pic != null) {
//			// 原图
//			File file = new File(file_src);
//			FileOutputStream fout = null;
//			fout = new FileOutputStream(file);
//			fout.write(new BASE64Decoder().decodeBuffer(pic));
//			fout.close();
//		}
//		String picUrl = filename;
//		out.println("{\"status\":1,\"picUrl\":\"" + picUrl + "\"}"); // 返回图片地址
//	}
//
//	@RequestMapping("/singleCompare")
//	public synchronized ModelAndView singleCompare(HttpServletRequest request,
//			HttpServletResponse response) throws UnsupportedEncodingException {
//		String path = request.getServletContext().getRealPath("/");
//		path = path.replace("\\", "/");
//		// address其实保存的是检索图片名，即检索Id+后缀
//		String address = request.getParameter("address");
//		// System.out.println("第一次参数："+address);
//		if (!"".equals(address)) {
//			address = new String(address.getBytes("iso-8859-1"), "UTF-8");
//		}
//		// System.out.println("第二次参数："+address.substring(0,
//		// address.lastIndexOf(".")));
//		int result = IndexHelper.query(path + "/upload", address,address.substring(0, address.lastIndexOf(".")));
//
//		// 1.获取此次检索的queryId
//		int queryId = 1;
//		if (result > 0) {
//			// 存在匹配对象
//			queryId = Integer.parseInt(address.substring(0,address.lastIndexOf(".")));
//			System.out.println("============="+queryId);
//		}
//		// 跳转到分页处理部分,直接分页显示
//		ModelAndView mav = new ModelAndView("redirect:/compare/page");
//		request.getSession().setAttribute("queryId", queryId);
//		return mav;
//	}

	// 分页显示检索图片的结果图片
	@RequestMapping("/page")
	public String page(HttpServletRequest request, ModelMap modelMap,
			Integer queryId, Integer pageNow, Integer pageSize)
			throws UnsupportedEncodingException {

		if (queryId == null)
			queryId = (Integer) request.getSession().getAttribute("queryId");
		if (pageNow == null)
			pageNow = 1;
		if (pageSize == null)
			pageSize = 15;

		ImageResultInfo imageResultInfo = new ImageResultInfo();
		imageResultInfo.setQueryId(queryId);
		// 通过检索ID到检索结果表中获取检索结果image_ID
		Pager<ImageInfo> pager = imageResultInfoService.findByQueryIdWithPage(queryId, pageNow, pageSize);

		List<ImageInfo> imageInfoList = new ArrayList<ImageInfo>();

		imageInfoList = pager.getReusltLists();
		ImageQueryInfo iqi = imageQueryInfoService.findById(queryId);
		// 传递到前台的参数
		modelMap.put("imageInfoList", imageInfoList);
		modelMap.put("page", pageNow);
		modelMap.put("queryId", queryId);
		modelMap.put("imageUrl",iqi.getImageUploadingAddress().substring(1,iqi.getImageUploadingAddress().length()));
		modelMap.put("lastPage", pager.getTotalPage());
		return "compare/imageCompareResult";
	}

	// 开始多图检索
	@RequestMapping("/mulsearch")
	public synchronized ModelAndView mulsearch(HttpServletRequest request,
			@RequestParam("files[]") ArrayList<MultipartFile> files,
			Integer classId[], String desp[]) throws IllegalStateException,
			IOException {

		String path = request.getServletContext().getRealPath("/");

		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		Integer userId = user.getUserId(); // 当前操作者的ID
		List<ImageQueryInfo> imageQueryInfolist = new ArrayList<ImageQueryInfo>();

		for (int i = 0; i < files.size(); i++) {
			MultipartFile file = files.get(i);
			// 取得当前上传文件的文件名称
			String originaFileName = file.getOriginalFilename();
			if (originaFileName != "") {
				String format = originaFileName.substring(originaFileName
						.lastIndexOf(".")); // 取得文件格式

				Integer maxId = imageQueryInfoService.findMaxId();// 获取当前数据库中的最大ID
				String filename = "";
				if (maxId != null)
					filename = (maxId + 1) + format;// 最大ID存在时，完整图片名
				String address = "/upload/queryImages/" + filename;// 用于在搜索保存内容中存储

				// 图片信息的相关存储
				Date date = new Date();
				ImageQueryInfo imageQueryInfo = new ImageQueryInfo();
				imageQueryInfo.setCreateTime(date);
				imageQueryInfo.setEditorUserId(userId);
				imageQueryInfo.setImageUploadingAddress(address);
				imageQueryInfoService.save(imageQueryInfo);

				// 注：下方所有操作前提为搜索记录保存的Id和图片存储时的名字相同
				// 再次确认最大ID是否为空，
				// 若不为空，则上述的图片路径正确，可直接使用
				// 若为空，则说明上面保存的路径不是正确的，需要获取这个搜索保存时的ID记录号，用作图片名的补全
				// 补全的同时需要更新刚才保存的搜索路径
				// 例：ID为空时，一开始保存的路径是xxxxx/.jpg,没有确切的名字，但是有保存的ID
				// 修改后的路径为：xxxxx/xxx.jpg
				if (maxId == null) {
					maxId = imageQueryInfo.getQueryId();
					filename = maxId + format;
					address = "/upload/queryImages/" + filename;
					imageQueryInfo.setImageUploadingAddress(address);
					imageQueryInfoService.update(imageQueryInfo);
				}

				String realPath = path + address;
				realPath = realPath.replace("\\", "/");
				realPath = realPath.replace("//", "/");
				File localFile = new File(realPath);

				if (!localFile.getParentFile().exists())
					localFile.getParentFile().mkdirs();
				file.transferTo(localFile); // 将图片保存到文件系统中

				int result = IndexHelper.query(path + "/upload", filename,
						filename.substring(0, filename.lastIndexOf(".")));
				if(result>=1){
					imageQueryInfo.setState(1);
				}

				imageQueryInfolist.add(imageQueryInfo);
			}
		}
		ModelAndView mnv = new ModelAndView("compare/imageCompareMulResult");
		request.getSession().setAttribute("lists", imageQueryInfolist);
		return mnv;
	}

}
