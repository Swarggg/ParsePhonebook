/**
 * This programm was created by @-=SwarG=-
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainParse {

    static String inputFile = "src\\phoneBookSrc.html";
    static String outputFile = "src\\serializedWorkerList";

    public static void main(String[] args) throws IOException {

        //getWorker_collection();
        serializingList(getWorker_collection(), outputFile);

    }

    public static void serializingList (ArrayList arrayList, String outputFile) throws IOException {

        FileOutputStream fos = new FileOutputStream(outputFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(arrayList);
        oos.close();

    }

    public static ArrayList getWorker_collection() {

        String name;
        String patronymic;
        String surname;
        String buffStr;
        String clearFIO;
        String post;
        String department;
        String telephone;

        ArrayList<Worker> workerList = new ArrayList<>();


        try {
            Scanner scanFF = new Scanner(new FileReader(inputFile));

                while (scanFF.hasNextLine()) {
                buffStr = scanFF.nextLine();

                    whenEmpty:
                    if (buffStr.contains("vydelen\">")) {//выбираем строки только с  "vydelen", т.к. только в них начинают идти имена

                    //формируем строку только с ФИО
                    clearFIO = buffStr.subSequence(buffStr.indexOf("vydelen\">")+9, buffStr.lastIndexOf("<")).toString();

                        if (clearFIO.contains("  ")) {    //этим условием пропускаем строчки не относящиеся к людям
                            break whenEmpty; //goto ))
                        }

                    Scanner fioParser = new Scanner(clearFIO);
                    surname=fioParser.next();
                    name=fioParser.next();
                    patronymic=fioParser.next();

                    //берем следующую строку, в ней идут должность и подразделение
                    String nextBuf=scanFF.nextLine();
                    post=nextBuf.subSequence(nextBuf.lastIndexOf("00006C")+9, nextBuf.lastIndexOf("</span>")).toString();
                    department=nextBuf.subSequence(nextBuf.lastIndexOf("<br>")+4, nextBuf.indexOf("</p>")).toString();

                    //берем следующую строку, в ней идет телефон
                    nextBuf= scanFF.nextLine();

                    if (!nextBuf.contains("Местн:")) { //исключаем строки, где нет местного телефона
                        telephone=" ";
                    } else {
                        telephone = nextBuf.subSequence(nextBuf.indexOf("Местн:") + 5, nextBuf.indexOf("<br>")).toString();
                    }

                    //собираем все данные в экземпляр и добавляем в коллекцию
                    workerList.add(new Worker(name, patronymic, surname, post, department, telephone));
                    } //close if "vydelen"
                } //close while

        }   catch (FileNotFoundException fnfe) {
            System.out.println("File "+inputFile+" not found. Add file and restart programm.");
            }

        /*for (Worker worker: workerList) {
            String suname=worker.getSurname();
            String tab="\t\t\t";

            if (suname.length()<6) {
                tab="\t\t\t\t";
            }

            if (suname.length()>9) {
                tab="\t\t";
            }

            System.out.println("Фамилия: "+suname+"."+tab+"Должность: "+worker.getPost());
        }*/

        System.out.println("______________________________________________");
        System.out.println("Number of elements in collection: "+workerList.size());

        return workerList;
    }






}
