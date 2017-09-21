package com.rrt.adp.util;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

/**
 * @Description TODO
 * @author Wuwuhao
 * @date 2017年9月18日
 * 
 */
public class PaginationJdbcTemplate extends JdbcTemplate {
	
	private static final String MYSQL = "mysql";
	private static final String ORACLE = "oracle";
	
	private String dbType;
	
	public <T> List<T> queryPagination (String sql, Object[] args, 
			int pageNum, int pageSize, RowMapper<T> mapper) {
		if(!StringUtils.hasText(sql)) {
			throw new IllegalArgumentException("queryPagination [sql] null");
		}
		String pageSql = buildPaginationSql(sql, pageNum, pageSize);
		if(null==args||args.length<1){
			return this.query(pageSql, mapper);
		}
		return this.query(pageSql, args, mapper);
	}
	
	public <T> List<T> queryPagination (String sql, int pageNum, 
			int pageSize, RowMapper<T> mapper) {
		return this.queryPagination(sql, null, pageNum, pageSize, mapper);
	}
	
	private String buildPaginationSql(String orginSql, int pageNum, int pageSize){
		if(pageNum<1||pageSize<0){
			return orginSql;
		}
		if(pageSize>200){
			pageSize = 200;
		}
		int start = (pageNum-1)*pageSize;
		StringBuilder pageSql = new StringBuilder();
		switch (getdBType().toLowerCase()) {
		case MYSQL: 
			pageSql.append(orginSql).append(" LIMIT ").append(start).append(",").append(pageSize);
			break;
		case ORACLE:
			pageSql.append("SELECT * FROM (SELECT a.*, ROWNUM rn FROM (");
			pageSql.append(orginSql);
			pageSql.append(" ) a WHERE ROWNUM <= ").append(pageNum*pageSize);
			pageSql.append(") WHERE rn >= ").append(start);
		default:
			throw new IllegalArgumentException("unsupported database type ["+this.dbType+"]");
		}
		
		return pageSql.toString();
	}

	public String getdBType() {
		if(null!=dbType){
			return dbType;
		}
		Object driver = null;
		try {
			driver = Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (ClassNotFoundException e) {
			
		}
		if(null!=driver){
			this.dbType = ORACLE;
			return this.dbType;
		}
		try {
			driver = Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			
		}
		if(null!=driver){
			this.dbType = MYSQL;
		}
		return this.dbType;
	}

	public void setdBType(String dBType) {
		this.dbType = dBType;
	}

}
