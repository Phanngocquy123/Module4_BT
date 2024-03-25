package com.example.jpa_2.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "product", schema = "warehouse", catalog = "")
public class ProductEntity {
    private String productId;
    private String productName;
    private String manufacturer;
    private Timestamp created;
    private short batch;
    private int quantity;
    private Boolean productStatus;
    private Collection<BillDetailEntity> billDetailsByProductId;

    private List<BillDetailEntity> billDetails = new ArrayList<>();

    public ProductEntity() {
    }

    public ProductEntity(String productId) {
        this.productId = productId;
    }

    @Id
    @Column(name = "Product_id")
    public String getProductId() {
        return productId;
    }


    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "Product_Name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "Manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
    @Column(name = "Batch")
    public short getBatch() {
        return batch;
    }

    public void setBatch(short batch) {
        this.batch = batch;
    }

    @Basic
    @Column(name = "Quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "Product_Status")
    public Boolean getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Boolean productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (batch != that.batch) return false;
        if (quantity != that.quantity) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (productStatus != null ? !productStatus.equals(that.productStatus) : that.productStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (int) batch;
        result = 31 * result + quantity;
        result = 31 * result + (productStatus != null ? productStatus.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<BillDetailEntity> getBillDetailsByProductId() {
        return billDetailsByProductId;
    }

    public void setBillDetailsByProductId(Collection<BillDetailEntity> billDetailsByProductId) {
        this.billDetailsByProductId = billDetailsByProductId;
    }
}
