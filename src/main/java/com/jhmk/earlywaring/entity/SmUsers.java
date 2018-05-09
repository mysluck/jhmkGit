package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sm_users", schema = "jhmk_waring", catalog = "")
public class SmUsers {
    private String userId;
    private String userName;
    private String userDept;
    private String createDate;
    private String userPwd;
    private String inputCode;
    private String subsysId;
    private String synchroFlag;
    private String userLoginName;
    private String domainuser;
    private String groupCode;
    private String pwdModifyTime;
    private String expiredTime;
    private String lockedTime;
    private String accountStatus;
    private String loginAttemptsCount;
    private String startDate;
    private String stopDate;
    private String expiryDate;
    private String hospitalNo;
    private String secretLevel;
    private String educationFlag;
    private String educationTitle;
    private String caNo;
    private String pym;
    private String wbm;
    private String userType;
    private String isRounds;
    private String isSingQuery;
    private String parentUserId;
    private String superUserId;
    private String state;
    private String physicianTitleId;
    private String administrativeDutiesId;
    private String isNurse;
    private String expiryDateUserPki;
    private String expiredTimeUserPki;
    private String userPki;
    private String phonenumber;
    private String identification;
    private String tmpOccupationNo;
    private String tmpOrderRole;
    private String tmpYzFlag;
    private String orderRole;
    private String isScheduling;
    private String sex;
    private String userClassification;
    private String foreignExperts;
    private String allowSetStudents;
    private String userDept1;
    private String roleId;

    @Transient
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Id
    @Column(name = "USER_ID", nullable = true, length = 255)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "USER_NAME", nullable = true, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "USER_DEPT", nullable = true, length = 255)
    public String getUserDept() {
        return userDept;
    }

    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true, length = 255)
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "USER_PWD", nullable = true, length = 255)
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Basic
    @Column(name = "INPUT_CODE", nullable = true, length = 255)
    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    @Basic
    @Column(name = "SUBSYS_ID", nullable = true, length = 255)
    public String getSubsysId() {
        return subsysId;
    }

    public void setSubsysId(String subsysId) {
        this.subsysId = subsysId;
    }

    @Basic
    @Column(name = "SYNCHRO_FLAG", nullable = true, length = 255)
    public String getSynchroFlag() {
        return synchroFlag;
    }

    public void setSynchroFlag(String synchroFlag) {
        this.synchroFlag = synchroFlag;
    }

    @Basic
    @Column(name = "USER_LOGIN_NAME", nullable = true, length = 255)
    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    @Basic
    @Column(name = "DOMAINUSER", nullable = true, length = 255)
    public String getDomainuser() {
        return domainuser;
    }

    public void setDomainuser(String domainuser) {
        this.domainuser = domainuser;
    }

    @Basic
    @Column(name = "GROUP_CODE", nullable = true, length = 255)
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Basic
    @Column(name = "PWD_MODIFY_TIME", nullable = true, length = 255)
    public String getPwdModifyTime() {
        return pwdModifyTime;
    }

    public void setPwdModifyTime(String pwdModifyTime) {
        this.pwdModifyTime = pwdModifyTime;
    }

    @Basic
    @Column(name = "EXPIRED_TIME", nullable = true, length = 255)
    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    @Basic
    @Column(name = "LOCKED_TIME", nullable = true, length = 255)
    public String getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(String lockedTime) {
        this.lockedTime = lockedTime;
    }

    @Basic
    @Column(name = "ACCOUNT_STATUS", nullable = true, length = 255)
    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Basic
    @Column(name = "LOGIN_ATTEMPTS_COUNT", nullable = true, length = 255)
    public String getLoginAttemptsCount() {
        return loginAttemptsCount;
    }

    public void setLoginAttemptsCount(String loginAttemptsCount) {
        this.loginAttemptsCount = loginAttemptsCount;
    }

    @Basic
    @Column(name = "START_DATE", nullable = true, length = 255)
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "STOP_DATE", nullable = true, length = 255)
    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    @Basic
    @Column(name = "EXPIRY_DATE", nullable = true, length = 255)
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Basic
    @Column(name = "HOSPITAL_NO", nullable = true, length = 255)
    public String getHospitalNo() {
        return hospitalNo;
    }

    public void setHospitalNo(String hospitalNo) {
        this.hospitalNo = hospitalNo;
    }

    @Basic
    @Column(name = "SECRET_LEVEL", nullable = true, length = 255)
    public String getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(String secretLevel) {
        this.secretLevel = secretLevel;
    }

    @Basic
    @Column(name = "EDUCATION_FLAG", nullable = true, length = 255)
    public String getEducationFlag() {
        return educationFlag;
    }

    public void setEducationFlag(String educationFlag) {
        this.educationFlag = educationFlag;
    }

    @Basic
    @Column(name = "EDUCATION_TITLE", nullable = true, length = 255)
    public String getEducationTitle() {
        return educationTitle;
    }

    public void setEducationTitle(String educationTitle) {
        this.educationTitle = educationTitle;
    }

    @Basic
    @Column(name = "CA_NO", nullable = true, length = 255)
    public String getCaNo() {
        return caNo;
    }

    public void setCaNo(String caNo) {
        this.caNo = caNo;
    }

    @Basic
    @Column(name = "PYM", nullable = true, length = 255)
    public String getPym() {
        return pym;
    }

    public void setPym(String pym) {
        this.pym = pym;
    }

    @Basic
    @Column(name = "WBM", nullable = true, length = 255)
    public String getWbm() {
        return wbm;
    }

    public void setWbm(String wbm) {
        this.wbm = wbm;
    }

    @Basic
    @Column(name = "USER_TYPE", nullable = true, length = 255)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "IS_ROUNDS", nullable = true, length = 255)
    public String getIsRounds() {
        return isRounds;
    }

    public void setIsRounds(String isRounds) {
        this.isRounds = isRounds;
    }

    @Basic
    @Column(name = "IS_SING_QUERY", nullable = true, length = 255)
    public String getIsSingQuery() {
        return isSingQuery;
    }

    public void setIsSingQuery(String isSingQuery) {
        this.isSingQuery = isSingQuery;
    }

    @Basic
    @Column(name = "PARENT_USER_ID", nullable = true, length = 255)
    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    @Basic
    @Column(name = "SUPER_USER_ID", nullable = true, length = 255)
    public String getSuperUserId() {
        return superUserId;
    }

    public void setSuperUserId(String superUserId) {
        this.superUserId = superUserId;
    }

    @Basic
    @Column(name = "STATE", nullable = true, length = 255)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "PHYSICIAN_TITLE_ID", nullable = true, length = 255)
    public String getPhysicianTitleId() {
        return physicianTitleId;
    }

    public void setPhysicianTitleId(String physicianTitleId) {
        this.physicianTitleId = physicianTitleId;
    }

    @Basic
    @Column(name = "ADMINISTRATIVE_DUTIES_ID", nullable = true, length = 255)
    public String getAdministrativeDutiesId() {
        return administrativeDutiesId;
    }

    public void setAdministrativeDutiesId(String administrativeDutiesId) {
        this.administrativeDutiesId = administrativeDutiesId;
    }

    @Basic
    @Column(name = "IS_NURSE", nullable = true, length = 255)
    public String getIsNurse() {
        return isNurse;
    }

    public void setIsNurse(String isNurse) {
        this.isNurse = isNurse;
    }

    @Basic
    @Column(name = "EXPIRY_DATE_USER_PKI", nullable = true, length = 255)
    public String getExpiryDateUserPki() {
        return expiryDateUserPki;
    }

    public void setExpiryDateUserPki(String expiryDateUserPki) {
        this.expiryDateUserPki = expiryDateUserPki;
    }

    @Basic
    @Column(name = "EXPIRED_TIME_USER_PKI", nullable = true, length = 255)
    public String getExpiredTimeUserPki() {
        return expiredTimeUserPki;
    }

    public void setExpiredTimeUserPki(String expiredTimeUserPki) {
        this.expiredTimeUserPki = expiredTimeUserPki;
    }

    @Basic
    @Column(name = "USER_PKI", nullable = true, length = 255)
    public String getUserPki() {
        return userPki;
    }

    public void setUserPki(String userPki) {
        this.userPki = userPki;
    }

    @Basic
    @Column(name = "PHONENUMBER", nullable = true, length = 255)
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Basic
    @Column(name = "IDENTIFICATION", nullable = true, length = 255)
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @Basic
    @Column(name = "TMP_OCCUPATION_NO", nullable = true, length = 255)
    public String getTmpOccupationNo() {
        return tmpOccupationNo;
    }

    public void setTmpOccupationNo(String tmpOccupationNo) {
        this.tmpOccupationNo = tmpOccupationNo;
    }

    @Basic
    @Column(name = "TMP_ORDER_ROLE", nullable = true, length = 255)
    public String getTmpOrderRole() {
        return tmpOrderRole;
    }

    public void setTmpOrderRole(String tmpOrderRole) {
        this.tmpOrderRole = tmpOrderRole;
    }

    @Basic
    @Column(name = "TMP_YZ_FLAG", nullable = true, length = 255)
    public String getTmpYzFlag() {
        return tmpYzFlag;
    }

    public void setTmpYzFlag(String tmpYzFlag) {
        this.tmpYzFlag = tmpYzFlag;
    }

    @Basic
    @Column(name = "ORDER_ROLE", nullable = true, length = 255)
    public String getOrderRole() {
        return orderRole;
    }

    public void setOrderRole(String orderRole) {
        this.orderRole = orderRole;
    }

    @Basic
    @Column(name = "IS_SCHEDULING", nullable = true, length = 255)
    public String getIsScheduling() {
        return isScheduling;
    }

    public void setIsScheduling(String isScheduling) {
        this.isScheduling = isScheduling;
    }

    @Basic
    @Column(name = "SEX", nullable = true, length = 255)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "USER_CLASSIFICATION", nullable = true, length = 255)
    public String getUserClassification() {
        return userClassification;
    }

    public void setUserClassification(String userClassification) {
        this.userClassification = userClassification;
    }

    @Basic
    @Column(name = "FOREIGN_EXPERTS", nullable = true, length = 255)
    public String getForeignExperts() {
        return foreignExperts;
    }

    public void setForeignExperts(String foreignExperts) {
        this.foreignExperts = foreignExperts;
    }

    @Basic
    @Column(name = "ALLOW_SET_STUDENTS", nullable = true, length = 255)
    public String getAllowSetStudents() {
        return allowSetStudents;
    }

    public void setAllowSetStudents(String allowSetStudents) {
        this.allowSetStudents = allowSetStudents;
    }

    @Basic
    @Column(name = "USER_DEPT1", nullable = true, length = 255)
    public String getUserDept1() {
        return userDept1;
    }

    public void setUserDept1(String userDept1) {
        this.userDept1 = userDept1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmUsers smUsers = (SmUsers) o;
        return Objects.equals(userId, smUsers.userId) &&
                Objects.equals(userName, smUsers.userName) &&
                Objects.equals(userDept, smUsers.userDept) &&
                Objects.equals(createDate, smUsers.createDate) &&
                Objects.equals(userPwd, smUsers.userPwd) &&
                Objects.equals(inputCode, smUsers.inputCode) &&
                Objects.equals(subsysId, smUsers.subsysId) &&
                Objects.equals(synchroFlag, smUsers.synchroFlag) &&
                Objects.equals(userLoginName, smUsers.userLoginName) &&
                Objects.equals(domainuser, smUsers.domainuser) &&
                Objects.equals(groupCode, smUsers.groupCode) &&
                Objects.equals(pwdModifyTime, smUsers.pwdModifyTime) &&
                Objects.equals(expiredTime, smUsers.expiredTime) &&
                Objects.equals(lockedTime, smUsers.lockedTime) &&
                Objects.equals(accountStatus, smUsers.accountStatus) &&
                Objects.equals(loginAttemptsCount, smUsers.loginAttemptsCount) &&
                Objects.equals(startDate, smUsers.startDate) &&
                Objects.equals(stopDate, smUsers.stopDate) &&
                Objects.equals(expiryDate, smUsers.expiryDate) &&
                Objects.equals(hospitalNo, smUsers.hospitalNo) &&
                Objects.equals(secretLevel, smUsers.secretLevel) &&
                Objects.equals(educationFlag, smUsers.educationFlag) &&
                Objects.equals(educationTitle, smUsers.educationTitle) &&
                Objects.equals(caNo, smUsers.caNo) &&
                Objects.equals(pym, smUsers.pym) &&
                Objects.equals(wbm, smUsers.wbm) &&
                Objects.equals(userType, smUsers.userType) &&
                Objects.equals(isRounds, smUsers.isRounds) &&
                Objects.equals(isSingQuery, smUsers.isSingQuery) &&
                Objects.equals(parentUserId, smUsers.parentUserId) &&
                Objects.equals(superUserId, smUsers.superUserId) &&
                Objects.equals(state, smUsers.state) &&
                Objects.equals(physicianTitleId, smUsers.physicianTitleId) &&
                Objects.equals(administrativeDutiesId, smUsers.administrativeDutiesId) &&
                Objects.equals(isNurse, smUsers.isNurse) &&
                Objects.equals(expiryDateUserPki, smUsers.expiryDateUserPki) &&
                Objects.equals(expiredTimeUserPki, smUsers.expiredTimeUserPki) &&
                Objects.equals(userPki, smUsers.userPki) &&
                Objects.equals(phonenumber, smUsers.phonenumber) &&
                Objects.equals(identification, smUsers.identification) &&
                Objects.equals(tmpOccupationNo, smUsers.tmpOccupationNo) &&
                Objects.equals(tmpOrderRole, smUsers.tmpOrderRole) &&
                Objects.equals(tmpYzFlag, smUsers.tmpYzFlag) &&
                Objects.equals(orderRole, smUsers.orderRole) &&
                Objects.equals(isScheduling, smUsers.isScheduling) &&
                Objects.equals(sex, smUsers.sex) &&
                Objects.equals(userClassification, smUsers.userClassification) &&
                Objects.equals(foreignExperts, smUsers.foreignExperts) &&
                Objects.equals(allowSetStudents, smUsers.allowSetStudents) &&
                Objects.equals(userDept1, smUsers.userDept1);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userName, userDept, createDate, userPwd, inputCode, subsysId, synchroFlag, userLoginName, domainuser, groupCode, pwdModifyTime, expiredTime, lockedTime, accountStatus, loginAttemptsCount, startDate, stopDate, expiryDate, hospitalNo, secretLevel, educationFlag, educationTitle, caNo, pym, wbm, userType, isRounds, isSingQuery, parentUserId, superUserId, state, physicianTitleId, administrativeDutiesId, isNurse, expiryDateUserPki, expiredTimeUserPki, userPki, phonenumber, identification, tmpOccupationNo, tmpOrderRole, tmpYzFlag, orderRole, isScheduling, sex, userClassification, foreignExperts, allowSetStudents, userDept1);
    }
}
