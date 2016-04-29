/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.LinkedList;

/**
 *
 * @author Gabriel
 */
public interface IJoueur {
    
    public Reponse verifierCoup(Case c);
    
    public void positionnerBateaux(int[] x,int[] y, boolean[] p);
    
    public boolean verifierBateaux();
    
    public boolean coupDejaJoue(Case c);
    
    public Case jouerCoup(Case c);
    
    public String getNom();

    public void setNom(String nom);

    public Grille getGrille();

    public void setGrille(Grille grille);

    public Coup getDernierCoup();

    public void setDernierCoup(Coup dernierCoup);
    
    public LinkedList<String> getListeCoup();
}
