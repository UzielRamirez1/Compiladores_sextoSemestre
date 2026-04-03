public class AFD {
    public boolean procesar(String cadena) {
        return estadoA(cadena);
    }

    private boolean estadoA(String cadena) {
        if (cadena.isEmpty()) return false;

        char simbolo = cadena.charAt(0);
        String resto = cadena.substring(1);

        if (simbolo == '0') return estadoB(resto);
        if (simbolo == '1') return estadoC(resto);

        return false;
    }

    private boolean estadoB(String cadena) {
        if (cadena.isEmpty()) return false;

        char simbolo = cadena.charAt(0);
        String resto = cadena.substring(1);

        if (simbolo == '0') return estadoD(resto);
        if (simbolo == '1') return estadoE(resto);

        return false;
    }

    private boolean estadoC(String cadena) {
        if (cadena.isEmpty()) return false;

        char simbolo = cadena.charAt(0);
        String resto = cadena.substring(1);

        if (simbolo == '0') return estadoF(resto);
        if (simbolo == '1') return estadoG(resto);

        return false;
    }

    private boolean estadoD(String cadena) {
        if (cadena.isEmpty()) return false;

        char simbolo = cadena.charAt(0);
        String resto = cadena.substring(1);

        if (simbolo == '0') return estadoD(resto);
        if (simbolo == '1') return estadoH(resto);

        return false;
    }

    private boolean estadoE(String cadena) {
        if (cadena.isEmpty()) return true; // Estado final

        char simbolo = cadena.charAt(0);
        String resto = cadena.substring(1);

        if (simbolo == '0') return estadoF(resto);
        if (simbolo == '1') return estadoI(resto);

        return false;
    }

    private boolean estadoF(String cadena) {
        if (cadena.isEmpty()) return false;

        char simbolo = cadena.charAt(0);
        String resto = cadena.substring(1);

        if (simbolo == '0') return estadoI(resto);
        if (simbolo == '1') return estadoJ(resto);

        return false;
    }

    private boolean estadoG(String cadena) {
        if (cadena.isEmpty()) return false;

        char simbolo = cadena.charAt(0);
        String resto = cadena.substring(1);

        if (simbolo == '0') return estadoF(resto);
        if (simbolo == '1') return estadoG(resto);

        return false;
    }

    private boolean estadoH(String cadena) {
        if (cadena.isEmpty()) return false;

        char simbolo = cadena.charAt(0);
        String resto = cadena.substring(1);

        if (simbolo == '0') return estadoF(resto);
        if (simbolo == '1') return estadoI(resto);

        return false;
    }

    private boolean estadoI(String cadena) {
        if (cadena.isEmpty()) return false;

        char simbolo = cadena.charAt(0);
        String resto = cadena.substring(1);

        if (simbolo == '0' || simbolo == '1') return estadoI(resto);

        return false;
    }

    private boolean estadoJ(String cadena) {
        if (cadena.isEmpty()) return true; // Estado final

        char simbolo = cadena.charAt(0);
        String resto = cadena.substring(1);

        if (simbolo == '0' || simbolo == '1') return estadoI(resto);

        return false;
    }


}
