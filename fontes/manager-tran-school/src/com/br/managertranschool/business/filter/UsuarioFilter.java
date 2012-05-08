package com.br.managertranschool.business.filter;

import java.io.Serializable;

import com.br.managertranschool.business.vo.UsuarioVO;

/**
 * Classe de filtro de Usuario
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 07/05/2012
 */
public class UsuarioFilter implements Serializable {

    private static final long serialVersionUID = -3095974823561612768L;

    private UsuarioVO usuario;

    /**
     * Construtor padrão.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public UsuarioFilter() {

        super();
    }

    /**
     * Construtor com argumento.
     * 
     * @param usuario - Objeto {@link UsuarioVO}
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public UsuarioFilter(UsuarioVO usuario) {

        super();
        this.usuario = usuario;
    }

    /**
     * @return the usuario
     */
    public UsuarioVO getUsuario() {

        return usuario;
    }

    /**
     * @param usuario - the usuario to set
     */
    public void setUsuario(UsuarioVO usuario) {

        this.usuario = usuario;
    }
}
