import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        try { FileWriter Writer = new FileWriter(args[3]);

            ArrayList<Film> films = new ArrayList<Film>();  //I stored all films object in this array list
            ArrayList<FeatureFilm> featureFilms = new ArrayList<FeatureFilm>(); //I stored all feature films object in this array list
            ArrayList<TVSeries> TvSeries = new ArrayList<TVSeries>();   //I stored all Tv series object in this array list

            ArrayList<String> usersIds = new ArrayList<String>();   //I stored all user ids in this array list to using when I need reach one of them
            ArrayList<String> filmIds = new ArrayList<String>();    //I stored all film ids in this array list to using when I need reach one of them
            ArrayList<String> artistIds = new ArrayList<String>();  //I stored all artist ids in this array list to using when I need reach one of them

            HashMap<String, String> allArtists = new HashMap<String, String>(); //This is the id:name and surname map
            HashMap<String, Integer> userRate = new HashMap<String, Integer>(); //This is the user id+film id:users rate for this film map

            ArrayList<Artist> artists = new ArrayList<Artist>();    //I stored all artist object in this array list
            ArrayList<User> users = new ArrayList<User>();          //I stored all user object in this array list

            FileReader commandFile = new FileReader(args[2]);    //This is mine command reader
            Scanner commandReader = new Scanner(commandFile);

            FileReader peopleFile = new FileReader(args[0]);       //This is mine people file reader
            Scanner peopleReader = new Scanner(peopleFile);

            FileReader filmFile = new FileReader(args[1]);          //This is mine film file reader
            Scanner filmReader = new Scanner(filmFile);

            //In this loop I create all film object respect to their types and add them array list respect to their types
            while (filmReader.hasNextLine()) {
                String filmData = filmReader.nextLine();
                String[] filmDatas = filmData.split("\t", 15); //I split the line to reach films features easily
                switch (filmDatas[0]) {
                    case "FeatureFilm:":
                        films.add(new FeatureFilm(filmDatas[1], filmDatas[2], filmDatas[3], filmDatas[4], filmDatas[5], filmDatas[6], filmDatas[7], filmDatas[8], filmDatas[9], filmDatas[10], filmDatas[11]));
                        featureFilms.add(new FeatureFilm(filmDatas[1], filmDatas[2], filmDatas[3], filmDatas[4], filmDatas[5], filmDatas[6], filmDatas[7], filmDatas[8], filmDatas[9], filmDatas[10], filmDatas[11]));
                        filmIds.add(filmDatas[1]);
                        break;
                    //Just for short film I controlled its time lenght
                    case "ShortFilm:":
                        if(Integer.parseInt(filmDatas[5]) <= 40 ){
                            films.add(new ShortFilm(filmDatas[1], filmDatas[2], filmDatas[3], filmDatas[4], filmDatas[5], filmDatas[6], filmDatas[7], filmDatas[8], filmDatas[9], filmDatas[10]));
                            filmIds.add(filmDatas[1]);}else{System.out.println("A Short Film’ runtime should be less ( or equal) than 40 min. ");}
                        break;
                    case "Documentary:":
                        films.add(new Documentary(filmDatas[1], filmDatas[2], filmDatas[3], filmDatas[4], filmDatas[5], filmDatas[6], filmDatas[7], filmDatas[8]));
                        filmIds.add(filmDatas[1]);
                        break;
                    case "TVSeries:":
                        films.add(new TVSeries(filmDatas[1], filmDatas[2], filmDatas[3], filmDatas[4], filmDatas[5], filmDatas[6], filmDatas[7], filmDatas[8], filmDatas[9], filmDatas[10], filmDatas[11], filmDatas[12], filmDatas[13]));
                        TvSeries.add(new TVSeries(filmDatas[1], filmDatas[2], filmDatas[3], filmDatas[4], filmDatas[5], filmDatas[6], filmDatas[7], filmDatas[8], filmDatas[9], filmDatas[10], filmDatas[11], filmDatas[12], filmDatas[13]));
                        filmIds.add(filmDatas[1]);
                        break;
                }
            }
            filmReader.close();

            //In this loop I create all people object respect to their types and add them array list respect to their types
            while (peopleReader.hasNextLine()) {
                String peopleData = peopleReader.nextLine();
                String[] peoplesDatas = peopleData.split("\t", 15);     //I split the line to reach peoples features easily
                switch (peoplesDatas[0]) {
                    case "User:":
                        users.add(new User(peoplesDatas[1], peoplesDatas[2], peoplesDatas[3], peoplesDatas[4]));
                        usersIds.add(peoplesDatas[1]);
                        break;

                    case "Director:":
                        artists.add(new Director(peoplesDatas[1], peoplesDatas[2], peoplesDatas[3], peoplesDatas[4], peoplesDatas[5]));
                        allArtists.put(peoplesDatas[1], peoplesDatas[2] + " " + peoplesDatas[3]);
                        artistIds.add(peoplesDatas[1]);
                        break;

                    case "Writer:":
                        artists.add(new Writer(peoplesDatas[1], peoplesDatas[2], peoplesDatas[3], peoplesDatas[4], peoplesDatas[5]));
                        allArtists.put(peoplesDatas[1], peoplesDatas[2] + " " + peoplesDatas[3]);
                        artistIds.add(peoplesDatas[1]);
                        break;
                    case "Actor:":
                        artists.add(new Actor(peoplesDatas[1], peoplesDatas[2], peoplesDatas[3], peoplesDatas[4], peoplesDatas[5]));
                        allArtists.put(peoplesDatas[1], peoplesDatas[2] + " " + peoplesDatas[3]);
                        artistIds.add(peoplesDatas[1]);
                        break;
                    case "ChildActor:":
                        artists.add(new ChildActor(peoplesDatas[1], peoplesDatas[2], peoplesDatas[3], peoplesDatas[4], peoplesDatas[5]));
                        allArtists.put(peoplesDatas[1], peoplesDatas[2] + " " + peoplesDatas[3]);
                        artistIds.add(peoplesDatas[1]);
                        break;
                    case "StuntPerformer:":
                        artists.add(new StuntPerformer(peoplesDatas[1], peoplesDatas[2], peoplesDatas[3], peoplesDatas[4], peoplesDatas[5], peoplesDatas[6]));
                        allArtists.put(peoplesDatas[1], peoplesDatas[2] + " " + peoplesDatas[3]);
                        artistIds.add(peoplesDatas[1]);
                        break;
                }
            }
            peopleReader.close();
            // In this loop I covered all commands
            while (commandReader.hasNextLine()) {
                String commandData = commandReader.nextLine();
                String[] commandsDatas = commandData.split("\t");       //I split the line to reach commands word easily
                switch (commandsDatas[0]) {
                    case "ADD":
                        ArrayList<String> newArtistIds = new ArrayList<String>();   //This is temporary artists ids array list to check whether the artist id exists or not
                        String[] directors = commandsDatas[5].split(",");     //I create a temporary list to all directors,performers and writers ids and than I add them
                        newArtistIds.addAll(Arrays.asList(directors));             //the temporary newArtistsIds array list
                        String[] performers = commandsDatas[8].split(",");
                        newArtistIds.addAll(Arrays.asList(performers));
                        String[] writers = commandsDatas[11].split(",");
                        newArtistIds.addAll(Arrays.asList(writers));
                        newArtistIds.removeAll(artistIds);      //Then I removed all real artist ids from the temporary newArtistIds array list
                        Writer.write(commandData+"\n"+"\n");    //After removing if newArtistIds is empty this means all ids exist an I can add this film
                        if (newArtistIds.size() == 0 && !(filmIds.contains(commandsDatas[2]))) {
                            films.add(new FeatureFilm(commandsDatas[2], commandsDatas[3], commandsDatas[4], commandsDatas[5], commandsDatas[6], commandsDatas[7], commandsDatas[8], commandsDatas[9], commandsDatas[10], commandsDatas[11], commandsDatas[12]));
                            featureFilms.add(new FeatureFilm(commandsDatas[2], commandsDatas[3], commandsDatas[4], commandsDatas[5], commandsDatas[6], commandsDatas[7], commandsDatas[8], commandsDatas[9], commandsDatas[10], commandsDatas[11], commandsDatas[12]));
                            filmIds.add(commandsDatas[2]);
                            Writer.write("FeatureFilm added successfully"+"\n");
                            Writer.write("Film ID: " + commandsDatas[2]+"\n");
                            Writer.write("Film title: " + commandsDatas[3]+"\n"+"\n");
                            Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");
                            break;
                        } else {
                            Writer.write("Command Failed"+"\n");
                            Writer.write("Film ID: " + commandsDatas[2]+"\n");
                            Writer.write("Film title: " + commandsDatas[3]+"\n"+"\n");
                            Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");
                            break;
                        }

                    case "VIEWFILM":
                        Writer.write(commandData+"\n"+"\n");        //I wrote the command
                        if (filmIds.contains(commandsDatas[1])) {       // These loops matches movies and users ids
                            for (Film f : films) {
                                if (f.getFilmId().equals(commandsDatas[1])) {
                                    if (f instanceof FeatureFilm) {     //I separate them to their types to doing downcasting
                                        Writer.write(f.getFilmTitle() + " (" + ((FeatureFilm) f).getReleaseDate().substring(6) + ")"+"\n");
                                        Writer.write(((FeatureFilm) f).getFilmGenre()+"\n");
                                        Writer.write("Writers: ");String w ="";         //I create temporary String to concatenate writer name and surname with comma
                                        for (String j : ((FeatureFilm) f).getWriters()) {
                                            w=w.concat(allArtists.get(j) + ",");            //With this loop I used the allArtist map for reaching films writers
                                        }Writer.write(w.substring(0, w.length()-1)); //I printed like these for removing the last comma
                                        Writer.write("\n"+"Directors: ");String d ="";  //I create temporary String to concatenate directors name and surname with comma
                                        for (String j : f.getDirectors()) {
                                            d=d.concat(allArtists.get(j) + ",");            //With this loop I used the allArtist map for reaching films directors
                                        }Writer.write(d.substring(0, d.length()-1));
                                        Writer.write("\n"+"Stars: ");String s="";       //I create temporary String to concatenate stars name and surname with comma
                                        for (String j : f.getCast()) {
                                            s=s.concat(allArtists.get(j) + ",");            //With this loop I used the allArtist map for reaching films stars
                                        }Writer.write(s.substring(0, s.length()-1));        // I have done the same things for all the other films
                                        if (((FeatureFilm) f).getValueScore() == 0) {
                                            Writer.write("\n"+"Awaiting for votes"+"\n");
                                        } else {
                                            Writer.write("\n"+"Ratings: " + ((FeatureFilm) f).getRatingScore() + "/10 from " + ((FeatureFilm) f).getRaters() + " users"+"\n"+"\n");
                                            break;
                                        }
                                    } else if (f instanceof ShortFilm) {
                                        Writer.write(f.getFilmTitle() + " (" + ((ShortFilm) f).getReleaseDate().substring(6) + ")"+"\n");
                                        Writer.write(((ShortFilm) f).getFilmGenre()+"\n");
                                        Writer.write("Writers: ");String w ="";
                                        for (String j : ((ShortFilm) f).getWriters()) {
                                            w=w.concat(allArtists.get(j) + ",");
                                        }Writer.write(w.substring(0, w.length()-1));
                                        Writer.write("\n"+"Directors: ");String d ="";
                                        for (String j : f.getDirectors()) {
                                            d=d.concat(allArtists.get(j) + ",");
                                        }Writer.write(d.substring(0, d.length()-1));
                                        Writer.write("\n"+"Stars: ");String s="";
                                        for (String j : f.getCast()) {
                                            s=s.concat(allArtists.get(j) + ",");
                                        }Writer.write(s.substring(0, s.length()-1));
                                        if (((ShortFilm) f).getValueScore() == 0) {
                                            Writer.write("\n"+"Awaiting for votes"+"\n");
                                        } else {
                                            Writer.write("\n"+"Ratings: " + ((ShortFilm) f).getRatingScore() + "/10 from " + ((ShortFilm) f).getRaters() + " users"+"\n");
                                            break;
                                        }
                                    } else if (f instanceof Documentary) {
                                        Writer.write(f.getFilmTitle() + " (" + ((Documentary) f).getReleaseDate().substring(6) + ")"+"\n"+"\n");
                                        Writer.write("Directors: ");String d ="";
                                        for (String j : f.getDirectors()) {
                                            d=d.concat(allArtists.get(j) + ",");
                                        }Writer.write(d.substring(0, d.length()-1));
                                        Writer.write("\n"+"Stars: ");String s="";
                                        for (String j : f.getCast()) {
                                            s=s.concat(allArtists.get(j) + ",");
                                        }Writer.write(s.substring(0, s.length()-1));
                                        if (((Documentary) f).getValueScore() == 0) {
                                            Writer.write("\n"+"Awaiting for votes"+"\n"+"\n");

                                        } else {
                                            Writer.write("\n"+"Ratings: " + ((Documentary) f).getRatingScore() + "/10 from " + ((Documentary) f).getRaters() + " users"+"\n"+"\n");
                                            break;
                                        }
                                    } else if (f instanceof TVSeries) {
                                        Writer.write(f.getFilmTitle() + " (" + ((TVSeries) f).getStartDate().substring(6) + "-" + ((TVSeries) f).getEndDate().substring(6) + ")"+"\n");
                                        Writer.write(((TVSeries) f).getNumberOfSeason() + " seasons, " + ((TVSeries) f).getNumberOfEpisodes() + " episodes"+"\n");
                                        Writer.write(((TVSeries) f).getFilmGenre()+"\n");
                                        Writer.write("Writers: ");String w ="";
                                        for (String j : ((TVSeries) f).getWriters()) {
                                            w=w.concat(allArtists.get(j) + ",");
                                        }Writer.write(w.substring(0, w.length()-1));
                                        Writer.write("\n"+"Directors: ");String d ="";
                                        for (String j : f.getDirectors()) {
                                            d=d.concat(allArtists.get(j) + ",");
                                        }Writer.write(d.substring(0, d.length()-1));
                                        Writer.write("\n"+"Stars: ");String s="";
                                        for (String j : f.getCast()) {
                                            s=s.concat(allArtists.get(j) + ",");
                                        }Writer.write(s.substring(0, s.length()-1));
                                        if (((TVSeries) f).getValueScore() == 0) {
                                            Writer.write("\n"+"Awaiting for votes"+"\n"+"\n");
                                        } else
                                            Writer.write("\n"+"Ratings: " + ((TVSeries) f).getRatingScore() + "/10 from " + ((TVSeries) f).getRaters() + " users"+"\n"+"\n");
                                        break;
                                    }
                                }
                            }
                        } else {
                            Writer.write("Command Failed"+"\n");
                            Writer.write("Film ID: " + commandsDatas[1]+"\n"+"\n");
                        }Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");
                        break;

                    case "LIST":
                        if (commandsDatas[1].equals("FILM") && commandsDatas[2].equals("SERIES")) {
                            Writer.write(commandData+"\n"+"\n");
                            if (TvSeries.size() == 0) {                 //If no TV series added yet
                                Writer.write("No result"+"\n"+"\n");
                            } else {
                                for (TVSeries i : TvSeries) {
                                    Writer.write(i.getFilmTitle() + " (" + i.getStartDate().substring(6) + "-" + i.getEndDate().substring(6) + ")"+"\n");
                                    Writer.write(i.getNumberOfSeason() + " seasons and " + i.getNumberOfEpisodes() + " episodes"+"\n"+"\n");
                                }
                            }Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");
                        } else if (commandsDatas[1].equals("FILMS") && commandsDatas[2].equals("BY") && commandsDatas[3].equals("COUNTRY")) {
                            Writer.write(commandData+"\n"+"\n");
                            int total = 0;  //This is a temporary counter to check if there are any movies about that country
                            for (Film f : films) { //This loop matches films countries
                                if (f.getCountry().equals(commandsDatas[4])) {
                                    Writer.write("Film title: " + f.getFilmTitle()+"\n");
                                    Writer.write(f.getRuntime() + " min"+"\n");
                                    Writer.write("Language: " + f.getLanguage()+"\n"+"\n");
                                    total += 1;
                                }
                            }
                            if (total == 0) {       //I check it with using this counter
                                Writer.write("No result"+"\n"+"\n");
                            }Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");
                        } else if (commandsDatas[1].equals("FEATUREFILMS") && commandsDatas[2].equals("BEFORE")) {
                            Writer.write(commandData+"\n"+"\n");
                            int total = 0;          //This is a temporary counter to check if there are any movies before that year
                            for (FeatureFilm f : featureFilms) {    //This loop searches films release dates
                                if (Integer.parseInt(f.getReleaseDate().substring(6)) < Integer.parseInt(commandsDatas[3])) {
                                    Writer.write("Film title: " + f.getFilmTitle() + " (" + f.getReleaseDate().substring(6) + ")"+"\n");
                                    Writer.write(f.getRuntime() + " min"+"\n");
                                    Writer.write("Language: " + f.getLanguage()+"\n"+"\n");
                                    total += 1;
                                }
                            }
                            if (total == 0) {       //I check it with using this counter
                                Writer.write("No result"+"\n"+"\n");
                            }Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");
                        } else if (commandsDatas[1].equals("FEATUREFILMS") && commandsDatas[2].equals("AFTER")) {
                            Writer.write(commandData+"\n"+"\n");
                            int total = 0;      //This is a temporary counter to check if there are any movies after that year
                            for (FeatureFilm f : featureFilms) {//This loop searches films release dates
                                if (Integer.parseInt(f.getReleaseDate().substring(6)) >= Integer.parseInt(commandsDatas[3])) {
                                    Writer.write("Film title: " + f.getFilmTitle() + " (" + f.getReleaseDate().substring(6) + ")"+"\n");
                                    Writer.write(f.getRuntime() + " min"+"\n");
                                    Writer.write("Language: " + f.getLanguage()+"\n"+"\n");
                                    total += 1;
                                }
                            }
                            if (total == 0) {   //I check it with using this counter
                                Writer.write("No result"+"\n"+"\n");
                            }Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");
                        } else if (commandsDatas[1].equals("ARTISTS") && commandsDatas[2].equals("FROM")) {
                            Writer.write(commandData+"\n"+"\n");
                            Writer.write("Directors: "+"\n");
                            int total = 0;  //This is a temporary counter to check if there are any directors,writers or performers
                            for (Artist a : artists) {
                                if (a instanceof Director && a.getCountry().equals(commandsDatas[3])) {
                                    Writer.write(a.getName() + " " + a.getSurname() + " " + ((Director) a).getAgent()+"\n");
                                    total += 1;
                                }
                            }
                            if (total == 0) {
                                Writer.write("No result"+"\n");
                            }
                            total = 0;
                            Writer.write("\n"+"Writers: "+"\n");
                            for (Artist a : artists) {
                                if (a instanceof Writer && a.getCountry().equals(commandsDatas[3])) {
                                    Writer.write(a.getName() + " " + a.getSurname() + " " + ((Writer) a).getStyle()+"\n");
                                    total += 1;
                                }
                            }
                            if (total == 0) {
                                Writer.write("No result"+"\n");
                            }
                            total = 0;
                            Writer.write("\n"+"Actors: "+"\n");
                            for (Artist a : artists) {
                                if (a instanceof Actor && a.getCountry().equals(commandsDatas[3])) {
                                    Writer.write(a.getName() + " " + a.getSurname() + " " + ((Actor) a).getHeight() + " cm"+"\n");
                                    total += 1;
                                }
                            }
                            if (total == 0) {
                                Writer.write("No result"+"\n");
                            }
                            total = 0;
                            Writer.write("\n"+"ChildActors: "+"\n");
                            for (Artist a : artists) {
                                if (a instanceof ChildActor && a.getCountry().equals(commandsDatas[3])) {
                                    Writer.write(a.getName() + " " + a.getSurname() + " " + ((ChildActor) a).getAge()+"\n");
                                    total += 1;
                                }
                            }
                            if (total == 0) {
                                Writer.write("No result"+"\n");
                            }
                            total = 0;
                            Writer.write("\n"+"StuntPerformers: "+"\n");
                            for (Artist a : artists) {
                                if (a instanceof StuntPerformer && a.getCountry().equals(commandsDatas[3])) {
                                    Writer.write(a.getName() + " " + a.getSurname() + " " + ((StuntPerformer) a).getHeight() + " cm"+"\n");
                                    total += 1;
                                }
                            }
                            if (total == 0) {
                                Writer.write("No result"+"\n"+"\n");
                            }Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");
                        } else if (commandsDatas[1].equals("USER") && commandsDatas[3].equals("RATES")) {
                            Writer.write(commandData+"\n"+"\n");
                            if (usersIds.contains(commandsDatas[2])) {          // I check whether this user id exist or not
                                for (User u : users) {                          //these loops matches the users id
                                    if (u.getId().equals(commandsDatas[2])) {
                                        if (u.getRatedFilms().size() == 0) {     //I checked whether a user rate any film or not
                                            Writer.write("There is not any ratings so far"+"\n"+"\n");
                                        } else for(String s : u.getRatedFilms()){
                                            for (Film f : films) {              //These loops matches the film ids with the users rated films
                                                if(f.getFilmId().equals(s)){    //With using the userRate map I reach the users rates for his/her all films
                                                    Writer.write(f.getFilmTitle() + ": " + userRate.get(u.getId() + f.getFilmId())+"\n");}
                                            }
                                        }
                                    }
                                }
                                Writer.write("\n");
                            } else {
                                Writer.write("Command Failed"+"\n");
                                Writer.write("User ID: " + commandsDatas[2]+"\n"+"\n");
                            }Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");

                        } else if (commandsDatas[1].equals("FILMS") && commandsDatas[2].equals("BY") && commandsDatas[3].equals("RATE")) {
                            Writer.write(commandData+"\n"+"\n");
                            Writer.write("FeatureFilm:"+"\n");
                            ArrayList<FeatureFilm> newFeatureFilms = new ArrayList<FeatureFilm>();  //These are all temporary array list
                            ArrayList<TVSeries> newTvSeries = new ArrayList<TVSeries>();            //that I used just for sorting film by rating degree
                            ArrayList<Documentary> newDocumentaryS = new ArrayList<Documentary>();
                            ArrayList<ShortFilm> newShortFilms = new ArrayList<ShortFilm>();
                            for (Film f : films) {if (f instanceof FeatureFilm) {newFeatureFilms.add(((FeatureFilm)f));}    //I separate all film object respect to their types
                            else if(f instanceof ShortFilm){newShortFilms.add(((ShortFilm)f));}                             //Than I add all films to these temporary array lists
                            else if(f instanceof Documentary){newDocumentaryS.add(((Documentary)f));}
                            else if(f instanceof TVSeries){newTvSeries.add(((TVSeries)f));}}
                            newFeatureFilms.sort(Comparator.comparing(FeatureFilm::getRatingScore));Collections.reverse(newFeatureFilms);//I sorted all temporary array lists using to their String
                            newDocumentaryS.sort(Comparator.comparing(Documentary::getRatingScore));Collections.reverse(newDocumentaryS);//type of rating score and than I reverse it
                            newShortFilms.sort(Comparator.comparing(ShortFilm::getRatingScore));Collections.reverse(newShortFilms);
                            newTvSeries.sort(Comparator.comparing(TVSeries::getRatingScore));Collections.reverse(newTvSeries);
                            //After sorting using loop I reach films features
                            for (FeatureFilm f : newFeatureFilms) {
                                Writer.write(f.getFilmTitle() + " (" + f.getReleaseDate().substring(6) + ") Ratings: " + f.getRatingScore() + "/10 from " + f.getRaters() + " users"+"\n");
                            }
                            Writer.write("\n"+"ShortFilm:"+"\n");
                            for (ShortFilm f : newShortFilms) {
                                Writer.write(f.getFilmTitle() + " (" + f.getReleaseDate().substring(6) + ") Ratings: " + f.getRatingScore() + "/10 from " + f.getRaters() + " users"+"\n");
                            }
                            Writer.write("\n"+"Documentary:"+"\n");
                            for (Documentary f : newDocumentaryS) {
                                Writer.write(f.getFilmTitle() + " (" + f.getReleaseDate().substring(6) + ") Ratings: " + f.getRatingScore() + "/10 from " + f.getRaters() + " users"+"\n");
                            }
                            Writer.write("\n"+"TVSeries:"+"\n");
                            for (TVSeries f : newTvSeries) {
                                Writer.write(f.getFilmTitle() + " (" + f.getStartDate().substring(6) + "-" + f.getEndDate().substring(6) + ") Ratings: " + f.getRatingScore() + "/10 from " + f.getRaters() + " users"+"\n");
                            }Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");
                        }
                        break;
                    case "RATE":
                        Writer.write(commandData+"\n"+"\n");    //For this command firstly I checked conformity of the rate value
                        if (1 <= Integer.parseInt(commandsDatas[3]) && Integer.parseInt(commandsDatas[3]) <= 10) {
                            for (User u : users) {              //These loops matches the users ids and film ids
                                for (Film f : films) {
                                    if (u.getId().equals(commandsDatas[1]) && u.getRatedFilms().contains(commandsDatas[2])) {     //I check whether the film rated before or not
                                        Writer.write("This film was earlier rated"+"\n"+"\n");
                                        break;
                                    } else if (u.getId().equals(commandsDatas[1]) && f.getFilmId().equals(commandsDatas[2])) {
                                        Writer.write("Film rated successfully"+"\n");
                                        Writer.write("Film type: " + f.getClass().getName()+"\n");
                                        Writer.write("Film title: " + f.getFilmTitle()+"\n"+"\n");
                                        if (f instanceof FeatureFilm) {
                                            ((FeatureFilm) f).setRatingScore(Integer.parseInt(commandsDatas[3]));           //For every type of film I set their rate degree
                                            userRate.put(u.getId() + f.getFilmId(), Integer.parseInt(commandsDatas[3]));    //and also I add this rating scores the userRate map
                                            u.addRatedFilms(f.getFilmId());
                                        } else if (f instanceof ShortFilm) {
                                            ((ShortFilm) f).setRatingScore(Integer.parseInt(commandsDatas[3]));
                                            userRate.put(u.getId() + f.getFilmId(), Integer.parseInt(commandsDatas[3]));
                                            u.addRatedFilms(f.getFilmId());
                                        } else if (f instanceof Documentary) {
                                            ((Documentary) f).setRatingScore(Integer.parseInt(commandsDatas[3]));
                                            userRate.put(u.getId() + f.getFilmId(), Integer.parseInt(commandsDatas[3]));
                                            u.addRatedFilms(f.getFilmId());
                                        } else if (f instanceof TVSeries) {
                                            ((TVSeries) f).setRatingScore(Integer.parseInt(commandsDatas[3]));
                                            userRate.put(u.getId() + f.getFilmId(), Integer.parseInt(commandsDatas[3]));
                                            u.addRatedFilms(f.getFilmId());
                                        }
                                        break;
                                    }
                                }
                            }
                            if (!(usersIds.contains(commandsDatas[1])) || !(filmIds.contains(commandsDatas[2]))) { //I checked whether user or film exist or not
                                Writer.write("Command Failed"+"\n");
                                Writer.write("User ID: " + commandsDatas[1]+"\n");
                                Writer.write("Film ID: " + commandsDatas[2]+"\n"+"\n");
                                Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");
                                break;
                            }
                        } else {
                            System.out.println("Rating score must be between 1-10");
                        }Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");
                        break;
                    case "EDIT":
                        Writer.write(commandData+"\n"+"\n");
                        if (usersIds.contains(commandsDatas[2]) && filmIds.contains(commandsDatas[3])) {        //I check whether the film and user exist or not
                            for (User u : users) {              //These loops matches the users ids and film ids
                                for (Film f : films) {
                                    if (commandsDatas[2].equals(u.getId()) && !(u.getRatedFilms().contains(commandsDatas[3]))) {   //I check whether the user has rated this film or not
                                        Writer.write("Command Failed"+"\n");
                                        Writer.write("User ID: " + commandsDatas[2]+"\n");
                                        Writer.write("Film ID: " + commandsDatas[3]+"\n"+"\n");
                                        break;
                                    } else if (commandsDatas[2].equals(u.getId()) && commandsDatas[3].equals(f.getFilmId())) {
                                        Writer.write("New ratings done successfully"+"\n");
                                        Writer.write("Film title: " + f.getFilmTitle()+"\n");
                                        Writer.write("Your rating: " + commandsDatas[4]+"\n"+"\n");
                                        if (f instanceof FeatureFilm) {
                                            ((FeatureFilm) f).editRatingScore(userRate.get(u.getId() + f.getFilmId()), Integer.parseInt(commandsDatas[4]));     //I used editRatingScore method
                                            userRate.put(u.getId() + f.getFilmId(), Integer.parseInt(commandsDatas[4]));                                        //for exchanging the old and new ratings
                                        } else if (f instanceof ShortFilm) {                                                                                    //I edit the userRate map
                                            ((ShortFilm) f).editRatingScore(userRate.get(u.getId() + f.getFilmId()), Integer.parseInt(commandsDatas[4]));
                                            userRate.put(u.getId() + f.getFilmId(), Integer.parseInt(commandsDatas[4]));
                                        } else if (f instanceof Documentary) {
                                            ((Documentary) f).editRatingScore(userRate.get(u.getId() + f.getFilmId()), Integer.parseInt(commandsDatas[4]));
                                            userRate.put(u.getId() + f.getFilmId(), Integer.parseInt(commandsDatas[4]));
                                        } else if (f instanceof TVSeries) {
                                            ((TVSeries) f).editRatingScore(userRate.get(u.getId() + f.getFilmId()), Integer.parseInt(commandsDatas[4]));
                                            userRate.put(u.getId() + f.getFilmId(), Integer.parseInt(commandsDatas[4]));
                                        }
                                    }
                                }
                            }
                        } else {
                            Writer.write("Command Failed"+"\n");
                            Writer.write("User ID: " + commandsDatas[2]+"\n");
                            Writer.write("Film ID: " + commandsDatas[3]+"\n"+"\n");
                        }Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");
                        break;
                    case "REMOVE":
                        Writer.write(commandData+"\n"+"\n");
                        if (usersIds.contains(commandsDatas[2]) && filmIds.contains(commandsDatas[3])) {    //I check whether the film and user exist or not
                            for (User u : users) {                                                          //These loops matches the users ids and film ids
                                for (Film f : films) {
                                    if (commandsDatas[2].equals(u.getId()) && !(u.getRatedFilms().contains(commandsDatas[3]))) {    //I check whether the film rated by this user
                                        Writer.write("Command Failed"+"\n");                                                    // before or not
                                        Writer.write("User ID: " + commandsDatas[2]+"\n");
                                        Writer.write("Film ID: " + commandsDatas[3]+"\n"+"\n");
                                        break;
                                    } else if (commandsDatas[2].equals(u.getId()) && commandsDatas[3].equals(f.getFilmId())) {
                                        Writer.write("Your film rating was removed successfully"+"\n");
                                        Writer.write("Film title: " + f.getFilmTitle()+"\n"+"\n");
                                        u.getRatedFilms().remove(commandsDatas[3]);
                                        if (f instanceof FeatureFilm) {
                                            ((FeatureFilm) f).removeRatingScore(userRate.get(u.getId() + f.getFilmId()));   //I used removeRatingScore method
                                            userRate.remove(u.getId() + f.getFilmId());                                 //for deleting rated films and ist score
                                        } else if (f instanceof ShortFilm) {
                                            ((ShortFilm) f).removeRatingScore(userRate.get(u.getId() + f.getFilmId()));
                                            userRate.remove(u.getId() + f.getFilmId());
                                        } else if (f instanceof Documentary) {
                                            ((Documentary) f).removeRatingScore(userRate.get(u.getId() + f.getFilmId()));
                                            userRate.remove(u.getId() + f.getFilmId());
                                        } else if (f instanceof TVSeries) {
                                            ((TVSeries) f).removeRatingScore(userRate.get(u.getId() + f.getFilmId()));
                                            userRate.remove(u.getId() + f.getFilmId());
                                        }
                                        break;
                                    }
                                }
                            }
                        } else {
                            Writer.write("Command Failed"+"\n");
                            Writer.write("User ID: " + commandsDatas[2]+"\n");
                            Writer.write("Film ID: " + commandsDatas[3]+"\n"+"\n");
                        }Writer.write("-----------------------------------------------------------------------------------------------------"+"\n");
                        break;
                }
            }
            commandReader.close();Writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
