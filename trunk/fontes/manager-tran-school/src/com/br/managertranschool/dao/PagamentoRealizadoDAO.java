package com.br.managertranschool.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.managertranschool.architecture.BaseDAO;
import com.br.managertranschool.business.filter.PagamentoRealizadoFilter;
import com.br.managertranschool.business.vo.PagamentoRealizadoVO;


/**
 * Classe de acesso a dados da tabela PAGAMENTO_REALIZADO
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 06/06/2012
 */
public class PagamentoRealizadoDAO extends BaseDAO{

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public PagamentoRealizadoDAO() {

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

        super.table = PagamentoRealizadoVO.TABLE;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunaId()
     */
    @Override
    protected void setNomeColunaId() {

        super.colunaId = PagamentoRealizadoVO.ID_PAGAMENTO_REALIZADO;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunas()
     */
    @Override
    protected void setNomeColunas() {

        super.colunas = PagamentoRealizadoVO.getNomesColunas();
        
    }

    /**
     * Método obtem um objeto pagamentoRealizado através do cursor passado por parametro.
     * 
     * @param cursor -Objeto {@link Cursor}.
     * @return Objeto {@link PagamentoRealizadoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private PagamentoRealizadoVO getPagamentoRealizado(Cursor cursor) {

        PagamentoRealizadoVO pagamentoRealizado = new PagamentoRealizadoVO();
        pagamentoRealizado.setId(cursor.getLong(cursor.getColumnIndexOrThrow(PagamentoRealizadoVO.ID_PAGAMENTO_REALIZADO)));        
        pagamentoRealizado.setPagamentoId(cursor.getLong(cursor.getColumnIndex(PagamentoRealizadoVO.PAGAMENTO_ID)));
        pagamentoRealizado.setDataPagamento(super.obterDate(cursor.getString(cursor.getColumnIndex(PagamentoRealizadoVO.DT_PAGAMENTO))));
        pagamentoRealizado.setReferencia(super.obterDate(cursor.getString(cursor.getColumnIndex(PagamentoRealizadoVO.MES_ANO_REFERENTE))));
        
        
        return pagamentoRealizado;
    }
    
    /**
     * Método obtem um objeto ContentValues através da pagamentoRealizado passada por parametro.
     * 
     * @param PagamentoRealizado - Objeto {@link PagamentoRealizadoVO}.
     * @return Objeto {@link ContentValues}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private ContentValues getContentValues(PagamentoRealizadoVO pagamentoRealizado) {

        ContentValues values = new ContentValues();
        values.put(PagamentoRealizadoVO.PAGAMENTO_ID, pagamentoRealizado.getPagamentoId());
        values.put(PagamentoRealizadoVO.DT_PAGAMENTO, super.obterDateString(pagamentoRealizado.getDataPagamento())); 
        values.put(PagamentoRealizadoVO.MES_ANO_REFERENTE, super.obterDateString(pagamentoRealizado.getReferencia())); 

        return values;
    }
    
    /**
     * Método busca PagamentoRealizado por id.
     * 
     * @param id - Id da pagamentoRealizado.
     * @return Objeto {@link PagamentoRealizadoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public PagamentoRealizadoVO buscarPorId(Long id) {

        PagamentoRealizadoVO pagamentoRealizado = null;

        Cursor cursor = super.findById(id);

        if (cursor != null) {
            pagamentoRealizado = this.getPagamentoRealizado(cursor);
            cursor.close();
        }

        return pagamentoRealizado;

    }
    
    /**
     * Método obtem lista de pagamentoRealizado de acordo com o filtro.
     * 
     * @param filter - Filtro de PagamentoRealizado {@link PagamentoRealizadoFilter}.
     * @return - Lista de pagamentoRealizado.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<PagamentoRealizadoVO> pesquisar(PagamentoRealizadoFilter filter) {

        List<PagamentoRealizadoVO> pagamentoRealizadoList = new ArrayList<PagamentoRealizadoVO>();

        if (filter.getPagamentoRealizado() != null) {
            ContentValues values = new ContentValues();
            PagamentoRealizadoVO pagamentoRealizado;

            if (filter.getPagamentoRealizado().getId() != null) {
                values.put(PagamentoRealizadoVO.ID_PAGAMENTO_REALIZADO, filter.getPagamentoRealizado().getId());
            }
            
            if (filter.getPagamentoRealizado().getPagamentoId() != null) {
                values.put(PagamentoRealizadoVO.PAGAMENTO_ID, filter.getPagamentoRealizado().getPagamentoId());
            }
            
            if (filter.getPagamentoRealizado().getReferencia() != null) {
                values.put(PagamentoRealizadoVO.MES_ANO_REFERENTE, super.obterDateString(filter.getPagamentoRealizado().getReferencia()));
            }
            
            if (filter.getPagamentoRealizado().getDataPagamento() != null) {
                values.put(PagamentoRealizadoVO.DT_PAGAMENTO, super.obterDateString(filter.getPagamentoRealizado().getDataPagamento()));
            }
            
            
            Cursor cursor = super.pesquisar(values);

            if (cursor != null) {
                do {
                    pagamentoRealizado = this.getPagamentoRealizado(cursor);
                    pagamentoRealizadoList.add(pagamentoRealizado);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }

        return pagamentoRealizadoList;
    }
    
    /**
     * Método insere uma nova pagamentoRealizado na base.
     * 
     * @param pagamentoRealizado - Objeto {@link PagamentoRealizadoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(PagamentoRealizadoVO pagamentoRealizado) {

        ContentValues values = this.getContentValues(pagamentoRealizado);        
        pagamentoRealizado.setId(super.salvar(values));
    }

    /**
     * Método atualizar a pagamentoRealizado na base de acordo com o id.
     * 
     * @param pagamentoRealizado - Objeto {@link PagamentoRealizadoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void atualizar(PagamentoRealizadoVO pagamentoRealizado) throws Exception {

        ContentValues values = this.getContentValues(pagamentoRealizado);
        super.atualizar(pagamentoRealizado.getId(), values);        
    }
    
    /**
     * Método deleta PagamentoRealizado da base de acordo com o id.
     * 
     * @param id - Id da pagamentoRealizado.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void delete(Long id) throws Exception {   
        super.delete(id);
    }
        
    
}
