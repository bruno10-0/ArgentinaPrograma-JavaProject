package com.mycompany.tpARG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, Participante> participantes = new HashMap<>();

        try {
            Connection connection = Database.getConnection();
            if (connection != null) {
                // Cargar participantes
                String queryParticipantes = "SELECT DISTINCT id_usuario, usuario FROM apuestas";
                PreparedStatement stmtApuestas;
                ResultSet rsApuestas;
                try (PreparedStatement stmtParticipantes = connection.prepareStatement(queryParticipantes); ResultSet rsParticipantes = stmtParticipantes.executeQuery()) {
                    while (rsParticipantes.next()) {
                        int idUsuario = rsParticipantes.getInt("id_usuario");
                        String usuario = rsParticipantes.getString("usuario");
                        participantes.put(idUsuario, new Participante(idUsuario, usuario));
                    }   // Calcular puntos
                    String queryApuestas = "SELECT * FROM apuestas APUESTA INNER JOIN resultadosDePartidos RESULT ON APUESTA.id_partido = RESULT.id_partido";
                    stmtApuestas = connection.prepareStatement(queryApuestas);
                    rsApuestas = stmtApuestas.executeQuery();
                    while (rsApuestas.next()) {
                        int idUsuario = rsApuestas.getInt("id_usuario");
                        String equipoApostado = rsApuestas.getString("equipoApostado");
                        String ganador = rsApuestas.getString("Ganador");
                        int ronda = rsApuestas.getInt("Ronda");

                        if (equipoApostado.equals(ganador)) {
                            Participante participante = participantes.get(idUsuario);
                            participante.sumarPunto(ronda);
                        }
                    }
                    List<Participante> participantesOrdenados = new ArrayList<>(participantes.values());
                    participantesOrdenados.sort(Comparator.comparingInt(Participante::getPuntos).reversed());
                    for (Participante participante : participantesOrdenados) {
                        System.out.println("Participante: " + participante.getNombre() + " - Puntos: " + participante.getPuntos());
                    }
                }
                rsApuestas.close();
                stmtApuestas.close();
                connection.close();
            } else {
                System.out.println("No se pudo establecer la conexión con la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error al conectarse a la base de datos" + e.getMessage());
        }
    }
}
