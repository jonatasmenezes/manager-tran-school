package com.br.managertranschool.view.activity;

import roboguice.inject.ContentView;

import android.os.Bundle;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;


/**
 * Classe activity responsavel pela view de home do aplicativo.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 07/06/2012
 */
@ContentView(R.layout.app_home)
public class HomeActivity extends BaseActivity {

    /* (non-Javadoc)
     * @see com.br.managertranschool.architecture.BaseActivity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
}
