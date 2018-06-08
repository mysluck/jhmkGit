//package com.jhmk.earlywaring.entity;
//
//import javax.persistence.Column;
//import java.util.Objects;
//
//
//public class RoleUserId implements java.io.Serializable {
//	private static final long serialVersionUID = 7624480583509562621L;
//	/**
//	 *
//	 */
//
//
//	private String userId;
//	private String roleId;
//
//	@Column(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
//	public String getUserId() {
//		return this.userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	@Column(name = "ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
//	public String getRoleId() {
//		return this.roleId;
//	}
//
//	public void setRoleId(String roleId) {
//		this.roleId = roleId;
//	}
//
//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		RoleUserId that = (RoleUserId) o;
//		return Objects.equals(userId, that.userId) &&
//				Objects.equals(roleId, that.roleId);
//	}
//
//	@Override
//	public int hashCode() {
//
//		return Objects.hash(userId, roleId);
//	}
//}