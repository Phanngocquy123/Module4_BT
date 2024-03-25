package com.example.jpa_2.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "bill", schema = "warehouse", catalog = "")
public class BillEntity {
    private long billId;
    private String billCode;
    private boolean billType;
    private Timestamp created;
    private Timestamp authDate;
    private short billStatus;
    private EmployeeEntity employeeByEmpIdCreated;
    private EmployeeEntity employeeByEmpIdAuth;
    private Collection<BillDetailEntity> billDetailsByBillId;

    @Id
    @Column(name = "Bill_id")
    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    @Basic
    @Column(name = "Bill_Code")
    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    @Basic
    @Column(name = "Bill_Type")
    public boolean isBillType() {
        return billType;
    }

    public void setBillType(boolean billType) {
        this.billType = billType;
    }

    @Basic
    @Column(name = "Created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "Auth_date")
    public Timestamp getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Timestamp authDate) {
        this.authDate = authDate;
    }

    @Basic
    @Column(name = "Bill_Status")
    public short getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(short billStatus) {
        this.billStatus = billStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillEntity that = (BillEntity) o;

        if (billId != that.billId) return false;
        if (billType != that.billType) return false;
        if (billStatus != that.billStatus) return false;
        if (billCode != null ? !billCode.equals(that.billCode) : that.billCode != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (authDate != null ? !authDate.equals(that.authDate) : that.authDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (billId ^ (billId >>> 32));
        result = 31 * result + (billCode != null ? billCode.hashCode() : 0);
        result = 31 * result + (billType ? 1 : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (authDate != null ? authDate.hashCode() : 0);
        result = 31 * result + (int) billStatus;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Emp_id_created", referencedColumnName = "Emp_Id", nullable = false)
    public EmployeeEntity getEmployeeByEmpIdCreated() {
        return employeeByEmpIdCreated;
    }

    public void setEmployeeByEmpIdCreated(EmployeeEntity employeeByEmpIdCreated) {
        this.employeeByEmpIdCreated = employeeByEmpIdCreated;
    }

    @ManyToOne
    @JoinColumn(name = "Emp_id_auth", referencedColumnName = "Emp_Id", nullable = false)
    public EmployeeEntity getEmployeeByEmpIdAuth() {
        return employeeByEmpIdAuth;
    }

    public void setEmployeeByEmpIdAuth(EmployeeEntity employeeByEmpIdAuth) {
        this.employeeByEmpIdAuth = employeeByEmpIdAuth;
    }

    @OneToMany(mappedBy = "billByBillId")
    public Collection<BillDetailEntity> getBillDetailsByBillId() {
        return billDetailsByBillId;
    }

    public void setBillDetailsByBillId(Collection<BillDetailEntity> billDetailsByBillId) {
        this.billDetailsByBillId = billDetailsByBillId;
    }
}
