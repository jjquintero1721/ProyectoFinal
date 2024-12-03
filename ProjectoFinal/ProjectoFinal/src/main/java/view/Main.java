package view;

import controller.GestorCitas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;  // Usar HBox en lugar de VBox
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private boolean isAuthenticated = false;
    GestorCitas gestorCitas = new GestorCitas();

    @Override
    public void start(Stage primaryStage) {
        gestorCitas.inicializarDatos();


        LoginView loginView = new LoginView();
        loginView.mostrar(primaryStage, () -> cargarVistaPrincipal(primaryStage));
    }

    private void cargarVistaPrincipal(Stage primaryStage) {

        Button btnGestionCitas = new Button("Gestionar Citas");
        Button btnRegistroEstudiantes = new Button("Registrar Estudiantes");
        Button btnDisponibilidadPsicologos = new Button("Ver Disponibilidad de Psicólogos");


        String buttonStyle = "-fx-background-color: transparent; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 15; " +
                "-fx-border-color: white; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 5;";

        btnGestionCitas.setStyle(buttonStyle);
        btnRegistroEstudiantes.setStyle(buttonStyle);
        btnDisponibilidadPsicologos.setStyle(buttonStyle);


        btnGestionCitas.setOnAction(e -> new GestorCitasView().mostrar());
        btnRegistroEstudiantes.setOnAction(e -> new RegistroEstudianteView().mostrar());
        btnDisponibilidadPsicologos.setOnAction(e -> new DisponibilidadView().mostrar());


        HBox layout = new HBox(70, btnGestionCitas, btnDisponibilidadPsicologos, btnRegistroEstudiantes); // Aumento el espacio entre los botones a 70


        layout.setStyle("-fx-alignment: center;");


        StackPane root = new StackPane();
        root.setStyle("-fx-background-image: url('https://old.cue.edu.co/upload/gallery/201711031008587.jpg'); " +
                "-fx-background-size: cover; -fx-background-position: center;");


        Pane overlay = new Pane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");


        root.getChildren().addAll(overlay, layout);


        Scene scene = new Scene(root, 800, 600);  // Tamaño más grande
        primaryStage.setTitle("Sistema de Gestión de Citas - Clínica Psicológica");
        primaryStage.setScene(scene);


        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setResizable(true);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}









