
import java.util.ArrayList;
import java.util.List;

public class Author {

    private String id;
    private String name="";
    private String university="";
    private String department="";
    private String email="";
    private String[] articlesIds = new String[5];
    private List<String> temporary = new ArrayList<String>();
    private List<String> arrayArticlesIds = new ArrayList<String>();

    Author(String id,String name,String university,String department,String email,String article1,String article2,
           String article3,String article4,String article5){

        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
        this.email = email;
        this.articlesIds = new String[]{article1,article2,article3,article4,article5};
        this.arrayArticlesIds.add(article1);
        this.arrayArticlesIds.add(article2);
        this.arrayArticlesIds.add(article3);
        this.arrayArticlesIds.add(article4);
        this.arrayArticlesIds.add(article5);

    }
    Author(String id,String name,String university,String department,String email,String article1,String article2,
           String article3,String article4){
        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
        this.email = email;
        this.articlesIds = new String[]{article1,article2,article3,article4};
        this.arrayArticlesIds.add(article1);
        this.arrayArticlesIds.add(article2);
        this.arrayArticlesIds.add(article3);
        this.arrayArticlesIds.add(article4);

    }
    Author(String id,String name,String university,String department,String email,String article1,String article2,
           String article3){
        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
        this.email = email;
        this.articlesIds = new String[]{article1,article2,article3};
        this.arrayArticlesIds.add(article1);
        this.arrayArticlesIds.add(article2);
        this.arrayArticlesIds.add(article3);

    }
    Author(String id,String name,String university,String department,String email,String article1,String article2){
        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
        this.email = email;
        this.articlesIds = new String[]{article1,article2};
        this.arrayArticlesIds.add(article1);
        this.arrayArticlesIds.add(article2);

    }
    Author(String id,String name,String university,String department,String email,String article1){
        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
        this.email = email;
        this.articlesIds = new String[]{article1};
        this.arrayArticlesIds.add(article1);

    }
    Author(String id,String name,String university,String department,String email){
        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
        this.email = email;
    }
    Author(String id,String name,String university,String department){
        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
    }
    Author(String id,String name,String university){
        this.id = id;
        this.name = name;
        this.university = university;
    }
    Author(String id,String name){
        this.id = id;
        this.name = name;
    }
    Author(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }
    public int getIntId(){
        return Integer.parseInt(id);
    }


    public String getAll(){
        return "Author:" + id +"    " +name+"    "+university+"    "+department+"    "+email;
    }


    public String[] getArticlesIds(){
        return articlesIds;
    }

    public void setArticles(String article){
        this.temporary.add(article);
    }
    public List<String> getArticles(){
        return temporary;
    }
    public List<String> getArrayArticlesIds(){
        return arrayArticlesIds;
    }

}
