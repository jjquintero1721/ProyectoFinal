package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Usuario;
import repository.UsuarioRepository;

public class LoginView {
    private final UsuarioRepository usuarioRepo = new UsuarioRepository();

    public void mostrar(Stage primaryStage, Runnable onLoginSuccess) {

        primaryStage.getIcons().add(new Image("https://www.nosequeestudiar.net/site/assets/files/4955/logo-cueavh-circular-1.180x180.png"));

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: transparent; -fx-padding: 20;");


        Label lblBienvenida = new Label("Bienvenido al Centro de Citas de Psicología\nUniversidad Alexander von Humboldt");
        lblBienvenida.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        lblBienvenida.setTextFill(Color.WHITE);
        lblBienvenida.setAlignment(Pos.CENTER);
        lblBienvenida.setStyle("-fx-text-alignment: center;");


        Label lblTitulo = new Label("Iniciar Sesión");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitulo.setTextFill(Color.WHITE);
        lblTitulo.setAlignment(Pos.CENTER);
        lblTitulo.setStyle("-fx-text-alignment: center;");


        TextField txtUsername = new TextField();
        txtUsername.setPromptText("Usuario");
        txtUsername.setMaxWidth(250);
        txtUsername.setStyle("-fx-padding: 10; -fx-font-size: 16; -fx-border-color: white; -fx-border-width: 1; -fx-border-radius: 5; " +
                "-fx-background-color: rgba(26,26,26,0.5); -fx-text-fill: white;");

        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Contraseña");
        txtPassword.setMaxWidth(250);
        txtPassword.setStyle("-fx-padding: 10; -fx-font-size: 16; -fx-border-color: white; -fx-border-width: 1; -fx-border-radius: 5; " +
                "-fx-background-color: rgba(26,26,26,0.5); -fx-text-fill: white;");


        Button btnLogin = new Button("Iniciar Sesión");
        btnLogin.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold; " +
                "-fx-padding: 10; -fx-border-color: white; -fx-border-width: 1; -fx-border-radius: 5;");

        Button btnRegister = new Button("Registrarse");
        btnRegister.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold; " +
                "-fx-padding: 10; -fx-border-color: white; -fx-border-width: 1; -fx-border-radius: 5;");


        btnLogin.setOnAction(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            Usuario usuario = usuarioRepo.obtenerPorUsername(username);

            if (usuario != null && usuario.getPassword().equals(password)) {
                onLoginSuccess.run();
            } else {
                mostrarMensaje("Usuario o contraseña incorrectos.");
            }
        });

        btnRegister.setOnAction(e -> new RegistroUsuarioView().mostrar());


        HBox botonesLayout = new HBox(10, btnLogin, btnRegister);
        botonesLayout.setAlignment(Pos.CENTER);


        layout.getChildren().addAll(lblBienvenida, lblTitulo, txtUsername, txtPassword, botonesLayout);


        StackPane root = new StackPane();
        root.setStyle("-fx-background-image: url('https://old.cue.edu.co/upload/gallery/201711031008587.jpg'); " +
                "-fx-background-size: cover; -fx-background-position: center;");


        Image logoImage = new Image("https://unihumboldt.edu.co/hs-fs/hubfs/humboldt-theme/logo-cue-ccaq.webp?width=304&height=44&name=logo-cue-ccaq.webp");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setPreserveRatio(true);
        logoImageView.setFitHeight(44);
        logoImageView.setFitWidth(304);


        AnchorPane.setTopAnchor(logoImageView, 10.0);
        AnchorPane.setLeftAnchor(logoImageView, 10.0);


        AnchorPane logoPane = new AnchorPane();
        logoPane.getChildren().add(logoImageView);


        root.getChildren().add(logoPane);


        Pane overlay = new Pane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");


        root.getChildren().add(overlay);


        root.getChildren().addAll(layout);


        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inicio de Sesión");
        primaryStage.show();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.show();
    }
}









