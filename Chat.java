import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*; 
import java.net.*;
import java.util.*; 

/** 
 * @author  Abigail Spring, David Mauriello, David Player
 * The purpose of this program is to connect between client and server using TCP and UDP Protocols. 
 */
public class Chat extends JFrame
{
 //Global attribues 
   private JMenuBar menu; 
   private  JMenu fileMenu; 
   private JTextArea jtaChat;
   private JTextArea jtaMsgReceived, jtfSend;
   private JButton jbSend;
   private JPanel jpChatBox, jpPanel; 
   private JMenuItem jmiNewChat, jmiJoinChat,jmiQuit, jmiHelp, jmiClose; 
   private JLabel jlStatus; 
   private String host, userName;
   private boolean pickTCP, pickUDP; 
   private Socket cs; 
   private BufferedReader input; 
   private PrintWriter output; 
   
 
   public static void main(String[] args){
    
      
      new ClientPrompt(); 
   }
	// Chat Constructor
   public Chat()
   {
      menu = new JMenuBar();
      setJMenuBar(menu);
      
      fileMenu = new JMenu("File");
      menu.add(fileMenu);
      
      jmiNewChat = new JMenuItem("New Chat Window");
      //Opening a new Chat window  
      jmiNewChat.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  new Chat().setVisible(true); 
               }
            });
   
      jmiJoinChat = new JMenuItem("Join Chat");
      
      //Joining Chat: Will have to add networking here
      jmiJoinChat.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
             
                new ClientPrompt(); 
                  //Adding networking here
               }
            }
         );
      jmiHelp = new JMenuItem("Help");
      //A help instruction for users how to use a Chat program 
      jmiHelp.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
               
                  String help = "New Chat Window: users to opening up a new window\n" + 
                     "Join chat: join in a current chat session\n" + 
                     "Close the chat: Ask an user if User want to leave chat session \n" + 
                     "Quit: Quit the chat session";
                  JOptionPane.showMessageDialog(null, help, "Chat program" ,  JOptionPane.PLAIN_MESSAGE );   
               }
            });
   
      
      jmiClose = new JMenuItem("Close");
      //Prompting a question if user is really want to leave a current chat session
      jmiClose.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  int response = JOptionPane.showConfirmDialog(null,"Do you want to leave chat room?", "Confirm", JOptionPane.YES_NO_OPTION); 
               
                  if(response == JOptionPane.NO_OPTION){
                  
                  }
                  else if(response == JOptionPane.YES_OPTION){
                     System.exit(0); 
                  }
               }
            }
         );
       
      jmiQuit = new JMenuItem("Quit");
      //Close the chat session immediately 
      jmiQuit.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                  System.exit(0); 
               }
            });
   
      //Add menu items to main menu 
      fileMenu.add(jmiNewChat);
      fileMenu.add(jmiJoinChat);
      fileMenu.add(jmiQuit);
      fileMenu.add(jmiClose);
      fileMenu.add(jmiHelp);
      
      //Sending your comments to Upper row that receive your messages
      jtaMsgReceived = new JTextArea(5,30);
      jtaMsgReceived.setBorder(new EtchedBorder());
      jtaMsgReceived.setLineWrap( true ); 
      jtaMsgReceived.setWrapStyleWord( true );  
   
    //Chat Box Layout is 2 row and one column 
      jpChatBox = new JPanel(); 
      jpChatBox.setLayout(new GridLayout(2,1)); 
     
      jpChatBox.add(jtaMsgReceived);
    
    //ChatBox is positioned at Center  in JFrame 
      add(jtaMsgReceived, BorderLayout.CENTER);
   
      jtfSend = new JTextArea(5,30); 
      
      jpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,15)); 
   
      jpPanel.add(jtfSend); 
      jbSend = new JButton("SEND"); 
      jpPanel.add(jbSend);
      
      add(jpPanel, BorderLayout.SOUTH); 
      
      //Chat Networking for connecting with server: FYI, this chatnetworking method is incomplete 
      ChatNetworking(host, pickTCP, pickUDP);
      
      ChatHandler send = new ChatHandler(); 
       //Adding ChatHandler ActionListener Object to the Send Button   
      jbSend.addActionListener(send);
      
      //Sending and Receive the message from the server using Thread
      Thread Reading = new Thread(new ReceivesMessage()); 
      Reading.start(); 
      
      
      //GUI Visible
      setTitle("Chat");
      pack();
      setLocationRelativeTo(null); 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }// end Chat constructor
   
   //Chat Networking method 
   public void ChatNetworking(String _host, boolean pickTCP, boolean pickUDP){
   
      host = _host; 
     //I'm not sure if I understand Chat Networking method's requirement on our document because it isn't that clear as I thought
      try{
      
         cs = new Socket("hostname", 16789); 
         input = new BufferedReader(new InputStreamReader(cs.getInputStream())); 
         output = new PrintWriter(new OutputStreamWriter(cs.getOutputStream())); 
         //I'm not sure how to use TCP DataOutputSTream to create a socket. That means DataOutputStream replace PrintWriter?
          DataOutputStream out = new DataOutputStream(cs.getOutputStream()); 
         
         System.out.println("getLocalHost: "+InetAddress.getLocalHost() );
     
      
      }
      catch (UnknownHostException uhe){
         jtaMsgReceived.setText("Unable to connect the host"); 
         return;
      }
      catch (IOException ioe){
         jtaMsgReceived.setText("Communication lost"); 
         return; 
      }
   
   }//end ChatNetworking method  
   
   class ChatHandler implements ActionListener{
      public void actionPerformed(ActionEvent ae){
      
         String message = jtfSend.getText();
          
         message = userName + ":" + message; 
          
         output.println(message); 
         output.flush(); 
         jtfSend.setText(" ");
          
      }//end actionPerformed
   }//end ChatHandler class
   
   //Receives Message class thread
   class ReceivesMessage implements Runnable{
      public void run(){
         String message; 
         
         try{
            while((message = input.readLine()) != null){
               System.out.println("Receives message: "+ message); 
               jtaMsgReceived.append(message + "\n"); 
            } 
         }
         catch(IOException ioe){
            jtaMsgReceived.setText("Bad IO connection"); 
         }
      
      }//end run
   
   }//end ReceviesMessage class 
   
} //end Chat class
  
  //Prompt Class 
class ClientPrompt extends JFrame implements ActionListener{
  
    //attributes
   private JPanel jpNorth, jpCenter, jpSouth; 
   private JLabel jlUserName, jlAddress, jlQuestion; 
   private String getUserName, getAddress, host; 
   private JTextField jtfUser, jtfAddress;
   private int port; 
   private JRadioButton jrTCP, jrUDP; 
   private JButton jbSubmit; 

    //ClientPrompt constructor  
   public ClientPrompt(){
        
      jlUserName = new JLabel("UserName"); 
      jlAddress = new JLabel("IP Address"); 
      jlQuestion = new JLabel("Which protocol do you want to use?"); 
        
      jtfUser = new JTextField(20); 
      jtfAddress = new JTextField(20); 
        
      jrTCP = new JRadioButton("TCP"); 
      jrUDP = new JRadioButton("UDP"); 
        
      jbSubmit = new JButton("Submit"); 
        
        //A prompt GUI will be close after an user submitting an neccessary information to join a chat 
      jbSubmit.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent ae){
                 
                 //Chat object
                  Chat users = new Chat(); 
                
                  setVisible(false); 
                  dispose(); 
               }//end actionPerformed
            }//end ActionListener
         );//end Submit Action Listener 
       
      jpNorth = new JPanel(); 
      jpCenter = new JPanel(); 
      jpSouth = new JPanel(); 
        
        
      jpNorth.add(jlUserName); 
      jpNorth.add(jtfUser); 
        
      jpCenter.add(jlAddress); 
      jpCenter.add(jtfAddress); 
        
      jpSouth.add(jlQuestion); 
      jpSouth.add(jrTCP); 
      jpSouth.add(jrUDP); 
        
      jpSouth.add(jbSubmit); 
        
      add(jpNorth, BorderLayout.NORTH); 
      add(jpCenter, BorderLayout.CENTER); 
      add(jpSouth, BorderLayout.SOUTH); 
        
      setTitle("Client Prompt");
      pack();
      setLocationRelativeTo(null); 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);     
   } // end Client Prompt constructor 
      public void actionPerformed(ActionEvent ae){
      
      String select = ""; 
      
      if(jrTCP.isSelected())
        {
          select = jrTCP.getText(); 
        }
      else if(jrUDP.isSelected())
        {
         select = jrUDP.getText(); 
        }
     }//end actionPerformed method
  
} // end Prompt Prompt class


