package Hanoi;

import javax.swing.*;
import java.awt.*;

public class PictureHanoi extends JPanel{

    private MainHanoi hanoi; //Панель родитель
    private Round[] rounds;
    private int numberOfDisks;


    //Конструктор
    public PictureHanoi(MainHanoi hanoi, int numberOfDisks){
        this.hanoi = hanoi;
        this.numberOfDisks = numberOfDisks;
        rounds = new Round[numberOfDisks];
        initializePanel();
    }


    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        for(int i = 0; i < numberOfDisks; i++){
            g2.drawImage(rounds[i].getImg(), rounds[i].getX(), rounds[i].getY(), this);
        }
        g2.drawString("Башня 1", 115, 320);
        g2.drawString("Башня 2", 315, 320);
        g2.drawString("Башня 3", 515, 320);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        repaint();
    }


    //Инициализируем сцену
    private void initializePanel(){
        setBackground(Color.black);

        for(int i = 0; i < numberOfDisks; i++){
            int w = numberOfDisks - i;
            ImageIcon ii = new ImageIcon(this.getClass().getResource("/res/Images/" + (i + 1) + ".png"));
            rounds[i] = new Round(posicionXRound(i+1,1),posicionYRound(w),ii.getImage());
            addMouseListener(rounds[i]);
            addMouseMotionListener(rounds[i]);
        }
    }


    public static int posicionXRound(int numberOfDisk, int torre) {
        int k = (torre - 1) * 200;
        switch (numberOfDisk) {
            case 1:
                return 110 + k;
            case 2:
                return 100 + k;
            case 3:
                return 90 + k;
            case 4:
                return 80 + k;
            case 5:
                return 70 + k;
            case 6:
                return 60 + k;
            case 7:
                return 50 + k;
            case 8:
                return 40 + k;
        }
        return 0;
    }

    public static int posicionYRound(int numberOfDisk) {
        switch (numberOfDisk) {
            case 1:
                return 260;
            case 2:
                return 233;
            case 3:
                return 206;
            case 4:
                return 179;
            case 5:
                return 152;
            case 6:
                return 125;
            case 7:
                return 98;
            case 8:
                return 71;
        }
        return 0;
    }
}
