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
public class Reponse {
   
    private boolean touche;
    private boolean coule;
    private String nom;
    private boolean gagnant;
    
    public Reponse(boolean touche, boolean coule, String nom, boolean gagnant) {
        this.touche = touche;
        this.coule = coule;
        this.nom = nom;
        this.gagnant = gagnant;
    }
    public Reponse() {
    }

    public boolean getGagnant() {
        return gagnant;
    }

    public void setGagnant(boolean gagnant) {
        this.gagnant = gagnant;
    }

    public boolean isTouche() {
        return touche;
    }

    public void setTouche(boolean touche) {
        this.touche = touche;
    }

    public boolean isCoule() {
        return coule;
    }

    public void setCoule(boolean coule) {
        this.coule = coule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
