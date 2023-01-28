public class Dwarf extends Calliance{
    public int dwarfHP=120;

    Dwarf(String id,int v,int h) { super(id,v,h); } //I called super class constructor

    @Override
    public void attack(Character c){                //This method takes a character and decrease its hp
        c.setHP(-Constants.dwarfAP);
    }
    @Override
    public void setHP(int hp){                      //This method increase or decrease the character hp
        this.dwarfHP+=hp;                           //This if block checks the hp is greater than the default value
        if(this.dwarfHP>Constants.dwarfHP){         //or smaller than 0
            this.dwarfHP=Constants.dwarfHP;
        }else if(this.dwarfHP<0){
            this.dwarfHP=0;
        }
    }
    @Override
    public int getHP(){
        return this.dwarfHP;
    }       //This method returns current hp
}
