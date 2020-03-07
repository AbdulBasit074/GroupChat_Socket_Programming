package Client;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;



import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;






public class Client_Interface extends javax.swing.JFrame {

    public static Socket C_Client1,I_Client;
    public InputStreamReader R;
    public static BufferedReader IN;
    public static BufferedReader  B;
    public static BufferedWriter OUT;
    public static BufferedWriter O ; 
    public OutputStreamWriter W;
    public static Random images = new Random(); 
    public static int Im = 0 ;
    public static  Thread st;
    //**********************************************************************   
    public Client_Interface() {
        initComponents();
        
        DisConnect.setEnabled(false);
        WriteMessage.setEnabled(false);
        Send.setEnabled(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter (){
        
        public void windowClosing(WindowEvent e)
        {
                      try{
                            OUT.write("DISCONNECTED.....");
                            OUT.flush();
                            C_Client1.close();
                            I_Client.close();
                            st.stop();
                             this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                             
                          
                         }
                      catch(Exception p)
                      {e.getWindow().dispose();}
        }

            private void setDefaultCloseOperation(int EXIT_ON_CLOSE) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        
        });         
        
    }

    public void Client() {
        
        try {
                
                    C_Client1 = new Socket("localhost", 3333);
                    I_Client  = new Socket("localhost", 3331);
                    if(C_Client1.isConnected()&&I_Client.isConnected())
                    {
                    st = new Thread(new message());
                    st.start();
                    Message.append("Connected To the GROUP "+"\n");
                     O = new BufferedWriter(new OutputStreamWriter(I_Client.getOutputStream()));
                    R = new InputStreamReader(C_Client1.getInputStream());
                    IN = new BufferedReader(R);
                    W = new OutputStreamWriter(C_Client1.getOutputStream());
                    OUT = new BufferedWriter(W);
                    OUT.write(UserName.getText());
                    OUT.newLine();
                    OUT.flush();
                    }
                     
        } catch (Exception e) 
               { Message.append(" Group  NOT Found ");
                        Connect.setEnabled(true);
                        DisConnect.setEnabled(false);

               }

    }
    class message implements Runnable 
    {
    
            public void run() 
            {
         while (true) {
                    try {
                                Client_Interface f = new Client_Interface();
                                String s = new String(IN.readLine());
                                  if(s.endsWith("@Send_Image@"))
                                     {
                                         Thread s_2 = new Thread(new Images());
                                         s_2.start();
                                         s=s.replace("@Send_Image@","Sending Image......");
                                     }
                                 if(s.matches("@Server_Closed@"))
                                     {
                                        s="Server Closed ";
                                        C_Client1.close();
                                        I_Client.close();
                                        DisConnect.setEnabled(false);
                                        Connect.setEnabled(true);
                                     }
                                 
                                 Message.append(s);
                                 Message.append("\n");
                       
            
                    } catch (Exception e) { }
               }
            
            
            }
            
    }
    //************************************************************************************
    //************************************************************************************
    //************************************************************************************
    //************************************************************************************
    //************************************************************************************
    //************************************************************************************
    public class Images implements Runnable
{         
   public void run()
    {  
        Im++;
         try
            {
                int a = 3 ;
                File  p2  = new File("E:/Chat_"+Im+"_"+images.nextInt()+".jpg");
                FileOutputStream f1 = new FileOutputStream(p2);
                BufferedReader r1 = new BufferedReader(new InputStreamReader(I_Client.getInputStream()));
                while(a!=-1)
                {
                           while(r1.ready())
                            {
                                    f1.write(r1.read());
                                    a=-1;
                            }
                               if(a!=3)
                                 {
                                    f1.flush();
                                    f1.close();
                                 }    
                }
                
             }
        catch(Exception e){}
   }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Message = new java.awt.TextArea();
        WriteMessage = new java.awt.TextField();
        label2 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        Send = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        UserName = new java.awt.TextField();
        Connect = new java.awt.Button();
        Clear = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        DisConnect = new java.awt.Button();
        allmessage = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 51, 255), new java.awt.Color(102, 153, 255)));
        jPanel1.setAlignmentX(0.7F);
        jPanel1.setAutoscrolls(true);
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.setLayout(null);

        Message.setBackground(new java.awt.Color(21, 25, 25));
        Message.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Message.setEditable(false);
        Message.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Message.setForeground(new java.awt.Color(204, 204, 204));
        Message.setMinimumSize(new java.awt.Dimension(100, 200));
        Message.setName(""); // NOI18N
        Message.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel1.add(Message);
        Message.setBounds(250, 170, 550, 430);

        WriteMessage.setBackground(new java.awt.Color(51, 51, 51));
        WriteMessage.setFont(new java.awt.Font("Lucida Sans Typewriter", 1, 12)); // NOI18N
        WriteMessage.setForeground(new java.awt.Color(153, 153, 153));
        WriteMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WriteMessageActionPerformed(evt);
            }
        });
        jPanel1.add(WriteMessage);
        WriteMessage.setBounds(310, 600, 430, 60);

        label2.setText("label2");
        jPanel1.add(label2);
        label2.setBounds(-52, -20, 820, 20);

        jButton1.setBackground(new java.awt.Color(41, 46, 46));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/nwwwww.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(250, 600, 60, 60);

        Send.setBackground(new java.awt.Color(41, 46, 46));
        Send.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/again.png"))); // NOI18N
        Send.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendActionPerformed(evt);
            }
        });
        jPanel1.add(Send);
        Send.setBounds(740, 600, 60, 60);

        jPanel2.setBackground(new java.awt.Color(21, 25, 25));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/photo-coming-soon1.jpg"))); // NOI18N
        image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        UserName.setBackground(new java.awt.Color(41, 46, 46));
        UserName.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        UserName.setForeground(new java.awt.Color(255, 255, 255));
        UserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserNameActionPerformed(evt);
            }
        });

        Connect.setBackground(new java.awt.Color(41, 46, 46));
        Connect.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Connect.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        Connect.setForeground(new java.awt.Color(255, 255, 255));
        Connect.setLabel("Connect");
        Connect.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ConnectFocusGained(evt);
            }
        });
        Connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectActionPerformed(evt);
            }
        });

        Clear.setBackground(new java.awt.Color(41, 46, 46));
        Clear.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        Clear.setForeground(new java.awt.Color(255, 255, 255));
        Clear.setText("Clear");
        Clear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });

        jTextField1.setBackground(new java.awt.Color(21, 25, 25));
        jTextField1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("               User Name");
        jTextField1.setAlignmentX(6.0F);
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(java.awt.Color.darkGray);
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("               Group Chat");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        DisConnect.setActionCommand("Disconnect");
        DisConnect.setBackground(new java.awt.Color(41, 46, 46));
        DisConnect.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        DisConnect.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        DisConnect.setForeground(new java.awt.Color(255, 255, 255));
        DisConnect.setLabel("Disconnect");
        DisConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Connect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DisConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(image)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(Connect, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(DisConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(411, 411, 411))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 120, 250, 540);

        allmessage.setBackground(new java.awt.Color(21, 25, 25));
        allmessage.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        allmessage.setForeground(new java.awt.Color(255, 255, 255));
        allmessage.setText("                                         All Message");
        allmessage.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        allmessage.setOpaque(true);
        allmessage.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(allmessage);
        allmessage.setBounds(250, 120, 550, 50);

        jPanel3.setBackground(new java.awt.Color(21, 25, 25));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Client/cont.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, -10, 810, 130);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void WriteMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WriteMessageActionPerformed

    }//GEN-LAST:event_WriteMessageActionPerformed

    private void DisConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisConnectActionPerformed
        Connect.setEnabled(true);
        UserName.setEnabled(true);
        DisConnect.setEnabled(false);
        WriteMessage.setEnabled(false);
        Message.append("\n : Disconnected  To Server : \n");
        try {
           
            OUT.write("DISCONNECTED.....");
            OUT.flush();
            Send.setEnabled(false);
            C_Client1.close();
            I_Client.close();
            

        } catch (Exception e) {
        }


    }//GEN-LAST:event_DisConnectActionPerformed

    private void ConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectActionPerformed
        Connect.setEnabled(false);
        UserName.setEnabled(false);
        DisConnect.setEnabled(true);
        WriteMessage.setEnabled(true);
        Send.setEnabled(true);
       Client();
    }//GEN-LAST:event_ConnectActionPerformed


    private void UserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserNameActionPerformed
         
        
        
        
    }//GEN-LAST:event_UserNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        
        try {

                      JFileChooser select = new JFileChooser();
                      select.showOpenDialog(null);
                      File  file  = select.getSelectedFile();
                      if(file.exists())
                      {
                          OUT.write("@Send_Image@");
                          OUT.newLine();
                          OUT.flush();
                          FileInputStream fin = new FileInputStream(file);
                          int d = 0 ;
                          while((d=fin.read())!=-1)
                          { O.write(d); }
                          O.flush();
                          fin.close();
                          OUT.write(" Image iS Recieved ");
                          OUT.newLine();
                            OUT.flush();
                      }
                                     
             } 
       catch (Exception e) 
            {           
                         try
                            {
                             OUT.write(" Error :: Sending Image ");
                             OUT.newLine();
                             OUT.flush();    
                             }
                        catch(Exception p){}    
            }
                   
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendActionPerformed
      
        String text = WriteMessage.getText();
        if (!text.isEmpty()) {
            try {
                OUT.write(text);
                OUT.newLine();
                OUT.flush();
            } catch (Exception e) {}
            WriteMessage.setText("");
        }
      
            
    }//GEN-LAST:event_SendActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        Message.setText("");
    }//GEN-LAST:event_ClearActionPerformed

    private void ConnectFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ConnectFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_ConnectFocusGained

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client_Interface().setVisible(true);

            }

        });

       

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Clear;
    private java.awt.Button Connect;
    private java.awt.Button DisConnect;
    private java.awt.TextArea Message;
    private javax.swing.JButton Send;
    private java.awt.TextField UserName;
    private java.awt.TextField WriteMessage;
    private javax.swing.JLabel allmessage;
    private javax.swing.JLabel image;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private java.awt.Label label2;
    // End of variables declaration//GEN-END:variables
}
