import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Usuario {
    public static final String RESET = "\u001B[0m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String MORADO = "\u001B[35m";
    public static final String CIAN = "\u001B[36m";
    public static final String BLANCO = "\u001B[37m";
    private static final String FILE = "Usuarios.txt";

    private static ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    private String Nombre;
    private String contrasena;
    private String tipo;

    public Usuario(String nombre, String contrasena, String tipo) {
        this.tipo = tipo;
        this.contrasena = contrasena;
        this.Nombre = nombre;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String isTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static void cargarUsuarios() {
        listaUsuarios.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    Usuario u = new Usuario(partes[0], partes[1], partes[2]);
                    listaUsuarios.add(u); // usuario[0] = nombre, usuario[1] = contraseña
                }
            }
        } catch (IOException e) {
            IO.println(ROJO+"Archivo no encontrado, Se creara uno"+RESET);
        }
    }

    static void crearCuenta(String nombre, String contrasena, String tipo) {
        try(FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(nombre + "," + contrasena + ","+ tipo + "\n");
            IO.println(AZUL+"****Cuenta creada correctamente****"+RESET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean iniciarSesion(String nombre, String contrasena, String tipo) {
        for (Usuario i: listaUsuarios) {
            if (i.getNombre().equals(nombre)&&i.getContrasena().equals(contrasena)&&i.isTipo().equals(tipo)){
                IO.println(AZUL+"****Iniciando sesion****"+RESET);
                return true;
            }
        }
        IO.println(ROJO+"Usuario no encontrado, vuelve a intentarlo"+RESET);
        return false;
    }
}
