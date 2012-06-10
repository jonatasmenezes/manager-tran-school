package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.CidadeFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.CidadeVO;
import com.br.managertranschool.dao.CidadeDAO;

/**
 * Classe de negocio responsavel pela entidade de Cidade.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 07/05/2012
 */
public class CidadeService extends BaseService {
    
    @Inject
    private CidadeDAO cidadeDAO;
    
    /**
     * Construtor padr�o.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public CidadeService() {

        super();
    }

    /**
     * M�todo respons�vel em realizar a busca de cidade por id.
     * 
     * @param cidade - Objeto {@link CidadeVO}.
     * @return Objeto {@link CidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public CidadeVO buscarPorId(CidadeVO cidade) {

        this.validarIdObrigatorio(cidade);
        CidadeVO cidadeReturn = null;
        
        if (super.isValido()) {
            cidadeReturn = cidadeDAO.buscarPorId(cidade.getId()); 
        }
        
        return cidadeReturn;
    }

    /**
     * M�todo respons�vel em obter lista de usu�rios.
     * 
     * @param filter - Filtro de Cidade {@link CidadeFilter}.
     * @return - Lista de usu�rios.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<CidadeVO> pesquisar(CidadeFilter filter) {

        List<CidadeVO> cidadeList = new ArrayList<CidadeVO>();
        
        cidadeList = cidadeDAO.pesquisar(filter);
        if (cidadeList.isEmpty()) {
            super.addMensagem(R.string.pesquisa_nao_encontrou_resultados, TipoMensagemList.INFORMACAO);
        }
        
        return cidadeList;
    }

    /**
     * M�todo respons�vel em inserir um novo usu�rio.
     * 
     * @param cidade - Objeto {@link CidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(CidadeVO cidade) {

        this.validarCamposObrigatorios(cidade);
        
        if (super.isValido()) {
            cidadeDAO.salvar(cidade); 
            super.addMensagem(R.string.inclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * M�todo respons�vel em atualizar dados do usu�rio.
     * 
     * @param cidade - Objeto {@link CidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void atualizar(CidadeVO cidade) throws Exception {

        this.validarIdObrigatorio(cidade);
        this.validarCamposObrigatorios(cidade);
        
        if (super.isValido()) {
            cidadeDAO.atualizar(cidade); 
            super.addMensagem(R.string.alteracao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * M�todo respons�vel em excluir dados do cidade.
     * 
     * @param cidade - Objeto {@link CidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void delete(CidadeVO cidade) throws Exception {

        this.validarIdObrigatorio(cidade);
        
        if (super.isValido()) {
            cidadeDAO.delete(cidade.getId()); 
            super.addMensagem(R.string.exclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

      
    /**
     * M�todo valida se id foi informado.
     * 
     * @param cidade - Objeto CidadeVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarIdObrigatorio(CidadeVO cidade) {
        if (super.isNullOrEmpty(cidade.getId())) {
            super.addMensagem(R.string.cidade_id_obrigatorio, TipoMensagemList.ERRO);
        }
    }
    
    /**
     * M�todo valida se campos obrigatorios foram informados.
     * 
     * @param cidade - Objeto CidadeVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarCamposObrigatorios(CidadeVO cidade) {
        if (super.isNullOrEmpty(cidade.getEstadoId())) {
            super.addMensagem(R.string.cidade_estado_id_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (super.isNullOrEmpty(cidade.getDescricao())) {
            super.addMensagem(R.string.cidade_descricao_obrigatorio, TipoMensagemList.ERRO);
        }

    }
    
}
