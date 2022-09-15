package damex.com.damex.model;

import javax.persistence.*;

@Entity
@Table(name="detalles")
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private double cantidad;
    private double precio;
    private double total;
    @ManyToOne
    private Orden orden;

    @ManyToOne
    private Producto producto;
    //Constructors

    public DetalleOrden(){}
    public DetalleOrden(int id, String nombre, double cantidad, double precio, double total) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }
    public DetalleOrden(Producto producto,Integer cantidad){
        this.nombre=producto.getNombre();
        this.cantidad=cantidad;
        this.producto=producto;
        this.precio=producto.getPrecio();
        this.total =precio*cantidad;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "DetalleOrden{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", tota=" + total +
                ", orden=" + orden +
                ", producto=" + producto +
                '}';
    }
}
