package com.br.managertranschool.view.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.business.list.TipoUsuarioList;
import com.br.managertranschool.business.service.UsuarioService;
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

    @InjectView(R.id.usuarioNome)
    private EditText usuarioNome;

    @InjectView(R.id.usuarioLogin)
    private EditText usuarioLogin;

    @InjectView(R.id.usuarioSenha)
    private EditText usuarioSenha;

    @InjectView(R.id.usuarioTipoUsuario)
    private Spinner usuarioTipoUsuario;

    @InjectView(R.id.btn_salvar)
    private Button btnSalvar;
    
    @InjectView(R.id.btn_cancelar)
    private Button btnCancelar;
    
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
        
        String[] from = {"Nome"};
        int[] to = {android.R.id.text1};
        
        SimpleAdapter adapter = new SimpleAdapter(this, this.obterTiposUsuario(), android.R.layout.simple_spinner_item, from, to);
        usuarioTipoUsuario.setAdapter(adapter);
        
        this.btnSalvar.setOnClickListener(this);
        this.btnCancelar.setOnClickListener(this);
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
                
                Map<String, String> item = (Map<String, String>) usuarioTipoUsuario.getSelectedItem();
                
                tipo = Integer.valueOf(item.get("Codigo"));
                
                UsuarioVO usuario = new UsuarioVO();
                usuario.setNome(nome);
                usuario.setLogin(login);
                usuario.setSenha(senha);
                usuario.setTipoUsuario(tipo);

                usuarioService.salvar(usuario);
                
                if (usuarioService.isValido()) {
                    
                    if (MainActivity.class.getName().equalsIgnoreCase(activityChamadora)) {
                        Intent it = new Intent(this, HomeActivity.class); 
                        super.startActivity(it);
                    } else {
                        super.finalize();
                    }
                }
                
                super.setMessages(usuarioService.getMensagens());                

            } else if (v.getId() == R.id.btn_cancelar) {
                super.finalize();
            }
        } catch (Exception e) {
            super.tratarException(this.getClass().getName(), e);
        } catch (Throwable e) {
            super.tratarException(this.getClass().getName(), new Exception(e));
        }

    }
    
    /**
     * Método obtem lista de tipos de usuários do drop down.
     * 
     * @return Lista de map.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private List<Map<String, String>> obterTiposUsuario() {
        List<Map<String, String>> retornoList = new ArrayList<Map<String,String>>();
        
        Map<String, String> map;
        
        for (TipoUsuarioList tipo : TipoUsuarioList.values()) {
            map = new HashMap<String, String>();
            map.put("Codigo", String.valueOf(tipo.getCodigo()));
            map.put("Nome", super.getString(tipo.getNome()));
            retornoList.add(map);
        }
        
        return retornoList;
    }

}
