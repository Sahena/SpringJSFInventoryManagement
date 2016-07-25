package com.verisk.ivnt.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import com.verisk.ivnt.dao.ProductDao;
import com.verisk.ivnt.model.ProductModel;

@ManagedBean(name = "prodCon", eager = true)
@ViewScoped
public class ProductController {

	@ManagedProperty(value = "#{prodModel}")
	private ProductModel prodModel;
	private int editId;
	private ProductDao pdao = new ProductDao();
	List<ProductModel> plist = null;

	public ProductModel getProdModel() {
		return prodModel;
	}

	public void setProdModel(ProductModel prodModel) {
		this.prodModel = prodModel;
	}

	public int getEditId() {
		return editId;
	}

	public void setEditId(int editId) {
		this.editId = editId;
	}

	public String addProduct() {
		pdao.addProduct(prodModel);
		return "displayProduct";
	}

	public List<ProductModel> getDisplayProduct() {
		if (plist == null) {
			plist = pdao.displayProduct();
		}
		return plist;
	}

	public String updateProduct(ProductModel pmodel) {
		pdao.updateProduct(pmodel);
		return "displayProduct";
	}

	public String deleteProduct(int pid) {
		pdao.deleteProduct(pid);
		return "displayProduct";
	}
}
