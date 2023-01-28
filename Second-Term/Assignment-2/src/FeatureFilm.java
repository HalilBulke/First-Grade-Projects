public class FeatureFilm extends Film {
    private String releaseDate;
    private String budget;
    private String writers;
    private String filmGenre;
    private double ratingScore;
    private int raters;

    FeatureFilm(String filmId,String filmTitle,String Language,String directors,String Runtime,String country,String cast,
                String filmGenre,String releaseDate,String writers, String budget){
        super(filmId,filmTitle,Language,directors,Runtime,country,cast);    //I used superclass constructor
        this.setFilmGenre(filmGenre);
        this.setReleaseDate(releaseDate);
        this.setWriters(writers);
        this.setBudget(budget);

    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getBudget(){return budget;}

    public String[] getWriters() {
        return writers.split(",");
    }   //These method returns list because there
                                                                        //might be several writers for a film
    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getFilmGenre() {
        return filmGenre;
    }

    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }
    public String getRatingScore() {    //These method returns films rating score as a string with comma if needed
        if(this.ratingScore==0){return "0";}
        else if (String.format("%1.1f", this.ratingScore / raters).substring(2).equals("0")){
            return String.format("%1.0f", this.ratingScore/raters);}
        else{
            return String.format("%1.1f", this.ratingScore/raters).replace(".",",");}
    }

    public void setRatingScore(int ratingScore) {
        this.ratingScore += ratingScore;
        this.raters+=1; }
    public void editRatingScore(int oldest,int newest) {    //These method used when a user editing her/his scores
        this.ratingScore -= oldest;
        this.ratingScore += newest; }
    public void removeRatingScore(int oldest) {             //These method used when a user removing her/his scores
        this.ratingScore -= oldest;
        this.raters-=1;}
    public int getRaters() {
        return this.raters;
    }       //These method returns number of users that rate this film
    public double getValueScore() {
        return this.ratingScore;
    }

}




