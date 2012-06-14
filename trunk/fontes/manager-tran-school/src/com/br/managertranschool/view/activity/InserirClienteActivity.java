package com.br.managertranschool.view.activity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.business.filter.CidadeFilter;
import com.br.managertranschool.business.filter.ClienteLocalidadeFilter;
import com.br.managertranschool.business.filter.LocalidadeFilter;
import com.br.managertranschool.business.filter.PagamentoFilter;
import com.br.managertranschool.business.list.TipoPagamentoList;
import com.br.managertranschool.business.service.CidadeService;
import com.br.managertranschool.business.service.ClienteLocalidadeService;
import com.br.managertranschool.business.service.ClienteService;
import com.br.managertranschool.business.service.LocalidadeService;
import com.br.managertranschool.business.service.PagamentoService;
import com.br.managertranschool.business.vo.CidadeVO;
import com.br.managertranschool.business.vo.ClienteLocalidadeVO;
import com.br.managertranschool.business.vo.ClienteVO;
import com.br.managertranschool.business.vo.LocalidadeVO;
import com.br.managertranschool.business.vo.PagamentoVO;

/**
 * Classe activity responsavel pela view de inserir novo cliente.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 09/06/2012
 */
@ContentView(R.layout.inserir_cliente)
public class InserirClienteActivity extends BaseActivity implements OnClickListener {

    @Inject
    private ClienteService clienteService;

    @Inject
    private CidadeService cidadeService;
    
    @Inject
    private LocalidadeService localidadeService;
    
    @Inject
    private ClienteLocalidadeService clienteLocalidadeService;
    
    @Inject
    private PagamentoService pagamentoService;
    
    @InjectView(R.id.clienteNome)
    private EditText clienteNome;

    @InjectView(R.id.clienteCpf)
    private EditText clienteCpf;

    @InjectView(R.id.clienteCep)
    private EditText clienteCep;

    @InjectView(R.id.clienteEndereco)
    private EditText clienteEndereco;
    
    @InjectView(R.id.clienteBairro)
    private EditText clienteBairro;
    
    @InjectView(R.id.clienteEmail)
    private EditText clienteEmail;
    
    @InjectView(R.id.clienteTelefone1)
    private EditText clienteTelefone1;
    
    @InjectView(R.id.clienteTelefone2)
    private EditText clienteTelefone2;
        
    @InjectView(R.id.clienteCidade)
    private Spinner clienteCidade;
    
    @InjectView(R.id.clienteLocalidade)
    private Spinner clienteLocalidade;

    @InjectView(R.id.clienteTipoPagamento)
    private Spinner clienteTipoPagamento;

    @InjectView(R.id.clienteDataVencimento)
    private EditText clienteDataVencimento;

    @InjectView(R.id.clientePagamentoValor)
    private EditText clientePagamentoValor;
    
    @InjectView(R.id.btn_salvar)
    private Button btnSalvar;
    
    @InjectView(R.id.btn_cancelar)
    private Button btnCancelar;
    
    private Long idCliente;
    
    private Long idPagamento;
    
    /*
     * (non-Javadoc)
     * 
     * @see com.br.managertranschool.architecture.BaseActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.idCliente = super.getIntent().getLongExtra(ClienteVO.ID_CLIENTE, Long.MIN_VALUE);
        
        String[] from = {CidadeVO.TX_DESCRICAO};
        int[] to = {android.R.id.text1};
        
        SimpleAdapter adapter = new SimpleAdapter(this, this.obterCidades(), android.R.layout.simple_spinner_item, from, to);
        clienteCidade.setAdapter(adapter);
        
        from = new String[] {"Nome"};
        to = new int[] {android.R.id.text1};
        
        adapter = new SimpleAdapter(this, this.obterTiposPagamento(), android.R.layout.simple_spinner_item, from, to);
        clienteTipoPagamento.setAdapter(adapter);
        
        from = new String[] {LocalidadeVO.TX_DESCRICAO};
        to = new int[] {android.R.id.text1};
        
        adapter = new SimpleAdapter(this, this.obterLocalidades(), android.R.layout.simple_spinner_item, from, to);
        clienteLocalidade.setAdapter(adapter);
        
        this.btnSalvar.setOnClickListener(this);
        this.btnCancelar.setOnClickListener(this);
        
        if (this.idCliente != null && this.idCliente > 0) {
            this.carregarDadosCliente();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {

        try {
            if (v.getId() == R.id.btn_salvar) {

                ClienteVO cliente = new ClienteVO();
                ClienteLocalidadeVO clienteLocalidadeVO = new ClienteLocalidadeVO();
                PagamentoVO pagamento = new PagamentoVO();
                
                String nome = clienteNome.getText().toString();
                String cpf = clienteCpf.getText().toString();
                String cep = clienteCep.getText().toString();
                String endereco = clienteEndereco.getText().toString();
                String bairro = clienteBairro.getText().toString();
                String email = clienteEmail.getText().toString();
                String telefone1 = clienteTelefone1.getText().toString();
                String telefone2 = clienteTelefone2.getText().toString();
                String diaVencimento = clienteDataVencimento.getText().toString();
                String pagamentoValor = clientePagamentoValor.getText().toString();
                Long cidade = null;
                Long localidade = null;
                Integer tipoPagamento = null;
                
                Map<String, String> selectedItem = super.getSelectedItem(clienteCidade);                
                cidade = Long.valueOf(selectedItem.get(CidadeVO.ID_CIDADE));
                
                selectedItem = super.getSelectedItem(clienteLocalidade);
                localidade = Long.valueOf(selectedItem.get(LocalidadeVO.ID_LOCALIDADE));
                
                selectedItem = super.getSelectedItem(clienteTipoPagamento);                
                tipoPagamento = Integer.valueOf(selectedItem.get("Codigo"));
                               
                // Cliente
                cliente.setNome(nome);
                cliente.setCpf(cpf);
                cliente.setCep(cep);
                cliente.setEndereco(endereco);
                cliente.setBairro(bairro);
                cliente.setEmail(email);
                cliente.setTelefonePrimario(telefone1);
                cliente.setTelefoneSecundario(telefone2);                
                cliente.setCidadeId(cidade);
                
                // Localidade
                clienteLocalidadeVO.setLocalidadeId(localidade);
                
                // Pagamento - TODO
                pagamento.setTipoPagamento(tipoPagamento);
                
                if (diaVencimento != null && diaVencimento.length() > 0) {
                    
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    pagamento.setVencimento(format.parse(diaVencimento + "/00/0000"));
                }
                
                if (pagamentoValor != null && pagamentoValor.length() > 0) {
                    
                    pagamento.setValor(new BigDecimal(pagamentoValor));
                }
                
                if (this.idCliente != null && this.idCliente > 0) {
                    cliente.setId(this.idCliente);
                    pagamento.setId(this.idPagamento);
                    
                    clienteService.atualizar(cliente, clienteLocalidadeVO, pagamento);
                } else {
                    
                    clienteService.salvar(cliente, clienteLocalidadeVO, pagamento);
                }                
                
                if (clienteService.isValido()) {
                    super.finish();
                }
                
                super.setMessages(clienteService.getMensagens());                

            } else if (v.getId() == R.id.btn_cancelar) {
                super.finish();
            }
        } catch (Exception e) {
            super.tratarException(this.getClass().getName(), e);
        } catch (Throwable e) {
            super.tratarException(this.getClass().getName(), new Exception(e));
        }

    }
    
    /**
     * Método obtem lista de cidades do drop down.
     * 
     * @return Lista de {@link Map}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private List<Map<String, String>> obterCidades() {
        List<Map<String, String>> retornoList = new ArrayList<Map<String,String>>();
        Map<String, String> map;
        
        List<CidadeVO> cidadeList = this.cidadeService.pesquisar(new CidadeFilter(new CidadeVO("BA")));
        
        for (CidadeVO cidade : cidadeList) {
            map = new HashMap<String, String>();
            map.put(CidadeVO.ID_CIDADE, String.valueOf(cidade.getId()));
            map.put(CidadeVO.TX_DESCRICAO, cidade.getDescricao());
            retornoList.add(map);
        }
                
        return retornoList;
    }

    /**
     * Método obtem lista de tipos de pagamento para o drop down.
     * 
     * @return Lista de {@link Map}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private List<Map<String, String>> obterTiposPagamento() {

        List<Map<String, String>> retornoList = new ArrayList<Map<String,String>>();
        
        Map<String, String> map;
        
        for (TipoPagamentoList tipo : TipoPagamentoList.values()) {
            map = new HashMap<String, String>();
            map.put("Codigo", String.valueOf(tipo.getCodigo()));
            map.put("Nome", super.getString(tipo.getNome()));
            retornoList.add(map);
        }
        
        return retornoList;
    }
    
    /**
     * Método obtem lista de localidade para o drop down.
     * 
     * @return Lista de {@link Map}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private List<Map<String, String>> obterLocalidades() {
        List<Map<String, String>> retornoList = new ArrayList<Map<String,String>>();
        Map<String, String> map;
        
        List<LocalidadeVO> localidadeList = this.localidadeService.pesquisar(new LocalidadeFilter(new LocalidadeVO()));
        
        for (LocalidadeVO localidade : localidadeList) {
            map = new HashMap<String, String>();
            map.put(LocalidadeVO.ID_LOCALIDADE, String.valueOf(localidade.getId()));
            map.put(LocalidadeVO.TX_DESCRICAO, localidade.getDescricao());
            retornoList.add(map);
        }
                
        return retornoList;
    }
    
    /**
     * Método popula dados da tela com cliente.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private void carregarDadosCliente() {

        ClienteVO cliente = clienteService.buscarPorId(new ClienteVO(this.idCliente));
        List<ClienteLocalidadeVO> clienteLocalidadeList = clienteLocalidadeService
            .pesquisar(new ClienteLocalidadeFilter(new ClienteLocalidadeVO(this.idCliente)));
        List<PagamentoVO> pagamentoList = pagamentoService.pesquisar(new PagamentoFilter(new PagamentoVO(this.idCliente)));
        
        this.clienteNome.setText(cliente.getNome());
        this.clienteCpf.setText(cliente.getCpf());
        this.clienteCep.setText(cliente.getCep());
        this.clienteEndereco.setText(cliente.getEndereco());
        this.clienteBairro.setText(cliente.getBairro());
        this.clienteEmail.setText(cliente.getEmail());
        this.clienteTelefone1.setText(cliente.getTelefonePrimario());
        this.clienteTelefone2.setText(cliente.getTelefoneSecundario());
        this.clienteCidade.setSelection(super.obterPosicaoSpinner(this.clienteCidade, CidadeVO.ID_CIDADE, cliente.getCidadeId()));
        
        if (!clienteLocalidadeList.isEmpty()) {
            this.clienteLocalidade.setSelection(super.obterPosicaoSpinner(
                this.clienteLocalidade, LocalidadeVO.ID_LOCALIDADE, clienteLocalidadeList.get(0).getLocalidadeId()));
        }
        
        if (!pagamentoList.isEmpty()) {
            PagamentoVO pagamento = pagamentoList.get(0);
            
            this.idPagamento = pagamento.getId();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(pagamento.getVencimento());
            this.clienteDataVencimento.setText(String.valueOf(calendar.DAY_OF_MONTH));
            this.clientePagamentoValor.setText(pagamento.getValor().toString());
            this.clienteTipoPagamento.setSelection(super.obterPosicaoSpinner(
                this.clienteTipoPagamento, "Codigo", pagamento.getTipoPagamento()));
        }
    }
    
}
