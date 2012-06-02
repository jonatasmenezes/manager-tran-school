package com.br.managertranschool.architecture;

import java.util.Map.Entry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.database.DatabaseCreate;
import com.br.managertranschool.architecture.exception.RegistroNaoEncontradoException;
import com.google.inject.Inject;

/**
 * Classe Pai de todas classes DAO da aplica��o.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 03/05/2012
 */
public abstract class BaseDAO {

    protected SQLiteDatabase dataBase;
    
    protected String table;
    
    protected String colunaId;
    
    protected String[] colunas;
    
    @Inject
    protected Context context;
        
    /**
     * Construtor padr�o.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public BaseDAO() {

        super();
    }
    
    /**
     * Metodo implementado pelas classes, respons�vel em obter o nome da tabela.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected abstract void setNomeTabela();
    
    /**
     * Metodo implementado pelas classes, respons�vel em obter o nome da coluna do id.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected abstract void setNomeColunaId();
    
    /**
     * Metodo implementado pelas classes, respons�vel em obter nome das colunas.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected abstract void setNomeColunas();
    
    /**
     * M�todo obtem objeto pelo id
     * @param id - Id do objeto.
     * @return Objeto {@link Cursor}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected Cursor findById(Long id) {
        
        dataBase = context.openOrCreateDatabase(DatabaseCreate.NOME_DATABASE, Context.MODE_PRIVATE, null);
        
        String selection = this.colunaId + "=?";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        
        Cursor cursor = dataBase.query(this.table, this.colunas, selection, selectionArgs, null, null, null);

        dataBase.close();
        
        if (cursor.moveToFirst()) {
            return cursor;
        }

        return null;
    }
    
    /**
     * M�todo obtem lista de objetos atrav�s dos filtros passado por par�metro.
     * @param values - Valores com filtros.
     * @return  Objeto {@link Cursor} com lista.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected Cursor pesquisar(ContentValues values) {
        
        dataBase = context.openOrCreateDatabase(DatabaseCreate.NOME_DATABASE, Context.MODE_PRIVATE, null);
        
        StringBuilder selection = new StringBuilder();
        String[] selectionArgs = new String[values.size()];
        int count = 0;
        
        for (Entry<String, Object> entry : values.valueSet()) {
            if (count != 0) {
                selection.append(" AND ");
            }
            selection.append(entry.getKey());
            selection.append("=?");
            selectionArgs[count] = String.valueOf(entry.getValue());
            count++;
        }
        
        Cursor cursor = dataBase.query(this.table, this.colunas, selection.toString(), selectionArgs, null, null, null);
        
        dataBase.close();
        
        if (cursor.moveToFirst()) {
            return cursor;
        }

        return null;
    }
    
    /**
     * M�todo executa delete do objeto atrav�s do id.
     * @param id - Id do objeto.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     * @throws Exception Registro n�o foi encontrado.
     */
    protected void delete(Long id) throws Exception {

        dataBase = context.openOrCreateDatabase(DatabaseCreate.NOME_DATABASE, Context.MODE_PRIVATE, null);
        
        String whereClause = this.colunaId + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        
        int linhasAfetadas = dataBase.delete(this.table, whereClause, whereArgs);
        
        dataBase.close();
        
        if (linhasAfetadas <= 0) {
            throw new RegistroNaoEncontradoException(R.string.registro_nao_encontrado);
        }
    }

    /**
     * M�todo executa insert de objeto na base.
     * 
     * @param values - Valores a serem inseridos.
     * @return ID do novo registro.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected long salvar(ContentValues values) {

        dataBase = context.openOrCreateDatabase(DatabaseCreate.NOME_DATABASE, Context.MODE_PRIVATE, null);
        
        long retorno = dataBase.insertOrThrow(this.table, null, values); 
        
        dataBase.close();
        
        return retorno;
    }

    /**
     * M�todo executa update do objeto.
     * 
     * @param id - Id do objeto.
     * @param values - Valores para serem atualizados.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     * @throws Exception Registro n�o foi encontrado.
     */
    protected void atualizar(Long id, ContentValues values) throws Exception {

        dataBase = context.openOrCreateDatabase(DatabaseCreate.NOME_DATABASE, Context.MODE_PRIVATE, null);
        
        String whereClause = this.colunaId + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        
        int linhasAfetadas = dataBase.update(this.table, values, whereClause, whereArgs);
        
        dataBase.close();
        
        if (linhasAfetadas <= 0) {
            throw new RegistroNaoEncontradoException(R.string.registro_nao_encontrado);
        }
    }
}
