package br.edu.iftm.jsf.bean;

import br.edu.iftm.jsf.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Getter;

@Named
@SessionScoped
public class UsuarioBean implements Serializable {

    @Getter
    private Usuario usuario;
    @Getter
    private List<Usuario> usuarios;
    private Long id = 1L;
    
    public UsuarioBean(){
        usuario = new Usuario();
        usuarios = new ArrayList<>();
    }
    
    public void salvar(){
        usuario.setId(id++);
        usuarios.add(usuario);
        usuario = new Usuario();
        FacesContext.getCurrentInstance()
            .addMessage("", new FacesMessage("Salvo com sucesso"));
    }
    
    public void remover(Usuario usuario){
        usuarios.remove(usuario);
        FacesContext.getCurrentInstance()
            .addMessage("", new FacesMessage("Removido"));
    }
    
}
