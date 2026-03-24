package Project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Manager {
	ArrayList<Product> products;

	public Manager() {
		products = new ArrayList<>();
	}

	public void loadData(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] data = line.split(",");
			int id = Integer.parseInt(data[0]);
			String type = data[1];
			switch (type) {
			case "Movie":
				products.add(new Movie(id, data[2], data[3], data[4], Integer.parseInt(data[5]), data[6],
						Integer.parseInt(data[7]), data[8]));
				break;
			case "TV Show":
				products.add(new TVShow(id, data[2], data[3], data[4], Integer.parseInt(data[5]), data[6],
						Integer.parseInt(data[7].replaceAll("\\D+", "")), data[8]));
				break;
			case "Video Game":
				products.add(new VideoGame(id, data[2], data[3], Integer.parseInt(data[4]), data[5], data[6],
						Double.parseDouble(data[7])));
				break;
			case "Music Album":
				products.add(new MusicAlbum(id, Integer.parseInt(data[2]), data[3], data[4], Integer.parseInt(data[5]),
						Integer.parseInt(data[6]), Double.parseDouble(data[7]), data[8]));
				break;
			}
		}
		reader.close();
	}

	public int getTotalProducts() {
		return products.size();
	}

	public int getTotalMovies() {
		int count = 0;
		for (Product product : products) {
			if (product instanceof Movie) {
				count++;
			}
		}
		return count;
	}

	public int getTotalTVShows() {
		int count = 0;
		for (Product product : products) {
			if (product instanceof TVShow) {
				count++;
			}
		}
		return count;
	}

	public int getTotalVideoGames() {
		int count = 0;
		for (Product product : products) {
			if (product instanceof VideoGame) {
				count++;
			}
		}
		return count;
	}

	public int getTotalMusicAlbums() {
		int count = 0;
		for (Product product : products) {
			if (product instanceof MusicAlbum) {
				count++;
			}
		}
		return count;
	}

	public Product getOldestProduct() {
		if (products.isEmpty()) {
			return null;
		}
		Product oldest = products.get(0);
		for (Product product : products) {
			if (product.getReleaseYear() < oldest.getReleaseYear()) {
				oldest = product;
			}
		}
		return oldest;
	}

	public MusicAlbum getMostPopularMusicAlbum() {
		MusicAlbum mostPopular = null;
		for (Product product : products) {
			if (product instanceof MusicAlbum) {
				MusicAlbum album = (MusicAlbum) product;
				if (mostPopular == null || album.getGlobalSales() > mostPopular.getGlobalSales()) {
					mostPopular = album;
				}
			}
		}
		return mostPopular;
	}

	public VideoGame getMostPopularVideoGame() {
		VideoGame mostPopular = null;
		for (Product product : products) {
			if (product instanceof VideoGame) {
				VideoGame game = (VideoGame) product;
				if (mostPopular == null || game.getCopiesSold() > mostPopular.getCopiesSold()) {
					mostPopular = game;
				}
			}
		}
		return mostPopular;
	}

	public String getMostCommonAgeRating() {
		String[] ratings = new String[100];
		int[] counts = new int[100];
		int uniqueCount = 0;

		for (Product product : products) {
			String rating = null;

			if (product instanceof Movie) {
				rating = ((Movie) product).getRating();
			} else if (product instanceof TVShow) {
				rating = ((TVShow) product).getRating();
			}

			if (rating != null) {
				boolean found = false;
				for (int i = 0; i < uniqueCount; i++) {
					if (ratings[i].equals(rating)) {
						counts[i]++;
						found = true;
						break;
					}
				}

				if (!found) {
					ratings[uniqueCount] = rating;
					counts[uniqueCount] = 1;
					uniqueCount++;
				}
			}
		}

		int maxIndex = 0;
		for (int i = 1; i < uniqueCount; i++) {
			if (counts[i] > counts[maxIndex]) {
				maxIndex = i;
			}
		}

		return ratings[maxIndex];
	}

	public Movie getShortestMovie() {
		Movie shortest = null;
		for (Product product : products) {
			if (product instanceof Movie) {
				Movie movie = (Movie) product;
				if (shortest == null || movie.getDuration() < shortest.getDuration()) {
					shortest = movie;
				}
			}
		}
		return shortest;
	}

	public MusicAlbum getShortestMusicAlbum() {
		MusicAlbum shortest = null;
		for (Product product : products) {
			if (product instanceof MusicAlbum) {
				MusicAlbum album = (MusicAlbum) product;
				if (shortest == null || album.getDuration() < shortest.getDuration()) {
					shortest = album;
				}
			}
		}
		return shortest;
	}
}
