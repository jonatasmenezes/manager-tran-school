package com.br.managertranschool.view.activity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseMapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;


/**
 * Classe activity responsavel pela view de mapa da localidade.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 11/06/2012
 */
@ContentView(R.layout.map_localidade)
public class MapLocalidadeActivity extends BaseMapActivity {

    @InjectView(R.id.map_localidade)
    private MapView mapView;
    
    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseMapActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        MyLocationOverlay locationOverlay = new MyLocationOverlay(this, mapView);
        locationOverlay.enableMyLocation();
        
        mapView.getOverlays().add(locationOverlay);
        mapView.setBuiltInZoomControls(true);
    }
    
}

