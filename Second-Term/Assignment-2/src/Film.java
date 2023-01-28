public class Film { //This is the superclass of all other film types
    private String filmId;
    private String filmTitle;
    private String language;
    private String runtime;
    private String country;
    private String directors;
    private String cast;

    Film(String filmId,String filmTitle,String Language,String directors,String Runtime,String country,String cast){
        this.setFilmId(filmId);
        this.setFilmTitle(filmTitle);
        this.setLanguage(Language);
        this.setDirectors(directors);
        this.setRuntime(Runtime);
        this.setCountry(country);
        this.setCast(cast);
    }


    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String[] getDirectors() { return directors.split(","); }//These method returns list because there
                                                                        //might be several directors for a film
    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String[] getCast() {
        return cast.split(",");
    } //These method returns list because there
                                                                //might be several stars for a film
    public void setCast(String cast) {
        this.cast = cast;
    }

}



