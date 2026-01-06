import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class AgregarContenidoArchivo {
    public static void main(String[] args) {
        boolean anexar = false;
        var nombreArchivo = "mi_archivo.txt";
        var archivo = new File(nombreArchivo);
        try {
            // Revisar si existe el archivo
            anexar = archivo.exists();
            // si anexar es true, se escribe en ultima linea, no se sobre escribe
            // si anexar es false (el arhcivo está vacio), se sobreescribe desde la primera linea
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            var nuevoContenido = "Nuevo\ncontenido";
            salida.println(nuevoContenido);
            salida.close();
            System.out.println("Se agregó el contenido al archivo!");
        } catch (Exception e) {
            System.out.println("Error al escribir al archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}