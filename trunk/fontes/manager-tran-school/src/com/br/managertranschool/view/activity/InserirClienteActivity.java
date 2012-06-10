package com.br.managertranschool.view.activity;

import java.util.ArrayList;
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
import com.br.managertranschool.business.service.CidadeService;
import com.br.managertranschool.business.service.ClienteService;
import com.br.managertranschool.business.vo.CidadeVO;
import com.br.managertranschool.business.vo.ClienteVO;

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

    @InjectView(R.id.btn_salvar)
    private Button btnSalvar;
    
    @InjectView(R.id.btn_cancelar)
    private Button btnCancelar;
    
    /*
     * (non-Javadoc)
     * 
     * @see com.br.managertranschool.architecture.BaseActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        
        String[] from = {CidadeVO.TX_DESCRICAO};
        int[] to = {android.R.id.text1};
        
        SimpleAdapter adapter = new SimpleAdapter(this, this.obterCidades(), android.R.layout.simple_spinner_item, from, to);
        clienteCidade.setAdapter(adapter);
        
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

                String nome = clienteNome.getText().toString();
                String cpf = clienteCpf.getText().toString();
                String cep = clienteCep.getText().toString();
                String endereco = clienteEndereco.getText().toString();
                String bairro = clienteBairro.getText().toString();
                String email = clienteEmail.getText().toString();
                String telefone1 = clienteTelefone1.getText().toString();
                String telefone2 = clienteTelefone2.getText().toString();
                Long cidade = null;
                
                Map<String, String> selectedItem = (Map<String, String>) clienteCidade.getSelectedItem();
                
                cidade = Long.valueOf(selectedItem.get("Codigo"));
                
                ClienteVO cliente = new ClienteVO();
                cliente.setNome(nome);
                cliente.setCpf(cpf);
                cliente.setCep(cep);

                cliente.setEndereco(endereco);
                cliente.setBairro(bairro);
                cliente.setEmail(email);
                cliente.setTelefonePrimario(telefone1);
                cliente.setTelefoneSecundario(telefone2);
                
                cliente.setCidadeId(cidade);
                
                clienteService.salvar(cliente);
                
                if (clienteService.isValido()) {
                    
                    super.finalize();
                }
                
                super.setMessages(clienteService.getMensagens());                

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

}
