package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RegistroEstudianteView {

    public void mostrar() {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        stage.getIcons().add(new Image("https://www.nosequeestudiar.net/site/assets/files/4955/logo-cueavh-circular-1.180x180.png"));

        Image fondo = new Image("https://old.cue.edu.co/upload/gallery/201711031008587.jpg");
        BackgroundImage bgImage = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(bgImage));


        VBox formContainer = new VBox();
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPadding(new Insets(20));
        formContainer.setSpacing(15);
        formContainer.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 20; -fx-border-radius: 10; -fx-background-radius: 10;");


        Label title = new Label("Registro de Estudiante");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");


        Label nombreLabel = new Label("Nombre:");
        nombreLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        TextField nombreField = new TextField();
        nombreField.setPromptText("Ingrese el nombre completo");
        nombreField.setMaxWidth(300);
        nombreField.setStyle("-fx-font-size: 14px; -fx-padding: 8; -fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-radius: 5;");

        Label codigoLabel = new Label("Código Estudiante:");
        codigoLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        TextField codigoField = new TextField();
        codigoField.setPromptText("Ingrese el código del estudiante");
        codigoField.setMaxWidth(300);
        codigoField.setStyle("-fx-font-size: 14px; -fx-padding: 8; -fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-radius: 5;");

        Label correoLabel = new Label("Correo:");
        correoLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        TextField correoField = new TextField();
        correoField.setPromptText("Ingrese el correo electrónico");
        correoField.setMaxWidth(300);
        correoField.setStyle("-fx-font-size: 14px; -fx-padding: 8; -fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-radius: 5;");

        Label telefonoLabel = new Label("Teléfono:");
        telefonoLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");
        TextField telefonoField = new TextField();
        telefonoField.setPromptText("Ingrese el número de teléfono");
        telefonoField.setMaxWidth(300);
        telefonoField.setStyle("-fx-font-size: 14px; -fx-padding: 8; -fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-radius: 5;");


        Button registrarBtn = new Button("Registrar Estudiante");
        registrarBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 5; -fx-background-radius: 5;");
        registrarBtn.setOnMouseEntered(e -> registrarBtn.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 5; -fx-background-radius: 5;"));
        registrarBtn.setOnMouseExited(e -> registrarBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 5; -fx-background-radius: 5;"));
        registrarBtn.setOnAction(event -> {

            System.out.println("Estudiante registrado con éxito.");
        });


        formContainer.getChildren().addAll(
                title,
                nombreLabel, nombreField,
                codigoLabel, codigoField,
                correoLabel, correoField,
                telefonoLabel, telefonoField,
                registrarBtn
        );


        root.setCenter(formContainer);


        Scene scene = new Scene(root, 600, 400);


        stage.setScene(scene);
        stage.setTitle("Registrar Estudiante");
        stage.show();
    }
}

