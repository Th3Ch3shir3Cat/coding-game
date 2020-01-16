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
        this.game = new GameOfFifteen(3,550,30);
        add(game, BorderLayout.CENTER);
    }

    private void initializeComponent(){

        buttonStart = new JButton("Решение");
        buttonStart.addActionListener(this);
        add(buttonStart,BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int [] arr1 = game.getTiles();
        int size = (int)Math.sqrt(arr1.length);
        int[][] situationOnBoard = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                situationOnBoard[i][j] = arr1[i*size + j];
            }
        }

        Board initial = new Board(situationOnBoard);
        Solve solve = new Solve(initial);

        for(Board board: solve.solution()){
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    System.out.print(board.getBlocks()[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
