package com.br.managertranschool.view.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.business.filter.PagamentoFilter;
import com.br.managertranschool.business.filter.PagamentoRealizadoFilter;
import com.br.managertranschool.business.service.ClienteService;
import com.br.managertranschool.business.service.PagamentoRealizadoService;
import com.br.managertranschool.business.service.PagamentoService;
import com.br.managertranschool.business.vo.ClienteVO;
import com.br.managertranschool.business.vo.PagamentoRealizadoVO;
import com.br.managertranschool.business.vo.PagamentoVO;


/**
 * Classe activity responsavel pela view de efetuar pagamento.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 10/06/2012
 */
@ContentView(R.layout.efetuar_pagamento)
public class EfetuarPagamentoActivity extends BaseActivity implements OnClickListener {

    @Inject
    private PagamentoRealizadoService pagamentoRealizadoService;
    
    @Inject
    private PagamentoService pagamentoService;
    
    @Inject
    private ClienteService clienteService;

    @InjectView(R.id.pagamento_realizado_mes_ano_referente)
    private EditText pagamentoRealizadoReferencia;
    
    @InjectView(R.id.label_pagamento_relizado_cliente_nome)
    private TextView clienteNome;
    
    @InjectView(R.id.pagamentos_list)
    private ListView pagamentosList;

    @InjectView(R.id.btn_efetuar_pagamento)
    private Button btnEfetuarPagamento;
    
    @InjectView(R.id.btn_voltar)
    private Button btnVoltar;
    
    private Long idCliente;
    
    private Long idPagamento;
    
    private List<PagamentoRealizadoVO> pagamentosVOList;
  
    /*
     * (non-Javadoc)
     * 
     * @see com.br.managertranschool.architecture.BaseActivity#onCreate(android.os.Bundle)
     */
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        
        this.idCliente = super.getIntent().getLongExtra(ClienteVO.ID_CLIENTE, Long.MIN_VALUE);
        
        carregarDadosCliente();
        
        this.btnEfetuarPagamento.setOnClickListener(this);
        
        this.btnVoltar.setOnClickListener(this);   
       
    }
   
    
    /*
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {

        try {
            if (v.getId() == R.id.btn_efetuar_pagamento) {

                String referencia = pagamentoRealizadoReferencia.getText().toString();
                
                PagamentoRealizadoVO pagamentoRealizado = new PagamentoRealizadoVO();
                pagamentoRealizado.setPagamentoId(idPagamento);
                
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");                
                pagamentoRealizado.setDataPagamento(new Date());                           
                pagamentoRealizado.setReferencia(format.parse("01/"+referencia));

                pagamentoRealizadoService.salvar(pagamentoRealizado);
                
                if (pagamentoRealizadoService.isValido()) {
                    
                    super.finish();
                }              
                
                super.setMessages(pagamentoRealizadoService.getMensagens());
                            

            } else if (v.getId() == R.id.btn_voltar) {
                super.finish();
            }
        } catch (Exception e) {
            super.tratarException(this.getClass().getName(), e);
        } catch (Throwable e) {
            super.tratarException(this.getClass().getName(), new Exception(e));
        }

    }
    
    
    /**
     * Método que busca os dados de cliente.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void carregarDadosCliente() {

        ClienteVO cliente = clienteService.buscarPorId(new ClienteVO(this.idCliente));

        List<PagamentoVO> pagamentoList = pagamentoService.pesquisar(new PagamentoFilter(new PagamentoVO(this.idCliente)));
        
        this.clienteNome.setText(cliente.getNome());
        PagamentoVO pagamento = pagamentoList.get(0);
        
        this.idPagamento = pagamento.getId();
        
        //
        PagamentoRealizadoVO pagamentoRealizado = new PagamentoRealizadoVO();
        
        pagamentoRealizado.setPagamentoId(this.idPagamento);
        
        this.pagamentosVOList = pagamentoRealizadoService.pesquisar(new PagamentoRealizadoFilter(pagamentoRealizado));
        if (this.pagamentosVOList != null && !(this.pagamentosVOList.isEmpty())) {         
            carregarListaPagamentos(this.pagamentosVOList);
        }

    }
    
    /**
     * Método carrega ListView de pagamentosRealizado.
     * 
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private void carregarListaPagamentos(List<PagamentoRealizadoVO> pagamentoRealizadoVOList) {
        List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
        
        Map<String, String> map;
        
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("MM-yyyy");
        
        for (PagamentoRealizadoVO pagamentoRealizado : pagamentoRealizadoVOList) {
            map = new HashMap<String, String>();
            map.put(PagamentoRealizadoVO.DT_PAGAMENTO, String.valueOf(pagamentoRealizado.getId()));
            map.put(PagamentoRealizadoVO.MES_ANO_REFERENTE, "Referência:"+format2.format(pagamentoRealizado.getReferencia()));
            map.put("Descricao", super.getString(R.string.pagamento_realizado_data) + ": " + format.format(pagamentoRealizado.getDataPagamento()));
            mapList.add(map);
        }
        
        String[] from = {PagamentoRealizadoVO.MES_ANO_REFERENTE, "Descricao"};
        int[] to = {android.R.id.text1, android.R.id.text2};
        
        SimpleAdapter adapter = new SimpleAdapter(this, mapList, android.R.layout.simple_list_item_2, from, to);
        pagamentosList.setAdapter(adapter);
        
        super.setMessages(pagamentoRealizadoService.getMensagens());
    } 

}
