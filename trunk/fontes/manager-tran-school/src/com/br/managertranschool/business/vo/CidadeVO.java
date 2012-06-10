package com.br.managertranschool.business.vo;

import java.io.Serializable;

/**
 * Classe VO responsável pela entidade de CIDADE.
 * 
 * @author Jeferson Almeida (jef.almeida.07@gmail.com)
 * @since 19/05/2012
 */
public class CidadeVO implements Serializable {

    private static final long serialVersionUID = -5214794343627284854L;

    public static final String TABLE = "CIDADE";

    public static final String ID_CIDADE = "ID_CIDADE";

    private Long id;

    public static final String ESTADO_ID = "ESTADO_ID";

    private Long estadoId;

    public static final String TX_DESCRICAO = "TX_DESCRICAO";

    private String descricao;

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public CidadeVO() {

        super();
    }

    /**
     * Construtor padrão com argumentos.
     * 
     * @param estadoId - Id do estado.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public CidadeVO(Long estadoId) {

        super();
        this.estadoId = estadoId;
    }
    
    /**
     * Método obtem nome das colunas da tabela CIDADE
     * 
     * @return Array de string
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public static final String[] getNomesColunas() {

        return new String[] { ID_CIDADE, ESTADO_ID, TX_DESCRICAO };
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
     * @return the estadoId
     */
    public Long getEstadoId() {

        return estadoId;
    }

    /**
     * @param estadoId - the estadoId to set
     */
    public void setEstadoId(Long estadoId) {

        this.estadoId = estadoId;
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
