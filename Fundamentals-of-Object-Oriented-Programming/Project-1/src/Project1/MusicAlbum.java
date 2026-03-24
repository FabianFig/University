package Project1;

public class MusicAlbum extends Product {
	private String artist;
	private int globalSales;
	private int tracks;
	private double duration;
	private String genre;

	public MusicAlbum(int id, int releaseYear, String artist, String title, int globalSales, int tracks,
			double duration, String genre) {
		super(id, title, releaseYear);
		this.artist = artist;
		this.globalSales = globalSales;
		this.tracks = tracks;
		this.duration = duration;
		this.genre = genre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getGlobalSales() {
		return globalSales;
	}

	public void setGlobalSales(int globalSales) {
		this.globalSales = globalSales;
	}

	public int getTracks() {
		return tracks;
	}

	public void setTracks(int tracks) {
		this.tracks = tracks;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
