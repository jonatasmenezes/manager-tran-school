package com.br.managertranschool.architecture;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;

import javax.inject.Inject;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.database.DatabaseCreate;
import com.br.managertranschool.architecture.exception.RegistroNaoEncontradoException;

/**
 * Classe Pai de todas classes DAO da aplicação.
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
    private Application context;
    
    /**
     * Construtor padrão.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public BaseDAO() {

        super();
    }
    
    /**
     * Metodo implementado pelas classes, responsável em obter o nome da tabela.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected abstract void setNomeTabela();
    
    /**
     * Metodo implementado pelas classes, responsável em obter o nome da coluna do id.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected abstract void setNomeColunaId();
    
    /**
     * Metodo implementado pelas classes, responsável em obter nome das colunas.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected abstract void setNomeColunas();
    
    /**
     * Método obtem objeto pelo id
     * @param id - Id do objeto.
     * @return Objeto {@link Cursor}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected Cursor findById(Long id) {
        
        dataBase = context.openOrCreateDatabase(DatabaseCreate.NOME_DATABASE, Context.MODE_PRIVATE, null);
        
        String selection = this.colunaId + "=?";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        
        Cursor cursor = dataBase.query(this.table, this.colunas, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            return cursor;
        }

        return null;
    }
    
    /**
     * Método obtem lista de objetos através dos filtros passado por parâmetro.
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
                
        Cursor cursor = dataBase.query(this.table, this.colunas, selection.length() > 0 ? selection.toString() : null, selectionArgs.length > 0 ? selectionArgs : null, null, null, null);
        
        if (cursor.moveToFirst()) {
            return cursor;
        }

        return null;
    }
    
    /**
     * Método executa delete do objeto através do id.
     * @param id - Id do objeto.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     * @throws Exception Registro não foi encontrado.
     */
    protected void delete(Long id) throws Exception {

        dataBase = context.openOrCreateDatabase(DatabaseCreate.NOME_DATABASE, Context.MODE_PRIVATE, null);
        
        String whereClause = this.colunaId + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        
        int linhasAfetadas = dataBase.delete(this.table, whereClause, whereArgs);
        
        if (linhasAfetadas <= 0) {
            throw new RegistroNaoEncontradoException(R.string.registro_nao_encontrado);
        }
    }

    /**
     * Método executa insert de objeto na base.
     * 
     * @param values - Valores a serem inseridos.
     * @return ID do novo registro.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected long salvar(ContentValues values) {

        dataBase = context.openOrCreateDatabase(DatabaseCreate.NOME_DATABASE, Context.MODE_PRIVATE, null);
        
        long retorno = dataBase.insertOrThrow(this.table, null, values); 
        
        return retorno;
    }

    /**
     * Método executa update do objeto.
     * 
     * @param id - Id do objeto.
     * @param values - Valores para serem atualizados.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     * @throws Exception Registro não foi encontrado.
     */
    protected void atualizar(Long id, ContentValues values) throws Exception {

        dataBase = context.openOrCreateDatabase(DatabaseCreate.NOME_DATABASE, Context.MODE_PRIVATE, null);
        
        String whereClause = this.colunaId + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        
        int linhasAfetadas = dataBase.update(this.table, values, whereClause, whereArgs);
        
        if (linhasAfetadas <= 0) {
            throw new RegistroNaoEncontradoException(R.string.registro_nao_encontrado);
        }
    }
    
    /**
     * Método obtém data através de string obtida do cursor.
     * 
     * @param data - Data a ser formatada.
     * @return Obtejo Date.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected Date obterDate(String data) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date retorno;
        
        try {
            retorno = format.parse(data);
        } catch (ParseException e) {
            retorno = null;
        }
        
        return retorno;
    }
    
    /**
     * Método obtém data em string através de data passada por parametro.
     * 
     * @param data - Data a ser formatada.
     * @return Obtejo String.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected String obterDateString(Date data) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(data);
    }
    
    /**
     * Método obtém time através de string obtida do cursor.
     * 
     * @param data - Time a ser formatado.
     * @return Obtejo Time.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected Time obterTime(String time) {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Time retorno = new Time();
        
        try {
            retorno.set(format.parse(time).getTime());
        } catch (ParseException e) {
            retorno = null;
        }
        
        return retorno;
    }
    
    /**
     * Método obtém time em string através do time passada por parametro.
     * 
     * @param time - Time a ser formatado.
     * @return Obtejo String.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected String obterTimeString(Time time) {
        
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String retorno;
        
        try {
            retorno = format.format(time);
        } catch (IllegalArgumentException e) {
            retorno = null;
        }
        
        return retorno;
    }
}
