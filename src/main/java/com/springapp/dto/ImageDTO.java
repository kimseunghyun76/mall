package com.springapp.dto;

public class ImageDTO 
{
    private String userid;
    private String imageurl;
    private String confirm;
    private String section;
    private String productInfo;
    private String productCode;
    private String productName;
 
    public ImageDTO() { super(); }
    
    public ImageDTO(String userid, String imageurl, String confirm, String section, 
    		String productInfo, String productCode, String productName ) 
    {
        super();
        
        this.userid = userid;
        this.imageurl = imageurl;
        this.confirm = confirm;
        this.section = section;
        this.productInfo = productInfo;
        this.productCode = productCode;
        this.productName = productName;
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

        ImageDTO other = (ImageDTO) obj;
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
        
        if (confirm == null) 
        {
            if (other.confirm != null)
            {
            	return false;
            }
        } 
        else if (!confirm.equals(other.confirm))
        {
        	return false;
        }
        
        if (imageurl == null) 
        {
            if (other.imageurl != null)
            {
            	return false;
            }
        } 
        else if (!imageurl.equals(other.imageurl))
        {
        	return false;
        }

        if (section == null) 
        {
        	if (other.section != null)
        	{
        		return false;
        	}
        } 
        else if (!section.equals(other.section))
        {
        	return false;
        }

        if (productInfo == null) 
        {
            if (other.productInfo != null)
            {
            	return false;
            }
        } 
        else if (!productInfo.equals(other.productInfo))
        {
        	return false;
        }

        if (productCode == null) 
        {
            if (other.productCode != null)
            {
            	return false;
            }
        } 
        else if (!productCode.equals(other.productCode))
        {
        	return false;
        }
        
        if (productName == null) 
        {
            if (other.productName != null)
            {
            	return false;
            }
        } 
        else if (!productName.equals(other.productName))
        {
        	return false;
        }

        return true;
    }
 
    public String getId() 
    {
        return userid;
    }
 
    public String getConfirm() 
    {
        return confirm;
    }
 
    public String getUrl() 
    {
        return imageurl;
    }

    public String getSection() 
    {
        return section;
    }

    public String getProductInfo() 
    {
        return productInfo;
    }    
    
    public String getProductCode() 
    {
        return productCode;
    }

    public String getproductName() 
    {
        return productName;
    }
    
//    @Override
//    public int hashCode() 
//    {
//    	int result = 1;
//        final int prime = 31;
//
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
//        result = prime * result + ((confirm == null) ? 0 : confirm.hashCode());
//        result = prime * result + ((url == null) ? 0 : url.hashCode());
//        result = prime * result + ((section == null) ? 0 : section.hashCode());
//        result = prime * result + ((productInfo == null) ? 0 : productInfo.hashCode());
//        result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
//        result = prime * result + ((productName == null) ? 0 : productName.hashCode());
//        
//        return result;
//    }
 
    public void setId(String id) 
    {
        this.userid = id;
    }
 
    public void setConfirm(String confirm) 
    {
        this.confirm = confirm;
    }
 
    public void setUrl(String url) 
    {
        this.imageurl = url;
    }

    public void setSection(String section) 
    {
        this.section = section;
    }
    
    public void setProductInfo(String productInfo) 
    {
        this.productInfo = productInfo;
    }
    
    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }
    
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }
    
    @Override
    public String toString() 
    {
        return "MemberDTO [ID=" + userid + ", Password=" + imageurl 
        		+ ", Name=" + confirm + ", Group Name =" + section
        		+ ", Shop Name=" + productInfo + ", Category =" + productCode
        		+ ", Tel=" + productName + "]";
    }
}