public class Goblin extends Zorde{
    public int goblinHP=80;
    Goblin(String id,int v,int h) {
        super(id,v,h);
    }   //I called super class constructor

    @Override
    public void attack(Character c){                //This method takes a character and decrease its hp
        c.setHP(-Constants.goblinAP); }
    @Override
    public void setHP(int hp){                      //This method increase or decrease the character hp
        this.goblinHP+=hp;
        if(this.goblinHP>Constants.goblinHP){       //This if block checks the hp is greater than the default value
            this.goblinHP=Constants.goblinHP;       //or smaller than 0
        }else if(this.goblinHP<0){
            this.goblinHP=0;
        }
    }
    @Override
    public int getHP(){                             //This method returns current hp
        return this.goblinHP;
    }
}
