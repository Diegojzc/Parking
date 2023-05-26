package com.parking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parking {
    private final Scanner keybord;
    private Coche coche1;
    private String matricula;
    private boolean exit;
    Pattern pattern;
    Matcher mat;
    int intentos =3;


    HashMap<String, Coche> mapa;
    HashMap<String, Coche> cochesTotal;


    public Parking() {
        keybord = new Scanner(System.in);
        coche1 = new Coche();
        mapa = new HashMap<>();
        cochesTotal = new HashMap<>();
        exit = true;


    }


    public void menu() {


        do {
            String entrada;

            System.out.println("Parking");
            System.out.println("1- Registrar entrada de coche");
            System.out.println("2- Registrar salida de coche");
            System.out.println("3- Imprimir coches del parking");
            System.out.println("4- Imprimir coches del sistema");
            System.out.println("5- Comprobar si coche esta en el parking");
            System.out.println("6- Ver datos del coche");
            System.out.println("7- Salir");
            System.out.print("Elegir opcion: ");
            entrada = keybord.nextLine();


            switch (entrada) {
                case "1":

                    entrar();
                    break;

                case "2":
                    registrarSalida();
                    break;

                case "3":
                    coche1.imprimirCocheParking(mapa);
                    saltarYLanzar();
                    break;

                case "4":
                    coche1.imprimirCocheSistema(cochesTotal);
                    saltarYLanzar();
                    break;

                case "5":
                    consultar();
                    break;

                case "6":
                    datosCoche();
                    saltarYLanzar();
                    break;

                case "7":
                    exit = false;
                    System.out.println("Programa terminado");
                    break;
            }

        } while (exit);


    }

    public void saltarYLanzar() {
        System.out.println("Pulse enter para salir al menu...");
        keybord.nextLine();
        menu();
    }

    public void entrar() {
        int intentosMarca=3;
        int intentosModelo =3;
        int intentosMatricula= 3;
        String marca;
        String modelo;

        do {


            System.out.print("Introducir marca:");
            marca = keybord.nextLine();

            if (marca.isEmpty()) {
                System.out.println("ERROR: Introduzca la marca correcta");
                intentosMarca--;
                if (intentosMarca==0){
                    saltarYLanzar();
                }
            }
        } while (marca.isEmpty());


        do {
            System.out.print("Introduzca el modelo:");
            modelo = keybord.nextLine();
            if (modelo.isEmpty()) {
                System.out.println("ERROR: Introduzca el modelo correcto");
                intentosModelo--;
                if (intentosModelo==0){
                    saltarYLanzar();
                }

            }
        } while (modelo.isEmpty());


        do {
           matriculaValida();
            if (matricula.isEmpty() || !mat.matches()) {
                System.out.println("ERROR: Introduzca la matricula correcta, con el patron: AAA111");
                intentosMatricula--;
                if (intentosMatricula==0){
                    saltarYLanzar();
                }
            }


        } while (matricula.isEmpty() || !mat.matches());


        LocalDateTime localTime = LocalDateTime.now();
        coche1 = new Coche(marca, modelo, localTime);
        mapa.put(matricula, coche1);

        cochesTotal.put(matricula, coche1);
        System.out.println("Coche introducido correctamente");

        saltarYLanzar();


    }

    public void registrarSalida() {
        primerPaso();
        int intentosMatricula =3;

        do {
           matriculaValida();
            if (matricula.isEmpty() || !mat.matches()) {
                System.out.println("ERROR: Introduzca la matricula correcta, con el patron: AAA111");
                intentosMatricula--;
                if (intentosMatricula==0){
                    saltarYLanzar();
                }

            }
            if (!mapa.containsKey(matricula)) {
                System.out.println("la matricula no existe");

            }
        } while (matricula.isEmpty() || !mat.matches() || !mapa.containsKey(matricula) && intentos>0);


        LocalDateTime horaSalida = LocalDateTime.now();
        String localTimeSalida = horaSalida.format(DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy/ HH:mm"));
        mapa.remove(matricula);
        System.out.println("Coche con: " + matricula + " ha salido del parking a " + localTimeSalida);
        LocalDateTime horaEntrada = coche1.getHoraEntrada();

        double minutosEntrada = horaEntrada.getMinute();
        double minutosSalida = horaSalida.getMinute();

        System.out.println("El precio a pagar es: "+ coche1.canidadPagar(minutosEntrada, minutosSalida)+ " €");

        saltarYLanzar();
    }


    public void consultar() {

        primerPaso();

        int intentosMatricula=3;
        do {
            matriculaValida();
            if (!mat.matches()) {
                System.out.println("ERROR! La matricula no es valida");
                intentosMatricula--;
                if (intentosMatricula==0){
                    saltarYLanzar();
                }
            }

        } while (!mat.matches());


        if (!coche1.existeCoche(matricula, mapa)) {
            System.out.println("coche ya no esta aun en el parking");
        } else {
            System.out.println("El coche esta aun en el parking");
        }
        saltarYLanzar();

    }

    public void datosCoche() {
        primerPaso();

        int intentosMatricula=3;
        do {
            matriculaValida();
            intentosMatricula--;
            if (intentosMatricula==0){
                saltarYLanzar();
            }

            System.out.println(coche1.getCoche(matricula, mapa));
        }while(matricula.isEmpty() || !(mat.matches()));


    }
    public void matriculaValida(){

        System.out.print("Introducir matricula: ");
        matricula = keybord.nextLine();
        String matriculaValida = "^[A-Za-z]{3}\\d{3}$";
        pattern = Pattern.compile(matriculaValida);
        mat = pattern.matcher(matricula);
    }
    public void primerPaso(){
        if (mapa.isEmpty()){
            System.out.println("Parking vacio, introducir primeramente coches en el parking");
            saltarYLanzar();

        }
    }

    }


