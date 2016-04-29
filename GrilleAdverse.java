/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

/**
 *
 * @author k1fryouf
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author k1fryouf
 */
public class GrilleAdverse extends JLayeredPane {

    public static final int WIDTH = 325;
    public static final int HEIGHT = 325;
    private static final int GRID_ROWS = 10;
    private static final int GRID_COLS = 10;
    private static final int GAP = 2;
    private static final Dimension LAYERED_PANE_SIZE = new Dimension(WIDTH, HEIGHT);
    protected static final Dimension LABEL_SIZE = new Dimension(30, 30);
    private GridLayout gridlayout = new GridLayout(GRID_ROWS, GRID_COLS, GAP, GAP);
    protected JPanel backingPanel = new JPanel();
    protected JPanel[][] panelGrid = new JPanel[GRID_ROWS][GRID_COLS];
    private BufferedImage image;
  

    public GrilleAdverse() {
        
        backingPanel.setLayout(gridlayout);
        backingPanel.setSize(LAYERED_PANE_SIZE);
        backingPanel.setLocation(2 * GAP, 2 * GAP);
        backingPanel.setOpaque(false);
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                //panelGrid[row][col] = new JPanel(new GridBagLayout());
                if((row+col)%2==0){
                    panelGrid[row][col] = new MyJPanel("/images/image_mer_foncee.png");
                }else{
                    panelGrid[row][col] = new MyJPanel("/images/image_mer_claire.png");
                }
                
                panelGrid[row][col].setOpaque(false);
                backingPanel.add(panelGrid[row][col]);
            }
        }
        backingPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, 0, 0));
        setPreferredSize(LAYERED_PANE_SIZE);
        add(backingPanel, JLayeredPane.DEFAULT_LAYER, 0);
    }

    @Override
    public void paintComponent(Graphics g) {
        /*
         g.setColor(Color.black);
         int uniteX = getWidth()/64;
         int uniteY = getHeight()/64;
         for(int i=0; i<64; i++){
         g.drawLine(uniteX*i, 0, uniteX*i, getHeight());
         g.drawLine(0, uniteY*i, getWidth(), uniteY*i);
         */
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

}
