public class Ork extends Zorde{
    public int orkHP=200;

    Ork(String id,int v,int h) { super(id,v,h); }//I called super class constructor

    @Override
    public void setHP(int hp){                  //This method increase or decrease the character hp
        this.orkHP+=hp;                         //This if block checks the hp is greater than the default value
        if(this.orkHP>Constants.orkHP){         //or smaller than 0
            this.orkHP=Constants.orkHP;
        }else if(this.orkHP<0){
            this.orkHP=0;
        }
    }
    @Override
    public void attack(Character c){            //This method takes a character and decrease its hp
        c.setHP(-Constants.orkAP);
    }
    @Override
    public int getHP(){
        return this.orkHP;
    }   //This method returns current hp
}
