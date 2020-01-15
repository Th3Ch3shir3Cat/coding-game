package Hanoi;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Round implements MouseListener, MouseMotionListener {



    private int x;
    private int y;

    private int destX;
    private int destY;
    private Image img;

    Round(int x, int y, Image img){
        this.x = x;
        this.y = y;
        this.img = img;
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

    public Image getImg(){
        return this.img;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

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
        )
            updateLocation(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getX() >= this.x && e.getX() <= this.x + img.getHeight(null)
                && e.getY() >= this.y && e.getY() <= this.y + img.getHeight(null)
        )
            updateLocation(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void updateLocation(MouseEvent e){
        setX(destX + e.getX());
        setY(destY + e.getY());
    }
}
