public class Calliance extends Character {

    Calliance(String id,int v,int h){
        super(id,v,h);
    } //I called super class constructor
    @Override
    public void attack(Character c){}   //I override this methods to use subclasses

    @Override
    public void setHP(int hp){}        //I override this methods to use subclasses

}
