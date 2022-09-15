package damex.com.damex.model;

import damex.com.damex.enumeration.ERol;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="roles")
public class Rol implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;
    @Enumerated(EnumType.STRING)
    private ERol rol;

    public ERol getRol() {
        return rol;
    }

    public void setRol(ERol rol) {
        this.rol = rol;
    }
    public Rol() {

    }
    public Rol(ERol rol) {
        this.rol = rol;
    }

    @Override
    public String getAuthority() {
        return rol.toString();
    }

}
