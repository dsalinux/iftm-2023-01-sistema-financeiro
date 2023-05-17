package br.edu.iftm.jsf.bean;

import br.edu.iftm.jsf.entity.Produto;
import br.edu.iftm.jsf.logic.ProdutoLogic;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ProdutoBean extends GenericBean<Produto, ProdutoLogic>{

    @Inject
    private ProdutoLogic logic;
    
    @Override
    public ProdutoLogic getLogic() {
        return logic;
    }

    @Override
    public Class<Produto> getClassEntity() {
        return Produto.class;
    }
    
}
