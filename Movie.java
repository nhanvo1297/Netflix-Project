
public class Movie extends Media {
	
	Movie(String title,String yearStr ,String userRating, String length, String caption){
		super(title, yearStr ,userRating, length, caption);
	}
	public String typeOfMedia() {
		return "movie";
	}
	
	
}
