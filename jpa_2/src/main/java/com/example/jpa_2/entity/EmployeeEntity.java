package com.example.jpa_2.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "employee", schema = "warehouse", catalog = "")
public class EmployeeEntity {
    private String empId;
    private String empName;
    private Timestamp birthOfDate;
    private String email;
    private String phone;
    private String address;
    private short empStatus;
    private Collection<BillEntity> billsByEmpId;
    private Collection<BillEntity> billsByEmpId_0;

    @Id
    @Column(name = "Emp_Id")
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Basic
    @Column(name = "Emp_Name")
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Basic
    @Column(name = "Birth_Of_Date")
    public Timestamp getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(Timestamp birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "Emp_Status")
    public short getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(short empStatus) {
        this.empStatus = empStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (empStatus != that.empStatus) return false;
        if (empId != null ? !empId.equals(that.empId) : that.empId != null) return false;
        if (empName != null ? !empName.equals(that.empName) : that.empName != null) return false;
        if (birthOfDate != null ? !birthOfDate.equals(that.birthOfDate) : that.birthOfDate != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = empId != null ? empId.hashCode() : 0;
        result = 31 * result + (empName != null ? empName.hashCode() : 0);
        result = 31 * result + (birthOfDate != null ? birthOfDate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (int) empStatus;
        return result;
    }

    @OneToMany(mappedBy = "employeeByEmpIdCreated")
    public Collection<BillEntity> getBillsByEmpId() {
        return billsByEmpId;
    }

    public void setBillsByEmpId(Collection<BillEntity> billsByEmpId) {
        this.billsByEmpId = billsByEmpId;
    }

    @OneToMany(mappedBy = "employeeByEmpIdAuth")
    public Collection<BillEntity> getBillsByEmpId_0() {
        return billsByEmpId_0;
    }

    public void setBillsByEmpId_0(Collection<BillEntity> billsByEmpId_0) {
        this.billsByEmpId_0 = billsByEmpId_0;
    }
}
