package com.verisk.ivnt.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.verisk.ivnt.dao.UserDao;
import com.verisk.ivnt.model.UserModel;

@ManagedBean(name = "userCon", eager = true)
@ViewScoped
public class UserController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserDao udao = new UserDao();

	private int editId;
	private List<UserModel> userList = null;
	@ManagedProperty(value = "#{userModel}")
	private UserModel userModel;

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public int getEditId() {
		return editId;
	}

	public void setEditId(int editId) {
		this.editId = editId;
	}

	public String validateAuthentication() {

		boolean valid = udao.validateAuthentication(userModel.getUserName(),
				userModel.getPassWord());
		if (valid) {
			HttpSession session = getSession();
			session.setAttribute("username", userModel.getUserName());
			return "dashboard";
		} else {
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Invalid Authentication",
									"Invalid Authentication"));
			return null;
		}
	}

	public String logOut() {
		HttpSession session = getSession();
		session.invalidate();
		return "login";
	}

	public HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	public String saveUser() {
		udao.saveUser(userModel);
		return "displayUser";
	}

	public List<UserModel> getDisplayUser() {
		if (userList == null) {
			userList = udao.displayUser();
		}
		return userList;
	}

	public String updateUser(UserModel userModel) {
		udao.updateUser(userModel);
		return "displayUser";
	}

	public String deleteUser(int id) {
		udao.deleteUser(id);
		return "null";
	}

}
