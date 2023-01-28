import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Board {

    static ArrayList<String> calliances = new ArrayList<String>();//I stored calliances characters ids in this list to use when I need to check a character is an calliance or not
    static ArrayList<String> zordes = new ArrayList<String>();//I stored zordes characters ids in this list to use when I need to check a character is an zorde or not
    public static ArrayList<Character> characters = new ArrayList<Character>();//I stored all my character objects in this list
    public static String[][] board = new String[0][];     //this is my game board

    public static void initialBoard() throws FileNotFoundException {
        try {
            FileWriter Writer = new FileWriter(Main.myArgs[2]);//This is my file writer
            Writer.write("\n");
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        FileReader initialFile = new FileReader(Main.myArgs[0]);//This is my initial board file reader
        Scanner initialReader = new Scanner(initialFile);
        while (initialReader.hasNextLine()) {
            String[] boardDatas = initialReader.nextLine().split(" ");//I split the line to use its elements while reaching the board and character information
            switch (boardDatas[0]) {
                case "BOARD":
                    int s = Integer.parseInt(initialReader.nextLine().split("x")[0])+2;//I reach the board size
                    board = new String[s][s];         //I finalized my board here
                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board.length; j++) {
                            if(j == 0 || j+1 == board.length){
                                board[i][j] = "*";              //I add "**" and "*" as borders of my game board
                            }else if( i == 0 || i+1 ==board.length){
                                board[i][j] = "**";
                            }else{
                                board[i][j] = "  ";}
                        }
                    }
                break;
                    //I create all character in here and I add them my character array list and my game board
                case "ELF":
                    characters.add(new Elf(boardDatas[1],Integer.parseInt(boardDatas[3])+1,Integer.parseInt(boardDatas[2])+1));//I create and add them to character array list
                    board[Integer.parseInt(boardDatas[3])+1][Integer.parseInt(boardDatas[2])+1] = boardDatas[1];//I located them to my board
                    calliances.add(boardDatas[1]);//Also respect to their side I add their id to calliences or zordes list
                break;
                case "DWARF":
                    characters.add(new Dwarf(boardDatas[1],Integer.parseInt(boardDatas[3])+1,Integer.parseInt(boardDatas[2])+1));
                    board[Integer.parseInt(boardDatas[3])+1][Integer.parseInt(boardDatas[2])+1] = boardDatas[1];
                    calliances.add(boardDatas[1]);
                break;
                case "HUMAN":
                    characters.add(new Human(boardDatas[1],Integer.parseInt(boardDatas[3])+1,Integer.parseInt(boardDatas[2])+1));
                    board[Integer.parseInt(boardDatas[3])+1][Integer.parseInt(boardDatas[2])+1] = boardDatas[1];
                    calliances.add(boardDatas[1]);
                break;
                case "GOBLIN":
                    characters.add(new Goblin(boardDatas[1],Integer.parseInt(boardDatas[3])+1,Integer.parseInt(boardDatas[2])+1));
                    board[Integer.parseInt(boardDatas[3])+1][Integer.parseInt(boardDatas[2])+1] = boardDatas[1];
                    zordes.add(boardDatas[1]);
                break;
                case "TROLL":
                    characters.add(new Troll(boardDatas[1],Integer.parseInt(boardDatas[3])+1,Integer.parseInt(boardDatas[2])+1));
                    board[Integer.parseInt(boardDatas[3])+1][Integer.parseInt(boardDatas[2])+1] = boardDatas[1];
                    zordes.add(boardDatas[1]);
                break;
                case "ORK":
                    characters.add(new Ork(boardDatas[1],Integer.parseInt(boardDatas[3])+1,Integer.parseInt(boardDatas[2])+1));
                    board[Integer.parseInt(boardDatas[3])+1][Integer.parseInt(boardDatas[2])+1] = boardDatas[1];
                    zordes.add(boardDatas[1]);
                break;
                    }
                }
        characters.sort(Comparator.comparing(Character::getId));        //I sort my character list respect to their ids
        }
    public static void printBoard(int s){                                    //these method prints the current situation of board
        try {
            FileWriter Writer = new FileWriter(Main.myArgs[2],true);// this is my file writer

            if(s ==0) { //If s is 0 it means there is no error
                for (int k = 0; k < characters.size(); k++) {            //In this loop I check the game and delete the dead characters from the game
                    gameCheck();
                }
                Writer.append("\n");
                for (String[] i : board) {                              //these loop prints the current board
                    Writer.append(Arrays.toString(i).replace(",", "").replace("[", "").replace("]", "") + "\n");
                }
                Writer.append("\n");
                for (Character c : characters) {                        // these loop prints the current hp of characters
                    if (c instanceof Human) {
                        Writer.append(c.id + "\t" + ((Human) c).humanHP + "\t(" + Constants.humanHP + ")" + "\n");
                    } else if (c instanceof Elf) {
                        Writer.append(c.id + "\t" + ((Elf) c).elfHP + "\t(" + Constants.elfHP + ")" + "\n");
                    } else if (c instanceof Dwarf) {
                        Writer.append(c.id + "\t" + ((Dwarf) c).dwarfHP + "\t(" + Constants.dwarfHP + ")" + "\n");
                    } else if (c instanceof Goblin) {
                        Writer.append(c.id + "\t" + ((Goblin) c).goblinHP + "\t(" + Constants.goblinHP + ")" + "\n");
                    } else if (c instanceof Troll) {
                        Writer.append(c.id + "\t" + ((Troll) c).trollHP + "\t(" + Constants.trollHP + ")" + "\n");
                    } else if (c instanceof Ork) {
                        Writer.append(c.id + "\t" + ((Ork) c).orkHP + "\t(" + Constants.orkHP + ")" + "\n");
                    }
                }
            }else if(s ==1){//If s is 1 it means there is move sequence error
                Writer.append("\nError : Move sequence contains wrong number of move steps. Input line ignored.\n");}
            else if(s == 2){//If s is 2 it means there is game board boundaries error
                Writer.append("\nError : Game board boundaries are exceeded. Input line ignored.\n");}
            Writer.close();winCheck();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void gameCheck(){     //these method checks dead characters
        for(int i = 0; i<characters.size(); i++){
            if(characters.get(i).getHP()<=0){
                if(board[characters.get(i).location[0]][characters.get(i).location[1]].equals(characters.get(i).id)){
                    board[characters.get(i).location[0]][characters.get(i).location[1]]="  ";
                }
                characters.remove(characters.get(i));
            }
        }
    }
    public static void winCheck() {     //These method checks who win
        try {
            FileWriter Writer = new FileWriter(Main.myArgs[2], true);
            int zordeNubmer = 0;
            int callianceNumber = 0;
            for (Character c : Board.characters) {  //with these loops I count number of zordes and calliences
                if (c instanceof Zorde) {
                    zordeNubmer += 1;
                } else if (c instanceof Calliance) {
                    callianceNumber += 1;
                }
            }
            if (zordeNubmer == 0) {             //respect to their number I reached who won
                Writer.append("\nGame Finished\n" + "Calliance Wins");
            } else if (callianceNumber == 0) {
                Writer.append("\nGame Finished\n" + "Zorde Wins");
            }Writer.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


