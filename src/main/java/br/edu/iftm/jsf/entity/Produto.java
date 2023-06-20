package br.edu.iftm.jsf.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produto")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotEmpty
    private String nome;
    private String descricao;
    @NotNull
    @Min(value = 1)
    private BigDecimal valor;
    @NotNull
    @Column(name = "data_fabricacao")
    @Temporal(TemporalType.DATE)
    private Date dataFabricacao;
    
    @Column(name = "foto_disco")
    @Size(max = 300)
    private String fotoDisco;
    @Column(name = "foto_banco")
    private byte[] fotoBanco;
    
}
