package com.br.managertranschool.view.activity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.inject.Inject;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseMapActivity;
import com.br.managertranschool.business.filter.ClienteLocalidadeFilter;
import com.br.managertranschool.business.filter.ClienteRotaFilter;
import com.br.managertranschool.business.overlay.MapaOverlay;
import com.br.managertranschool.business.service.ClienteLocalidadeService;
import com.br.managertranschool.business.service.ClienteRotaService;
import com.br.managertranschool.business.service.ClienteService;
import com.br.managertranschool.business.service.LocalidadeService;
import com.br.managertranschool.business.vo.ClienteLocalidadeVO;
import com.br.managertranschool.business.vo.ClienteRotaVO;
import com.br.managertranschool.business.vo.ClienteVO;
import com.br.managertranschool.business.vo.LocalidadeVO;
import com.br.managertranschool.business.vo.RotaVO;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;


/**
 * Classe activity responsavel pela view de mapa da rota.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 11/06/2012
 */
@ContentView(R.layout.map_rota)
public class MapRotaActivity extends BaseMapActivity {

    @Inject
    private LocalidadeService localidadeService;
    
    @Inject
    private ClienteRotaService clienteRotaService;
    
    @Inject
    private ClienteService clienteService;
    
    @Inject
    private ClienteLocalidadeService clienteLocalidadeService;
    
    @InjectView(R.id.map_rota)
    private MapView mapView;
    
    @InjectView(R.id.rotaDistancia)
    private TextView rotaDistancia;
    
    private Long idRota;
    
    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseMapActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.idRota = super.getIntent().getLongExtra(RotaVO.ID_ROTA, Long.MIN_VALUE);
        
        if (this.idRota != null && this.idRota > 0) {
            
            List<ClienteRotaVO> clienteRotaList = clienteRotaService
                .pesquisar(new ClienteRotaFilter(new ClienteRotaVO(this.idRota)));
            
            List<Overlay> overlayList = this.mapView.getOverlays();
            Drawable drawable = super.getResources().getDrawable(R.drawable.zoom_in);
            MapaOverlay mapaOverlay = new MapaOverlay(drawable, this);
            Location location = null;
            double latitude;
            double longitude; 
            GeoPoint pontoMapa;
            OverlayItem overlay;
            Float distancia = null;
            List<ClienteLocalidadeVO> clienteLocalidadeList;
            ClienteVO cliente;
            ClienteLocalidadeVO clienteLocalidade;
            LocalidadeVO localidade;
            
            GeoPoint pontocentral = null;
            
            for (ClienteRotaVO clienteRotaVO : clienteRotaList) {
                cliente = clienteService.buscarPorId(new ClienteVO(clienteRotaVO.getClienteId()));
                
                clienteLocalidadeList = clienteLocalidadeService.pesquisar(
                    new ClienteLocalidadeFilter(new ClienteLocalidadeVO(clienteRotaVO.getClienteId())));
                
                if (!clienteLocalidadeList.isEmpty()) {
                    clienteLocalidade = clienteLocalidadeList.get(0);
                    localidade = localidadeService.buscarPorId(new LocalidadeVO(clienteLocalidade.getLocalidadeId()));
                    
                    latitude = localidade.getLatitude() * 1E6;
                    longitude = localidade.getLongitude() * 1E6;
                    
                    if (location != null) {
                        
                        Location location2 = new Location(localidade.getDescricao());
                        location2.setLatitude(latitude);
                        location2.setLongitude(longitude);
                        
                        distancia += location.distanceTo(location2);
                        
                    } else {
                        location = new Location(localidade.getDescricao());
                        location.setLatitude(latitude);
                        location.setLongitude(longitude);
                        distancia = 0F;
                    }
                    
                    location = new Location(localidade.getDescricao());
                    location.setLatitude(latitude);
                    location.setLongitude(longitude);
                                        
                    pontoMapa = new GeoPoint((int) latitude, (int) longitude);
                    
                    int i = 0;
                    if (i == 0) {
                        pontocentral = pontoMapa; 
                    }
                    i++;
                    overlay = new OverlayItem(pontoMapa, cliente.getNome(), localidade.getDescricao());
                    mapaOverlay.add(overlay);
                }
            }
            MyLocationOverlay locationOverlay = new MyLocationOverlay(this, this.mapView);
            locationOverlay.enableMyLocation();
            
            overlayList.add(locationOverlay);
            
            overlayList.add(mapaOverlay);
            MapController mapController = this.mapView.getController();            
            mapController.setZoom(16);
            if (pontocentral != null) {
                
                mapController.setCenter(pontocentral);
            }
            
            BigDecimal dist = new BigDecimal((distancia/1000)/1000);
            DecimalFormat df = new DecimalFormat( "#0.00" );
            this.rotaDistancia.setText(String.valueOf(df.format(dist)) + " KM");
            
        }
        
        this.mapView.setTraffic(true);
        this.mapView.setBuiltInZoomControls(true);
        this.mapView.setClickable(true);
        
    }
    
}

