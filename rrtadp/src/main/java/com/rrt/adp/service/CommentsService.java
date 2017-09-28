package com.rrt.adp.service;

import com.rrt.adp.model.Account;
import com.rrt.adp.model.Comments;
import com.rrt.adp.model.Page;

public interface CommentsService {
	
	boolean addComments(Account account, Comments comments);
	
	boolean deleteComments(Account account, String commentsId);
	
	Page<Comments> getCommentsPage(Comments comments, Page<Comments> page);

}
