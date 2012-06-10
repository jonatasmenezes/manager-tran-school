package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.PagamentoRealizadoFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.PagamentoRealizadoVO;
import com.br.managertranschool.dao.PagamentoRealizadoDAO;

/**
 * Classe de negocio responsavel pela entidade de PagamentoRealizado.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 07/05/2012
 */
public class PagamentoRealizadoService extends BaseService {
    
    @Inject
    private PagamentoRealizadoDAO pagamentoRealizadoDAO;
    
    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public PagamentoRealizadoService() {

        super();
    }

    /**
     * Método responsável em realizar a busca de pagamentoRealizado por id.
     * 
     * @param pagamentoRealizado - Objeto {@link PagamentoRealizadoVO}.
     * @return Objeto {@link PagamentoRealizadoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public PagamentoRealizadoVO buscarPorId(PagamentoRealizadoVO pagamentoRealizado) {

        this.validarIdObrigatorio(pagamentoRealizado);
        PagamentoRealizadoVO pagamentoRealizadoReturn = null;
        
        if (super.isValido()) {
            pagamentoRealizadoReturn = pagamentoRealizadoDAO.buscarPorId(pagamentoRealizado.getId()); 
        }
        
        return pagamentoRealizadoReturn;
    }

    /**
     * Método responsável em obter lista de usuários.
     * 
     * @param filter - Filtro de PagamentoRealizado {@link PagamentoRealizadoFilter}.
     * @return - Lista de usuários.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<PagamentoRealizadoVO> pesquisar(PagamentoRealizadoFilter filter) {

        List<PagamentoRealizadoVO> pagamentoRealizadoList = new ArrayList<PagamentoRealizadoVO>();
        
        pagamentoRealizadoList = pagamentoRealizadoDAO.pesquisar(filter);
        if (pagamentoRealizadoList.isEmpty()) {
            super.addMensagem(R.string.pesquisa_nao_encontrou_resultados, TipoMensagemList.INFORMACAO);
        }
        
        return pagamentoRealizadoList;
    }

    /**
     * Método responsável em inserir um novo usuário.
     * 
     * @param pagamentoRealizado - Objeto {@link PagamentoRealizadoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(PagamentoRealizadoVO pagamentoRealizado) {

        this.validarCamposObrigatorios(pagamentoRealizado);
        
        if (super.isValido()) {
            pagamentoRealizadoDAO.salvar(pagamentoRealizado); 
            super.addMensagem(R.string.inclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em atualizar dados do usuário.
     * 
     * @param pagamentoRealizado - Objeto {@link PagamentoRealizadoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void atualizar(PagamentoRealizadoVO pagamentoRealizado) throws Exception {

        this.validarIdObrigatorio(pagamentoRealizado);
        this.validarCamposObrigatorios(pagamentoRealizado);
        
        if (super.isValido()) {
            pagamentoRealizadoDAO.atualizar(pagamentoRealizado); 
            super.addMensagem(R.string.alteracao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em excluir dados do pagamentoRealizado.
     * 
     * @param pagamentoRealizado - Objeto {@link PagamentoRealizadoVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void delete(PagamentoRealizadoVO pagamentoRealizado) throws Exception {

        this.validarIdObrigatorio(pagamentoRealizado);
        
        if (super.isValido()) {
            pagamentoRealizadoDAO.delete(pagamentoRealizado.getId()); 
            super.addMensagem(R.string.exclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

      
    /**
     * Método valida se id foi informado.
     * 
     * @param pagamentoRealizado - Objeto PagamentoRealizadoVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarIdObrigatorio(PagamentoRealizadoVO pagamentoRealizado) {
        if (super.isNullOrEmpty(pagamentoRealizado.getId())) {
            super.addMensagem(R.string.pagamento_realizado_id_obrigatorio, TipoMensagemList.ERRO);
        }
    }
    
    /**
     * Método valida se campos obrigatorios foram informados.
     * 
     * @param pagamentoRealizado - Objeto PagamentoRealizadoVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarCamposObrigatorios(PagamentoRealizadoVO pagamentoRealizado) {
        if (super.isNullOrEmpty(pagamentoRealizado.getDataPagamento())) {
            super.addMensagem(R.string.pagamento_realizado_data_pagamento_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (super.isNullOrEmpty(pagamentoRealizado.getReferencia())) {
            super.addMensagem(R.string.pagamento_realizado_mes_ano_referente_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (super.isNullOrEmpty(pagamentoRealizado.getPagamentoId())) {
            super.addMensagem(R.string.pagamento_realizado_pagamento_id_obrigatorio, TipoMensagemList.ERRO);
        }
        
    }
    
    public void efetuarPagamento(PagamentoRealizadoVO pagamentoRealizado){
        
        pagamentoRealizado.setDataPagamento(new Date());
        salvar(pagamentoRealizado);
    }
    
}
