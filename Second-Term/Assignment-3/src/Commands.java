import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Commands {
    public static String[] range= new String[8];//I stored normal attack range in this list
    public static String[] elfRange= new String[24];//this is just for the elf special attack range

    public static void moves()throws FileNotFoundException {
        FileReader commandFile = new FileReader(Main.myArgs[1]);   //This is my command reader
        Scanner commandReader = new Scanner(commandFile);
        while (commandReader.hasNextLine()) {                              //With this while loop I read all lines of file
            String[] commandDatas = commandReader.nextLine().split(" ");//I split every line to reach id and commands
            String[] moveDatas = commandDatas[1].split(";");          //I split commands to reach directions
            int[] moves = new int[moveDatas.length];                         //This list stored directions as an integer values
            for (int i = 0; i < moveDatas.length; i++) {                         //These loop turns directions strings to integer
                moves[i] = Integer.parseInt(moveDatas[i]);
            }
            for (int j = 0; j < Board.characters.size(); j++) {                 //With this loop I find the current character who will move
                if (Board.characters.get(j).id.equals(commandDatas[0])) {
                    if (Board.characters.get(j) instanceof Ork) {             //In this if block ı separate them respect to their clan
                        try{
                        if (!(moveDatas.length == Constants.orkMaxMove * 2)) {  //In this inner if blocks I checked errors and characters next move step
                            throw new MoveSequence();
                        } else if (Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("*")) {
                            throw new BoundaryException();
                        } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]])) {
                            setRange(Board.characters.get(j));                  //this method finds the range of the character
                            Board.characters.get(j).setHP(Constants.orkHealPoints); //I healed ork
                            for (Character c : Board.characters) {              //In this loop I compare characters location and orks range
                                for (String l : range) {
                                    if (l.equals(c.getLocation()) && c instanceof Zorde) {
                                        c.setHP(Constants.orkHealPoints);       //if they match ork will heal them if they are allies of ork
                                    }
                                }
                            }
                            break;
                        } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]])) {
                            fightToDeath(Board.characters.get(j), moves, 1, 0);        //If cell is occupied an enemy it calls figthToDeath method
                            Board.printBoard(0);
                        } else if (Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("  ")) {
                            setRange(Board.characters.get(j));                            //If cell is empty it just move and do normal attack
                            Board.characters.get(j).setHP(Constants.orkHealPoints);
                            for (Character c : Board.characters) {
                                for (String l : range) {
                                    if (l.equals(c.getLocation()) && c instanceof Zorde) {
                                        c.setHP(Constants.orkHealPoints);
                                    }
                                }
                            }
                            locate(Board.characters.get(j), moves, 1, 0);              //It calls locate function to upgrade its current location
                            Board.printBoard(0);
                        }
                        }catch(BoundaryException e){
                            Board.printBoard(2);
                        }catch(MoveSequence k){
                            Board.printBoard(1);
                        }
                    } else if (Board.characters.get(j) instanceof Elf) {
                        try{
                        if (!(moveDatas.length == Constants.elfMaxMove * 2)) {                //In this inner if blocks I checked errors and characters next move step
                            throw new MoveSequence();
                        } else if (Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("*")) {
                            throw new BoundaryException();
                        } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]])) {
                            break;
                        } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]])) {
                            fightToDeath(Board.characters.get(j), moves, 1, 0);        //If cell is occupied an enemy it calls figthToDeath method
                            Board.printBoard(0);
                            break;
                        } else if (Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("  ")) {
                            locate(Board.characters.get(j), moves, 1, 0);              //If cell is empty it just move and do normal attack and continue with the second step
                            if (Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]].equals("*")) {
                                Board.printBoard(0);
                                throw new BoundaryException();
                            } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]])) {
                                Board.printBoard(0);
                                break;
                            } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]])) {
                                fightToDeath(Board.characters.get(j), moves, 3, 2);
                                Board.printBoard(0);
                                break;                                                      //After I checked the errors it moves forward until the last step
                            } else if (Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]].equals("  ")) {
                                locate(Board.characters.get(j), moves, 3, 2);
                                if (Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]].equals("*")) {
                                    Board.printBoard(0);
                                    throw new BoundaryException();
                                } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]])) {
                                    Board.printBoard(0);
                                    break;
                                } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]])) {
                                    fightToDeath(Board.characters.get(j), moves, 5, 4);
                                    Board.printBoard(0);
                                    break;
                                } else if (Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]].equals("  ")) {
                                    locate(Board.characters.get(j), moves, 5, 4);
                                    if (Board.board[Board.characters.get(j).location[0] + moves[7]][Board.characters.get(j).location[1] + moves[6]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[7]][Board.characters.get(j).location[1] + moves[6]].equals("*")) {
                                        Board.printBoard(0);
                                        throw new BoundaryException();
                                    } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[7]][Board.characters.get(j).location[1] + moves[6]])) {
                                        Board.printBoard(0);
                                        break;
                                    } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[7]][Board.characters.get(j).location[1] + moves[6]])) {
                                        fightToDeath(Board.characters.get(j), moves, 7, 6);
                                        Board.printBoard(0);
                                        break;
                                    } else if (Board.board[Board.characters.get(j).location[0] + moves[7]][Board.characters.get(j).location[1] + moves[6]].equals("  ")) {
                                        elfLocate(Board.characters.get(j), moves, 7, 6);        //In the last step just for elfs it attacks all enemies inside
                                        Board.printBoard(0);
                                    }                                          //the elf final attack range
                                }
                            }                                   //For the all other characters I did same things to follow commands
                        }
                        }catch(BoundaryException e){
                            Board.printBoard(2);
                        }catch(MoveSequence k){
                            Board.printBoard(1);
                        }
                    }
                    else if (Board.characters.get(j) instanceof Goblin) {
                    try{
                        if (!(moveDatas.length == Constants.goblinMaxMove * 2)) {
                            throw new MoveSequence();
                        } else if (Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("*")) {
                            throw new BoundaryException();
                        } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]])) {
                            break;
                        } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]])) {
                            fightToDeath(Board.characters.get(j), moves, 1, 0);
                            Board.printBoard(0);
                            break;
                        } else if (Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("  ")) {
                            locate(Board.characters.get(j), moves, 1, 0);
                            if (Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]].equals("*")) {
                                Board.printBoard(0);
                                throw new BoundaryException();
                            } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]])) {
                                Board.printBoard(0);
                                break;
                            } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]])) {
                                fightToDeath(Board.characters.get(j), moves, 3, 2);
                                Board.printBoard(0);
                                break;
                            } else if (Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]].equals("  ")) {
                                locate(Board.characters.get(j), moves, 3, 2);
                                if (Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]].equals("*")) {
                                    Board.printBoard(0);
                                    throw new BoundaryException();
                                } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]])) {
                                    Board.printBoard(0);
                                    break;
                                } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]])) {
                                    fightToDeath(Board.characters.get(j), moves, 5, 4);
                                    Board.printBoard(0);
                                    break;
                                } else if (Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]].equals("  ")) {
                                    locate(Board.characters.get(j), moves, 5, 4);
                                    if (Board.board[Board.characters.get(j).location[0] + moves[7]][Board.characters.get(j).location[1] + moves[6]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[7]][Board.characters.get(j).location[1] + moves[6]].equals("*")) {
                                        Board.printBoard(0);
                                        throw new BoundaryException();
                                    } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[7]][Board.characters.get(j).location[1] + moves[6]])) {
                                        Board.printBoard(0);
                                        break;
                                    } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[7]][Board.characters.get(j).location[1] + moves[6]])) {
                                        fightToDeath(Board.characters.get(j), moves, 7, 6);
                                        Board.printBoard(0);
                                        break;
                                    } else if (Board.board[Board.characters.get(j).location[0] + moves[7]][Board.characters.get(j).location[1] + moves[6]].equals("  ")) {
                                        locate(Board.characters.get(j), moves, 7, 6);
                                        Board.printBoard(0);
                                    }
                                }
                            }
                        }
                    }catch(BoundaryException e){
                        Board.printBoard(2);
                    }catch(MoveSequence k){
                        Board.printBoard(1);
                    }
                    } else if (Board.characters.get(j) instanceof Troll) {
                    try{
                        if (!(moveDatas.length == Constants.trollMaxMove * 2)) {
                            throw new MoveSequence();
                        } else if (Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("*")) {
                            throw new BoundaryException();
                        } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]])) {
                            break;
                        } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]])) {
                            fightToDeath(Board.characters.get(j), moves, 1, 0);
                            Board.printBoard(0);
                        } else if (Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("  ")) {
                            locate(Board.characters.get(j), moves, 1, 0);
                            Board.printBoard(0);
                        }
                    }catch(BoundaryException e){
                        Board.printBoard(2);
                    }catch(MoveSequence k){
                        Board.printBoard(1);
                    }
                    } else if (Board.characters.get(j) instanceof Dwarf) {
                    try{
                        if (!(moveDatas.length == Constants.dwarfMaxMove * 2)) {
                            throw new MoveSequence();
                        } else if (Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("*")) {
                            throw new BoundaryException();
                        } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]])) {
                            break;
                        } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]])) {
                            fightToDeath(Board.characters.get(j), moves, 1, 0);
                            Board.printBoard(0);
                            break;
                        } else if (Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("  ")) {
                            locate(Board.characters.get(j), moves, 1, 0);
                            if (Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]].equals("*")) {
                                Board.printBoard(0);
                                throw new BoundaryException();
                            } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]])) {
                                Board.printBoard(0);
                                break;
                            } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]])) {
                                fightToDeath(Board.characters.get(j), moves, 3, 2);
                                Board.printBoard(0);
                                break;
                            } else if (Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]].equals("  ")) {
                                locate(Board.characters.get(j), moves, 3, 2);
                                Board.printBoard(0);
                            }
                        }
                    }catch(BoundaryException e){
                        Board.printBoard(2);
                    }catch(MoveSequence k){
                        Board.printBoard(1);
                    }
                    } else if (Board.characters.get(j) instanceof Human) {           //just for human it only attacks at the final step
                        try {
                            if (!(moveDatas.length == Constants.humanMaxMove * 2)) {
                                throw new MoveSequence();
                            } else if (Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("*")) {
                                throw new BoundaryException();
                            } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]])) {
                                break;
                            } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]])) {
                                fightToDeath(Board.characters.get(j), moves, 1, 0);
                                Board.printBoard(0);
                                break;
                            } else if (Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]].equals("  ")) {
                                Board.board[Board.characters.get(j).location[0] + moves[1]][Board.characters.get(j).location[1] + moves[0]] = Board.characters.get(j).id;
                                Board.board[Board.characters.get(j).location[0]][Board.characters.get(j).location[1]] = "  ";
                                Board.characters.get(j).setLocation(Board.characters.get(j).location[0] + moves[1], Board.characters.get(j).location[1] + moves[0]);
                                if (Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]].equals("*")) {
                                    Board.printBoard(0);
                                    throw new BoundaryException();
                                } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]])) {
                                    Board.printBoard(0);
                                    break;
                                } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]])) {
                                    fightToDeath(Board.characters.get(j), moves, 3, 2);
                                    Board.printBoard(0);
                                    break;
                                } else if (Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]].equals("  ")) {
                                    Board.board[Board.characters.get(j).location[0] + moves[3]][Board.characters.get(j).location[1] + moves[2]] = Board.characters.get(j).id;
                                    Board.board[Board.characters.get(j).location[0]][Board.characters.get(j).location[1]] = "  ";
                                    Board.characters.get(j).setLocation(Board.characters.get(j).location[0] + moves[3], Board.characters.get(j).location[1] + moves[2]);
                                    if (Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]].equals("**") || Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]].equals("*")) {
                                        Board.printBoard(0);
                                        throw new BoundaryException();
                                    } else if (Board.calliances.contains(Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]])) {
                                        Board.printBoard(0);
                                        break;
                                    } else if (Board.zordes.contains(Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]])) {
                                        fightToDeath(Board.characters.get(j), moves, 5, 4);
                                        Board.printBoard(0);
                                        break;
                                    } else if (Board.board[Board.characters.get(j).location[0] + moves[5]][Board.characters.get(j).location[1] + moves[4]].equals("  ")) {
                                        locate(Board.characters.get(j), moves, 5, 4);
                                        Board.printBoard(0);
                                    }
                                }
                            }
                        } catch (BoundaryException e) {
                            Board.printBoard(2);
                        } catch (MoveSequence k) {
                            Board.printBoard(1);
                        }
                    }
                }
            }
        }
    }
    public static void setRange(Character i){                           //This method finds characters current range
        range[0] = "" + (i.location[0] - 1) + (i.location[1] -1) ;
        range[1] = "" + (i.location[0] -1) + (i.location[1] ) ;
        range[2] = "" + (i.location[0] - 1) + (i.location[1] +1) ;
        range[3] = "" + (i.location[0] ) + (i.location[1] +1) ;
        range[4] = "" + (i.location[0] + 1) + (i.location[1] +1) ;
        range[5] = "" + (i.location[0] + 1) + (i.location[1] ) ;
        range[6] = "" + (i.location[0] + 1) + (i.location[1] -1) ;
        range[7] = "" + (i.location[0] ) + (i.location[1] -1) ;
    }
    public static void setElfRange(Character i){                        //This method finds elfs special attack range
        elfRange[0] = "" + (i.location[0] - 1) + (i.location[1] -1) ;
        elfRange[1] = "" + (i.location[0] -1) + (i.location[1] ) ;
        elfRange[2] = "" + (i.location[0] - 1) + (i.location[1] +1) ;
        elfRange[3] = "" + (i.location[0] ) + (i.location[1] +1) ;
        elfRange[4] = "" + (i.location[0] + 1) + (i.location[1] +1) ;
        elfRange[5] = "" + (i.location[0] + 1) + (i.location[1] ) ;
        elfRange[6] = "" + (i.location[0] + 1) + (i.location[1] -1) ;
        elfRange[7] = "" + (i.location[0] ) + (i.location[1] -1) ;
        elfRange[8] = "" + (i.location[0] - 2) + (i.location[1] -2) ;
        elfRange[9] = "" + (i.location[0] -1) + (i.location[1] -2) ;
        elfRange[10] = "" + (i.location[0] - 2) + (i.location[1] ) ;
        elfRange[11] = "" + (i.location[0] -2) + (i.location[1] +1) ;
        elfRange[12] = "" + (i.location[0] -2) + (i.location[1] +2) ;
        elfRange[13] = "" + (i.location[0] - 1) + (i.location[1] +2) ;
        elfRange[14] = "" + (i.location[0] ) + (i.location[1] +2) ;
        elfRange[15] = "" + (i.location[0] +1 ) + (i.location[1] +2) ;
        elfRange[16] = "" + (i.location[0] +2) + (i.location[1] +2) ;
        elfRange[17] = "" + (i.location[0] +2) + (i.location[1] +1) ;
        elfRange[18] = "" + (i.location[0] + 2) + (i.location[1] ) ;
        elfRange[19] = "" + (i.location[0] + 2) + (i.location[1] -1) ;
        elfRange[20] = "" + (i.location[0] + 2) + (i.location[1] -2) ;
        elfRange[21] = "" + (i.location[0] +1 ) + (i.location[1] -2) ;
        elfRange[22] = "" + (i.location[0] ) + (i.location[1] -2) ;
        elfRange[23] = "" + (i.location[0] -1) + (i.location[1] -2) ;
    }


    public static void locate(Character i,int[] moves,int v,int h){         //This method changes the characters location and attacks enemy characters
        Board.board[i.location[0]+moves[v]][i.location[1]+moves[h]]=i.id;   //First change location on game board
        Board.board[i.location[0]][i.location[1]]="  ";
        i.setLocation(i.location[0]+moves[v],i.location[1]+moves[h]);
        setRange(i);                                                        //find attack range
        for (Character c : Board.characters) {                              //According to range attack enemy
            for(String l:range){
                if(i instanceof Zorde && l.equals(c.getLocation()) && c instanceof Calliance){
                    i.attack(c);                                            //I used the attack method which I created in the characters classes
                }else if(i instanceof Calliance && l.equals(c.getLocation()) && c instanceof Zorde){
                    i.attack(c);
                }
            }
        }
    }
    public static void elfLocate(Character i,int[] moves,int v,int h){//This method is special for elfs last move
        Board.board[i.location[0]+moves[v]][i.location[1]+moves[h]]=i.id;
        Board.board[i.location[0]][i.location[1]]="  ";
        i.setLocation(i.location[0]+moves[v],i.location[1]+moves[h]);
        setElfRange(i);    //This is the main difference of elfLocate method from the locate method
        for (Character c : Board.characters) {
            for(String l:elfRange){
                if(l.equals(c.getLocation()) && c instanceof Zorde){
                    ((Elf)i).rangedAttack(c);//This method also special for elfs
                }
            }
        }
    }
    public static void fightToDeath(Character i,int[] moves,int v,int h){//This method used for fight to death situations
        for (Character c : Board.characters) {
            if(Board.board[i.location[0]+moves[v]][i.location[1]+moves[h]].equals(c.id)){//First I match the characters in the same cell
                i.attack(c);                                                             //Then attacking character attacks ones
                if(c.getHP() < i.getHP()){                                               //After that I check current hps of characters
                    i.setHP(-c.getHP());                                                 //If attacker hp is greater, it survived the other die
                    c.setHP(-c.getHP());                                                 //and attackers hp decreases by the amount of defenders hp
                    Board.board[i.location[0]+moves[v]][i.location[1]+moves[h]]=i.id;    //attacker location changes
                    Board.board[i.location[0]][i.location[1]]="  ";
                    i.setLocation(i.location[0]+moves[v],i.location[1]+moves[h]);
                    break;
                }else if(c.getHP() > i.getHP()){                                        //If defenders hp is greater, it survived the other die
                    c.setHP(-i.getHP());                                                //and defenders hp decreases by the amount of attackers hp
                    i.setHP(-i.getHP());
                    break;
                }else
                    c.setHP(-c.getHP());                                                //If their hp is same, they both die
                    i.setHP(-i.getHP());
                    break;
                }
            }
        }

}

class MoveSequence extends Exception{   //These are my custom exceptions
    MoveSequence(){}
}

class BoundaryException extends Exception{
    BoundaryException(){}
}
