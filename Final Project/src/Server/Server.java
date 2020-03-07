
package Server;

public class Server {

    public static void main(String[] args) {
        // TODO code application logic here
        Splash Sp = new Splash();
        Server_Interface Sp1 = new Server_Interface();
        Sp1.setVisible(false);
        Sp.setVisible(true);
        for(int i = 0 ; i <= 100 ; i++)
        {
            try
            {
            Thread.sleep(100);
            Sp.loading.setValue(i);
            }
            catch(Exception e)
            {}  
        }
        Sp.setVisible(false);  
        Sp1.setVisible(true);
        
        
    }    
}
