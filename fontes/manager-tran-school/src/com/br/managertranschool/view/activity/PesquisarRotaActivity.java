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
import android.widget.Spinner;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.business.filter.LocalidadeFilter;
import com.br.managertranschool.business.filter.RotaFilter;
import com.br.managertranschool.business.service.LocalidadeService;
import com.br.managertranschool.business.service.RotaService;
import com.br.managertranschool.business.vo.LocalidadeVO;
import com.br.managertranschool.business.vo.RotaVO;

/**
 * Classe activity responsavel pela view de pesquisar rotas.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 13/06/2012
 */
@ContentView(R.layout.pesquisar_rota)
public class PesquisarRotaActivity extends BaseActivity implements OnClickListener, OnItemClickListener {

    @Inject
    private RotaService rotaService;

    @Inject
    private LocalidadeService localidadeService;

    @InjectView(R.id.rotaDescricao)
    private EditText rotaDescricao;
    
    @InjectView(R.id.rotaLocalPartida)
    private Spinner rotaLocalPartida;
    
    @InjectView(R.id.rotas_list)
    private ListView rotasList;

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
        
        String[] from = {LocalidadeVO.TX_DESCRICAO};
        int[] to = {android.R.id.text1};
        
        SimpleAdapter adapter = new SimpleAdapter(this, this.obterLocalidades(), android.R.layout.simple_spinner_item, from, to);
        rotaLocalPartida.setAdapter(adapter);
        
        this.btnNovo.setOnClickListener(this);
        this.btnPesquisar.setOnClickListener(this);
        rotasList.setOnItemClickListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {

        try {
            if (v.getId() == R.id.btn_pesquisar) {

                String descricao = rotaDescricao.getText().toString();
                Long localPartida = null;
                
                Map<String, String> selectedItem = super.getSelectedItem(this.rotaLocalPartida);
                if (selectedItem.get(RotaVO.ID_ROTA) != null && !selectedItem.get(RotaVO.ID_ROTA).equals(String.valueOf(0))) {
                    localPartida = Long.valueOf(selectedItem.get(RotaVO.ID_ROTA));
                }
                
                RotaVO rota = new RotaVO();
                rota.setDescricao(descricao);
                rota.setLocalPartida(localPartida);

                List<RotaVO> rotaVOList = rotaService.pesquisar(new RotaFilter(rota));
                
                if (rotaService.isValido()) {                    
                    if (!rotaVOList.isEmpty()) {
                        this.carregarListaRotas(rotaVOList);
                    } else {
                        this.rotasList.setAdapter(null);
                    }
                }
                
                super.setMessages(rotaService.getMensagens());          

            } else if (v.getId() == R.id.btn_novo) {
                Intent it = new Intent(this, InserirLocalidadeActivity.class); 
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
        
        long idRota = Long.valueOf(item.get(RotaVO.ID_ROTA));
     
        Intent it = new Intent(this, MapRotaActivity.class);
        it.putExtra(RotaVO.ID_ROTA, idRota);
        super.startActivity(it);
    }
    
    /**
     * Método carrega ListView de rotas.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private void carregarListaRotas(List<RotaVO> rotaVOList) {
        List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
        
        Map<String, String> map;
        LocalidadeVO localidade;
        
        for (RotaVO rota : rotaVOList) {
            map = new HashMap<String, String>();
            map.put(RotaVO.ID_ROTA, String.valueOf(rota.getId()));
            map.put(RotaVO.TX_DESCRICAO, rota.getDescricao());
            
            if (rota.getLocalPartida() != null) {
                localidade = localidadeService.buscarPorId(new LocalidadeVO(rota.getLocalPartida()));
                map.put(LocalidadeVO.TX_DESCRICAO + 1, super.getString(R.string.rota_local_partida) + ": " + localidade.getDescricao());
            }
            
            mapList.add(map);
        }
        
        String[] from = {RotaVO.TX_DESCRICAO, LocalidadeVO.TX_DESCRICAO + 1};
        int[] to = {android.R.id.text1, android.R.id.text2};
        
        SimpleAdapter adapter = new SimpleAdapter(this, mapList, android.R.layout.simple_list_item_2, from, to);
        rotasList.setAdapter(adapter);
    }  
    
    /**
     * Método obtem lista de localidade para o drop down.
     * 
     * @return Lista de {@link Map}.
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private List<Map<String, String>> obterLocalidades() {
        List<Map<String, String>> retornoList = new ArrayList<Map<String,String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put(LocalidadeVO.ID_LOCALIDADE, String.valueOf(0));
        map.put(LocalidadeVO.TX_DESCRICAO, super.getString(R.string.label_spinner_selecione));
        retornoList.add(map);
        
        List<LocalidadeVO> localidadeList = this.localidadeService.pesquisar(new LocalidadeFilter(new LocalidadeVO()));
        
        for (LocalidadeVO localidade : localidadeList) {
            map = new HashMap<String, String>();
            map.put(LocalidadeVO.ID_LOCALIDADE, String.valueOf(localidade.getId()));
            map.put(LocalidadeVO.TX_DESCRICAO, localidade.getDescricao());
            retornoList.add(map);
        }
                
        return retornoList;
    }
}
