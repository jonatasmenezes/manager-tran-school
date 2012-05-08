package com.br.managertranschool.business.service;

import java.util.List;

import com.br.managertranschool.business.filter.UsuarioFilter;
import com.br.managertranschool.business.vo.UsuarioVO;


/**
 * Classe de negocio responsavel pela entidade de Usuario.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 03/05/2012
 */
public class UsuarioService {

    /**
     * Método responsável em realizar a busca de usuario por id.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @return Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public UsuarioVO buscarPorId(UsuarioVO usuario) {
        return null;
    }

    /**
     * Método responsável em obter lista de usuários.
     * 
     * @param filter - Filtro de Usuario {@link UsuarioFilter}.
     * @return - Lista de usuários.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public List<UsuarioVO> pesquisar(UsuarioFilter filter) {
        return null;
    }

    /**
     * Método responsável em inserir um novo usuário.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void salvar(UsuarioVO usuario) {
    }

    /**
     * Método responsável em atualizar dados do usuário.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void atualizar(UsuarioVO usuario) {
    }
    
    /**
     * Método responsável em excluir dados do usuario.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void delete(UsuarioVO usuario) {   
    }
    
}
