package com.java.controler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.UploadDAO;
import com.java.model.File;

public class UploadFileControler  extends HttpServlet{ 
	  private static final int BUFFER_SIZE = 4096;	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			int upLoadId=Integer.parseInt(req.getParameter("id"));
		UploadDAO filedao = new UploadDAO();
		try {
            File fileData = filedao.getFileById(upLoadId);

            if (fileData != null) {
// doan nay e k hieu 
                ServletContext context = getServletContext();
                String mimeType = context.getMimeType(fileData.getFileName());
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }

                resp.setContentType(mimeType);
                resp.setContentLength(fileData.getFileSize());
                resp.setHeader("Content-Disposition", 
                    String.format("attachment; filename=\"%s\"", fileData.getFileName()));

            
                try (InputStream inputStream = fileData.getFileStream();
                     OutputStream outputStream = resp.getOutputStream()) {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                resp.getWriter().print("File not found for the ID: " + upLoadId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            resp.getWriter().print("SQL Error: " + ex.getMessage());
        }
		}
		
	
}
