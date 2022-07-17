import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.lang.Object;

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
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setTitle(PFRAME_TITLE);
        mainFrame.setLocation(FRM_LOC_X, FRM_LOC_Y);
        mainFrame.setSize(FRM_WIDTH, FRM_HEIGHT);
        mainFrame.setResizable(true);

        //Border
        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);


        //Dimension
        Dimension labelSize = new Dimension(28, 20);

        //Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new VerticalLayout());
        Border borderButtonPanel = BorderFactory.createTitledBorder(solidBorder, "Buttons panel", TitledBorder.CENTER, 0);
        buttonPanel.setBorder(borderButtonPanel);

        //Status Panel
        JPanel statusPanel = new JPanel();
        Border borderStatusPanel= BorderFactory.createTitledBorder(solidBorder, "Status panel", TitledBorder.CENTER, 0);
        statusPanel.setPreferredSize(labelSize);
        statusPanel.setBorder(borderStatusPanel);

/*
        //Main Text area
        JTextField mainTextField = new JTextField("Results will replace here", JTextField.NORTH, 20);
        Border borderTextField = BorderFactory.createTitledBorder(solidBorder, "Text field", TitledBorder.CENTER, 0);
        mainTextField.setBorder(borderTextField);
*/

        //Label Data load from
        JLabel labelSourceFile = new JLabel("Data load from: "+inputFile);
        statusPanel.setBorder(solidBorder);
        //statusPanel.setPreferredSize();
        statusPanel.add(labelSourceFile);



        /*
        //Label 1
        JLabel mainLabel1 = new JLabel("Number of elements in collection: ", SwingConstants.RIGHT);
        mainLabel1.setSize(labelSize);
        mainLabel1.setBorder(solidBorder);
        mainLabel1.setVerticalAlignment(JLabel.NORTH);
        mainLabel1.setHorizontalAlignment(JLabel.LEFT);
        //buttonPanel.add(mainLabel1);
        */

        //buttonPanel.add(labelSourceFile);

        //Button to set Source File
        JButton btnSetSourceFile = new JButton();

        btnSetSourceFile.setText("Set path to source file");
        JFileChooser fileChooser = new JFileChooser();
        buttonPanel.add(btnSetSourceFile);

        btnSetSourceFile.addActionListener((ae)->{int ret=  fileChooser.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                inputFile = fileChooser.getSelectedFile().getAbsolutePath();
                labelSourceFile.setText("inputFile: ");
            }
        }
        );


        //Button to START parsing
        JButton btnGo = new JButton();
        btnGo.setText("Start parsing");
        btnGo.addActionListener((ae)->MainParse.getWorker_collection());
        buttonPanel.add(btnGo);

        /*
        //Устанавливаем диспетчер компоновки для //Buttons panel
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        buttonPanel.setLayout(gbl);

        //ограничения на элемент - лэйбл
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbl.setConstraints(labelSourceFile, gbc);

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
         */

        //введение элементов


        //вводи панель на главный фрейм
        mainFrame.add(statusPanel, BorderLayout.WEST);
        mainFrame.add(buttonPanel, BorderLayout.EAST);

        //показываем глайвный фрейм
        mainFrame.setVisible(true);
    }


}
