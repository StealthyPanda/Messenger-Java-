class Reciever
{

	public static void main(String[] args) throws Exception
	{
		
		ServerSocket sock = new ServerSocket(5000);
		sock.accept();

	}

}