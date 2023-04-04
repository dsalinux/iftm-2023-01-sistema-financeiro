package br.edu.iftm.jsf.entity;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author danilo
 */
@Data
public class Usuario implements Serializable {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    
}
