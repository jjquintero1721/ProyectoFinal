package view;

import controller.GestorCitas;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Cita;

import java.util.List;

public class GestorCitasView {
    private final GestorCitas gestorCitas = new GestorCitas();

    public void mostrar() {
        Stage stage = new Stage();

        stage.getIcons().add(new Image("https://www.nosequeestudiar.net/site/assets/files/4955/logo-cueavh-circular-1.180x180.png"));

        Label lblTitulo = new Label("Gestión de Citas");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        // Tabla de citas
        TableView<Cita> tablaCitas = new TableView<>();
        TableColumn<Cita, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Cita, String> colEstudiante = new TableColumn<>("Estudiante");
        colEstudiante.setCellValueFactory((c) ->
                c.getValue().getEstudiante() != null ?
                        new SimpleStringProperty(c.getValue().getEstudiante().getNombre()) :
                        new SimpleStringProperty("")
        );

        TableColumn<Cita, String> colPsicologo = new TableColumn<>("Psicólogo");
        colPsicologo.setCellValueFactory((c) ->
                c.getValue().getPsicologo() != null ?
                        new SimpleStringProperty(c.getValue().getPsicologo().getNombre()) :
                        new SimpleStringProperty("")
        );

        TableColumn<Cita, String> colFechaHora = new TableColumn<>("Fecha y Hora");
        colFechaHora.setCellValueFactory((c) ->
                new SimpleStringProperty(c.getValue().getFechaHora().toString())
        );

        tablaCitas.getColumns().addAll(colId, colEstudiante, colPsicologo, colFechaHora);
        cargarTablaCitas(tablaCitas);

        // Botones
        Button btnRegistrarCita = crearBotonConEstilo("Registrar Nueva Cita");
        Button btnModificarCita = crearBotonConEstilo("Modificar Cita");
        Button btnCancelarCita = crearBotonConEstilo("Cancelar Cita");

        // Acciones de los botones
        btnRegistrarCita.setOnAction((e) -> (new RegistroCitaView()).mostrar());
        btnModificarCita.setOnAction((e) -> mostrarDialogoModificarCita(tablaCitas));
        btnCancelarCita.setOnAction((e) -> cancelarCita(tablaCitas));

        // Layout de botones en fila horizontal
        HBox buttonLayout = new HBox(20, btnRegistrarCita, btnModificarCita, btnCancelarCita);
        buttonLayout.setAlignment(Pos.CENTER);

        // Layout principal
        VBox layout = new VBox(15, lblTitulo, tablaCitas, buttonLayout);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

        // Fondo con imagen
        Image backgroundImage = new Image("https://old.cue.edu.co/upload/gallery/201711031008587.jpg"); // Cambia a la ruta correcta
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true
                )
        );

        StackPane root = new StackPane();
        root.setBackground(new Background(bgImage));

        // Filtro oscuro para contraste
        Pane overlay = new Pane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6);");

        root.getChildren().addAll(overlay, layout);

        // Crear escena
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Gestión de Citas");
        stage.show();
    }

    private Button crearBotonConEstilo(String texto) {
        Button boton = new Button(texto);
        boton.setStyle("-fx-background-color: transparent; " +
                "-fx-border-color: white; " +
                "-fx-border-width: 2px; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-padding: 10px 20px; " +
                "-fx-border-radius: 8px; " +
                "-fx-background-radius: 8px;");
        boton.setOnMouseEntered(e -> boton.setStyle("-fx-background-color: white; " +
                "-fx-text-fill: black; " +
                "-fx-border-color: white; " +
                "-fx-border-width: 2px; " +
                "-fx-font-size: 16px; " +
                "-fx-padding: 10px 20px; " +
                "-fx-border-radius: 8px; " +
                "-fx-background-radius: 8px;"));
        boton.setOnMouseExited(e -> boton.setStyle("-fx-background-color: transparent; " +
                "-fx-border-color: white; " +
                "-fx-border-width: 2px; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-padding: 10px 20px; " +
                "-fx-border-radius: 8px; " +
                "-fx-background-radius: 8px;"));
        return boton;
    }

    private void mostrarDialogoModificarCita(TableView<Cita> tablaCitas) {

        Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada != null) {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Modificar Cita");
            dialog.setHeaderText("Modificar Fecha y Hora");


            dialog.getDialogPane().setMinWidth(400);
            dialog.getDialogPane().setPrefSize(450, 300);


            dialog.getDialogPane().setStyle("-fx-background-color: #2d2d2d; -fx-text-fill: white;");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            Label label = new Label("Ingrese la nueva fecha y hora (YYYY-MM-DDTHH:MM):");
            label.setStyle("-fx-text-fill: white;");

            TextField txtFechaHora = new TextField(citaSeleccionada.getFechaHora().toString());
            txtFechaHora.setPromptText("YYYY-MM-DDTHH:MM");
            txtFechaHora.setPrefWidth(250);

            VBox content = new VBox(20, label, txtFechaHora);
            content.setAlignment(Pos.CENTER);
            content.setPadding(new Insets(20));

            dialog.getDialogPane().setContent(content);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    return txtFechaHora.getText();
                }
                return null;
            });

            dialog.showAndWait().ifPresent(nuevaFechaHora -> {
                try {
                    gestorCitas.modificarCita(citaSeleccionada.getId(), nuevaFechaHora);
                    cargarTablaCitas(tablaCitas);
                    mostrarMensaje("Cita modificada correctamente.");
                } catch (IllegalArgumentException ex) {
                    mostrarMensaje(ex.getMessage());
                } catch (Exception ex) {
                    mostrarMensaje("Error al modificar la cita: " + ex.getMessage());
                }
            });
        } else {
            mostrarMensaje("Por favor, seleccione una cita.");
        }
    }

    private void cargarTablaCitas(TableView<Cita> tabla) {
        List<Cita> citas = gestorCitas.obtenerCitas();
        tabla.setItems(FXCollections.observableArrayList(citas));
    }

    private void cancelarCita(TableView<Cita> tablaCitas) {
        Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada != null) {
            gestorCitas.cancelarCita(citaSeleccionada.getId());
            cargarTablaCitas(tablaCitas);
            mostrarMensaje("Cita cancelada correctamente.");
        } else {
            mostrarMensaje("Por favor, seleccione una cita.");
        }
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.show();
    }
}


