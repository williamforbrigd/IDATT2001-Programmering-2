package views.icons;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Test extends Application{

    public static void main(String[]args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vBox = new VBox();

        Button okButton = new Button("OK");
        Label statusBarLabel = new Label();

        vBox.getChildren().addAll(okButton, statusBarLabel);


        okButton.setOnAction(e -> {
            statusBarLabel.setText("Status: OK button clicked...");
            System.out.println(statusBarLabel.getText());
        });


        Scene scene = new Scene(vBox, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
