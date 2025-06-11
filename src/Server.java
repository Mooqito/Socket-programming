import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        final String HOST = "localhost";
        final int PORT = 5447;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT,5, InetAddress.getByName(HOST));
            System.out.println("server is listening on " + HOST + ":" + PORT);

            Socket clientSocket = serverSocket.accept();
            InetAddress clientAddress = clientSocket.getInetAddress();
            System.out.println("accepted a new connection from " + clientAddress);

            InputStream input = clientSocket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));

            OutputStream output = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(output,true);

            String receivedLine;

            while ((receivedLine=in.readLine())!=null){
                out.println(receivedLine);
            }

            clientSocket.close();
            System.out.println("connection with " + clientAddress + " closed");

            serverSocket.close();
            System.out.println("server is shut down");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
