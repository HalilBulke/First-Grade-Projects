public class Human extends Calliance{
    public int humanHP=100;

    Human(String id,int v,int h) { super(id,v,h);}  //I called super class constructor

    @Override
    public void attack(Character c){                //This method takes a character and decrease its hp
        c.setHP(-Constants.humanAP);
    }
    @Override
    public void setHP(int hp){                      //This method increase or decrease the character hp
        this.humanHP+=hp;                           //This if block checks the hp is greater than the default value
        if(this.humanHP>Constants.humanHP){         //or smaller than 0
            this.humanHP=Constants.humanHP;
        }else if(this.humanHP<0){
            this.humanHP=0;
        }
    }
    @Override
    public int getHP(){
        return this.humanHP;
    }   //This method returns current hp
}
