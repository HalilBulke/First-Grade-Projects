public class Troll extends Zorde{
    public int trollHP=150;

    Troll(String id,int v,int h) {
        super(id,v,h);
    }//I called super class constructor

    @Override
    public void attack(Character c){            //This method takes a character and decrease its hp
        c.setHP(-Constants.trollAP);
    }
    @Override
    public void setHP(int hp){                  //This method increase or decrease the character hp
        this.trollHP+=hp;                       //This if block checks the hp is greater than the default value
        if(this.trollHP>Constants.trollHP){     //or smaller than 0
            this.trollHP=Constants.trollHP;
        }else if(this.trollHP<0){
            this.trollHP=0;
        }
    }

    @Override
    public int getHP(){
        return this.trollHP;
    } //This method returns current hp
}
