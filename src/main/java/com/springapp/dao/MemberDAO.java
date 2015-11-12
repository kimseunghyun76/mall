package com.springapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import webDevProject.db.ConnectionDB;
import webDevProject.dto.MemberDTO;
 
public class MemberDAO 
{
    Connection connection;
    Statement stmt;
    private int noOfRecords;
         
    public MemberDAO() { }
     
    private static Connection getConnection() throws SQLException, ClassNotFoundException
    {
        Connection con = ConnectionDB.getInstance().getConnection();
        return con;
    }

    public List<MemberDTO> viewEachMember(String userId)
    {
    	String query = "SELECT * FROM member WHERE userid=\"" + userId + "\"";
        System.out.println(query);
        List<MemberDTO> list = new ArrayList<MemberDTO>();
        MemberDTO memberDto = null;
        try 
        {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
            	memberDto = new MemberDTO();
            	memberDto.setId(rs.getString("userid"));
            	memberDto.setName(rs.getString("username"));
            	memberDto.setGrpName(rs.getString("groupName"));
            	memberDto.setShopName(rs.getString("shopName"));
            	memberDto.setTel(rs.getString("tel"));
            	memberDto.setCategory(rs.getString("category"));
            	memberDto.setRole(rs.getString("role"));
                list.add(memberDto);
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
    
    public List<MemberDTO> viewAllMembers(int offset, int noOfRecords, String[] allData)
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
					case 0: eachCondition = "category";break;
					case 1: eachCondition = "groupName";break;
					case 2: eachCondition = "shopName";break;
					case 3: eachCondition = "userid";break;
					case 4: eachCondition = "role";break;
					}
					
					whereCondition += eachCondition + "=\"" + allData[i] + "\" ";
				}
				first = false;
			}
		}
    	
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM member "
        		+ whereCondition + "limit " + offset + ", " + noOfRecords;
        System.out.println(query);
        List<MemberDTO> list = new ArrayList<MemberDTO>();
        MemberDTO memberDto = null;
        
        try 
        {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
            	memberDto = new MemberDTO();
            	memberDto.setId(rs.getString("userid"));
            	memberDto.setName(rs.getString("username"));
            	memberDto.setGrpName(rs.getString("groupName"));
            	memberDto.setShopName(rs.getString("shopName"));
            	memberDto.setTel(rs.getString("tel"));
            	memberDto.setCategory(rs.getString("category"));
            	memberDto.setRole(rs.getString("role"));
                list.add(memberDto);
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