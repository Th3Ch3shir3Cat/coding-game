package Hanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PictureHanoi extends JPanel{

    private int numberOfDisks; //Кол-во дисков
    private MainHanoi hanoi; //Панель родитель
    private JLabel[] rounds;

    //Конструктор
    public PictureHanoi(int numberOfDisks, MainHanoi hanoi){

        this.numberOfDisks = numberOfDisks;
        this.hanoi = hanoi;
        rounds = new JLabel[numberOfDisks];
        for(int i = 0; i < numberOfDisks; i++)
            rounds[i] = new JLabel();
        initializePanel();
    }

    //Инициализируем сцену
    private void initializePanel(){
        setBackground(Color.black);
        for(int i = 0; i < numberOfDisks; i++){
            rounds[i] = new JLabel();
            ImageIcon ii = new ImageIcon(this.getClass().getResource("/res/Images/" + (i + 1) + ".png"));
            rounds[i].setIcon(ii);
            add(new Round(rounds[i]));
        }
    }


}
