/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.Random;

/**
 *
 * @author k1fryouf
 */
    public class Coup{
        
        private boolean touche;
        private boolean coule;
        private int nbCase;
        private Case c;
        private Sens sens;
        private Random r;

        public Coup() {
            this.touche = false;
            this.c = null;
            this.sens = null;
            this.r = new Random();
        }
        public Coup(boolean touche, Case c, Sens sens) {
            this.touche = touche;
            this.c = c;
            this.sens = sens;
            this.r = new Random();
        }

        public boolean isTouche() {
            return touche;
        }

        public void setTouche(boolean touche) {
            this.touche = touche;
        }

        public Case getCase() {
            return c;
        }

        public void setCase(Case c) {
            this.c = c;
        }

        public Sens getSens() {
            return sens;
        }

        public void setSens(Sens sens) {
            this.sens = sens;
        }

        public int getNbCase() {
            return nbCase;
        }

        public boolean isCoule() {
            return coule;
        }

        public void setCoule(boolean coule) {
            this.coule = coule;
        }
        
        

        public void setNbCase(int nbCase) {
            this.nbCase = nbCase;
        }
        
        

        public Case coupProche(){
            Case ca = new Case();
            do{
                ca.setX(r.nextInt(9));
                ca.setY(r.nextInt(9));
            }while(!validerCoup(ca));
            
            return ca;
        }
        
        public boolean validerCoup(Case ca){
            boolean dansLaGrille = ca.getX() >= 0 && ca.getY()<10;
            boolean gaucheOuDroite = (Math.abs(this.c.getX()-ca.getX())==1) && (this.c.getY() == ca.getY());
            boolean hautOuBas = (Math.abs(this.c.getY()-ca.getY())==1) && (this.c.getX() == ca.getX());
            return dansLaGrille && (gaucheOuDroite || hautOuBas);
        }
    }
