
package com.dao;

import com.db.DbConnection;
import com.model.Order;
import com.model.ProductUpdate;
import com.model.Products;
import com.model.SupplyRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    


public class OrderDao {
     Connection con=DbConnection.getConnection();
     public void  addOrder(Order order)
    {
        try {
        String query="insert into orders(customerId, storeId, productName, unitsOrdered, orderTime) values(?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(query);
        
        ps.setInt(1, order.getCustomerId());
         
        ps.setInt(2, order.getStoreId());
         
        ps.setString(3, order.getProductName());
         
        ps.setInt(4, order.getUnitsOrdered());
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
         
        ps.setString(5, dtf.format(now));
       
        ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
     
     
     
     
     
     
     
     
     
     
     public void  updateProduct(Products products)
    {
        try{
        String query="update products set numberOfUnits=?, pricePerUnit=? where productName=? and storeId=?";
        PreparedStatement ps=con.prepareStatement(query);
         
        ps.setInt(1, products.getNumOfUnits());
         
        ps.setInt(2, products.getPricePerUnit());
         
        ps.setString(3, products.getName());
        
        ps.setInt(4, products.getStoreId());
        
        ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
     
     
     
     
     
     
     
     
     public Products getProductByNameAndId(String name, int id)
    {
        Products products= new Products();
        try{
        String query="select * from products where productName=? and storeId=?";
        PreparedStatement ps=con.prepareStatement(query);
        
        ps.setString(1,name);
        ps.setInt(2, id);
        
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
               products.setStoreId(rs.getInt("storeId"));
               products.setName(rs.getString("productName"));
               products.setNumOfUnits(rs.getInt("numberOfUnits"));
               products.setPricePerUnit(rs.getInt("pricePerUnit"));
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return products;
    }
     
     public Products getProductByName(String name)
    {
        Products products= new Products();
        try{
        String query="select * from products where productName=?";
        PreparedStatement ps=con.prepareStatement(query);
        
        ps.setString(1,name);        
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
               products.setStoreId(rs.getInt("storeId"));
               products.setName(rs.getString("productName"));
               products.setNumOfUnits(rs.getInt("numberOfUnits"));
               products.setPricePerUnit(rs.getInt("pricePerUnit"));
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return products;
    }
     
     
     
    public void  addProductUpdate(ProductUpdate update)
    {
        try{
        String query="insert into productupdates(managerId,storeId,productName,updateOn) values(?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(query);
        
        ps.setInt(1, update.getManagerId());
         
        ps.setInt(2, update.getStoreId());
         
        ps.setString(3, update.getProductName());
         
        ps.setDate(4, update.getDate());
        
        ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
 
    
    
    
    public List<ProductUpdate> getLast5Updates(int id)
    {
     List<ProductUpdate> list=new ArrayList<>();   
        try
        {
            String query="SELECT * FROM (SELECT * FROM productupdates WHERE managerId =? ORDER BY updateNumber DESC LIMIT 5) AS sub ORDER BY updateNumber ASC;"; 
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
               ProductUpdate update= new ProductUpdate();
               
               update.setUpdateNumber(rs.getInt("updateNumber"));
               update.setManagerId(rs.getInt("managerId"));
               update.setStoreId(rs.getInt("storeId"));
               update.setProductName(rs.getString("productName"));
              update.setDate(rs.getDate("updateOn"));
              
               list.add(update);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
return list;
    } 
    
    
    
    
    
    
    
    
    
    
    public List<Order> getLast5Orders(int id)
    {
     List<Order> list=new ArrayList<>();   
        try
        {
            String query="SELECT * FROM (SELECT * FROM orders WHERE customerId =? ORDER BY orderNumber DESC LIMIT 5) AS sub ORDER BY orderNumber ASC"; 
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
               Order order= new Order();
               
               order.setOrderNumber(rs.getInt("orderNumber"));
               order.setCustomerId(rs.getInt("customerId"));
               order.setStoreId(rs.getInt("storeId"));
               order.setProductName(rs.getString("productName"));
               order.setOrderDate(rs.getDate("orderTime"));
              order.setUnitsOrdered(rs.getInt("unitsOrdered"));
               list.add(order);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
return list;
    } 
    
    
    
    
    
    
    
    
    public List<String> get5PopularProducts()
    {
       List<String> list= new ArrayList();
        try{
        String query="SELECT productName FROM orders GROUP BY productName ORDER BY SUM(unitsOrdered) DESC LIMIT 5";
        PreparedStatement ps=con.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
              String name = rs.getString("productName");
              list.add(name);
     
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    
    
    
    
    public List<Integer> get5PopularCustomers()
    {
       List<Integer> list= new ArrayList();
        try{
        String query="SELECT customerId FROM orders GROUP BY customerId ORDER BY COUNT(customerId) DESC LIMIT 5";
        PreparedStatement ps=con.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
              int id = rs.getInt("customerId");
              list.add(id);
     
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
   
    
    
    
    
    public void  addSupplyRequest(SupplyRequest request)
    {
        try{
        String query="insert into productsupplyrequests(managerId,warehouseId,storeId,productName, unitsRequested) values(?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(query);
        
        ps.setInt(1, request.getManagerId());
         
        ps.setInt(2, request.getWarehouseId());
         
        ps.setInt(3, request.getStoreId());
         
        ps.setString(4, request.getProductName());
         
        ps.setInt(5, request.getUnits());
       
       
        ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
}
