package com.br.managertranschool.business.filter;

import java.io.Serializable;

import com.br.managertranschool.business.vo.ClienteRotaVO;


/**
 * Classe de filtro de ClienteRota
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 11/06/2012
 */
public class ClienteRotaFilter  implements Serializable{

    private static final long serialVersionUID = -6723762705290075055L;
    
    private ClienteRotaVO clienteRota;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteRotaFilter() {

        super();
    }

    /**
     * Construtor com argumento.
     * 
     * @param clienteRota - Objeto {@link ClienteRotaVO}
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteRotaFilter(ClienteRotaVO clienteRota) {

        super();
        this.clienteRota = clienteRota;
    }

    /**
     * @return the clienteRota
     */
    public ClienteRotaVO getClienteRota() {

        return clienteRota;
    }

    /**
     * @param clienteRota - the clienteRota to set
     */
    public void setClienteRota(ClienteRotaVO clienteRota) {

        this.clienteRota = clienteRota;
    }
}
