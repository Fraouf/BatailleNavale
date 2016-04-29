/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author k1fryouf
 */
public class Grille {

    int longeur;
    int Largeur;
    Bateau[] bateaux;
    LinkedList<String> casesJouees;
    LinkedList<Case> casesJoueesI;
    

    public Grille(int longeur, int Largeur) {
        this.longeur = longeur;
        this.Largeur = Largeur;
        this.bateaux = new Bateau[5];
        this.casesJouees = new LinkedList<>();
        this.casesJoueesI = new LinkedList<Case>();
        initialiserBateaux();
    }
    
    public boolean verifierGrille(){
        
        for(int i=0;i<bateaux.length;i++){
            for(int j=i+1;j<bateaux.length;j++){
                for (Case c : bateaux[i].getCases()) {
                    if (contient(bateaux[j].getCases(), c) || c.getX()<0 || c.getY()>9) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean contient (Case[] cases, Case c){
        for(Case uneCase : cases){
            if(uneCase.equals(c)){
                return true;
            }
        }
        return false;
    }

    public void positionnerBateaux(int[] x, int[] y, boolean[] p) {
        int i = 0;

        for (Bateau b : bateaux) {

            b.setX(x[i]);
            b.setY(y[i]);
            i++;
        }
        
        tournerBateaux(p);
    }

    public void positionnerBateauxAleatoire() {
        Random r = new Random();
        do{
            for (Bateau b : bateaux) {

                b.setX(r.nextInt(9));
                b.setY(r.nextInt(9));
            }
            tournerBateauxAleatoire();
        }while(!verifierGrille());
    }

    public void tournerBateaux(boolean[] p) {
        int i = 0;

        for (Bateau b : bateaux) {

            if (p[i]) {
                b.setSens(Sens.Horizontal);
            }
            b.genererCases();
            i++;
        }
    }

    public void tournerBateauxAleatoire() {
        Random r = new Random();
        for (Bateau b : bateaux) {

            if (!r.nextBoolean() && b.isRotatable()) {
                b.setSens(Sens.Horizontal);
            }
            b.genererCases();
        }
    }

    public Reponse verifierCoup(Case c) {

        casesJouees.add(c.toString());
        casesJoueesI.add(c);
        for (Bateau bateau : bateaux) {
            if (bateau.touche(c)) {
                return new Reponse(true, bateau.coule(), bateau.getType().getNom(), gagne());
            }
        }
        return new Reponse(false, false, "",false);
    }
    
    public boolean gagne(){
        for(Bateau b : bateaux){
            if(!b.coule()){
                return false;
            }
        }
        return true;
    }
    
    public boolean coupDejaJoue(Case c){
        return casesJouees.contains(c.toString());
    }
    
    public  LinkedList<Case> getCasesJouees(){
        return casesJoueesI;
    }
    
    public Bateau[] getBateau()
    {
        return bateaux;
    }

    private void initialiserBateaux() {
        bateaux[0] = new Bateau(new TypeBateau("porteAvion", 5), 0, 0, Sens.Vertical);
        bateaux[1] = new Bateau(new TypeBateau("croiseur", 4), 0, 0, Sens.Vertical);
        bateaux[2] = new Bateau(new TypeBateau("contreTorpilleur", 3), 0, 0, Sens.Vertical);
        bateaux[3] = new Bateau(new TypeBateau("soumarin", 3), 0, 0, Sens.Vertical);
        bateaux[4] = new Bateau(new TypeBateau("torpilleur", 2), 0, 0, Sens.Vertical);
    }

}
