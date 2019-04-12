import java.util.*;
import java.io.*;
import java.net.*;

class Premes
{

	static ServerSocket server = null;
	static Socket sock = null;
	static InetAddress ip;
	static byte[] buffer = new byte[100];
	static InputStream input = null;
	static OutputStream output = null;
	static String othern, myn;
	

	public static void main(String[] args) throws Exception
	{
		//init stuff
		Scanner scan = new Scanner(System.in);
		String choice;
		ip = InetAddress.getLocalHost();



		//main stuff
		System.out.println("Welcome to Messenger By StealthyPanda! (Java edition)");
		System.out.print("Enter a nickname: "); myn = scan.nextLine().trim();
		System.out.println("\n" + myn + " joins the convo!\n");
		System.out.println("Join or New Convo?: ");
		while (true)
		{
			choice = scan.nextLine().toLowerCase();
			if (choice.equals("j")||choice.equals("join")) 
			{

				break;
								
			} else if (choice.equals("n")||choice.equals("new"))
			{

				
				ServerStart();
				break;

			} else 
			{
				System.out.println("Invalid choice!");
				System.out.println("Join or New Convo?: ");
			}
		}


		
	}

	

	static void ServerStart()
	{

		System.out.println("\nEnter the code exactly in the other one: ");
		System.out.println(ip.getHostAddress() + "\n");

		try
		{

			server = new ServerSocket(5000);
			System.out.println("\nNew Convo started! Waiting for someone to join...");
			sock = server.accept();
			input = sock.getInputStream();
			output = sock.getOutputStream();
			othern = Get();
			System.out.println("Connected to " + othern + "!");

		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	static String GetString(byte[] bytes)
	{
		String retter;
		int till = 0;

		for (int i = 0; i < bytes.length; i++) 
		{
			
			if (bytes[i] == 0) 
			{
				till = i;
				break;	
			}

		}

		retter = new String(bytes, 0, till);

		return retter; 
		
	}

	static String Get()
	{

		input.read(buffer);
		return GetString(buffer);

	}

	static void Send(String str)
	{
		
	}

}

class AThread extends Thread
{

}