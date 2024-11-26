package com.java.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import com.java.config.Constants;
import com.java.model.File;


public class UploadDAO {

	private static final int BUFFER_SIZE = 4096; 
	private Connection getConnection () throws ClassNotFoundException {
		Connection con = null;
		Class.forName(Constants.dbDriver);
		try {
			con = DriverManager.getConnection(Constants.jdbcURL,Constants.jdbcUsername,Constants.jdbcPassword);
			if (con != null) {
				System.out.println("Connect Success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}
	public File getFileById(int upload) throws SQLException {
		File filedata = null;
		try {
			Connection con = getConnection();
			String sql="SELECT file_name, file_data FROM files_upload WHERE upload_id = ?\"";
			PreparedStatement st =con.prepareStatement(sql);
			st.setInt(1, upload);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String fileName = rs.getString("file_name");
				Blob fileBlob = rs.getBlob("file_data");
				InputStream fileStream = fileBlob.getBinaryStream();
                int fileSize = (int) fileBlob.length();
                filedata = new File(fileName, fileStream, fileSize);
				
			}
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}

