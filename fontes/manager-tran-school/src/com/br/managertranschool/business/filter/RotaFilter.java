package com.br.managertranschool.business.filter;

import java.io.Serializable;

import com.br.managertranschool.business.vo.RotaVO;


/**
 * Classe de filtro de Rota
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 03/06/2012
 */
public class RotaFilter  implements Serializable{

    private static final long serialVersionUID = -4582339798666753057L;

    private RotaVO rota;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaFilter() {

        super();
    }

    /**
     * Construtor com argumento.
     * 
     * @param rota - Objeto {@link RotaVO}
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaFilter(RotaVO rota) {

        super();
        this.rota = rota;
    }

    /**
     * @return the rota
     */
    public RotaVO getRota() {

        return rota;
    }

    /**
     * @param rota - the rota to set
     */
    public void setRota(RotaVO rota) {

        this.rota = rota;
    }
}
