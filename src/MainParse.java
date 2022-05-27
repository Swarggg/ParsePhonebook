import com.mysql.cj.jdbc.Driver;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс предназначен для:
 * 1. парсинга парсинга .html файла в коллекцию
 * 2. Сериализации коллекции
 * 3. Записи коллекции в файл
 *
 * created by @-=SwarG=-
 */

public class MainParse {

    static String inputFile = "src\\phoneBookSrc1.html";
    static String outputFile = "src\\serializedWorkerList";
    static String fileToWrite = "src\\phonebook2.txt";

    public static void main(String[] args) {

        /**
         * загрузка драйвера подключения к БД
         */
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

        } catch (SQLException sqle) {
            System.err.println("Driver not registered");
        }

        //getWorker_collection(); //по выполнению метода получаем коллекцию workerList

        //serializingList(getWorker_collection(), outputFile);
        //writeListToFile(getWorker_collection(),fileToWrite);

        Worker oneOfWorker = (Worker) getWorker_collection().get(3);
        String nameOfWorker = oneOfWorker.getName();

        /**
         * подключение к БД
         */
        try (Connection connectionAW = DriverManager.getConnection(ConnectionData.URL, ConnectionData.USERNAME, ConnectionData.PASSWORD);
             Statement statementAW = connectionAW.createStatement())  {

            statementAW.execute("INSERT INTO workers (name, patro, surname, telephone) VALUES ('"+nameOfWorker+"', 'Palkovna', 'Lesgustova', '2342')");

        }


        catch (SQLException qwe) {
            qwe.printStackTrace();
        }




    }

    /**
     *Этот метод предназначен для записи коллекции объектов типа Worker в файл
     *
     * @param inputList  коллекция для записи в файл
     *
     * @param outputFile  файл, куда записывается коллекция
     */
    public static void writeListToFile (ArrayList<Worker> inputList, String outputFile)  {
        try (FileWriter fileWriter = new FileWriter(fileToWrite, true)) {

            for (Worker worker : inputList) {
                String w = "ФИО: " + worker.getSurname() + " " + worker.getName() + " " + worker.getPatronymic() +
                        " Tel: " + worker.getTelephone() + "\r\nДолжность: " + worker.getPost() +
                        "\r\nМесто: " + worker.getDepartment() + "\r\n--------------------------------\r\n";
                fileWriter.append(w);
            }

        } catch (IOException ioe) {
            System.out.println("File "+fileToWrite+" for write data not found. Add file and restart programm");
            }
    }

    /**
     * Метод для сериализации коллекции
     * @param arrayList коллекция для сериализации
     * @param outputFile файл, где сохраняется сериализованная коллекция
     */
    public static void serializingList (ArrayList arrayList, String outputFile) {

        try (FileOutputStream fos = new FileOutputStream(outputFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(arrayList);

        } catch (IOException ioe) {
            System.out.println("File not found");
            }
    }


    /** Метод для парсинга .html странички
     * конкретного сайта и получения коллекции объектов
     * вида  new Worker (name, patronymic, surname, post, department, telephone).
     * <p>
     * Со страницами других сайтов работать не будет
     */

    public static ArrayList getWorker_collection() {

        String name;
        String patronymic;
        String surname;
        String buffStr;
        String clearFIO;
        String post;
        String department;
        String telephone;

        ArrayList <Worker> workerList = new ArrayList<>();


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
                        telephone = nextBuf.subSequence(nextBuf.indexOf("Местн:") + 7, nextBuf.indexOf("<br>")).toString();
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
