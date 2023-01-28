import java.util.*;

public class Stack {
    String name;
    private ArrayList<Item> items = new ArrayList<Item>();

    public Stack(String name) {
        this.name=name;
    }

    //Returns true if this stack is empty; false otherwise
    public boolean isEmpty() {
        return this.items == null;
    }

    // Returns the number of items in this stack.
    public int size() {
        return this.items.size();
    }

    //Adds the item to this stack.
    public void push(Item item) {
        this.items.add(item);
    }

    // Removes the item most recently added to this stack.
    public void pop() {
        this.items.remove(this.items.size()-1);
    }

    // Returns (but does not remove) the item most recently added to this stack.
    public Item peek() {
        return this.items.get(this.items.size()-1);
    }

    // Returns a string representation of this stack.
    public String toString() {
        String t="";
        for (Item item : this.items) {
            t+= item;
        }
        return t;
    }
    // Returns Items as a stack list.
    public ArrayList<Item> getItems(){
        Collections.reverse(this.items);
        return this.items;
    }

}
