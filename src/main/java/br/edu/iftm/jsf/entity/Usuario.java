package br.edu.iftm.jsf.entity;

import java.io.Serializable;
import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author danilo
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    private String email;
    private String senha;

      
}
