package com.rrt.adp.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import com.rrt.adp.dao.CodeDao;
import com.rrt.adp.model.Code;
import com.rrt.adp.util.PaginationJdbcTemplate;
import com.rrt.adp.util.SequenceGenerator;

@Repository
public class CodeDaoImpl implements CodeDao {

	@Resource
	private PaginationJdbcTemplate jdbcTemplate;
	
	@Override
	public int insertCode(Code code) {
		if(null==code){
			return 0;
		}
		code.setId(SequenceGenerator.next());
		return this.jdbcTemplate.update("insert into code_dictionary (id, code, type, name) "
				+ "values (?, ?, ?, ?)",
				new Object[]{
						code.getId(),
						code.getCode(),
						code.getType(),
						code.getName()
				});
	}

	@Override
	public int updateCode(Code code) {
		Object[] update = buildUpdateSql(code);
		if(null==update){
			return 0;
		}
		String sql = (String)update[update.length-1];
		Object[] values = new Object[update.length-1];
		System.arraycopy(update, 0, values, 0, update.length-1);
		return this.jdbcTemplate.update(sql, values);
	}

	@Override
	public int deleteCode(String codeId) {
		if(null==codeId){
			return 0;
		}
		return this.jdbcTemplate.update("delete from code_dictionary where id = ?", codeId);
	}

	@Override
	public List<Code> selectCode(Code code) {
		Object[] select = buildSelectSql("select id, code, name from code_dictionary", code);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.query(sql, values, new CodeMapper());
	}
	
	private static final class CodeMapper implements RowMapper<Code> {

	    public Code mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Code code = new Code();
	        code.setId(rs.getString("id"));
	        code.setCode(rs.getString("code"));
	       // code.setType(rs.getString("type"));
	        code.setName(rs.getString("name"));
	        return code;
	    }
	}
	
	private Object[] buildSelectSql(String selectOrCount, Code code) {
		
		StringBuilder select = new StringBuilder();
		select.append(selectOrCount);
		select.append(" where 1");
		if(null==code){
			return new Object[]{select.toString()};
		}
		Object[] values = new Object[20];
		int i = 0;
		
		if(StringUtils.hasText(code.getId())){
			select.append(" and id = ?");
			values[i] = code.getId();
			i++;
		}
		if(StringUtils.hasText(code.getType())){
			select.append(" and type = ?");
			values[i] = code.getType();
		    i++;
		}
		if(StringUtils.hasText(code.getCode())){
			select.append(" and code = ?");
			values[i] = code.getCode();
			i++;
		}
		if(StringUtils.hasText(code.getName())){
			select.append(" and name = ?");
			values[i] = code.getName();
			i++;
		}
		values[i] = select.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}
	
	private Object[] buildUpdateSql(Code code) {
		if(null==code||null==code.getId()){
			return null;
		}
		StringBuilder update = new StringBuilder();
		Object[] values = new Object[20];
		int i = 0;
		if(StringUtils.hasText(code.getCode())){
			update.append(",code = ?");
			values[i] = code.getCode();
			i++;
		}
		if(StringUtils.hasText(code.getType())){
			update.append(",type = ?");
			values[i] = code.getType();
			i++;
		}
		if(StringUtils.hasText(code.getName())){
			update.append(",name = ?");
			values[i] = code.getName();
			i++;
		}
		if(update.indexOf(",")<0){
			return null;
		}
		update.deleteCharAt(update.indexOf(","));
		update.insert(0, "update code_dictionary set ");
		
		values[i] = code.getId();
		i++;
		
		update.append(" where id = ?");
		values[i] = update.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}

}
