package com.br.managertranschool.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.managertranschool.architecture.BaseDAO;
import com.br.managertranschool.business.filter.RotaFilter;
import com.br.managertranschool.business.vo.ClienteVO;
import com.br.managertranschool.business.vo.RotaVO;


/**
 * Classe de acesso a dados da tabela ROTA
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 03/06/2012
 */
public class RotaDAO extends BaseDAO{

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaDAO() {

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

        super.table = RotaVO.TABLE;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunaId()
     */
    @Override
    protected void setNomeColunaId() {

        super.colunaId = RotaVO.ID_ROTA;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunas()
     */
    @Override
    protected void setNomeColunas() {

        super.colunas = RotaVO.getNomesColunas();
        
    }

    /**
     * Método obtem um objeto rota através do cursor passado por parametro.
     * 
     * @param cursor -Objeto {@link Cursor}.
     * @return Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private RotaVO getRota(Cursor cursor) {

        RotaVO rota = new RotaVO();
        rota.setId(cursor.getLong(cursor.getColumnIndexOrThrow(RotaVO.ID_ROTA)));
        rota.setDescricao(cursor.getString(cursor.getColumnIndex(RotaVO.TX_DESCRICAO)));
        rota.setLocalPartida(cursor.getLong(cursor.getColumnIndex(RotaVO.LOCAL_PARTIDA)));
        
        return rota;
    }
    
    /**
     * Método obtem um objeto ContentValues através da rota passada por parametro.
     * 
     * @param cliente - Objeto {@link RotaVO}.
     * @return Objeto {@link ContentValues}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private ContentValues getContentValues(RotaVO rota) {

        ContentValues values = new ContentValues();
        values.put(RotaVO.LOCAL_PARTIDA, rota.getLocalPartida());
        values.put(RotaVO.TX_DESCRICAO, rota.getDescricao());      

        return values;
    }
    
    /**
     * Método busca Rota por id.
     * 
     * @param id - Id da rota.
     * @return Objeto {@link RotaVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaVO buscarPorId(Long id) {

        RotaVO rota = null;

        Cursor cursor = super.findById(id);

        if (cursor != null) {
            rota = this.getRota(cursor);
            cursor.close();
        }

        return rota;

    }
    
    /**
     * Método obtem lista de rota de acordo com o filtro.
     * 
     * @param filter - Filtro de Rota {@link RotaFilter}.
     * @return - Lista de rota.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<RotaVO> pesquisar(RotaFilter filter) {

        List<RotaVO> rotaList = new ArrayList<RotaVO>();

        if (filter.getRota() != null) {
            ContentValues values = new ContentValues();
            RotaVO rota;

            if (filter.getRota().getId() != null) {
                values.put(RotaVO.ID_ROTA, filter.getRota().getId());
            }
            
            if (filter.getRota().getDescricao() != null) {
                values.put(RotaVO.TX_DESCRICAO, filter.getRota().getDescricao());
            }
            
            if (filter.getRota().getLocalPartida() != null) {
                values.put(RotaVO.LOCAL_PARTIDA, filter.getRota().getLocalPartida());
            }
            
            
            Cursor cursor = super.pesquisar(values);

            if (cursor != null) {
                do {
                    rota = this.getRota(cursor);
                    rotaList.add(rota);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }

        return rotaList;
    }
    
    /**
     * Método insere uma nova rota na base.
     * 
     * @param rota - Objeto {@link RotaVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(RotaVO rota) {

        ContentValues values = this.getContentValues(rota);        
        rota.setId(super.salvar(values));
    }

    /**
     * Método atualizar a rota na base de acordo com o id.
     * 
     * @param rota - Objeto {@link RotaVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void atualizar(RotaVO rota) throws Exception {

        ContentValues values = this.getContentValues(rota);
        super.atualizar(rota.getId(), values);        
    }
    
    /**
     * Método deleta Rota da base de acordo com o id.
     * 
     * @param id - Id da rota.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void delete(Long id) throws Exception {   
        super.delete(id);
    }
        
    
}
