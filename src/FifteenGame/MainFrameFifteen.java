package FifteenGame;

import sample.Delegate.AnalizeForFifteen;
import sample.Delegate.AnalizeForTowers;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameFifteen extends JFrame implements ActionListener, ChangeListener {

    private JButton buttonStart;
    private JLabel labelInformation;
    private JSpinner spinnerNumberSquad;
    private JPanel panel;
    private int width;
    private int height;
    private JPanel panelLog;
    private JButton buttonLog;
    private JTextPane textpane;

    public int[] masNumber; //массив шагов

    private GameOfFifteen game;
    private Style heading = null; // стиль заголовка
    private Style normal = null; // стиль текста
    private final String STYLE_heading = "heading",
            STYLE_normal = "normal",
            FONT_style = "Times New Roman";
    private AnalizeForFifteen AnalizeTowers;


    public MainFrameFifteen(JPanel panelInfo, JLabel labelInfo, JSpinner spinner, JButton button, int width, int height) {
        super("Пятнашки");
        this.width = width;
        this.height = height;
        this.panel = panelInfo;
        this.labelInformation = labelInfo;
        this.spinnerNumberSquad = spinner;
        this.buttonStart = button;
        this.setSize(this.width, this.height);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.panelLog = new JPanel();
        this.textpane = new JTextPane();
        this.buttonLog = new JButton("Выполнить");
        initializeComponent();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    private void initializeComponent() {

        buttonStart.addActionListener(this);
        spinnerNumberSquad.addChangeListener(this);
        buttonLog.setActionCommand("ReadFromTextInput");
        buttonLog.addActionListener(this);
        this.textpane.setBackground(Color.lightGray);
        add(panel, BorderLayout.SOUTH);
        this.game = new GameOfFifteen(Integer.parseInt(this.spinnerNumberSquad.getValue().toString()), 400, 30, this);
        add(game, BorderLayout.CENTER);
        panelLog.setLayout(new BorderLayout());
        this.textpane.setFont(new Font("Dialog", Font.BOLD, 14));
        panelLog.add(textpane, BorderLayout.CENTER);
        panelLog.add(buttonLog, BorderLayout.SOUTH);
        add(this.panelLog, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if("ReadFromTextInput".equals(actionEvent.getActionCommand())){
            String string = this.textpane.getText();
            String[] str = string.split("\n");
            changeStringStyle(this.textpane,0, returnPositionInText(str,str.length), Color.black);
            checkInput(str);
        }else {
            if (buttonStart.getText().equals("Пауза")) {
                game.pauseAnimacion();
                buttonStart.setText("Решение");
            } else if (buttonStart.getText().equals("Сначала")) {
                remove(this.game);
                this.game = new GameOfFifteen(Integer.parseInt(this.spinnerNumberSquad.getValue().toString()), 400, 30, this);
                add(game, BorderLayout.CENTER);
                buttonStart.setText("Старт");
                this.setVisible(true);
            } else {
                game.getInfoAboutSituation();
                game.startAnimacion();
                buttonStart.setText("Пауза");
                game.indexSolve = 0;
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        this.game.pauseAnimacion();
        labelInformation.setVisible(false);
        remove(this.game);
        this.game = new GameOfFifteen(Integer.parseInt(this.spinnerNumberSquad.getValue().toString()), 400, 30, this);
        add(this.game, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void resultComplete() {
        buttonStart.setText("Сначала");
    }

    public void checkInput(String[] str) {
        if (str[0].length() != 0) {
            game.setStrForMove(str);
            AnalizeTowers = new AnalizeForFifteen(str);
            if (AnalizeTowers.textAnalize() != -1) {
                int numStr = AnalizeTowers.textAnalize();
                changeStringStyle(textpane, returnPositionInText(str,numStr), str[numStr].length(), Color.red);
                JOptionPane.showMessageDialog(this,
                        "Вы ошиблись в " + (numStr + 1) + " строке. Ничего страшного, со всеми бывает!!!");
            }
            else{
                masNumber = AnalizeTowers.getNumbers();
                game.startAnimacion();
            }
        }
    }

    public int returnPositionInText(String[] str, int numStr){
        int num = 0;
        for(int i = 0; i < numStr; i++){
            for(int j = 0; j < str[i].length(); j++){
                num++;
            }
        }
        return num;
    }

    public void changeStringStyle(JTextPane editor, int uk, int size, Color color){
        SimpleAttributeSet col = new SimpleAttributeSet();
        StyleConstants.setForeground(col, color);
        StyledDocument doc = editor.getStyledDocument();
        doc.setCharacterAttributes(uk,size,col,false);
    }
}
