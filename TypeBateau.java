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
public class TypeBateau {
    
    private String nom;
    private int nbCase;
    
    TypeBateau(){

    }
    TypeBateau(String nom, int nbCase){
        this.nom = nom;
        this.nbCase = nbCase;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbCase() {
        return nbCase;
    }

    public void setNbCase(int nbCase) {
        this.nbCase = nbCase;
    }
    
}
