package com.br.managertranschool.business.filter;

import java.io.Serializable;

import com.br.managertranschool.business.vo.RotaLocalidadeVO;


/**
 * Classe de filtro de RotaLocalidade
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 11/06/2012
 */
public class RotaLocalidadeFilter  implements Serializable{

    private static final long serialVersionUID = 2083200231088592111L;
    
    private RotaLocalidadeVO rotaLocalidade;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaLocalidadeFilter() {

        super();
    }

    /**
     * Construtor com argumento.
     * 
     * @param rotaLocalidade - Objeto {@link RotaLocalidadeVO}
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaLocalidadeFilter(RotaLocalidadeVO rotaLocalidade) {

        super();
        this.rotaLocalidade = rotaLocalidade;
    }

    /**
     * @return the rotaLocalidade
     */
    public RotaLocalidadeVO getRotaLocalidade() {

        return rotaLocalidade;
    }

    /**
     * @param rotaLocalidade - the rotaLocalidade to set
     */
    public void setRotaLocalidade(RotaLocalidadeVO rotaLocalidade) {

        this.rotaLocalidade = rotaLocalidade;
    }
}
