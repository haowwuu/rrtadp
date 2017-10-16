package com.rrt.adp.dao.support;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.rrt.adp.dao.ObjectFileDao;
import com.rrt.adp.model.Advertisement;
import com.rrt.adp.model.Comments;
import com.rrt.adp.model.Page;
import com.rrt.adp.service.CommentsService;

public class CommentsDaoImplTest {
	
	@Resource
	private CommentsService commentsService;
	@Resource
	private ObjectFileDao objFileDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testInsertComments() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteComments() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectComments() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountComments() {
		String[] objectIds = {"AD1501161837174", "AD1500895029525"};
		List<String> ids = Arrays.asList(objectIds);
		ids.parallelStream().map(String::toLowerCase).forEach(System.out::println);
		List<Advertisement> advertisements = new ArrayList<>();
		advertisements.parallelStream().forEach((ad)->{Comments comments = new Comments();
		comments.setReplayTo(ad.getId());
		comments.setType(Comments.TYPE_ZAN);
		ad.setComments(commentsService.getCommentsPage(comments, new Page<>(1, 20)));
		if(Advertisement.TYPE_VIDEO.equals(ad.getType())){
			ad.setCoverUrl(objFileDao.selectObjFileUrl(ad.getId(), Advertisement.ATTR_COVER, 0));
		}});
		
	}

}
