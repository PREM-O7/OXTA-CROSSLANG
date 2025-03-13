import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8080)) {
            System.out.println("Server is running on port 8080...");
            while (true) {
                Socket client = server.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String request = input.readLine();
                
                // Call C++ AI engine
                ProcessBuilder pb = new ProcessBuilder("./core/ai_engine");
                pb.start().waitFor();
                
                // Read suggestion from file
                String suggestion = new BufferedReader(new FileReader("suggestion.txt")).readLine();
                
                // Send response to client
                PrintWriter output = new PrintWriter(client.getOutputStream(), true);
                output.println("Suggested Code: " + suggestion);
                
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
