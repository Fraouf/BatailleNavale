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
public class PartieModele {
    
    private final IJoueur joueur1;
    private final IJoueur joueur2;
    
    private final ILogicIA ia;
    
    PartieModele(){

        ia=new AlgoAleatoire();
        joueur1 = JoueurFactory.addJoueurHumain("Joueur1",new Grille(10,10));
        joueur2 = JoueurFactory.addJoueurIntelligent("Ordinateur",new Grille(10,10),ia);
        
    }
    
    public Reponse verifierCoup(int i,Case c){
        if(i==0)
        {
            return joueur1.verifierCoup(joueur2.jouerCoup(c));
        }
        else
        {
            return joueur2.verifierCoup(joueur1.jouerCoup(c));
        }
        
    }
    
    public void positionnerBateaux(int[] x,int[] y, boolean[] p){
        joueur1.positionnerBateaux(x, y, p);
        joueur2.positionnerBateaux(x, y, p);
    }
    
    public boolean verifierBateaux(){
        return joueur1.verifierBateaux() && joueur2.verifierBateaux();
    }
    
    public boolean coupDejaJoue(int i,Case c){
        if(i==0)
        {
            return joueur1.coupDejaJoue(c);
        }
        else
        {
            return joueur2.coupDejaJoue(c);
        }
        
    }
    
    public Case dernierCoup(){
        return joueur2.getDernierCoup().getCase();
    }
    
    public IJoueur getJoueur1 (){
        return joueur1;
    }
    
    public IJoueur getJoueur2 (){
        return joueur2;
    }
}
