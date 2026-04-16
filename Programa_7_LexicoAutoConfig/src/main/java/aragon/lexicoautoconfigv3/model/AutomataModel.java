package aragon.lexicoautoconfigv3.model;

import java.io.File;
import java.util.Scanner;

public class AutomataModel {
    private int[][] tabla;
    private boolean[] estadosAceptacion;
    private String[] alfabeto;
    private int filas, columnas;

    public void cargarConfiguracion(File archivo) throws Exception {
        Scanner sc = new Scanner(archivo);
        if (!sc.hasNextLine()) throw new Exception("Archivo vacío");

        String[] dims = sc.nextLine().split(",");
        filas = Integer.parseInt(dims[0].trim());
        int columnasArchivo = Integer.parseInt(dims[1].trim());

        if (!sc.hasNextLine()) throw new Exception("Falta el alfabeto");
        String[] partesAlfabeto = sc.nextLine().split(",");

        // IMPORTANTE: El alfabeto real son (columnas - 1)
        // última columna es para Aceptación (0 o 1)
        alfabeto = new String[columnasArchivo - 1];
        for(int i=0; i < alfabeto.length; i++) {
            alfabeto[i] = partesAlfabeto[i].trim();
        }

        tabla = new int[filas][alfabeto.length];
        estadosAceptacion = new boolean[filas];

        for (int i = 0; i < filas; i++) {
            if (!sc.hasNextLine()) throw new Exception("Faltan filas");
            String[] datosFila = sc.nextLine().split(",");


            for (int j = 0; j < alfabeto.length; j++) {
                String valor = datosFila[j].trim();
                tabla[i][j] = (valor.equalsIgnoreCase("e") || valor.equals("-1")) ? -1 : Integer.parseInt(valor);
            }


            String esFinal = datosFila[datosFila.length - 1].trim();
            estadosAceptacion[i] = esFinal.equals("1");
        }
        sc.close();
    }

    public boolean evaluar(String cadena) {
        int estadoActual = 0;
        for (char c : cadena.toCharArray()) {
            int col = -1;
            for (int i = 0; i < alfabeto.length; i++) {
                if (alfabeto[i].equals(String.valueOf(c))) { col = i; break; }
            }


            if (col == -1 || estadoActual == -1) return false;

            estadoActual = tabla[estadoActual][col];
        }


        return (estadoActual != -1 && estadosAceptacion[estadoActual]);
    }
}