package br.edu.iftm.jsf.dao;

import br.edu.iftm.jsf.entity.Usuario;
import java.util.List;
import javax.persistence.Query;

public class UsuarioDAO extends GenericDAO<Usuario, Long>{

    @Override
    public Class<Usuario> getClassEntity() {
        return Usuario.class;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = super.listar();
        for (Usuario usuario : usuarios) {
            usuario.getPermissaoList().size();
        }
        return usuarios;
    }
    
    public Usuario findUsuarioByEmail(String email) {
        Query query = getManager().createQuery("from Usuario where email = :email");
        query.setParameter("email", email);
        return (Usuario) query.getSingleResult();
    }
  
    
}
