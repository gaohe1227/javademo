package 网络编程;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class HomePageReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket=null;
		try {
    		  socket= new Socket("www.yahoo/com", 80);
			OutputStream out=socket.getOutputStream();
			String str="GRT/HTTP/1.0\n\n";
			out.write(str.getBytes());
			InputStream in=socket.getInputStream();
			Scanner scanner=new Scanner(in);
			System.err.println("回收:----------------");
			while (scanner.hasNextLine()) {
			 String line=scanner.nextLine();
			 System.out.println(line);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
