package com.example.jenny.practice1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvHello;
    EditText editTextHello;
    Button btnCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();
    }

    private void initInstances() {
        tvHello = findViewById(R.id.tvHello);
        tvHello.setMovementMethod(LinkMovementMethod.getInstance());
        tvHello.setText(Html.fromHtml("<b>He<u>ll</u>o</b> <i>World</i> <a href=\"https://www.google.com/\">Google</a>"));

        editTextHello = findViewById(R.id.editTextHello);
        editTextHello.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    // Copy text in EditText to TextView
                    tvHello.setText(editTextHello.getText());
                    return true;
                }
                return false;
            }
        });

        btnCopy = findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnCopy){
            tvHello.setText(editTextHello.getText());
        }
    }
}
