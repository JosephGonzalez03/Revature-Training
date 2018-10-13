package examples.pubhub.model;

public class Tag {
	private String nameTag;
	private String isbn13;
	
	// Constructor used when tag specified
	public Tag(String nameTag, String isbn13) {
		this.nameTag = nameTag;
		this.isbn13 = isbn13;
	}
	
	// Constructor used when tag not specified
	public Tag() {
		this.nameTag = null;
		this.isbn13 = null;
	}
	
	public String getNameTag() {
		return this.nameTag;
	}
	
	public void setNameTag(String nameTag) {
		this.nameTag = nameTag;
	}
	
	public String getIsbn13() {
		return this.isbn13;
	}
	
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
}
