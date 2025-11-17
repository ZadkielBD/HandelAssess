public class Usuario {

    private String Nombre;
    private String contrasena;
    private String tipo;

    public Usuario(String nombre, String contrasena, String tipo) {
        this.tipo = tipo;
        this.contrasena = contrasena;
        Nombre = nombre;
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
}
