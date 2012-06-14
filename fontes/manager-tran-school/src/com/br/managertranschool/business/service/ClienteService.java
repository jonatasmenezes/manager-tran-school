package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.ClienteFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.ClienteLocalidadeVO;
import com.br.managertranschool.business.vo.ClienteVO;
import com.br.managertranschool.business.vo.PagamentoVO;
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
    
    @Inject
    private PagamentoService pagamentoService;
    
    @Inject
    private ClienteLocalidadeService clienteLocalidadeService;
    
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
     * Método responsável em obter lista de clientes.
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
     * Método responsável em inserir um novo cliente.
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
     * Método responsável em inserir um novo cliente com localidade e pagamento.
     * 
     * @param cliente - Objeto {@link ClienteVO}. 
     * @param clienteLocalidade - Objeto {@link ClienteLocalidadeVO}.
     * @param pagamento - Objeto {@link PagamentoVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void salvar(ClienteVO cliente, ClienteLocalidadeVO clienteLocalidade, PagamentoVO pagamento) {

        this.validarCamposObrigatorios(cliente, clienteLocalidade, pagamento);
        
        if (super.isValido()) {
            clienteDAO.salvar(cliente);
            
            pagamento.setClienteId(cliente.getId());
            pagamentoService.salvar(pagamento);
            
            clienteLocalidade.setClienteId(cliente.getId());
            clienteLocalidadeService.salvar(clienteLocalidade);    
            
            super.addMensagem(R.string.inclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }
    
    /**
     * Método responsável em atualizar dados do clientes.
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
     * Método responsável em atualizar o cliente com localidade e pagamento.
     * 
     * @param cliente - Objeto {@link ClienteVO}. 
     * @param clienteLocalidade - Objeto {@link ClienteLocalidadeVO}.
     * @param pagamento - Objeto {@link PagamentoVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void atualizar(ClienteVO cliente, ClienteLocalidadeVO clienteLocalidade, PagamentoVO pagamento) throws Exception {

        this.validarIdObrigatorio(cliente);
        
        if (super.isValido()) {
            
            clienteLocalidade.setClienteId(cliente.getId());
            pagamento.setClienteId(cliente.getId());
            
            this.validarCamposObrigatorios(cliente, clienteLocalidade, pagamento);
            
            if (super.isValido()) {
                clienteDAO.atualizar(cliente);
                
                pagamentoService.atualizar(pagamento);
                
                clienteLocalidadeService.atualizar(clienteLocalidade);    
                
                super.addMensagem(R.string.alteracao_sucesso, TipoMensagemList.INFORMACAO);
            }
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
        if (super.isNullOrEmpty(cliente.getId())) {
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
        if (super.isNullOrEmpty(cliente.getCpf())) {
            super.addMensagem(R.string.cliente_cpf_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (super.isNullOrEmpty(cliente.getNome())) {
            super.addMensagem(R.string.cliente_nome_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (super.isNullOrEmpty(cliente.getCidadeId())) {
            super.addMensagem(R.string.cliente_cidade_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (super.isNullOrEmpty(cliente.getEndereco())) {
            super.addMensagem(R.string.cliente_endereco_obrigatorio, TipoMensagemList.ERRO);
        }
       
        if (super.isNullOrEmpty(cliente.getBairro())) {
            super.addMensagem(R.string.cliente_bairro_obrigatorio, TipoMensagemList.ERRO);
        }
    }
    
    /**
     * Método valida se campos obrigatorios foram informados.
     * 
     * @param cliente - Objeto ClienteVO
     * @param cliente - Objeto ClienteLocalidadeVO
     * @param cliente - Objeto PagamentoVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarCamposObrigatorios(ClienteVO cliente, ClienteLocalidadeVO clienteLocalidade, PagamentoVO pagamento) {
        this.validarCamposObrigatorios(cliente);
               
        if (super.isNullOrEmpty(clienteLocalidade.getLocalidadeId())) {
            super.addMensagem(R.string.cliente_localidade_id_localidade_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (super.isNullOrEmpty(pagamento.getTipoPagamento())) {
            super.addMensagem(R.string.pagamento_tipo_pagamento_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (super.isNullOrEmpty(pagamento.getValor())) {
            super.addMensagem(R.string.pagamento_valor, TipoMensagemList.ERRO);
        }
        
        if (super.isNullOrEmpty(pagamento.getVencimento())) {
            super.addMensagem(R.string.pagamento_data_vencimento_obrigatorio, TipoMensagemList.ERRO);
        }
    }
    
}
