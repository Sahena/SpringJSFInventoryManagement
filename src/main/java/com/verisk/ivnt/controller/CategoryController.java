package com.verisk.ivnt.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import com.verisk.ivnt.dao.CategoryDao;
import com.verisk.ivnt.model.CategoryModel;

@ManagedBean(name = "catCon", eager = true)
@ViewScoped
public class CategoryController {

	@ManagedProperty(value = "#{catModel}")
	private CategoryModel catModel;
	private int editId;
	private List<CategoryModel> catList = null;
	private CategoryDao cdao = new CategoryDao();

	public CategoryModel getCatModel() {
		return catModel;
	}

	public void setCatModel(CategoryModel catModel) {
		this.catModel = catModel;
	}

	public int getEditId() {
		return editId;
	}

	public void setEditId(int editId) {
		this.editId = editId;
	}

	public List<CategoryModel> getDisplayCategory() {
		if (catList == null) {
			catList = cdao.displayCategory();
		}
		return catList;
	}

	public String addCategory() {
		cdao.saveCategory(catModel);
		return "displayCategory";
	}

	public String deleteCategory(int cid) {
		cdao.deleteCategory(cid);
		catList.clear();
		return null;
	}

	public String updateCategory(CategoryModel cmodel) {
		cdao.updateCategory(cmodel);
		return "displayCategory";
	}
}
