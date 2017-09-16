package com.rrt.adp.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;
import com.rrt.adp.dao.PersonUserDao;
import com.rrt.adp.model.PersonUser;
import com.rrt.adp.util.SequenceGenerator;

/**
 * @Description TODO
 * @author Wuwuhao
 * @date 2017年9月16日
 * 
 */
public class PersonUserDaoImpl implements PersonUserDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int insertUser(PersonUser user) {
		if(null==user){
			return 0;
		}
		user.setId(SequenceGenerator.next());
		user.setCreateTime(new Date());
		user.setState(PersonUser.STATE_NEW);
		return this.jdbcTemplate.update("insert into user_person (id, create_time, update_time, "
				+ "account, password, description, account_type, account_role, account_state, "
				+ "user_name, nick_name, phone, email, district_code, address, id_card, "
				+ "id_card_front_url, id_card_back_url) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[]{
						user.getId(),
						user.getCreateTime(),
						user.getUpdateTime(),
						user.getAccount(),
						user.getPassword(),
						user.getDescription(),
						user.getType(),
						user.getRole(),
						user.getState(),
						user.getName(),
						user.getNickName(),
						user.getPhone(),
						user.getEmail(),
						user.getDistrictCode(),
						user.getAddress(),
						user.getIDCard(),
						user.getIDCardFrontPicUrl(),
						user.getIDCardBackPicUrl()
				});
	}

	@Override
	public int deleteUser(String account) {
		if(null==account){
			return 0;
		}
		return this.jdbcTemplate.update("delete from user_person where account = ?", account);
	}

	@Override
	public int updateUser(PersonUser user) {
		Object[] update = buildUpdateSql(user);
		if(null==update){
			return 0;
		}
		String sql = (String)update[update.length-1];
		Object[] values = new Object[update.length-1];
		System.arraycopy(update, 0, values, 0, update.length-1);
		return this.jdbcTemplate.update(sql, values);
	}
	
	private List<PersonUser> select(PersonUser user){
		Object[] select = buildSelectSql(user);
		String sql = (String)select[select.length-1];
		Object[] values = new Object[select.length-1];
		System.arraycopy(select, 0, values, 0, select.length-1);
		return this.jdbcTemplate.query(sql, values, new PersonUserMapper());
	}

	@Override
	public List<PersonUser> selectUser() {
		PersonUser user = new PersonUser();
		return select(user);
	}

	@Override
	public PersonUser selectUserByAccount(String accont) {
		if(!StringUtils.hasText(accont)){
			return null;
		}
		PersonUser user = new PersonUser();
		user.setAccount(accont);
		List<PersonUser> users = select(user);
		if(null==users||users.size()==0){
			return null;
		}
		return users.get(0);
	}
	
	private static final class PersonUserMapper implements RowMapper<PersonUser> {

	    public PersonUser mapRow(ResultSet rs, int rowNum) throws SQLException {
	        PersonUser user = new PersonUser();
	        user.setId(rs.getString("id"));
	        user.setCreateTime(rs.getDate("create_time"));
	        user.setUpdateTime(rs.getDate("update_time"));
	        user.setAccount(rs.getString("account"));
	        user.setPassword(rs.getString("password"));
	        user.setDescription(rs.getString("description"));
	        user.setType(rs.getString("account_type"));
	        user.setRole(rs.getString("account_role"));
	        user.setState(rs.getString("account_state"));
	        user.setName(rs.getString("user_name"));
	        user.setNickName(rs.getString("nick_name"));
	        user.setPhone(rs.getString("phone"));
	        user.setEmail(rs.getString("email"));
	        user.setDistrictCode(rs.getString("district_code"));
			user.setAddress(rs.getString("address"));
			user.setIDCard(rs.getString("id_card"));
			user.setIDCardFrontPicUrl(rs.getString("id_card_front_url"));
			user.setIDCardBackPicUrl(rs.getString("id_card_back_url"));
			
	        return user;
	    }
	}
	
	private Object[] buildSelectSql(PersonUser user) {
		
		StringBuilder select = new StringBuilder();
		select.append("select * from user_person where 1");
		if(null==user){
			return new Object[]{select.toString()};
		}
		Object[] values = new Object[30];
		int i = 0;
		if(StringUtils.hasText(user.getAccount())){
			select.append(" and account = ?");
			values[i] = user.getAccount();
			i++;
		}
		if(StringUtils.hasText(user.getType())){
			select.append(" and account_type = ?");
			values[i] = user.getType();
		    i++;
		}
		if(StringUtils.hasText(user.getRole())){
			select.append(" and account_role = ?");
			values[i] = user.getRole();
			i++;
		}
		if(StringUtils.hasText(user.getState())){
			select.append(" and account_state = ?");
			values[i] = user.getState();
			i++;
		}
		if(StringUtils.hasText(user.getName())){
			select.append(" and user_name = ?");
			values[i] = user.getName();
			i++;
		}
		if(StringUtils.hasText(user.getNickName())){
			select.append(" and nick_name = ?");
			values[i] = user.getNickName();
			i++;
		}
		if(StringUtils.hasText(user.getPhone())){
			select.append(" and phone = ?");
			values[i] = user.getPhone();
			i++;
		}
		if(StringUtils.hasText(user.getEmail())){
			select.append(" and email = ?");
			values[i] = user.getEmail();
			i++;
		}
		if(StringUtils.hasText(user.getDistrictCode())){
			select.append(" and district_code = ?");
			values[i] = user.getDistrictCode();
			i++;
		}
		if(StringUtils.hasText(user.getAddress())){
			select.append(" and address = ?");
			values[i] = user.getAddress();
			i++;
		}
		if(StringUtils.hasText(user.getIDCard())){
			select.append(" and id_card = ?");
			values[i] = user.getIDCard();
			i++;
		}
		
		values[i] = select.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}
	
	
	private Object[] buildUpdateSql(PersonUser user) {
		if(null==user||null==user.getAccount()){
			return null;
		}
		StringBuilder update = new StringBuilder();
		Object[] values = new Object[30];
		int i = 0;
		update.append("update user_company set update_time = ?");
		values[i] = new Date(); 
		i++;
		
		if(StringUtils.hasText(user.getPassword())){
			update.append(",password = ?");
			values[i] = user.getPassword();
			i++;
		}
		if(StringUtils.hasText(user.getDescription())){
			update.append(",description = ?");
			values[i] = user.getDescription();
			i++;
		}
		if(StringUtils.hasText(user.getType())){
			update.append(",account_type = ?");
			values[i] = user.getType();
			i++;
		}
		if(StringUtils.hasText(user.getRole())){
			update.append(",account_role = ?");
			values[i] = user.getRole();
			i++;
		}
		if(StringUtils.hasText(user.getState())){
			update.append(",account_state = ?");
			values[i] = user.getState();
			i++;
		}
		if(StringUtils.hasText(user.getName())){
			update.append(",user_name = ?");
			values[i] = user.getName();
			i++;
		}
		if(StringUtils.hasText(user.getNickName())){
			update.append(",nick_name = ?");
			values[i]=user.getNickName();
			i++;
		}
		if(StringUtils.hasText(user.getPhone())){
			update.append(",phone = ?");
			values[i]=user.getPhone();
			i++;
		}
		if(StringUtils.hasText(user.getEmail())){
			update.append(",email = ?");
			values[i]=user.getEmail();
			i++;
		}
		if(StringUtils.hasText(user.getDistrictCode())){
			update.append(",district_code = ?");
			values[i]=user.getDistrictCode();
			i++;
		}
		if(StringUtils.hasText(user.getAddress())){
			update.append(",address = ?");
			values[i]=user.getAddress();
			i++;
		}
		if(StringUtils.hasText(user.getIDCard())){
			update.append(",id_card = ?");
			values[i]=user.getIDCard();
			i++;
		}
		if(StringUtils.hasText(user.getIDCardFrontPicUrl())){
			update.append(",id_card_front_url = ?");
			values[i]=user.getIDCardFrontPicUrl();
			i++;
		}
		if(StringUtils.hasText(user.getIDCardBackPicUrl())){
			update.append(",id_card_back_url = ?");
			values[i]=user.getIDCardBackPicUrl();
			i++;
		}
		
		values[i] = user.getAccount();
		i++;
		
		update.append(" where account = ?");
		values[i] = update.toString();
		i++;
		
		Object[] retn = new Object[i];
		System.arraycopy(values, 0, retn, 0, i);
		
		return retn;
	}

}
