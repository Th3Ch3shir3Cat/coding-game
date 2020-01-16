package FifteenGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameFifteen extends JFrame implements ActionListener {

    private JButton buttonStart;
    private GameOfFifteen game;

    public MainFrameFifteen(){
        super("Пятнашки");
        configuration();
        initializeComponent();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    private void configuration() {
        this.setSize(680, 600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.game = new GameOfFifteen(3,500,30,this);
        add(game, BorderLayout.CENTER);
    }

    private void initializeComponent(){

        //buttonStart = new JButton("Решение");
        //buttonStart.addActionListener(this);
        //add(buttonStart,BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    public void resultComplete() {
        buttonStart.setText("Решение");
    }
}
