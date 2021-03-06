package com.rrt.adp.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import com.rrt.adp.dao.MediaDeviceDao;
import com.rrt.adp.model.MediaDevice;
import com.rrt.adp.model.Page;
import com.rrt.adp.util.PaginationJdbcTemplate;
import com.rrt.adp.util.SequenceGenerator;

@Repository
public class MediaDeviceDaoImpl implements MediaDeviceDao {
	
	@Resource
	private PaginationJdbcTemplate jdbcTemplate;
	
	@Override
	public int insertDevice(MediaDevice device) {
		if(null==device){
			return 0;
		}
		device.setId(MediaDevice.PREFIX_MEDIA_DEVICE+SequenceGenerator.next());
		device.setCreateTime(new Date());
		device.setState(MediaDevice.STATE_NEW);
		return this.jdbcTemplate.update("insert into rrt_media_device (id, create_time, update_time, "
				+ "device_type, device_status, base_price, key_words, name, description, play_id, foreign_id, serial_number, "
				+ "state, play_time, play_frequency, lng, lat, district_code, address, owner) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[]{
						device.getId(),
						device.getCreateTime(),
						device.getUpdateTime(),
						device.getDeviceType(),
						device.getDeviceStatus(),
						device.getBasePrice(),
						device.getKeyWords(),
						device.getName(),
						device.getDescription(),
						device.getPlayId(),
						device.getForeignId(),
						device.getSerialNumber(),
						device.getState(),
						device.getPlayTime(),
						device.getPlayFrequency(),
						device.getLng(),
						device.getLat(),
						device.getDistrictCode(),
						device.getAddress(),
						device.getOwner()
				});
	}

	@Override
	public int deleteDevice(String deviceId) {
		if(null==deviceId){
			return 0;
		}
		return this.jdbcTemplate.update("delete from rrt_media_device where id = ?", deviceId);
	}

	@Override
	public int updateDevice(MediaDevice device) {
		Object[] update = buildUpdateSql(device);
		if(null==update){
			return 0;
		}
		String sql = (String)update[update.length-1];
		Object[] values = new Object[update.length-1];
		System.arraycopy(update, 0, values, 0, update.length-1);
		return this.jdbcTemplate.update(sql, values);
	}

	@Override
	public MediaDevice selectDevice(String deviceId) {
		if(!StringUtils.hasText(deviceId)){
			return null;
		}
		MediaDevice device = new MediaDevice();
		device.setId(deviceId);
		List<MediaDevice> devices = selectDeviceList(device);
		if(null==devices||devices.size()==0){
			return null;
		}
		return devices.get(0);
	}

	@Override
	public List<MediaDevice> selectUserDeviceList(String account) {
		if(!StringUtils.hasText(account)){
			return null;
		}
		MediaDevice device = new MediaDevice();
		device.setOwner(account);
		return selectDeviceList(device);
	}

	@Override
	public List<MediaDevice> selectDeviceList(MediaDevice device) {
		Object[] select = buildSelectSql("select * from rrt_media_device as dev", device);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.query(sql, values, new MediaDeviceMapper());
	}
	
	@Override
	public List<MediaDevice> selectDeviceList(MediaDevice device, Page<?> page) {
		Object[] select = buildSelectSql("select * from rrt_media_device as dev", device);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.queryPagination(sql, values, 
				page.getPageNum(), page.getPageSize(), new MediaDeviceMapper());
	}
	
	@Override
	public List<MediaDevice> selectDeviceListOrderByOrder(MediaDevice device, Page<?> page) {
		String sql = "select dev.id, dev.create_time, dev.update_time, dev.play_id, dev.foreign_id, dev.serial_number, "
				+ "dev.device_type, dev.device_status, dev.base_price, dev.key_words, dev.name, dev.description, dev.state, "
				+ "dev.play_time, dev.play_frequency, dev.lng, dev.lat, dev.district_code, dev.address, dev.owner, "
				+ "count(*) as num from rrt_media_device as dev left join rrt_order as ord on  (dev.id = ord.ad_id)";
		Object[] select = buildSelectSql(sql, device);
		sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.queryPagination(sql+" group by dev.id order by num desc", values, 
				page.getPageNum(), page.getPageSize(), new MediaDeviceMapper());
	}

	@Override
	public int countDevice(MediaDevice device) {
		Object[] select = buildSelectSql("select count(*) from rrt_media_device as dev", device);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.queryForObject(sql, Integer.class, values);
	}
	
	private static final class MediaDeviceMapper implements RowMapper<MediaDevice> {

	    public MediaDevice mapRow(ResultSet rs, int rowNum) throws SQLException {
	        MediaDevice device = new MediaDevice();
	        device.setId(rs.getString("id"));
	        device.setCreateTime(rs.getDate("create_time"));
	        device.setUpdateTime(rs.getDate("update_time"));
	        device.setDeviceType(rs.getString("device_type"));
	        device.setDeviceStatus(rs.getString("device_status"));
	        device.setBasePrice(rs.getInt("base_price"));
	        device.setKeyWords(rs.getString("key_words"));
	        device.setName(rs.getString("name"));
	        device.setDescription(rs.getString("description"));
	        device.setPlayId(rs.getString("play_id"));
	        device.setForeignId(rs.getString("foreign_id"));
	        device.setSerialNumber(rs.getString("serial_number"));
	        device.setState(rs.getString("state"));
	        device.setPlayTime(rs.getDate("play_time"));
	        device.setPlayFrequency(rs.getInt("play_frequency"));
		    device.setLng(rs.getDouble("lng"));
		    device.setLat(rs.getDouble("lat"));
			device.setDistrictCode(rs.getString("district_code"));
			device.setAddress(rs.getString("address"));
			device.setOwner(rs.getString("owner"));
			
	        return device;
	    }
	}
	
	private Object[] buildSelectSql(String selectOrCount, MediaDevice device) {
		
		StringBuilder select = new StringBuilder();
		select.append(selectOrCount);
		select.append(" where 1");
		if(null==device){
			return new Object[]{select.toString()};
		}
		Object[] values = new Object[30];
		int i = 0;
		if(StringUtils.hasText(device.getId())){
			select.append(" and dev.id = ?");
			values[i] = device.getId();
			i++;
		}
		if(StringUtils.hasText(device.getDeviceType())){
			select.append(" and dev.device_type = ?");
			values[i] = device.getDeviceType();
			i++;
		}
		if(StringUtils.hasText(device.getDeviceStatus())){
			select.append(" and dev.device_status = ?");
			values[i] = device.getDeviceStatus();
			i++;
		}
		if(device.getBasePrice()>0){
			select.append(" and dev.base_price <= ?");
			values[i] = device.getBasePrice();
			i++;
		}
		if(StringUtils.hasText(device.getKeyWords())){
			select.append(" and dev.key_words like ?");
			values[i] = "%"+device.getKeyWords()+"%";
			i++;
		}
		if(StringUtils.hasText(device.getName())){
			select.append(" and dev.name like ?");
			values[i]="%"+device.getName()+"%";
			i++;
		}
		if(StringUtils.hasText(device.getPlayId())){
			select.append(" and dev.play_id = ?");
			values[i]=device.getPlayId();
			i++;
		}
		if(StringUtils.hasText(device.getForeignId())){
			select.append(" and dev.foreign_id = ?");
			values[i]=device.getForeignId();
			i++;
		}
		if(StringUtils.hasText(device.getSerialNumber())){
			select.append(" and dev.serial_number = ?");
			values[i]=device.getSerialNumber();
			i++;
		}
		if(StringUtils.hasText(device.getState())){
			select.append(" and dev.state = ?");
			values[i]=device.getState();
			i++;
		}
		if(device.getLng()>0&&device.getLat()>0){
			select.append(" and abs(dev.lng - ?) < 0.3 and abs(dev.lat - ?) < 0.3");
			values[i]=device.getLng();
			i++;
			values[i]=device.getLat();
			i++;
		}
		if(StringUtils.hasText(device.getDistrictCode())){
			select.append(" and dev.district_code = ?");
			values[i]=device.getDistrictCode();
			i++;
		}
		if(StringUtils.hasText(device.getAddress())){
			select.append(" and dev.address like ?");
			values[i] = "%"+device.getAddress()+"%";
			i++;
		}
		if(StringUtils.hasText(device.getOwner())){
			select.append(" and dev.owner = ?");
			values[i] = device.getOwner();
			i++;
		}
		
		values[i] = select.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}
	
	private Object[] buildUpdateSql(MediaDevice device) {
		if(null==device||null==device.getId()){
			return null;
		}
		StringBuilder update = new StringBuilder();
		Object[] values = new Object[30];
		int i = 0;
		update.append("update rrt_media_device set update_time = ?");
		values[i] = new Date(); 
		i++;
		
		if(StringUtils.hasText(device.getDeviceType())){
			update.append(",device_type = ?");
			values[i] = device.getDeviceType();
			i++;
		}
		if(StringUtils.hasText(device.getDeviceStatus())){
			update.append(",device_status = ?");
			values[i] = device.getDeviceStatus();
			i++;
		}
		if(device.getBasePrice()>0){
			update.append(",base_price = ?");
			values[i]=device.getBasePrice();
			i++;
		}
		if(StringUtils.hasText(device.getKeyWords())){
			update.append(",key_words = ?");
			values[i] = device.getKeyWords();
			i++;
		}
		if(StringUtils.hasText(device.getName())){
			update.append(",name = ?");
			values[i] = device.getName();
			i++;
		}
		if(StringUtils.hasText(device.getDescription())){
			update.append(",description = ?");
			values[i] = device.getDescription();
			i++;
		}
		if(StringUtils.hasText(device.getPlayId())){
			update.append(",play_id = ?");
			values[i] = device.getPlayId();
			i++;
		}
		if(StringUtils.hasText(device.getForeignId())){
			update.append(",foreign_id = ?");
			values[i] = device.getForeignId();
			i++;
		}
		if(StringUtils.hasText(device.getSerialNumber())){
			update.append(",serial_number = ?");
			values[i] = device.getSerialNumber();
			i++;
		}
		if(StringUtils.hasText(device.getState())){
			update.append(",state = ?");
			values[i] = device.getState();
			i++;
		}
		if(null!=device.getPlayTime()){
			update.append(",play_time = ?");
			values[i] = device.getPlayTime();
			i++;
		}
		if(device.getPlayFrequency()>0){
			update.append(",play_frequency = ?");
			values[i] = device.getPlayFrequency();
			i++;
		}
		if(device.getLng()>0){
			update.append(",lng = ?");
			values[i] = device.getLng();
			i++;	
		}
		if(device.getLat()>0){
			update.append(",lat = ?");
			values[i] = device.getLat();
			i++;
		}
		if(StringUtils.hasText(device.getDistrictCode())){
			update.append(",district_code = ?");
			values[i] = device.getDistrictCode();
			i++;
		}
		if(StringUtils.hasText(device.getAddress())){
			update.append(",address = ?");
			values[i] = device.getAddress();
			i++;
		}
		if(StringUtils.hasText(device.getOwner())){
			update.append(",owner = ?");
			values[i] = device.getOwner();
			i++;
		}
		
		values[i] = device.getId();
		i++;
		
		update.append(" where id = ?");
		values[i] = update.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}

}
