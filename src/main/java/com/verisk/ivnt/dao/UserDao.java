package com.verisk.ivnt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.verisk.ivnt.common.DBConnection;
import com.verisk.ivnt.model.UserModel;

public class UserDao extends DBConnection {

	private final static class UserMapper implements RowMapper<UserModel> {

		public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserModel userModel = new UserModel();
			userModel.setId(rs.getInt("id"));
			userModel.setUserName(rs.getString("uname"));
			userModel.setPassWord(rs.getString("password"));
			userModel.setDob(rs.getTimestamp("dob"));
			return userModel;
		}
	}

	public boolean validateAuthentication(String userName, String passWord) {
		String query = "select count(*) from users where uname=:userName and password=:password";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("userName", userName);
		parameterSource.addValue("password", passWord);

		int result = getJdbcTemplate().queryForObject(query, parameterSource,
				Integer.class);

		if (result > 0)
			return true;
		else
			return false;
	}

	public void saveUser(UserModel userModel) {
		String query = "Insert into users (uname,password,dob) values (:uname,:password,:dob)";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("uname", userModel.getUserName());
		parameterSource.addValue("password", userModel.getPassWord());
		parameterSource.addValue("dob", userModel.getDob());
		getJdbcTemplate().update(query, parameterSource);
	}

	public List<UserModel> displayUser() {
		String query = "select * from users";
		return getJdbcTemplate().query(query, new UserMapper());
	}

	public void updateUser(UserModel userModel) {
		String query = "Update users set uname=:uname, password=:password, dob=:dob where id=:id";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("uname", userModel.getUserName());
		parameterSource.addValue("password", userModel.getPassWord());
		parameterSource.addValue("dob", userModel.getDob());
		parameterSource.addValue("id", userModel.getId());
		getJdbcTemplate().update(query, parameterSource);
	}

	public void deleteUser(int id) {
		String query = "delete from users where id = :id";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		getJdbcTemplate().update(query, parameterSource);
	}

}
