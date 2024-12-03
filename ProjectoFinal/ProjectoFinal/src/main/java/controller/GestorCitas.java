package controller;

import model.Cita;
import model.Estudiante;
import model.Psicologo;
import repository.CitaRepository;
import repository.EstudianteRepository;
import repository.PsicologoRepository;

import java.util.List;

public class GestorCitas {
    private final CitaRepository citaRepo = new CitaRepository();
    private final EstudianteRepository estudianteRepo = new EstudianteRepository();
    private final PsicologoRepository psicologoRepo = new PsicologoRepository();



    public void registrarEstudiante(Estudiante estudiante) {
        if (estudiante.getCodigoEstudiante() == null || estudiante.getCodigoEstudiante().isEmpty()) {
            throw new IllegalArgumentException("El código del estudiante es obligatorio.");
        }
        estudianteRepo.guardar(estudiante);
    }

    public void registrarPsicologo(Psicologo psicologo) {
        psicologoRepo.guardar(psicologo);
    }

    public void registrarCita(Cita cita) {

        Psicologo psicologo = cita.getPsicologo();
        String horariosDisponibles = psicologo.getHorariosDisponibles();


        String horaCita = cita.getFechaHora().toLocalTime().toString();


        if (!horariosDisponibles.contains(horaCita)) {
            throw new IllegalArgumentException("El psicólogo no está disponible en el horario indicado.");
        }


        citaRepo.guardar(cita);


        actualizarDisponibilidadPsicologo(psicologo, horaCita, false);
    }


    public void modificarCita(Long idCita, String nuevaFechaHora) {
        Cita cita = citaRepo.obtenerPorId(idCita);
        Psicologo psicologo = cita.getPsicologo();


        String horariosDisponibles = psicologo.getHorariosDisponibles();
        String nuevaHora = java.time.LocalDateTime.parse(nuevaFechaHora).toLocalTime().toString();

        if (!horariosDisponibles.contains(nuevaHora)) {
            throw new IllegalArgumentException("El psicólogo no está disponible en el nuevo horario.");
        }


        citaRepo.modificarCita(idCita, nuevaFechaHora);


        actualizarDisponibilidadPsicologo(psicologo, cita.getFechaHora().toLocalTime().toString(), true); // Liberar horario anterior
        actualizarDisponibilidadPsicologo(psicologo, nuevaHora, false);
    }


    public void cancelarCita(Long idCita) {
        Cita cita = citaRepo.obtenerPorId(idCita);
        citaRepo.cancelarCita(idCita);
        actualizarDisponibilidadPsicologo(cita.getPsicologo(), cita.getFechaHora().toString(), true);
    }

    public List<Psicologo> obtenerPsicologos() {
        return psicologoRepo.obtenerTodos();
    }

    public Psicologo obtenerPsicologoPorNombre(String nombre) {
        return psicologoRepo.obtenerPorNombre(nombre);
    }

    public boolean verificarDisponibilidadPsicologo(String nombre, String horario) {
        return psicologoRepo.verificarDisponibilidad(nombre, horario);
    }

    private void actualizarDisponibilidadPsicologo(Psicologo psicologo, String horario, boolean disponible) {
        String horarios = psicologo.getHorariosDisponibles();
        if (disponible) {

            if (!horarios.contains(horario)) {
                horarios += (horarios.isEmpty() ? "" : ",") + horario;
            }
        } else {

            horarios = horarios.replace(horario, "").replace(",,", ",").replaceAll("^,|,$", "");
        }
        psicologoRepo.actualizarDisponibilidad(psicologo, horarios);
    }

    public List<Estudiante> obtenerEstudiantes() {
        return estudianteRepo.obtenerTodos();
    }
    public List<Cita> obtenerCitas() {
        return citaRepo.obtenerTodas();
    }
    public void inicializarDatos() {
        if (psicologoRepo.obtenerTodos().isEmpty()) {
            registrarPsicologo(new Psicologo(1L,"Dr. Juan Pérez", "Psicología Clínica", "10:00,11:00,14:00"));
            registrarPsicologo(new Psicologo(2L,"Dra. Ana Gómez", "Psicoterapia Familiar", "09:00,13:00,16:00"));
            registrarPsicologo(new Psicologo(3L,"Dr. Carlos López", "Psicología Infantil", "08:00,12:00,15:00"));
            System.out.println("Psicólogos preestablecidos registrados.");
        } else {
            System.out.println("Psicólogos ya registrados.");
        }
    }

    public void View() {
    }
}