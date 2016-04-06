import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ServerGUI extends JFrame
{
   private JTextField jtfIP, jtfTCPport, jtfUDPport;
   
   public Server()
   {
      JPanel jpInfo = new JPanel();
      jpInfo.setLayout(new GridLayout(3,3));
      
      jpInfo.add(new JLabel("IP Address:"));
      jpInfo.add(jtfIP = new JTextField(15));
      jtfIP.setEditable(false);
      
      jpInfo.add(new JLabel("TCP/IP Port Number:"));
      jpInfo.add(jtfTCPport = new JTextField(15));
      jtfTCPport.setEditable(false);
      
      jpInfo.add(new JLabel("UDP Port Number:"));
      jpInfo.add(jtfUDPport = new JTextField(15));
      jtfUDPport.setEditable(false);
      
      add(jpInfo, BorderLayout.CENTER);
   }
   
   public static void main(String[] args)
   {
      Server startFrame = new Server();
      
      startFrame.setTitle("Server Information");
      startFrame.pack();
      startFrame.setLocation(20,20);
      startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      startFrame.setVisible(true);
   }
}