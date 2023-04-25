package com.mycompany.tpARG;

import java.util.List;

public class Ronda {
    private String nro;
    private Partido[] partidos;
    private List<Pronostico> pronosticos;

    public Ronda(String nro, Partido[] partidos) {
        this.nro = nro;
        this.partidos = partidos;
    }

   public int puntos() {
        int totalPuntos = 0;
        for (Pronostico pronostico : pronosticos) {
            totalPuntos += pronostico.puntos();
        }
        return totalPuntos;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public Partido[] getPartidos() {
        return partidos;
    }

    public void setPartidos(Partido[] partidos) {
        this.partidos = partidos;
    }
   
   
}
