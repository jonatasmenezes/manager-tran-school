package com.br.managertranschool.view.activity;

import java.util.List;

import javax.inject.Inject;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseMapActivity;
import com.br.managertranschool.business.overlay.MapaOverlay;
import com.br.managertranschool.business.service.LocalidadeService;
import com.br.managertranschool.business.vo.LocalidadeVO;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;


/**
 * Classe activity responsavel pela view de mapa da localidade.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 11/06/2012
 */
@ContentView(R.layout.map_localidade)
public class MapLocalidadeActivity extends BaseMapActivity implements OnClickListener {

    @Inject
    private LocalidadeService localidadeService;
    
    @InjectView(R.id.map_localidade)
    private MapView mapView;
    
    @InjectView(R.id.btn_voltar)
    private Button btnVoltar;
    
    private Long idLocalidade;
    
    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseMapActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.idLocalidade = super.getIntent().getLongExtra(LocalidadeVO.ID_LOCALIDADE, Long.MIN_VALUE);
        
        if (this.idLocalidade != null && this.idLocalidade > 0) {
            LocalidadeVO localidade = localidadeService.buscarPorId(new LocalidadeVO(this.idLocalidade));
            double latitude = localidade.getLatitude() * 1E6;
            double longitude = localidade.getLongitude() * 1E6; 
            
            GeoPoint pontoMapa = new GeoPoint((int) latitude, (int) longitude);
            List<Overlay> overlayList = this.mapView.getOverlays();
            Drawable drawable = super.getResources().getDrawable(R.drawable.zoom_in);
            MapaOverlay mapaOverlay = new MapaOverlay(drawable, this);
            OverlayItem overlay = new OverlayItem(pontoMapa, localidade.getDescricao(), 
                localidade.getLatitude().toString() + localidade.getLongitude().toString());
            
            mapaOverlay.add(overlay);
            overlayList.add(mapaOverlay);
            
            MapController mapController = this.mapView.getController();            
            mapController.setZoom(16);
            mapController.animateTo(pontoMapa);
            mapController.setCenter(pontoMapa);
            
        } else {
            MyLocationOverlay locationOverlay = new MyLocationOverlay(this, this.mapView);
            locationOverlay.enableMyLocation();
            
            this.mapView.getOverlays().add(locationOverlay);
        }
        
        this.mapView.setBuiltInZoomControls(true);
        this.mapView.setClickable(true);
        
        this.btnVoltar.setOnClickListener(this);
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View view) {

        if (view.getId() == R.id.btn_voltar) {
            super.finish();
        }        
    }
    
}

