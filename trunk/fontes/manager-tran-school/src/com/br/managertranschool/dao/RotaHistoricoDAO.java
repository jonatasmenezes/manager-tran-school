package com.br.managertranschool.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.managertranschool.architecture.BaseDAO;
import com.br.managertranschool.business.filter.RotaHistoricoFilter;
import com.br.managertranschool.business.vo.ClienteVO;
import com.br.managertranschool.business.vo.RotaHistoricoVO;


/**
 * Classe de acesso a dados da tabela ROTAHISTORICO
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 05/06/2012
 */
public class RotaHistoricoDAO extends BaseDAO {

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaHistoricoDAO() {

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

        super.table = RotaHistoricoVO.TABLE;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunaId()
     */
    @Override
    protected void setNomeColunaId() {

        super.colunaId = RotaHistoricoVO.ID_ROTA_HISTORICO;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunas()
     */
    @Override
    protected void setNomeColunas() {

        super.colunas = RotaHistoricoVO.getNomesColunas();
        
    }

    /**
     * Método obtem um objeto rotaHistorico através do cursor passado por parametro.
     * 
     * @param cursor -Objeto {@link Cursor}.
     * @return Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private RotaHistoricoVO getRotaHistorico(Cursor cursor) {

        RotaHistoricoVO rotaHistorico = new RotaHistoricoVO();
        rotaHistorico.setId(cursor.getLong(cursor.getColumnIndexOrThrow(RotaHistoricoVO.ID_ROTA_HISTORICO)));
        rotaHistorico.setUsuarioId(cursor.getLong(cursor.getColumnIndex(RotaHistoricoVO.USUARIO_ID)));
        rotaHistorico.setRotaId(cursor.getLong(cursor.getColumnIndex(RotaHistoricoVO.ROTA_ID)));
        /*
         * Verificar qual get eu coloco pois esses campos são datas e não existe getData
         */
        rotaHistorico.setData(super.obterDate(cursor.getString(cursor.getColumnIndex(RotaHistoricoVO.DT_ROTA))));
        rotaHistorico.setDuracao(super.obterTime(cursor.getString(cursor.getColumnIndex(RotaHistoricoVO.DURACAO))));
        
        
        return rotaHistorico;
    }
    
    /**
     * Método obtem um objeto ContentValues através da rotaHistorico passada por parametro.
     * 
     * @param cliente - Objeto {@link RotaHistoricoVO}.
     * @return Objeto {@link ContentValues}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private ContentValues getContentValues(RotaHistoricoVO rotaHistorico) {

        ContentValues values = new ContentValues();
        values.put(RotaHistoricoVO.ROTA_ID, rotaHistorico.getRotaId());
        values.put(RotaHistoricoVO.USUARIO_ID, rotaHistorico.getUsuarioId());
        /*
         * problema com datas denovo
         **/
        values.put(RotaHistoricoVO.DT_ROTA, super.obterDateString(rotaHistorico.getData()));
        values.put(RotaHistoricoVO.DURACAO, super.obterTimeString(rotaHistorico.getDuracao()));
        
        return values;
    }
    
    /**
     * Método busca RotaHistorico por id.
     * 
     * @param id - Id da rotaHistorico.
     * @return Objeto {@link RotaHistoricoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaHistoricoVO buscarPorId(Long id) {

        RotaHistoricoVO rotaHistorico = null;

        Cursor cursor = super.findById(id);

        if (cursor != null) {
            rotaHistorico = this.getRotaHistorico(cursor);
            cursor.close();
        }

        super.dataBase.close();
        
        return rotaHistorico;

    }
    
    /**
     * Método obtem lista de rotaHistorico de acordo com o filtro.
     * 
     * @param filter - Filtro de RotaHistorico {@link RotaHistoricoFilter}.
     * @return - Lista de rotaHistorico.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<RotaHistoricoVO> pesquisar(RotaHistoricoFilter filter) {

        List<RotaHistoricoVO> rotaHistoricoList = new ArrayList<RotaHistoricoVO>();

        if (filter.getRotaHistorico() != null) {
            ContentValues values = new ContentValues();
            RotaHistoricoVO rotaHistorico;

            if (filter.getRotaHistorico().getId() != null) {
                values.put(RotaHistoricoVO.ID_ROTA_HISTORICO, filter.getRotaHistorico().getId());
            }
            
            if (filter.getRotaHistorico().getRotaId() != null) {
                values.put(RotaHistoricoVO.ROTA_ID, filter.getRotaHistorico().getRotaId());
            }
            
            if (filter.getRotaHistorico().getUsuarioId() != null) {
                values.put(RotaHistoricoVO.USUARIO_ID, filter.getRotaHistorico().getUsuarioId());
            }
            // datas again
            if (filter.getRotaHistorico().getData() != null) {
                values.put(RotaHistoricoVO.DT_ROTA, super.obterDateString(filter.getRotaHistorico().getData()));
            }
            
            if (filter.getRotaHistorico().getDuracao() != null) {
                values.put(RotaHistoricoVO.DURACAO, super.obterTimeString(filter.getRotaHistorico().getDuracao()));
            }
            
            Cursor cursor = super.pesquisar(values);

            if (cursor != null) {
                do {
                    rotaHistorico = this.getRotaHistorico(cursor);
                    rotaHistoricoList.add(rotaHistorico);
                } while (cursor.moveToNext());
                cursor.close();
            }
            
            super.dataBase.close();
        }

        return rotaHistoricoList;
    }
    
    /**
     * Método insere uma nova rotaHistorico na base.
     * 
     * @param rotaHistorico - Objeto {@link RotaHistoricoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(RotaHistoricoVO rotaHistorico) {

        ContentValues values = this.getContentValues(rotaHistorico);        
        rotaHistorico.setId(super.salvar(values));
        super.dataBase.close();
    }

    /**
     * Método atualizar a rotaHistorico na base de acordo com o id.
     * 
     * @param rotaHistorico - Objeto {@link RotaHistoricoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void atualizar(RotaHistoricoVO rotaHistorico) throws Exception {

        ContentValues values = this.getContentValues(rotaHistorico);
        super.atualizar(rotaHistorico.getId(), values); 
        super.dataBase.close();
    }
    
    /**
     * Método deleta RotaHistorico da base de acordo com o id.
     * 
     * @param id - Id da rotaHistorico.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void delete(Long id) throws Exception {   
        super.delete(id);
        super.dataBase.close();
    }
        
    
}
