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
import com.br.managertranschool.business.filter.LocalidadeFilter;
import com.br.managertranschool.business.service.LocalidadeService;
import com.br.managertranschool.business.vo.LocalidadeVO;

/**
 * Classe activity responsavel pela view de pesquisar localidades.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 10/06/2012
 */
@ContentView(R.layout.pesquisar_localidade)
public class PesquisarLocalidadeActivity extends BaseActivity implements OnClickListener, OnItemClickListener {

    @Inject
    private LocalidadeService localidadeService;

    @InjectView(R.id.localidadeDescricao)
    private EditText localidadeDescricao;
    
    @InjectView(R.id.localidades_list)
    private ListView localidadesList;

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
        localidadesList.setOnItemClickListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View v) {

        try {
            if (v.getId() == R.id.btn_pesquisar) {

                String descricao = localidadeDescricao.getText().toString();
                
                LocalidadeVO localidade = new LocalidadeVO();
                localidade.setDescricao(descricao);

                List<LocalidadeVO> localidadeVOList = localidadeService.pesquisar(new LocalidadeFilter(localidade));
                
                if (localidadeService.isValido()) {                    
                    if (!localidadeVOList.isEmpty()) {
                        this.carregarListaLocalidades(localidadeVOList);
                    } else {
                        localidadesList.setAdapter(null);
                    }
                }
                
                super.setMessages(localidadeService.getMensagens());          

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
        
        long idLocalidade = Long.valueOf(item.get(LocalidadeVO.ID_LOCALIDADE));
     
        Intent it = new Intent(this, MapLocalidadeActivity.class);
        it.putExtra(LocalidadeVO.ID_LOCALIDADE, idLocalidade);
        super.startActivity(it);
    }
    
    /**
     * Método carrega ListView de localidades.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    private void carregarListaLocalidades(List<LocalidadeVO> localidadeVOList) {
        List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
        
        Map<String, String> map;
        
        for (LocalidadeVO localidade : localidadeVOList) {
            map = new HashMap<String, String>();
            map.put(LocalidadeVO.ID_LOCALIDADE, String.valueOf(localidade.getId()));
            map.put(LocalidadeVO.TX_DESCRICAO, localidade.getDescricao());
            mapList.add(map);
        }
        
        String[] from = {LocalidadeVO.TX_DESCRICAO};
        int[] to = {android.R.id.text1};
        
        SimpleAdapter adapter = new SimpleAdapter(this, mapList, android.R.layout.simple_list_item_2, from, to);
        localidadesList.setAdapter(adapter);
    }    
}
