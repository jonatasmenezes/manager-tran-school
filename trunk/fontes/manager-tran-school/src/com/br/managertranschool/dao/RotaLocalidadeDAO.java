package com.br.managertranschool.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.managertranschool.architecture.BaseDAO;
import com.br.managertranschool.business.filter.RotaLocalidadeFilter;
import com.br.managertranschool.business.vo.RotaLocalidadeVO;
import com.br.managertranschool.business.vo.ClienteVO;


/**
 * Classe de acesso a dados da tabela ROTA_LOCALIDADE
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 11/06/2012
 */
public class RotaLocalidadeDAO extends BaseDAO{

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaLocalidadeDAO() {

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

        super.table = RotaLocalidadeVO.TABLE;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunaId()
     */
    @Override
    protected void setNomeColunaId() {

        super.colunaId = "";
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunas()
     */
    @Override
    protected void setNomeColunas() {

        super.colunas = RotaLocalidadeVO.getNomesColunas();
        
    }
    
    /**
     * Método obtem um objeto rotaLocalidade através do cursor passado por parametro.
     * 
     * @param cursor -Objeto {@link Cursor}.
     * @return Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private RotaLocalidadeVO getRotaLocalidade(Cursor cursor) {

        RotaLocalidadeVO rotaLocalidade = new RotaLocalidadeVO();
        rotaLocalidade.setLocalidadeId(cursor.getLong(cursor.getColumnIndexOrThrow(RotaLocalidadeVO.LOCALIDADE_ID)));
        rotaLocalidade.setRotaId(cursor.getLong(cursor.getColumnIndex(RotaLocalidadeVO.ROTA_ID)));
        
        return rotaLocalidade;
    }
    
    /**
     * Método obtem um objeto ContentValues através da rotaLocalidade passada por parametro.
     * 
     * @param cliente - Objeto {@link RotaLocalidadeVO}.
     * @return Objeto {@link ContentValues}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private ContentValues getContentValues(RotaLocalidadeVO rotaLocalidade) {

        ContentValues values = new ContentValues();
        values.put(RotaLocalidadeVO.LOCALIDADE_ID, rotaLocalidade.getLocalidadeId());
        values.put(RotaLocalidadeVO.ROTA_ID, rotaLocalidade.getRotaId());      

        return values;
    }
    
    /**
     * Método obtem lista de Cliente-Localidade de acordo com o filtro.
     * 
     * @param filter - Filtro de Cliente-Localidade {@link RotaLocalidadeFilter}.
     * @return - Lista de entidades.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public List<RotaLocalidadeVO> pesquisar(RotaLocalidadeFilter filter) {

        List<RotaLocalidadeVO> rotaLocalidadeList = new ArrayList<RotaLocalidadeVO>();

        if (filter.getRotaLocalidade() != null) {
            ContentValues values = new ContentValues();
            RotaLocalidadeVO rotaLocalidade;

            if (!super.isNullOrEmpty(filter.getRotaLocalidade().getLocalidadeId())) {
                values.put(RotaLocalidadeVO.LOCALIDADE_ID, filter.getRotaLocalidade().getLocalidadeId());
            }

            if (!super.isNullOrEmpty(filter.getRotaLocalidade().getRotaId())) {
                values.put(RotaLocalidadeVO.ROTA_ID, filter.getRotaLocalidade().getRotaId());
            }

            Cursor cursor = super.pesquisar(values);

            if (cursor != null) {
                do {
                    rotaLocalidade = this.getRotaLocalidade(cursor);
                    rotaLocalidadeList.add(rotaLocalidade);
                } while (cursor.moveToNext());
                cursor.close();
            }
            super.dataBase.close();
        }
        
        return rotaLocalidadeList;
    }
    
    /**
     * Método insere uma nova rotaLocalidade na base.
     * 
     * @param rotaLocalidade - Objeto {@link RotaLocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(RotaLocalidadeVO rotaLocalidade) {

        ContentValues values = this.getContentValues(rotaLocalidade);        
        super.salvar(values);
        super.dataBase.close();
    }
    
    /**
     * Método deleta RotaLocalidade da base de acordo com os ids de localidade e cliente.
     * 
     * @param rotaLocalidade - Objeto {@link RotaLocalidadeVO}.
     * @throws Exception Exceção da camada de persistência.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void delete(RotaLocalidadeVO rotaLocalidade) throws Exception {   
        
        ContentValues values = new ContentValues();
        values.put(RotaLocalidadeVO.ROTA_ID, rotaLocalidade.getRotaId());
        values.put(RotaLocalidadeVO.LOCALIDADE_ID, rotaLocalidade.getLocalidadeId());
        
        super.delete(values);
        super.dataBase.close();
    }
        
    
}
