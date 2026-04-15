package mx.unam.modelo;


public class modelo {

    public static class Resultado {
        public boolean valida;
        public String mensaje;

        public Resultado(boolean valida, String mensaje) {
            this.valida = valida;
            this.mensaje = mensaje;
        }
    }

    public Resultado validar(String cadena) {
        int estado = 0;

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            switch (estado) {

                case 0:
                    if (Character.isDigit(c)) estado = 1;
                    else return new Resultado(false, "Debe iniciar con un dígito");
                    break;

                case 1:
                    if (Character.isDigit(c)) estado = 1;
                    else if (c == '.') estado = 2;
                    else if (c == 'e' || c == 'E') estado = 4;
                    else return new Resultado(false, "Carácter inválido en entero");
                    break;

                case 2:
                    if (Character.isDigit(c)) estado = 3;
                    else return new Resultado(false, "Se esperaba dígito después del punto");
                    break;

                case 3:
                    if (Character.isDigit(c)) estado = 3;
                    else if (c == 'e' || c == 'E') estado = 4;
                    else return new Resultado(false, "Carácter inválido en decimal");
                    break;

                case 4:
                    if (c == '+' || c == '-') estado = 5;
                    else if (Character.isDigit(c)) estado = 6;
                    else return new Resultado(false, "Se esperaba signo o dígito en exponente");
                    break;

                case 5:
                    if (Character.isDigit(c)) estado = 6;
                    else return new Resultado(false, "Se esperaba dígito después del signo");
                    break;

                case 6:
                    if (Character.isDigit(c)) estado = 6;
                    else return new Resultado(false, "Carácter inválido en exponente");
                    break;
            }
        }

        // Validación final
        if (estado == 1) return new Resultado(true, "Entero válido");
        if (estado == 3) return new Resultado(true, "Decimal válido");
        if (estado == 6) return new Resultado(true, "Notación científica válida");

        if (estado == 2) return new Resultado(false, "Número incompleto después del punto");
        if (estado == 4 || estado == 5) return new Resultado(false, "Exponente incompleto");

        return new Resultado(false, "Expresión inválida");
    }
}

