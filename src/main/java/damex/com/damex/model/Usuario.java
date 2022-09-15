package damex.com.damex.model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name= "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
    private String password;
    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;
    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;
    @ManyToMany
    @JoinTable(name = "TB_RULES",joinColumns = @JoinColumn(name = "id_user"),inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private List<Rol> roles;


    //Constructors
    public Usuario(){}

    public Usuario(int id_user, String nombre, String email, String direccion, String telefono, String tipo, String password) {
        this.id_user = id_user;
        this.nombre = nombre;

        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.password = password;
    }

    //Getter and Setters
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id) {
        this.id_user = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
