package com.br.managertranschool.business.adapter;

import java.util.List;

import com.br.managertranschool.business.vo.EstadoVO;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


/**
 * Adapter de Estado.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 09/06/2012
 */
public class EstadoAdapter extends BaseAdapter {

    private Context context; 
    private List<EstadoVO> estadoList;
    
    /**
     * Construtor padrão.
     * 
     * @param context - Context
     * @param estadoList - Lista de estados
     * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
     */
    public EstadoAdapter(Context context, List<EstadoVO> estadoList) {

        this.context = context;
        this.estadoList = estadoList;
    }
    
    /* (non-Javadoc)
     * @see android.widget.Adapter#getCount()
     */
    public int getCount() {

        return this.estadoList.size();
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItem(int)
     */
    public Object getItem(int posicao) {

        return this.estadoList.get(posicao);
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItemId(int)
     */
    public long getItemId(int posicao) {
        
        return posicao;
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    public View getView(int posicao, View view, ViewGroup viewParent) {

        return view;
    }

}
