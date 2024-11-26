package com.java.model;

import java.io.InputStream;

public class File {
	private String fileName;
	private InputStream fileStream;
	private int fileSize;

	public File() {
		super();
	}

	public File(String fileName, InputStream fileStream, int fileSize) {
		super();
		this.fileName = fileName;
		this.fileStream = fileStream;
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getFileStream() {
		return fileStream;
	}

	public void setFileStream(InputStream fileStream) {
		this.fileStream = fileStream;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

}
