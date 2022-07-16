import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ParserFrame extends JFrame {
    private static JTextArea taMain;
    private static JTextField tfMsg;
    private static final String PFRAME_TITLE = "Phonebook parser";
    private static final int FRM_LOC_X = 100;
    private static final int FRM_LOC_Y = 100;
    private static final int FRM_WIDTH = 400;
    private static final int FRM_HEIGHT = 400;

    static String inputFile = "---";

    // static String getWorklistSIze (ArrayList MainParse.getWorker_collection())
    public ParserFrame()  {

        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Border
        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        //Dimension
        Dimension labelSize = new Dimension(280, 20);

        //Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainFrame.setTitle(PFRAME_TITLE);
        mainFrame.setLocation(FRM_LOC_X, FRM_LOC_Y);
        mainFrame.setSize(FRM_WIDTH, FRM_HEIGHT);
        mainFrame.setResizable(true);

        //Inner panel
        JPanel aligmentPanel = new JPanel();
        aligmentPanel.setBorder(BorderFactory.createTitledBorder(solidBorder,"Alignment panel", TitledBorder.CENTER, 0));


        //Label 1
        JLabel mainLabel1 = new JLabel("Number of elements in collection: ", SwingConstants.RIGHT);
        mainLabel1.setSize(labelSize);
        mainLabel1.setBorder(solidBorder);
        mainLabel1.setVerticalAlignment(JLabel.NORTH);
        mainLabel1.setHorizontalAlignment(JLabel.LEFT);
        //aligmentPanel.add(mainLabel1);

        //Label2
        JLabel mainLabel2 = new JLabel("Data load from: "+inputFile);
        mainLabel2.setVerticalAlignment(JLabel.NORTH);
        mainLabel2.setHorizontalAlignment(JLabel.LEFT);
        //aligmentPanel.add(mainLabel2);

        //Button to set Source File
        JButton btnSetSourceFile = new JButton();
        btnSetSourceFile.setSize(100,20);
        btnSetSourceFile.setText("Set path to source file");
        JFileChooser fileChooser = new JFileChooser();
        aligmentPanel.add(btnSetSourceFile);

        btnSetSourceFile.addActionListener((ae)->{int ret=  fileChooser.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                inputFile = fileChooser.getSelectedFile().getAbsolutePath();
                mainLabel1.setText("inputFile: ");
            }
        }
        );


        //Button to START parsing
        JButton btnGo = new JButton();
        btnGo.setText("Start parsing");
        btnGo.addActionListener((ae)->MainParse.getWorker_collection());
        //aligmentPanel.add(btnGo);

        //Устанавливаем диспетчер компоновки для //Inner panel
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        aligmentPanel.setLayout(gbl);

        //ограничения на элемент - лэйбл
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbl.setConstraints(mainLabel2, gbc);

        //ограничения на элемент - //Button to set Source File
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 100;
        gbc.gridy = 0;
        gbl.setConstraints(btnSetSourceFile, gbc);

        //ограничения на элемент - //Button to START parsing
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 100;
        gbc.gridy = 100;
        gbl.setConstraints(btnSetSourceFile, gbc);

        //введение элементов
        aligmentPanel.add(mainLabel2);
        aligmentPanel.add(btnSetSourceFile);
        aligmentPanel.add(btnGo);

        //вводип панель на главный фрейм
        mainFrame.getContentPane().add(aligmentPanel);

        //показываем глайвный фрейм
        mainFrame.setVisible(true);
    }


}
