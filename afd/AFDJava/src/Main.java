public class Main {
    public static void main(String[] args) {
        AFD afd = new AFD();

        String cadena = "000101";
        if (afd.procesar(cadena)) {
            System.out.println("Cadena aceptada");
        } else {
            System.out.println("Cadena rechazada");
        }
    }
}
