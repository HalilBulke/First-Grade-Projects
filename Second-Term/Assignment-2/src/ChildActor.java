public class ChildActor extends Performer{
    private String age;
    ChildActor(String id,String name,String surname,String country,String age){
        super(id,name,surname,country);
        this.setAge(age);}

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

