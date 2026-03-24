package Project1;

public class Movie extends Product {
	private String director;
	private String country;
	private String rating;
	private int duration;
	private String description;

	public Movie(int id, String title, String director, String country, int releaseYear, String rating, int duration,
			String description) {
		super(id, title, releaseYear);
		this.director = director;
		this.country = country;
		this.rating = rating;
		this.duration = duration;
		this.description = description;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
