package com.example.jenny.practice1;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvHello;
    EditText editTextHello;
    Button btnCopy;

    EditText editText1;
    EditText editText2;
    TextView tvResults;
    Button btnCalculate;

    RadioGroup rgOperator;

    CustomViewGroup viewGroup1;
    CustomViewGroup viewGroup2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x; // Screen Width
        int height = size.y; // Screen Height

        Toast.makeText(MainActivity.this, "Width : " + width + " Height : " + height, Toast.LENGTH_LONG).show();
    }

    private void initInstances() {
        tvHello = findViewById(R.id.tvHello);
        tvHello.setMovementMethod(LinkMovementMethod.getInstance());
        tvHello.setText(Html.fromHtml("<b>He<u>ll</u>o</b> <i>World</i> <a href=\"https://www.google.com/\">Google</a>"));

        editTextHello = findViewById(R.id.editTextHello);
        editTextHello.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Copy text in EditText to TextView
                    tvHello.setText(editTextHello.getText());
                    return true;
                }
                return false;
            }
        });

        btnCopy = findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(this);

        // Start here
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        tvResults = findViewById(R.id.tvResults);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(this);

        rgOperator = findViewById(R.id.rgOperator);

        viewGroup1 = findViewById(R.id.viewGroup1);
        viewGroup2 = findViewById(R.id.viewGroup2);

        viewGroup1.setButtonText("Hello");
        viewGroup2.setButtonText("World");

    }

    @Override
    public void onClick(View v) {
        if (v == btnCopy) {
            tvHello.setText(editTextHello.getText());
        } else if (v == btnCalculate) {
            int number1 = 0;
            int number2 = 0;
            int result = 0;
            try {
                number1 = Integer.parseInt(editText1.getText().toString());
            } catch (NumberFormatException e) {
            }
            try {
                number2 = Integer.parseInt(editText2.getText().toString());
            } catch (NumberFormatException e) {
            }


            // Check Operator
            switch (rgOperator.getCheckedRadioButtonId()) {
                case R.id.rbPlus:
                    result = number1 + number2;
                    break;
                case R.id.rbMinus:
                    result = number1 - number2;
                    break;
                case R.id.rbMultiply:
                    result = number1 * number2;
                    break;
                case R.id.rbDivide:
                    result = number1 / number2;
                    break;

                // Add case here
            }

            tvResults.setText("= " + result);
            Log.d("Calculation", "Result = " + result);
            Log.i("Cal2",result+"");
            Log.w("Cal3",result+"");
            Log.e("Cal4",result+"");

            Toast.makeText(MainActivity.this, "Result = " + result, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this,
                    SecondActivity.class);
            intent.putExtra("result", result);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            // Do what you want
            Toast.makeText(MainActivity.this, "Setting", Toast.LENGTH_SHORT).show();
            // Handled
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
