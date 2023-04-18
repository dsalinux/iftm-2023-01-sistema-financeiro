package br.edu.iftm.jsf.logic;

import br.edu.iftm.jsf.dao.UsuarioDAO;
import br.edu.iftm.jsf.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class UsuarioLogic implements GenericLogic<Usuario> {

    private List<Usuario> usuarios = new ArrayList<>();
    private Long id = 1L;
    
    @Inject
    private UsuarioDAO dao;
    
    @Override
    public Usuario salvar(Usuario entity) {
        dao.salvar(entity);
        return entity;
    }

    @Override
    public void remover(Usuario entity) {
        usuarios.remove(entity);
    }

    @Override
    public List<Usuario> listar() {
        return usuarios;
    }
    
}
