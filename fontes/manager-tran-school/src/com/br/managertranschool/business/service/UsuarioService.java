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
     * M�todo respons�vel em realizar a busca de usuario por id.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @return Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public UsuarioVO buscarPorId(UsuarioVO usuario) {
        return null;
    }

    /**
     * M�todo respons�vel em obter lista de usu�rios.
     * 
     * @param filter - Filtro de Usuario {@link UsuarioFilter}.
     * @return - Lista de usu�rios.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public List<UsuarioVO> pesquisar(UsuarioFilter filter) {
        return null;
    }

    /**
     * M�todo respons�vel em inserir um novo usu�rio.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void salvar(UsuarioVO usuario) {
    }

    /**
     * M�todo respons�vel em atualizar dados do usu�rio.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void atualizar(UsuarioVO usuario) {
    }
    
    /**
     * M�todo respons�vel em excluir dados do usuario.
     * 
     * @param usuario - Objeto {@link UsuarioVO}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void delete(UsuarioVO usuario) {   
    }
    
}
