package com.example.jpa_3.entity;

import javax.persistence.*;

@Entity
@Table(name = "account", schema = "warehouse", catalog = "")
public class AccountEntity {
    private int accId;
    private String userName;
    private String password;
    private Boolean permission;
    private String empId;
    private Boolean accStatus;

    @Id
    @Column(name = "Acc_Id")
    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    @Basic
    @Column(name = "User_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Permission")
    public Boolean getPermission() {
        return permission;
    }

    public void setPermission(Boolean permission) {
        this.permission = permission;
    }

    @Basic
    @Column(name = "Emp_id")
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Basic
    @Column(name = "Acc_status")
    public Boolean getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(Boolean accStatus) {
        this.accStatus = accStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity that = (AccountEntity) o;

        if (accId != that.accId) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (permission != null ? !permission.equals(that.permission) : that.permission != null) return false;
        if (empId != null ? !empId.equals(that.empId) : that.empId != null) return false;
        if (accStatus != null ? !accStatus.equals(that.accStatus) : that.accStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        result = 31 * result + (empId != null ? empId.hashCode() : 0);
        result = 31 * result + (accStatus != null ? accStatus.hashCode() : 0);
        return result;
    }
}
