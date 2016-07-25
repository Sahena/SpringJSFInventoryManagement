package com.verisk.ivnt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.verisk.ivnt.common.DBConnection;
import com.verisk.ivnt.model.CategoryModel;

public class CategoryDao extends DBConnection {

	private static final class CategoryMapper implements
			RowMapper<CategoryModel> {
		public CategoryModel mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			CategoryModel cmodel = new CategoryModel();
			cmodel.setCid(rs.getInt("cid"));
			cmodel.setCname(rs.getString("cname"));
			return cmodel;
		}
	}

	public List<CategoryModel> displayCategory() {
		String query = "Select * from category";
		return getJdbcTemplate().query(query, new CategoryMapper());
	}

	public void saveCategory(CategoryModel catModel) {
		String query = "Insert into category (cname) values (:cname)";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("cname", catModel.getCname());
		getJdbcTemplate().update(query, parameterSource);
	}

	public void updateCategory(CategoryModel catModel) {
		String query = "UPDATE category set cname=:cname where cid=:cid";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("cname", catModel.getCname());
		parameterSource.addValue("cid", catModel.getCid());
		getJdbcTemplate().update(query, parameterSource);
	}

	public void deleteCategory(int cid) {
		String query = "delete from category where cid=:cid";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("cid", cid);
		getJdbcTemplate().update(query, parameterSource);
	}
}
