package com.springapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import webDevProject.db.ConnectionDB;
import webDevProject.dto.ImageDTO;
import webDevProject.dto.MemberDTO;
 
public class ImageDAO 
{
    Connection connection;
    Statement stmt;
    private int noOfRecords;
         
    public ImageDAO() { }
     
    private static Connection getConnection() throws SQLException, ClassNotFoundException
    {
        Connection con = ConnectionDB.getInstance().getConnection();
        return con;
    }

    public List<ImageDTO> viewEachImage(String userId)
    {
    	String query = "SELECT * FROM image WHERE userid=\"" + userId + "\"";
        System.out.println(query);
        List<ImageDTO> list = new ArrayList<ImageDTO>();
        ImageDTO imageDto = null;
        try 
        {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
            	imageDto = new ImageDTO();
            	imageDto.setId(rs.getString("userid"));
            	imageDto.setUrl(rs.getString("imageurl"));
            	imageDto.setConfirm(rs.getString("confirm"));
            	imageDto.setSection(rs.getString("section"));
            	imageDto.setProductInfo(rs.getString("productInfo"));
            	imageDto.setProductCode(rs.getString("productCode"));
            	imageDto.setProductName(rs.getString("productName"));
                list.add(imageDto);
            }
            rs.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
                if(stmt != null)
                {
                	stmt.close();
                }
                if(connection != null)
                {
                	connection.close();
                }
        	}
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public List<ImageDTO> viewAllImages(int offset, int noOfRecords, String[] allData)
    {
    	boolean first = true;
    	String whereCondition = "";
    	String eachCondition = "";
		
    	if(allData != null)
		{
			for(int i = 0; i < allData.length; i++)
			{
				if(allData[i] != null)
				{
					if(first)
					{
						whereCondition = "WHERE ";
					}
					else
					{
						whereCondition += "OR ";
					}
					
					switch(i)
					{
					case 0: eachCondition = "mem.category";break;
					case 1: eachCondition = "mem.groupname";break;
					case 2: eachCondition = "mem.shopname";break;
					case 3: eachCondition = "mem.userid";break;
					case 4: eachCondition = "mem.role";break;
					case 5: eachCondition = "img.section";break;
					}
					
					whereCondition += eachCondition + "=\"" + allData[i] + "\" ";
				}
				first = false;
			}
		}
    	
        String query = "SELECT SQL_CALC_FOUND_ROWS "
        		+ "mem.groupname, mem.shopname, mem.username, mem.userid, mem.tel, img.section, img.imageurl "
        		+ "FROM member AS mem "
        		+ "INNER JOIN image AS img "
        		+ "ON mem.userid = img.userid "
        		+ whereCondition + "limit " + offset + ", " + noOfRecords;
        System.out.println(query);
        List<ImageDTO> list = new ArrayList<ImageDTO>();
        ImageDTO imageDto = null;
        MemberDTO memberDto = null;
        
        try 
        {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
            	imageDto = new ImageDTO();
            	memberDto = new MemberDTO();
            	memberDto.setGrpName(rs.getString("groupname"));
            	memberDto.setShopName(rs.getString("shopname"));
            	memberDto.setName(rs.getString("username"));
            	memberDto.setId(rs.getString("userid"));
            	memberDto.setTel(rs.getString("tel"));
            	imageDto.setSection(rs.getString("section"));
            	imageDto.setUrl(rs.getString("imageurl"));
                list.add(imageDto);
            }
            rs.close();
             
            rs = stmt.executeQuery("SELECT FOUND_ROWS()");
            if(rs.next())
            {
            	this.noOfRecords = rs.getInt(1);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
                if(stmt != null)
                {
                	stmt.close();
                }
                if(connection != null)
                {
                	connection.close();
                }
        	}
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
        return list;
    }
 
    public int getNoOfRecords() 
    {
        return noOfRecords;
    }
}