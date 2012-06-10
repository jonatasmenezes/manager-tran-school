package com.br.managertranschool.business.filter;

import java.io.Serializable;

import com.br.managertranschool.business.vo.ClienteLocalidadeVO;


/**
 * Classe de filtro de ClienteLocalidade
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 09/06/2012
 */
public class ClienteLocalidadeFilter  implements Serializable{

    private static final long serialVersionUID = 5472616693747657589L;
    
    private ClienteLocalidadeVO clienteLocalidade;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteLocalidadeFilter() {

        super();
    }

    /**
     * Construtor com argumento.
     * 
     * @param clienteLocalidade - Objeto {@link ClienteLocalidadeVO}
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteLocalidadeFilter(ClienteLocalidadeVO clienteLocalidade) {

        super();
        this.clienteLocalidade = clienteLocalidade;
    }

    /**
     * @return the clienteLocalidade
     */
    public ClienteLocalidadeVO getClienteLocalidade() {

        return clienteLocalidade;
    }

    /**
     * @param clienteLocalidade - the clienteLocalidade to set
     */
    public void setClienteLocalidade(ClienteLocalidadeVO clienteLocalidade) {

        this.clienteLocalidade = clienteLocalidade;
    }
}
