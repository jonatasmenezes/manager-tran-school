package com.br.managertranschool.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.managertranschool.architecture.BaseDAO;
import com.br.managertranschool.business.filter.ClienteLocalidadeFilter;
import com.br.managertranschool.business.vo.ClienteLocalidadeVO;
import com.br.managertranschool.business.vo.ClienteVO;


/**
 * Classe de acesso a dados da tabela ROTA
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 09/06/2012
 */
public class ClienteLocalidadeDAO extends BaseDAO{

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteLocalidadeDAO() {

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

        super.table = ClienteLocalidadeVO.TABLE;
        
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

        super.colunas = ClienteLocalidadeVO.getNomesColunas();
        
    }
    
    /**
     * Método obtem um objeto clienteLocalidade através do cursor passado por parametro.
     * 
     * @param cursor -Objeto {@link Cursor}.
     * @return Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private ClienteLocalidadeVO getClienteLocalidade(Cursor cursor) {

        ClienteLocalidadeVO clienteLocalidade = new ClienteLocalidadeVO();
        clienteLocalidade.setClienteId(cursor.getLong(cursor.getColumnIndexOrThrow(ClienteLocalidadeVO.CLIENTE_ID)));
        clienteLocalidade.setLocalidadeId(cursor.getLong(cursor.getColumnIndex(ClienteLocalidadeVO.LOCALIDADE_ID)));
        
        return clienteLocalidade;
    }
    
    /**
     * Método obtem um objeto ContentValues através da clienteLocalidade passada por parametro.
     * 
     * @param cliente - Objeto {@link ClienteLocalidadeVO}.
     * @return Objeto {@link ContentValues}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private ContentValues getContentValues(ClienteLocalidadeVO clienteLocalidade) {

        ContentValues values = new ContentValues();
        values.put(ClienteLocalidadeVO.CLIENTE_ID, clienteLocalidade.getClienteId());
        values.put(ClienteLocalidadeVO.LOCALIDADE_ID, clienteLocalidade.getLocalidadeId());      

        return values;
    }
    
    /**
     * Método obtem lista de Cliente-Localidade de acordo com o filtro.
     * 
     * @param filter - Filtro de Cliente-Localidade {@link ClienteLocalidadeFilter}.
     * @return - Lista de entidades.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public List<ClienteLocalidadeVO> pesquisar(ClienteLocalidadeFilter filter) {

        List<ClienteLocalidadeVO> clienteLocalidadeList = new ArrayList<ClienteLocalidadeVO>();

        if (filter.getClienteLocalidade() != null) {
            ContentValues values = new ContentValues();
            ClienteLocalidadeVO clienteLocalidade;

            if (!super.isNullOrEmpty(filter.getClienteLocalidade().getClienteId())) {
                values.put(ClienteLocalidadeVO.CLIENTE_ID, filter.getClienteLocalidade().getClienteId());
            }

            if (!super.isNullOrEmpty(filter.getClienteLocalidade().getLocalidadeId())) {
                values.put(ClienteLocalidadeVO.LOCALIDADE_ID, filter.getClienteLocalidade().getLocalidadeId());
            }

            Cursor cursor = super.pesquisar(values);

            if (cursor != null) {
                do {
                    clienteLocalidade = this.getClienteLocalidade(cursor);
                    clienteLocalidadeList.add(clienteLocalidade);
                } while (cursor.moveToNext());
                cursor.close();
            }
            super.dataBase.close();
        }
        
        return clienteLocalidadeList;
    }
    
    /**
     * Método insere uma nova clienteLocalidade na base.
     * 
     * @param clienteLocalidade - Objeto {@link ClienteLocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(ClienteLocalidadeVO clienteLocalidade) {

        ContentValues values = this.getContentValues(clienteLocalidade);        
        super.salvar(values);
        super.dataBase.close();
    }
    
    /**
     * Método atualiza clienteLocalidade na base de dados.
     * 
     * @param clienteLocalidade - Objeto {@link ClienteLocalidadeVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void atualizar(ClienteLocalidadeVO clienteLocalidade) throws Exception {

        ContentValues values = new ContentValues();
        values.put(ClienteLocalidadeVO.LOCALIDADE_ID, clienteLocalidade.getLocalidadeId());

        super.colunaId = ClienteLocalidadeVO.CLIENTE_ID;
        
        super.atualizar(clienteLocalidade.getClienteId(), values);  
        super.dataBase.close();
    }
    
    /**
     * Método deleta ClienteLocalidade da base de acordo com os ids de localidade e cliente.
     * 
     * @param clienteLocalidade - Objeto {@link ClienteLocalidadeVO}.
     * @throws Exception Exceção da camada de persistência.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void delete(ClienteLocalidadeVO clienteLocalidade) throws Exception {   
        
        ContentValues values = new ContentValues();
        values.put(ClienteLocalidadeVO.LOCALIDADE_ID, clienteLocalidade.getLocalidadeId());
        values.put(ClienteLocalidadeVO.CLIENTE_ID, clienteLocalidade.getClienteId());
        
        super.delete(values);
        super.dataBase.close();
    }
        
    
}
