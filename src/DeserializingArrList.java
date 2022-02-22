import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DeserializingArrList {

    public static String inputFile = "src\\serializedWorkerList";

    public static void main(String[] args) throws IOException {

       // ArrayList buf =MainParse.getWorker_collection();

        //MainParse.serializingList(buf, inputFile);

        ArrayList al =  deserailList(inputFile);

        ArrayList<Worker> workers = new ArrayList<>();

        for (Object obj: al) {
            Worker worker = (Worker) obj;

            workers.add(worker);

            //System.out.println("Name: "+worker.getSurname()+" "+worker.getName()+". Post: "+worker.getPost());

        }
        System.out.println("Number of elements in collection: "+workers.size());
        System.out.println(workers.get(0).getSurname());

    }

    public static ArrayList deserailList (String inputFile) {
        ArrayList outputList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(inputFile);
            ObjectInputStream ois = new ObjectInputStream(fis);

            outputList = (ArrayList) ois.readObject();

        } catch (IOException ioe) {
            System.out.println("File "+inputFile+" not found. Add file and restart programm.");
        }
        catch (ClassNotFoundException cce)  {
            cce.printStackTrace();
        }
        return outputList;
    }

}
