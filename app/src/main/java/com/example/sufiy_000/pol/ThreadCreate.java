package com.example.sufiy_000.pol;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ThreadCreate extends Activity {

    EditText m_title_input;
    EditText m_content_input;

    Button m_submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_create);

        m_title_input = (EditText) findViewById(R.id.title_input);
        m_content_input = (EditText) findViewById(R.id.content_input);

        m_submit_btn = (Button) findViewById(R.id.subbmit_btn);
        m_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent output = new Intent();
                output.putExtra("Title", m_title_input.getText().toString());
                //Log.d("Submit Title", m_title_input.getText().toString());
                output.putExtra("Content", m_content_input.getText().toString());
                setResult(RESULT_OK, output);
                finish();
            }
        });
    }
}
