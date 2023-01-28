import java.util.ArrayList;

public class User extends Person {
    private ArrayList<String> ratedFilms = new ArrayList<String>();//I stored all films that this user rated before
    User(String id,String name,String surname,String country){
        super(id,name,surname,country);
    }
    public void addRatedFilms(String filmIds){
        this.ratedFilms.add(filmIds);
    }
    public ArrayList<String> getRatedFilms(){
        return this.ratedFilms;
    }

}

