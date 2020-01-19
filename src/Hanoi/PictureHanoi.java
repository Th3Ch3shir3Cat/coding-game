package Hanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PictureHanoi extends JPanel implements ActionListener {

    private MainHanoi hanoi; //Панель родитель

    private Round[] rounds; //Диски
    private int numberOfDisks; //Кол-во дисков

    private Towers[] towers;

    private Timer timer;

    private int step;
    private int numberSteps;

    private int topDisks;
    private int x,y;

    private MoveDisks[] moveDIsks;
    private int numberOfSteps;
    private boolean moveComplete;

    private static final int VELOCIDAD = 1;

    //Конструктор
    public PictureHanoi(MainHanoi hanoi, int numberOfDisks){
        this.hanoi = hanoi;
        this.numberOfDisks = numberOfDisks;
        rounds = new Round[numberOfDisks];
        towers = new Towers[3];
        moveDIsks = new MoveDisks[(int) Math.pow(2, this.numberOfDisks)];
        initializePanel();
        timer = new Timer(VELOCIDAD, this);
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

        towers[0] = new Towers(0,220,numberOfDisks);
        towers[1] = new Towers(220,440,0);
        towers[2] = new Towers(440,660,0);

        for(int i = numberOfDisks - 1; i >= 0; i--){
            towers[0].addRound(rounds[i]);
        }

        x = towers[0].getLastRound().getX();
        y = towers[0].getLastRound().getY();

        topDisks = 1;
        numberOfSteps = 1;
        step = 1;
        moveComplete = false;
        algoritmoHanoi(numberOfDisks,1,2,3);

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


    public void algoritmoHanoi(int n, int from, int inter, int to) {
        if (n == 0) {
            return;
        }
        algoritmoHanoi(n - 1, from, to, inter);
        numberSteps++;
        moveDIsks[numberSteps] = new MoveDisks(n, from, to);
        algoritmoHanoi(n - 1, inter, from, to);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (step) {
            case 1: // движение вверх
                if (y > 30) { // 30 - максимум, чтобы поднять фишку
                    y--;
                    rounds[topDisks - 1].setY(y);
                } else {
                    if (moveDIsks[numberOfSteps].getFromTowers() - 1 < moveDIsks[numberOfSteps].getToTowers() - 1) {
                        step = 2; // двигаться вправо
                    } else {
                        step = 3; // двигаться влево
                    }
                }
                break;
            case 2: // двигаться вправо
                if (x < posicionXRound(topDisks, moveDIsks[numberOfSteps].getToTowers())) { // путь к башне назначения
                    x++;
                    rounds[topDisks - 1].setX(x);
                } else {
                    step = 4;
                }
                break;
            case 3: // двигаться влево
                if (x > posicionXRound(topDisks, moveDIsks[numberOfSteps].getToTowers())) { // путь к башне назначения
                    x--;
                    rounds[topDisks - 1].setX(x);
                } else {
                    step = 4;
                }
                break;
            case 4: // двигаться вниз
                int nivel = towers[moveDIsks[numberOfSteps].getToTowers() - 1].getNumberOfDisksOnTower() + 1;
                if (y < posicionYRound(nivel)) {
                    y++;
                    rounds[topDisks - 1].setY(y);
                } else {
                    moveComplete = true;
                }
                break;
        }
        if (moveComplete) {
            step = 1;
            towers[moveDIsks[numberOfSteps].getToTowers() - 1].setNumberOfDisksOnTower(towers[moveDIsks[numberOfSteps].getToTowers() - 1].getNumberOfDisksOnTower() + 1);
            towers[moveDIsks[numberOfSteps].getFromTowers() - 1].setNumberOfDisksOnTower(towers[moveDIsks[numberOfSteps].getFromTowers() - 1].getNumberOfDisksOnTower() - 1);
            numberOfSteps++;
            if (numberOfSteps == (int) Math.pow(2, numberOfDisks)) {
                timer.stop();
                hanoi.resultComplete();
            } else {
                moveComplete = false;
                topDisks = moveDIsks[numberOfSteps].getNumberOfDisks();
                x = rounds[topDisks - 1].getX();
                y = rounds[topDisks - 1].getY();
            }
        }
        repaint();
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


    public void startAnimacion() {
        timer.restart();
    }


    public void pauseAnimacion() {
        timer.stop();
    }

}
