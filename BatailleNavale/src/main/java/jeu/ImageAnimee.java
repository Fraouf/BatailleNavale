/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

/**
 *
 * @author k1fryouf
 */
public class ImageAnimee {
    private JLabel label;
    private JComponent panel;
    private int compteur;
    private String path;
    private URL url;
    private Point point;
    private Timer timer;

    ImageAnimee(JComponent panel, String path, Point point){
        this.label = new JLabel();
        this.panel = panel;
        this.path = path;
        this.point = point;
        this.compteur = 1;
        this.url = getClass().getResource(path);
        
        this.label.setIcon(new ImageIcon(url.getPath()+compteur+".png"));
        this.label.setSize(GrilleAdverse.LABEL_SIZE);
        if(point!=null){
            panel.add(label,JLayeredPane.DRAG_LAYER);
            // +3 pixelspour ajuster la place du label sur la grille
            label.setLocation(point.x+3,point.y+3);
        }else{
            panel.add(label);
        }
        this.label.setOpaque(false);
        this.panel.revalidate();
    }
    
    public void startCountDownFromNow(){
        
            timer = new Timer(250, (ActionEvent e) -> {
            compteur++;
            label.setIcon(new ImageIcon(url.getPath()+compteur+".png"));
            label.setSize(GrilleAdverse.LABEL_SIZE);
            if(point!=null){
                panel.add(label,JLayeredPane.DRAG_LAYER);
                // +3 pixelspour ajuster la place du label sur la grille
                label.setLocation(point.x+3,point.y+3);
            }else{
                panel.add(label);
            } 
            label.setOpaque(false);
            panel.revalidate();
            panel.repaint();
            timer.stop();
            
            if(compteur<7){
                timer.start();
            }else{
                label.setIcon(new ImageIcon(url.getPath()+compteur+".png"));
                label.setSize(GrilleAdverse.LABEL_SIZE);
                
                if(point!=null){
                    panel.add(label,JLayeredPane.DRAG_LAYER);
                    // +3 pixelspour ajuster la place du label sur la grille
                    label.setLocation(point.x+3,point.y+3);
                }else{
                    panel.add(label);
                }
                
                label.setOpaque(true);
                label.setBackground(new Color(0,0,0,100));
                
                panel.revalidate();
                panel.repaint();
            }
        });
            
        timer.start();
    }
}
