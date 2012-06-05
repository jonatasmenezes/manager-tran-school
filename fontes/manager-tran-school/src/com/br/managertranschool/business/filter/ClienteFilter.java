package com.br.managertranschool.business.filter;

import java.io.Serializable;

import com.br.managertranschool.business.vo.ClienteVO;


/**
 * Classe de filtro de Cliente
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 03/06/2012
 */
public class ClienteFilter implements Serializable{

    private static final long serialVersionUID = -621844318171851775L;
    
    private ClienteVO cliente;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteFilter() {

        super();
    }
    
    /**
     * Construtor com argumento.
     * 
     * @param cliente - Objeto {@link ClienteVO}
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteFilter(ClienteVO cliente) {

        super();
        this.cliente = cliente;
    }
    
    /**
     * @return the cliente
     */
    public ClienteVO getCliente() {

        return cliente;
    }

    /**
     * @param cliente - the cliente to set
     */
    public void setCliente(ClienteVO cliente) {

        this.cliente = cliente;
    }


}
