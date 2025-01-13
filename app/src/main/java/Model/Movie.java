package Model;

public class Movie {
    private int id;
    private String name;
    private String releaseDate;
    private String runTime;
    private String genre;
    private String director;
    private String cast;
    private String plot;
    private String language;
    private String country;
    private String boxOffice;
    private String posterUrl;
    private String metacriticScore;

    public Movie() {
    }

    public Movie(int id,String name, String releaseDate, String runTime, String genre, String director, String cast, String plot, String language, String country, String boxOffice, String posterUrl, String metacriticScore) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.runTime = runTime;
        this.genre = genre;
        this.director = director;
        this.cast = cast;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.boxOffice = boxOffice;
        this.posterUrl = posterUrl;
        this.metacriticScore = metacriticScore;
    }

    public Movie(String name, String releaseDate, String runTime, String genre, String director, String cast, String plot, String language, String country, String boxOffice, String posterUrl, String metacriticScore) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.runTime = runTime;
        this.genre = genre;
        this.director = director;
        this.cast = cast;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.boxOffice = boxOffice;
        this.posterUrl = posterUrl;
        this.metacriticScore = metacriticScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getMetacriticScore() {
        return metacriticScore;
    }

    public void setMetacriticScore(String metacriticScore) {
        this.metacriticScore = metacriticScore;
    }
}
