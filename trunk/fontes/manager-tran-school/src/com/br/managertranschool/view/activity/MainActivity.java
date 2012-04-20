package com.br.managertranschool.view.activity;

import android.app.Activity;
import android.os.Bundle;

import com.br.managertranschool.R;

/**
 * Classe activity responsavel pela view principal da aplicacao.
 * 
 * @author Jonatas O. Menezes (menezes.jonatas@hotmail.com)
 * @since 19/04/2012
 */
public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}