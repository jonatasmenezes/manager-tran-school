package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.ClienteRotaFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.ClienteRotaVO;
import com.br.managertranschool.dao.ClienteRotaDAO;

/**
 * Classe de negocio responsavel pela entidade de ClienteRota.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 11/05/2012
 */
public class ClienteRotaService extends BaseService {

    @Inject
    private ClienteRotaDAO clienteRotaDAO;

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteRotaService() {

        super();
    }

    /**
     * Método responsável em obter lista de entidades {@link ClienteRotaVO}.
     * 
     * @param filter - Filtro da entidade {@link ClienteRotaFilter}.
     * @return - Lista de entidades.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public List<ClienteRotaVO> pesquisar(ClienteRotaFilter filter) {

        List<ClienteRotaVO> clienteRotaList = new ArrayList<ClienteRotaVO>();
        
        clienteRotaList = clienteRotaDAO.pesquisar(filter);
        if (clienteRotaList.isEmpty()) {
            super.addMensagem(R.string.pesquisa_nao_encontrou_resultados, TipoMensagemList.INFORMACAO);
        }
        
        return clienteRotaList;
    }
    
    /**
     * Método responsável em inserir um novo usuário.
     * 
     * @param clienteRota - Objeto {@link ClienteRotaVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(ClienteRotaVO clienteRota) {

        this.validarIdObrigatorio(clienteRota);

        if (super.isValido()) {
            clienteRotaDAO.salvar(clienteRota);
            super.addMensagem(R.string.inclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em excluir dados do clienteRota.
     * 
     * @param clienteRota - Objeto {@link ClienteRotaVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception
     */
    public void delete(ClienteRotaVO clienteRota) throws Exception {

        this.validarIdObrigatorio(clienteRota);

        if (super.isValido()) {
            clienteRotaDAO.delete(clienteRota);
            super.addMensagem(R.string.exclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método valida se id foi informado.
     * 
     * @param clienteRota - Objeto ClienteRotaVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarIdObrigatorio(ClienteRotaVO clienteRota) {

        if (super.isNullOrEmpty(clienteRota.getClienteId())) {
            super.addMensagem(R.string.cliente_rota_id_cliente_obrigatorio, TipoMensagemList.ERRO);
        }

        if (super.isNullOrEmpty(clienteRota.getRotaId())) {
            super.addMensagem(R.string.cliente_rota_id_rota_obrigatorio, TipoMensagemList.ERRO);
        }
    }
}
