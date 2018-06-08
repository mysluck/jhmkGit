//package com.jhmk.earlywaring.entity;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//
//@Entity
//@Table(name = "SM_ROLE_USER")
//public class RoleUser {
//
//	private RoleUserId userRoleId;
//	private SmUsers user;
//	private SmRole role;
//
//	@EmbeddedId
//	@AttributeOverrides( {
//			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 255)),
//			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)) })
//	public RoleUserId getUserRoleId() {
//		return userRoleId;
//	}
//
//	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
//	@JoinColumn(name = "USER_ID", unique = false, nullable = false, insertable = false, updatable = false)
//	public SmUsers getUser() {
//		return user;
//	}
//
//	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
//	@JoinColumn(name = "ROLE_ID", unique = false, nullable = false, insertable = false, updatable = false)
//	public SmRole getRole() {
//		return role;
//	}
//	public void setUserRoleId(RoleUserId userRoleId) {
//		this.userRoleId = userRoleId;
//	}
//	public void setUser(SmUsers user) {
//		this.user = user;
//	}
//	public void setRole(SmRole role) {
//		this.role = role;
//	}
//
//	@Override
//	public String toString() {
//		return "RoleUser{" +
//				"userRoleId=" + userRoleId +
//				", user=" + user.toString() +
//				", role=" + role.toString() +
//				'}';
//	}
//}
