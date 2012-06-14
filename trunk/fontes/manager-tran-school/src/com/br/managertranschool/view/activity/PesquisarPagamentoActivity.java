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
import com.br.managertranschool.business.filter.ClienteFilter;
import com.br.managertranschool.business.service.ClienteService;
import com.br.managertranschool.business.vo.ClienteVO;

/**
 * Classe activity responsavel pela view de pesquisar clientes.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 13/06/2012
 */
@ContentView(R.layout.pesquisar_pagamento)
public class PesquisarPagamentoActivity extends BaseActivity implements OnClickListener, OnItemClickListener {
    
    
    @Inject
    private ClienteService clienteService;
    
    @InjectView(R.id.clienteNome)
    private EditText clienteNome;

    @InjectView(R.id.clientes_list)
    private ListView clientesList;
    
    
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
        
        
        this.btnPesquisar.setOnClickListener(this);
        
        clientesList.setOnItemClickListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {

        try {
            if (v.getId() == R.id.btn_pesquisar) {

                String nome = this.clienteNome.getText().toString();
                
                
                ClienteVO cliente = new ClienteVO();
                cliente.setNome(nome);

                List<ClienteVO> clienteVOList = clienteService.pesquisar(new ClienteFilter(cliente));
                
                if (clienteService.isValido()) {                    
                    if (!clienteVOList.isEmpty()) {
                        this.carregarListaClientes(clienteVOList);
                    } else {
                        this.clientesList.setAdapter(null);
                    }
                }
                
                super.setMessages(clienteService.getMensagens());          

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
        
        long idCliente = Long.valueOf(item.get(ClienteVO.ID_CLIENTE));
     
        Intent it = new Intent(this, EfetuarPagamentoActivity.class);
        it.putExtra(ClienteVO.ID_CLIENTE, idCliente);
        super.startActivity(it);
    }
    
    /**
     * Método carrega ListView de clientes.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void carregarListaClientes(List<ClienteVO> clienteVOList) {
        List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
        
        Map<String, String> map;
        
        for (ClienteVO cliente : clienteVOList) {
            map = new HashMap<String, String>();
            map.put(ClienteVO.ID_CLIENTE, String.valueOf(cliente.getId()));
            map.put(ClienteVO.TX_NOME, cliente.getNome());
            map.put("Descricao", super.getString(R.string.cliente_cpf) + ": " + cliente.getCpf() 
                + " - " + super.getString(R.string.cliente_telefone_primario) + ": " + cliente.getTelefonePrimario()
                + " - " + super.getString(R.string.cliente_bairro) + ": " + cliente.getBairro());
            mapList.add(map);
        }
        
        String[] from = {ClienteVO.TX_NOME, "Descricao"};
        int[] to = {android.R.id.text1, android.R.id.text2};
        
        SimpleAdapter adapter = new SimpleAdapter(this, mapList, android.R.layout.simple_list_item_2, from, to);
        clientesList.setAdapter(adapter);
    }   
    
}
