package com.work.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.work.entity.ImageChange;
import com.work.entity.ImageClassify;
import com.work.entity.ImageInfo;
import com.work.entity.ImageLog;
import com.work.entity.ImageQueryInfo;
import com.work.entity.UserInfo;
import com.work.service.ImageChangeService;
import com.work.service.ImageClassifyService;
import com.work.service.ImageInfoService;
import com.work.service.ImageLogService;
import com.work.service.ImageQueryInfoService;
import com.work.service.ImageResultInfoService;
import com.work.util.ImageNameGenerator;
import com.work.util.IndexHelper;
import com.work.util.Pager;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	private ImageLogService imageLogService;
	@Autowired
	private ImageInfoService imageInfoService;
	@Autowired
	private ImageClassifyService imageClassifyService;
	@Autowired
	private ImageChangeService imageChangeService;
	@Autowired
	private ImageQueryInfoService imageQueryInfoService;
	@Autowired
	private ImageResultInfoService imageResultInfoService;
	
	@RequestMapping("/list")
	public String list(Integer pageSize,Integer page,Integer classId,String desp,Integer editorId,String createTime,ModelMap modelMap) throws ParseException{
		
		if(page == null)
			page = 1;
		if(pageSize == null)
			pageSize = 15;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		ImageInfo imageInfo = new ImageInfo();
		if(createTime!=null&&!createTime.equals(""))
			imageInfo.setCreateTime(sdf.parse(createTime));
		if(classId!=null)
			imageInfo.setImageClassify(imageClassifyService.findById(classId));
		imageInfo.setEditorUserId(editorId);
		imageInfo.setImageDespretion(desp);
		
		
		Pager<ImageInfo> pager = imageInfoService.findByExample(imageInfo,page,pageSize);
		modelMap.put("lists", pager.getReusltLists());
		modelMap.put("page", page);
		modelMap.put("lastPage",pager.getTotalPage());
		
		
		modelMap.put("classId", classId);
		modelMap.put("desp", desp);
		modelMap.put("editorId",editorId);
		modelMap.put("createTime",createTime);
		
		return "image/imageList";
	}
	
	@RequestMapping("/save")
	public String save(ModelMap modelMap){
		List<ImageClassify> classLists = imageClassifyService.findAll();
		modelMap.put("classLists", classLists);
		return "image/imageAdd";
	}

	@RequestMapping("/delete")		
	public String delete(Integer id,HttpServletRequest request,HttpSession session) throws IOException{
		
		if(((UserInfo)session.getAttribute("user")).getPermission()==2){	//权限为2即admin级别的才能执行删除操作
			String path = request.getServletContext().getRealPath("/");
			imageInfoService.deleteImageAndFile(id,path);
			
			//日志记录
			UserInfo user = (UserInfo)request.getSession().getAttribute("user");
			ImageLog imageLog = new ImageLog();
			imageLog.setContent(1);					//1代表删除图片
	        imageLog.setEditorUserId(user.getUserId());
	        imageLog.setCreateTime(new Date());
	        imageLogService.save(imageLog);			//将日志信息保存到数据库
	        
	        request.getServletContext().setAttribute("isIndexLatest", false);
			
			return "redirect:list";
		}
		return null;
	}
	
	@RequestMapping("/multiDelete")
	public String multiDelete(Integer ids[],HttpServletRequest request,HttpSession session){
		
		if(((UserInfo)session.getAttribute("user")).getPermission()==2){	//只有权限为2(admin)级别的才能执行删除操作
			String path = request.getServletContext().getRealPath("/");
			imageInfoService.deleteImagesAndFiles(ids,path);
			
			//日志记录
			UserInfo user = (UserInfo)request.getSession().getAttribute("user");
			ImageLog imageLog = new ImageLog();
			imageLog.setContent(1);					//1代表修改图片
	        imageLog.setEditorUserId(user.getUserId());
	        imageLog.setCreateTime(new Date());
			imageLogService.save(imageLog);			//将日志信息保存到数据库
			
			request.getServletContext().setAttribute("isIndexLatest", false);
			
			return "redirect:list";
		}
		return null;
	}
	

	
	@RequestMapping(value = "/edit")
	public ModelAndView edit(Integer id,HttpSession session){
		if(((UserInfo)session.getAttribute("user")).getPermission()!=0){	//权限不为0(operator),都可以编辑
			ImageInfo imageInfo = imageInfoService.findById(id);
			List<ImageClassify> classLists = imageClassifyService.findAll();
			ModelAndView mnv = new ModelAndView();
			mnv.addObject("imageInfo", imageInfo);
			mnv.addObject("classLists", classLists);
			mnv.setViewName("image/imageEdit");
			return mnv;
		}
		return null;
	}
	
	@RequestMapping("/editSubmit")
	public void editSubmit(Integer id,Integer classId,String desp,HttpSession session,HttpServletResponse response) throws IOException{
		
		UserInfo user = (UserInfo) session.getAttribute("user");
		
		if(user.getPermission()!=0){	//权限不为0(operator),都可以编辑
			ImageInfo imageInfo = imageInfoService.findById(id);
			
			//图片修改信息
			ImageChange imageChange = new ImageChange();
			imageChange.setImageId(id);
			imageChange.setChangebName(imageInfo.getImageDespretion()); 	//修改前图像描述
			imageChange.setChangeContent(desp);								//修改后图像描述
			imageChange.setChangebClassify(imageInfo.getImageClassify().getImageClassifyId());//修改前类别ID
			imageChange.setChangeClassify(classId);							//修改后类别ID
			imageChange.setPermission(user.getPermission());				//操作人的权限
			imageChange.setCreateTime(new Date());							//修改时间
			imageChange.setEditorUserId(user.getUserId());					//修改人ID
			imageChange.setEditorUserName(user.getUserName());				//修改人用户名
			imageChangeService.save(imageChange);
			
			//修改图片
			imageInfo.setImageClassify(imageClassifyService.findById(classId));  
			imageInfo.setImageDespretion(desp);
			imageInfoService.update(imageInfo);				//存入数据库
			
			
			//日志记录
			ImageLog imageLog = new ImageLog();
			imageLog.setContent(2);					//2代表修改图片
	        imageLog.setEditorUserId(user.getUserId());
	        imageLog.setCreateTime(new Date());
	        imageLogService.save(imageLog);			//将日志信息保存到数据库
			
			response.sendRedirect("list");
		}
		
	}
	@RequestMapping("/singleUpload")
	public String uploadSingle(HttpServletRequest request,MultipartFile files) throws Exception{
		
		String originaFileName = files.getOriginalFilename();
		if(originaFileName!=null){
			String path = request.getServletContext().getRealPath("/");
			path = path.replace("\\", "/");
			
			String format = originaFileName.substring(originaFileName.lastIndexOf("."));
			Integer userId = ((UserInfo)request.getSession().getAttribute("user")).getUserId();		//当前操作者的ID
		
			Integer maxId = imageQueryInfoService.findMaxId();
			String filename = "";
			if(maxId!=null)
				filename = (maxId+1)+format;
			String address = "/upload/queryImages/"+filename;
			
			Date date = new Date();
			ImageQueryInfo imageQueryInfo = new ImageQueryInfo();
			imageQueryInfo.setCreateTime(date);
			imageQueryInfo.setEditorUserId(userId);
			imageQueryInfo.setImageUploadingAddress(address);
			imageQueryInfoService.save(imageQueryInfo);
			if(maxId==null){
				maxId = imageQueryInfo.getQueryId();
				filename = maxId+format;
				address = "/upload/queryImages/"+filename;
				imageQueryInfo.setImageUploadingAddress(address);
				imageQueryInfoService.update(imageQueryInfo);
			}
			String realPath = path+address;
            File localFile = new File(realPath);  
            
            if(!localFile.getParentFile().exists())
            	localFile.getParentFile().mkdirs();
            files.transferTo(localFile);  			//将图片保存到文件系统中
            
            IndexHelper.query(path+"/upload", imageQueryInfo.getQueryId()+format, imageQueryInfo.getQueryId().toString());
		}
		return null;
	}
	
	@RequestMapping("/upload")
	public @ResponseBody String upload(HttpServletRequest request,@RequestParam("files[]") ArrayList<MultipartFile> files,Integer classId[],String desp[]) throws Exception{
		
		String path = request.getServletContext().getRealPath("/");
		path = path.replace("\\", "/");
		SimpleDateFormat sdfNoSecond = new SimpleDateFormat("yyyyMMdd");
		
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		Integer userId = user.getUserId();		//当前操作者的ID
		
		Integer maxId = imageInfoService.findMaxId();
		List<ImageInfo> batch = new ArrayList<ImageInfo>();
		
		//上传日期
		Date date = new Date();
		String dateString = sdfNoSecond.format(date);
		
		List<File> filelist = new ArrayList<File>();
		for(int i=0;i<files.size();i++){
			MultipartFile file = files.get(i);
			//取得当前上传文件的文件名称  
            String originaFileName = file.getOriginalFilename();
            if(originaFileName!=""){
            	String format = originaFileName.substring(originaFileName.lastIndexOf(".")); //取得文件格式
            	//当前上传日期(不包括时分秒)
            	//文件名为ID号
            	String fileName = ImageNameGenerator.generator(maxId)+format; 
                //文件夹为/upload/Images/yyyyMMdd/
                String address = "/upload/Images/"+dateString+"/"+fileName;
                //图片信息的相关存储
                ImageInfo imageInfo = new ImageInfo();
                
                imageInfo.setImageAddress(address);
                imageInfo.setCreateTime(date);
                imageInfo.setEditorUserId(userId);
                imageInfo.setImageClassify(imageClassifyService.findById(classId[i]));
                imageInfo.setImageDespretion(desp[i]);
                imageInfo.setImageFormat(format);
                imageInfo.setImageRank(1);
                
                batch.add(imageInfo);
                
                maxId++;
                
                String realPath = path+address;
                File localFile = new File(realPath);  
                
                if(!localFile.getParentFile().exists())
                	localFile.getParentFile().mkdirs();
                file.transferTo(localFile);  			//将图片保存到文件系统中
                filelist.add(localFile);
            }
		}
		
		try{
			imageInfoService.bathInsert(batch);
		}catch (Exception e) {
			for(File f : filelist){
				f.delete();
			}
		}
        //图片日志的相关存储
        ImageLog imageLog = new ImageLog();
        imageLog.setContent(0);					//0代表添加图片
        imageLog.setEditorUserId(userId);
        imageLog.setCreateTime(new Date());
        imageLogService.save(imageLog);			//将日志信息保存到数据库
        
        request.getServletContext().setAttribute("isIndexLatest", false);
        
		return "true";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getAllClass")
	public @ResponseBody List<Map> getAllClass(){
		return imageClassifyService.findAllWithMap();
	}
	
	public ImageInfoService getImageInfoService() {
		return imageInfoService;
	}
	public void setImageInfoService(ImageInfoService imageInfoService) {
		this.imageInfoService = imageInfoService;
	}

	public ImageClassifyService getImageClassifyService() {
		return imageClassifyService;
	}

	public void setImageClassifyService(ImageClassifyService imageClassifyService) {
		this.imageClassifyService = imageClassifyService;
	}

	public ImageLogService getImageLogService() {
		return imageLogService;
	}

	public void setImageLogService(ImageLogService imageLogService) {
		this.imageLogService = imageLogService;
	}

	public ImageChangeService getImageChangeService() {
		return imageChangeService;
	}

	public void setImageChangeService(ImageChangeService imageChangeService) {
		this.imageChangeService = imageChangeService;
	}
	
	public ImageQueryInfoService getImageQueryInfoService() {
		return imageQueryInfoService;
	}
	public void setImageQueryInfoService(ImageQueryInfoService imageQueryInfoService) {
		this.imageQueryInfoService = imageQueryInfoService;
	}
	public ImageResultInfoService getImageResultInfoService() {
		return imageResultInfoService;
	}
	public void setImageResultInfoService(
			ImageResultInfoService imageResultInfoService) {
		this.imageResultInfoService = imageResultInfoService;
	}
	@RequestMapping("/imageSearch")
	public ModelAndView imageSearch(){
		ModelAndView mav = new ModelAndView("image/imageSearch");
		return mav;
	}
	
	
}
