package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.RotaHistoricoFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.RotaHistoricoVO;
import com.br.managertranschool.dao.RotaHistoricoDAO;

/**
 * Classe de negocio responsavel pela entidade de RotaHistorico.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 07/05/2012
 */
public class RotaHistoricoService extends BaseService {
    
    @Inject
    private RotaHistoricoDAO rotaHistoricoDAO;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaHistoricoService() {

        super();
    }

    /**
     * Método responsável em realizar a busca de rotaHistorico por id.
     * 
     * @param rotaHistorico - Objeto {@link RotaHistoricoVO}.
     * @return Objeto {@link RotaHistoricoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaHistoricoVO buscarPorId(RotaHistoricoVO rotaHistorico) {

        this.validarIdObrigatorio(rotaHistorico);
        RotaHistoricoVO rotaHistoricoReturn = null;
        
        if (super.isValido()) {
            rotaHistoricoReturn = rotaHistoricoDAO.buscarPorId(rotaHistorico.getId()); 
        }
        
        return rotaHistoricoReturn;
    }

    /**
     * Método responsável em obter lista de usuários.
     * 
     * @param filter - Filtro de RotaHistorico {@link RotaHistoricoFilter}.
     * @return - Lista de usuários.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<RotaHistoricoVO> pesquisar(RotaHistoricoFilter filter) {

        List<RotaHistoricoVO> rotaHistoricoList = new ArrayList<RotaHistoricoVO>();
        
        rotaHistoricoList = rotaHistoricoDAO.pesquisar(filter);
        if (rotaHistoricoList.isEmpty()) {
            super.addMensagem(R.string.pesquisa_nao_encontrou_resultados, TipoMensagemList.INFORMACAO);
        }
        
        return rotaHistoricoList;
    }

    /**
     * Método responsável em inserir um novo usuário.
     * 
     * @param rotaHistorico - Objeto {@link RotaHistoricoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(RotaHistoricoVO rotaHistorico) {

        this.validarCamposObrigatorios(rotaHistorico);
        
        if (super.isValido()) {
            rotaHistoricoDAO.salvar(rotaHistorico); 
            super.addMensagem(R.string.inclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em atualizar dados do usuário.
     * 
     * @param rotaHistorico - Objeto {@link RotaHistoricoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void atualizar(RotaHistoricoVO rotaHistorico) throws Exception {
        
        rotaHistorico.setData(new Date());
        
        this.validarIdObrigatorio(rotaHistorico);
        this.validarCamposObrigatorios(rotaHistorico);
        
        if (super.isValido()) {            
            rotaHistoricoDAO.atualizar(rotaHistorico); 
            super.addMensagem(R.string.alteracao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em excluir dados do rotaHistorico.
     * 
     * @param rotaHistorico - Objeto {@link RotaHistoricoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void delete(RotaHistoricoVO rotaHistorico) throws Exception {

        this.validarIdObrigatorio(rotaHistorico);
        
        if (super.isValido()) {
            rotaHistoricoDAO.delete(rotaHistorico.getId()); 
            super.addMensagem(R.string.exclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

      
    /**
     * Método valida se id foi informado.
     * 
     * @param rotaHistorico - Objeto RotaHistoricoVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarIdObrigatorio(RotaHistoricoVO rotaHistorico) {
        if (super.isNullOrEmpty(rotaHistorico.getId())) {
            super.addMensagem(R.string.rota_historico_id_obrigatorio, TipoMensagemList.ERRO);
        }
    }
    
    /**
     * Método valida se campos obrigatorios foram informados.
     * 
     * @param rotaHistorico - Objeto RotaHistoricoVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarCamposObrigatorios(RotaHistoricoVO rotaHistorico) {
        if (super.isNullOrEmpty(rotaHistorico.getRotaId())) {
            super.addMensagem(R.string.rota_historico_rota_id_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (super.isNullOrEmpty(rotaHistorico.getData())) {
            super.addMensagem(R.string.rota_historico_data_obrigatorio, TipoMensagemList.ERRO);
        }        
        
        if (super.isNullOrEmpty(rotaHistorico.getUsuarioId())) {
            super.addMensagem(R.string.rota_historico_usuario_id_obrigatorio, TipoMensagemList.ERRO);
        }

    }
    
}
