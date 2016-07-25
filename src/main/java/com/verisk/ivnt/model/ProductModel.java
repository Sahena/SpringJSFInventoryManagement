package com.verisk.ivnt.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "prodModel", eager = true)
@SessionScoped
public class ProductModel {

	private int pid;
	private String pname;
	private int price;
	private int quantity;

	@ManagedProperty(value = "#{catModel}")
	private CategoryModel catModel;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CategoryModel getCatModel() {
		return catModel;
	}

	public void setCatModel(CategoryModel catModel) {
		this.catModel = catModel;
	}
}
