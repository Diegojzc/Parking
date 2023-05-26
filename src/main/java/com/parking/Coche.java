package com.parking;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashMap;


public class Coche {
    public String marca;
    public String modelo;
    public String matricula;
    public LocalDateTime horaEntrada;
    public LocalDateTime horaSalida;


    public Coche() {

    }

    public Coche(String marca, String modelo,LocalDateTime horaEntrada) {
        this.marca = marca;
        this.modelo = modelo;
        this.horaEntrada= horaEntrada;

    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    @Override
    public String toString() {
        return  "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", horaEntrada=" + horaEntrada +
                '}';
    }


    public boolean existeCoche(String matricula, HashMap<String, Coche> map){
        return map.containsKey(matricula);
    }


    public String getCoche(String matricula,HashMap<String, Coche> map){

        if (map.containsKey(matricula)){
            return "Marca: " +getMarca() + "\nModelo: " + getModelo();
        }
        return "No hay coche con esa matricula";
    }


    public void imprimirCocheSistema(HashMap<String, Coche> map){

        map.forEach((key, value) -> System.out.println("Matricula: " + key + "  " + value));


    }
    public void imprimirCocheParking(HashMap<String, Coche> mapa){
        if (mapa.isEmpty()){
            System.out.println("No hay coches en el parking");
        }
        mapa.forEach((key, value) -> System.out.println(key + " " + value));

    }
    public String canidadPagar(double minutosEntrada, double minutosSalida ){

        double totalMinutos = minutosSalida - minutosEntrada;
        double costo= 0.15;
        double pago = costo* totalMinutos;
        BigDecimal total = BigDecimal.valueOf(pago);

        return "" + total;


    }


}
