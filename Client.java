import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** 
 * Start Client GUI class for chat program
 *
 */
public class Client extends JFrame implements ActionListener
{

   private JTextField jtfUser;
   private JTextField jtfIP;
   private JRadioButton tcp;
   private JRadioButton udp;
   private JButton jbSubmit;

	// Default Constructor
   public Client()
   {
   	// jpInfo to hold text fields and labels
      JPanel jpInfo = new JPanel();
      jpInfo.setLayout(new GridLayout(2,2));
   
      jpInfo.add(new JLabel("Username:"));
      jpInfo.add(jtfUser = new JTextField(20));
   
      jpInfo.add(new JLabel("IP Address:"));
      jpInfo.add(jtfIP = new JTextField(20));
   
   	// Panel jpProtocol to hold buttons/protocol choice 
      JPanel jpProtocol = new JPanel();
      
      ButtonGroup protocolButtons = new ButtonGroup( );
      tcp = new JRadioButton("TCP/IP");
      udp = new JRadioButton("UDP");
      protocolButtons.add(tcp);
      protocolButtons.add(udp);
      
      jpProtocol.setLayout(new BorderLayout());
      jpProtocol.add(new JLabel("What protocol do you wish to use?"), BorderLayout.NORTH);
      jpProtocol.add(tcp, BorderLayout.WEST);
      jpProtocol.add(udp, BorderLayout.EAST);
      jpProtocol.add(jbSubmit = new JButton("Submit"), BorderLayout.SOUTH);
   
   
   	// Add panels to the frame
      add(jpInfo,    BorderLayout.CENTER);
      add(jpProtocol, BorderLayout.SOUTH);
   
   	// Register listeners
      jbSubmit.addActionListener(this);
   }


	// Handle ActionEvent from buttons
   public void actionPerformed(ActionEvent e)
   {
      if(e.getActionCommand().equals("Add")) {
         //go to the main chat GUI?
      }
   
   } // end actionPerformed()

   public static void main(String[] args){
      Client startFrame = new Client();
      
      startFrame.setTitle("Chat Start");
      startFrame.pack();
      startFrame.setLocation(20,20);
      startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      startFrame.setVisible(true);
   }
} // end Client class