package com.br.managertranschool.business.vo;

import java.io.Serializable;

/**
 * Classe VO responsável pela entidade de ROTA.
 * 
 * @author Jeferson Almeida (jef.almeida.07@gmail.com)
 * @since 19/05/2012
 */
public class RotaVO implements Serializable {

    public static final String TABLE = "ROTA";
    
    public static final String ID_ROTA = "ID_ROTA";

    private Long id;

    public static final String TX_DESCRICAO = "TX_DESCRICAO";

    private String descricao;
    
    public static final String LOCAL_PARTIDA = "LOCAL_PARTIDA";

    private Long localPartida;
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public RotaVO() {

        super();
    }

    /**
     * Método obtem nome das colunas da tabela ROTA
     * 
     * @return Array de string
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public static final String[] getNomesColunas() {

        return new String[] { ID_ROTA, TX_DESCRICAO, LOCAL_PARTIDA };
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

    /**
     * @return the localPartida
     */
    public Long getLocalPartida() {

        return localPartida;
    }

    /**
     * @param localPartida - the localPartida to set
     */
    public void setLocalPartida(Long localPartida) {

        this.localPartida = localPartida;
    }

}
