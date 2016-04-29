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
public interface ILogicIA {
    public Case jouerCoup(LinkedList<String> coups,Coup dernierCoup);
}
