package br.edu.iftm.jsf.bean;

import br.edu.iftm.jsf.entity.Usuario;
import br.edu.iftm.jsf.logic.UsuarioLogic;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class UsuarioBean extends GenericBean<Usuario, UsuarioLogic> {
    
    @Inject
    private UsuarioLogic logic;

    @Override
    public UsuarioLogic getLogic() {
        return logic;
    }


    @Override
    public Class<Usuario> getClassEntity() {
        return Usuario.class;
    }
}
