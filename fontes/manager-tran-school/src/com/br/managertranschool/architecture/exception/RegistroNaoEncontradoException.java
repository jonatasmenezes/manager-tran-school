package com.br.managertranschool.architecture.exception;

/**
 * Classe de exceção para registro não encontrado na base.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 16/05/2012
 */
public class RegistroNaoEncontradoException extends Exception {

    private static final long serialVersionUID = 1L;

    private int idStringResource;

    /**
     * Construtor padrão.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public RegistroNaoEncontradoException() {

        super();
    }

    /**
     * Construtor utilizando argumentos.
     * 
     * @param idResource - Id da mensagem de detalhe.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public RegistroNaoEncontradoException(int idResource) {

        super();
        this.idStringResource = idResource;
    }

    /**
     * @return the idStringResource
     */
    public int getIdStringResource() {

        return idStringResource;
    }
}
