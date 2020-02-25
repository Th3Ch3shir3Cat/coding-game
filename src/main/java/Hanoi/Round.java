package Hanoi;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Round implements MouseListener, MouseMotionListener {

    private int x;
    private int y;

    private int startX;
    private int startY;

    private int destX;
    private int destY;

    private int number;
    private int numberTower;

    private Image img;
    private PictureHanoi picture;

    Round(int x, int y, Image img, PictureHanoi picture, int number, int numberTower){
        this.x = x;
        this.y = y;
        this.img = img;
        this.startX = x;
        this.startY = y;
        this.number = number;
        this.numberTower = numberTower;
        this.picture = picture;
    }

    public int getNumberTower(){
        return this.numberTower;
    }
    public void setNumberTower(int num){
        this.numberTower = num;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int xx){
        this.x = xx;
    }

    public void setY(int yy){
        this.y = yy;
    }

    public void setStartX(int xx){
        this.startX = xx;
    }
    public void setStartY(int yy){
        this.startY = yy;
    }

    public int getStartX(){
        return this.startX;
    }

    public int getStartY(){
        return this.startY;
    }

    public int getNumber(){
        return this.number;
    }

    public Image getImg(){
        return this.img;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //not necessary
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getX() >= this.x && e.getX() <= this.x + img.getHeight(null)
            && e.getY() >= this.y && e.getY() <= this.y + img.getHeight(null)
        ) {
            destX = this.getX() - e.getX();
            destY = this.getY() - e.getY();
            updateLocation(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getX() >= this.x && e.getX() <= this.x + img.getHeight(null)
                && e.getY() >= this.y && e.getY() <= this.y + img.getHeight(null)
        ) {
            updateLocation(e);
            picture.goTowers(this, numberTower);
            picture.repaint();

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //not necessary
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //not necessary
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getX() >= this.x && e.getX() <= this.x + img.getHeight(null)
                && e.getY() >= this.y && e.getY() <= this.y + img.getHeight(null)
        ) {
            updateLocation(e);
            picture.repaint();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //not necessary
    }

    public void updateLocation(MouseEvent e){
        setX(destX + e.getX());
        setY(destY + e.getY());
    }
}
