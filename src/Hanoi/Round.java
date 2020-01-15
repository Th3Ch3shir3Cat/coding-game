package Hanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Round extends JPanel implements MouseListener, MouseMotionListener {

    JLabel label;
    int prevX;
    int prevY;

    Round(JLabel label){
        this.label = label;
        addMouseListener(this);
        addMouseMotionListener(this);
        add(label);
        setBackground(Color.black);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        prevX = label.getX() - e.getX();
        prevY = label.getY() - e.getY();

        Component c = e.getComponent();
        if (c instanceof JLabel)
            updateLocation(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Component c = e.getComponent();
        if(c instanceof JLabel)
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
        updateLocation(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void updateLocation(MouseEvent e){
        label.setLocation(prevX + e.getX(), prevY + e.getY());
        repaint();
    }
}
