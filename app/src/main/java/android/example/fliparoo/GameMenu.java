package android.example.fliparoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Toast;

public class GameMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        //starts running main page animation
        GradientAnimation();
    }

    // method for the gradient animation
    private void GradientAnimation()
    {
        ConstraintLayout constraintLayout = findViewById(R.id.gradient_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
    }

    // sends a toast to screen to signal that time is up, then calls helper to bring user back to main menu
    public void timeOutForGame() {
        Toast.makeText(GameMenu.this, "Time's up", Toast.LENGTH_LONG).show();
        helperForMenu();
    }

    // brings user back to main game menu
    private void helperForMenu(){
        Intent intent = new Intent(GameMenu.this,MainMenuActivity.class);
        startActivity(intent);
    }
}
