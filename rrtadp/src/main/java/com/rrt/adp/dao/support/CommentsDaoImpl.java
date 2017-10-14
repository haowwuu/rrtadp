package com.rrt.adp.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.rrt.adp.dao.CommentsDao;
import com.rrt.adp.model.Comments;
import com.rrt.adp.model.Page;
import com.rrt.adp.util.PaginationJdbcTemplate;
import com.rrt.adp.util.SequenceGenerator;

@Repository
public class CommentsDaoImpl implements CommentsDao {
	
	@Resource
	private PaginationJdbcTemplate jdbcTemplate;

	@Override
	public int insertComments(Comments comments) {
		if(null==comments){
			return 0;
		}
		comments.setId(SequenceGenerator.next());
		comments.setCreateTime(new Date());
		return this.jdbcTemplate.update("insert into rrt_comments (id, type, content, replay_to, account, create_time) "
				+ "values (?, ?, ?, ?, ?, ?)",
				new Object[]{
						comments.getId(),
						comments.getType(),
						comments.getContent(),
						comments.getReplayTo(),
						comments.getAccount(),
						comments.getCreateTime()
				});
	}
	
	@Override
	public int deleteComments(String commentsId) {
		if(null==commentsId){
			return 0;
		}
		return this.jdbcTemplate.update("delete from rrt_comments where id = ?", commentsId);
	}
	
	@Override
	public List<Comments> selectComments(Comments comments, Page<?> page) {
		Object[] select = buildSelectSql("select * from rrt_comments",comments);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.queryPagination(sql, values, 
				page.getPageNum(), page.getPageSize(), new CommentsMapper());
	}
	
	@Override
	public int countComments(Comments comments) {
		Object[] select = buildSelectSql("select count(*) from rrt_comments", comments);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.queryForObject(sql, Integer.class, values);
	}
	
	private static final class CommentsMapper implements RowMapper<Comments> {

	    public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Comments comments = new Comments();
	        comments.setId(rs.getString("id"));
	        comments.setContent(rs.getString("content"));
	        comments.setType(rs.getString("type"));
	        comments.setReplayTo(rs.getString("replay_to"));
	        comments.setAccount(rs.getString("account"));
	        comments.setCreateTime(rs.getDate("create_time"));
	        return comments;
	    }
	}

    private Object[] buildSelectSql(String selectOrCount, Comments comments) {
		
		StringBuilder select = new StringBuilder();
		select.append(selectOrCount);
		select.append(" where 1");
		if(null==comments){
			return new Object[]{select.toString()};
		}
		Object[] values = new Object[20];
		int i = 0;
		
		if(StringUtils.hasText(comments.getId())){
			select.append(" and id = ?");
			values[i] = comments.getId();
			i++;
		}
		if(StringUtils.hasText(comments.getType())){
			select.append(" and type = ?");
			values[i] = comments.getType();
		    i++;
		}
		if(StringUtils.hasText(comments.getContent())){
			select.append(" and content like ?");
			values[i] = "%"+comments.getContent()+"%";
			i++;
		}
		if(StringUtils.hasText(comments.getReplayTo())){
			select.append(" and replay_to = ?");
			values[i] = comments.getReplayTo();
			i++;
		}
		if(StringUtils.hasText(comments.getAccount())){
			select.append(" and account = ?");
			values[i] = comments.getAccount();
			i++;
		}
		if(null!=comments.getCreateTime()){
			select.append(" and create_time >= ?");
			values[i] = comments.getCreateTime();
			i++;
		}
		values[i] = select.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}

}
