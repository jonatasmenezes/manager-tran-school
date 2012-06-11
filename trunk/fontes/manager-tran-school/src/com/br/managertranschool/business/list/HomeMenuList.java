package com.br.managertranschool.business.list;

import com.br.managertranschool.R;

/**
 * Enumerador que define os links no Menu do Home do app.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 09/06/2012
 */
public enum HomeMenuList {

    USUARIOS(1, R.string.home_usuarios, R.string.home_usuarios_descricao),
    CLIENTES(2, R.string.home_clientes, R.string.home_clientes_descricao),
    ROTAS(3, R.string.home_rotas, R.string.home_rotas_descricao),
    LOCALIDADES(4, R.string.home_localidades, R.string.home_localidades_descricao),
    PAGAMENTOS(5, R.string.home_pagamentos, R.string.home_pagamentos_descricao),
    TESTEMAPA(6, R.string.home_teste_mapa, R.string.home_teste_mapa_descricao);

    private int codigo;

    private int nome;

    private int descricao;

    /**
     * Construtor padrão com argumentos.
     * 
     * @param codigo - Código do item
     * @param nome - Nome do item
     * @param descricao - Descricao do item
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private HomeMenuList(int codigo, int nome, int descricao) {

        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {

        return codigo;
    }

    /**
     * @return the nome
     */
    public int getNome() {

        return nome;
    }

    /**
     * @return the descricao
     */
    public int getDescricao() {

        return descricao;
    }

}
