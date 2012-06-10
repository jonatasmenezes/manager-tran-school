package com.br.managertranschool.business.filter;

import java.io.Serializable;

import com.br.managertranschool.business.vo.PagamentoVO;


/**
 * Classe de filtro de Pagamento
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 06/06/2012
 */
public class PagamentoFilter  implements Serializable{


    private static final long serialVersionUID = 9047822537943039400L;
    private PagamentoVO pagamento;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public PagamentoFilter() {

        super();
    }

    /**
     * Construtor com argumento.
     * 
     * @param pagamento - Objeto {@link PagamentoVO}
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public PagamentoFilter(PagamentoVO pagamento) {

        super();
        this.pagamento = pagamento;
    }

    /**
     * @return the pagamento
     */
    public PagamentoVO getPagamento() {

        return pagamento;
    }

    /**
     * @param pagamento - the pagamento to set
     */
    public void setPagamento(PagamentoVO pagamento) {

        this.pagamento = pagamento;
    }
}
