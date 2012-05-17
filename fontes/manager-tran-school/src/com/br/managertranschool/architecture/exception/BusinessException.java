package com.br.managertranschool.architecture.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de exce��o para regras de neg�cio.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 16/05/2012
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;

    private List<Integer> idsStringsResource = new ArrayList<Integer>();

    /**
     * Construtor padr�o.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public BusinessException() {

        super();
    }

    /**
     * Construtor utilizando argumento.
     * 
     * @param idsResource - Lista com ids das mensagens.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public BusinessException(List<Integer> idsResource) {

        super();
        this.idsStringsResource = idsResource;
    }

    /**
     * @return the idsStringsResource
     */
    public List<Integer> getIdsStringsResource() {

        return idsStringsResource;
    }
}
