package com.springapp.servlet;

import java.io.File;
import java.io.IOException;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ImageFileUploadServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

    private static final String DATA_DIRECTORY = "C:\\Work\\db-manager\\webDevProject\\WebContent\\upload";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 5;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException 
    {
        if(ServletFileUpload.isMultipartContent(request))
        {
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setSizeThreshold(MAX_MEMORY_SIZE);
	        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	        
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setSizeMax(MAX_REQUEST_SIZE);
	        
	        try 
	        {
	        	MultipartRequest multiReq = new MultipartRequest(
	        			request, DATA_DIRECTORY, MAX_REQUEST_SIZE, "UTF-8", new DefaultFileRenamePolicy()
	        		);

	        	request.setAttribute("message", "이미지 파일이 저장되었습니다.");
	            RequestDispatcher rd = request.getRequestDispatcher("./image_register.jsp");
	            rd.forward(request, response);
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