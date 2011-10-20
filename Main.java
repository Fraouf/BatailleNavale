
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import loadIn.LoadJsonFile;
import saveIn.SaveJsonFile;
import user.Calculate;
import user.User;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author k1fryouf
 */
public class Main {
    

    public static void main (String[]args){
            
            try{
                
                for(int i=0;i<args.length;i++){
                    
                    //Lecture des fichiers source .json donn� par les arguments du main
                    JsonReader  file = new JsonReader( new FileReader(args[i]));
                    
                    //r�cup�ration du fichier json de la classe JsonReader
                    User customer  = LoadJsonFile.load(file);
                    
                    //calcul� les payements de l'utilisateurs
                    Calculate.calculateTotalPayment(customer);
                    
                    //sauvegarde des fichi�s dans le dossier "extrant"
                    SaveJsonFile.save(customer);
                }


            }catch (Exception e){
            
                e.printStackTrace();
                //System.out.println (customer.getDescription());
                //System.out.println (customer.getAmount());
                //System.out.println (customer.getDuration());
                //System.out.println (customer.getPaymentFrequency());
                //System.out.println (customer.getInterestRates());
                //System.out.println (customer.getFrequencyComposition());
            }

    }
}


    

