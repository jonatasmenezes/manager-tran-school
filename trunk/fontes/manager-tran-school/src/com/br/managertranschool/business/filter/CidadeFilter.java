package com.br.managertranschool.business.filter;

import java.io.Serializable;

import com.br.managertranschool.business.vo.CidadeVO;


/**
 * Classe de filtro de Cidade
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 04/06/2012
 */
public class CidadeFilter  implements Serializable{


    private static final long serialVersionUID = 2405273633586675256L;
    private CidadeVO cidade;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public CidadeFilter() {

        super();
    }

    /**
     * Construtor com argumento.
     * 
     * @param cidade - Objeto {@link CidadeVO}
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public CidadeFilter(CidadeVO cidade) {

        super();
        this.cidade = cidade;
    }

    /**
     * @return the cidade
     */
    public CidadeVO getCidade() {

        return cidade;
    }

    /**
     * @param cidade - the cidade to set
     */
    public void setEstado(CidadeVO cidade) {

        this.cidade = cidade;
    }
}
