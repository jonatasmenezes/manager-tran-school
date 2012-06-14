package com.br.managertranschool.view.activity;

import java.util.List;

import javax.inject.Inject;

import roboguice.inject.ContentView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.architecture.database.DatabaseCreate;
import com.br.managertranschool.business.filter.UsuarioFilter;
import com.br.managertranschool.business.service.UsuarioService;
import com.br.managertranschool.business.vo.UsuarioVO;

/**
 * Classe activity responsavel pela view principal do aplicativo.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 23/04/2012
 */
@ContentView(R.layout.main)
public class MainActivity extends BaseActivity {

    @Inject
    private UsuarioService usuarioService;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            new DatabaseCreate(this);
            Intent it;
            
            List<UsuarioVO> usuarioList = usuarioService.pesquisar(new UsuarioFilter(new UsuarioVO()));

            if (usuarioList.isEmpty() || usuarioList.size() == 1) {
                super.exibirInformacao(super.getString(R.string.necessario_incluir_usuario_inicial));
                
                it = new Intent(this, InserirUsuarioActivity.class);
                it.putExtra("activityChamadora", this.getClass().getName());
            } else {
                it = new Intent(this, LoginActivity.class);
            }
            
            super.setMessages(usuarioService.getMensagens());
            super.startActivity(it);  
            super.finish();
        } catch (Exception e) {
            super.tratarException(this.getClass().getName(), e);
        }

    }
    
    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseActivity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    
        return false;
    }
}