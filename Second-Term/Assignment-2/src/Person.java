public class Person {//This is the superclass of all other type of people
    private String name;
    private String surname;
    private String country;
    private String id;
    Person(String id,String name,String surname,String country){
        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.setCountry(country);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

