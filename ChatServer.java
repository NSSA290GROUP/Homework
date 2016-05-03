/*
   @author: David Mauriello
*/

import java.net.*;
import java.io.*;
import java.util.*; 

public class ChatServer{
   Vector<Thread>clients = new Vector<Thread>(); 
 
   public static void main(String [] args){
      new ServerGUI();
      new ChatServer(); 
      
   }
   public ChatServer(){
   
      try { 
         ServerSocket ss = new ServerSocket(16789); 
         
         while(true){
            //When it accepts, saves the client's tread to clients, starts the thread
            System.out.println("Waiting for client...");  
            Socket cs =  ss.accept(); 
            Thread message = new Thread(new ServerThread(cs));
            clients.add(message);
            message.start(); 
            System.out.println("Server is running"); 
         
         }
      }catch(IOException ioe){
         ioe.printStackTrace();  
      }
   }//end ChatServer()
   
   class ServerThread implements Runnable{ 
      Socket cs;
      String message;
      
      ServerThread(Socket cs){
         this.cs =cs;
      }
      public void run(){
         try{
         
            BufferedReader br = new BufferedReader(
                              new InputStreamReader(cs.getInputStream())); 
                              
            PrintWriter pw = new PrintWriter(
                           new OutputStreamWriter(cs.getOutputStream())); 
                    
           
            while((message = br.readLine())!= null){
            
               //Sends message to clients
               for(Thread t: clients){
                  System.out.println("Server read: " + message); 
                     
                  pw.println(message.toUpperCase());
                  pw.flush();
                     
                 
               }//end for
               
               //close everything
               br.close();
               pw.close(); 
               cs.close(); 
            }//end while
         } catch(IOException ioe){
            ioe.printStackTrace(); 
         }
      } //end run()
   } //end ServerThread()
} //end ChatServer{}