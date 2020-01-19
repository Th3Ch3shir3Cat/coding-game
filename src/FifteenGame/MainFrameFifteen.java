package FifteenGame;

import Hanoi.PictureHanoi;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

    private GameOfFifteen game;

    public MainFrameFifteen(JPanel panelInfo, JLabel labelInfo, JSpinner spinner, JButton button,int width, int height){
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
        initializeComponent();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    private void initializeComponent(){

        buttonStart.addActionListener(this);
        spinnerNumberSquad.addChangeListener(this);
        add(panel,BorderLayout.SOUTH);
        this.game = new GameOfFifteen(Integer.parseInt(this.spinnerNumberSquad.getValue().toString()),400,30,this);
        add(game, BorderLayout.CENTER);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (buttonStart.getText().equals("Пауза")) {
            game.pauseAnimacion();
            buttonStart.setText("Решение");
        }
        else if(buttonStart.getText().equals("Сначала")){
            remove(this.game);
            this.game = new GameOfFifteen(Integer.parseInt(this.spinnerNumberSquad.getValue().toString()),400,30,this);
            add(game, BorderLayout.CENTER);
            buttonStart.setText("Старт");
            this.setVisible(true);
        }
        else {
            game.getInfoAboutSituation();
            game.startAnimacion();
            buttonStart.setText("Пауза");
            game.indexSolve = 0;
        }
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        this.game.pauseAnimacion();
        labelInformation.setVisible(false);
        remove(this.game);
        this.game = new GameOfFifteen(Integer.parseInt(this.spinnerNumberSquad.getValue().toString()),400,30,this);
        add(this.game, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void resultComplete() {
        buttonStart.setText("Сначала");
    }

}
