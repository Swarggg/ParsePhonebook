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
        String fioBufStr;
        String clearFIO;
        ArrayList<String> workerList = new ArrayList<>();

        Scanner scanFF = new Scanner(new FileReader(inputFile));
        while (scanFF.hasNextLine()) {
            if (scanFF.next().equals(patternFIO)) {

                scanFF.nextLine(); //пропускаем пустую строку
                fioBufStr = scanFF.nextLine();
                if (fioBufStr.contains("vydelen")) {//пропускаем всякие служебные строки, берем только с  "vydelen"
                    int lio = fioBufStr.lastIndexOf("<");
                    clearFIO = fioBufStr.subSequence(58, lio).toString();
                    System.out.println(clearFIO);
                    workerList.add(clearFIO);
                }
            }
        }
        System.out.println("______________________________________________");
        System.out.println("Number of elements in collection: "+workerList.size());
        return workerList;
    }

    public static void main(String[] args) throws FileNotFoundException {

        getFIO_collection();


        }


}
