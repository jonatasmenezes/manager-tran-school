package com.br.managertranschool.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.managertranschool.architecture.BaseDAO;
import com.br.managertranschool.business.filter.EstadoFilter;
import com.br.managertranschool.business.vo.ClienteVO;
import com.br.managertranschool.business.vo.EstadoVO;


/**
 * Classe de acesso a dados da tabela ESTADO
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 04/06/2012
 */
public class EstadoDAO extends BaseDAO{

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public EstadoDAO() {

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

        super.table = EstadoVO.TABLE;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunaId()
     */
    @Override
    protected void setNomeColunaId() {

        super.colunaId = EstadoVO.ID_ESTADO;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunas()
     */
    @Override
    protected void setNomeColunas() {

        super.colunas = EstadoVO.getNomesColunas();
        
    }

    /**
     * Método obtem um objeto estado através do cursor passado por parametro.
     * 
     * @param cursor -Objeto {@link Cursor}.
     * @return Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private EstadoVO getEstado(Cursor cursor) {

        EstadoVO estado = new EstadoVO();
        estado.setId(cursor.getLong(cursor.getColumnIndexOrThrow(EstadoVO.ID_ESTADO)));
        estado.setDescricao(cursor.getString(cursor.getColumnIndex(EstadoVO.TX_DESCRICAO)));
        
        return estado;
    }
    
    /**
     * Método busca Estado por id.
     * 
     * @param id - Id da estado.
     * @return Objeto {@link EstadoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public EstadoVO buscarPorId(Long id) {

        EstadoVO estado = null;

        Cursor cursor = super.findById(id);

        if (cursor != null) {
            estado = this.getEstado(cursor);
            cursor.close();
        }

        super.dataBase.close();
        
        return estado;

    }
    
    /**
     * Método obtem lista de estado de acordo com o filtro.
     * 
     * @param filter - Filtro de Estado {@link EstadoFilter}.
     * @return - Lista de estado.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<EstadoVO> pesquisar(EstadoFilter filter) {

        List<EstadoVO> estadoList = new ArrayList<EstadoVO>();

        if (filter.getEstado() != null) {
            ContentValues values = new ContentValues();
            EstadoVO estado;

            if (filter.getEstado().getId() != null) {
                values.put(EstadoVO.ID_ESTADO, filter.getEstado().getId());
            }
            
            if (filter.getEstado().getDescricao() != null) {
                values.put(EstadoVO.TX_DESCRICAO, filter.getEstado().getDescricao());
            }
                    
            
            Cursor cursor = super.pesquisar(values);

            if (cursor != null) {
                do {
                    estado = this.getEstado(cursor);
                    estadoList.add(estado);
                } while (cursor.moveToNext());
                cursor.close();
            }
            
            super.dataBase.close();
        }

        return estadoList;
    }
}
