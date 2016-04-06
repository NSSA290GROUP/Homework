import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** 
 * Start Chat
 *
 */
public class Chat extends JFrame implements ActionListener
{

   private JTextArea jtaChat;
   private JTextArea jtaMsg;
   private JButton jbSend;

	// Default Constructor
   public Chat()
   {
      JMenuBar menu = new JMenuBar();
      setJMenuBar(menu);
      
      JMenu fileMenu = new JMenu("File");
      menu.add(fileMenu);
      
      JMenuItem jmiNewChat = new JMenuItem("New Chat Window");
      JMenuItem jmiJoinChat = new JMenuItem("Join Chat");
      JMenuItem jmiQuit = new JMenuItem("Quit");
      JMenuItem jmiHelp = new JMenuItem("Help");
      JMenuItem jmiClose = new JMenuItem("Close");
      
      fileMenu.add(jmiNewChat);
      fileMenu.add(jmiJoinChat);
      fileMenu.add(jmiQuit);
      fileMenu.add(jmiHelp);
      fileMenu.add(jmiClose);
      
   	// jpInfo to hold text fields and labels
      JPanel jpChat = new JPanel();
      jpChat.setLayout(new BorderLayout());
      jpChat.add(jtaChat = new JTextArea(25,25), BorderLayout.CENTER);
      
      JPanel jpMsg = new JPanel();
      jpMsg.setLayout(new FlowLayout());
      jpMsg.add(jtaMsg = new JTextArea(50,50));
      jpMsg.add(jbSend = new JButton("Send"));
   
     	// Add panels to the frame
      add(jpChat, BorderLayout.CENTER);
      add(jpMsg, BorderLayout.SOUTH);
      
   	// Register listeners
      jbSend.addActionListener(this);
   }


	// Handle ActionEvent from buttons
   public void actionPerformed(ActionEvent e)
   {
      if(e.getActionCommand().equals("Add")) {
         //add message to jtaChat
      }
   
   } // end actionPerformed()
   
   public void run() {
      //try/catch maybe? 
      //method to like set the connection and then JOptionPane for the prompt which is below commented out
      //method will need 3 arguments, the third one returns an integer
      
      //Object[] options = {"TCP/IP",
      //              "UDP"};
      /*
         JOptionPane.showInputDialog("Enter an IP address:"), 
                       JOptionPane.showInputDialog("Enter your name:"), 
                       JOptionPane.showOptionDialog(frame, "Select a protocol to use:", "Protocol Choice", JOptionPane.YES_NO_OPTION, null, null, options, options[0]));
      */
   }

   public static void main(String[] args){
      Chat startFrame = new Chat();
      
      startFrame.setTitle("Chat");
      startFrame.pack();
      startFrame.setLocation(20,20);
      startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      startFrame.setVisible(true);
   }
} // end Client class