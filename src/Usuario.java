import java.io.*;
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
    private static final String FILE = "./Usuarios.txt";
    private static final ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();//se crea el arrayList
    private static final File file = new File(FILE);

    private String Nombre;
    private String contraseña;
    private String tipo;

    //Metodo de usuario

    public Usuario(String nombre, String contraseña, String tipo) {
        this.tipo = tipo;
        this.contraseña = contraseña;
        this.Nombre = nombre;
    }

    // Getters
    public String getNombre() {
        return Nombre;
    }
    public String getContraseña() {
        return contraseña;
    }
    public String getTipo() {
        return tipo;
    }

    //Setters
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static void verificarArchivo() {
        if (!file.exists()) {
            IO.println(ROJO+"Archivo no encontado"+RESET);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
                bw.write(String.format("|%-17s %s %17s| %n","","=====LISTA USUARIOS=====",""));
                bw.write(String.format("|%-20s |%-20s |%-15s |%n", "NOMBRE", "CONTRASEÑA", "TIPO"));
                IO.println("Se ha creado el archivo: " + file.getName());
            } catch (IOException e) {
                System.out.println("Ocurrio un error");
            }
        }
    }

    public static void cargarUsuarios() {
        listaUsuarios.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.replaceFirst("^\\|", "").replaceFirst("\\|$", "");
                String[] partes = linea.split("\\|");
                if (partes.length == 3) {
                    listaUsuarios.add(new Usuario(partes[0].trim(),partes[1].trim(),partes[2].trim()));
                     // parte[0] = nombre, parte[1] = contraseña, parte[2] = tipo
                }
            }
        } catch (IOException e) {
            IO.println("Errar al leer usuarios: "+e.getMessage());
        }
    }

    static void crearCuenta(String nombre, String contraseña, String tipo) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(String.format( "|%-20s |%-20s |%-15s |%n",nombre,contraseña,tipo));
            IO.println(AZUL+"****Cuenta creada correctamente****"+RESET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean iniciarSesion(String nombre, String contraseña, String tipo) {
        for (Usuario usuario: listaUsuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)&&
                    usuario.getContraseña().equals(contraseña)&&
                    usuario.getTipo().equals(tipo)){
                IO.println(AZUL+"****Iniciando sesion****"+RESET);
                return true;
            }
        }
        IO.println(ROJO+"Usuario no encontrado, vuelve a intentarlo"+RESET);
        return false;
    }

    static boolean verificarExistente(String nombre, String contraseña, String tipo) {
        Usuario.cargarUsuarios();
        for (Usuario usuario: listaUsuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)&&
                    usuario.getContraseña().equals(contraseña)&&
                    usuario.getTipo().equals(tipo)){
                return true;
            }
        }
        return false;
    }

}
