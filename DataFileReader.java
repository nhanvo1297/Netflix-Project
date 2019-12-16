import java.util.*;
import java.io.*;

public class DataFileReader {
		private ArrayList<Media> media= new ArrayList<Media>();
		private ArrayList<Media> masterList = new ArrayList<Media>();
		private ArrayList<Filter> filterList = new ArrayList<Filter>();
		private Scanner scnr;
		private Movie movie;
		private Series series;
		
		DataFileReader(String fileDirect) {
			try {
				scnr = new Scanner(new FileReader(fileDirect));
				media = new ArrayList<Media>();
				String[] media_info;
				
				while(scnr.hasNextLine()) {
					String line = scnr.nextLine();	
					media_info= splitString(line);
					
					if(isMovie(media_info[3])) {
						movie = new Movie(media_info[0], media_info[1], media_info[2], media_info[3], media_info[4]);
						media.add(movie);
					}
					else {
						series= new Series(media_info[0], media_info[1], media_info[2], media_info[3], media_info[4]);
						media.add(series);
					}
				}
			}
				
			catch(FileNotFoundException ex) {
				System.out.println("File not found");
				System.exit(0);
			}
			catch(Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
			finally {
				scnr.close();
			}
			
		}
		
		public ArrayList<Media> getMediaList(){
			return media;
		}
		public DataFileReader getDataFileReader() {
			return this;
		}
		// The method to get data of media from a string.
		public static String[] splitString(String s) {
			String[] str = new String[5];
			String[] line = s.split("\\(|\\)   ");;
			str[0]=line[0];
			str[1]=line[1];
			String[] line1 = line[2].split("\\, ");
			str[2]=line1[0];	
			String[] line2 = line1[1].split("\\  ");
			str[3]= line2[0];
			str[4]= line2[1];
			return str;
			
		}
		
		
		// return true if it is movie. false if it is series
		public static boolean isMovie(String s) {
			
			if(s.toLowerCase().contains("hr")||
					s.toLowerCase().contains("hr")&&s.toLowerCase().contains("m")
					||s.toLowerCase().contains("m")&&!s.toLowerCase().contains("volume")
					||s.equals("") ) {
				return true;
			}
			else {
				return false;
			}
			
		}
		
		// method to find a media with matching title.
		public void filterTitle(String exact_title) {
			boolean found = false;
			for(int i=0;i<media.size();i++) {
				if(media.get(i).getTitle().contains(exact_title)) {
					found=true;
					System.out.println(media.get(i).getTitle()+" "
										+media.get(i).getUserRating()+" "
										+" "+media.get(i).getCaption()+" "+media.get(i).typeOfMedia() +" "+media.get(i).getLength());
				}
			}
			if(found==false) {
				System.out.println("No results is found.");
			}
			
		}
		// Method to print the desired list of media after filtered
		public void printMedia() {
			for(Media m : media) {
				int numberOfFilterWorked = 0;
				
				for(Filter f : filterList) {
					if(f.getOperator().equals("genre = ")&&f.getTarget().equals("movie")) {
						if(isMovie(m)) {
							numberOfFilterWorked++;
						}
						
	
					}
					else if(f.getOperator().equals("genre = ")&&f.getTarget().equals("series")) {
						if(!isMovie(m)) {
							numberOfFilterWorked ++;
						}
						
					}
					else if (f.getOperator().equals("rating >")) {
						double ratingMedia =  parseRatingToDouble(m.getUserRating());
						if(ratingMedia>Double.parseDouble(f.getTarget())) {
							numberOfFilterWorked++;
						}
						
					}
					else if (f.getOperator().equals("rating <")) {
						double ratingMedia =  parseRatingToDouble(m.getUserRating());
						if(ratingMedia<Double.parseDouble(f.getTarget())) {
							numberOfFilterWorked++;
						}
						
					}
					
					
					else if(f.getOperator().equals("year >")) {
						int[] year = yearToInt(m.getYear());
						if(year.length==1&&year[0]>Integer.parseInt(f.getTarget())) {
							numberOfFilterWorked++;
						}
						else if(year.length==2&&year[0]>Integer.parseInt(f.getTarget())||
								year.length==2&&year[1]>Integer.parseInt(f.getTarget())) {
							numberOfFilterWorked++;
						}
						
					}
					else if(f.getOperator().equals("year <")) {
						int[] year = yearToInt(m.getYear());
						if(year.length==1&&year[0]<Integer.parseInt(f.getTarget())) {
							numberOfFilterWorked++;
						}
						else if(year.length==2&&year[0]<Integer.parseInt(f.getTarget())||
								year.length==2&&year[1]<Integer.parseInt(f.getTarget())||
								year.length==2&&year[1]>Integer.parseInt(f.getTarget())
								&&year.length==2&&year[0]<Integer.parseInt(f.getTarget())) {
							numberOfFilterWorked++;
						}
					}
					else if(f.getOperator().equals("title contain")) {
						if(m.getTitle().contains(f.getTarget().toLowerCase())) {
							numberOfFilterWorked++;
						}
					}
					else if(f.getOperator().equals("title >")) {
						char charOfMedia = m.getTitle().toLowerCase().charAt(0);
						char charOfTarget = f.getTarget().toLowerCase().charAt(0);
						if(charOfMedia>=charOfTarget) {
							numberOfFilterWorked++;
						}
					}
					else if(f.getOperator().equals("title <")) {
						char charOfMedia = m.getTitle().toLowerCase().charAt(0);
						char charOfTarget = f.getTarget().toLowerCase().charAt(0);
						if(charOfMedia<=charOfTarget) {
							numberOfFilterWorked++;
						}
					}		
				}
			
				if(numberOfFilterWorked==filterList.size()) {
					masterList.add(m);
				}
				
				
				
			}
			
			if(masterList.size()==0) {
				System.out.println("No results is found.");
			}
			
			for(Media m : masterList) {		
				System.out.println(m.toString());
			}			
		}
		
		// MEthod to convert String year to integer year in form of an array.
		public int[] yearToInt(String s) {
			
			if(s.equals("")) {
				int[] year = {0};
				return year;
			}
			
			else if(s.contains("-")) {
				int[] year = new int[2];
				String[] str= s.split("\\-");
				year[0]=Integer.parseInt(str[0]);
				year[1]= Integer.parseInt(str[1]);
				return year;
			}
			else {
				int[] year;
				year= new int[1];
				year[0]= Integer.parseInt(s);
				return year;
			}
		}
		// method to check whether the media is movie or not.
		public static boolean isMovie(Media m) {
			
			if(m.typeOfMedia().equals("movie")) {
				return true;
			}
			return false;
		}
		// Method to add filter
		public void addFilter(Filter filter) {
			if(!filterList.contains(filter)) {
				filterList.add(filter);
			}
		} 
		//Method to remove filter
		public void removeFilter(Filter filter) {
			if(filterList.contains(filter)) {
				filterList.remove(filterList.indexOf(filter));
			}
		}
		//Method to convert Rating from String to Double value.
		public double parseRatingToDouble(String s) {
			double rating;
			if(s.isBlank()) {
				rating=0.0;
			}
			else {
			String[] str = s.split("\\ ");
			rating = Double.parseDouble(str[0]);
			}
			return rating;
		}	
}
