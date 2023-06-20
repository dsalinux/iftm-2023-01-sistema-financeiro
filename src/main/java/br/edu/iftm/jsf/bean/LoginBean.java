package br.edu.iftm.jsf.bean;

import br.edu.iftm.jsf.entity.Usuario;
import br.edu.iftm.jsf.logic.UsuarioLogic;
import br.edu.iftm.jsf.util.BeanUtil;
import br.edu.iftm.jsf.util.exception.ErroNegocioException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
@Getter @Setter
public class LoginBean extends BeanUtil {
    
    private String email;
    private String senha;
    
    @Inject
    private UsuarioLogic logic;
    
    public String logar(){
        try {
            Usuario usuario = logic.logar(email, senha);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .put("usuarioLogado", usuario);
            return "/index?faces-redirect=true";
        } catch(ErroNegocioException ex) {
            addError(ex.getMessage());//TODO: Adicionar erroNegocio na mensagem
            return "login?faces-redirect=true";
        }
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }
    
}
