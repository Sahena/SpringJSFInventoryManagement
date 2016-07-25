package com.verisk.ivnt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.verisk.ivnt.common.DBConnection;
import com.verisk.ivnt.model.CategoryModel;
import com.verisk.ivnt.model.ProductModel;

public class ProductDao extends DBConnection {

	private static final class ProductMapper implements RowMapper<ProductModel> {

		public ProductModel mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			ProductModel pmodel = new ProductModel();
			CategoryModel cmodel = new CategoryModel();
			pmodel.setPid(rs.getInt("pid"));
			pmodel.setPname(rs.getString("pname"));
			pmodel.setPrice(rs.getInt("price"));
			pmodel.setQuantity(rs.getInt("quantity"));
			cmodel.setCid(rs.getInt("cid"));
			cmodel.setCname(rs.getString("cname"));
			pmodel.setCatModel(cmodel);
			return pmodel;
		}
	}

	public void addProduct(ProductModel pmodel) {
		String query = "Insert into product (pname,price,quantity,cid) values (:pname,:price,:quantity,:cid)";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("pname", pmodel.getPname());
		paramSource.addValue("price", pmodel.getPrice());
		paramSource.addValue("quantity", pmodel.getQuantity());
		paramSource.addValue("cid", pmodel.getCatModel().getCid());
		getJdbcTemplate().update(query, paramSource);
	}

	public List<ProductModel> displayProduct() {
		String query = "SELECT a.pid, a.pname, a.price, a.quantity, b.cid, b.cname FROM product a LEFT JOIN category b ON a.cid= b.cid";
		return getJdbcTemplate().query(query, new ProductMapper());
	}

	public void updateProduct(ProductModel pmodel) {
		String query = "Update product set pname=:pname, price=:price, quantity=:quantity, cid=:cid where pid=:pid";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("pname", pmodel.getPname());
		paramSource.addValue("price", pmodel.getPrice());
		paramSource.addValue("quantity", pmodel.getQuantity());
		paramSource.addValue("cid", pmodel.getCatModel().getCid());
		paramSource.addValue("pid", pmodel.getPid());
		getJdbcTemplate().update(query, paramSource);
	}

	public void deleteProduct(int pid) {
		String query = "delete from product where pid=:pid";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("pid", pid);
		getJdbcTemplate().update(query, paramSource);
	}
}
