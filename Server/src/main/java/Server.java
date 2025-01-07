import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 5554;
    private static final String MAIN_SYM = "~~~# ";

    public static void main(String[] args) {
        System.out.println(MAIN_SYM + "Projektovanje Softvera 2.0 SERVER");
        System.out.println(MAIN_SYM + "Setting up...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println(MAIN_SYM +"Serving on 127.0.0.1:" + PORT);    // 2022-0554 :)

            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println(MAIN_SYM + "Accepting a new connection from: " + socket.getInetAddress());
                new ClientHandler(socket).start();
            }

        } catch (BindException e) {
            System.err.println("Address is already in use. Stop all other instances of the server" +
                " or update the config file.");
        } catch (IOException e) {
            System.err.println("Something went wrong (Probably premature disconnect): " + e.getMessage());
        }



    }
}
