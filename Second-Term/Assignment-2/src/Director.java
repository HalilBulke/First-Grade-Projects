public class Director extends Artist{
    private String agent;
    Director(String id,String name,String surname,String country,String agent){
        super(id,name,surname,country);
        this.setAgent(agent);}

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}

