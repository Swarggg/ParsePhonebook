/**
 * This programm was created by @swarg
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainParse {

    static String inputFile = "src\\phoneBookSrc.html";

    public static ArrayList getFIO_collection () throws FileNotFoundException {

        String patternFIO = "<tbody><tr>";
        String patternPost = "00006C";
        String name;
        String patronymic;
        String surname;
        String fioBufStr;
        String clearFIO;
        String post;
        String department;
        int telephone;
        ArrayList<Worker> workerList = new ArrayList<>();

        Scanner scanFF = new Scanner(new FileReader(inputFile));
        while (scanFF.hasNextLine()) {


            if (scanFF.next().equals(patternFIO)) {

                scanFF.nextLine(); //пропускаем пустую строку
                fioBufStr = scanFF.nextLine();
                if (fioBufStr.contains("vydelen\">")) {//пропускаем всякие служебные строки, берем только с  "vydelen"

                    Worker worker = new Worker("name", "patronymic", "surname", "post", "department", "tel");
                    workerList.add(worker);
                    int indexOfWorker = workerList.indexOf(worker);
                    System.out.println("workerList.indexOf(worker)="+indexOfWorker);

                    int lio = fioBufStr.lastIndexOf("<");
                    clearFIO = fioBufStr.subSequence(58, lio).toString();
                    if (!clearFIO.contains("  ")) {    //этим условием убираем строчки не относящиеся к людям
                        //System.out.println(clearFIO);

                        Scanner fioParser = new Scanner(clearFIO);
                        surname=fioParser.next();
                        name=fioParser.next();
                        patronymic=fioParser.next();

                        System.out.print(name+" ");
                        System.out.print(patronymic+" ");
                        System.out.println(surname);

                        workerList.set(indexOfWorker, new Worker(name, patronymic, surname, worker.getPost(), worker.getDepartment(), worker.getTelephone()));

                        //System.out.println(workerList.get(indexOfWorker).getName()+workerList.get(indexOfWorker).getPost());
                    }

                    String nextBuf= scanFF.nextLine();
                    post=nextBuf.subSequence(nextBuf.lastIndexOf(patternPost)+9, nextBuf.lastIndexOf("</span>")).toString();

                    department=nextBuf.subSequence(nextBuf.lastIndexOf("<br>")+4, nextBuf.indexOf("</p>")).toString();
                    System.out.println(post+" "+department);

                    nextBuf= scanFF.nextLine();
                    System.out.println(nextBuf);

                }

            }



        } //close while
        System.out.println("______________________________________________");
        System.out.println("Number of elements in collection: "+workerList.size());
        return workerList;
    }

    public static ArrayList getFIO_collection2 () throws FileNotFoundException {

        String patternPost = "00006C";
        String name;
        String patronymic;
        String surname;
        String fioBufStr;
        String clearFIO;
        String post;
        String department;
        String telephone;

        ArrayList<Worker> workerList = new ArrayList<>();

        Scanner scanFF = new Scanner(new FileReader(inputFile));
         while (scanFF.hasNextLine()) {
                fioBufStr = scanFF.nextLine();

                whenEmpty:
                if (fioBufStr.contains("vydelen\">")) {//пропускаем всякие служебные строки, берем только с  "vydelen"

                    Worker worker = new Worker("name", "patronymic", "surname", "post", "department", "0");
                    workerList.add(worker);
                    int indexOfWorker = workerList.indexOf(worker);
                    //System.out.println("workerList.indexOf(worker)="+indexOfWorker);

                    clearFIO = fioBufStr.subSequence(fioBufStr.indexOf("vydelen\">")+9, fioBufStr.lastIndexOf("<")).toString();

                    if (clearFIO.contains("  ")) {    //этим условием пропускаем строчки не относящиеся к людям
                        break whenEmpty;
                    }

                    Scanner fioParser = new Scanner(clearFIO);
                    surname=fioParser.next();
                    name=fioParser.next();
                    patronymic=fioParser.next();

                    //System.out.print(name+" ");
                    //System.out.print(patronymic+" ");
                    //System.out.println(surname);



                           //System.out.println(workerList.get(indexOfWorker).getName()+workerList.get(indexOfWorker).getPost());


                    String nextBuf= scanFF.nextLine();
                    post=nextBuf.subSequence(nextBuf.lastIndexOf(patternPost)+9, nextBuf.lastIndexOf("</span>")).toString();

                    department=nextBuf.subSequence(nextBuf.lastIndexOf("<br>")+4, nextBuf.indexOf("</p>")).toString();


                    nextBuf= scanFF.nextLine();
                    if (!nextBuf.contains("Местн:")) {
                        telephone=" ";
                    } else {
                        telephone = nextBuf.subSequence(nextBuf.indexOf("Местн:") + 5, nextBuf.indexOf("<br>")).toString();
                    }
                    //System.out.println(nextBuf);

                    //System.out.println("                                   Tele: "+telephone);

                    workerList.set(indexOfWorker, new Worker(name, patronymic, surname, post, department, telephone));

                }





        } //close while
        System.out.println("______________________________________________");
        System.out.println("Number of elements in collection: "+workerList.size());
        return workerList;
    }



    public static void main(String[] args) throws FileNotFoundException {

        getFIO_collection2();


        }


}
