import java.io.*;
import java.util.ArrayList;

public class Usuario {
    private static final String FILE = "datos/Usuarios.txt";
    private static final ArrayList<Usuario> listaUsuarios = new ArrayList<>();
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

    public static void VerificarArchivo() {
        if (!file.exists()) {
            IO.println(Color.RED_BOLD+"Archivo no encontado"+Color.RESET);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
                bw.write(" ____________________________________________________________\n");
                bw.write(String.format("|%-22s %s %22s| %n","","LISTA USUARIOS",""));
                bw.write("|____________________________________________________________|\n");
                bw.write(String.format("|%-6s %s %6s|  %-2s %s %4s| %-4s %s %5s| %n","","NOMBRES","","","CONTRASEÑAS","","","TIPO",""));
                bw.write("|____________________________________________________________|\n");
                IO.println(Color.GREEN_BOLD+"Se ha creado el archivo: " + file.getName()+Color.RESET);
            } catch (IOException e) {
                System.out.println("No se pudo crear");
            }
        }
    }

    public static void CargarUsuarios() {
        listaUsuarios.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.replaceFirst("^\\|", "").replaceFirst("\\|$", "");
                String[] partes = linea.split("\\|");
                if (partes.length == 3) {
                    listaUsuarios.add(new Usuario(
                            partes[0].trim()
                            ,partes[1].trim()
                            ,partes[2].trim()
                    )); // parte[0] = nombre, parte[1] = contraseña, parte[2] = tipo
                }
            }
        } catch (IOException e) {
            IO.println(Color.RED_BOLD+"Error al leer usuarios: "+e.getMessage()+Color.RESET);
        }
    }

    public static void crearCuenta(String nombre, String contraseña, String tipo) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(String.format( "|%-20s |%-20s |%-15s |%n",nombre,contraseña,tipo));
            IO.println(Color.BLUE_BOLD+"****Cuenta creada correctamente****"+Color.RESET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean iniciarSesion(String nombre, String contraseña, String tipo) {
        CargarUsuarios();
        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre)&&
                    u.getContraseña().equals(contraseña)&&
                    u.getTipo().equals(tipo)){
                IO.println(Color.BLUE_BOLD+"****Iniciando sesion****"+Color.RESET);
                return true;
            }
        }
        IO.println(Color.RED_BOLD+"Usuario no encontrado, vuelve a intentarlo"+Color.RESET);
        return false;
    }

    public static boolean verificarExistente(String nombre, String contraseña, String tipo) {
        CargarUsuarios();
        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre)&&
                    u.getContraseña().equals(contraseña)&&
                    u.getTipo().equals(tipo)){
                return true;
            }
        }
        return false;
    }
}
