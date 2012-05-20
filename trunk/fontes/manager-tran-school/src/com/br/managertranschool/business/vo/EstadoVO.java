package com.br.managertranschool.business.vo;

import java.io.Serializable;

/**
 * Classe VO responsável pela entidade de ESTADO.
 * 
 * @author Jeferson Almeida (jef.almeida.07@gmail.com)
 * @since 19/05/2012
 */
public class EstadoVO implements Serializable {

    public static final String TABLE = "ESTADO";
    
    public static final String ID_ESTADO = "ID_ESTADO";

    private Long id;

    public static final String TX_ABREVIACAO = "TX_ABREVIACAO";

    private String abreviacao;

    public static final String TX_DESCRICAO = "TX_DESCRICAO";

    private String descricao;
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public EstadoVO() {

        super();
    }

    /**
     * Método obtem nome das colunas da tabela ESTADO
     * 
     * @return Array de string
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public static final String[] getNomesColunas() {

        return new String[] { ID_ESTADO, TX_ABREVIACAO, TX_DESCRICAO };
    }

    /**
     * @return the id
     */
    public Long getId() {

        return id;
    }

    /**
     * @param id - the id to set
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * @return the abreviacao
     */
    public String getAbreviacao() {

        return abreviacao;
    }

    /**
     * @param abreviacao - the abreviacao to set
     */
    public void setAbreviacao(String abreviacao) {

        this.abreviacao = abreviacao;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {

        return descricao;
    }

    /**
     * @param descricao - the descricao to set
     */
    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

 

}
