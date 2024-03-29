package com.br.managertranschool.business.vo;

import java.io.Serializable;

/**
 * Classe VO respons�vel pela entidade de ESTADO.
 * 
 * @author Jeferson Almeida (jef.almeida.07@gmail.com)
 * @since 19/05/2012
 */
public class EstadoVO implements Serializable {

    private static final long serialVersionUID = 5215267636239840836L;

    public static final String TABLE = "ESTADO";

    public static final String ID_ESTADO = "ID_ESTADO";

    private String id;

    public static final String TX_DESCRICAO = "TX_DESCRICAO";

    private String descricao;

    /**
     * Construtor padr�o.
     * 
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public EstadoVO() {

        super();
    }

    /**
     * M�todo obtem nome das colunas da tabela ESTADO
     * 
     * @return Array de string
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public static final String[] getNomesColunas() {

        return new String[] { ID_ESTADO, TX_DESCRICAO };
    }

    /**
     * @return the id
     */
    public String getId() {

        return id;
    }

    /**
     * @param id - the id to set
     */
    public void setId(String id) {

        this.id = id;
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
