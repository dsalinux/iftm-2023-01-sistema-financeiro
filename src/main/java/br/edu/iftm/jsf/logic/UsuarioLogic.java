package br.edu.iftm.jsf.logic;

import br.edu.iftm.jsf.dao.UsuarioDAO;
import br.edu.iftm.jsf.entity.Usuario;
import br.edu.iftm.jsf.util.Assert;
import br.edu.iftm.jsf.util.HashUtil;
import br.edu.iftm.jsf.util.Transacional;
import br.edu.iftm.jsf.util.exception.ErroNegocioException;
import br.edu.iftm.jsf.util.exception.ErroSistemaException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class UsuarioLogic implements GenericLogic<Usuario> {

    @Inject
    private UsuarioDAO dao;
    
    @Override
    @Transacional
    public Usuario salvar(Usuario entity)  throws ErroNegocioException, ErroSistemaException {
        if(Assert.isStringEmpty(entity.getNome())) {
            throw new ErroNegocioException("O nome é obrigatório");
        }
        if(!Assert.isValidEmail(entity.getEmail())) {
            throw new ErroNegocioException("Email inválido.");
        }
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException ex) {
            throw new ErroSistemaException("Erro de segurança. Algorítimo SHA1PRNG não disponível.", ex);
        }
        entity.setSalt(secureRandom.nextLong()+"");
        if((entity.getSenha() == null || "".equals(entity.getSenha())) && (entity.getNovaSenha() == null || "".equals(entity.getNovaSenha().trim()))){
            throw new ErroNegocioException("Senha deve ser preenchida.");
        }
        if(!"".equals(entity.getNovaSenha())) {
            String hash = entity.getNovaSenha() + entity.getSalt();
            hash = HashUtil.sha256Hex(hash);
            entity.setSenha(hash);
        }
        if(entity.getDataCadastro() == null) {
            entity.setDataCadastro(new Date());
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

    public Usuario logar(String email, String senha)  throws ErroNegocioException{
        Usuario usuario  = dao.findUsuarioByEmail(email);
        String hash = senha + usuario.getSalt();
        hash = HashUtil.sha256Hex(hash);
        if(!usuario.getSenha().equals(hash)) {
            throw new ErroNegocioException("Usuário ou senha inválidos");
        }
        return usuario;
    }
    
}
