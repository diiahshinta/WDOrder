package com.example.warehousedeliveryorder;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class kirim_text extends AppCompatActivity {
    Button button_kirim;
    Switch switch2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirim_text);
        button_kirim = findViewById(R.id.button_kirim);
        switch2 = findViewById(R.id.switch2);
        button_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch2.isChecked()){
                    sendReverseMessage();
                }else {
                    sendNormalMessage();
                }
            }
        });
    }


    public void sendNormalMessage(){
        Intent intent = new Intent(this, hasil_scan.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void sendReverseMessage(){
        Intent intent = new Intent(this, hasil_scan.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        char[] tempForwardsMessage = message.toCharArray();
        char[] tempBackwardsMessage = new char[message.length()];
        for(int i = 0 ; i < tempForwardsMessage.length ; i++){
            tempBackwardsMessage[i] = tempForwardsMessage[message.length() - i - 1];
        }
        String reversedMessage = new String(tempBackwardsMessage);

        intent.putExtra(EXTRA_MESSAGE, reversedMessage);
        startActivity(intent);
    }
}