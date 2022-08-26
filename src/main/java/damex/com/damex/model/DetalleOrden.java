package damex.com.damex.model;

public class DetalleOrden {
    private int id;
    private String nombre;
    private double cantidad;
    private double precio;
    private double tota;
    //Constructors

    public DetalleOrden(){}
    public DetalleOrden(int id, String nombre, double cantidad, double precio, double tota) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.tota = tota;
    }
    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTota() {
        return tota;
    }

    public void setTota(double tota) {
        this.tota = tota;
    }
}
