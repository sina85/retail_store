
package com.dao;

import com.db.DbConnection;
import com.model.Products;
import com.model.Stores;
import com.model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {
    
     Connection con=DbConnection.getConnection();
    
    
    public void  addUser(Users users)
    {
        try{
        String query="insert into users(name,password,latitude,longitude,type) values(?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(query);
        
        ps.setString(1, users.getName());
         
        ps.setString(2, users.getPassword());
         
         
        ps.setFloat(3, users.getLatitude());
         
        ps.setFloat(4, users.getLongitude());
         
        ps.setString(5, users.getType());
       
       
        ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    public void  updateUser(Users user)
    {
        try{
        String query="update users set name=?, password=?, latitude=?, longitude=?, type=? where id=?";
        PreparedStatement ps=con.prepareStatement(query);
        
        ps.setString(1, user.getName());
         
        ps.setString(2, user.getPassword());
         
        ps.setFloat(3, user.getLatitude());
         
        ps.setFloat(4, user.getLongitude());
        
        ps.setString(5, user.getType());
        
        ps.setInt(6, user.getId());
         
        ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    
    public List<Users> getUsers()
    {
     List<Users> list=new ArrayList<>();   
        try
        {
            String query="select *from users"; 
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
               Users user= new Users();
               user.setId(rs.getInt("id"));
               user.setName(rs.getString("name"));
               user.setPassword(rs.getString("password"));
               user.setLatitude(rs.getFloat("latitude"));
               user.setLongitude(rs.getFloat("longitude"));
               user.setType(rs.getString("type"));
               
               list.add(user);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
return list;
    } 
    
    
    
    
    
    
    public Users getById(int id)
    {
        Users user= new Users();
        try{
        String query="select *from users where id=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
               user.setId(rs.getInt("id"));
               user.setName(rs.getString("name"));
               user.setPassword(rs.getString("password"));
               user.setLatitude(rs.getFloat("latitude"));
               user.setLongitude(rs.getFloat("longitude"));
               user.setType(rs.getString("type"));
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }
    
    
    
       public List<Products> getProductsByStoreId(int id)
    {
        List<Products> list= new ArrayList<>();
        try{
        String query="select *from products where storeId=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
            Products products= new Products();
                    
              
               products.setName(rs.getString("productName"));
               products.setNumOfUnits(rs.getInt("numberOfUnits"));
               products.setPricePerUnit(rs.getInt("pricePerUnit"));
               
               list.add(products);
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
       
       
       
       
       
       
        public List<Products> getProductsForManager(int id)
    {
        List<Products> list= new ArrayList<>();
        try{
            //SELECT * FROM products WHERE storeId IN (SELECT id FROM stores WHERE managerId = 20); 
        String query="select *from products where storeId IN (select id from stores where managerId =?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
            Products products= new Products();
                    
               products.setStoreId(rs.getInt("storeId"));
               products.setName(rs.getString("productName"));
               products.setNumOfUnits(rs.getInt("numberOfUnits"));
               products.setPricePerUnit(rs.getInt("pricePerUnit"));
               
               list.add(products);
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
       
       
       
       
       
    
    
    
    
    public List<Stores> getStores()
    {
     List<Stores> list=new ArrayList<>();   
        try
        {
            String query="select *from stores"; 
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Stores stores= new Stores();
               stores.setId(rs.getInt("id"));
               stores.setName(rs.getString("name"));
               
               stores.setLatitude(rs.getFloat("latitude"));
               stores.setLongitude(rs.getFloat("longitude"));
               stores.setManagerId(rs.getInt("managerId"));
               stores.setEstablishDate(rs.getString("dateEstablished"));
               
               list.add(stores);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
return list;
    } 
    
    
    
    
    
       public List<Products> getAllProducts()
    {
        List<Products> list= new ArrayList<>();
        try{
        String query="select *from products";
        PreparedStatement ps=con.prepareStatement(query);
   
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
            Products products= new Products();
                    
              products.setStoreId(rs.getInt("storeId"));
               products.setName(rs.getString("productName"));
               products.setNumOfUnits(rs.getInt("numberOfUnits"));
               products.setPricePerUnit(rs.getInt("pricePerUnit"));
               
               list.add(products);
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
       
    
    
    
    
}
