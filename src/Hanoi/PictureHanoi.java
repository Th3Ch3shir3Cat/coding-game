package Hanoi;

import Hanoi.Snapshot.History;
import Hanoi.Snapshot.Memento;
import Hanoi.State.StartState;
import Hanoi.State.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PictureHanoi extends JPanel implements ActionListener {

    private MainHanoi hanoi; //Панель родитель

    private Round[] rounds; //Диски

    private int numberOfDisks; //Кол-во дисков

    private Towers[] towers;

    private Timer timer;

    private State state;
    private History history;
    private int step;

    private int numberSteps;
    private int numerusInput;

    private int topDisks;
    private int x,y;

    private MoveDisks[] moves;

    private MoveDisks[] moveDIsks;
    private int numberOfSteps;
    private boolean moveComplete;

    private static final int VELOCIDAD = 1;

    //Конструктор
    public PictureHanoi(MainHanoi hanoi, int numberOfDisks){

        this.hanoi = hanoi;
        this.numberOfDisks = numberOfDisks;
        this.numerusInput = 0;
        rounds = new Round[numberOfDisks];
        towers = new Towers[3];
        moves = new MoveDisks[10];
        history = new History();
        this.state = new StartState(this);
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
        initializeTowers();
    }

    public void initializeTowers(){

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
    }

    public void goTowers(Round round, int num){
        int indexTower = num;
        for(int i = 0; i < 3; i++) {
            if(towers[i].checkDisksInTower(round.getX(),round.getX() + round.getImg().getWidth(null)))
                indexTower = i;
        }
        if(indexTower != num) {
            if (towers[indexTower].getNumberOfDisksOnTower() != 0 && towers[indexTower].getLastRound().getNumber() > round.getNumber()) {
                execute(new MoveDisks(round.getNumber(),num,indexTower));
                towers[indexTower].addRound(round);
                towers[num].removeRound();
                towers[indexTower].setNumberOfDisksOnTower(towers[indexTower].getNumberOfDisksOnTower() + 1);
                int position = (towers[indexTower].getX2() - towers[indexTower].getX1() - round.getImg().getWidth(null)) / 2;
                round.setX(towers[indexTower].getX1() + position);
                round.setY(posicionYRound(towers[indexTower].getNumberOfDisksOnTower()));
                round.setStartX(round.getX());
                round.setStartY(round.getY());
                round.setNumberTower(indexTower);

            } else if (towers[indexTower].getNumberOfDisksOnTower() == 0) {
                execute(new MoveDisks(round.getNumber(),num,indexTower));
                towers[indexTower].addRound(round);
                towers[num].removeRound();
                towers[indexTower].setNumberOfDisksOnTower(towers[indexTower].getNumberOfDisksOnTower() + 1);
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
        if(towers[2].getNumberOfDisksOnTower() == this.numberOfDisks){
            hanoi.resultComplete();
            JOptionPane.showMessageDialog(this,
                    "Умница, а теперь попробуй написать код!!!");
        }
    }

    public boolean addMove(int num1, int num2){
        if(!checkNumberOfDisks(num1))
            return false;
        if (!checkMoveBetweenTowers(towers[num1 - 1].getLastRound(), num2))
            return false;
        moveDIsks[numerusInput++] = new MoveDisks(towers[num1 - 1].getLastRound().getNumber(), num1, num2);
        towers[num2 - 1].addRound(towers[num1 - 1].getLastRound());
        towers[num2 - 1].setNumberOfDisksOnTower(towers[num2 - 1].getNumberOfDisksOnTower() + 1);
        towers[num1 - 1].removeRound();
        return true;
    }

    private boolean checkNumberOfDisks(int num){
        if(towers[num-1].getNumberOfDisksOnTower() != 0)
            return true;
        return false;
    }

    private boolean checkMoveBetweenTowers(Round round, int num2){
        if(towers[num2 - 1].getNumberOfDisksOnTower() != 0)
            if(round.getNumber() > towers[num2 - 1].getLastRound().getNumber())
                return false;
        return true;
    }

    public void removeAllInfoAboutText(){
        this.numerusInput = 0;
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
            towers[moveDIsks[numberOfSteps].getToTowers() - 1].addRound(rounds[moveDIsks[numberOfSteps].getNumberOfDisks() - 1]);
            towers[moveDIsks[numberOfSteps].getFromTowers() - 1].removeRound();
            rounds[moveDIsks[numberOfSteps].getNumberOfDisks()-1].setX(x);
            rounds[moveDIsks[numberOfSteps].getNumberOfDisks()-1].setY(y);
            rounds[moveDIsks[numberOfSteps].getNumberOfDisks()-1].setStartX(x);
            rounds[moveDIsks[numberOfSteps].getNumberOfDisks()-1].setStartY(y);
            numberOfSteps++;
            if(numberOfSteps == numerusInput && numerusInput != 0){
                timer.stop();
                if(hanoi.getNumtry() == 1 && towers[2].getNumberOfDisksOnTower() == this.numberOfDisks){
                    JOptionPane.showMessageDialog(this,
                            "Ого, да гений, раз выполнил с 1го раза");
                }
                hanoi.resultComplete();
            }
            else
            if (numberOfSteps >= (int) Math.pow(2, numberOfDisks)) {
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

    public void setNumberSteps(int num){
        this.numberOfSteps = num;
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

    public void changeState(State state){
        this.state = state;
    }

    public State getState(){
        return this.state;
    }

    public MoveDisks[] getBackup(){
        return this.moves;
    }

    public void undo(){
        if(history.undo()){
            System.out.println(history.getLastMove().getNumberOfDisks());

            towers[history.getLastMove().getFromTowers()].setNumberOfDisksOnTower(towers[history.getLastMove().getFromTowers()].getNumberOfDisksOnTower() + 1);
            towers[history.getLastMove().getFromTowers()].addRound(rounds[history.getLastMove().getNumberOfDisks() - 1]);
            towers[history.getLastMove().getToTowers()].removeRound();

            int position = (towers[history.getLastMove().getFromTowers()].getX2() - towers[history.getLastMove().getFromTowers()].getX1() - rounds[history.getLastMove().getNumberOfDisks() - 1].getImg().getWidth(null)) / 2;
            rounds[history.getLastMove().getNumberOfDisks() - 1].setX(towers[history.getLastMove().getFromTowers()].getX1() + position);
            rounds[history.getLastMove().getNumberOfDisks() - 1].setY(posicionYRound(towers[history.getLastMove().getFromTowers()].getNumberOfDisksOnTower()));
            rounds[history.getLastMove().getNumberOfDisks() - 1].setStartX(rounds[history.getLastMove().getNumberOfDisks() - 1].getX());
            rounds[history.getLastMove().getNumberOfDisks() - 1].setStartY(rounds[history.getLastMove().getNumberOfDisks() - 1].getY());
            rounds[history.getLastMove().getNumberOfDisks() - 1].setNumberTower(history.getLastMove().getFromTowers());
            repaint();
        }
    }

    public void redo(){
        if(history.redo()){
            repaint();
        }
    }

    public void restore(MoveDisks[] moves){
        this.moves = moves;
    }

    public void execute(MoveDisks move){
        history.push(move, new Memento(this));
    }

}
