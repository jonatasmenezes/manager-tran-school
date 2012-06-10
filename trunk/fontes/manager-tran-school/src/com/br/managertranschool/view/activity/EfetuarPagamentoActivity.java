package com.br.managertranschool.view.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.business.filter.ClienteFilter;
import com.br.managertranschool.business.filter.PagamentoFilter;
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
    private ClienteService clienteService;
    
    @Inject
    private PagamentoService pagamentoService;

    @InjectView(R.id.pagamentoRealizadoCliente)
    private Spinner pagamentoRealizadoCliente;

    @InjectView(R.id.pagamento_realizado_mes_ano_referente)
    private EditText pagamentoRealizadoReferencia;

    @InjectView(R.id.btn_salvar)
    private Button btnSalvar;
    
    @InjectView(R.id.btn_cancelar)
    private Button btnCancelar;
    
    private String activityChamadora;
    
    @InjectView(R.id.pagamento_realizado_mes_ano_referente)
    private Button mPickDate;
    
    @InjectView(R.id.label_pagamento_realizado_mes_ano_referente)
    private TextView mDateDisplay;
    
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 0;
    
    // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }

            };
    
    

    
    
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }
    /*
     * (non-Javadoc)
     * 
     * @see com.br.managertranschool.architecture.BaseActivity#onCreate(android.os.Bundle)
     */
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityChamadora = super.getIntent().getStringExtra("activityChamadora");
        
        String[] from = {ClienteVO.TX_NOME};
        int[] to = {android.R.id.text1};
        
        SimpleAdapter adapter = new SimpleAdapter(this, this.obterClientes(), android.R.layout.simple_spinner_item, from, to);
        pagamentoRealizadoCliente.setAdapter(adapter);
          
        this.btnSalvar.setOnClickListener(this);
        this.btnCancelar.setOnClickListener(this);   

        
        // add a click listener to the button
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date (this method is below)
        updateDisplay();
        
       
    }
    
    private void updateDisplay() {
        mDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("-")
                    .append(mDay).append("-")
                    .append(mYear).append(" "));
    }
    
    
    /*
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {

        try {
            if (v.getId() == R.id.btn_salvar) {

             
                Long clienteId = null;
                
                Map<String, String> selectedItem = (Map<String, String>) pagamentoRealizadoCliente.getSelectedItem();
                
                clienteId = Long.valueOf(selectedItem.get("Codigo"));
               
                Long pagamentoId = obterIdPagamento(clienteId);
                             
                
                PagamentoRealizadoVO pagamentoRealizado = new PagamentoRealizadoVO();
                
                           
                pagamentoRealizado.setPagamentoId(pagamentoId);
                //pagamentoRealizado.setReferencia(referencia);

                pagamentoRealizadoService.salvar(pagamentoRealizado);
                
                if (pagamentoRealizadoService.isValido()) {
                    
                    super.finalize();
                }
                
                super.setMessages(pagamentoRealizadoService.getMensagens());      
                            

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
     * Método obtem lista de clientes do drop down.
     * 
     * @return Lista de {@link Map}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private List<Map<String, String>> obterClientes() {
        List<Map<String, String>> retornoList = new ArrayList<Map<String,String>>();
        Map<String, String> map;
        
        List<ClienteVO> clienteList = this.clienteService.pesquisar(new ClienteFilter());
        
        for (ClienteVO cliente : clienteList) {
            map = new HashMap<String, String>();
            map.put(ClienteVO.ID_CLIENTE, String.valueOf(cliente.getId()));
            map.put(ClienteVO.TX_NOME, cliente.getNome());
            retornoList.add(map);
        }        
                    
        return retornoList;
    }
    
    /**
     * Método obtem lista de id do pagamento do cliente.
     * 
     * @return Lista de {@link Map}.
     * @author Jeferson Almeida (jef.henrique.07@gmail.com)
     */
    private Long obterIdPagamento(Long clienteId) {

        
        PagamentoVO pagamento = new PagamentoVO();
        pagamento.setClienteId(clienteId);
        PagamentoFilter pagamentoFilter = new PagamentoFilter();
        pagamentoFilter.setPagamento(pagamento);
        
        List <PagamentoVO> pagamentoList = this.pagamentoService.pesquisar(pagamentoFilter);      
        pagamento = pagamentoList.get(0);
        
        return pagamento.getId();
    }

}
