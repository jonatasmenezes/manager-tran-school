package com.br.managertranschool.architecture;

import java.util.List;

import roboguice.activity.RoboActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.br.managertranschool.R;
import com.br.managertranschool.business.list.TipoMensagemList;
import com.br.managertranschool.business.vo.MensagemVO;

/**
 * Classe Pai de todas classes Activity da aplicação.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 16/05/2012
 */
public class BaseActivity extends RoboActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    /**
     * Construtor padrão.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public BaseActivity() {

        super();
    }

    public void setMessages(List<MensagemVO> mensagens) {
              
        if (!mensagens.isEmpty()) {
            StringBuilder mensagensErro = new StringBuilder();
            StringBuilder mensagensInfo = new StringBuilder();
            
            for (MensagemVO item : mensagens) {
                if (item.getId() != null) {
                    
                    if (item.getTipo().equals(TipoMensagemList.INFORMACAO)) {
                        if (mensagensInfo.length() > 1) {
                            mensagensInfo.append("\n");
                        }
                        
                        if (item.getParametros() != null) {                        
                            mensagensInfo.append(super.getString(item.getId(), item.getParametros()));
                        } else {
                            mensagensInfo.append(super.getString(item.getId()));
                        }
                    } else {
                        if (mensagensErro.length() > 1) {
                            mensagensErro.append("\n");
                        }
                        
                        mensagensErro.append(" - ");
                        if (item.getParametros() != null) {
                            mensagensErro.append(super.getString(item.getId(), item.getParametros()));
                        } else {
                            mensagensErro.append(super.getString(item.getId()));
                        }
                    }             
                    
                }
            }
            
            this.exibirAlert(mensagensErro.toString(), super.getString(R.string.btn_ok));
            this.exibirInformacao(mensagensInfo.toString());
        }
    }

    /**
     * Método trata exceptions geradas pelo aplicativo gerando log e informando ao usuário que ocorreu um erro.
     * 
     * @param tag - Nome da classe, funcionalidade que disparou a exception
     * @param exception - Exception gerada.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected void tratarException(String tag, Exception exception) {

        String mensagem = super.getString(R.string.erro_desconhecido);

        Log.e(tag, mensagem, exception);
        this.exibirAlert(mensagem, super.getString(R.string.btn_ok));
    }

    /**
     * Método exibe informação para o usuário.
     * 
     * @param mensagem - Mensagem para ser exibida ao usuário
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected void exibirInformacao(String mensagem) {

        if (mensagem != null && mensagem.trim() != "") {
            
            Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método exibe uma caixa de dialogo de alerta.
     * 
     * @param mensagem - Mensagem para ser exibida ao usuário
     * @param textoButton - Texto do botão.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    protected void exibirAlert(String mensagem, String textoButton) {

        if (mensagem != null && mensagem.trim() != "") {
            
            AlertDialog alerta = new AlertDialog.Builder(this).create();            
            alerta.setTitle(R.string.alert_titulo);
            alerta.setMessage(mensagem);
            alerta.setButton(textoButton, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    return;
             } });
            alerta.show();
        }
    }

}
