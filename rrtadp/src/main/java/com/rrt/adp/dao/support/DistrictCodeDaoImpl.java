package com.rrt.adp.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rrt.adp.dao.DistrictCodeDao;
import com.rrt.adp.model.DistrictCode;

@Repository
public class DistrictCodeDaoImpl implements DistrictCodeDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<DistrictCode> selectTopLevel() {
		return this.jdbcTemplate.query(
				"select * from district_code where level = 0",  
				 new DistrictMapper());
	}

	@Override
	public List<DistrictCode> selectChild(int parentId) {
		return this.jdbcTemplate.query(
				"select * from district_code where parent_id = ?",  
				 new Object[]{parentId},new DistrictMapper());
	}

	@Override
	public DistrictCode selectByCityCode(String cityCode) {
		List<DistrictCode> districts = this.jdbcTemplate.query(
				"select * from district_code where city_code = ? order by id asc limit 1",  
				 new Object[]{cityCode},new DistrictMapper());
		if(null==districts||districts.size()<1){
			return null;
		}
		return districts.get(0);
	}
	
	private static final class DistrictMapper implements RowMapper<DistrictCode> {

	    public DistrictCode mapRow(ResultSet rs, int rowNum) throws SQLException {
	        DistrictCode district = new DistrictCode();
	        district.setId(rs.getInt("id"));
	        district.setParentId(rs.getInt("parent_id"));
	        district.setLevel(rs.getInt("level"));
	        district.setAreaCode(rs.getString("area_code"));
	        district.setZipCode(rs.getString("zip_code"));
	        district.setCityCode(rs.getString("city_code"));
	        district.setShortName(rs.getString("short_name"));
	        district.setName(rs.getString("name"));
	        district.setMergerName(rs.getString("merger_name"));
	        district.setPinyin(rs.getString("pinyin"));
	        district.setLng(rs.getDouble("lng"));
	        district.setLat(rs.getDouble("lat"));
	        
	        return district;
	    }
	}

}
