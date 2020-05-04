package android.example.fliparoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {
    final String LEVEL = "LEVEL";
    final String FOR_TIMER = "forTimer";
    final int CLASSIC = 1;
    final int ADVANCED = 2;

    private Button buttonClassic;
    private Button buttonAdvanced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonClassic = findViewById(R.id.classicButton_button);
        buttonAdvanced = findViewById(R.id.advancedButton_button);

        buttonClassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, GameMenu.class);
                intent.putExtra(LEVEL, 4);
                intent.putExtra(FOR_TIMER, CLASSIC);
                startActivity(intent);
            }
        });

        buttonAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, GameMenu.class);
                intent.putExtra(LEVEL, 6);
                intent.putExtra(FOR_TIMER, ADVANCED);
                startActivity(intent);
            }
        });
    }




}
