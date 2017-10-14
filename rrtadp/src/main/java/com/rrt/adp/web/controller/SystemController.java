package com.rrt.adp.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
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
import com.rrt.adp.util.PushUtil;
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
	@Resource
	private PushUtil pushUtil;
	
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
	
	@ApiOperation("点赞接口, 为广告点赞 传入广告id")
	@RequestMapping(value="/comments/zan", method=RequestMethod.POST)
	public RestResult zan(String adId, HttpServletRequest request){
		if(!StringUtils.hasText(adId)){
			return RestResult.defaultSuccessResult();
		}
		Account account = RestSecurity.getSessionAccount(request);
		Comments comments = new Comments();
		comments.setType(Comments.TYPE_ZAN);
		comments.setReplayTo(adId);
		comments.setAccount(account.getAccount());
		if(commentsService.addComments(account, comments)){
			return RestResult.defaultSuccessResult();
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("分页获取点赞数和点赞用户，分页参数pageNum， pageSize")
	@RequestMapping(value="/comments/zan/page", method={RequestMethod.GET, RequestMethod.POST})
	public RestResult pageComments(String adId, Page<Comments> page){
		if(!StringUtils.hasText(adId)){
			return RestResult.defaultSuccessResult(page);
		}
		Comments comments = new Comments();
		comments.setReplayTo(adId);
		comments.setType(Comments.TYPE_ZAN);
		Page<Comments> pages = commentsService.getCommentsPage(comments, page);
		if(null!=pages){
			return RestResult.defaultSuccessResult(pages);
		}
		return RestResult.defaultFailResult(MessageContext.getMsg());
	}
	
	@ApiOperation("消息推送接口， 如果audienceAccount为空，则广播消息")
	@RequestMapping(value="/push", method=RequestMethod.POST)
	public RestResult pushMessage(String audienceAccount, String alert, String title, HttpServletRequest request){
		Account account = RestSecurity.getSessionAccount(request);
		if(account.isAdmin()){
			pushUtil.push(audienceAccount, alert, title);
		}
		return RestResult.defaultSuccessResult();
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
