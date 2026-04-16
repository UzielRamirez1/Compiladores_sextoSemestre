package fes.aragon.demo1.modelo;

public class Lexico {

    private int indice;
    private String cadena;
    private final int error = -1;
    private final int aceptado = 1;

    public boolean analizar(String cadena) {
        this.cadena = cadena + " "; // espacio para detectar fin
        this.indice = 0;
        int valor = estado_A();
        return valor == aceptado;
    }

    private char siguienteCaracter() {
        if (indice < cadena.length()) {
            return cadena.charAt(indice++);
        }
        return ' ';
    }

    private int estado_A() {
        char c = siguienteCaracter();
        switch (c) {
            case '0': return estado_B();
            case '1': return estado_C();
            default: return error;
        }
    }

    private int estado_B() {
        char c = siguienteCaracter();
        switch (c) {
            case '0': return estado_D();
            case '1': return estado_E();
            default: return error;
        }
    }

    private int estado_C() {
        char c = siguienteCaracter();
        switch (c) {
            case '0': return estado_F();
            case '1': return estado_G();
            default: return error;
        }
    }

    private int estado_D() {
        char c = siguienteCaracter();
        switch (c) {
            case '0': return estado_D();
            case '1': return estado_H();
            default: return error;
        }
    }

    private int estado_E() {
        char c = siguienteCaracter();
        switch (c) {
            case '0': return estado_F();
            case '1': return estado_I();
            case ' ': return aceptado;
            default: return error;
        }
    }

    private int estado_F() {
        char c = siguienteCaracter();
        switch (c) {
            case '0': return estado_I();
            case '1': return estado_J();
            default: return error;
        }
    }

    private int estado_G() {
        char c = siguienteCaracter();
        switch (c) {
            case '0': return estado_F();
            case '1': return estado_G();
            default: return error;
        }
    }

    private int estado_H() {
        char c = siguienteCaracter();
        switch (c) {
            case '0': return estado_F();
            case '1': return estado_I();
            default: return error;
        }
    }

    private int estado_I() {
        char c = siguienteCaracter();
        switch (c) {
            case '0':
            case '1': return estado_I();
            default: return error;
        }
    }

    private int estado_J() {
        char c = siguienteCaracter();
        switch (c) {
            case '0':
            case '1': return estado_I();
            case ' ': return aceptado;
            default: return error;
        }
    }
}