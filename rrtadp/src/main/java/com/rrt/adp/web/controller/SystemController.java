package com.rrt.adp.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.Comments;
import com.rrt.adp.model.Page;
import com.rrt.adp.model.PersonUser;
import com.rrt.adp.service.CommentsService;
import com.rrt.adp.service.UserService;
import com.rrt.adp.util.MessageContext;
import com.rrt.adp.web.RestResult;
import com.rrt.adp.web.RestSecurity;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sys")
public class SystemController {
	
	@Resource
	private CommentsService commentsService;
	@Resource
	private UserService userService;
	
	@ApiOperation("添加意见评论")
	@RequestMapping(value="/comments/new", method=RequestMethod.POST)
	public RestResult addComments(Comments comments, HttpServletRequest request){
		comments.setType(Comments.TYPE_PLATFORM);
		Account account = RestSecurity.getSessionAccount(request);
		if(commentsService.addComments(account, comments)){
			return RestResult.defaultSuccessResult();
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("管理员删除意见评论")
	@RequestMapping(value="/comments/delete", method=RequestMethod.POST)
	public RestResult deleteComments(String commentsId, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(commentsService.deleteComments(account, commentsId)){
			return RestResult.defaultSuccessResult();
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("分页获取意见评论，分页参数pageNum， pageSize")
	@RequestMapping(value="/comments/page", method={RequestMethod.GET, RequestMethod.POST})
	public RestResult pageComments(Comments comments, Page<Comments> page){
		comments.setType(Comments.TYPE_PLATFORM);
		Page<Comments> pages = commentsService.getCommentsPage(comments, page);
		if(null!=pages){
			return RestResult.defaultSuccessResult(pages);
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("联系我们")
	@RequestMapping(value="/contacts", method=RequestMethod.GET)
	public RestResult platformContacts(){
		PersonUser admin = userService.getPersonUser("admin");
		Map<String, String> map = new HashMap<>();
		if(null!=admin){
			map.put("name", admin.getName());
			map.put("phone", admin.getPhone());
		}
		return RestResult.defaultSuccessResult(map);
	}
	

}
