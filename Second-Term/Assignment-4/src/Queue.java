import java.util.*;

public class Queue{
    String token;
    ArrayList<Token> tokens = new ArrayList<Token>();
    public Queue(String tokens) {
        this.token=tokens;
    }

    //Returns true if this queue is empty.
    public boolean isEmpty() {
        return this.tokens == null;
    }

    // Returns the number of items in this queue.
    public int size() {
        return this.tokens.size();
    }

    // Returns the item least recently added to this queue.
    public Token peek() {
        return this.tokens.get(tokens.size()-1);
    }

    // Adds the item to this queue.
    public void enqueue(Token token) {
        this.tokens.add(token);
    }

    // Removes the item on this queue
    public void dequeue(Token t) {
        this.tokens.remove(t);
    }

    // Returns a string representation of this queue.
    public String toString() {
        String t="";
        for (Token item : this.tokens) {
            t+= item;
        }
        return t;
    }
    //Sorts every token in this queue according to its capacity and priority.
    public void sort(int i){
        if(i==0){                   //if int i is 0 it sorts and reverse the queue
        tokens.sort(Comparator.comparing(Token::getCapacity).thenComparing(Token::getPriority));
        Collections.reverse(tokens);}
        else if(i ==1){             //if int i is 1 it just sorts
            tokens.sort(Comparator.comparing(Token::getCapacity).thenComparing(Token::getPriority));
        }
    }

}

