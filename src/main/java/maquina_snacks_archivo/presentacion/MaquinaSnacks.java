package maquina_snacks_archivo.presentacion;

import maquina_snacks_archivo.dominio.Snack;
import maquina_snacks_archivo.servicio.IServicioSnacks;
import maquina_snacks_archivo.servicio.ServicioSnacksLista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main(String[] args) {
        maquinaSnacks();
    }

    public static void maquinaSnacks() {
        var salir = false;
        var consola = new Scanner(System.in);
        // Crear objeto para obtener el servicio de Snacks
        IServicioSnacks servicioSnacks = new ServicioSnacksLista();
        // Crear lista de productos de tipo Snack
        List<Snack> productos = new ArrayList<>();
        System.out.println("*** Maquina de maquina_snacks_archivo.servicio.Snacks ***");
        servicioSnacks.mostrarSnacks(); // Mostrar inventario de snacks disponibles
        while(!salir) {
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, productos, servicioSnacks);
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            } finally {
                System.out.println(); // Imprime un salto de linea con cada iteración
            }
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.print("""
                Menú:
                1. Comprar Snack
                2. Mostrar Ticket
                3. Agregar Nuevo Snack
                4. Salir
                
                Elige una opción:\s""");
        // Leemos y retornamos la opción seleccionada
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, List<Snack> productos, IServicioSnacks servicioSnacks) {
        var salir = false;
        switch (opcion) {
            case 1 -> comprarSnack(consola, productos, servicioSnacks);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(consola, servicioSnacks);
            case 4 -> {
                System.out.println("Regresa pronto!");
                salir = true;
            }
            default -> System.out.println("Opción inválida: " + opcion);
        }
        return salir;
    }

    private static void comprarSnack (Scanner consola, List<Snack> productos, IServicioSnacks servicioSnacks) {
        System.out.print("Qué snack quieres comprar (id)?: ");
        var idSnack = Integer.parseInt(consola.nextLine());
        // Validación
        var snackEncontrado = false;
        for(var snack: servicioSnacks.getSnacks()) {
            if (idSnack == snack.getIdSnack()) {
                // Agregar snack porque se encontró
                productos.add(snack);
                System.out.println("Snack agregado: " + snack);
                snackEncontrado = true;
                break;
            }
        }
        if(!snackEncontrado) {
            System.out.println("Id de snack no encontrado: " + idSnack);
        }
    }

    private static void mostrarTicket(List<Snack> productos) {
        var ticket = "*** Ticket de Venta ***";
        var total = 0.0;
        for(var producto: productos) {
            ticket += "\n\t-" + producto.getNombre() + " - $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        ticket += "\n\tTotal -> $" + total;
        System.out.println(ticket);
    }

    private static void agregarSnack(Scanner consola, IServicioSnacks servicioSnacks) {
        System.out.print("Nombre del snack: ");
        var nombre = consola.nextLine();
        System.out.print("Precio del snack: ");
        var precio = Double.parseDouble(consola.nextLine());
        servicioSnacks.agregarSnack(new Snack(nombre,precio));
        System.out.println("Snack creado correctamente");
        servicioSnacks.mostrarSnacks();
    }

}