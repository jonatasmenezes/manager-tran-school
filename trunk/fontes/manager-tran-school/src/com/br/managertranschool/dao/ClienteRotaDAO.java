package com.br.managertranschool.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.managertranschool.architecture.BaseDAO;
import com.br.managertranschool.business.filter.ClienteRotaFilter;
import com.br.managertranschool.business.vo.ClienteRotaVO;
import com.br.managertranschool.business.vo.ClienteVO;


/**
 * Classe de acesso a dados da tabela CLIENTE_ROTA
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 11/06/2012
 */
public class ClienteRotaDAO extends BaseDAO{

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteRotaDAO() {

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

        super.table = ClienteRotaVO.TABLE;
        
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

        super.colunas = ClienteRotaVO.getNomesColunas();
        
    }
    
    /**
     * Método obtem um objeto clienteRota através do cursor passado por parametro.
     * 
     * @param cursor -Objeto {@link Cursor}.
     * @return Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private ClienteRotaVO getClienteRota(Cursor cursor) {

        ClienteRotaVO clienteRota = new ClienteRotaVO();
        clienteRota.setClienteId(cursor.getLong(cursor.getColumnIndexOrThrow(ClienteRotaVO.CLIENTE_ID)));
        clienteRota.setRotaId(cursor.getLong(cursor.getColumnIndex(ClienteRotaVO.ROTA_ID)));
        
        return clienteRota;
    }
    
    /**
     * Método obtem um objeto ContentValues através da clienteRota passada por parametro.
     * 
     * @param cliente - Objeto {@link ClienteRotaVO}.
     * @return Objeto {@link ContentValues}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private ContentValues getContentValues(ClienteRotaVO clienteRota) {

        ContentValues values = new ContentValues();
        values.put(ClienteRotaVO.CLIENTE_ID, clienteRota.getClienteId());
        values.put(ClienteRotaVO.ROTA_ID, clienteRota.getRotaId());      

        return values;
    }
    
    /**
     * Método obtem lista de Cliente-Localidade de acordo com o filtro.
     * 
     * @param filter - Filtro de Cliente-Localidade {@link ClienteRotaFilter}.
     * @return - Lista de entidades.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public List<ClienteRotaVO> pesquisar(ClienteRotaFilter filter) {

        List<ClienteRotaVO> clienteRotaList = new ArrayList<ClienteRotaVO>();

        if (filter.getClienteRota() != null) {
            ContentValues values = new ContentValues();
            ClienteRotaVO clienteRota;

            if (!super.isNullOrEmpty(filter.getClienteRota().getClienteId())) {
                values.put(ClienteRotaVO.CLIENTE_ID, filter.getClienteRota().getClienteId());
            }

            if (!super.isNullOrEmpty(filter.getClienteRota().getRotaId())) {
                values.put(ClienteRotaVO.ROTA_ID, filter.getClienteRota().getRotaId());
            }

            Cursor cursor = super.pesquisar(values);

            if (cursor != null) {
                do {
                    clienteRota = this.getClienteRota(cursor);
                    clienteRotaList.add(clienteRota);
                } while (cursor.moveToNext());
                cursor.close();
            }
            super.dataBase.close();
        }
        
        return clienteRotaList;
    }
    
    /**
     * Método insere uma nova clienteRota na base.
     * 
     * @param clienteRota - Objeto {@link ClienteRotaVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(ClienteRotaVO clienteRota) {

        ContentValues values = this.getContentValues(clienteRota);        
        super.salvar(values);
        super.dataBase.close();
    }
    
    /**
     * Método deleta ClienteRota da base de acordo com os ids de localidade e cliente.
     * 
     * @param clienteRota - Objeto {@link ClienteRotaVO}.
     * @throws Exception Exceção da camada de persistência.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void delete(ClienteRotaVO clienteRota) throws Exception {   
        
        ContentValues values = new ContentValues();
        values.put(ClienteRotaVO.ROTA_ID, clienteRota.getRotaId());
        values.put(ClienteRotaVO.CLIENTE_ID, clienteRota.getClienteId());
        
        super.delete(values);
        super.dataBase.close();
    }
        
    
}
