package com.br.managertranschool.business.filter;

import java.io.Serializable;

import com.br.managertranschool.business.vo.PagamentoRealizadoVO;


/**
 * Classe de filtro de PagamentoRealizado
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 04/06/2012
 */
public class PagamentoRealizadoFilter  implements Serializable{


    private static final long serialVersionUID = 2405273633586675256L;
    private PagamentoRealizadoVO pagamentoRealizado;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public PagamentoRealizadoFilter() {

        super();
    }

    /**
     * Construtor com argumento.
     * 
     * @param pagamentoRealizado - Objeto {@link PagamentoRealizadoVO}
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public PagamentoRealizadoFilter(PagamentoRealizadoVO pagamentoRealizado) {

        super();
        this.pagamentoRealizado = pagamentoRealizado;
    }

    /**
     * @return the pagamentoRealizado
     */
    public PagamentoRealizadoVO getPagamentoRealizado() {

        return pagamentoRealizado;
    }

    /**
     * @param pagamentoRealizado - the pagamentoRealizado to set
     */
    public void setEstado(PagamentoRealizadoVO pagamentoRealizado) {

        this.pagamentoRealizado = pagamentoRealizado;
    }
}
