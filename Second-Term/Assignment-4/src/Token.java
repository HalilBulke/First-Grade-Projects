public class Token {
    String id;
    String part;
    int capacity;
    static int counter=100; //Using this counter I set tokens priority
    int priority;           //Decrease its value every time token used

    Token(String id,String part,int capacity){
        this.id=id;
        this.part=part;
        this.capacity=capacity;
    }
    //Decreases tokens capacity
    public void setCapacity(int i){
        this.capacity-=i;
    }

    //Returns a string representation of this token.
    public String toString(){
        return this.id+" "+this.part+" "+this.capacity;
    }

    //Returns tokens capacity
    public int getCapacity(){
        return this.capacity;
    }

    //Sets tokens priority
    public void setPriority(int i){
        counter-=i;
        this.priority=counter;
    }
    //Returns tokens priority
    public int getPriority(){
        return  this.priority;
    }
}
