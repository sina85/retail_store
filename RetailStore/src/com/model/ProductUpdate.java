
package com.model;

import java.sql.Date;


public class ProductUpdate {
    private int updateNumber;
    private int managerId;
    private int storeId;
    private String productName;
    private Date date;

    public int getUpdateNumber() {
        return updateNumber;
    }

    public void setUpdateNumber(int updateNumber) {
        this.updateNumber = updateNumber;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
