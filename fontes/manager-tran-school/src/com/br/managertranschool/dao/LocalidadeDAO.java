package com.br.managertranschool.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.managertranschool.architecture.BaseDAO;
import com.br.managertranschool.business.filter.LocalidadeFilter;
import com.br.managertranschool.business.vo.ClienteVO;
import com.br.managertranschool.business.vo.LocalidadeVO;


/**
 * Classe de acesso a dados da tabela LOCALIDADE
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 03/06/2012
 */
public class LocalidadeDAO extends BaseDAO{

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public LocalidadeDAO() {

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

        super.table = LocalidadeVO.TABLE;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunaId()
     */
    @Override
    protected void setNomeColunaId() {

        super.colunaId = LocalidadeVO.ID_LOCALIDADE;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunas()
     */
    @Override
    protected void setNomeColunas() {

        super.colunas = LocalidadeVO.getNomesColunas();
        
    }

    /**
     * Método obtem um objeto localidade através do cursor passado por parametro.
     * 
     * @param cursor -Objeto {@link Cursor}.
     * @return Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private LocalidadeVO getLocalidade(Cursor cursor) {

        LocalidadeVO localidade = new LocalidadeVO();
        localidade.setId(cursor.getLong(cursor.getColumnIndexOrThrow(LocalidadeVO.ID_LOCALIDADE)));
        localidade.setDescricao(cursor.getString(cursor.getColumnIndex(LocalidadeVO.TX_DESCRICAO)));
        localidade.setLatitude(cursor.getDouble(cursor.getColumnIndex(LocalidadeVO.NR_LATITUDE)));
        localidade.setLongitude(cursor.getDouble(cursor.getColumnIndex(LocalidadeVO.NR_LONGITUDE)));
        
        return localidade;
    }
    
    /**
     * Método obtem um objeto ContentValues através da localidade passada por parametro.
     * 
     * @param cliente - Objeto {@link LocalidadeVO}.
     * @return Objeto {@link ContentValues}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private ContentValues getContentValues(LocalidadeVO localidade) {

        ContentValues values = new ContentValues();
        values.put(LocalidadeVO.TX_DESCRICAO, localidade.getDescricao());      
        values.put(LocalidadeVO.NR_LATITUDE, localidade.getLatitude());
        values.put(LocalidadeVO.NR_LONGITUDE, localidade.getLongitude());
        
        return values;
    }
    
    /**
     * Método busca Localidade por id.
     * 
     * @param id - Id da localidade.
     * @return Objeto {@link LocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public LocalidadeVO buscarPorId(Long id) {

        LocalidadeVO localidade = null;

        Cursor cursor = super.findById(id);

        if (cursor != null) {
            localidade = this.getLocalidade(cursor);
            cursor.close();
        }

        super.dataBase.close();
        
        return localidade;

    }
    
    /**
     * Método obtem lista de localidade de acordo com o filtro.
     * 
     * @param filter - Filtro de Localidade {@link LocalidadeFilter}.
     * @return - Lista de localidade.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<LocalidadeVO> pesquisar(LocalidadeFilter filter) {

        List<LocalidadeVO> localidadeList = new ArrayList<LocalidadeVO>();

        if (filter.getLocalidade() != null) {
            ContentValues values = new ContentValues();
            LocalidadeVO localidade;

            if (filter.getLocalidade().getId() != null) {
                values.put(LocalidadeVO.ID_LOCALIDADE, filter.getLocalidade().getId());
            }
            
            if (filter.getLocalidade().getDescricao() != null) {
                values.put(LocalidadeVO.TX_DESCRICAO, "%" + filter.getLocalidade().getDescricao() + "%");
            }
            
            if (filter.getLocalidade().getLatitude() != null) {
                values.put(LocalidadeVO.NR_LATITUDE, filter.getLocalidade().getLatitude());
            }
            
            if (filter.getLocalidade().getLongitude() != null) {
                values.put(LocalidadeVO.NR_LONGITUDE, filter.getLocalidade().getLongitude());
            }
            
            
            Cursor cursor = super.pesquisar(values);

            if (cursor != null) {
                do {
                    localidade = this.getLocalidade(cursor);
                    localidadeList.add(localidade);
                } while (cursor.moveToNext());
                cursor.close();
            }
            super.dataBase.close();
        }

        return localidadeList;
    }
    
    /**
     * Método insere uma nova localidade na base.
     * 
     * @param localidade - Objeto {@link LocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(LocalidadeVO localidade) {

        ContentValues values = this.getContentValues(localidade);        
        localidade.setId(super.salvar(values));
        super.dataBase.close();
    }

    /**
     * Método atualizar a localidade na base de acordo com o id.
     * 
     * @param localidade - Objeto {@link LocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void atualizar(LocalidadeVO localidade) throws Exception {

        ContentValues values = this.getContentValues(localidade);
        super.atualizar(localidade.getId(), values);  
        super.dataBase.close();
    }
    
    /**
     * Método deleta Localidade da base de acordo com o id.
     * 
     * @param id - Id da localidade.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void delete(Long id) throws Exception {   
        super.delete(id);
        super.dataBase.close();
    }
        
    
}
