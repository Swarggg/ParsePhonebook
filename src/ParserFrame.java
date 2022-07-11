import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ParserFrame {
    private static JTextArea taMain;
    private static JTextField tfMsg;
    private static final String PFRAME_TITLE = "Phonebook parser";
    private static final int FRM_LOC_X = 100;
    private static final int FRM_LOC_Y = 100;
    private static final int FRM_WIDTH = 400;
    private static final int FRM_HEIGHT = 400;

    static String inputFile;

    // static String getWorklistSIze (ArrayList MainParse.getWorker_collection())
    public ParserFrame() {

        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        Dimension labelSize = new Dimension(280, 20);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel aligmentPanel = new JPanel(new FlowLayout());
        aligmentPanel.setBorder(BorderFactory.createTitledBorder(solidBorder,"Alignment panel", TitledBorder.CENTER, 0));
        //aligmentPanel.setAlignmentX(1.0f);
        //aligmentPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        //tfMsg = new JTextField("Number of elements in collection: "+MainParse.getWorker_collection().size());
        //taMain = new JTextArea(FRM_HEIGHT/19, 50);

       // JScrollPane spMain = new JScrollPane(taMain);
       // spMain.setLocation(0,0);
       // taMain.setLineWrap(true);
       // taMain.setEditable(false);



        //btnSetSourceFile.setSize(2,2);
        //btnSend.setToolTipText("Broadcast message");
       /* btnSend.addActionListener(e -> {
            try {
                btnSend_Handler();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //taMain.append("button works");
        });*/
       //


        mainFrame.setTitle(PFRAME_TITLE);
        mainFrame.setLocation(FRM_LOC_X, FRM_LOC_Y);
        mainFrame.setSize(FRM_WIDTH, FRM_HEIGHT);
        mainFrame.setResizable(true);

        JLabel mainLabel1 = new JLabel("Number of elements in collection: ", SwingConstants.LEFT);
        mainLabel1.setSize(labelSize);
        mainLabel1.setBorder(solidBorder);
        mainLabel1.setVerticalAlignment(JLabel.NORTH);
        //mainLabel1.setHorizontalAlignment();
        aligmentPanel.add(mainLabel1);

        JLabel mainLabel2 = new JLabel("Data load from: ");
        mainLabel2.setVerticalAlignment(JLabel.NORTH);
        mainLabel2.setHorizontalAlignment(JLabel.LEFT);
        aligmentPanel.add(mainLabel2);

        JButton btnSetSourceFile = new JButton();
        btnSetSourceFile.setText("Set source file");
        JFileChooser fileChooser = new JFileChooser();



        btnSetSourceFile.addActionListener((ae)->{int ret=  fileChooser.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                inputFile = fileChooser.getSelectedFile().getAbsolutePath();
                mainLabel1.setText("inputFile: ");
            }
        }
        );

        JButton btnGo = new JButton();
        btnSetSourceFile.setText("Go");
        btnGo.addActionListener((ae)->MainParse.getWorker_collection());

        //frame.getContentPane().add(BorderLayout.NORTH, spMain);

        //mainFrame.getContentPane().add(BorderLayout.CENTER, tfMsg);
        //mainFrame.getContentPane().add(BorderLayout.EAST, btnSend);
        mainFrame.getContentPane().add(aligmentPanel);
        mainFrame.getContentPane().add(BorderLayout.NORTH, btnSetSourceFile);
        mainFrame.getContentPane().add(BorderLayout.EAST, btnGo);
        mainFrame.setVisible(true);
    }


}
