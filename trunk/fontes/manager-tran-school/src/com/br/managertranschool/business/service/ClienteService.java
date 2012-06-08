package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.ClienteFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.ClienteVO;
import com.br.managertranschool.dao.ClienteDAO;

/**
 * Classe de negocio responsavel pela entidade de Cliente.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 07/05/2012
 */
public class ClienteService extends BaseService {
    
    @Inject
    private ClienteDAO clienteDAO;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteService() {

        super();
    }

    /**
     * Método responsável em realizar a busca de cliente por id.
     * 
     * @param cliente - Objeto {@link ClienteVO}.
     * @return Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public ClienteVO buscarPorId(ClienteVO cliente) {

        this.validarIdObrigatorio(cliente);
        ClienteVO clienteReturn = null;
        
        if (super.isValido()) {
            clienteReturn = clienteDAO.buscarPorId(cliente.getId()); 
        }
        
        return clienteReturn;
    }

    /**
     * Método responsável em obter lista de usuários.
     * 
     * @param filter - Filtro de Cliente {@link ClienteFilter}.
     * @return - Lista de usuários.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<ClienteVO> pesquisar(ClienteFilter filter) {

        List<ClienteVO> clienteList = new ArrayList<ClienteVO>();
        
        clienteList = clienteDAO.pesquisar(filter);
        if (clienteList.isEmpty()) {
            super.addMensagem(R.string.pesquisa_nao_encontrou_resultados, TipoMensagemList.INFORMACAO);
        }
        
        return clienteList;
    }

    /**
     * Método responsável em inserir um novo usuário.
     * 
     * @param cliente - Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(ClienteVO cliente) {

        this.validarCamposObrigatorios(cliente);
        
        if (super.isValido()) {
            clienteDAO.salvar(cliente); 
            super.addMensagem(R.string.inclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em atualizar dados do usuário.
     * 
     * @param cliente - Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void atualizar(ClienteVO cliente) throws Exception {

        this.validarIdObrigatorio(cliente);
        this.validarCamposObrigatorios(cliente);
        
        if (super.isValido()) {
            clienteDAO.atualizar(cliente); 
            super.addMensagem(R.string.alteracao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em excluir dados do cliente.
     * 
     * @param cliente - Objeto {@link ClienteVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void delete(ClienteVO cliente) throws Exception {

        this.validarIdObrigatorio(cliente);
        
        if (super.isValido()) {
            clienteDAO.delete(cliente.getId()); 
            super.addMensagem(R.string.exclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

      
    /**
     * Método valida se id foi informado.
     * 
     * @param cliente - Objeto ClienteVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarIdObrigatorio(ClienteVO cliente) {
        if (!super.isNotNullAndNotEmpty(cliente.getId())) {
            super.addMensagem(R.string.cliente_id_obrigatorio, TipoMensagemList.ERRO);
        }
    }
    
    /**
     * Método valida se campos obrigatorios foram informados.
     * 
     * @param cliente - Objeto ClienteVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarCamposObrigatorios(ClienteVO cliente) {
        if (!super.isNotNullAndNotEmpty(cliente.getCpf())) {
            super.addMensagem(R.string.cliente_cpf_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (!super.isNotNullAndNotEmpty(cliente.getNome())) {
            super.addMensagem(R.string.cliente_nome_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (!super.isNotNullAndNotEmpty(cliente.getCidadeId())) {
            super.addMensagem(R.string.cliente_cidade_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (!super.isNotNullAndNotEmpty(cliente.getEndereco())) {
            super.addMensagem(R.string.cliente_endereco_obrigatorio, TipoMensagemList.ERRO);
        }
       
        if (!super.isNotNullAndNotEmpty(cliente.getBairro())) {
            super.addMensagem(R.string.cliente_bairro_obrigatorio, TipoMensagemList.ERRO);
        }
    }
    
}
