package egovframework.sample.service;

import org.springframework.web.multipart.MultipartFile;

public class TestVo {
	private String fileName;
	private MultipartFile uploadFile;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	@Override
	public String toString() {
		return "TestVo [fileName=" + fileName + ", uploadFile=" + uploadFile + "]";
	}
	
	

	
}
