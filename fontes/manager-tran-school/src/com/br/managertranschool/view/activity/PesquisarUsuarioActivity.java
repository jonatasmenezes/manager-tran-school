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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.business.filter.UsuarioFilter;
import com.br.managertranschool.business.list.TipoUsuarioList;
import com.br.managertranschool.business.service.UsuarioService;
import com.br.managertranschool.business.vo.UsuarioVO;

/**
 * Classe activity responsavel pela view de pesquisar usuários.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 10/06/2012
 */
@ContentView(R.layout.pesquisar_usuario)
public class PesquisarUsuarioActivity extends BaseActivity implements OnClickListener, OnItemClickListener {

    @Inject
    private UsuarioService usuarioService;

    @InjectView(R.id.usuarioNome)
    private EditText usuarioNome;

    @InjectView(R.id.usuarioLogin)
    private EditText usuarioLogin;

    @InjectView(R.id.usuarios_list)
    private ListView usuariosList;
    
    @InjectView(R.id.btn_novo)
    private Button btnNovo;
    
    @InjectView(R.id.btn_pesquisar)
    private Button btnPesquisar;
    
    /*
     * (non-Javadoc)
     * 
     * @see com.br.managertranschool.architecture.BaseActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        
        this.btnNovo.setOnClickListener(this);
        this.btnPesquisar.setOnClickListener(this);
        usuariosList.setOnItemClickListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {

        try {
            if (v.getId() == R.id.btn_pesquisar) {

                String nome = usuarioNome.getText().toString();
                String login = usuarioLogin.getText().toString();
                
                UsuarioVO usuario = new UsuarioVO();
                usuario.setNome(nome);
                usuario.setLogin(login);

                List<UsuarioVO> usuarioVOList = usuarioService.pesquisar(new UsuarioFilter(usuario));
                
                if (usuarioService.isValido()) {                    
                    if (!usuarioVOList.isEmpty()) {
                        this.carregarListaUsuarios(usuarioVOList);
                    } else {
                        usuariosList.setAdapter(null);
                    }
                }
                
                super.setMessages(usuarioService.getMensagens());          

            } else if (v.getId() == R.id.btn_novo) {
                Intent it = new Intent(this, InserirUsuarioActivity.class); 
                super.startActivity(it);
            }
        } catch (Exception e) {
            super.tratarException(this.getClass().getName(), e);
        } catch (Throwable e) {
            super.tratarException(this.getClass().getName(), new Exception(e));
        }
    }

    /* (non-Javadoc)
     * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
     */
    public void onItemClick(AdapterView<?> arg0, View view, int posicao, long arg3) {

        Map<String, String> item = (Map<String, String>) arg0.getItemAtPosition(posicao);
        
        long idUsuario = Long.valueOf(item.get(UsuarioVO.ID_USUARIO));
     
        Intent it = new Intent(this, InserirUsuarioActivity.class);
        it.putExtra(UsuarioVO.ID_USUARIO, idUsuario);
        super.startActivity(it);
    }
    
    /**
     * Método carrega ListView de usuários.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private void carregarListaUsuarios(List<UsuarioVO> usuarioVOList) {
        List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
        
        Map<String, String> map;
        
        for (UsuarioVO usuario : usuarioVOList) {
            map = new HashMap<String, String>();
            map.put(UsuarioVO.ID_USUARIO, String.valueOf(usuario.getId()));
            map.put(UsuarioVO.TX_NOME, usuario.getNome());
            map.put(UsuarioVO.TX_LOGIN, super.getString(R.string.usuario_login) + ": " + usuario.getLogin() 
                + " - " + super.getString(TipoUsuarioList.obterResourceIdByCodigo(usuario.getTipoUsuario()).getNome()));
            mapList.add(map);
        }
        
        String[] from = {UsuarioVO.TX_NOME, UsuarioVO.TX_LOGIN};
        int[] to = {android.R.id.text1, android.R.id.text2};
        
        SimpleAdapter adapter = new SimpleAdapter(this, mapList, android.R.layout.simple_list_item_2, from, to);
        usuariosList.setAdapter(adapter);
    }    
}
