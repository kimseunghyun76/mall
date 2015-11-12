package com.springapp.servlet;

import java.io.File;
import java.io.IOException;
//import java.util.List;
//import java.util.Iterator;
import java.util.Enumeration;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ImageFilePreviewServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

    private static final String DATA_DIRECTORY = "C:\\Work\\db-manager\\webDevProject\\WebContent\\upload\\temp";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 5;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException 
    {
    	File file = null;

    	String fileName = "";
    	String fileLength = "";
    	String cnt = "";
    	
        if(ServletFileUpload.isMultipartContent(request))
        {
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setSizeThreshold(MAX_MEMORY_SIZE);
	        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	        
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setSizeMax(MAX_REQUEST_SIZE);
	        
	        try 
	        {
	        	int i = 0;
	        	MultipartRequest multiReq = new MultipartRequest(
	        			request, DATA_DIRECTORY, MAX_REQUEST_SIZE, "UTF-8", new DefaultFileRenamePolicy()
	        		);
	        	cnt = multiReq.getParameter("cnt");
				
	    		Enumeration<?> enumFiles = multiReq.getFileNames();
				
				while(enumFiles.hasMoreElements())
				{
					String name = (String)enumFiles.nextElement(); 
				    file = multiReq.getFile(name);                       
				    String str = file.getName();
				    i++;
				    fileName += "&fileName" + i + "=" + str;
				    fileLength += "&fileLength" + i + "=" + file.length();
				}
	
	            String url = "/image_preview.jsp?cnt=" + cnt + fileName + fileLength;
	            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	            rd.forward(request, response);

	            System.out.println("URL:" + url);
	        } 
	        catch (Exception ex) 
	        {
	        	request.setAttribute("message", ex);
	            throw new ServletException(ex);
	        }
	    }
        else
        {
        	request.setAttribute("message", "Only file upload request");
        }
    }
}