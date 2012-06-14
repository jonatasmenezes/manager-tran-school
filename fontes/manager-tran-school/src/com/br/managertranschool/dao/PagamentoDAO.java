package com.br.managertranschool.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.managertranschool.architecture.BaseDAO;
import com.br.managertranschool.business.filter.PagamentoFilter;
import com.br.managertranschool.business.vo.PagamentoVO;


/**
 * Classe de acesso a dados da tabela PAGAMENTO
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 06/06/2012
 */
public class PagamentoDAO extends BaseDAO{

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public PagamentoDAO() {

        super();
        this.setNomeTabela();
        this.setNomeColunaId();
        this.setNomeColunas();
    }
    
    
    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeTabela()
     */
    @Override
    protected void setNomeTabela() {

        super.table = PagamentoVO.TABLE;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunaId()
     */
    @Override
    protected void setNomeColunaId() {

        super.colunaId = PagamentoVO.ID_PAGAMENTO;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunas()
     */
    @Override
    protected void setNomeColunas() {

        super.colunas = PagamentoVO.getNomesColunas();
        
    }

    /**
     * Método obtem um objeto pagamento através do cursor passado por parametro.
     * 
     * @param cursor -Objeto {@link Cursor}.
     * @return Objeto {@link PagamentoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private PagamentoVO getPagamento(Cursor cursor) {

        PagamentoVO pagamento = new PagamentoVO();
        pagamento.setId(cursor.getLong(cursor.getColumnIndexOrThrow(PagamentoVO.ID_PAGAMENTO)));
        pagamento.setValor(new BigDecimal(cursor.getDouble(cursor.getColumnIndex(PagamentoVO.VALOR))));
        pagamento.setClienteId(cursor.getLong(cursor.getColumnIndex(PagamentoVO.CLIENTE_ID)));
        pagamento.setTipoPagamento(cursor.getInt(cursor.getColumnIndex(PagamentoVO.TIPO_PAGAMENTO)));
        pagamento.setVencimento(super.obterDate(cursor.getString(cursor.getColumnIndex(PagamentoVO.DT_VENCIMENTO))));
                    
        return pagamento;
    }
    
    /**
     * Método obtem um objeto ContentValues através da pagamento passada por parametro.
     * 
     * @param Pagamento - Objeto {@link PagamentoVO}.
     * @return Objeto {@link ContentValues}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private ContentValues getContentValues(PagamentoVO pagamento) {

        ContentValues values = new ContentValues();
        values.put(PagamentoVO.CLIENTE_ID, pagamento.getClienteId());
        values.put(PagamentoVO.DT_VENCIMENTO, super.obterDateString(pagamento.getVencimento()));
        values.put(PagamentoVO.VALOR, pagamento.getValor().doubleValue());
        values.put(PagamentoVO.TIPO_PAGAMENTO, pagamento.getTipoPagamento());
        
        return values;
    }
    
    /**
     * Método busca Pagamento por id.
     * 
     * @param id - Id da pagamento.
     * @return Objeto {@link PagamentoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public PagamentoVO buscarPorId(Long id) {

        PagamentoVO pagamento = null;

        Cursor cursor = super.findById(id);

        if (cursor != null) {
            pagamento = this.getPagamento(cursor);
            cursor.close();
        }

        super.dataBase.close();
        
        return pagamento;

    }
    
    /**
     * Método obtem lista de pagamento de acordo com o filtro.
     * 
     * @param filter - Filtro de Pagamento {@link PagamentoFilter}.
     * @return - Lista de pagamento.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<PagamentoVO> pesquisar(PagamentoFilter filter) {

        List<PagamentoVO> pagamentoList = new ArrayList<PagamentoVO>();

        if (filter.getPagamento() != null) {
            ContentValues values = new ContentValues();
            PagamentoVO pagamento;

            if (!super.isNullOrEmpty(filter.getPagamento().getId())) {
                values.put(PagamentoVO.ID_PAGAMENTO, filter.getPagamento().getId());
            }
            
            if (!super.isNullOrEmpty(filter.getPagamento().getClienteId())) {
                values.put(PagamentoVO.CLIENTE_ID, filter.getPagamento().getClienteId());
            }
            
            if (!super.isNullOrEmpty(filter.getPagamento().getTipoPagamento())) {
                values.put(PagamentoVO.TIPO_PAGAMENTO, filter.getPagamento().getTipoPagamento());
            }
            
            if (!super.isNullOrEmpty(filter.getPagamento().getValor())) {
                values.put(PagamentoVO.VALOR, filter.getPagamento().getValor().doubleValue());
            }
            
            if (!super.isNullOrEmpty(filter.getPagamento().getVencimento())) {
                values.put(PagamentoVO.DT_VENCIMENTO, super.obterDateString(filter.getPagamento().getVencimento()));
            }
            
            
            Cursor cursor = super.pesquisar(values);

            if (cursor != null) {
                do {
                    pagamento = this.getPagamento(cursor);
                    pagamentoList.add(pagamento);
                } while (cursor.moveToNext());
                cursor.close();
            }
            
            super.dataBase.close();
            
        }

        return pagamentoList;
    }
    
    /**
     * Método insere uma nova pagamento na base.
     * 
     * @param pagamento - Objeto {@link PagamentoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(PagamentoVO pagamento) {

        ContentValues values = this.getContentValues(pagamento);        
        pagamento.setId(super.salvar(values));
        super.dataBase.close();
    }

    /**
     * Método atualizar a pagamento na base de acordo com o id.
     * 
     * @param pagamento - Objeto {@link PagamentoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void atualizar(PagamentoVO pagamento) throws Exception {

        ContentValues values = this.getContentValues(pagamento);
        super.atualizar(pagamento.getId(), values);
        super.dataBase.close();
    }
    
    /**
     * Método deleta Pagamento da base de acordo com o id.
     * 
     * @param id - Id da pagamento.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void delete(Long id) throws Exception {   
        super.delete(id);
        super.dataBase.close();
    }
        
    
}
