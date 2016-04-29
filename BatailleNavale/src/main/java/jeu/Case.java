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
public class Case {
    
    private int x;
    private int y;
    private boolean touche;
    
    Case(){
        
    }

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        touche = false;
    }
    
    public boolean equals(Case c){
        return x==c.x && y==c.y;
    }
    
    @Override
    public String toString(){
        return x+""+y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isTouche() {
        return touche;
    }

    public void setTouche(boolean touche) {
        this.touche = touche;
    }
    
    
}
