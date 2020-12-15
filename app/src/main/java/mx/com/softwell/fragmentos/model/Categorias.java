package mx.com.softwell.fragmentos.model;

public class Categorias {
    private int idJuego;
    private String nombre;
    private String imagen;

    public Categorias() {
    }

    public Categorias(int idJuego, String nombre, String imagen) {
        this.idJuego = idJuego;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
