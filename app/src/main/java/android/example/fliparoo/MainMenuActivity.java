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
    final int CLASSIC = 0;
    final int ADVANCED = 1;

    private Button buttonClassic;
    private Button buttonAdvanced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //starts running main page animation
        GradientAnimation();

        buttonClassic = findViewById(R.id.classicButton_button);
        buttonAdvanced = findViewById(R.id.advancedButton_button);

        buttonClassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, GameMenu.class);
                intent.putExtra(LEVEL, 4);
                intent.putExtra(FOR_TIMER, CLASSIC);
            }
        });

        buttonAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, GameMenu.class);
                intent.putExtra(LEVEL, 6);
                intent.putExtra(FOR_TIMER, ADVANCED);
            }
        });
    }

    private void GradientAnimation()
    {
        ConstraintLayout constraintLayout = findViewById(R.id.gradient_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
    }



}
