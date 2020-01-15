package Hanoi;

import javax.swing.*;
import java.awt.*;

public class PictureHanoi extends JPanel{

    private MainHanoi hanoi; //Панель родитель

    private Round[] rounds; //Диски
    private int numberOfDisks; //Кол-во дисков

    private Towers[] towers;

    //Конструктор
    public PictureHanoi(MainHanoi hanoi, int numberOfDisks){
        this.hanoi = hanoi;
        this.numberOfDisks = numberOfDisks;
        rounds = new Round[numberOfDisks];
        towers = new Towers[3];
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
        g2.drawString("Кол-во на башне 1: " + towers[0].getNumberOfDisksOnTower(), 510,90);
        g2.drawString("Кол-во на башне 2: " + towers[1].getNumberOfDisksOnTower(), 510,120);
        g2.drawString("Кол-во на башне 3: " + towers[2].getNumberOfDisksOnTower(), 510,150);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    //Инициализируем сцену
    private void initializePanel(){
        setBackground(Color.black);

        for(int i = 0; i < numberOfDisks; i++){
            int w = numberOfDisks - i;
            ImageIcon ii = new ImageIcon(this.getClass().getResource("/res/Images/" + (i + 1) + ".png"));
            rounds[i] = new Round(posicionXRound(i+1,1),posicionYRound(w),ii.getImage(),this,i+1,0);
            addMouseListener(rounds[i]);
            addMouseMotionListener(rounds[i]);
        }

        towers[0] = new Towers(0,210,8);
        towers[1] = new Towers(210,420,0);
        towers[2] = new Towers(420,630,0);

        for(int i = 0; i < numberOfDisks; i++){
            towers[0].addRound(rounds[i]);
        }

    }

    public void goTowers(Round round, int num){
        int indexTower = num;
        for(int i = 0; i < 3; i++) {
            if(towers[i].checkDisksInTower(round.getX(),round.getX() + round.getImg().getWidth(null)))
                indexTower = i;
        }
        if(indexTower != num) {
            if (towers[indexTower].getNumberOfDisksOnTower() != 0 && towers[indexTower].getLastRound().getNumber() > round.getNumber()) {
                towers[indexTower].setNumberOfDisksOnTower(towers[indexTower].getNumberOfDisksOnTower() + 1);
                towers[indexTower].addRound(round);
                towers[num].removeRound();
                int position = (towers[indexTower].getX2() - towers[indexTower].getX1() - round.getImg().getWidth(null)) / 2;
                round.setX(towers[indexTower].getX1() + position);
                round.setY(posicionYRound(towers[indexTower].getNumberOfDisksOnTower()));
                round.setStartX(round.getX());
                round.setStartY(round.getY());
                round.setNumberTower(indexTower);
            } else if (towers[indexTower].getNumberOfDisksOnTower() == 0) {
                towers[indexTower].setNumberOfDisksOnTower(towers[indexTower].getNumberOfDisksOnTower() + 1);
                towers[indexTower].addRound(round);
                towers[num].removeRound();
                int position = (towers[indexTower].getX2() - towers[indexTower].getX1() - round.getImg().getWidth(null)) / 2;
                round.setX(towers[indexTower].getX1() + position);
                round.setY(posicionYRound(towers[indexTower].getNumberOfDisksOnTower()));
                round.setStartX(round.getX());
                round.setStartY(round.getY());
                round.setNumberTower(indexTower);
            } else {
                round.setX(round.getStartX());
                round.setY(round.getStartY());
            }
        }
        else{
            round.setX(round.getStartX());
            round.setY(round.getStartY());
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
