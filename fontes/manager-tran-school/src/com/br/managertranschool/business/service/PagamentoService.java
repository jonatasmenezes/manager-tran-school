package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.PagamentoFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.PagamentoVO;
import com.br.managertranschool.dao.PagamentoDAO;

/**
 * Classe de negocio responsavel pela entidade de Pagamento.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 09/05/2012
 */
public class PagamentoService extends BaseService {
    
    @Inject
    private PagamentoDAO pagamentoDAO;
    
    /**
     * Construtor padr�o.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public PagamentoService() {

        super();
    }

    /**
     * M�todo respons�vel em realizar a busca de pagamento por id.
     * 
     * @param pagamento - Objeto {@link PagamentoVO}.
     * @return Objeto {@link PagamentoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public PagamentoVO buscarPorId(PagamentoVO pagamento) {

        this.validarIdObrigatorio(pagamento);
        PagamentoVO pagamentoReturn = null;
        
        if (super.isValido()) {
            pagamentoReturn = pagamentoDAO.buscarPorId(pagamento.getId()); 
        }
        
        return pagamentoReturn;
    }

    /**
     * M�todo respons�vel em obter lista de usu�rios.
     * 
     * @param filter - Filtro de Pagamento {@link PagamentoFilter}.
     * @return - Lista de usu�rios.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<PagamentoVO> pesquisar(PagamentoFilter filter) {

        List<PagamentoVO> pagamentoList = new ArrayList<PagamentoVO>();
        
        pagamentoList = pagamentoDAO.pesquisar(filter);
        if (pagamentoList.isEmpty()) {
            super.addMensagem(R.string.pesquisa_nao_encontrou_resultados, TipoMensagemList.INFORMACAO);
        }
        
        return pagamentoList;
    }

    /**
     * M�todo respons�vel em inserir um novo usu�rio.
     * 
     * @param pagamento - Objeto {@link PagamentoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(PagamentoVO pagamento) {

        this.validarCamposObrigatorios(pagamento);
        
        if (super.isValido()) {
            pagamentoDAO.salvar(pagamento); 
            super.addMensagem(R.string.inclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * M�todo respons�vel em atualizar dados do usu�rio.
     * 
     * @param pagamento - Objeto {@link PagamentoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void atualizar(PagamentoVO pagamento) throws Exception {

        this.validarIdObrigatorio(pagamento);
        this.validarCamposObrigatorios(pagamento);
        
        if (super.isValido()) {
            pagamentoDAO.atualizar(pagamento); 
            super.addMensagem(R.string.alteracao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * M�todo respons�vel em excluir dados do pagamento.
     * 
     * @param pagamento - Objeto {@link PagamentoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void delete(PagamentoVO pagamento) throws Exception {

        this.validarIdObrigatorio(pagamento);
        
        if (super.isValido()) {
            pagamentoDAO.delete(pagamento.getId()); 
            super.addMensagem(R.string.exclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

      
    /**
     * M�todo valida se id foi informado.
     * 
     * @param pagamento - Objeto PagamentoVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarIdObrigatorio(PagamentoVO pagamento) {
        if (!super.isNotNullAndNotEmpty(pagamento.getId())) {
            super.addMensagem(R.string.pagamento_id_obrigatorio, TipoMensagemList.ERRO);
        }
    }
    
    /**
     * M�todo valida se campos obrigatorios foram informados.
     * 
     * @param pagamento - Objeto PagamentoVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarCamposObrigatorios(PagamentoVO pagamento) {
        if (!super.isNotNullAndNotEmpty(pagamento.getValor())) {
            super.addMensagem(R.string.pagamento_valor_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (!super.isNotNullAndNotEmpty(pagamento.getTipoPagamento())) {
            super.addMensagem(R.string.pagamento_tipo_pagamento_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (!super.isNotNullAndNotEmpty(pagamento.getVencimento())) {
            super.addMensagem(R.string.pagamento_vencimento_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (!super.isNotNullAndNotEmpty(pagamento.getClienteId())) {
            super.addMensagem(R.string.pagamento_cliente_id_obrigatorio, TipoMensagemList.ERRO);
        }
       
    }
    
}
