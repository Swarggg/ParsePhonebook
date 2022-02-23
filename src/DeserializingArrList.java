import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        //System.out.println(workers.get(0).getSurname());


       List<String> sd = workers.stream()
                .filter(n->n.getName().contains("атал"))
                //.sorted()
                //.filter(m->m.getPatronymic().equals("Александровна"))
                .map(m->m.toString())
                .collect(Collectors.toList());

        System.out.println("Всего "+sd.size());
        for (String c: sd
             ) {
            System.out.println(c);
            System.out.println("---------------");
        }


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
