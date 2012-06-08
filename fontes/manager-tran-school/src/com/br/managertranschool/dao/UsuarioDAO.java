package com.br.managertranschool.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.br.managertranschool.architecture.BaseDAO;
import com.br.managertranschool.business.filter.UsuarioFilter;
import com.br.managertranschool.business.vo.UsuarioVO;

/**
 * Classe de acesso a dados da tabela USUARIO
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 06/05/2012
 */
public class UsuarioDAO extends BaseDAO {

    /**
     * Construtor padrão.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public UsuarioDAO() {

        super();
        this.setNomeTabela();
        this.setNomeColunaId();
        this.setNomeColunas();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.br.managertranschool.architecture.BaseDAO#getNomeTabela()
     */
    @Override
    protected void setNomeTabela() {

        super.table = UsuarioVO.TABLE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.br.managertranschool.architecture.BaseDAO#getNomeColunaId()
     */
    @Override
    protected void setNomeColunaId() {

        super.colunaId = UsuarioVO.ID_USUARIO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.br.managertranschool.architecture.BaseDAO#getNomeColunas()
     */
    @Override
    protected void setNomeColunas() {

        super.colunas = UsuarioVO.getNomesColunas();
    }

    /**
     * Método obtem um objeto usuário através do cursor passado por parametro.
     * 
     * @param cursor -Objeto {@link Cursor}.
     * @return Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private UsuarioVO getUsuario(Cursor cursor) {

        UsuarioVO usuario = new UsuarioVO();
        usuario.setId(cursor.getLong(cursor.getColumnIndexOrThrow(UsuarioVO.ID_USUARIO)));
        usuario.setLogin(cursor.getString(cursor.getColumnIndex(UsuarioVO.TX_LOGIN)));
        usuario.setSenha(cursor.getString(cursor.getColumnIndex(UsuarioVO.TX_SENHA)));
        usuario.setTipoUsuario(cursor.getInt(cursor.getColumnIndex(UsuarioVO.TIPO_USUARIO)));
        usuario.setNome(cursor.getString(cursor.getColumnIndex(UsuarioVO.TX_NOME)));

        return usuario;
    }

    /**
     * Método obtem um objeto ContentValues através do usuário passado por parametro.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @return Objeto {@link ContentValues}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private ContentValues getContentValues(UsuarioVO usuario) {

        ContentValues values = new ContentValues();
        values.put(UsuarioVO.TX_LOGIN, usuario.getLogin());
        values.put(UsuarioVO.TX_NOME, usuario.getNome());
        values.put(UsuarioVO.TX_SENHA, usuario.getSenha());
        values.put(UsuarioVO.TIPO_USUARIO, usuario.getTipoUsuario());

        return values;
    }
        
    /**
     * Método busca Usuario por id.
     * 
     * @param id - Id do usuário.
     * @return Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public UsuarioVO buscarPorId(Long id) {

        UsuarioVO usuario = null;

        Cursor cursor = super.findById(id);

        if (cursor != null) {
            usuario = this.getUsuario(cursor);
            cursor.close();
        }

        super.dataBase.close();
        
        return usuario;

    }

    /**
     * Método obtem lista de usuários de acordo com o filtro.
     * 
     * @param filter - Filtro de Usuario {@link UsuarioFilter}.
     * @return - Lista de usuários.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public List<UsuarioVO> pesquisar(UsuarioFilter filter) {

        List<UsuarioVO> usuarioList = new ArrayList<UsuarioVO>();

        if (filter.getUsuario() != null) {
            ContentValues values = new ContentValues();
            UsuarioVO usuario;

            if (filter.getUsuario().getId() != null) {
                values.put(UsuarioVO.ID_USUARIO, filter.getUsuario().getId());
            }

            if (filter.getUsuario().getLogin() != null) {
                values.put(UsuarioVO.TX_LOGIN, filter.getUsuario().getLogin());
            }

            if (filter.getUsuario().getNome() != null) {
                values.put(UsuarioVO.TX_NOME, filter.getUsuario().getNome());
            }

            if (filter.getUsuario().getSenha() != null) {
                values.put(UsuarioVO.TX_SENHA, filter.getUsuario().getSenha());
            }

            if (filter.getUsuario().getTipoUsuario() != null) {
                values.put(UsuarioVO.TIPO_USUARIO, filter.getUsuario().getTipoUsuario());
            }

            Cursor cursor = super.pesquisar(values);

            if (cursor != null) {
                do {
                    usuario = this.getUsuario(cursor);
                    usuarioList.add(usuario);
                } while (cursor.moveToNext());
                cursor.close();
            }
            super.dataBase.close();
        }
        
        return usuarioList;
    }

    /**
     * Método insere um novo usuário na base.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void salvar(UsuarioVO usuario) {

        ContentValues values = this.getContentValues(usuario);        
        usuario.setId(super.salvar(values));
        super.dataBase.close();
    }

    /**
     * Método atualizar o usuário na base de acordo com o id.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void atualizar(UsuarioVO usuario) throws Exception {

        ContentValues values = this.getContentValues(usuario);
        super.atualizar(usuario.getId(), values);  
        super.dataBase.close();
    }
    
    /**
     * Método deleta Usuario da base de acordo com o id.
     * 
     * @param id - Id do usuário.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     * @throws Exception Exceção da camada de persistência.
     */
    public void delete(Long id) throws Exception {   
        super.delete(id);
        super.dataBase.close();
    }
}
