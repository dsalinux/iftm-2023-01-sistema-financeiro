package br.edu.iftm.jsf.dao;

import br.edu.iftm.jsf.entity.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Long>{

    @Override
    public Class<Usuario> getClassEntity() {
        return Usuario.class;
    }
  
    
}
