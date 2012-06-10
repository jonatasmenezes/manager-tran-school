package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.ClienteLocalidadeFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.ClienteLocalidadeVO;
import com.br.managertranschool.business.vo.RotaVO;
import com.br.managertranschool.dao.ClienteLocalidadeDAO;

/**
 * Classe de negocio responsavel pela entidade de ClienteLocalidade.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 07/05/2012
 */
public class ClienteLocalidadeService extends BaseService {
    
    @Inject
    private ClienteLocalidadeDAO clienteLocalidadeDAO;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteLocalidadeService() {

        super();
    }


    /**
     * Método responsável em inserir um novo usuário.
     * 
     * @param clienteLocalidade - Objeto {@link ClienteLocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(ClienteLocalidadeVO clienteLocalidade) {

        this.validarCamposObrigatorios(clienteLocalidade);
        
        if (super.isValido()) {
            clienteLocalidadeDAO.salvar(clienteLocalidade); 
            super.addMensagem(R.string.inclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }



    /**
     * Método responsável em excluir dados do clienteLocalidade.
     * 
     * @param clienteLocalidade - Objeto {@link ClienteLocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void delete(ClienteLocalidadeVO clienteLocalidade) throws Exception {

        this.validarIdObrigatorio(clienteLocalidade);
        
        if (super.isValido()) {
            clienteLocalidadeDAO.delete(clienteLocalidade.getId()); 
            super.addMensagem(R.string.exclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

      
    /**
     * Método valida se id foi informado.
     * 
     * @param clienteLocalidade - Objeto ClienteLocalidadeVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarIdObrigatorio(ClienteLocalidadeVO clienteLocalidade) {
        if (super.isNullOrEmpty(clienteLocalidade.getClienteId())) {
            super.addMensagem(R.string.clienteLocalidade_id_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (super.isNullOrEmpty(clienteLocalidade.getLocalidadeId())) {
            super.addMensagem(R.string.clienteLocalidade_id_obrigatorio, TipoMensagemList.ERRO);
        }
    }
    
    /**
     * Método valida se campos obrigatorios foram informados.
     * 
     * @param rota - Objeto ClieteLocalidadeVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarCamposObrigatorios(ClienteLocalidadeVO clienteLocalidade) {
        if (super.isNullOrEmpty(clienteLocalidade.getClienteId())) {
            super.addMensagem(R.string.clienteLocalidade_id_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (super.isNullOrEmpty(clienteLocalidade.getLocalidadeId())) {
            super.addMensagem(R.string.clienteLocalidade_id_obrigatorio, TipoMensagemList.ERRO);
        }

    }
    
}
