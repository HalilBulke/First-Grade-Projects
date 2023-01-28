public class Character {
    public String id;     //All character have id
    public int[] location= new int[2];  //I stored characters location on the game board in this list
    public int hp;        //All character have hp

    Character(String id,int v,int h){   //This character class constructor takes 3 parameter:id,vertical location,horizontal location
        this.id=id;
        setLocation( v, h);
    }
    public void attack(Character c){}   //I override this method in every character class (benefits of polymorphism)

    public void setLocation(int v,int h){this.location[0]=v;this.location[1]=h;}    //This method takes vertical and horizontal component of characters location

    public void setHP(int hp){}         //I override this method in every character class (benefits of polymorphism)
    public int getHP(){
        return hp;
    }   //I used this method when I need to reach hp values

    public String getLocation(){
        return ""+location[0]+location[1];}

    public static String getId(Character c){ return c.id; }   // I used this method while sorting characters array list
}                                                             // according to characters ids
