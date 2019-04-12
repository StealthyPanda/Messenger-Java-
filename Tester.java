import java.net.*;
import java.util.*; import java.io.*;

class Tester
{
	public static void main(String[] args) throws Exception
	{
		Scanner scan = new Scanner(System.in);
		Socket s = null;
		System.out.print("Enter what you think the ip is: ");
		String address = scan.nextLine();
		try
		{
			s = new Socket(address, 5000);
		}
		catch (Exception e)
		{
			//lmao
		}
		System.out.println("Socket doin done");
		OutputStream i = s.getOutputStream();
		i.write(scan.nextLine().trim().getBytes());
	}
}