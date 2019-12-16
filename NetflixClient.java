import java.util.*;



public class NetflixClient {
	public static void printFilterOption() {
		System.out.println("0. Exit"
						+  "1. genre = movie \n"
						+  "2. genre = series\n"
						+  "3. title =_______(exact title match)\n"
						+  "4. year > _______\n"
						+  "5. year < _______\n"
						+  "6. rating > ______\n"
						+  "7. rating <________\n"
						+  "8. print the list of Media\n");
	}
	
	
	
	public static void main(String[] args) {
		DataFileReader data = new DataFileReader("/Users/nhan/Documents/workspace/Netflix/src/NetflixUSA_Oct15.txt");
		
		while(true) {
		Scanner input = new Scanner(System.in);
		NetflixClient.printFilterOption();
		System.out.println("Choose your filter option: ");
		int option = input.nextInt();
		switch(option) {
		case 1:
			data.addFilter(new Filter("genre = ","movie"));
			break;
		case 2: 
			data.addFilter(new Filter("genre = ","series"));
			break;
		case 3:
			System.out.println("Enter exact title to find");
			String title = input.nextLine();
			data.filterTitle(title);
			break;
		case 4:
			input.nextLine();
			System.out.println("Enter the year to filter");
			String year1 = input.nextLine();
			data.addFilter(new Filter("year >",year1));
			break;
		case 5:
			input.nextLine();
			System.out.println("Enter the year to filter");
			String year2 = input.nextLine();
			data.addFilter(new Filter("year <",year2));
			break;
		case 6:
			input.nextLine();
			System.out.println("Enter the rating to filter");
			String rating1 = input.nextLine();
			data.addFilter(new Filter("rating >",rating1));
			break;
		case 7:
			input.nextLine();
			System.out.println("Enter the rating to filter");
			String rating2 = input.nextLine();
			data.addFilter(new Filter("rating <",rating2));
			break;
		case 8:
			data.printMedia();
			break;
		case 9:
			System.out.println("Choose filter number to remove:");
			
		}
		
		}

		
	}

}
