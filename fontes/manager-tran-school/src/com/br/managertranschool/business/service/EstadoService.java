package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.EstadoFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.EstadoVO;
import com.br.managertranschool.dao.EstadoDAO;


/**
 * Classe de negocio responsavel pela entidade de Estado.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 07/06/2012
 */
public class EstadoService extends BaseService {

    @Inject
    private EstadoDAO estadoDAO;
    
    /**
     * Construtor padrão.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public EstadoService() {

        super();
    }
    
    /**
     * Método responsável em obter lista de estados.
     * 
     * @param filter - Filtro de Estado {@link EstadoFilter}.
     * @return - Lista de estados.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public List<EstadoVO> pesquisar(EstadoFilter filter) {

        List<EstadoVO> estadoList = new ArrayList<EstadoVO>();
        
        estadoList = estadoDAO.pesquisar(filter);
        if (estadoList.isEmpty()) {
            super.addMensagem(R.string.pesquisa_nao_encontrou_resultados, TipoMensagemList.INFORMACAO);
        }
        
        return estadoList;
    }    
}
