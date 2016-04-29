/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

/**
 *
 * @author Gabriel
 */
public class JoueurFactory {
    
    public static IJoueur addJoueurHumain(String nom,Grille grille)
    {
        return new JoueurHumain(nom,grille);
    }
    
    public static IJoueur addJoueurIntelligent(String nom,Grille grille,ILogicIA ia)
    {
        return new JoueurIntelligent(nom,grille,ia);
    }
}
