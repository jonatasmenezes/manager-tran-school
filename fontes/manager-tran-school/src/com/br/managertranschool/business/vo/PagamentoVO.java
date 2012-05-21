package com.br.managertranschool.business.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe VO responsável pela entidade de PAGAMENTO.
 * 
 * @author Jeferson Almeida (jef.almeida.07@gmail.com)
 * @since 20/05/2012
 */
public class PagamentoVO implements Serializable {

    public static final String TABLE = "PAGAMENTO";
    
    private static final long serialVersionUID = -2318798736906167441L;
    
    public static final String ID_PAGAMENTO = "ID_PAGAMENTO";

    private Long id;

    public static final String CLIENTE_ID = "CLIENTE_ID";

    private Long clienteId;

    public static final String TIPO_PAGAMENTO = "TIPO_PAGAMENTO";

    private Integer tipoPagamento;
    
    public static final String VALOR = "VALOR";

    private Double valor; 
    
    public static final String DT_VENCIMENTO = "DT_VENCIMENTO";

    private Date vencimento;
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public PagamentoVO() {

        super();
    }

    /**
     * Método obtem nome das colunas da tabela PAGAMENTO
     * 
     * @return Array de string
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public static final String[] getNomesColunas() {

        return new String[] { ID_PAGAMENTO, CLIENTE_ID, TIPO_PAGAMENTO, VALOR, DT_VENCIMENTO };
    }

    /**
     * @return the id
     */
    public Long getId() {

        return id;
    }

    /**
     * @param id - the id to set
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * @return the clienteId
     */
    public Long getClienteId() {

        return clienteId;
    }

    /**
     * @param clienteId - the clienteId to set
     */
    public void setClienteId(Long clienteId) {

        this.clienteId = clienteId;
    }

    /**
     * @return the tipoPagamento
     */
    public Integer getTipoPagamento() {

        return tipoPagamento;
    }

    /**
     * @param tipoPagamento - the tipoPagamento to set
     */
    public void setTipoPagamento(Integer tipoPagamento) {

        this.tipoPagamento = tipoPagamento;
    }

    /**
     * @return the valor
     */
    public Double getValor() {

        return valor;
    }

    /**
     * @param valor - the valor to set
     */
    public void setValor(Double valor) {

        this.valor = valor;
    }
 
    /**
     * @return the vencimento
     */
    public Date getVencimento() {

        return vencimento;
    }

    /**
     * @param vencimento - the vencimento to set
     */
    public void setVencimento(Date vencimento) {

        this.vencimento = vencimento;
    }
 
    

}
