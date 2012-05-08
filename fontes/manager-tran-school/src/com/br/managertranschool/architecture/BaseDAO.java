package com.br.managertranschool.architecture;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Classe Pai de todas classes DAO da aplica��o.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 03/05/2012
 */
public abstract class BaseDAO {

    protected static SQLiteDatabase dataBase;
    
    protected String table;
    
    protected String colunaId;
    
    protected String[] colunas;
        
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
        
        String selection = this.colunaId + "=?";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        
        Cursor cursor = dataBase.query(this.table, this.colunas, selection, selectionArgs, null, null, null);

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
        
        StringBuilder selection = new StringBuilder();
        String[] selectionArgs = new String[values.size()];
        int count = 0;
        
        for (String item : values.keySet()) {
            if (count != 0) {
                selection.append(" AND ");
            }
            selection.append(item);
            selection.append("=?");            
            selectionArgs[count] = values.getAsString(item);
            count++;
        }
        
        Cursor cursor = dataBase.query(this.table, this.colunas, selection.toString(), selectionArgs, null, null, null);
        
        if (cursor.moveToFirst()) {
            return cursor;
        }

        return null;
    }
    
    /**
     * M�todo executa delete do objeto atrav�s do id.
     * @param id - Id do objeto.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected void delete(Long id) {

        String whereClause = this.colunaId + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        
        // TODO: Lan�ar exception se o retorno for igual a 0: Nenhuma coluna afetada.
        dataBase.delete(this.table, whereClause, whereArgs);
    }

    /**
     * M�todo executa insert de objeto na base.
     * 
     * @param values - Valores a serem inseridos.
     * @return ID do novo registro.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected long salvar(ContentValues values) {

        // TODO: Lan�ar exception se o retorno for igual a -1: Ocorreu erro no insert.
        return dataBase.insertOrThrow(this.table, null, values);
    }

    /**
     * M�todo executa update do objeto.
     * 
     * @param id - Id do objeto.
     * @param values - Valores para serem atualizados.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected void atualizar(Long id, ContentValues values) {

        String whereClause = this.colunaId + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        
        // TODO: Lan�ar exception se o retorno for igual a 0: Nenhuma coluna afetada.
        dataBase.update(this.table, values, whereClause, whereArgs);
    }
}
