package view;

import controller.GestorCitas;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Cita;
import model.Estudiante;
import model.Psicologo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class RegistroCitaView {
    private final GestorCitas gestorCitas = new GestorCitas();

    public RegistroCitaView() {

    }

    public void mostrar() {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("https://www.nosequeestudiar.net/site/assets/files/4955/logo-cueavh-circular-1.180x180.png"));

        Image fondo = new Image("https://old.cue.edu.co/upload/gallery/201711031008587.jpg");
        BackgroundImage bgImage = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(bgImage));
        layout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 20; -fx-border-radius: 10; -fx-background-radius: 10;");

        Label lblTitulo = new Label("Registrar Nueva Cita");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2d2d2d;");

        ComboBox<Estudiante> cbEstudiantes = new ComboBox<>();
        cbEstudiantes.setPromptText("Seleccione un estudiante");
        cbEstudiantes.setMaxWidth(250);

        ComboBox<Psicologo> cbPsicologos = new ComboBox<>();
        cbPsicologos.setPromptText("Seleccione un psicólogo");
        cbPsicologos.setMaxWidth(250);

        TextField txtFechaHora = new TextField();
        txtFechaHora.setPromptText("Ingrese fecha y hora (YYYY-MM-DDTHH:MM)");
        txtFechaHora.setMaxWidth(250);
        txtFechaHora.setStyle("-fx-font-size: 14px; -fx-padding: 8; -fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-radius: 5;");

        Button btnRegistrar = new Button("Registrar Cita");
        btnRegistrar.setStyle("-fx-border-color: #333; -fx-background-color: transparent; -fx-text-fill: #333; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 8;");
        btnRegistrar.setOnMouseEntered(e -> btnRegistrar.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 8;"));
        btnRegistrar.setOnMouseExited(e -> btnRegistrar.setStyle("-fx-border-color: #333; -fx-background-color: transparent; -fx-text-fill: #333; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 8;"));


        cargarEstudiantes(cbEstudiantes);
        cargarPsicologos(cbPsicologos);


        btnRegistrar.setOnAction(e -> {
            Estudiante estudianteSeleccionado = cbEstudiantes.getValue();
            Psicologo psicologoSeleccionado = cbPsicologos.getValue();
            String fechaHoraTexto = txtFechaHora.getText();

            if (estudianteSeleccionado == null || psicologoSeleccionado == null || fechaHoraTexto.isEmpty()) {
                mostrarMensaje("Por favor, complete todos los campos.");
                return;
            }

            try {
                LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraTexto);
                Cita nuevaCita = new Cita();
                nuevaCita.setEstudiante(estudianteSeleccionado);
                nuevaCita.setPsicologo(psicologoSeleccionado);
                nuevaCita.setFechaHora(fechaHora);

                gestorCitas.registrarCita(nuevaCita);
                mostrarMensaje("Cita registrada exitosamente.");
                stage.close();
            } catch (IllegalArgumentException ex) {
                mostrarMensaje(ex.getMessage());
            } catch (DateTimeParseException ex) {
                mostrarMensaje("El formato de fecha y hora es incorrecto. Use el formato: YYYY-MM-DDTHH:MM");
            }
        });


        layout.getChildren().addAll(lblTitulo, cbEstudiantes, cbPsicologos, txtFechaHora, btnRegistrar);


        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Registrar Nueva Cita");
        stage.show();
    }

    private void cargarEstudiantes(ComboBox<Estudiante> cbEstudiantes) {
        List<Estudiante> estudiantes = gestorCitas.obtenerEstudiantes();
        cbEstudiantes.setItems(FXCollections.observableArrayList(estudiantes));
    }

    private void cargarPsicologos(ComboBox<Psicologo> cbPsicologos) {
        List<Psicologo> psicologos = gestorCitas.obtenerPsicologos();
        cbPsicologos.setItems(FXCollections.observableArrayList(psicologos));
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.show();
    }
}
