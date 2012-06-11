package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.RotaLocalidadeFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.RotaLocalidadeVO;
import com.br.managertranschool.dao.RotaLocalidadeDAO;

/**
 * Classe de negocio responsavel pela entidade de RotaLocalidade.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 11/05/2012
 */
public class RotaLocalidadeService extends BaseService {

    @Inject
    private RotaLocalidadeDAO rotaLocalidadeDAO;

    /**
     * Construtor padrão.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public RotaLocalidadeService() {

        super();
    }

    /**
     * Método responsável em obter lista de entidades {@link RotaLocalidadeVO}.
     * 
     * @param filter - Filtro da entidade {@link RotaLocalidadeFilter}.
     * @return - Lista de entidades.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public List<RotaLocalidadeVO> pesquisar(RotaLocalidadeFilter filter) {

        List<RotaLocalidadeVO> rotaLocalidadeList = new ArrayList<RotaLocalidadeVO>();
        
        rotaLocalidadeList = rotaLocalidadeDAO.pesquisar(filter);
        if (rotaLocalidadeList.isEmpty()) {
            super.addMensagem(R.string.pesquisa_nao_encontrou_resultados, TipoMensagemList.INFORMACAO);
        }
        
        return rotaLocalidadeList;
    }
    
    /**
     * Método responsável em inserir um novo usuário.
     * 
     * @param rotaLocalidade - Objeto {@link RotaLocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    public void salvar(RotaLocalidadeVO rotaLocalidade) {

        this.validarIdObrigatorio(rotaLocalidade);

        if (super.isValido()) {
            rotaLocalidadeDAO.salvar(rotaLocalidade);
            super.addMensagem(R.string.inclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em excluir dados do rotaLocalidade.
     * 
     * @param rotaLocalidade - Objeto {@link RotaLocalidadeVO}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     * @throws Exception
     */
    public void delete(RotaLocalidadeVO rotaLocalidade) throws Exception {

        this.validarIdObrigatorio(rotaLocalidade);

        if (super.isValido()) {
            rotaLocalidadeDAO.delete(rotaLocalidade);
            super.addMensagem(R.string.exclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método valida se id foi informado.
     * 
     * @param rotaLocalidade - Objeto RotaLocalidadeVO
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void validarIdObrigatorio(RotaLocalidadeVO rotaLocalidade) {

        if (super.isNullOrEmpty(rotaLocalidade.getLocalidadeId())) {
            super.addMensagem(R.string.rota_localidade_id_localidade_obrigatorio, TipoMensagemList.ERRO);
        }

        if (super.isNullOrEmpty(rotaLocalidade.getRotaId())) {
            super.addMensagem(R.string.rota_localidade_id_rota_obrigatorio, TipoMensagemList.ERRO);
        }
    }
}
