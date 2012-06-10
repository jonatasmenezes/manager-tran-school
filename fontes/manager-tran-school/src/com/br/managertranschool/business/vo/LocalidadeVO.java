package com.br.managertranschool.business.vo;

import java.io.Serializable;

/**
 * Classe VO responsável pela entidade de LOCALIDADE.
 * 
 * @author Jeferson Almeida (jef.almeida.07@gmail.com)
 * @since 19/05/2012
 */
public class LocalidadeVO implements Serializable {

    private static final long serialVersionUID = 7075097032643255169L;

    public static final String TABLE = "LOCALIDADE";

    public static final String ID_LOCALIDADE = "ID_LOCALIDADE";

    private Long id;

    public static final String NR_LATITUDE = "NR_LATITUDE";

    private Double latitude;

    public static final String NR_LONGITUDE = "NR_LONGITUDE";

    private Double longitude;

    public static final String TX_DESCRICAO = "TX_DESCRICAO";

    private String descricao;

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public LocalidadeVO() {

        super();
    }

    /**
     * Método obtem nome das colunas da tabela LOCALIDADE
     * 
     * @return Array de string
     * @author Jeferson Almeida (jef.almeida.07@gmail.com)
     */
    public static final String[] getNomesColunas() {

        return new String[] { ID_LOCALIDADE, NR_LATITUDE, NR_LONGITUDE, TX_DESCRICAO };
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
     * @return the latitude
     */
    public Double getLatitude() {

        return latitude;
    }

    /**
     * @param latitude - the latitude to set
     */
    public void setLatitude(Double latitude) {

        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {

        return longitude;
    }

    /**
     * @param longitude - the longitude to set
     */
    public void setLongitude(Double longitude) {

        this.longitude = longitude;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {

        return descricao;
    }

    /**
     * @param descricao - the descricao to set
     */
    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

}
