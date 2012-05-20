package com.br.managertranschool.view.activity;

import roboguice.inject.ContentView;
import android.os.Bundle;

import com.br.managertranschool.R;
import com.br.managertranschool.architecture.BaseActivity;
import com.br.managertranschool.architecture.database.DatabaseCreate;

/**
 * Classe activity responsavel pela view principal do aplicativo.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 23/04/2012
 */
@ContentView(R.layout.main)
public class MainActivity extends BaseActivity {
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        new DatabaseCreate(this);
    }
}