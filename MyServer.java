import java.util.Scanner;
import java.net.*;
import java.io.*;

public class MyServer extends Thread
{
   private ServerSocket serverSocket;
   
   public MyServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(1000000);
   }

   public void run()
   {
      while(true)
      {
         try
         {        Scanner s=new Scanner(System.in);       
	                                     
          System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "
                  + server.getRemoteSocketAddress());
		int n=0;
                          while(n<=1000)
                                  {

            DataInputStream in =
                  new DataInputStream(server.getInputStream());
            System.out.println("Partner Says :\n "+in.readUTF());
            DataOutputStream out =
                 new DataOutputStream(server.getOutputStream());
			System.out.println("Send msg");
			String msg=s.nextLine();
            out.writeUTF(msg+" "
              + server.getLocalSocketAddress());

n++;
}		
            server.close();

		
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   public static void main(String [] args)
   {
      int port = Integer.parseInt(args[0]);
      try
      {
         Thread t = new MyServer(port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}
  