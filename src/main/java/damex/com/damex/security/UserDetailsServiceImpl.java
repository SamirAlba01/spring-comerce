package damex.com.damex.security;

import damex.com.damex.model.Rol;
import damex.com.damex.model.Usuario;
import damex.com.damex.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario=usuarioRepository.findByEmail(username).orElseThrow();
        List<Rol> x=usuario.getRoles();
        for (Rol rol:x){
            System.out.println(rol.getRol().toString());
        }
        return new User(usuario.getNombre(),usuario.getPassword(),usuario.getAuthorities());
    }
}
