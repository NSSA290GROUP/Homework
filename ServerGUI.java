import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class ServerGUI extends JFrame
{
   private JTextField jtfIP, jtfTCPport, jtfUDPport;
   
   public ServerGUI()
   {
      JPanel jpInfo = new JPanel();
      jpInfo.setLayout(new GridLayout(3,3));
      
      try{
         jpInfo.add(new JLabel("IP Address: "+ InetAddress.getLocalHost()));
         jpInfo.add(jtfIP = new JTextField(15));
         jtfIP.setEditable(false);
      } catch(UnknownHostException uhe){
         System.out.println("Server can't get its own IP address, gg.");
      }
      
      jpInfo.add(new JLabel("TCP/IP Port Number: 16789"));
      jpInfo.add(jtfTCPport = new JTextField(15));
      jtfTCPport.setEditable(false);
      
      jpInfo.add(new JLabel("UDP Port Number: 16789"));
      jpInfo.add(jtfUDPport = new JTextField(15));
      jtfUDPport.setEditable(false);
      
      add(jpInfo, BorderLayout.CENTER);
      
      setTitle("Server Information");
      pack();
      setLocation(20,20);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }
   
   public static void main(String[] args)
   {
      new ServerGUI();
   }
}