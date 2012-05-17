package com.br.managertranschool.business.vo;

import java.io.Serializable;

import com.br.managertranschool.business.list.TipoMensagemList;

/**
 * Classe VO responsável por conter as mensagens do aplicativo.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 16/05/2012
 */
public class MensagemVO implements Serializable {

    private static final long serialVersionUID = 6084749136059293184L;

    private Integer id;

    private Object[] parametros;

    private TipoMensagemList tipo;

    /**
     * Construtor padrão.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public MensagemVO() {

        super();
    }

    /**
     * Construtor utilizando id e tipo da mensagem.
     * 
     * @param id - Id da mensagem no resource.
     * @param tipo - Tipo de mensagem.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public MensagemVO(Integer id, TipoMensagemList tipo) {

        super();
        this.id = id;
        this.tipo = tipo;
    }

    /**
     * Construtor utilizando id, tipo de mensagem e parametros.
     * 
     * @param id - Id da mensagem no resource.
     * @param parametros - Parametros da mensagem.
     * @param tipo - Id da mensagem no resource.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public MensagemVO(Integer id, Object[] parametros, TipoMensagemList tipo) {

        super();
        this.id = id;
        this.parametros = parametros;
        this.tipo = tipo;
    }

    /**
     * @return the id
     */
    public Integer getId() {

        return id;
    }

    /**
     * @return the parametros
     */
    public Object[] getParametros() {

        return parametros;
    }

    /**
     * @return the tipo
     */
    public TipoMensagemList getTipo() {

        return tipo;
    }

}
