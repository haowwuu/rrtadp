package com.rrt.adp.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.rrt.adp.dao.ObjectFileDao;
import com.rrt.adp.model.ObjectFile;
import com.rrt.adp.util.SequenceGenerator;

@Repository
public class ObjectFileDaoImpl implements ObjectFileDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insertObjectFile(ObjectFile objFile) {
		if(null==objFile){
			return 0;
		}
		objFile.setId(SequenceGenerator.next());
		objFile.setCreateTime(new Date());
		return this.jdbcTemplate.update("insert into rrt_file (id, obj_id, obj_attr, file_idx, file_name, file_url, "
				+ "create_tme) values (?, ?, ?, ?, ?, ?, ?)",
				new Object[]{
						objFile.getId(),
						objFile.getObjectId(),
						objFile.getObjectAttr(),
						objFile.getFileIndex(),
						objFile.getFileName(),
						objFile.getFileUrl(),
						objFile.getCreateTime()
				});
	}

	@Override
	public int updateObjectFile(ObjectFile objectFile) {
		Object[] update = buildUpdateSql(objectFile);
		if(null==update){
			return 0;
		}
		String sql = (String)update[update.length-1];
		Object[] values = new Object[update.length-1];
		System.arraycopy(update, 0, values, 0, update.length-1);
		return this.jdbcTemplate.update(sql, values);
	}
	
	@Override
	public int replaceObjectFile(ObjectFile objectFile) {
		int update = updateObjectFile(objectFile);
		if(update<1){
			return insertObjectFile(objectFile);
		}
		return update;
	}

	@Override
	public int deleteObjectFile(String objId, String objAttrName, int fileIdx) {
		if(null==objId||null==objAttrName){
			return 0;
		}
		return this.jdbcTemplate.update("delete from rrt_file where obj_id = ? and obj_attr = ? and file_idx = ?", 
				objId, objAttrName, fileIdx);
	}

	@Override
	public List<String> selectObjFileUrlList(String objId, String objAttrName) {
		if(null==objId||null==objAttrName){
			return null;
		}
		return this.jdbcTemplate.query(
				"select file_url from rrt_file where obj_id = ? and obj_attr = ?", 
				new Object[]{objId, objAttrName}, new RowMapper<String>(){
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("file_url");
					}
				});
	}

	@Override
	public String selectObjFileUrl(String objId, String objAttrName, int fileIdx) {
		if(null==objId||null==objAttrName){
			return null;
		}
		List<String> retn = this.jdbcTemplate.query("select file_url from rrt_file "
				+ "where obj_id = ? and obj_attr = ? and file_idx = ?", 
				new Object[]{objId, objAttrName, fileIdx}, new RowMapper<String>(){
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("file_url");
					}
				});
		if(null==retn||retn.size()<1){
			return null;
		}
		return retn.get(0);
	}
	
	@Override
	public ObjectFile selectObjFile(String objId, String objAttrName, int fileIdx) {
		if(null==objId||null==objAttrName){
			return null;
		}
		List<ObjectFile> files = this.jdbcTemplate.query(
				"select file_url from rrt_file where obj_id = ? and obj_attr = ? and file_idx = ?", 
				new Object[]{objId, objAttrName, fileIdx}, new ObjFileMapper());
		if(null==files||files.size()<1){
			return null;
		}
		return files.get(0);
	}
	
	private static final class ObjFileMapper implements RowMapper<ObjectFile> {

	    public ObjectFile mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	ObjectFile objFile = new ObjectFile();
	    	objFile.setId(rs.getString("id"));
	    	objFile.setObjectId(rs.getString("obj_id"));
	    	objFile.setObjectAttr(rs.getString("obj_attr"));
	    	objFile.setFileIndex(rs.getInt("file_idx"));
	    	objFile.setFileName(rs.getString("file_name"));
	    	objFile.setFileUrl(rs.getString("file_url"));
	    	objFile.setCreateTime(rs.getDate("create_time"));
	    	objFile.setUpdateTime(rs.getDate("update_time"));
	        return objFile;
	    }
	}
	
	private Object[] buildUpdateSql(ObjectFile objectFile) {
		if(null==objectFile||null==objectFile.getObjectId()||null==objectFile.getObjectAttr()){
			return null;
		}
		StringBuilder update = new StringBuilder();
		Object[] values = new Object[30];
		int i = 0;
		update.append("update rrt_file set update_time = ?");
		values[i] = new Date(); 
		i++;
		if(StringUtils.hasText(objectFile.getFileName())){
			update.append(",file_name = ?");
			values[i] = objectFile.getFileName();
			i++;
		}
		if(StringUtils.hasText(objectFile.getFileUrl())){
			update.append(",file_url = ?");
			values[i] = objectFile.getFileUrl();
			i++;
		}
		values[i] = objectFile.getObjectId();
		i++;
		values[i] = objectFile.getObjectAttr();
		i++;
		values[i] = objectFile.getFileIndex();
		i++;

		update.append(" where obj_id = ? and obj_attr = ? and file_idx = ?");
		values[i] = update.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}

}
