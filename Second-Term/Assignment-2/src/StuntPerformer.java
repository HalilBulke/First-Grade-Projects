public class StuntPerformer extends Performer{
    private String height;
    private String realId;
    StuntPerformer(String id,String name,String surname,String country,String height,String realId){
        super(id,name,surname,country);
        this.setHeight(height);
        this.setRealId(realId);
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getRealId() {
        return realId;
    }

    public void setRealId(String realId) {
        this.realId = realId;
    }
}

