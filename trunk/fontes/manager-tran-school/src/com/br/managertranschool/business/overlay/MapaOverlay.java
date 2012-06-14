package com.br.managertranschool.business.overlay;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;


/**
 * Classe responsável em representar coleção de overlays dos mapas.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 12/06/2012
 */
public class MapaOverlay extends ItemizedOverlay<OverlayItem> {

    private ArrayList<OverlayItem> overlayList = new ArrayList<OverlayItem>();
    
    private Context context;
    
    /**
     * Construtor da classe.
     * 
     * @param defaultMarker
     * @param context
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public MapaOverlay(Drawable defaultMarker, Context context) {

        super(boundCenterBottom(defaultMarker));
        this.context = context;
    }

    /* (non-Javadoc)
     * @see com.google.android.maps.ItemizedOverlay#onTap(int)
     */
    @Override
    protected boolean onTap(int index) {
    
        OverlayItem overlay = this.overlayList.get(index);

        if (overlay.getTitle() != null && overlay.getTitle().length() > 0) {
            
            Toast.makeText(this.context, overlay.getTitle(), Toast.LENGTH_LONG).show();
        }

        return true;
    }
    
    /* (non-Javadoc)
     * @see com.google.android.maps.ItemizedOverlay#createItem(int)
     */
    @Override
    protected OverlayItem createItem(int index) {
        return this.overlayList.get(index);
    }

    /* (non-Javadoc)
     * @see com.google.android.maps.ItemizedOverlay#size()
     */
    @Override
    public int size() {

        return this.overlayList.size();
    }
   
    /**
     * Método add novo overlay na lista do mapa.
     * 
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public void add(OverlayItem overlay) {
    
        this.overlayList.add(overlay);
        populate();    
    }
    
}
