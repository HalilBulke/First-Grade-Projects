

public class Article implements Comparable<Article> {

    private String paperId;
    private String Articlename;
    private String publisherName;
    private String publishYear;

    Article(String paperId,String name,String publisherName,String publishYear){
        this.paperId = paperId;
        this.Articlename = name;
        this.publisherName = publisherName;
        this.publishYear = publishYear;


    }
    public String getPaperId(){
        return paperId;
    }
    public int getIntpaperId(){
        return Integer.parseInt(paperId);
    }

    public String getAll(){ return "+"+paperId+"    "+Articlename+"    "+publisherName+"    "+publishYear; }

    @Override
    public int compareTo(Article paperId) {
        int comparePaperid=((Article)paperId).getIntpaperId();
        return getIntpaperId()-comparePaperid;
    }

}
