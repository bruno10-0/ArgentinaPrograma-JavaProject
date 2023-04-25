package com.mycompany.tpARG;

import java.util.HashMap;

public class Participante {

    private int id;
    private String nombre;
    private int puntos;
    private final HashMap<Integer, Integer> rondasAciertos;
    private int rondasCompletas;

    public Participante(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.puntos = 0;
        this.rondasAciertos = new HashMap<>();
        this.rondasCompletas = 0;
        for (int i = 1; i <= 3; i++) {
            rondasAciertos.put(i, 0);
        }
    }

public void sumarPunto(int ronda) {
    int aciertosRonda = rondasAciertos.get(ronda);
    int nuevosAciertos = aciertosRonda + 1;
    rondasAciertos.put(ronda, nuevosAciertos);
    puntos += Configuracion.getPuntosPorAcierto();
    if (nuevosAciertos == 2) {
        puntos += Configuracion.getPuntosPorAciertoRonda();
    } else if (nuevosAciertos == 3) {
        rondasCompletas++;
        if (rondasCompletas % 3 == 0 && rondasAciertos.get(1) >= 3 && rondasAciertos.get(2) >= 3 && rondasAciertos.get(3) >= 3) {
            puntos += Configuracion.getPuntosPorAciertoFases();
            // Restablecer los conteos de aciertos para las rondas
            rondasAciertos.put(1, 0);
            rondasAciertos.put(2, 0);
            rondasAciertos.put(3, 0);
        }
    }
}



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void sumarAciertoRonda(int ronda) {
        rondasAciertos.put(ronda, rondasAciertos.getOrDefault(ronda, 0) + 1);
    }
}
