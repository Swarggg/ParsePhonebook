import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DeserializingArrList {

    public static String inputFile = "src\\serializedWorkerList";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ArrayList al =  deserailList(inputFile);

        for (Object obj: al) {
            Worker worker = (Worker) obj;
            //System.out.println("Name: "+worker.getSurname()+" "+worker.getName()+". Post: "+worker.getPost());
            //System.out.println(worker);
        }

    }

    public static ArrayList deserailList (String inputFile) throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(inputFile);
        ObjectInputStream ois = new ObjectInputStream(fis);

        ArrayList  outputList=(ArrayList) ois.readObject();


        return outputList;
    }

}
