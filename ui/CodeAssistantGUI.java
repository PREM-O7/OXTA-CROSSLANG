import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class CodeAssistantGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextField inputField = new TextField();
        Button sendButton = new Button("Get Suggestion");
        Label outputLabel = new Label("Suggestion will appear here...");

        sendButton.setOnAction(e -> {
            try (Socket socket = new Socket("localhost", 8080);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                out.println(inputField.getText());
                String response = in.readLine();
                outputLabel.setText(response);

            } catch (IOException ex) {
                outputLabel.setText("Error: " + ex.getMessage());
            }
        });

        VBox root = new VBox(10, inputField, sendButton, outputLabel);
        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("OXTA CrossLang");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
