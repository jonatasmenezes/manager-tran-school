package com.br.managertranschool.business.vo;

import java.io.Serializable;

/**
 * Classe VO responsável pela entidade de CLIENTE_ROTA.
 * 
 * @author Jeferson Almeida (jef.almeida.07@gmail.com)
 * @since 19/05/2012
 */
public class ClienteRotaVO implements Serializable {

    private static final long serialVersionUID = 250730267995309544L;

    public static final String TABLE = "CLIENTE_ROTA";

    public static final String ROTA_ID = "ROTA_ID";

    private Long rotaId;

    public static final String CLIENTE_ID = "CLIENTE_ID";

    private Long clienteId;

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public ClienteRotaVO() {

        super();
    }

    /**
     * Método obtem nome das colunas da tabela CLIENTE_ROTA
     * 
     * @return Array de string
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public static final String[] getNomesColunas() {

        return new String[] { CLIENTE_ID, ROTA_ID };
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

    /**
     * @return the clienteId
     */
    public Long getClienteId() {

        return clienteId;
    }

    /**
     * @param clienteId - the clienteId to set
     */
    public void setClienteId(Long clienteId) {

        this.clienteId = clienteId;
    }

}
