package fes.aragon.ico.leertxt.modelo;

public class Modelo {

    // Valida que empiece con 00 y termine con 00
    // y que solo contenga 0 y 1
    public boolean esValido(String cadena) {


        if (cadena == null || cadena.length() < 2) {
            return false;
        }

        // Verificar que solo tenga 0 y 1
        for (int i = 0; i < cadena.length(); i++) {

            char c = cadena.charAt(i);
            if (c != '0' && c != '1') {
                return false;
            }
        }

        // Verificar inicio 00
        if (!(cadena.charAt(0) == '0' && cadena.charAt(1) == '0')) {
            return false;
        }

        // Verificar final 00
        int n = cadena.length();
        if (!(cadena.charAt(n - 1) == '0' && cadena.charAt(n - 2) == '0')) {
            return false;
        }

        return true;
    }
}

