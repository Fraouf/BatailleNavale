/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author k1fryouf
 */
public class GrillePanel extends JLayeredPane {
    
    
    public static final int WIDTH = 325;
    public static final int HEIGHT = 325;
    private static final int GRID_ROWS = 10;
    private static final int GRID_COLS = 10;
    private static final int GAP = 2;
    private static final Dimension LAYERED_PANE_SIZE = new Dimension(WIDTH, HEIGHT);
    private static final Dimension LABEL_SIZE = new Dimension(30, 30);
    private GridLayout gridlayout = new GridLayout(GRID_ROWS, GRID_COLS, GAP, GAP);
    private JPanel panelPorteBateau = new JPanel();
    public JPanel backingPanel = new JPanel();
    public JPanel[][] panelGrid = new JPanel[GRID_ROWS][GRID_COLS];
    private Point[] tableauPoints = {new Point(5,0), new Point(80, 10), new Point(150, 28), new Point(215, 32), new Point(280, 47)};
    public RotateLabel jLabelPorteAvion = new RotateLabel("/images/image_porte_avion.png",tableauPoints[0]);
    public RotateLabel jLabelCroiseur = new RotateLabel("/images/image_croiseur.png",tableauPoints[1]);
    public RotateLabel jLabelContreTorpilleur = new RotateLabel("/images/image_contre_torpilleur.png",tableauPoints[2]);
    public RotateLabel jLabelSoumarin = new RotateLabel("/images/image_soumarin.png",tableauPoints[3]);
    public RotateLabel jLabelTorpilleur = new RotateLabel("/images/image_torpilleur.png",tableauPoints[4]);
    //private JLabel jLabelTorpilleur = new RotateLabel(new ImageIcon(getClass().getClassLoader().getResource("jeu/image_torpilleur.png")),SwingConstants.CENTER);
    private boolean rotation = false;
    private BufferedImage image;
    private MyMouseAdapter myMouseAdapter;
    Point[][] pointsGrille = new Point[10][10];


    public GrillePanel() {
        
        
        backingPanel.setLayout(gridlayout);
        panelPorteBateau.setSize(342,155);
        panelPorteBateau.setLocation(5,350);
        panelPorteBateau.setLayout(null);
        panelPorteBateau.setOpaque(false);
        panelPorteBateau.add(jLabelPorteAvion);
        jLabelPorteAvion.setSize(34, 155);
        jLabelPorteAvion.setLocation(tableauPoints[0]);
        panelPorteBateau.add(jLabelCroiseur);
        jLabelCroiseur.setSize(34, 125);
        jLabelCroiseur.setLocation(tableauPoints[1]);
        panelPorteBateau.add(jLabelContreTorpilleur);
        jLabelContreTorpilleur.setSize(34, 93);
        jLabelContreTorpilleur.setLocation(tableauPoints[2]);
        panelPorteBateau.add(jLabelSoumarin);
        jLabelSoumarin.setSize(34, 93);
        jLabelSoumarin.setLocation(tableauPoints[3]);
        panelPorteBateau.add(jLabelTorpilleur);
        jLabelTorpilleur.setSize(32, 61);
        jLabelTorpilleur.setLocation(tableauPoints[4]);
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
        add(backingPanel, JLayeredPane.DEFAULT_LAYER);
        add(panelPorteBateau, JLayeredPane.DEFAULT_LAYER);
        myMouseAdapter = new MyMouseAdapter();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);
        initialiserPoints();
    }

    private class MyMouseAdapter extends MouseAdapter {
        private RotateLabel dragLabel = null;
        private int dragLabelWidthDiv2;
        private int dragLabelHeightDiv2;
        private JPanel clickedPanel = null;

        @Override
        public void mousePressed(MouseEvent me) {
            rotation = false;
            if(getComponentAt(me.getPoint()) instanceof JLabel){
                
                rotation = true;
                dragLabel = (RotateLabel) getComponentAt(me.getPoint());
                if(dragLabel.isRotatable()){
                    Point pt = dragLabel.getLocation();
                    remove(dragLabel);
                    revalidate();
                    repaint();

                    dragLabel.rotate();
                    dragLabel.setSize(dragLabel.getHeight(), dragLabel.getWidth());
                    add(dragLabel,JLayeredPane.PALETTE_LAYER);
                    if(dragLabel.rotate){
                        dragLabel.setLocation(pt.x-(dragLabel.getWidth()-dragLabel.getHeight()),pt.y);
                    }else{
                        dragLabel.setLocation(pt.x+(dragLabel.getHeight()-dragLabel.getWidth()),pt.y);
                    }

                    revalidate();
                    repaint();
                }
            }
            if(rotation == false){
                
                clickedPanel = (JPanel) getComponentAt(me.getPoint());
                Component[] components = clickedPanel.getComponents();
                if (components.length == 0) {
                    return;
                }
                // if we click on jpanel that holds a jlabel
                if (components[0] instanceof JLabel) {

                    // remove label from panel
                    int petit = Integer.MAX_VALUE;
                    for(Component c : components){
                        int dif = Math.abs((c.getLocation().x+(c.getWidth()/2))-me.getPoint().x)+Math.abs(c.getLocation().y+(c.getHeight()/2)-me.getPoint().y);
                        if(dif < petit){
                            dragLabel = (RotateLabel) c;
                            petit = dif;
                        }
                    }
                    
                    clickedPanel.remove(dragLabel);
                    clickedPanel.revalidate();
                    clickedPanel.repaint();
                    dragLabelWidthDiv2 = dragLabel.getWidth() / 2;
                    dragLabelHeightDiv2 = dragLabel.getHeight() / 2;

                    int x = me.getPoint().x - dragLabelWidthDiv2;
                    int y = me.getPoint().y - dragLabelHeightDiv2;
                    dragLabel.setLocation(x, y);
                    add(dragLabel, JLayeredPane.PALETTE_LAYER);
                    repaint();
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            if (dragLabel == null ) {
                return;
            }
            int x = me.getPoint().x - dragLabelWidthDiv2;
            int y = me.getPoint().y - dragLabelHeightDiv2;
            dragLabel.setLocation(x, y);
            dragLabel.setOpaque(true);
            
            if(dragLabel.isPlacable(backingPanel)){
                // green
                dragLabel.setBackground(new Color(0,255,0,65));
                
            }else{
                // red
                dragLabel.setBackground(new Color(255,0,0,65));
            }
            repaint();
            
        }

        @Override
        public void mouseReleased(MouseEvent me) {

            if (dragLabel == null ) {
                return;
            }
            remove(dragLabel); // remove dragLabel for drag layer of JLayeredPane
            JPanel droppedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            if (droppedPanel == null) {
                // if off the grid, return label to home

                if(dragLabel.rotate){
                   Point pt = dragLabel.getLocation();
                    dragLabel.rotate();
                    dragLabel.setSize(dragLabel.getHeight(), dragLabel.getWidth());
                    if(dragLabel.rotate){
                        dragLabel.setLocation(pt.x-(dragLabel.getWidth()-dragLabel.getHeight()),pt.y);
                    }else{
                        dragLabel.setLocation(pt.x+(dragLabel.getHeight()-dragLabel.getWidth()),pt.y);
                    }
                }
                panelPorteBateau.add(dragLabel);
                dragLabel.setLocation(dragLabel.positionInitiale);
                
                dragLabel.setOpaque(false);
                panelPorteBateau.revalidate();
            } else if(!dragLabel.isPlacable(backingPanel)){

                if(dragLabel.rotate){
                    Point pt = dragLabel.getLocation();
                    dragLabel.rotate();
                    dragLabel.setSize(dragLabel.getHeight(), dragLabel.getWidth());
                    if(dragLabel.rotate){
                        dragLabel.setLocation(pt.x-(dragLabel.getWidth()-dragLabel.getHeight()),pt.y);
                    }else{
                        dragLabel.setLocation(pt.x+(dragLabel.getHeight()-dragLabel.getWidth()),pt.y);
                    }
                }
                panelPorteBateau.add(dragLabel);
                dragLabel.setLocation(dragLabel.positionInitiale);
                dragLabel.setOpaque(false);
                panelPorteBateau.revalidate();
            }else{
                int r = -1;
                int c = -1;
                searchPanelGrid: for (int row = 0; row < panelGrid.length; row++) {
                    for (int col = 0; col < panelGrid[row].length; col++) {
                        if (panelGrid[row][col] == droppedPanel) {
                            r = row;
                            c = col;
                            break searchPanelGrid;
                        }
                    }
                }

                if (r == -1 || c == -1) {
                    // if off the grid, return label to home

                    if(dragLabel.rotate){
                        Point pt = dragLabel.getLocation();
                        dragLabel.rotate();
                        dragLabel.setSize(dragLabel.getHeight(), dragLabel.getWidth());
                        if(dragLabel.rotate){
                            dragLabel.setLocation(pt.x-(dragLabel.getWidth()-dragLabel.getHeight()),pt.y);
                        }else{
                            dragLabel.setLocation(pt.x+(dragLabel.getHeight()-dragLabel.getWidth()),pt.y);
                        }
                    }
                    panelPorteBateau.add(dragLabel);
                    dragLabel.setLocation(dragLabel.positionInitiale);
                    dragLabel.setOpaque(false);
                    panelPorteBateau.revalidate();
                } else {
                    add(dragLabel, JLayeredPane.PALETTE_LAYER);
                    dragLabel.setOpaque(false);
                    dragLabel.setLocation(trouverPoint(dragLabel.getLocation()));
                    revalidate();
                }
            }

            repaint();
            dragLabel = null;
        }
    }
    
    private Point trouverPoint(Point pnt){
        int diff = Integer.MAX_VALUE; 
        
        Point pointProche = new Point(-1,-1);
        
        for(int i = 0;i<pointsGrille.length;i++){
            
            for(int j = 0;j<pointsGrille[0].length;j++){
                if(diff>(Math.abs(pointsGrille[i][j].x-pnt.x)+Math.abs(pointsGrille[i][j].y-pnt.y))){
                    diff = (Math.abs(pointsGrille[i][j].x-pnt.x)+Math.abs(pointsGrille[i][j].y-pnt.y));
                    pointProche = pointsGrille[i][j];
                }
            }
        }
        return pointProche;
    }
    private void initialiserPoints(){

        pointsGrille[0][0] = new Point(0,0);
        for(int i = 0;i<pointsGrille.length;i++){
            for(int j = 0; j<pointsGrille[0].length;j++){
                pointsGrille[i][j] = new Point((32*j)+5,(32*i)+5);
            }
        }
    }
        public int[] retourneX(){
            int[] tab = new int[5];
            tab[0] = (jLabelPorteAvion.getLocation().x-5)/32;
            tab[1] = (jLabelCroiseur.getLocation().x-5)/32;
            tab[2] = (jLabelContreTorpilleur.getLocation().x-5)/32;
            tab[3] = (jLabelSoumarin.getLocation().x-5)/32;
            tab[4] = (jLabelTorpilleur.getLocation().x-5)/32;
            
        return tab;
        }
        public int[] retourneY(){
            int[] tab = new int[5];

            tab[0] = (jLabelPorteAvion.getLocation().y-5)/32;
            tab[1] = (jLabelCroiseur.getLocation().y-5)/32;
            tab[2] = (jLabelContreTorpilleur.getLocation().y-5)/32;
            tab[3] = (jLabelSoumarin.getLocation().y-5)/32;
            tab[4] = (jLabelTorpilleur.getLocation().y-5)/32;
            
        return tab;
        }
        
        public boolean[] retourneVH(){
            boolean[] tab = new boolean[5];

            tab[0] = jLabelPorteAvion.rotate;
            tab[1] = jLabelCroiseur.rotate;
            tab[2] = jLabelContreTorpilleur.rotate;
            tab[3] = jLabelSoumarin.rotate;
            tab[4] = jLabelTorpilleur.rotate;
            
        return tab;
            
        }

        public boolean ready(){
            return panelPorteBateau.getComponents().length == 0;
        }
        public void desactiver(){
            removeMouseListener(myMouseAdapter);
            removeMouseMotionListener(myMouseAdapter);
        }
    @Override
    public void paintComponent(Graphics g){
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
