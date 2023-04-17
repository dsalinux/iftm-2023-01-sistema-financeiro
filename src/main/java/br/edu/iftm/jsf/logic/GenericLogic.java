package br.edu.iftm.jsf.logic;

import java.io.Serializable;
import java.util.List;

public interface GenericLogic<E> extends Serializable{
    
    public E salvar(E entity);
    public void remover(E entity);
    public List<E> listar();
    
}
