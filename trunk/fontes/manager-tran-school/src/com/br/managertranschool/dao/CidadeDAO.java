package com.br.managertranschool.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.managertranschool.architecture.BaseDAO;
import com.br.managertranschool.business.filter.CidadeFilter;
import com.br.managertranschool.business.vo.CidadeVO;


/**
 * Classe de acesso a dados da tabela CIDADE
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 05/06/2012
 */
public class CidadeDAO extends BaseDAO{

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public CidadeDAO() {

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

        super.table = CidadeVO.TABLE;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunaId()
     */
    @Override
    protected void setNomeColunaId() {

        super.colunaId = CidadeVO.ID_CIDADE;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunas()
     */
    @Override
    protected void setNomeColunas() {

        super.colunas = CidadeVO.getNomesColunas();
        
    }

    /**
     * Método obtem um objeto cidade através do cursor passado por parametro.
     * 
     * @param cursor -Objeto {@link Cursor}.
     * @return Objeto {@link CidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private CidadeVO getCidade(Cursor cursor) {

        CidadeVO cidade = new CidadeVO();
        cidade.setId(cursor.getLong(cursor.getColumnIndexOrThrow(CidadeVO.ID_CIDADE)));
        cidade.setDescricao(cursor.getString(cursor.getColumnIndex(CidadeVO.TX_DESCRICAO)));
        cidade.setEstadoId(cursor.getString(cursor.getColumnIndex(CidadeVO.ESTADO_ID)));
        
        return cidade;
    }
    
    /**
     * Método obtem um objeto ContentValues através da cidade passada por parametro.
     * 
     * @param Cidade - Objeto {@link CidadeVO}.
     * @return Objeto {@link ContentValues}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private ContentValues getContentValues(CidadeVO cidade) {

        ContentValues values = new ContentValues();
        values.put(CidadeVO.ESTADO_ID, cidade.getEstadoId());
        values.put(CidadeVO.TX_DESCRICAO, cidade.getDescricao());      

        return values;
    }
    
    /**
     * Método busca Cidade por id.
     * 
     * @param id - Id da cidade.
     * @return Objeto {@link CidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public CidadeVO buscarPorId(Long id) {

        CidadeVO cidade = null;

        Cursor cursor = super.findById(id);

        if (cursor != null) {
            cidade = this.getCidade(cursor);
            cursor.close();
        }

        super.dataBase.close();
        
        return cidade;

    }
    
    /**
     * Método obtem lista de cidade de acordo com o filtro.
     * 
     * @param filter - Filtro de Cidade {@link CidadeFilter}.
     * @return - Lista de cidade.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<CidadeVO> pesquisar(CidadeFilter filter) {

        List<CidadeVO> cidadeList = new ArrayList<CidadeVO>();

        if (filter.getCidade() != null) {
            ContentValues values = new ContentValues();
            CidadeVO cidade;

            if (filter.getCidade().getId() != null) {
                values.put(CidadeVO.ID_CIDADE, filter.getCidade().getId());
            }
            
            if (filter.getCidade().getDescricao() != null) {
                values.put(CidadeVO.TX_DESCRICAO, filter.getCidade().getDescricao());
            }
            
            if (filter.getCidade().getEstadoId() != null) {
                values.put(CidadeVO.ESTADO_ID, filter.getCidade().getEstadoId());
            }
            
            
            Cursor cursor = super.pesquisar(values);

            if (cursor != null) {
                do {
                    cidade = this.getCidade(cursor);
                    cidadeList.add(cidade);
                } while (cursor.moveToNext());
                cursor.close();
            }
            super.dataBase.close();
        }

        return cidadeList;
    }
    
    /**
     * Método insere uma nova cidade na base.
     * 
     * @param cidade - Objeto {@link CidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(CidadeVO cidade) {

        ContentValues values = this.getContentValues(cidade);        
        cidade.setId(super.salvar(values));
        super.dataBase.close();
    }

    /**
     * Método atualizar a cidade na base de acordo com o id.
     * 
     * @param cidade - Objeto {@link CidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void atualizar(CidadeVO cidade) throws Exception {

        ContentValues values = this.getContentValues(cidade);
        super.atualizar(cidade.getId(), values);
        super.dataBase.close();
    }
    
    /**
     * Método deleta Cidade da base de acordo com o id.
     * 
     * @param id - Id da cidade.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void delete(Long id) throws Exception {   
        super.delete(id);
        super.dataBase.close();
    }
        
    
}
