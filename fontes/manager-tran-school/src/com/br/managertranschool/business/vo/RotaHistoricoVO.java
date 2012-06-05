package com.br.managertranschool.business.vo;

import java.io.Serializable;
import java.util.Date;

import android.text.format.Time;

/**
 * Classe VO responsável pela entidade de ROTA_HISTORICO.
 * 
 * @author Jeferson Almeida (jef.almeida.07@gmail.com)
 * @since 19/05/2012
 */
public class RotaHistoricoVO implements Serializable {

    private static final long serialVersionUID = -1450916171568473727L;

    public static final String TABLE = "ROTA_HISTORICO";

    public static final String ID_ROTA_HISTORICO = "ID_ROTA_HISTORICO";

    private Long id;

    public static final String ROTA_ID = "ROTA_ID";

    private Long rotaId;

    public static final String DT_ROTA = "DT_ROTA";

    private Date data;

    public static final String DURACAO = "DURACAO";

    private Time duracao;

    public static final String USUARIO_ID = "USUARIO_ID";

    private Long usuarioId;

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public RotaHistoricoVO() {

        super();
    }

    /**
     * Método obtem nome das colunas da tabela ROTA_HISTORICO
     * 
     * @return Array de string
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public static final String[] getNomesColunas() {

        return new String[] { ID_ROTA_HISTORICO, ROTA_ID, DT_ROTA, DURACAO, USUARIO_ID };
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
     * @return the rotaId
     */
    public Long getRotaId() {

        return rotaId;
    }

    /**
     * @param rotaId - the rotaId to set
     */
    public void setRotaId(Long rotaId) {

        this.rotaId = rotaId;
    }

    /**
     * @return the data
     */
    public Date getData() {

        return data;
    }

    /**
     * @param data - the data to set
     */
    public void setData(Date data) {

        this.data = data;
    }

    /**
     * @return the duracao
     */
    public Time getDuracao() {

        return duracao;
    }

    /**
     * @param duracao - the duracao to set
     */
    public void setDuracao(Time duracao) {

        this.duracao = duracao;
    }

    /**
     * @return the usuarioId
     */
    public Long getUsuarioId() {

        return usuarioId;
    }

    /**
     * @param usuarioId - the usuarioId to set
     */
    public void setUsuarioId(Long usuarioId) {

        this.id = usuarioId;
    }

}
