package com.br.managertranschool.architecture;

import java.util.ArrayList;
import java.util.List;

import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.MensagemVO;

/**
 * Classe Pai de todas classes Service da aplicação.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 03/05/2012
 */
public class BaseService {

    private List<MensagemVO> mensagens = new ArrayList<MensagemVO>();

    /**
     * Construtor padrão.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public BaseService() {

        super();
    }

    /**
     * Método adiciona uma mensagem com id e tipo.
     * 
     * @param id - Id da mensagem no resource.
     * @param tipo - Tipo de mensagem.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected void addMensagem(Integer id, TipoMensagemList tipo) {

        MensagemVO mensagem = new MensagemVO(id, tipo);
        this.mensagens.add(mensagem);
    }

    /**
     * Método adiciona uma mensagem com id, tipo e parametros.
     * 
     * @param id - Id da mensagem no resource.
     * @param tipo - Tipo de mensagem.
     * @param parametros - Parametros da mensagem.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected void addMensagem(Integer id, TipoMensagemList tipo, Object[] parametros) {

        MensagemVO mensagem = new MensagemVO(id, parametros, tipo);
        this.mensagens.add(mensagem);
    }

    /**
     * Método verificar se objeto é nulo ou vazio no caso de String.
     * 
     * @param object - Objeto a ser verificado.
     * @return True se não nulo e não vazio.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected boolean isNullOrEmpty(Object object) {

        boolean isNull = true;

        if (object != null) {
            isNull = false;
            if (object instanceof String) {
                isNull = object.toString().trim().length() == 0;
            }
        }

        return isNull;
    }
    
    /**
     * Método verificar se não existem mensagens geradas pelo service.
     * 
     * @return Se não existir mensagens no service retorna true. Se não, false.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public boolean isValido() {

        boolean valido = true;

        for (MensagemVO mensagem : this.mensagens) {
            if (mensagem.getTipo().equals(TipoMensagemList.ERRO)) {
                valido = false;
                break;
            }
        }

        return valido;
    }

    /**
     * @return the idsMensagens
     */
    public List<MensagemVO> getMensagens() {

        List<MensagemVO> retorno = new ArrayList<MensagemVO>();
        retorno.addAll(this.mensagens);
        this.mensagens.clear();
        return retorno;
    }

}
