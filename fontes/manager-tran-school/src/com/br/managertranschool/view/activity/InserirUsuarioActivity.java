package com.br.managertranschool.view.activity;

import javax.inject.Inject;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.business.service.UsuarioService;


/**
 * Classe activity responsavel pela view de inserir novo usuário.
 *  
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 07/06/2012
 */
@ContentView(R.layout.inserir_usuario)
public class InserirUsuarioActivity extends BaseActivity implements OnClickListener {

    @Inject
    private UsuarioService usuarioService;
    
    @InjectView(R.id.usuarioNome)
    private EditText usuarioNome;

    @InjectView(R.id.usuarioLogin)
    private EditText usuarioLogin;
    
    @InjectView(R.id.usuarioSenha)
    private EditText usuarioSenha;

    @InjectView(R.id.usuarioTipoUsuario)
    private Spinner usuarioTipoUsuario;
    
    private String activityChamadora;
    
    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChamadora = super.getIntent().getStringExtra("activityChamadora");
        
        
        
        // usuarioTipoUsuario.set
        
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {

        if (v.getId() == R.id.btn_salvar) {

           /* String login = usuarioLogin.getText().toString();
            String senha = usuarioSenha.getText().toString();

            UsuarioVO usuario = usuarioService.autenticarUsuario(new UsuarioVO(login, senha));

            if (usuario != null) {
                Intent it = new Intent(this, HomeActivity.class);
                super.startActivity(it);
            }
            
            super.setMessages(usuarioService.getMensagens());*/

        } else if (v.getId() == R.id.btn_cancelar) {
        }
        
    }
    
}
