import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** 
 * Start Client GUI class for chat program
 *
 */
public class ClientGUI extends JFrame implements ActionListener
{

   private JTextArea jtaChat;
   private JTextArea jtaMsg;
   private JButton jbSend;

	// Default Constructor
   public Client()
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
      jpChat.add(jtaChat = new JTextArea(100,75), BorderLayout.CENTER);
      
      JPanel jpMsg = new JPanel();
      jpMsg.setLayout(new FlowLayout());
      jpMsg.add(jtaMsg = new JTextArea(100,25));
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

   public static void main(String[] args){
      Client startFrame = new Client();
      
      startFrame.setTitle("Chat");
      startFrame.pack();
      startFrame.setLocation(20,20);
      startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      startFrame.setVisible(true);
   }
} // end Client class