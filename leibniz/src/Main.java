
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import loadIn.LoadJsonFile;
import saveIn.SaveJsonFile;
import user.Calculate3;
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
            
            String fileName = "test2.json";
            
            
            
            try{
                
                JsonReader  file = new JsonReader( new FileReader("src/intrant/"+fileName));

		User customer = new User();
                
                customer = LoadJsonFile.load(file);
                
                Calculate3.calculateTotalPayment(customer);

                SaveJsonFile.save(customer);


            }catch (IOException e){
            
		System.out.println (e);
            }

	}
}


    
