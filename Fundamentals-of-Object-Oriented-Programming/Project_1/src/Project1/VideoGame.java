package Project1;

public class VideoGame extends Product {
	private String platform;
	private String genre;
	private String publisher;
	private double copiesSold;
//    private String description;

	public VideoGame(int id, String title, String platform, int releaseYear, String genre, String publisher,
			double copiesSold) {
		super(id, title, releaseYear);
		this.platform = platform;
		this.genre = genre;
		this.publisher = publisher;
		this.copiesSold = copiesSold;
//        this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public double getCopiesSold() {
		return copiesSold;
	}

	public void setCopiesSold(double copiesSold) {
		this.copiesSold = copiesSold;
	}
}
