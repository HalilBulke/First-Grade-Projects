import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        try {
            FileWriter Writer = new FileWriter("output.txt");
            try {
                List<Article> articles = new ArrayList<Article>();
                List<Author> authors = new ArrayList<Author>();

                FileReader commandFile = new FileReader(args[1]);
                Scanner commandReader = new Scanner(commandFile);

                FileReader authorFile = new FileReader(args[0]);
                Scanner authorReader = new Scanner(authorFile);

                while (authorReader.hasNextLine()) {
                    String data = authorReader.nextLine();
                    String[] datas = data.split(" ", 11);
                    switch (datas.length) {
                        case 11:
                            authors.add(new Author(datas[1], datas[2], datas[3], datas[4], datas[5], datas[6], datas[7], datas[8], datas[9], datas[10]));
                            break;

                        case 10:
                            authors.add(new Author(datas[1], datas[2], datas[3], datas[4], datas[5], datas[6], datas[7], datas[8], datas[9]));
                            break;

                        case 9:
                            authors.add(new Author(datas[1], datas[2], datas[3], datas[4], datas[5], datas[6], datas[7], datas[8]));
                            break;
                        case 8:
                            authors.add(new Author(datas[1], datas[2], datas[3], datas[4], datas[5], datas[6], datas[7]));
                            break;

                        case 7:
                            authors.add(new Author(datas[1], datas[2], datas[3], datas[4], datas[5], datas[6]));
                            break;
                        case 6:
                            authors.add(new Author(datas[1], datas[2], datas[3], datas[4], datas[5]));
                            break;
                        case 5:
                            authors.add(new Author(datas[1], datas[2], datas[3], datas[4]));
                            break;
                        case 4:
                            authors.add(new Author(datas[1], datas[2], datas[3]));
                            break;
                        case 3:
                            authors.add(new Author(datas[1], datas[2]));
                            break;
                        case 2:
                            authors.add(new Author(datas[1]));
                            break;

                    }
                }
                authorReader.close();
                while (commandReader.hasNextLine()) {
                    String command = commandReader.nextLine();
                    String[] commands = command.split(" ", 11);

                    if (commands[0].equals("read")) {
                        try {
                            FileReader articleFile = new FileReader(commands[1]);
                            Scanner articleReader = new Scanner(articleFile);

                            while (articleReader.hasNextLine()) {
                                String art = articleReader.nextLine();
                                String[] arts = art.split(" ", 10);
                                articles.add(new Article(arts[1], arts[2], arts[3], arts[4]));
                            }
                            articleReader.close();
                            for (Author i : authors) {
                                for (Article k : articles) {
                                    if(i.getArticles().size()>=5){
                                        break;
                                    }else if(i.getArticles().contains(k.getAll()) ){
                                    }
                                    else{for (int j = 0; j < i.getArticlesIds().length; j++) {
                                        if (i.getArticlesIds()[j] == null) {
                                            break;
                                        } else if (i.getArticlesIds()[j].equals(k.getPaperId())) {
                                            i.setArticles(k.getAll());

                                        }
                                    }
                                    }
                                }
                            }


                        } catch (FileNotFoundException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                    } else if (commands[0].equals("list")) {
                        Writer.write("----------------------------------------------List---------------------------------------------");
                        for (Author i : authors) {
                            Writer.write("\n");
                            Writer.write(i.getAll()+"\n");
                            for (String j : i.getArticles()){
                                Writer.write(j+"\n");
                                }
                        }
                        Writer.write("----------------------------------------------End----------------------------------------------"+"\n");
                    } else if (commands[0].equals("completeAll")) {
                        Writer.write("*************************************CompleteAll Successful*************************************"+"\n");
                        for (Author i : authors) {
                            for (Article k : articles) {
                                if(i.getArticles().size()>=5) {
                                    break;
                                }else if (i.getArticles().contains(k.getAll())){
                                }else if (i.getId().equals(k.getPaperId().substring(0, 3))) {
                                        i.setArticles(k.getAll());
                                    }
                                }

                            }
                        }
                     else if (commands[0].equals("del")) {
                        Writer.write("*************************************del Successful*************************************"+"\n");
                        authors.removeIf(i -> i.getId().equals(commands[1]));
                    } else if (commands[0].equals("sortedAll")) {
                        Writer.write("*************************************SortedAll Successful*************************************"+"\n");
                        for (Author i : authors) {
                            Collections.sort(i.getArticles());
                        }
                    }
                }Writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
