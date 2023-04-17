package br.edu.iftm.jsf.bean;

import br.edu.iftm.jsf.entity.Usuario;
import br.edu.iftm.jsf.logic.GenericLogic;
import br.edu.iftm.jsf.logic.UsuarioLogic;
import br.edu.iftm.jsf.util.BeanUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;

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
