package com.rrt.adp.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.rrt.adp.dao.AdvertisementDao;
import com.rrt.adp.model.Advertisement;
import com.rrt.adp.model.Page;
import com.rrt.adp.util.PaginationJdbcTemplate;
import com.rrt.adp.util.SequenceGenerator;

/**
 * @Description TODO
 * @author Wuwuhao
 * @date 2017年9月16日
 * 
 */
@Repository
public class AdvertisementDaoImpl implements AdvertisementDao {
	
	@Resource
	private PaginationJdbcTemplate jdbcTemplate;
	
	@Override
	public int insertAd(Advertisement ad) {
		if(null==ad){
			return 0;
		}
		ad.setId(Advertisement.PREFIX_ADVERTISEMENT+SequenceGenerator.next());
		ad.setCreateTime(new Date());
		ad.setState(Advertisement.STATE_NEW);
		return this.jdbcTemplate.update("insert into rrt_ad (id, create_time, update_time, "
				+ "title, type, content, state, content_url, time_in_second, ad_company_id, owner) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[]{
						ad.getId(),
						ad.getCreateTime(),
						ad.getUpdateTime(),
						ad.getTitle(),
						ad.getType(),
						ad.getContent(),
						ad.getState(),
						ad.getContentUrl(),
						ad.getTimeInSecond(),
						ad.getAdCompanyId(),
						ad.getOwner()
				});
	}

	@Override
	public int deleteAd(String adId) {
		if(null==adId){
			return 0;
		}
		return this.jdbcTemplate.update("delete from rrt_ad where id = ?", adId);
	}

	@Override
	public int updateAd(Advertisement ad) {
		Object[] update = buildUpdateSql(ad);
		if(null==update){
			return 0;
		}
		String sql = (String)update[update.length-1];
		Object[] values = new Object[update.length-1];
		System.arraycopy(update, 0, values, 0, update.length-1);
		return this.jdbcTemplate.update(sql, values);
	}

	@Override
	public Advertisement selectAd(String adId) {
		if(null==adId){
			return null;
		}
		List<Advertisement> ads = this.jdbcTemplate.query("select * from rrt_ad where id = ?",  
				new Object[]{adId},  new AdMapper());
		if(null==ads||ads.size()<1){
			return null;
		}
		return ads.get(0);
	}

	@Override
	public List<Advertisement> selectAdList(Advertisement ad) {
		Object[] select = buildSelectSql("select * from rrt_ad as ad", ad);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.query(sql, values, new AdMapper());
	}

	@Override
	public List<Advertisement> selectUserAdList(String account) {
		if(!StringUtils.hasText(account)) {
			return new ArrayList<>();
		}
		Advertisement ad = new Advertisement();
		ad.setId(null);
		ad.setOwner(account);
		return selectAdList(ad);
	}
	
	@Override
	public List<Advertisement> selectAdList(Advertisement ad, Page<?> page) {
		Object[] select = buildSelectSql("select * from rrt_ad as ad",ad);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.queryPagination(sql, values, 
				page.getPageNum(), page.getPageSize(), new AdMapper());
	}
	
	@Override
	public List<Advertisement> selectAdListOrderByOrder(Advertisement ad, Page<?> page) {
		String sql = "SELECT ad.id, ad.create_time, ad.update_time, ad.type, ad.title, ad.content,"
				+ " ad.content_url, ad.state, ad.time_in_second, ad.ad_company_id, ad.owner, count(*) as num"
				+ " FROM rrt_ad as ad left join rrt_order as ord on  (ad.id = ord.ad_id)";
		Object[] select = buildSelectSql(sql, ad);
		sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.queryPagination(sql+" group by ad.id order by num desc", values, 
				page.getPageNum(), page.getPageSize(), new AdMapper());
	}

	@Override
	public int countAd(Advertisement ad) {
		Object[] select = buildSelectSql("select count(*) from rrt_ad as ad", ad);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.queryForObject(sql, Integer.class, values);
	}
	
	private static final class AdMapper implements RowMapper<Advertisement> {

	    public Advertisement mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Advertisement ad = new Advertisement();
	        ad.setId(rs.getString("id"));
	        ad.setCreateTime(rs.getDate("create_time"));
	        ad.setUpdateTime(rs.getDate("update_time"));
	        ad.setTitle(rs.getString("title"));
	        ad.setType(rs.getString("type"));
			ad.setState(rs.getString("state"));
			ad.setContent(rs.getString("content"));
			ad.setContentUrl(rs.getString("content_url"));
		    ad.setTimeInSecond(rs.getInt("time_in_second"));
		    ad.setAdCompanyId(rs.getString("ad_company_id"));
		    ad.setOwner(rs.getString("owner"));

	        return ad;
	    }
	}
	
	private Object[] buildSelectSql(String selectOrCount, Advertisement ad) {
		
		StringBuilder select = new StringBuilder();
		select.append(selectOrCount);
		select.append(" where 1");
		if(null==ad){
			return new Object[]{select.toString()};
		}
		Object[] values = new Object[20];
		int i = 0;
		if(StringUtils.hasText(ad.getId())){
			select.append(" and ad.id = ?");
			values[i] = ad.getId();
		    i++;
		}
		if(StringUtils.hasText(ad.getTitle())){
			select.append(" and ad.title like ?");
			values[i] = "%"+ad.getTitle()+"%";
			i++;
		}
		if(StringUtils.hasText(ad.getType())){
			select.append(" and ad.type = ?");
			values[i] = ad.getType();
		    i++;
		}
		if(StringUtils.hasText(ad.getState())){
			select.append(" and ad.state = ?");
			values[i] = ad.getState();
			i++;
		}
		if(StringUtils.hasText(ad.getContent())){
			select.append(" and ad.content like ?");
			values[i] = "%"+ad.getContent()+"%";
			i++;
		}
		if(StringUtils.hasText(ad.getAdCompanyId())){
			select.append(" and ad.ad_company_id = ?");
			values[i] = ad.getAdCompanyId();
			i++;
		}
		if(StringUtils.hasText(ad.getOwner())){
			select.append(" and ad.owner = ?");
			values[i] = ad.getOwner();
			i++;
		}
		values[i] = select.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}
	
	private Object[] buildUpdateSql(Advertisement ad) {
		if(null==ad||null==ad.getId()){
			return null;
		}
		StringBuilder update = new StringBuilder();
		Object[] values = new Object[20];
		int i = 0;
		update.append("update rrt_ad set update_time = ?");
		values[i] = new Date(); 
		i++;
		if(StringUtils.hasText(ad.getTitle())){
			update.append(",title = ?");
			values[i] = ad.getTitle();
			i++;
		}
		if(StringUtils.hasText(ad.getType())){
			update.append(",type = ?");
			values[i] = ad.getType();
			i++;
		}
		if(StringUtils.hasText(ad.getState())){
			update.append(",state = ?");
			values[i] = ad.getState();
			i++;
		}
		if(StringUtils.hasText(ad.getContent())){
			update.append(",content = ?");
			values[i] = ad.getContent();
			i++;
		}
		if(StringUtils.hasText(ad.getContentUrl())){
			update.append(",content_url = ?");
			values[i] = ad.getContentUrl();
			i++;
		}
		if(ad.getTimeInSecond()>0){
			update.append(",time_in_second = ?");
			values[i] = ad.getTimeInSecond();
			i++;
		}
		if(StringUtils.hasText(ad.getAdCompanyId())){
			update.append(",ad_company_id = ?");
			values[i] = ad.getAdCompanyId();
			i++;
		}
		if(StringUtils.hasText(ad.getOwner())){
			update.append(",owner = ?");
			values[i] = ad.getOwner();
			i++;
		}
		
		values[i] = ad.getId();
		i++;
		
		update.append(" where id = ?");
		values[i] = update.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}

}
