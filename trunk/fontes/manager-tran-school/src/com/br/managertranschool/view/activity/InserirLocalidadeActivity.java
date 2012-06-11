package com.br.managertranschool.view.activity;

import javax.inject.Inject;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.business.service.LocalidadeService;
import com.br.managertranschool.business.vo.LocalidadeVO;

/**
 * Classe activity responsavel pela view de inserir nova localidade.
 * 
 * @author Jeferson Almeida (jef.henrique.07@gmail.com)
 * @since 09/06/2012
 */
@ContentView(R.layout.inserir_localidade)
public class InserirLocalidadeActivity extends BaseActivity implements OnClickListener {

    @Inject
    private LocalidadeService localidadeService;

    @InjectView(R.id.localidadeLatitude)
    private EditText localidadeLatitude;

    @InjectView(R.id.localidadeLongitude)
    private EditText localidadeLongitude;

    @InjectView(R.id.localidadeDescricao)
    private EditText localidadeDescricao;

    @InjectView(R.id.btn_salvar)
    private Button btnSalvar;

    @InjectView(R.id.btn_cancelar)
    private Button btnCancelar;

    private Long idLocalidade;

    /*
     * (non-Javadoc)
     * 
     * @see com.br.managertranschool.architecture.BaseActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        idLocalidade = super.getIntent().getLongExtra(LocalidadeVO.ID_LOCALIDADE, Long.MIN_VALUE);

        this.btnSalvar.setOnClickListener(this);
        this.btnCancelar.setOnClickListener(this);

        if (this.idLocalidade != null && this.idLocalidade > 0) {
            // TODO this.carregarDadosLocalidade();
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

                String latitude = localidadeLatitude.getText().toString();
                String longitude = localidadeLongitude.getText().toString();
                String descricao = localidadeDescricao.getText().toString();

                LocalidadeVO localidade = new LocalidadeVO();
                if (latitude != null && latitude.trim().length() > 0) {
                    localidade.setLatitude(Double.valueOf(latitude));
                }

                if (longitude != null && longitude.trim().length() > 0) {
                    localidade.setLongitude(Double.valueOf(longitude));
                }

                localidade.setDescricao(descricao);

                localidadeService.salvar(localidade);

                if (localidadeService.isValido()) {
                    super.finish();
                }

                super.setMessages(localidadeService.getMensagens());

            } else if (v.getId() == R.id.btn_cancelar) {
                super.finish();
            }
        } catch (Exception e) {
            super.tratarException(this.getClass().getName(), e);
        } catch (Throwable e) {
            super.tratarException(this.getClass().getName(), new Exception(e));
        }

    }

}
