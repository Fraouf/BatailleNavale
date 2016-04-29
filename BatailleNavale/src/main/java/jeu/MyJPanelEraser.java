/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author k1fryouf
 */
public class MyJPanelEraser {

    private MyJPanel panel;
    private JLabel label;
    private int waitSeconds;
    private Timer timer;

    public MyJPanelEraser( int waitSeconds, MyJPanel panel, JLabel label) {
        this.panel = panel;
        this.label = label;
        this.waitSeconds = waitSeconds;
    }
    
    public void startCountDownFromNow(){
        
            timer = new Timer(waitSeconds*1000, (ActionEvent e) -> {
            panel.remove(label);
            panel.revalidate();
            panel.repaint();
            timer.stop();
        });
        timer.start();
    }
    
}
