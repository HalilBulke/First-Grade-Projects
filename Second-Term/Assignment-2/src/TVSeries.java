public class TVSeries extends Film {
    private String startDate;
    private String endDate;
    private String numberOfSeason;
    private String numberOfEpisodes;
    private String writers;
    private String filmGenre;
    private double ratingScore;
    private int raters;

    TVSeries(String filmId,String filmTitle,String Language,String directors,String Runtime,String country,String cast,
             String filmGenre,String writers,String startDate,String endDate,String numberOfSeason, String numberOfEpisodes){
        super(filmId,filmTitle,Language,directors,Runtime,country,cast); //I used superclass constructor
        this.setFilmGenre(filmGenre);
        this.setWriters(writers);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setNumberOfSeason(numberOfSeason);
        this.setNumberOfEpisodes(numberOfEpisodes);
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNumberOfSeason() {
        return numberOfSeason;
    }

    public void setNumberOfSeason(String numberOfSeason) {
        this.numberOfSeason = numberOfSeason;
    }

    public String getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(String numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public String[] getWriters() {
        return writers.split(",");
    }//These method returns list because there
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
    public String getRatingScore() {
        if(this.ratingScore==0){return "0";} //These method returns films rating score as a string with comma if needed
        else if (String.format("%1.1f", this.ratingScore / raters).substring(2).equals("0")){
            return String.format("%1.0f", this.ratingScore/raters);}
        else{
            return String.format("%1.1f", this.ratingScore/raters).replace(".",",");}
    }

    public double getValueScore() {
        return this.ratingScore;
    }
    public void setRatingScore(int ratingScore) {
        this.ratingScore += ratingScore;
        this.raters+=1;
    }
    public void editRatingScore(int oldest,int newest) {    //These method used when a user editing her/his scores
        this.ratingScore -= oldest;
        this.ratingScore += newest;
    }
    public void removeRatingScore(int oldest) {             //These method used when a user removing her/his scores
        this.ratingScore -= oldest; this.raters-=1;}
    public int getRaters() {    //These method returns number of users that rate this film
        return this.raters;
    }

}

