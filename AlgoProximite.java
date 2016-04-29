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
public class AlgoProximite implements ILogicIA{
    @Override
    public Case jouerCoup(LinkedList<String> coups,Coup dernierCoup)
    {
        Case c=new Case();
        if(dernierCoup.isTouche()){
            do{
                c=dernierCoup.coupProche();
            }while(coups.contains(c.getX()+""+c.getY()));
        }
        else
        {
            Random r=new Random();
            int x;
            int y;
            do{
                x = r.nextInt(10);
                y = r.nextInt(10);
            }while(coups.contains(x+""+y));
            c=new Case(x,y);
        }
        return c;
    }
}
