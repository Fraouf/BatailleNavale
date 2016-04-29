/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author k1fryouf
 */
public class MyTimer {
    
    private Timer timer;
    private double waitSeconds;
    private JFrame frame;
    private JPanel panel;
    private JLabel label;

    public MyTimer() {
        
    }

    public MyTimer(double waitSeconds, JFrame frame, JPanel panel, JLabel label) {
        this.waitSeconds = waitSeconds;
        this.frame = frame;
        this.panel = panel;
        this.label = label;
    }

    
    public void starCountDownFromNow(){
        
        timer = new Timer((int)waitSeconds*1000, (ActionEvent e) -> {
            frame.remove(label);
            frame.revalidate();
            frame.repaint();
            timer.stop();
        });
        timer.start();
    }
}
