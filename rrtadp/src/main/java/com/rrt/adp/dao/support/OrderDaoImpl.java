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
import com.rrt.adp.dao.OrderDao;
import com.rrt.adp.model.Order;
import com.rrt.adp.util.SequenceGenerator;

@Repository
public class OrderDaoImpl implements OrderDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int insertOrder(Order order) {
		if(null==order){
			return 0;
		}
		order.setId(Order.PREFIX_ADVERTISEMENT+SequenceGenerator.next());
		order.setCreateTime(new Date());
		order.setState(Order.STATE_NEW);
		return this.jdbcTemplate.update("insert into rrt_order (id, create_time, update_time, "
				+ "ad_id, device_id, price, state, ad_owner, device_owner) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[]{
						order.getId(),
						order.getCreateTime(),
						order.getUpdateTime(),
						order.getAdId(),
						order.getDeviceId(),
						order.getPrice(),
						order.getState(),
						order.getAdOwner(),
						order.getDeviceOwner()
				});
	}

	@Override
	public int deleteOrder(String orderId) {
		if(null==orderId){
			return 0;
		}
		return this.jdbcTemplate.update("delete from rrt_order where id = ?", orderId);
	}

	@Override
	public int updateOrder(Order order) {
		Object[] update = buildUpdateSql(order);
		if(null==update){
			return 0;
		}
		String sql = (String)update[update.length-1];
		Object[] values = new Object[update.length-1];
		System.arraycopy(update, 0, values, 0, update.length-1);
		return this.jdbcTemplate.update(sql, values);
	}
	
	private List<Order> select(Order order) {
		Object[] select = buildSelectSql(order);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.query(sql, values, new OrderMapper());
	}

	@Override
	public int updateOrderBid(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Order selectOrder(String orderId) {
		if(!StringUtils.hasText(orderId)){
			return null;
		}
		Order order = new Order();
		order.setId(orderId);
		List<Order> orders = select(order);
		if(null==orders||orders.size()==0){
			return null;
		}
		return orders.get(0);
	}

	@Override
	public Order selectUserOrder(String orderId, String account) {
		List<Order> orders =  this.jdbcTemplate.query("select * from rrt_order "
				+ "where id = ? and ( ad_owner = ? or device_owner = ? )",
				new Object[]{orderId, account, account}, new OrderMapper());
		
		if(null==orders||orders.size()==0){
			return null;
		}
		return orders.get(0);
	}

	@Override
	public List<Order> selectOrderList() {
		List<Order> orders =  this.jdbcTemplate.query("select * from rrt_order "
				+ "order by create_time desc", new OrderMapper());
		return orders;
	}

	@Override
	public List<Order> selectUserOrderList(String account) {
		List<Order> orders =  this.jdbcTemplate.query("select * from rrt_order "
				+ "where ad_owner = ? or device_owner = ? order by create_time desc",
				new Object[]{account, account}, new OrderMapper());
		
		return orders;
	}

	@Override
	public List<String> selectBidDevice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateDeviceBidSuccess(String deviceId) {
		if(!StringUtils.hasText(deviceId)){
			return 0;
		}
		return this.jdbcTemplate.update("update rrt_order set state = ? "
			+ "where device_id = ? and state = ? or state = ? order by price DESC LIMIT 20", 
			new Object[]{Order.STATE_BID_SUCCESS, deviceId, Order.STATE_NEW, Order.STATE_CHECKED});
	}

	@Override
	public int updateDeviceBidFail() {
		return this.jdbcTemplate.update("update rrt_order set state = ? "
			+ "WHERE state = ? or state = ? and create_time > DATE_SUB(NOW(), INTERVAL 10 DAY)", 
			new Object[]{Order.STATE_BID_FAIL, Order.STATE_NEW, Order.STATE_CHECKED});
	}
	
	private static final class OrderMapper implements RowMapper<Order> {

	    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Order order = new Order();
	        order.setId(rs.getString("id"));
	        order.setCreateTime(rs.getDate("create_time"));
	        order.setUpdateTime(rs.getDate("update_time"));
	        order.setAdId(rs.getString("ad_id"));
	        order.setDeviceId(rs.getString("device_id"));
	        order.setPrice(rs.getFloat("price"));
	        order.setState(rs.getString("state"));
	        order.setAdOwner(rs.getString("ad_owner"));
	        order.setDeviceOwner(rs.getString("device_owner"));
	        
	        return order;
	    }
	}
	
	private Object[] buildSelectSql(Order order) {
		
		StringBuilder select = new StringBuilder();
		select.append("select * from rrt_order where 1");
		if(null==order){
			return new Object[]{select.toString()};
		}
		Object[] values = new Object[20];
		int i = 0;
		if(StringUtils.hasText(order.getId())){
			select.append(" and id = ?");
			values[i] = order.getId();
			i++;
		}
		if(StringUtils.hasText(order.getAdId())){
			select.append(" and ad_id = ?");
			values[i] = order.getAdId();
			i++;
		}
		if(StringUtils.hasText(order.getDeviceId())){
			select.append(" and device_id = ?");
			values[i] = order.getDeviceId();
			i++;
		}
		if(order.getPrice()>0){
			select.append(" and price = ?");
			values[i] = order.getPrice();
			i++;
		}
		if(StringUtils.hasText(order.getState())){
			select.append(" and state = ?");
			values[i] = order.getState();
			i++;
		}
		if(StringUtils.hasText(order.getAdOwner())){
			select.append(" and ad_owner = ?");
			values[i] = order.getAdOwner();
			i++;
		}
		if(StringUtils.hasText(order.getDeviceOwner())){
			select.append(" and device_owner = ?");
			values[i] = order.getDeviceOwner();
			i++;
		}
		select.append(" order by create_time desc");
		values[i] = select.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}
	
	private Object[] buildUpdateSql(Order order) {
		if(null==order||null==order.getId()){
			return null;
		}
		StringBuilder update = new StringBuilder();
		Object[] values = new Object[20];
		int i = 0;
		update.append("update rrt_order set update_time = ?");
		values[i] = new Date(); 
		i++;
		
		if(StringUtils.hasText(order.getAdId())){
			update.append(",ad_id = ?");
			values[i] = order.getAdId();
			i++;
		}
		if(StringUtils.hasText(order.getDeviceId())){
			update.append(",device_id = ?");
			values[i] = order.getDeviceId();
			i++;
		}
		if(order.getPrice()>0){
			update.append(",price = ?");
			values[i] = order.getPrice();
			i++;
		}
		if(StringUtils.hasText(order.getState())){
			update.append(",state = ?");
			values[i] = order.getState();
			i++;
		}
		if(StringUtils.hasText(order.getAdOwner())){
			update.append(",ad_owner = ?");
			values[i] = order.getAdOwner();
			i++;
		}
		if(StringUtils.hasText(order.getDeviceOwner())){
			update.append(",device_owner = ?");
			values[i] = order.getDeviceOwner();
			i++;
		}	
		values[i] = order.getId();
		i++;
		
		update.append(" where id = ?");
		values[i] = update.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}

}
