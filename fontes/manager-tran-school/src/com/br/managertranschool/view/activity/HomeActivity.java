package com.br.managertranschool.view.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.business.list.HomeMenuList;


/**
 * Classe activity responsavel pela view de home do aplicativo.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 07/06/2012
 */
@ContentView(R.layout.app_home)
public class HomeActivity extends BaseActivity implements OnItemClickListener {

    @InjectView(R.id.menu_list)
    private ListView menuList;
    
    private static String CODIGO = "Codigo";

    private static String NOME = "Nome";
    
    private static String DESCRICAO = "Descricao";
    
    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] from = {NOME, DESCRICAO};
        int[] to = {android.R.id.text1, android.R.id.text2};
        
        SimpleAdapter adapter = new SimpleAdapter(this, this.obterMenu(), android.R.layout.simple_list_item_2, from, to);
        menuList.setOnItemClickListener(this);
        menuList.setAdapter(adapter);
    }
    
    /* (non-Javadoc)
     * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
     */
    public void onItemClick(AdapterView<?> arg0, View view, int posicao, long arg3) {

        Map<String, String> item = (Map<String, String>) arg0.getItemAtPosition(posicao);
        
        int codigo = Integer.valueOf(item.get(CODIGO));
        
        if (HomeMenuList.CLIENTES.getCodigo() == codigo) {
            super.exibirInformacao("Ainda não implementado: " + super.getString(HomeMenuList.CLIENTES.getDescricao()));
        } else if (HomeMenuList.USUARIOS.getCodigo() == codigo) {
            Intent it = new Intent(this, PesquisarUsuarioActivity.class); 
            super.startActivity(it);
        } else if (HomeMenuList.ROTAS.getCodigo() == codigo) {
            super.exibirInformacao("Ainda não implementado: " + super.getString(HomeMenuList.ROTAS.getDescricao()));
        } else if (HomeMenuList.LOCALIDADES.getCodigo() == codigo) {
            super.exibirInformacao("Ainda não implementado: " + super.getString(HomeMenuList.LOCALIDADES.getDescricao()));
        }      
        
    }
    
    /**
     * Método obtem lista de itens do menu.
     * 
     * @return Lista de map com codigo do item e descricao.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private List<Map<String, String>> obterMenu() {
        List<Map<String, String>> retornoList = new ArrayList<Map<String,String>>();
        
        Map<String, String> map;
        
        for (HomeMenuList itemMenu : HomeMenuList.values()) {
            map = new HashMap<String, String>();
            map.put(CODIGO, String.valueOf(itemMenu.getCodigo()));
            map.put(NOME, super.getString(itemMenu.getNome()));
            map.put(DESCRICAO, super.getString(itemMenu.getDescricao()));
            retornoList.add(map);
        }
        
        return retornoList;
    }
    
}
