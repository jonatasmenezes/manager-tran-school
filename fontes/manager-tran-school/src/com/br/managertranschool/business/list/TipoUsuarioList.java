package com.br.managertranschool.business.list;

import com.br.managertranschool.R;


/**
 * Enumerador que define os tipos de usu�rios.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 08/05/2012
 */
public enum TipoUsuarioList {

    ADMINISTRADOR(1, R.string.tipo_usuario_administrador),
    PADRAO(2, R.string.tipo_usuario_padrao);

    private int codigo;

    private int nome;

    /**
     * @param codigo - C�digo do tipo
     * @param nome - Nome do tipo
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private TipoUsuarioList(int codigo, int nome) {

        this.codigo = codigo;
        this.nome = nome;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {

        return codigo;
    }

    /**
     * @return the nome
     */
    public int getNome() {

        return nome;
    }

}
