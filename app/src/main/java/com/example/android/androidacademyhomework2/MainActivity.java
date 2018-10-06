package com.example.android.androidacademyhomework2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button emailButton = findViewById(R.id.send_button);
        final EditText messageView = findViewById(R.id.message_input);

        final Button facebookButton = findViewById(R.id.facebook_button);
        final Button vkButton = findViewById(R.id.vk_button);
        final Button githubButton = findViewById(R.id.github_button);

        // Listeners
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmailApp(messageView.getText().toString());
            }
        });

        vkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBrowser(getString(R.string.facebook_url));
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBrowser(getString(R.string.vk_url));
            }
        });

        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBrowser(getString(R.string.github_url));
            }
        });
    }

    private void openEmailApp(String messageString) {
        final Intent intent = new Intent(Intent.ACTION_SENDTO)
                .setData(Uri.parse(String.format("mailto:%s", getString(R.string.hero_email))))
                .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
                .putExtra(Intent.EXTRA_TEXT, messageString);

        // Check if the system can handle this type of intent or startActivity will crash otherwise.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.error_no_email_app, Toast.LENGTH_LONG).show();
        }
    }

    private void openBrowser(String url) {

        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.error_no_browser_app, Toast.LENGTH_LONG).show();
        }
    }
}
