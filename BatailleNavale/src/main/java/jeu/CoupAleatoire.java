/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

/**
 *
 * @author k1fryouf
 */
public class CoupAleatoire {
    
    private Reponse coup2;
    private PartieControleur partieControleur;
    private Case dernierecase;
    private GrillePanel grille1;
    private PartieInterface partieInterface;
    private Timer timer;

    public CoupAleatoire(Reponse coup2, PartieControleur partieControleur, GrillePanel grille1, PartieInterface partieInterface) {
        this.coup2 = coup2;
        this.partieControleur = partieControleur;
        this.grille1 = grille1;
        this.partieInterface = partieInterface;
    }
    
    
    public void startCountDownFromNow(){

        timer = new Timer(3000, (ActionEvent e) -> {
        
        coup2 = partieControleur.verifierCoup(0, null);
        dernierecase = partieControleur.dernierCoup();

        if(coup2.isTouche()){

            new ImageAnimee(partieInterface.getGrille1(),"/images/explosion/",new Point((dernierecase.getX()*32)+5,(dernierecase.getY()*32)+5)).startCountDownFromNow();
        }else{
            partieInterface.afficherCoup(coup2, grille1.panelGrid[dernierecase.getY()][dernierecase.getX()]);
        }
        timer.stop();

        if(coup2.isTouche()){
            if(coup2.getGagnant()){
                partieInterface.afficherMessage(null,"Vous avez perdu !!!");
                partieInterface.desactiver(partieInterface.getGrille1());
                partieInterface.desactiver(partieInterface.getGrille2());
            }else{
                timer.start();
            }
        }else{
          partieInterface.activer(partieInterface.getGrille2());
        }
    });
       
    timer.start();
    }
}
