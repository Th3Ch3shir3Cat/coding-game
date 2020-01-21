package Hanoi;

import Hanoi.State.*;
import sample.Delegate.AnalizeForFifteen;
import sample.Delegate.AnalizeForTowers;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.BreakIterator;

public class MainHanoi extends JFrame implements ActionListener, ChangeListener {

    private JLabel labelInformation;
    private JSpinner spinnerNumberDisks;
    private JButton buttonStart;
    private JButton buttonBack;
    private JButton buttonInput;
    private PictureHanoi panelHanoi;
    private JPanel panel;
    private JPanel panelLog;
    private JTextPane textPane;
    private int width;
    private int height;
    private AnalizeForTowers AnalizeTowers;


    private static int numtry = 1;

    public MainHanoi(JPanel panelInfo, JLabel labelInfo, JSpinner spinner, JButton button,JButton buttonBack,int width, int height){
        super("Ханойские башни");
        this.panel = panelInfo;
        this.buttonInput = new JButton("Выполнить");
        //this.inputText = new JTextArea("",8,20);
        this.textPane = new JTextPane();
        this.panelLog = new JPanel();
        this.labelInformation = labelInfo;
        this.spinnerNumberDisks = spinner;
        this.width = width;
        this.height = height;
        this.buttonStart = button;
        this.buttonBack = buttonBack;
        this.setSize(this.width, this.height);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        inicializeComponents();
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    private void inicializeComponents() {

        buttonStart.addActionListener(this);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                panelHanoi.undo();
            }
        });
        buttonInput.setActionCommand("ReadFromTextInput");
        buttonInput.addActionListener(this);
        spinnerNumberDisks.addChangeListener(this);
        //this.inputText.setFont(new Font("Dialog", Font.PLAIN, 12));
        //this.inputText.setTabSize(10);
        add(panel, BorderLayout.SOUTH);
        panelHanoi = new PictureHanoi(this,8);
        add(panelHanoi, BorderLayout.CENTER);
        panelLog.setLayout(new BorderLayout());
        //panelLog.add(this.inputText, BorderLayout.CENTER);
        this.textPane.setFont(new Font("Dialog", Font.BOLD, 14));
        panelLog.add(textPane, BorderLayout.CENTER);
        panelLog.add(buttonInput, BorderLayout.SOUTH);
        add(this.panelLog, BorderLayout.EAST);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if("ReadFromTextInput".equals(actionEvent.getActionCommand())){
            remove(panelHanoi);
            panelHanoi = new PictureHanoi(this, Integer.parseInt(spinnerNumberDisks.getValue().toString()));
            add(panelHanoi, BorderLayout.CENTER);
            this.setVisible(true);
            String string = this.textPane.getText();
            String[] str = string.split("\n");
            int sum = 0;
            for(int i = 0; i < str.length; i++){
                for(int j = 0; j < str[i].length(); j++)
                    sum++;
            }
            changeStringStyle(this.textPane,0, sum, Color.black);
            checkInput(str);
        }
        else {
            if (buttonStart.getText().equals("Пауза")) {
                panelHanoi.pauseAnimacion();
                buttonStart.setText(panelHanoi.getState().continuest());
                panelHanoi.changeState(new ContinueState(panelHanoi));
            } else {
                if (buttonStart.getText().equals("Сначала")) {
                    remove(panelHanoi);
                    this.numtry = 1;
                    buttonStart.setText(panelHanoi.getState().start());
                    panelHanoi = new PictureHanoi(this, Integer.parseInt(spinnerNumberDisks.getValue().toString()));
                    add(panelHanoi, BorderLayout.CENTER);
                    labelInformation.setVisible(false);
                    this.setVisible(true);
                    panelHanoi.changeState(new StartState(panelHanoi));
                } else {
                    if (buttonStart.getText().equals("Решение")) {
                        remove(panelHanoi);
                        buttonStart.setText("Пауза");
                        panelHanoi = new PictureHanoi(this, Integer.parseInt(spinnerNumberDisks.getValue().toString()));
                        panelHanoi.algoritmoHanoi(Integer.parseInt(spinnerNumberDisks.getValue().toString()),1,2,3);
                        panelHanoi.startAnimacion();
                        add(panelHanoi, BorderLayout.CENTER);
                        labelInformation.setVisible(false);
                        this.setVisible(true);
                        panelHanoi.changeState(new PauseState(panelHanoi));
                    } else {
                        panelHanoi.startAnimacion();
                        buttonStart.setText(panelHanoi.getState().pause());
                        panelHanoi.changeState(new PauseState(panelHanoi));
                    }
                }
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        panelHanoi.pauseAnimacion();
        buttonStart.setText("Решение");
        labelInformation.setVisible(false);
        remove(panelHanoi);
        panelHanoi = new PictureHanoi( this,Integer.parseInt(spinnerNumberDisks.getValue().toString()));
        add(panelHanoi, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public int getNumtry(){
        return this.numtry;
    }

    public void resultComplete() {
        buttonStart.setText("Сначала");
        panelHanoi.changeState(new AgainState(panelHanoi));
        labelInformation.setVisible(true);
    }

    public void checkInput(String[] str){
        if(str[0].length() != 0) {
            AnalizeTowers = new AnalizeForTowers(str);
            if (AnalizeTowers.textAnalize() != 0) {
                this.numtry++;
                int numStr = AnalizeTowers.textAnalize();
                int numPos = 0;
                for (int i = 0; i < numStr; i++) {
                    for (int j = 0; j < str[i].length(); j++) {
                        numPos++;
                    }
                }
                changeStringStyle(textPane, numPos, str[numStr].length(), Color.red);
                JOptionPane.showMessageDialog(this,
                        "Вы ошиблись в " + (numStr + 1) + " строке. Ничего страшного, со всеми бывает!!!");
            } else {
                panelHanoi.removeAllInfoAboutText();
                for (int i = 0; i < str.length; i++) {
                    char[] chars = str[i].toCharArray();
                    if (!panelHanoi.addMove(Character.getNumericValue(chars[5]), Character.getNumericValue(chars[7])))
                        break;
                }
                panelHanoi.initializeTowers();
                panelHanoi.setNumberSteps(0);
                panelHanoi.startAnimacion();
            }
        }
    }

    public void changeStringStyle(JTextPane editor, int uk, int size, Color color){
        SimpleAttributeSet col = new SimpleAttributeSet();
        StyleConstants.setForeground(col, color);
        StyledDocument doc = editor.getStyledDocument();
        doc.setCharacterAttributes(uk,size,col,false);
    }
}
