package com.br.managertranschool.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.managertranschool.architecture.BaseDAO;
import com.br.managertranschool.business.filter.ClienteFilter;
import com.br.managertranschool.business.vo.ClienteVO;


/**
 * Classe de acesso a dados da tabela CLIENTE
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 03/06/2012
 */
public class ClienteDAO extends BaseDAO{

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteDAO() {
        
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

        super.table = ClienteVO.TABLE;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunaId()
     */
    @Override
    protected void setNomeColunaId() {

        super.colunaId = ClienteVO.ID_CLIENTE;
        
    }

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseDAO#setNomeColunas()
     */
    @Override
    protected void setNomeColunas() {

        super.colunas = ClienteVO.getNomesColunas();
        
    }
    
    /**
     * Método obtem um objeto cliente através do cursor passado por parametro.
     * 
     * @param cursor -Objeto {@link Cursor}.
     * @return Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private ClienteVO getCliente(Cursor cursor) {

        ClienteVO cliente = new ClienteVO();
        cliente.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ClienteVO.ID_CLIENTE)));
        cliente.setNome(cursor.getString(cursor.getColumnIndex(ClienteVO.TX_NOME)));
        cliente.setEndereco(cursor.getString(cursor.getColumnIndex(ClienteVO.TX_ENDERECO)));
        cliente.setEmail(cursor.getString(cursor.getColumnIndex(ClienteVO.TX_EMAIL)));
        cliente.setBairro(cursor.getString(cursor.getColumnIndex(ClienteVO.TX_BAIRRO)));
        cliente.setCep(cursor.getLong(cursor.getColumnIndex(ClienteVO.CEP)));
        cliente.setCidadeId(cursor.getLong(cursor.getColumnIndex(ClienteVO.CIDADE_ID)));
        cliente.setTelefonePrimario(cursor.getLong(cursor.getColumnIndex(ClienteVO.NR_TELEFONE_PRIMARIO)));
        cliente.setTelefoneSecundario(cursor.getLong(cursor.getColumnIndex(ClienteVO.NR_TELEFONE_SECUNDARIO)));

        return cliente;
    }

    /**
     * Método obtem um objeto ContentValues através do cliente passado por parametro.
     * 
     * @param cliente - Objeto {@link ClienteVO}.
     * @return Objeto {@link ContentValues}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private ContentValues getContentValues(ClienteVO cliente) {

        ContentValues values = new ContentValues();
        values.put(ClienteVO.CEP, cliente.getCep());
        values.put(ClienteVO.CIDADE_ID, cliente.getCidadeId());
        values.put(ClienteVO.CPF, cliente.getCpf());
        values.put(ClienteVO.NR_TELEFONE_PRIMARIO, cliente.getTelefonePrimario());
        values.put(ClienteVO.NR_TELEFONE_SECUNDARIO, cliente.getTelefoneSecundario());
        values.put(ClienteVO.TX_BAIRRO, cliente.getBairro());
        values.put(ClienteVO.TX_EMAIL, cliente.getEmail());
        values.put(ClienteVO.TX_ENDERECO, cliente.getEndereco());
        values.put(ClienteVO.TX_NOME, cliente.getNome());
      

        return values;
    }
    
    /**
     * Método busca Cliente por id.
     * 
     * @param id - Id do cliente.
     * @return Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteVO buscarPorId(Long id) {

        ClienteVO cliente = null;

        Cursor cursor = super.findById(id);

        if (cursor != null) {
            cliente = this.getCliente(cursor);
            cursor.close();
        }

        super.dataBase.close();
        
        return cliente;

    }
    
    /**
     * Método obtem lista de cliente de acordo com o filtro.
     * 
     * @param filter - Filtro de Cliente {@link ClienteFilter}.
     * @return - Lista de cliente.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<ClienteVO> pesquisar(ClienteFilter filter) {

        List<ClienteVO> clienteList = new ArrayList<ClienteVO>();

        if (filter.getCliente() != null) {
            ContentValues values = new ContentValues();
            ClienteVO cliente;

            if (filter.getCliente().getId() != null) {
                values.put(ClienteVO.ID_CLIENTE, filter.getCliente().getId());
            }
            
            if (filter.getCliente().getCep() != null) {
                values.put(ClienteVO.CEP, filter.getCliente().getCep());
            }
            
            if (filter.getCliente().getCidadeId() != null) {
                values.put(ClienteVO.CIDADE_ID, filter.getCliente().getCidadeId());
            }
            
            if (filter.getCliente().getCpf() != null) {
                values.put(ClienteVO.CPF, filter.getCliente().getCpf());
            }

            if (filter.getCliente().getTelefonePrimario() != null) {
                values.put(ClienteVO.NR_TELEFONE_PRIMARIO, filter.getCliente().getTelefonePrimario());
            }

            if (filter.getCliente().getTelefoneSecundario() != null) {
                values.put(ClienteVO.NR_TELEFONE_SECUNDARIO, filter.getCliente().getTelefoneSecundario());
            }
            
            if (filter.getCliente().getBairro() != null) {
                values.put(ClienteVO.TX_BAIRRO, filter.getCliente().getBairro());
            }
            
            if (filter.getCliente().getEmail() != null) {
                values.put(ClienteVO.TX_EMAIL, filter.getCliente().getEmail());
            }
            
            if (filter.getCliente().getEndereco() != null) {
                values.put(ClienteVO.TX_ENDERECO, filter.getCliente().getEndereco());
            }
            
            if (filter.getCliente().getNome() != null) {
                values.put(ClienteVO.TX_NOME, filter.getCliente().getNome());
            }
            
            Cursor cursor = super.pesquisar(values);

            if (cursor != null) {
                do {
                    cliente = this.getCliente(cursor);
                    clienteList.add(cliente);
                } while (cursor.moveToNext());
                cursor.close();
            }
            
            super.dataBase.close();
        }

        return clienteList;
    }
    
    
    /**
     * Método insere um novo cliente na base.
     * 
     * @param cliente - Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(ClienteVO cliente) {

        ContentValues values = this.getContentValues(cliente);        
        cliente.setId(super.salvar(values));
        super.dataBase.close();
    }

    /**
     * Método atualizar o cliente na base de acordo com o id.
     * 
     * @param cliente - Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void atualizar(ClienteVO cliente) throws Exception {

        ContentValues values = this.getContentValues(cliente);
        super.atualizar(cliente.getId(), values);  
        super.dataBase.close();
    }
    
    /**
     * Método deleta Cliente da base de acordo com o id.
     * 
     * @param id - Id do cliente.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void delete(Long id) throws Exception {   
        super.delete(id);
        super.dataBase.close();
    }
    
     
}
