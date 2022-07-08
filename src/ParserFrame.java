import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ParserFrame extends JFrame {
    private static JTextArea taMain;
    private static JTextField tfMsg;
    private static final String PFRAME_TITLE = "Phonebook parser";
    private static final int FRM_LOC_X = 100;
    private static final int FRM_LOC_Y = 100;
    private static final int FRM_WIDTH = 400;
    private static final int FRM_HEIGHT = 400;

    // static String getWorklistSIze (ArrayList MainParse.getWorker_collection())
    static void frameDraw(JFrame frame){
        tfMsg = new JTextField("Number of elements in collection: "+MainParse.getWorker_collection().size());

        //taMain = new JTextArea(FRM_HEIGHT/19, 50);

       // JScrollPane spMain = new JScrollPane(taMain);
       // spMain.setLocation(0,0);
       // taMain.setLineWrap(true);
       // taMain.setEditable(false);

        JButton btnSend = new JButton();
        btnSend.setText("Send");
        btnSend.setToolTipText("Broadcast message");
       /* btnSend.addActionListener(e -> {
            try {
                btnSend_Handler();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //taMain.append("button works");
        });*/

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(PFRAME_TITLE);
        frame.setLocation(FRM_LOC_X, FRM_LOC_Y);
        frame.setSize(FRM_WIDTH, FRM_HEIGHT);
        frame.setResizable(true);
        //frame.getContentPane().add(BorderLayout.NORTH, spMain);
        frame.getContentPane().add(BorderLayout.CENTER, tfMsg);
        frame.getContentPane().add(BorderLayout.EAST, btnSend);
        frame.setVisible(true);
    }


}
