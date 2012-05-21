package com.br.managertranschool.business.vo;

import java.io.Serializable;

/**
 * Classe VO responsável pela entidade de PAGAMENTO_REALIZADO.
 * 
 * @author Jeferson Almeida (jef.almeida.07@gmail.com)
 * @since 20/05/2012
 */
public class PagamentoRealizadoVO implements Serializable {

    public static final String TABLE = "PAGAMENTO_REALIZADO";
    
    public static final String ID_PAGAMENTO_REALIZADO = "ID_PAGAMENTO_REALIZADO";

    private Long id;

    public static final String PAGAMENTO_ID = "PAGAMENTO_ID";

    private Long pagamentoId;

    public static final String DT_PAGAMENTO = "DT_PAGAMENTO";

    private Date dataPagamento;
    
    public static final String MES_ANO_REFERENTE = "MES_ANO_REFERENTE";

    private Date referencia;
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public PagamentoRealizadoVO() {

        super();
    }

    /**
     * Método obtem nome das colunas da tabela PAGAMENTO_REALIZADO
     * 
     * @return Array de string
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public static final String[] getNomesColunas() {

        return new String[] { ID_PAGAMENTO_REALIZADO, PAGAMENTO_ID, DT_PAGAMENTO, MES_ANO_REFERENTE };
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
     * @return the pagamentoId
     */
    public Long getPagamentoId() {

        return pagamentoId;
    }

    /**
     * @param pagamentoId - the pagamentoId to set
     */
    public void setPagamentoId(Long pagamentoId) {

        this.pagamentoId = pagamentoId;
    }

    /**
     * @return the dataPagamento
     */
    public Date getDataPagamento() {

        return dataPagamento;
    }

    /**
     * @param dataPagamento - the dataPagamento to set
     */
    public void setDataPagamento(Date dataPagamento) {

        this.dataPagamento = dataPagamento;
    }

    /**
     * @return the referencia
     */
    public Date getReferencia() {

        return referencia;
    }

    /**
     * @param referencia - the referencia to set
     */
    public void setReferencia(Date referencia) {

        this.referencia = referencia;
    }
 
    

}
