package br.edu.iftm.jsf.logic;

import br.edu.iftm.jsf.dao.ProdutoDAO;
import br.edu.iftm.jsf.entity.Produto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ProdutoLogic implements GenericLogic<Produto> {


    @Inject
    private ProdutoDAO dao;
    
    @Override
    public Produto salvar(Produto entity) {
        entity = dao.salvar(entity);
        return entity;
    }

    @Override
    public void remover(Produto entity) {
        dao.deletar(entity);
    }

    @Override
    public List<Produto> listar() {
        return dao.listar();
    }
    
}
