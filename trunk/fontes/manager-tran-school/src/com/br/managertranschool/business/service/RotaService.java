package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.RotaFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.RotaVO;
import com.br.managertranschool.dao.RotaDAO;

/**
 * Classe de negocio responsavel pela entidade de Rota.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 07/05/2012
 */
public class RotaService extends BaseService {
    
    @Inject
    private RotaDAO rotaDAO;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaService() {

        super();
    }

    /**
     * Método responsável em realizar a busca de rota por id.
     * 
     * @param rota - Objeto {@link RotaVO}.
     * @return Objeto {@link RotaVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaVO buscarPorId(RotaVO rota) {

        this.validarIdObrigatorio(rota);
        RotaVO rotaReturn = null;
        
        if (super.isValido()) {
            rotaReturn = rotaDAO.buscarPorId(rota.getId()); 
        }
        
        return rotaReturn;
    }

    /**
     * Método responsável em obter lista de usuários.
     * 
     * @param filter - Filtro de Rota {@link RotaFilter}.
     * @return - Lista de usuários.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<RotaVO> pesquisar(RotaFilter filter) {

        List<RotaVO> rotaList = new ArrayList<RotaVO>();
        
        rotaList = rotaDAO.pesquisar(filter);
        if (rotaList.isEmpty()) {
            super.addMensagem(R.string.pesquisa_nao_encontrou_resultados, TipoMensagemList.INFORMACAO);
        }
        
        return rotaList;
    }

    /**
     * Método responsável em inserir um novo usuário.
     * 
     * @param rota - Objeto {@link RotaVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(RotaVO rota) {

        this.validarCamposObrigatorios(rota);
        
        if (super.isValido()) {
            rotaDAO.salvar(rota); 
            super.addMensagem(R.string.inclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em atualizar dados do usuário.
     * 
     * @param rota - Objeto {@link RotaVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void atualizar(RotaVO rota) throws Exception {

        this.validarIdObrigatorio(rota);
        this.validarCamposObrigatorios(rota);
        
        if (super.isValido()) {
            rotaDAO.atualizar(rota); 
            super.addMensagem(R.string.alteracao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em excluir dados do rota.
     * 
     * @param rota - Objeto {@link RotaVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void delete(RotaVO rota) throws Exception {

        this.validarIdObrigatorio(rota);
        
        if (super.isValido()) {
            rotaDAO.delete(rota.getId()); 
            super.addMensagem(R.string.exclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

      
    /**
     * Método valida se id foi informado.
     * 
     * @param rota - Objeto RotaVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarIdObrigatorio(RotaVO rota) {
        if (!super.isNotNullAndNotEmpty(rota.getId())) {
            super.addMensagem(R.string.rota_id_obrigatorio, TipoMensagemList.ERRO);
        }
    }
    
    /**
     * Método valida se campos obrigatorios foram informados.
     * 
     * @param rota - Objeto RotaVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarCamposObrigatorios(RotaVO rota) {
        if (!super.isNotNullAndNotEmpty(rota.getDescricao())) {
            super.addMensagem(R.string.rota_descricao_obrigatorio, TipoMensagemList.ERRO);
        }

    }
    
}
