package com.rrt.adp.service.support;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rrt.adp.dao.CommentsDao;
import com.rrt.adp.model.Account;
import com.rrt.adp.model.Comments;
import com.rrt.adp.model.Page;
import com.rrt.adp.service.CommentsService;
import com.rrt.adp.util.MessageContext;
import com.rrt.adp.util.MessageUtil;

@Service
public class CommentsServiceImpl implements CommentsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CodeServiceImpl.class);
	
	@Resource
	private CommentsDao commentsDao;
	@Resource
	private MessageUtil msgUtil;

	@Override
	public boolean addComments(Account account, Comments comments) {
		if(null==account||null==account.getAccount()||
				null==comments||null==comments.getContent()){
			return false;
		}
		try{
			commentsDao.insertComments(comments);
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("[{}] addComments comments [{}] exception [{}]", account, comments, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteComments(Account account, String commentsId) {
		if(!Account.isAdmin(account)){
			MessageContext.setMsg(msgUtil.get("permission.deny"));
			return false;
		}
		try{
			commentsDao.deleteComments(commentsId);
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("[{}] deleteComments code [{}] exception [{}]", account, commentsId, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Page<Comments> getCommentsPage(Comments comments, Page<Comments> page) {
		CompletableFuture<List<Comments>> commentsfuture = new CompletableFuture<>();
		new Thread(() -> {
			try{
				List<Comments> commentsList = commentsDao.selectComments(comments, page);
				commentsfuture.complete(commentsList);
			}catch (Exception e) {
				LOGGER.error("commentsDao.selectComments comments[{}] page[{}] exception [{}]", comments, page, e.getMessage());
				commentsfuture.completeExceptionally(e);
			}
		}).start();
		
		try{
			page.setTotal(commentsDao.countComments(comments));
			page.setList(commentsfuture.get());
		}catch (Exception e) {
			MessageContext.setMsg(msgUtil.get("db.exception"));
			LOGGER.error("commentsDao.countComments comments[{}] page[{}] exception [{}]", comments, page, e.getMessage());
			return null;
		} 
		
		return page;
	}

}
