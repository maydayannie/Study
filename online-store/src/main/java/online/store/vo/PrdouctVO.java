package online.store.vo;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

public class PrdouctVO {
	private String prodId;
	private String notes;
	private int price;
	private String prodName;
	private String fileName;
	private MultipartFile file;
	
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "PrdouctVO [prodId=" + prodId + ", notes=" + notes + ", price=" + price + ", prodName=" + prodName + "]";
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
