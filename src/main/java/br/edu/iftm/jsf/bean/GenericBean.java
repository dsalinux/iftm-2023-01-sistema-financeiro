package br.edu.iftm.jsf.bean;

import br.edu.iftm.jsf.entity.Usuario;
import br.edu.iftm.jsf.logic.GenericLogic;
import br.edu.iftm.jsf.logic.UsuarioLogic;
import br.edu.iftm.jsf.util.BeanUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import lombok.Getter;

public abstract class GenericBean<E, L extends GenericLogic<E>> extends BeanUtil{
    
    @Getter
    private E entity;
    @Getter
    private List<E> entitys;
    
    @Getter
    private Estado estado = Estado.PESQUISANDO;
       
    enum Estado {
        CRIANDO,
        EDITANDO,
        PESQUISANDO
    }
    
    public void newInstanceOfEntity(){
        try {
            entity = (E) getClassEntity().getConstructors()[0].newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GenericBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void novo(){
        newInstanceOfEntity();
        estado = Estado.CRIANDO;
    }
  
    public void salvar(){
        getLogic().salvar(entity);
        addInfo("Salvo com sucesso.");
        newInstanceOfEntity();
        estado = Estado.PESQUISANDO;
    }
    
    
    public void listar(){
        estado = Estado.PESQUISANDO;
        entitys = getLogic().listar();
    }
    
    public void remover(E entity){
        getLogic().remover(entity);
        addInfo("Removido com sucesso.");
    }
    public void editar(E entity) {
        this.entity = entity;
        estado = Estado.EDITANDO;
    }
    public abstract L getLogic();
    public abstract Class<E> getClassEntity();
}
