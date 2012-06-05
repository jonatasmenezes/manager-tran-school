package com.br.managertranschool.business.filter;

import java.io.Serializable;

import com.br.managertranschool.business.vo.EstadoVO;


/**
 * Classe de filtro de Estado
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 04/06/2012
 */
public class EstadoFilter  implements Serializable{


    private static final long serialVersionUID = 5158327595511725164L;
    private EstadoVO estado;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public EstadoFilter() {

        super();
    }

    /**
     * Construtor com argumento.
     * 
     * @param estado - Objeto {@link EstadoVO}
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public EstadoFilter(EstadoVO estado) {

        super();
        this.estado = estado;
    }

    /**
     * @return the estado
     */
    public EstadoVO getEstado() {

        return estado;
    }

    /**
     * @param estado - the estado to set
     */
    public void setEstado(EstadoVO estado) {

        this.estado = estado;
    }
}
