import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
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
   private JTextArea jtaMsgReceived, typingMessage;
   private JButton jbSend;
   private JPanel jpChatBox, jpPanel; 
   private JMenuItem jmiNewChat, jmiJoinChat,jmiQuit, jmiHelp, jmiClose; 
   private JLabel jlStatus; 
 
   public static void main(String[] args){
      new Chat();  
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
      
      //Joining Chat: Will have to add networking method here
      jmiJoinChat.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
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
   
      typingMessage = new JTextArea(5,30); 
      
      jpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,15)); 
   
      jpPanel.add(typingMessage); 
      jbSend = new JButton("SEND"); 
      jpPanel.add(jbSend);
      
      add(jpPanel, BorderLayout.SOUTH); 
   	
       //Adding ChatHandler ActionListener Object to the Send Button   
      //jbSend.addActionListener();
      
      //GUI Visible
      setTitle("Chat");
      pack();
      setLocationRelativeTo(null); 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }


  
   // public void run() {
   //    //try/catch maybe? 
   //    //method to like set the connection and then JOptionPane for the prompt which is below commented out
   //    //method will need 3 arguments, the third one returns an integer
   //    
   //    //Object[] options = {"TCP/IP",
   //    //              "UDP"};
   //    /*
   //       JOptionPane.showInputDialog("Enter an IP address:"), 
   //                     JOptionPane.showInputDialog("Enter your name:"), 
   //                     JOptionPane.showOptionDialog(frame, "Select a protocol to use:", "Protocol Choice", JOptionPane.YES_NO_OPTION, null, null, options, options[0]));
   //    */
   // }

  
} // end Chat class