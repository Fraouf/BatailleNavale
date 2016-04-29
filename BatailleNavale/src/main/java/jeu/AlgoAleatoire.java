/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.*;

/**
 *
 * @author Gabriel
 */
public class AlgoAleatoire implements ILogicIA{
    
    @Override
    public Case jouerCoup(LinkedList<String> coups,Coup dernierCoup)
    {
        Random r = new Random();
        int x;
        int y;
        do{
            x = r.nextInt(10);
            y = r.nextInt(10);
        }while(coups.contains(x+""+y));
        
        return new Case(x,y);
    }
}
