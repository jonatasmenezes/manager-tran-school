package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.LocalidadeFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.LocalidadeVO;
import com.br.managertranschool.dao.LocalidadeDAO;

/**
 * Classe de negocio responsavel pela entidade de Localidade.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 07/05/2012
 */
public class LocalidadeService extends BaseService {
    
    @Inject
    private LocalidadeDAO localidadeDAO;
    
    /**
     * Construtor padr�o.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public LocalidadeService() {

        super();
    }

    /**
     * M�todo respons�vel em realizar a busca de localidade por id.
     * 
     * @param localidade - Objeto {@link LocalidadeVO}.
     * @return Objeto {@link LocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public LocalidadeVO buscarPorId(LocalidadeVO localidade) {

        this.validarIdObrigatorio(localidade);
        LocalidadeVO localidadeReturn = null;
        
        if (super.isValido()) {
            localidadeReturn = localidadeDAO.buscarPorId(localidade.getId()); 
        }
        
        return localidadeReturn;
    }

    /**
     * M�todo respons�vel em obter lista de usu�rios.
     * 
     * @param filter - Filtro de Localidade {@link LocalidadeFilter}.
     * @return - Lista de usu�rios.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public List<LocalidadeVO> pesquisar(LocalidadeFilter filter) {

        List<LocalidadeVO> localidadeList = new ArrayList<LocalidadeVO>();
        
        localidadeList = localidadeDAO.pesquisar(filter);
        if (localidadeList.isEmpty()) {
            super.addMensagem(R.string.pesquisa_nao_encontrou_resultados, TipoMensagemList.INFORMACAO);
        }
        
        return localidadeList;
    }

    /**
     * M�todo respons�vel em inserir um novo usu�rio.
     * 
     * @param localidade - Objeto {@link LocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(LocalidadeVO localidade) {

        this.validarCamposObrigatorios(localidade);
        
        if (super.isValido()) {
            localidadeDAO.salvar(localidade); 
            super.addMensagem(R.string.inclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * M�todo respons�vel em atualizar dados do usu�rio.
     * 
     * @param localidade - Objeto {@link LocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void atualizar(LocalidadeVO localidade) throws Exception {

        this.validarIdObrigatorio(localidade);
        this.validarCamposObrigatorios(localidade);
        
        if (super.isValido()) {
            localidadeDAO.atualizar(localidade); 
            super.addMensagem(R.string.alteracao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * M�todo respons�vel em excluir dados do localidade.
     * 
     * @param localidade - Objeto {@link LocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception 
     */
    public void delete(LocalidadeVO localidade) throws Exception {

        this.validarIdObrigatorio(localidade);
        
        if (super.isValido()) {
            localidadeDAO.delete(localidade.getId()); 
            super.addMensagem(R.string.exclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

      
    /**
     * M�todo valida se id foi informado.
     * 
     * @param localidade - Objeto LocalidadeVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarIdObrigatorio(LocalidadeVO localidade) {
        if (super.isNullOrEmpty(localidade.getId())) {
            super.addMensagem(R.string.localidade_id_obrigatorio, TipoMensagemList.ERRO);
        }
    }
    
    /**
     * M�todo valida se campos obrigatorios foram informados.
     * 
     * @param localidade - Objeto LocalidadeVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarCamposObrigatorios(LocalidadeVO localidade) {
        if (super.isNullOrEmpty(localidade.getLatitude())) {
            super.addMensagem(R.string.localidade_latitude_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (super.isNullOrEmpty(localidade.getLongitude())) {
            super.addMensagem(R.string.localidade_longitude_obrigatorio, TipoMensagemList.ERRO);
        }        
        
        if (super.isNullOrEmpty(localidade.getDescricao())) {
            super.addMensagem(R.string.localidade_descricao_obrigatorio, TipoMensagemList.ERRO);
        }

    }
    
}
