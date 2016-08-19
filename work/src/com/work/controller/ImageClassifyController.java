package com.work.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.work.entity.ImageClassify;
import com.work.entity.UserInfo;
import com.work.service.ImageClassifyService;

@Controller
@RequestMapping("/imageclass")
public class ImageClassifyController {
	@Autowired
	private ImageClassifyService classifyService;

	@RequestMapping("list")
	public ModelAndView list(){
		List<ImageClassify> lists = classifyService.findAll();
		ModelAndView mnv = new ModelAndView("image/imageclassList");
		if(lists!=null&&lists.size()>0){
			mnv.addObject("lists", lists);
		}
		return mnv;
	}
	@RequestMapping("save")
	public String save(){
		return "image/imageclassAdd";
	}
	@RequestMapping("delete")  //暂时测试用，后期应该加入权限判断
	public String delete(Integer id,HttpSession session){
		if(((UserInfo)session.getAttribute("user")).getPermission()==2){ //只有权限为2(admin)级别的才能执行删除操作
			classifyService.delete(id);
			return "redirect:list";
		}
		return null;
	}
	@RequestMapping("edit")
	public ModelAndView edit(Integer id,HttpSession session){
		
		if(((UserInfo)session.getAttribute("user")).getPermission()!=0){	//权限不为0(operator),都可以编辑
			
			ImageClassify imageClassify = classifyService.findById(id);
			ModelAndView mnv = new ModelAndView("image/imageclassEdit");
			mnv.addObject("imageClassify", imageClassify);
			return mnv;
		}
		return null;
	}
	@RequestMapping("editSubmit")
	public String editSubmit(Integer id,String name,String desp,HttpSession session){
		
		UserInfo user = (UserInfo)session.getAttribute("user");
		if(user.getPermission()!=0){		//权限不为0(operator),都可以编辑
			
			ImageClassify imageClassify = classifyService.findById(id);
			if(!imageClassify.getImageClassifyName().equals(name))
				if(classifyService.isClassnameExists(name)){
					session.setAttribute("clas_error", "'"+name+"'已存在！");
					return "redirect:edit?id="+imageClassify.getImageClassifyId();
				}
					
			imageClassify.setImageClassifyName(name);
			imageClassify.setEditorUserId(user.getUserId());   
			
			if(desp!=null)
				imageClassify.setImageClassifyDespretion(desp);
			classifyService.update(imageClassify);
			
			session.removeAttribute("clas_error");
			
			return "redirect:list";
		}
		return null;
	}
	@RequestMapping("saveSubmit")
	public String saveSubmit(String name,String desp,HttpSession session){
		
		if(!classifyService.isClassnameExists(name)){
			ImageClassify classify = new ImageClassify();
			classify.setCreateTime(new Date());
			if(desp!=null&&desp!="")
				classify.setImageClassifyDespretion(desp);
			classify.setImageClassifyName(name);
			classify.setEditorUserId(((UserInfo)session.getAttribute("user")).getUserId());			
			classifyService.save(classify);
			session.removeAttribute("clas_error");
			return "redirect:list";
		}
		session.setAttribute("clas_error", "'"+name+"'已存在！");
		return "redirect:save";
	}
	
	@RequestMapping("validateClassName")
	public @ResponseBody String validateClassName(String classname){
		String msg = null;
		if(classifyService.isClassnameExists(classname)){
			msg = "类别名称已存在！";
		}
		return msg;
	}
	
	@RequestMapping("validateClassName1")
	public @ResponseBody String validateClassName1(String classname,String orginClassname){
		String msg = null;
		if(!classname.equals(orginClassname)&&classifyService.isClassnameExists(classname)){
			msg = "类别名称已存在！";
		}
		return msg;
	}
	public ImageClassifyService getClassifyService() {
		return classifyService;
	}

	public void setClassifyService(ImageClassifyService classifyService) {
		this.classifyService = classifyService;
	}
	
}
