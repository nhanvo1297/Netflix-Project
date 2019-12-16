
public class Series extends Media {
	
	public Series(String title, String yearStr,String userRating, String season, String caption){
		super(title, yearStr, userRating, season, caption);
	}
	
	public String typeOfMedia() {
		return "series";
	}
	
}
