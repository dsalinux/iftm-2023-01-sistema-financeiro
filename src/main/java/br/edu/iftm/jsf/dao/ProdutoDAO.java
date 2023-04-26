package br.edu.iftm.jsf.dao;

import br.edu.iftm.jsf.entity.Produto;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public class ProdutoDAO implements Serializable {
    
    @Inject
    private EntityManager manager;
    
    public Produto salvar(Produto produto){
        manager.getTransaction().begin();
        produto = manager.merge(produto);
        manager.getTransaction().commit();
        return produto;
    }
    
    public void deletar(Produto produto){
        manager.getTransaction().begin();
        produto = manager.find(Produto.class, produto.getId());
        manager.remove(produto);
        manager.getTransaction().commit();
    }
    
    public List<Produto> listar(){
        List<Produto> produtos;
        produtos = manager.createQuery("from Produto").getResultList();
        return produtos;
    }
    
}
