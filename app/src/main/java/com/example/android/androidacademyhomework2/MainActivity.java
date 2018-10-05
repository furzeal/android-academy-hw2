package com.example.android.androidacademyhomework2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button emailButton = findViewById(R.id.send_button);
        final EditText messageView = findViewById(R.id.message_input);


        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmailApp(messageView.getText().toString());
            }
        });
    }

    private void openEmailApp(String messageString) {
        final Intent intent = new Intent(Intent.ACTION_SENDTO)
                .setData(Uri.parse(String.format("mailto:%s", getString(R.string.email_address))))
                .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
                .putExtra(Intent.EXTRA_TEXT, messageString);

        // Check if the system can handle this type of intent or startActivity will crash otherwise.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.error_no_email_app, Toast.LENGTH_LONG).show();
        }
    }
}
