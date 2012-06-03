package com.br.managertranschool.business.filter;

import java.io.Serializable;

import com.br.managertranschool.business.vo.LocalidadeVO;


/**
 * Classe de filtro de Localidade
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 03/06/2012
 */
public class LocalidadeFilter  implements Serializable{

    private static final long serialVersionUID = -714018665004336810L;

    private LocalidadeVO localidade;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public LocalidadeFilter() {

        super();
    }

    /**
     * Construtor com argumento.
     * 
     * @param localidade - Objeto {@link LocalidadeVO}
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public LocalidadeFilter(LocalidadeVO localidade) {

        super();
        this.localidade = localidade;
    }

    /**
     * @return the localidade
     */
    public LocalidadeVO getLocalidade() {

        return localidade;
    }

    /**
     * @param localidade - the localidade to set
     */
    public void setLocalidade(LocalidadeVO localidade) {

        this.localidade = localidade;
    }
}
