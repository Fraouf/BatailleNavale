/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.*;

/**
 *
 * @author k1fryouf
 */
public class JoueurHumain implements IJoueur{
    
    private String nom;
    private Grille grille;
    private Coup dernierCoup;
    
    private LinkedList<String> coupJoue;
    protected List<Case> coups=new ArrayList();
    
    public JoueurHumain(){
    }
    
    public JoueurHumain(String nom, Grille grille){
        coupJoue = new LinkedList<String>();
        this.nom = nom;
        this.grille = grille;
        dernierCoup = new Coup();
    }
    
    @Override
    public Reponse verifierCoup(Case c){
        coups.add(c);
        dernierCoup.setCase(c);
        int x = c.getX();
        int y = c.getY();
        coupJoue.add(Integer.toString(x));
        coupJoue.add(Integer.toString(y));
        
        return grille.verifierCoup(c);
    }
    
    @Override
    public void positionnerBateaux(int[] x,int[] y, boolean[] p){
        grille.positionnerBateaux(x, y, p);
    }
    
    @Override
    public boolean verifierBateaux(){
        return grille.verifierGrille();
    }
    
    @Override
    public boolean coupDejaJoue(Case c){
        return grille.coupDejaJoue(c);
    }
    
    @Override
    public Case jouerCoup(Case c){
      return c;  
    } 

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public Grille getGrille() {
        return grille;
    }

    @Override
    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    @Override
    public Coup getDernierCoup() {
        return dernierCoup;
    }

    @Override
    public void setDernierCoup(Coup dernierCoup) {
        this.dernierCoup = dernierCoup;
    }
    
    @Override
    public LinkedList<String> getListeCoup()
    {
        return coupJoue;
    }
}
