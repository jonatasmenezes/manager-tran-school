package com.br.managertranschool.business.filter;

import java.io.Serializable;

import com.br.managertranschool.business.vo.RotaHistoricoVO;


/**
 * Classe de filtro de RotaHistorico
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 03/06/2012
 */
public class RotaHistoricoFilter  implements Serializable{

    
    private static final long serialVersionUID = -2315673777428194178L;
    
    private RotaHistoricoVO rotaHistorico;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaHistoricoFilter() {

        super();
    }

    /**
     * Construtor com argumento.
     * 
     * @param rotaHistorico - Objeto {@link RotaHistoricoVO}
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaHistoricoFilter(RotaHistoricoVO rotaHistorico) {

        super();
        this.rotaHistorico = rotaHistorico;
    }

    /**
     * @return the rotaHistorico
     */
    public RotaHistoricoVO getRotaHistorico() {

        return rotaHistorico;
    }

    /**
     * @param rotaHistorico - the rotaHistorico to set
     */
    public void setRotaHistorico(RotaHistoricoVO rotaHistorico) {

        this.rotaHistorico = rotaHistorico;
    }
}
