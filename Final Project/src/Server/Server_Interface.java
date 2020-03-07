
package Server;
import java.awt.image.BufferedImage;
import java.util.*;
import java.net.*;
import java.io.*;
import static java.lang.System.exit;
import javax.imageio.*;
import javax.imageio.stream.*;



 public  class Server_Interface extends javax.swing.JFrame {
       
        
         public static  ServerSocket Chat_Socket , Image_Socket;
         static int user = 0,active=0,Im=0;
         public static Random images  = new Random();
         LinkedHashMap<String,BufferedWriter>  list = new  LinkedHashMap<String,BufferedWriter> ();
         LinkedHashMap<String,BufferedWriter>   list2 = new  LinkedHashMap<String,BufferedWriter>();
         Stack<Thread> stk = new Stack<Thread>();
         Set set = list.entrySet();
         Set set2 = list2.entrySet();
         Thread server;                  
         public Server_Interface() 
         {      
              initComponents();
              Active.setVisible(true);
         }
   
//   ///////////////////////////////////////////////////////////////////////////
//   ///////////////////////////////////////////////////////////////////////////
//   ///////////////////////////////////////////////////////////////////////////
//   ///////////////////////////////////////////////////////////////////////////                
//   ///////////////////////////////////////////////////////////////////////////
//   ///////////////////////////////////////////////////////////////////////////
  class server_active implements Runnable
    {
       public  void   run()
        {
                
                       Socket C_Client  = new Socket(); 
                       Socket I_Client  = new Socket();
         try{
                 
                   
                   while(true)
                    {
                            System.out.println("Waiting For Connection ");
                          
                            C_Client = Chat_Socket.accept();  
                            I_Client = Image_Socket.accept();
                            
                            active++;
                            System.out.println(active);
                            Thread sp  = new Thread(new List_Client(C_Client,I_Client));
                            stk.push(sp);
                            sp.start();  
                    }
           
                 }
                   
                 catch(Exception e)
                 {Message1.append(" SERVER CLOSE ");}   
           }
    
    
    }
//*****************************************************************************
//*****************************************************************************    
 //*****************************************************************************
 //*****************************************************************************
 //*****************************************************************************
    
    
   class List_Client   implements Runnable
    {
            Socket C_Client,I_Client;
            String Name_c ;
            public  List_Client(Socket c , Socket d)
               { 
                 C_Client=c;
                 I_Client=d;  
               }
         
            public  void run()
            { 
                  try{
                       BufferedReader IN;
                       BufferedWriter OUT,OUT1;
                       IN = new BufferedReader(new InputStreamReader(C_Client.getInputStream()));   
                       OUT = new BufferedWriter(new OutputStreamWriter(C_Client.getOutputStream()));
                       OUT1 = new BufferedWriter(new OutputStreamWriter(I_Client.getOutputStream()));
                       String s = IN.readLine();  
                       String s2 ;
                       if(s.isEmpty())
                         {  
                            s = "USER "+ ++user ; 
                         } 
                       Name_c = new String(s) ;
                       Online.add(Name_c);
                       TotalActive.setValue(active);
                       list.put(Name_c,OUT); 
                       list2.put(Name_c,OUT1);
                       Message1.append(Name_c+"    CONNECTED        \n");
                       while(!s.equals("DISCONNECTED....."))
                           {                                  
                                       s2 =s = IN.readLine();
                                     if(s.matches("@Send_Image@"))
                                      {   
                                          System.out.println("Enter");
                                          Thread start1 = new Thread(new image(I_Client,Name_c));
                                          start1.start();
                                          s2="Sending Image.......";
                                      }
                                      
                                      SendAll(s,Name_c);
                                      Message1.append(Name_c +  ":    ");   
                                      Message1.append(s2+"\n");  
                                   
                                      Clear.setVisible(true); 
                           }
                       Online.remove(Name_c);
                       active--;
                       TotalActive.setValue(active);
                       C_Client.close();
                       I_Client.close();
                       list.remove(Name_c);
                       list2.remove(Name_c);
                      }
                      catch(Exception e)
                      {         
                      }
        
            }
    }
// **********************************************************************
//  **********************************************************************
public class image implements Runnable
{      
    Socket I_Client ;
    String Name_c ;
    public  image(Socket s,String p)
        {
            I_Client = s ; 
            Name_c = p ;
        }
    
    public void run()
    {  
            Im++;
            int img  = images.nextInt();
         try
            {
                int a = 3 ;
                File  p  = new File("F:/GroupChat_"+img+".jpg");
                FileOutputStream f = new FileOutputStream(p);
                BufferedReader r = new BufferedReader(new InputStreamReader(I_Client.getInputStream()));
                while(a!=-1)
                    {
                           while(r.ready())
                                {
                                    f.write(r.read());
                                    a=-1;
                                }
                               if(a!=3)
                               {
                                   f.flush();
                                   f.close();
                               }    
                    }
                
              SendAll_image(Name_c,img);
            }
        catch(Exception e)
        {}   
    }
}
   
// **********************************************************************
//  **********************************************************************
//   *********************************************************************
//   *********************************************************************
public void SendAll_image(String Name_c,int img)
{
    try
    { 
        Iterator itr1 = set2.iterator();
        while(itr1.hasNext())
        {
             Map.Entry pt1 = (Map.Entry)itr1.next();
             BufferedWriter ot = (BufferedWriter) pt1.getValue();
             File  fp  = new File("F:/GroupChat_"+img+".jpg");
             FileInputStream fin = new FileInputStream(fp);
             int d = 0 ;
                       while((d=fin.read())!=-1)
                          {
                                ot.write(d);
                          }
                         ot.flush();
                         fin.close();
        }       
                        
    }
    catch(Exception h)
    {System.out.println("Error On Sending ");}
    
}
   
   


   
// **********************************************************************
//  **********************************************************************
//   *********************************************************************
//   *********************************************************************
// **********************************************************************
//  **********************************************************************
//   *********************************************************************
//   *********************************************************************
      
   
  public void SendAll(String s ,String Name_c)
     {
               
                 
                 Iterator itr = set.iterator();
                 while(itr.hasNext())  
                     {                           
                        Map.Entry pt = (Map.Entry)itr.next();
                        BufferedWriter p = (BufferedWriter) pt.getValue();
                        String st = (String) pt.getKey();
                        try
                        {
                            if(st==Name_c)
                              {p.write("You : ");}
                            else
                              {p.write(Name_c+" : ");}
                            p.write(s);
                             p.newLine();
                            p.flush();
                           
                            
                        }
                        catch(Exception e)
                        {}
                     
                      }
     }
     
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jLabel4 = new javax.swing.JLabel();
        Message = new java.awt.TextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        Online = new java.awt.List();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tog = new javax.swing.JToggleButton();
        TotalActive = new javax.swing.JProgressBar();
        Switch = new java.awt.Button();
        Clear = new java.awt.Button();
        Active = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Message1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("jLabel4");

        Message.setBackground(new java.awt.Color(41, 46, 46));
        Message.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Message.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Message.setForeground(new java.awt.Color(255, 255, 255));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 0, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.black);
        setIconImages(null);

        jPanel2.setBackground(new java.awt.Color(21, 25, 25));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setAlignmentX(0.7F);
        jPanel2.setAutoscrolls(true);
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel2.setLayout(null);

        label2.setText("label2");
        jPanel2.add(label2);
        label2.setBounds(-52, -20, 820, 20);

        Online.setBackground(new java.awt.Color(41, 46, 46));
        Online.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Online.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(Online);
        Online.setBounds(0, 510, 230, 150);

        jLabel1.setBackground(new java.awt.Color(21, 25, 25));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Server/cont.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, -10, 710, 150);

        jPanel3.setBackground(new java.awt.Color(41, 46, 46));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tog.setBackground(new java.awt.Color(41, 46, 46));
        tog.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tog.setForeground(new java.awt.Color(0, 0, 0));
        tog.setText("ACTIVE");
        tog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togActionPerformed(evt);
            }
        });

        TotalActive.setBackground(new java.awt.Color(41, 46, 46));
        TotalActive.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TotalActive.setForeground(new java.awt.Color(0, 0, 0));
        TotalActive.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Switch.setActionCommand("Switch");
        Switch.setBackground(new java.awt.Color(41, 46, 46));
        Switch.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Switch.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Switch.setForeground(new java.awt.Color(255, 255, 255));
        Switch.setLabel("Switch");
        Switch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SwitchMouseClicked(evt);
            }
        });
        Switch.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                SwitchComponentShown(evt);
            }
        });
        Switch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SwitchFocusGained(evt);
            }
        });
        Switch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SwitchActionPerformed(evt);
            }
        });

        Clear.setBackground(new java.awt.Color(41, 46, 46));
        Clear.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Clear.setForeground(new java.awt.Color(255, 255, 255));
        Clear.setLabel("Clear All");
        Clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ClearMousePressed(evt);
            }
        });
        Clear.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ClearFocusGained(evt);
            }
        });
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });
        Clear.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ClearPropertyChange(evt);
            }
        });
        Clear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ClearKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ClearKeyReleased(evt);
            }
        });

        Active.setBackground(new java.awt.Color(41, 46, 46));
        Active.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Active.setForeground(new java.awt.Color(255, 255, 255));
        Active.setText("Active");
        Active.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, Clear, org.jdesktop.beansbinding.ELProperty.create("${visible}"), Active, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, Clear, org.jdesktop.beansbinding.ELProperty.create("${visible}"), Active, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);

        Active.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                ActiveHierarchyChanged(evt);
            }
        });
        Active.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ActiveFocusLost(evt);
            }
        });
        Active.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActiveActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(41, 46, 46));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("          Group Chat");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Switch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addComponent(Active, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(TotalActive, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tog, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Active, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(Clear, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(Switch, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(TotalActive, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tog, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(215, 215, 215))
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(0, 150, 240, 560);

        Message1.setBackground(new java.awt.Color(21, 25, 25));
        Message1.setColumns(20);
        Message1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Message1.setForeground(new java.awt.Color(255, 255, 255));
        Message1.setRows(5);
        Message1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(Message1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(240, 210, 460, 450);

        jLabel3.setBackground(new java.awt.Color(21, 25, 25));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("                                      All Message");
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(jLabel3);
        jLabel3.setBounds(240, 150, 460, 60);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SwitchActionPerformed

                Iterator itr1 =  set.iterator();
                try
                {
                while(itr1.hasNext())
                   {
                        Map.Entry val  = (Map.Entry) itr1.next();
                        BufferedWriter pt  = (BufferedWriter) val.getValue();
                        pt.write("@Server_Closed@");
                        pt.newLine();
                        pt.flush();
                    }
                }
                catch(Exception w)
                    {}
             while(!stk.isEmpty())
                      {   
                         Thread t = stk.pop();
                         t.stop();
                      }
                        user = 0 ;
                       active  = 0 ; 
                       Online.removeAll();
                       list.clear();
                       list2.clear();
                       
             try {        
                     
                       Chat_Socket.close();
                       Image_Socket.close();
                       server.stop();
             } catch (Exception ex) {
                 
             }
        
      
    }//GEN-LAST:event_SwitchActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
                 Message1.setText("");
    }//GEN-LAST:event_ClearActionPerformed

    private void ClearFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ClearFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_ClearFocusGained

    private void ClearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClearMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClearMousePressed

    private void ActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActiveActionPerformed
            tog.setSelected(true);
        try{ 
                   Chat_Socket  = new ServerSocket(3333);
                   Image_Socket  = new ServerSocket(3331);
                   Thread server = new Thread(new server_active());
                   server.start();
                   }
                catch(Exception e)
                   {}
               Message1.append("Server Active ");
                
    }//GEN-LAST:event_ActiveActionPerformed

    private void ClearKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClearKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ClearKeyReleased

    private void ActiveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ActiveFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_ActiveFocusLost

    private void ClearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClearKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClearKeyPressed

    private void ClearPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ClearPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_ClearPropertyChange

    private void SwitchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SwitchFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_SwitchFocusGained

    private void ActiveHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_ActiveHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ActiveHierarchyChanged

    private void SwitchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SwitchMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SwitchMouseClicked

    private void SwitchComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_SwitchComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_SwitchComponentShown

    private void togActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_togActionPerformed

    /**
     * @param args the command line arguments
     */

    
    public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server_Interface().setVisible(true);
            }
        });
        
       
           
        
        
       
        
        
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Active;
    private java.awt.Button Clear;
    private java.awt.TextArea Message;
    private javax.swing.JTextArea Message1;
    private java.awt.List Online;
    private java.awt.Button Switch;
    private javax.swing.JProgressBar TotalActive;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label2;
    private javax.swing.JToggleButton tog;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
