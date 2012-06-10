package com.br.managertranschool.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseService;
import com.br.managertranschool.business.filter.UsuarioFilter;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.UsuarioVO;
import com.br.managertranschool.dao.UsuarioDAO;

/**
 * Classe de negocio responsavel pela entidade de Usuario.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 03/05/2012
 */
public class UsuarioService extends BaseService {
    
    @Inject
    private UsuarioDAO usuarioDAO;
    
    /**
     * Construtor padrão.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public UsuarioService() {

        super();
    }

    /**
     * Método responsável em realizar a busca de usuario por id.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @return Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public UsuarioVO buscarPorId(UsuarioVO usuario) {

        this.validarIdObrigatorio(usuario);
        UsuarioVO usuarioReturn = null;
        
        if (super.isValido()) {
            usuarioReturn = usuarioDAO.buscarPorId(usuario.getId()); 
        }
        
        return usuarioReturn;
    }

    /**
     * Método responsável em obter lista de usuários.
     * 
     * @param filter - Filtro de Usuario {@link UsuarioFilter}.
     * @return - Lista de usuários.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public List<UsuarioVO> pesquisar(UsuarioFilter filter) {

        List<UsuarioVO> usuarioList = new ArrayList<UsuarioVO>();
        
        usuarioList = usuarioDAO.pesquisar(filter);
        if (usuarioList.isEmpty()) {
            super.addMensagem(R.string.pesquisa_nao_encontrou_resultados, TipoMensagemList.INFORMACAO);
        }
        
        return usuarioList;
    }

    /**
     * Método responsável em inserir um novo usuário.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void salvar(UsuarioVO usuario) {

        this.validarCamposObrigatorios(usuario);
        
        if (super.isValido()) {
            usuarioDAO.salvar(usuario); 
            super.addMensagem(R.string.inclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em atualizar dados do usuário.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     * @throws Exception 
     */
    public void atualizar(UsuarioVO usuario) throws Exception {

        this.validarIdObrigatorio(usuario);
        this.validarCamposObrigatorios(usuario);
        
        if (super.isValido()) {
            usuarioDAO.atualizar(usuario); 
            super.addMensagem(R.string.alteracao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em excluir dados do usuario.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     * @throws Exception 
     */
    public void delete(UsuarioVO usuario) throws Exception {

        this.validarIdObrigatorio(usuario);
        
        if (super.isValido()) {
            usuarioDAO.delete(usuario.getId()); 
            super.addMensagem(R.string.exclusao_sucesso, TipoMensagemList.INFORMACAO);
        }
    }

    /**
     * Método responsável em realizar a autenticação de usuario.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @return Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public UsuarioVO autenticarUsuario(UsuarioVO usuario) {

        UsuarioVO usuarioReturn = null;
        
        if (!super.isNullOrEmpty(usuario.getLogin())) {
            super.addMensagem(R.string.usuario_login_obrigatorio, TipoMensagemList.ERRO);
        }
        
        if (!super.isNullOrEmpty(usuario.getSenha())) {
            super.addMensagem(R.string.usuario_senha_obrigatorio, TipoMensagemList.ERRO);
        }
       
        if (super.isValido()) {
            UsuarioFilter filter = new UsuarioFilter(usuario);
            
            List<UsuarioVO> usuarioList = usuarioDAO.pesquisar(filter);
            
            if (!usuarioList.isEmpty()) {
                usuarioReturn = usuarioList.get(0);
            }
        }
        
        return usuarioReturn;
    }
    
    /**
     * Método valida se id foi informado.
     * 
     * @param usuario - Objeto UsuarioVO
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private void validarIdObrigatorio(UsuarioVO usuario) {
        if (super.isNullOrEmpty(usuario.getId())) {
            super.addMensagem(R.string.usuario_id_obrigatorio, TipoMensagemList.ERRO);
        }
    }
    
    /**
     * Método valida se campos obrigatorios foram informados.
     * 
     * @param usuario - Objeto UsuarioVO
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private void validarCamposObrigatorios(UsuarioVO usuario) {
        if (super.isNullOrEmpty(usuario.getLogin())) {
            super.addMensagem(R.string.usuario_login_obrigatorio, TipoMensagemList.ERRO);
        }
        if (super.isNullOrEmpty(usuario.getSenha())) {
            super.addMensagem(R.string.usuario_senha_obrigatorio, TipoMensagemList.ERRO);
        }
        if (super.isNullOrEmpty(usuario.getTipoUsuario())) {
            super.addMensagem(R.string.usuario_tipo_obrigatorio, TipoMensagemList.ERRO);
        }
        if (super.isNullOrEmpty(usuario.getNome())) {
            super.addMensagem(R.string.usuario_nome_obrigatorio, TipoMensagemList.ERRO);
        }
    }
    
}
