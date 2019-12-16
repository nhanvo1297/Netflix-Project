

public abstract class Media {
	protected String title;
	protected String rating;
	protected String yearStr;
	protected String length; // could be be season |volumes| series| episodes| or x-hr-y-m.
	protected String caption;
	
	protected Media(String title,String yearStr, String rating,  String length, String caption) {
		this.title=title;
		this.yearStr= yearStr;
		this.rating=rating;
		this.length=length;
		this.caption=caption;
	}
	
	// method to return year : string.
	public String getYear() {
		return yearStr;
	}

	/* Method to get title of the media.
	 */
	public String getTitle() {
		return title;
	}
	
	//
	public String getUserRating() {
		
		return rating;
	}
	
	public String getLength() {
		return length;
	}
	public String getCaption() {
		return caption;
	}
	public abstract String typeOfMedia();
	public String toString() {
		return title+" ("+ yearStr+") "+rating+" "+ ", "+length+" "+caption;
	}
	
}
