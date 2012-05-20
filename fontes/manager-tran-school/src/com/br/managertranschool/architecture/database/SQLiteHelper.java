package com.br.managertranschool.architecture.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Classe helper utilizado na base de dados SQLite. Criação e atualização da base na primeira utilização.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 20/05/2012
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private String[] scriptCreateTables;

    private String[] scriptDropTables;

    private String[] scriptInsertDados;

    /**
     * Construtor utilizando dos argumentos: Contexto do app, nome da base, versão da base, script de criação da base,
     * script de delete da base e script de insert na base.
     * 
     * @param context - Contexto do app.
     * @param nomeDatabase - Nome da base de dados.
     * @param versaoDatabase - Versão da base de dados.
     * @param scriptCreateTables - Script de criação das tabelas.
     * @param scriptDropTables - Script de drop das tabelas que sofrerão upgrade.
     * @param scriptInsertDados - Script de insert nas tabelas de dados fixos.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public SQLiteHelper(Context context, String nomeDatabase, int versaoDatabase, String[] scriptCreateTables,
        String[] scriptDropTables, String[] scriptInsertDados) {

        super(context, nomeDatabase, null, versaoDatabase);
        Log.i(this.getClass().getName(), context + nomeDatabase + versaoDatabase);
        this.scriptCreateTables = scriptCreateTables;
        this.scriptDropTables = scriptDropTables;
        this.scriptInsertDados = scriptInsertDados;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
     */
    @Override
    public void onCreate(SQLiteDatabase database) {

        Log.i(this.getClass().getName(), "android.database.sqlite.SQLiteOpenHelper#onCreate(" + database.toString() + ")");

        // Criar tabelas na base de dados.
        for (String script : scriptCreateTables) {
            Log.i(this.getClass().getName(), script);
            database.execSQL(script);
        }

        // Inserir dados na base de dados.
        for (String script : scriptInsertDados) {
            Log.i(this.getClass().getName(), script);
            database.execSQL(script);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int versaoAnterior, int versaoAtual) {

        Log.i(this.getClass().getName(), "android.database.sqlite.SQLiteOpenHelper#onUpgrade("
            + database.toString()
            + ", "
            + versaoAnterior
            + ", "
            + versaoAtual
            + ")");

        // Deleta tabelas que possui alterações para serem feitas na base.
        for (String script : scriptDropTables) {
            Log.i(this.getClass().getName(), script);
            database.execSQL(script);
        }

        this.onCreate(database);
    }

}
