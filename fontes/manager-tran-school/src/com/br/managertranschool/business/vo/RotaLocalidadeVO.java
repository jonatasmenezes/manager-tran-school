package com.br.managertranschool.business.vo;

import java.io.Serializable;

/**
 * Classe VO responsável pela entidade de ROTA_LOCALIDADE.
 * 
 * @author Jeferson Almeida (jef.almeida.07@gmail.com)
 * @since 19/05/2012
 */
public class RotaLocalidadeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String TABLE = "ROTA_LOCALIDADE";

    public static final String LOCALIDADE_ID = "LOCALIDADE_ID";

    private Long localidadeId;

    public static final String ROTA_ID = "ROTA_ID";

    private Long rotaId;

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public RotaLocalidadeVO() {

        super();
    }

    /**
     * Método obtem nome das colunas da tabela ROTA_LOCALIDADE
     * 
     * @return Array de string
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public static final String[] getNomesColunas() {

        return new String[] { LOCALIDADE_ID, ROTA_ID };
    }

    /**
     * @return the localidadeId
     */
    public Long getLocalidadeId() {

        return localidadeId;
    }

    /**
     * @param localidadeId - the localidadeId to set
     */
    public void setLocalidadeId(Long localidadeId) {

        this.localidadeId = localidadeId;
    }

    /**
     * @return the rotaId
     */
    public Long getRotaId() {

        return rotaId;
    }

    /**
     * @param rotaId - the rotaId to set
     */
    public void setRotaId(Long rotaId) {

        this.rotaId = rotaId;
    }

}
