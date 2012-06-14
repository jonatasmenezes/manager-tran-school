package com.br.managertranschool.business.vo;

import java.io.Serializable;

/**
 * Classe VO responsável pela entidade de CLIENTE_LOCALIDADE.
 * 
 * @author Jeferson Almeida (jef.almeida.07@gmail.com)
 * @since 19/05/2012
 */
public class ClienteLocalidadeVO implements Serializable {

    private static final long serialVersionUID = 8917598711802630236L;

    public static final String TABLE = "CLIENTE_LOCALIDADE";

    public static final String LOCALIDADE_ID = "LOCALIDADE_ID";

    private Long localidadeId;

    public static final String CLIENTE_ID = "CLIENTE_ID";

    private Long clienteId;

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public ClienteLocalidadeVO() {

        super();
    }
    
    /**
     * Construtor padrão com argumentos
     * 
     * @param clienteId - Id do cliente.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public ClienteLocalidadeVO(Long clienteId) {

        super();
        this.clienteId = clienteId;
    }

    /**
     * Método obtem nome das colunas da tabela CLIENTE_LOCALIDADE
     * 
     * @return Array de string
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public static final String[] getNomesColunas() {

        return new String[] { LOCALIDADE_ID, CLIENTE_ID };
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
