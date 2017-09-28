package com.rrt.adp.dao;

import java.util.List;

import com.rrt.adp.model.Comments;
import com.rrt.adp.model.Page;

public interface CommentsDao {
	
	int insertComments(Comments comments);
	
	List<Comments> selectComments(Comments comments, Page<?> page);
	
	int countComments(Comments comments);
	
	int deleteComments(String commentsId);

}
