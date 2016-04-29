/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author k1fryouf
 */
public class RotateLabel extends JLabel {
    
    BufferedImage image;
    URL url;
    Point positionInitiale; 
    boolean rotate = false;
    int h,w;

    RotateLabel(String s, Point p){
        positionInitiale = p;
        url = url = getClass().getResource(s);
        try {                
          image = ImageIO.read(new File(url.getPath()));
       } catch (IOException ex) {
            // handle exception...
           System.out.println(ex.getMessage());
       }
    }

    RotateLabel() {
        
    }
    public boolean isRotatable(){
        return (getLocation().x - (getHeight()-getWidth()) >= 0);
    }
    public boolean isPlacable(JPanel jp){
        return (getLocation().x >= jp.getLocation().x &&
                getLocation().y >= jp.getLocation().y &&
                getLocation().x + getWidth() < jp.getWidth()+10 &&
                getLocation().y + getHeight() < jp.getHeight()+10);
    }
    public void rotate(){
       
        rotate = !rotate;
        
    }
    
    @Override
    public void setLocation(int x, int y){
        super.setLocation(x,y);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(rotate){
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(Math.toRadians(90));
            g.drawImage(image, 0, -getWidth(), null);
        }else{
            g.drawImage(image, 0, 0, null);
        }
    
    
}
    
}
