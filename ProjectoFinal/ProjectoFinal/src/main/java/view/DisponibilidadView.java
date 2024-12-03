package view;

import controller.GestorCitas;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Psicologo;

import java.util.List;

public class DisponibilidadView {
    private final GestorCitas gestorCitas = new GestorCitas();

    public void mostrar() {
        Stage stage = new Stage();

        stage.getIcons().add(new Image("https://www.nosequeestudiar.net/site/assets/files/4955/logo-cueavh-circular-1.180x180.png"));

        Label lblTitulo = new Label("Disponibilidad de Psicólogos");
        lblTitulo.setFont(Font.font("Arial", 24));
        lblTitulo.setTextFill(Color.WHITE);


        TableView<Psicologo> tablaPsicologos = new TableView<>();
        tablaPsicologos.setPrefHeight(300);
        tablaPsicologos.setPrefWidth(400);

        TableColumn<Psicologo, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(150);

        TableColumn<Psicologo, String> colEspecialidad = new TableColumn<>("Especialidad");
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        colEspecialidad.setPrefWidth(150);

        TableColumn<Psicologo, String> colHorariosDisponibles = new TableColumn<>("Horarios Disponibles");
        colHorariosDisponibles.setCellValueFactory(new PropertyValueFactory<>("horariosDisponibles"));
        colHorariosDisponibles.setPrefWidth(200);

        tablaPsicologos.getColumns().addAll(colNombre, colEspecialidad, colHorariosDisponibles);


        cargarTablaDisponibilidad(tablaPsicologos);

        // Layout principal
        VBox layout = new VBox(15, lblTitulo, tablaPsicologos);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");


        Image backgroundImage = new Image("https://old.cue.edu.co/upload/gallery/201711031008587.jpg"); // Cambia a la ruta correcta
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        StackPane root = new StackPane();
        root.setBackground(new Background(bgImage));


        Pane overlay = new Pane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6);");

        root.getChildren().addAll(overlay, layout);


        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Disponibilidad de Psicólogos");
        stage.show();
    }

    private void cargarTablaDisponibilidad(TableView<Psicologo> tabla) {
        List<Psicologo> psicologos = gestorCitas.obtenerPsicologos();
        tabla.setItems(FXCollections.observableArrayList(psicologos));
    }
}
