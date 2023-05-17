package br.edu.iftm.jsf.logic;

import br.edu.iftm.jsf.dao.UsuarioDAO;
import br.edu.iftm.jsf.entity.Usuario;
import br.edu.iftm.jsf.util.Transacional;
import br.edu.iftm.jsf.util.exception.ErroNegocioException;
import br.edu.iftm.jsf.util.exception.ErroSistemaException;
import java.util.List;
import javax.inject.Inject;

public class UsuarioLogic implements GenericLogic<Usuario> {

    @Inject
    private UsuarioDAO dao;
    
    @Override
    @Transacional
    public Usuario salvar(Usuario entity)  throws ErroNegocioException, ErroSistemaException {
        if(!"".equals(entity.getNovaSenha())) {
            String hash = entity.getNovaSenha();//criptografar a senha
            entity.setSenha(hash);
        }
        dao.salvar(entity);
        return entity;
    }

    @Override
    @Transacional
    public void remover(Usuario entity)  throws ErroNegocioException, ErroSistemaException{
        dao.deletar(entity.getId());
    }

    @Override
    public List<Usuario> listar()  throws ErroNegocioException, ErroSistemaException{
        return dao.listar();
    }
    
}
