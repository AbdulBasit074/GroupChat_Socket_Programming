
package Client;

public class Client {

    public static void main(String[] args) {
                
        front Sp = new front();
        Sp.setVisible(true);
        for(int i = 0 ; i <= 100 ; i++)
        {
            try
            {
            Thread.sleep(40);
            Sp.loading1.setValue(i);
            }
            catch(Exception e)
            {}  
        }
        Sp.setVisible(false);  
        Client_Interface Sp1 = new Client_Interface();
        Sp1.setVisible(true);
        
    }
    
}
