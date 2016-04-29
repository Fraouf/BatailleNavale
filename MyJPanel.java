/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author k1fryouf
 */
public class MyJPanel extends JPanel{
    
    private BufferedImage image;
    private URL url;
    
     public MyJPanel(String s) {
        setLayout(new GridBagLayout());
        url = getClass().getResource(s);
        try {                
           image = ImageIO.read(new File(url.getPath()));
        } catch (IOException ex) {
             // handle exception...
        }
    }

    
    @Override
    public void paintComponent(Graphics g){
    super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
}
}
