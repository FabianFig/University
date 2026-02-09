package Project1;

import java.io.IOException;

public class Driver {

	public static void main(String[] args) {
		Manager manager = new Manager();
		try {
			manager.loadData("C:\\Users\\fabia\\Downloads\\project1dataset.csv");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		System.out.println("Total number of products: " + manager.getTotalProducts());
		System.out.println("Total number of Movies: " + manager.getTotalMovies());
		System.out.println("Total number of TV Shows: " + manager.getTotalTVShows());
		System.out.println("Total number of Video Games: " + manager.getTotalVideoGames());
		System.out.println("Total number of Music Albums: " + manager.getTotalMusicAlbums());
		System.out.println("Oldest product: " + manager.getOldestProduct().getTitle());
		System.out.println("Most popular Music Album: " + manager.getMostPopularMusicAlbum().getTitle());
		System.out.println("Most popular Video Game: " + manager.getMostPopularVideoGame().getTitle());
		System.out.println("Most common age rating: " + manager.getMostCommonAgeRating());
		System.out.println("Shortest Movie: " + manager.getShortestMovie().getTitle());
		System.out.println("Shortest Music Album: " + manager.getShortestMusicAlbum().getTitle());
	}
}
