package com.br.managertranschool.view.activity;

import java.util.List;

import javax.inject.Inject;

import roboguice.inject.ContentView;
import android.os.Bundle;
import android.widget.TextView;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.architecture.database.DatabaseCreate;
import com.br.managertranschool.business.filter.UsuarioFilter;
import com.br.managertranschool.business.list.TipoUsuarioList;
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
    private UsuarioService service;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            new DatabaseCreate(this);

            UsuarioVO usuario = new UsuarioVO();
            usuario.setLogin("admin_2");
            usuario.setNome("Administrador");
            usuario.setSenha("admin");
            usuario.setTipoUsuario(TipoUsuarioList.ADMINISTRADOR.getCodigo());

            service.salvar(usuario);
            super.setMessages(service.getMensagens());

            List<UsuarioVO> usuarioList = service.pesquisar(new UsuarioFilter(new UsuarioVO()));

            if (!usuarioList.isEmpty()) {
                
                UsuarioVO usuario2 = usuarioList.get(0);
                
                TextView nome = (TextView) this.findViewById(R.id.usuarioNome);
                TextView login = (TextView) this.findViewById(R.id.usuarioLogin);
                TextView tipo = (TextView) this.findViewById(R.id.usuarioTipo);
                
                nome.setText(usuario2.getNome());
                login.setText(usuario2.getNome());
                tipo.setText(usuario2.getNome());
            }
            super.setMessages(service.getMensagens());

        } catch (Exception e) {
            super.tratarException(this.getClass().getName(), e);
        }

    }
}