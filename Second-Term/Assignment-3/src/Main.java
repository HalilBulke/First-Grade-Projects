import java.io.FileNotFoundException;
public class Main {
    public static String[] myArgs; //I collect arguments in this list to use them my other class while reading and writing to files
    public static void main(String[] args) throws FileNotFoundException {
        myArgs=args;
        Board.initialBoard();//I initialized game board and create characters
        Board.printBoard(0);//I print the initial board
        Commands.moves();//I start following the commands
    }
}
