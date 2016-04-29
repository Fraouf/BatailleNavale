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
public class Bateau {
    
    private TypeBateau type; 
    private int x;
    private int y;
    private Sens sens;
    private Case[] cases;
    
    
    Bateau(){
        
    }

    public Bateau(TypeBateau type, int x, int y, Sens sens) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.sens = sens;
        this.cases = new Case[this.type.getNbCase()];
    }
    
    public boolean isRotatable(){
        return (x-type.getNbCase())>=0;
    }
    
    public void genererCases(){
        if(sens == Sens.Vertical){
            for(int i = 0; i<type.getNbCase();i++){
                cases[i] = new Case(x,y+i);
            }
        }else{
            for(int i = 0; i<type.getNbCase();i++){
                cases[i] = new Case(x+i,y);
            }
        }
    }
    
    public boolean touche(Case ca){
        //System.out.println("Bateau : "+type.nom);
        for(Case c : cases){
            //System.out.println("Cases "+c.x+" "+c.y);
            if(c.getX()==ca.getX() && c.getY()==ca.getY()){
                c.setTouche(true);
                return true;
            }
        }
        return false;
    }
    
    public boolean coule(){
        boolean b = true;
        for(Case c : cases){
            if(!c.isTouche()){  
                b = false;
            }
        }
        return b;
    }

    public TypeBateau getType() {
        return type;
    }

    public void setType(TypeBateau type) {
        this.type = type;
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

    public Sens getSens() {
        return sens;
    }

    public void setSens(Sens sens) {
        this.sens = sens;
    }

    public Case[] getCases() {
        return cases;
    }

    public void setCases(Case[] cases) {
        this.cases = cases;
    }
    
    
}