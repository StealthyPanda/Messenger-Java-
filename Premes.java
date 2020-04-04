import java.util.*;
import java.io.*;
import java.net.*;

class Premes
{

	static ServerSocket server = null;
	static Socket sock = null;
	static Inet4Address ip;
	static byte[] buffer = new byte[100];
	static InputStream input = null;
	static OutputStream output = null;
	static String othern, myn;
	static Scanner scan;
	

	public static void main(String[] args) throws Exception
	{
		//init stuff
		scan = new Scanner(System.in);
		String choice;
		ip = (Inet4Address) InetAddress.getLocalHost();
		Reader readthread = new Reader();
		Writer writethread = new Writer();



		//main start stuff
		System.out.println("Welcome to Messenger By StealthyPanda! (Java edition)");
		System.out.print("Enter a nickname: "); myn = scan.nextLine().trim();
		System.out.println("\n" + myn + " joins the convo!\n");
		System.out.println("Join or New Convo?: ");
		while (true)
		{
			choice = scan.nextLine().toLowerCase().trim();
			if (choice.equals("j")||choice.equals("join")) 
			{
				Join();
				break;
								
			} else if (choice.equals("n")||choice.equals("new"))
			{

				ServerStart();
				break;

			} else 
			{
				System.out.println("Invalid choice!\n");
				System.out.println("Join or New Convo?: ");
			}
		}


		//Thread inits
		readthread.start();
		writethread.start();



		//by this point everything is done. so this while loop is for stopping the termination of main thread
		while (true)
		{
			/*
			his palms are sweaty,
			knees weak arms are heavy,
			theres vomit on his sweater already,
			moms spaghetti,
			hes nervous,
			but on the surface he looks calm and ready,
			he draws palms, but he keeps on forgetting
			what he wrote down,
			the whole crowd goes so loud,
			he opens his mouth,
			but the words wont come out.
			*/
		}
		
	}


	static void Join()
	{

		System.out.print("Enter the IP address of convo: ");
		String add = scan.nextLine().trim();
		System.out.println("Connecting to convo...");

		try
		{

			sock = new Socket(add, 500);
			input = sock.getInputStream();
			output = sock.getOutputStream();
			Send(myn);
			othern = Get();
			System.out.println("Connected to " + othern + "!");

		}
		catch(Exception e)
		{
			System.out.println("Connection Failed!");
			System.out.println(e);
		}



	}



	

	static void ServerStart()
	{

		System.out.println("\nEnter the code exactly in the other one: ");
		System.out.println(ip.getHostAddress() + "\n");

		try
		{

			server = new ServerSocket(500);
			System.out.println("\nNew Convo started! Waiting for someone to join...");
			sock = server.accept();
			input = sock.getInputStream();
			output = sock.getOutputStream();
			othern = Get();
			Send(myn);
			System.out.println("Connected to " + othern + "!");
			//Send("<connected>");

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
		buffer = new byte[100];
		try
		{
			input.read(buffer);
		}
		catch(Exception e)
		{
			//Im tired of these try statements. Same.
		}

		return GetString(buffer);

	}

	static void Send(String str)
	{
		try
		{

			output.write(str.getBytes());
		}
		catch(Exception e)
		{
			//Dont do no nothing
		}

	}

}

class Reader extends Thread
{
	String recmes;
	
	public void run()
	{
		
		while (true)
		{
			recmes = Premes.Get();

			if ( recmes.equals("<<requestforfile>>") )
			{
				while (true){

					System.out.println("Incoming file. Accept? Y/N: ");
					
					if (Writer.tosend.equals("y")||Writer.tosend.equals("yes")) 
					{
						System.out.println("yessed");
						break;
					} else if (Writer.tosend.equals("n")||Writer.tosend.equals("no"))
					{
						System.out.println("nooed");
						break;
					} else {
						System.out.println("\nInvalid input.\n");
					}

				}


			} else if (recmes.equals("<<leavingconvo>>")) 
			{

				System.out.println("\n" + Premes.othern + " has left the conversation!");
				break;
			} else {

				System.out.println("\n" + Premes.othern + ": " + recmes);

			}

		}

	}

}

class Writer extends Thread
{
	public static String tosend;

	public void run()
	{

		Scanner elscan = new Scanner(System.in);
				
		while (true)
		{

			tosend = elscan.nextLine().toLowerCase().trim();

			if (tosend.equals("<send>")){

				Premes.Send("<<requestforfile>>");

			} else if (tosend.equals("<exit>")){

				Premes.Send("<<leavingconvo>>");

			} else { 

				Premes.Send(tosend);
			
			}

		}

	}

}