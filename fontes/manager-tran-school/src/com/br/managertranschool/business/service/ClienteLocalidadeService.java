package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.ClienteLocalidadeFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.ClienteLocalidadeVO;
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
     * Método responsável em obter lista de entidades {@link ClienteLocalidadeVO}.
     * 
     * @param filter - Filtro da entidade {@link ClienteLocalidadeFilter}.
     * @return - Lista de entidades.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public List<ClienteLocalidadeVO> pesquisar(ClienteLocalidadeFilter filter) {

        List<ClienteLocalidadeVO> clienteLocalidadeList = new ArrayList<ClienteLocalidadeVO>();
        
        clienteLocalidadeList = clienteLocalidadeDAO.pesquisar(filter);
        if (clienteLocalidadeList.isEmpty()) {
            super.addMensagem(R.string.pesquisa_nao_encontrou_resultados, TipoMensagemList.INFORMACAO);
        }
        
        return clienteLocalidadeList;
    }
    
    /**
     * Método responsável em inserir um novo usuário.
     * 
     * @param clienteLocalidade - Objeto {@link ClienteLocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(ClienteLocalidadeVO clienteLocalidade) {

        this.validarIdObrigatorio(clienteLocalidade);

        if (super.isValido()) {
            clienteLocalidadeDAO.salvar(clienteLocalidade);
            super.addMensagem(R.string.inclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em atualizar dados.
     * 
     * @param clienteLocalidade - Objeto {@link ClienteLocalidadeVO}.
     * @throws Exception
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void atualizar(ClienteLocalidadeVO clienteLocalidade) throws Exception {

        this.validarIdObrigatorio(clienteLocalidade);
        
        if (super.isValido()) {
            clienteLocalidadeDAO.atualizar(clienteLocalidade); 
            super.addMensagem(R.string.alteracao_sucesso, TipoMensagemList.INFORMACAO);
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
            clienteLocalidadeDAO.delete(clienteLocalidade);
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
            super.addMensagem(R.string.cliente_localidade_id_cliente_obrigatorio, TipoMensagemList.ERRO);
        }

        if (super.isNullOrEmpty(clienteLocalidade.getLocalidadeId())) {
            super.addMensagem(R.string.cliente_localidade_id_localidade_obrigatorio, TipoMensagemList.ERRO);
        }
    }
}
