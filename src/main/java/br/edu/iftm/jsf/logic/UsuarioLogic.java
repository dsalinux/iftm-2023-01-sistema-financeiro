package br.edu.iftm.jsf.logic;

import br.edu.iftm.jsf.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsuarioLogic implements GenericLogic<Usuario> {

    private List<Usuario> usuarios = new ArrayList<>();
    private Long id = 1L;
    
    @Override
    public Usuario salvar(Usuario entity) {
        entity.setId(id++);
        usuarios.add(entity);
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
