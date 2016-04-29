/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.io.File;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author k1fryouf
 */
public class PartieControleur {
    
    PartieModele partieModele;
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    Document doc;
    Element rootElement;

    PartieControleur() throws ParserConfigurationException{
        partieModele = new PartieModele();
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.newDocument();
        rootElement = doc.createElement("Partie");
        doc.appendChild(rootElement);
    }
    
    public Reponse verifierCoup(int i, Case c){
        return partieModele.verifierCoup(i, c); 
    }

    
    public boolean verifierBateaux(){
        return partieModele.verifierBateaux();
    }
    
    public void positionnerBateaux(int[] x, int[] y, boolean[] p){
        partieModele.positionnerBateaux(x, y, p);
    }
    
    public boolean coupDejaJoue(int i,Case c){
        return partieModele.coupDejaJoue(i,c);
    }
    
    public Case dernierCoup(){
        return partieModele.dernierCoup();
    }
    
    public void sauvegarderPartie() throws TransformerConfigurationException, TransformerException, ParserConfigurationException{
   
        LinkedList<Case> listeC = partieModele.getJoueur1().getGrille().getCasesJouees();
        ListIterator<Case> listIteratorC = listeC.listIterator();
        
        LinkedList<Case> listeC2 = partieModele.getJoueur2().getGrille().getCasesJouees();
        ListIterator<Case> listIteratorC2 = listeC2.listIterator();
        
        Bateau[] listeB = partieModele.getJoueur1().getGrille().getBateau();
        Bateau[] listeB2 = partieModele.getJoueur2().getGrille().getBateau();

         
        Ecriture("Joueur1",listIteratorC,listeB);
        Ecriture("Joueur2",listIteratorC2,listeB2);

        TransformerFactory transformerFactory =
        TransformerFactory.newInstance();
        Transformer transformer =
        transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result =
        new StreamResult(new File("partie.xml"));
        transformer.transform(source, result);

        StreamResult consoleResult =
        new StreamResult(System.out);
        transformer.transform(source, consoleResult);
        
    }
    
    private void Ecriture(String joueur,ListIterator<Case>listIterator,Bateau[] listeB)
    {
        Element supercar = doc.createElement(joueur);
        rootElement.appendChild(supercar);
        // setting attribute to element
        Attr attr = doc.createAttribute("jeu");
         
        attr.setValue("Bataille Navale");
        supercar.setAttributeNode(attr);

        generateGrille(joueur,listIterator,supercar);
        generateBateau(joueur,listeB, supercar);
      
    }
    private void generateGrille(String joueur, ListIterator<Case> iterateur, Element supercar)
    {
        
        while (iterateur.hasNext()) {
            Case temp = iterateur.next();
            addElement(joueur,iterateur,supercar,"x",Integer.toString(temp.getX()));
            addElement(joueur,iterateur,supercar,"y",Integer.toString(temp.getY()));
            addElement(joueur,iterateur,supercar,"touchee",Boolean.toString(temp.isTouche()));
        }
    }
    
    private void addElement(String joueur, ListIterator<Case> iterateur,Element supercar, String type,String entree)
    {
        Element carname = doc.createElement(joueur);
            Attr attrType = doc.createAttribute("grille");
            attrType.setValue(type);
            carname.setAttributeNode(attrType);
            carname.appendChild(doc.createTextNode(entree));
            supercar.appendChild(carname);
    }
    
    private void generateBateau(String joueur,Bateau[] listeB,Element supercar)
    {
        for(int i = 0; i< listeB.length; i++)
         {
             Bateau temp = listeB[i];
             addElementB(joueur,"x",Integer.toString(temp.getX()),supercar);
             addElementB(joueur,"y",Integer.toString(temp.getY()),supercar);
             addElementB(joueur,"longueur",Integer.toString(temp.getCases().length),supercar);
             addElementB(joueur,"orientation",temp.getSens().name(),supercar);
        }
    }
    
    private void addElementB(String joueur,String type,String valeur,Element element)
    {
        Element carname2 = doc.createElement(joueur);
        Attr attrType2 = doc.createAttribute("bateau");
        attrType2.setValue(type);
        carname2.setAttributeNode(attrType2);
        carname2.appendChild(
        doc.createTextNode(valeur));
        element.appendChild(carname2);
    }
}
