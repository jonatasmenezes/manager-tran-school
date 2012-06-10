package com.br.managertranschool.business.vo;

import java.io.Serializable;

/**
 * Classe VO responsável pela entidade de CLIENTE.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 25/05/2012
 */
public class ClienteVO implements Serializable {

    private static final long serialVersionUID = 223098970295361306L;

    public static final String TABLE = "CLIENTE";

    public static final String ID_CLIENTE = "ID_CLIENTE";

    private Long id;

    public static final String TX_NOME = "TX_NOME";

    private String nome;

    public static final String TX_ENDERECO = "TX_ENDERECO";

    private String endereco;

    public static final String CIDADE_ID = "CIDADE_ID";

    private Long cidadeId;

    public static final String CEP = "CEP";

    private String cep;

    public static final String TX_EMAIL = "TX_EMAIL";

    private String email;

    public static final String CPF = "CPF";

    private String cpf;

    public static final String TX_BAIRRO = "TX_BAIRRO";

    private String bairro;

    public static final String NR_TELEFONE_PRIMARIO = "NR_TELEFONE_PRIMARIO";

    private String telefonePrimario;

    public static final String NR_TELEFONE_SECUNDARIO = "NR_TELEFONE_SECUNDARIO";

    private String telefoneSecundario;

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public ClienteVO() {

        super();
    }
    
    /**
     * Construtor padrão com argumentos.
     * 
     * @param id - Id do cliente.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public ClienteVO(Long id) {

        super();
        this.id = id;
    }

    /**
     * Método obtem nome das colunas da tabela CLIENTE
     * 
     * @return Array de string
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public static final String[] getNomesColunas() {

        return new String[] { ID_CLIENTE, TX_NOME, TX_ENDERECO, CIDADE_ID, CEP, TX_EMAIL, CPF, TX_BAIRRO,
            NR_TELEFONE_PRIMARIO, NR_TELEFONE_SECUNDARIO };
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

    /**
     * @return the endereco
     */
    public String getEndereco() {

        return endereco;
    }

    /**
     * @param endereco - the endereco to set
     */
    public void setEndereco(String endereco) {

        this.endereco = endereco;
    }

    /**
     * @return the cidadeId
     */
    public Long getCidadeId() {

        return cidadeId;
    }

    /**
     * @param cidadeId - the cidadeId to set
     */
    public void setCidadeId(Long cidadeId) {

        this.cidadeId = cidadeId;
    }

    /**
     * @return the cep
     */
    public String getCep() {

        return cep;
    }

    /**
     * @param cep - the cep to set
     */
    public void setCep(String cep) {

        this.cep = cep;
    }

    /**
     * @return the email
     */
    public String getEmail() {

        return email;
    }

    /**
     * @param email - the email to set
     */
    public void setEmail(String email) {

        this.email = email;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {

        return cpf;
    }

    /**
     * @param cpf - the cpf to set
     */
    public void setCpf(String cpf) {

        this.cpf = cpf;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {

        return bairro;
    }

    /**
     * @param bairro - the bairro to set
     */
    public void setBairro(String bairro) {

        this.bairro = bairro;
    }

    /**
     * @return the telefonePrimario
     */
    public String getTelefonePrimario() {

        return telefonePrimario;
    }

    /**
     * @param telefonePrimario - the telefonePrimario to set
     */
    public void setTelefonePrimario(String telefonePrimario) {

        this.telefonePrimario = telefonePrimario;
    }

    /**
     * @return the telefoneSecundario
     */
    public String getTelefoneSecundario() {

        return telefoneSecundario;
    }

    /**
     * @param telefoneSecundario - the telefoneSecundario to set
     */
    public void setTelefoneSecundario(String telefoneSecundario) {

        this.telefoneSecundario = telefoneSecundario;
    }

}
