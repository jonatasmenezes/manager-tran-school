package com.br.managertranschool.business.vo;

import java.io.Serializable;

/**
 * Classe VO responsável pela entidade de Usuario.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 19/04/2012
 */
public class UsuarioVO implements Serializable {

    public static final String TABLE = "USUARIO";

    private static final long serialVersionUID = -7635966082812185598L;

    public static final String ID_USUARIO = "ID_USUARIO";

    private Long id;

    public static final String TX_LOGIN = "TX_LOGIN";

    private String login;

    public static final String TX_SENHA = "TX_SENHA";

    private String senha;

    public static final String TIPO_USUARIO = "TIPO_USUARIO";

    private Integer tipoUsuario;

    public static final String TX_NOME = "TX_NOME";

    private String nome;

    /**
     * Construtor padrão.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public UsuarioVO() {

        super();
    }

    /**
     * Construtor utilizando argumentos.
     * 
     * @param id - Id do usuario.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public UsuarioVO(Long id) {

        super();
        this.id = id;
    }

    /**
     * Construtor utilizando argumentos.
     * 
     * @param login - Login do usuario.
     * @param senha - Senha do usuario.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public UsuarioVO(String login, String senha) {

        super();
        this.login = login;
        this.senha = senha;
    }

    /**
     * Método obtem nome das colunas da tabela USUARIO
     * 
     * @return Array de string
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public static final String[] getNomesColunas() {

        return new String[] { ID_USUARIO, TX_LOGIN, TX_SENHA, TIPO_USUARIO, TX_NOME };
    }

    /**
     * @return the id
     */
    public Long getId() {

        return id;
    }

    /**
     * @param id - the id to set
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * @return the login
     */
    public String getLogin() {

        return login;
    }

    /**
     * @param login - the login to set
     */
    public void setLogin(String login) {

        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {

        return senha;
    }

    /**
     * @param senha - the senha to set
     */
    public void setSenha(String senha) {

        this.senha = senha;
    }

    /**
     * @return the tipoUsuario
     */
    public Integer getTipoUsuario() {

        return tipoUsuario;
    }

    /**
     * @param tipoUsuario - the tipoUsuario to set
     */
    public void setTipoUsuario(Integer tipoUsuario) {

        this.tipoUsuario = tipoUsuario;
    }

    /**
     * @return the nome
     */
    public String getNome() {

        return nome;
    }

    /**
     * @param nome - the nome to set
     */
    public void setNome(String nome) {

        this.nome = nome;
    }

}
