package com.br.managertranschool.view.activity;

import javax.inject.Inject;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.business.service.UsuarioService;
import com.br.managertranschool.business.vo.UsuarioVO;

/**
 * Classe activity responsavel pela view de login.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 07/06/2012
 */
@ContentView(R.layout.login)
public class LoginActivity extends BaseActivity implements OnClickListener {

    @Inject
    private UsuarioService usuarioService;

    @InjectView(R.id.usuarioLogin)
    private EditText usuarioLogin;

    @InjectView(R.id.usuarioSenha)
    private EditText usuarioSenha;

    @InjectView(R.id.btn_entrar)
    private Button btnEntrar;
    
    @InjectView(R.id.btn_cancelar)
    private Button btnCancelar;
    
    /*
     * (non-Javadoc)
     * 
     * @see com.br.managertranschool.architecture.BaseActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.btnEntrar.setOnClickListener(this);
        this.btnCancelar.setOnClickListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {

        try {
            if (v.getId() == R.id.btn_entrar) {

                String login = usuarioLogin.getText().toString();
                String senha = usuarioSenha.getText().toString();

                UsuarioVO usuario = usuarioService.autenticarUsuario(new UsuarioVO(login, senha));

                if (usuario != null) {
                    Intent it = new Intent(this, HomeActivity.class);
                    super.startActivity(it);
                }
                
                super.setMessages(usuarioService.getMensagens());

            } else if (v.getId() == R.id.btn_cancelar) {
                super.finish();
            }
        } catch (Exception e) {
            super.tratarException(this.getClass().getName(), e);
        } catch (Throwable e) {
            super.tratarException(this.getClass().getName(), new Exception(e));
        }
    }

}
