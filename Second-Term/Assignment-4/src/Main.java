import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Stack> parts = new ArrayList<Stack>();                   //Stored all stacks (item parts) in this ArrayList
        Queue tokens = new Queue("tokens");                         //Tokens queue

        FileReader partFile = new FileReader(args[0]);        //File reader
        Scanner partReader = new Scanner(partFile);

        while (partReader.hasNextLine()) {                                //Create parts as a stack and add them
            String partData = partReader.nextLine();                      //into ArrayList
            parts.add(new Stack(partData));
        }

        FileReader itemFile = new FileReader(args[1]);        //File reader
        Scanner itemReader = new Scanner(itemFile);

        while (itemReader.hasNextLine()) {                                //Create Items and add them to their
            String itemData = itemReader.nextLine();                      //corresponding part of vending machine
            String[] itemDatas = itemData.split(" ");
            for(Stack s: parts){
                if(s.name.equals(itemDatas[1])){
                    s.push(new Item(itemDatas[0],itemDatas[1]));
                }
            }
        }
        FileReader tokenFile = new FileReader(args[2]);       //Token film file reader
        Scanner tokenReader = new Scanner(tokenFile);

        while (tokenReader.hasNextLine()) {                                //Create Tokens and add them Tokens queue
            String[] tokenData= tokenReader.nextLine().split(" ");
            tokens.enqueue(new Token(tokenData[0],tokenData[1],Integer.parseInt(tokenData[2])));
        }

        FileReader taskFile = new FileReader(args[3]);          //Task file reader
        Scanner taskReader = new Scanner(taskFile);

        for(Token t:tokens.tokens){t.setPriority(1);}                       //Set initial priority of all tokens

        while (taskReader.hasNextLine()) {                                  //Start following commands
            String task = taskReader.nextLine();
            String[] taskData = task.split("\t");                     //Split command line to reach information easily
            if(taskData[0].equals("BUY")){
                for(int j=1;j<taskData.length;j++){                         //Mach tokens and parts
                    tokens.sort(0);                                      //Setup my queue every time it has change
                    String[] info = taskData[j].split(",");
                    int total = Integer.parseInt(info[1]);                  //Amount to buy
                    int remaining = Integer.parseInt(info[1]);              //Remaining from the amount to be purchased
                    while(remaining>0) {                                    //Keep buying until the remaining value becomes zero
                        for (Token t : tokens.tokens) {
                            if (t.part.equals(info[0])) {
                                if (t.capacity < remaining) {               //If tokens capacity is less than requested value
                                    remaining-=t.capacity;                  //Decrease remaining value by capacity of token
                                    t.setCapacity(total);                   //And remove this token
                                    tokens.dequeue(t);
                                    break;
                                } else if (t.capacity == remaining) {       //If tokens capacity is equal the requested value
                                    remaining-=t.capacity;                  //Remove this token
                                    t.setCapacity(total);
                                    tokens.dequeue(t);
                                    break;
                                } else {                                    //If tokens capacity is greater than the requested value
                                    t.setCapacity(remaining);
                                    remaining-=remaining;                   //Decrease tokens capacity
                                    t.setPriority(1);                       //and set its priority
                                    break;
                                }
                            }
                        }
                    }

                    for (Stack s : parts){                                  //Match parts
                        if(s.name.equals(info[0])){
                            while (total > 0){                              //Take Items from vending machine
                                s.pop();                                    //in the desired amount
                                total-=1;
                            }
                        }
                    }
                }
            }else if (taskData[0].equals("PUT")){
                for(int j=1;j<taskData.length;j++) {
                    String[] info = taskData[j].split(",");
                    for(Stack s : parts) {
                        if(s.name.equals(info[0])) {
                            for (int k = 1; k < info.length; k++){          //Create Items
                                s.push(new Item(info[k],info[0]));          //and add them to their parts
                            }
                        }
                    }
                }
            }
        }

        tokens.sort(1);                                                   //Sort tokens queue last time
        FileWriter Writer = new FileWriter(args[4]);           //Output writer
        for(Stack s : parts){
            Writer.write(s.name+":\n");
            for(Item i:s.getItems()){
                Writer.write(i.id+"\n");
            }
            Writer.write("---------------\n");
        }
        Writer.write("Token Box:\n");
        for(Token j:tokens.tokens){
            Writer.write(j+"\n");
        }
        Writer.close();
    }
}
