package com.springapp.dto;

public class MemberDTO 
{
    private String userid;
    private String userpw;
    private String username;
    private String grpName;
    private String shopName;
    private String category;
    private String tel;
    private String role;
 
    public MemberDTO() { super(); }
    
    public MemberDTO(String id, String name) 
    {
    	super();
    	
    	this.userid = id;
    	this.username = name;
    }

    public MemberDTO(String category, String grpName, String shopName) 
    {
        super();
        
        this.category = category;
        this.grpName = grpName;
        this.shopName = shopName;
    }
    
    public MemberDTO(String id, String pw, String name, String role) 
    {
        super();
        
        this.userid = id;
        this.userpw = pw;
        this.username = name;
        this.role = role;
    }
    
    public MemberDTO(String id, String pw, String name, String grpName, 
    		String shopName, String category, String tel,String role) 
    {
        super();
        
        this.userid = id;
        this.userpw = pw;
        this.username = name;
        this.grpName = grpName;
        this.shopName = shopName;
        this.category = category;
        this.tel = tel;
        this.role = role;
    }
 
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
        {
        	return true;
        }
        if (obj == null)
        {
        	return false;
        }
        if (getClass() != obj.getClass())
        {
        	return false;
        }

        MemberDTO other = (MemberDTO) obj;
        if (userid == null) 
        {
            if (other.userid != null)
            {
            	return false;
            }
        } 
        else if (!userid.equals(other.userid))
        {
            return false;
        }
        
        if (username == null) 
        {
            if (other.username != null)
            {
            	return false;
            }
        } 
        else if (!username.equals(other.username))
        {
        	return false;
        }
        
        if (userpw == null) 
        {
            if (other.userpw != null)
            {
            	return false;
            }
        } 
        else if (!userpw.equals(other.userpw))
        {
        	return false;
        }

        if (grpName == null) 
        {
        	if (other.grpName != null)
        	{
        		return false;
        	}
        } 
        else if (!grpName.equals(other.grpName))
        {
        	return false;
        }

        if (shopName == null) 
        {
            if (other.shopName != null)
            {
            	return false;
            }
        } 
        else if (!shopName.equals(other.shopName))
        {
        	return false;
        }

        if (category == null) 
        {
            if (other.category != null)
            {
            	return false;
            }
        } 
        else if (!category.equals(other.category))
        {
        	return false;
        }
        
        if (tel == null) 
        {
            if (other.tel != null)
            {
            	return false;
            }
        } 
        else if (!tel.equals(other.tel))
        {
        	return false;
        }

        if (role == null) 
        {
            if (other.role != null)
            {
            	return false;
            }
        } 
        else if (!role.equals(other.role))
        {
        	return false;
        }
        
        return true;
    }
 
    public String getId() 
    {
        return userid;
    }
 
    public String getName() 
    {
        return username;
    }
 
    public String getPassword() 
    {
        return userpw;
    }

    public String getGrpName() 
    {
        return grpName;
    }

    public String getShopName() 
    {
        return shopName;
    }    
    
    public String getCategory() 
    {
        return category;
    }

    public String getTel() 
    {
        return tel;
    }
    
    public String getRole()
    {
    	return role;
    }
 
//    @Override
//    public int hashCode() 
//    {
//    	int result = 1;
//        final int prime = 31;
//
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
//        result = prime * result + ((name == null) ? 0 : name.hashCode());
//        result = prime * result + ((pw == null) ? 0 : pw.hashCode());
//        result = prime * result + ((grpName == null) ? 0 : grpName.hashCode());
//        result = prime * result + ((shopName == null) ? 0 : shopName.hashCode());
//        result = prime * result + ((category == null) ? 0 : category.hashCode());
//        result = prime * result + ((tel == null) ? 0 : tel.hashCode());
//        result = prime * result + ((role == null) ? 0 : role.hashCode());
//        
//        return result;
//    }
 
    public void setId(String id) 
    {
        this.userid = id;
    }
 
    public void setName(String name) 
    {
        this.username = name;
    }
 
    public void setPassword(String pw) 
    {
        this.userpw = pw;
    }

    public void setGrpName(String grpName) 
    {
        this.grpName = grpName;
    }
    
    public void setShopName(String shopName) 
    {
        this.shopName = shopName;
    }
    
    public void setCategory(String category) 
    {
        this.category = category;
    }
    
    public void setTel(String tel) 
    {
        this.tel = tel;
    }
    
    public void setRole(String role) 
    {
        this.role = role;
    }
    
    @Override
    public String toString() 
    {
        return "MemberDTO [ID=" + userid + ", Password=" + userpw 
        		+ ", Name=" + username + ", Group Name =" + grpName
        		+ ", Shop Name=" + shopName + ", Category =" + category
        		+ ", Tel=" + tel + ", Role=" + role + "]";
    }
}