package Project1;

public class TVShow extends Product {
    private String director;
    private String country;
    private String rating;
    private int numberOfSeasons;
    private String description;

    public TVShow(int id, String title, String director, String country, int releaseYear, String rating, int numberOfSeasons, String description) {
        super(id, title, releaseYear);
        this.director = director;
        this.country = country;
        this.rating = rating;
        this.numberOfSeasons = numberOfSeasons;
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

	public int getNumberOfSeasons() {
		return numberOfSeasons;
	}

	public void setNumberOfSeasons(int numberOfSeasons) {
		this.numberOfSeasons = numberOfSeasons;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    
}
