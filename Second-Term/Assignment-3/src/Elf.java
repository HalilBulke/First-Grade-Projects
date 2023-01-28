public class Elf extends Calliance{
    public int elfHP=70;

    Elf(String id,int v,int h) {
        super(id,v,h);
    }   //I called super class constructor

    @Override
    public void attack(Character c){                //This method takes a character and decrease its hp
        c.setHP(-Constants.elfAP);
    }
    public void rangedAttack(Character c){          //This method just used for elfs ranged attack it also takes character
        c.setHP(-Constants.elfRangedAP);
    }
    @Override
    public void setHP(int hp){                      //This method increase or decrease the character hp
        this.elfHP+=hp;                             //This if block checks the hp is greater than the default value
        if(this.elfHP>Constants.elfHP){             //or smaller than 0
            this.elfHP=Constants.elfHP;
        }else if(this.elfHP<0){
            this.elfHP=0;
        }
    }
    @Override
    public int getHP(){
        return this.elfHP;
    }       //This method returns current hp
}
