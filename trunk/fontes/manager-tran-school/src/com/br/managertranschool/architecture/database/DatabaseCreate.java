package com.br.managertranschool.architecture.database;

import com.br.managertranschool.business.vo.UsuarioVO;

import android.content.Context;

/**
 * Classe utilizada para criar ou atualizar a base de dados da aplica��o.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 20/05/2012
 */
public class DatabaseCreate {

    public static final String NOME_DATABASE = "manager-tran-school";

    public static final Integer VERSAO_DATABASE = 1;

    /**
     * Construtor utilizando contexto do app como argumento.
     * 
     * @param context - Contexto do app;
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public DatabaseCreate(Context context) {

        SQLiteHelper sqlHelper = new SQLiteHelper(context, NOME_DATABASE, VERSAO_DATABASE, getScriptCreateTables(),
            getScriptDropTables(), getScriptInsert());

        sqlHelper.getWritableDatabase();
    }

    /**
     * M�todo obt�m script de cria��o das tabelas da base.
     * 
     * @return Array de scripts.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String[] getScriptCreateTables() {

        return new String[] { this.getCreateTableUsuario() };
    }

    /**
     * M�todo obtem script de drop das tabelas que sofrer�o mudan�as ao atualizar a base.
     * 
     * @return Array de scripts.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String[] getScriptDropTables() {

        return new String[] {};
    }

    /**
     * M�todo obtem script de insert de dados.
     * 
     * @return Array de scripts.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String[] getScriptInsert() {

        return new String[] {};
    }

    /**
     * M�todo obt�m script de create da tabela USUARIO.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTableUsuario() {

        StringBuilder script = new StringBuilder();
        script.append("CREATE TABLE IF NOT EXISTS ").append(UsuarioVO.TABLE);
        script.append("(");
        script.append(UsuarioVO.ID_USUARIO).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, ");
        script.append(UsuarioVO.TX_LOGIN).append(" VARCHAR(12) NOT NULL UNIQUE, ");
        script.append(UsuarioVO.TX_SENHA).append(" VARCHAR(12) NOT NULL, ");
        script.append(UsuarioVO.TIPO_USUARIO).append(" INT NOT NULL, ");
        script.append(UsuarioVO.TX_NOME).append(" VARCHAR(45) NOT NULL ");
        script.append(");");

        return script.toString();
    }

    /**
     * M�todo obt�m script de create da tabela ESTADO.
     * 
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private String getCreateTableEstado() {

        return null;
    }

    /**
     * M�todo obt�m script de drop da tabela.
     * 
     * @param table - Nome da tabela
     * @return Script
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    @SuppressWarnings("unused")
    private String getDropTable(String table) {

        return "DROP TABLE IF EXISTS " + table;
    }
}
