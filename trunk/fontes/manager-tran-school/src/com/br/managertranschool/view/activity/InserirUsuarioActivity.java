package com.br.managertranschool.view.activity;

import java.util.List;

import javax.inject.Inject;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.business.filter.EstadoFilter;
import com.br.managertranschool.business.service.EstadoService;
import com.br.managertranschool.business.service.UsuarioService;
import com.br.managertranschool.business.vo.EstadoVO;
import com.br.managertranschool.business.vo.UsuarioVO;

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

    @Inject
    private EstadoService estadoService;

    @InjectView(R.id.usuarioNome)
    private EditText usuarioNome;

    @InjectView(R.id.usuarioLogin)
    private EditText usuarioLogin;

    @InjectView(R.id.usuarioSenha)
    private EditText usuarioSenha;

    @InjectView(R.id.usuarioTipoUsuario)
    private Spinner usuarioTipoUsuario;

    private String activityChamadora;

    /*
     * (non-Javadoc)
     * 
     * @see com.br.managertranschool.architecture.BaseActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityChamadora = super.getIntent().getStringExtra("activityChamadora");

        List<EstadoVO> estadoList = estadoService.pesquisar(new EstadoFilter(new EstadoVO()));
        
        // TODO
        /*List<String> descricaoList = new ArrayList<String>();
        for (EstadoVO estado : estadoList) {
            descricaoList.add(estado.getDescricao());
        }*/
        
        ArrayAdapter<EstadoVO> estadosAdapter = new ArrayAdapter<EstadoVO>(this,
            android.R.layout.simple_spinner_dropdown_item, estadoList);
        usuarioTipoUsuario.setAdapter(estadosAdapter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {

        try {
            if (v.getId() == R.id.btn_salvar) {

                String nome = usuarioNome.getText().toString();
                String login = usuarioLogin.getText().toString();
                String senha = usuarioSenha.getText().toString();
                Integer tipo = null;
                if (usuarioTipoUsuario.getSelectedItemId() > 0) {
                    tipo = new Integer(String.valueOf(usuarioTipoUsuario.getSelectedItemId()));
                }

                UsuarioVO usuario = new UsuarioVO();
                usuario.setNome(nome);
                usuario.setLogin(login);
                usuario.setSenha(senha);
                usuario.setTipoUsuario(tipo);

                usuarioService.salvar(usuario);
                super.setMessages(usuarioService.getMensagens());
                
                if (MainActivity.class.getName().equalsIgnoreCase(activityChamadora)) {
                    Intent it = new Intent(this, HomeActivity.class); 
                    super.startActivity(it);
                } else {
                    super.finalize();
                }

            } else if (v.getId() == R.id.btn_cancelar) {
                super.finalize();
            }
        } catch (Exception e) {
            super.tratarException(this.getClass().getName(), e);
        } catch (Throwable e) {
            super.tratarException(this.getClass().getName(), new Exception(e));
        }

    }

}
