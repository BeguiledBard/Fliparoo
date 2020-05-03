package android.example.fliparoo;

import android.os.CountDownTimer;
import android.widget.EditText;

public class Timer extends CountDownTimer {
    EditText timerText;
    GameMenu theGameMenu;

    public Timer(long numOfSeconds, long interval, EditText text,GameMenu theGameMenu){
        super(numOfSeconds,interval);
        this.timerText = text;
        this.theGameMenu = theGameMenu;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        timerText.setText("Timer: " + millisUntilFinished/1000 + " seconds");
    }

    @Override
    public void onFinish() {
        timerText.setText("Timer: " + 0 + " seconds");
        theGameMenu.timeOutForGame();
    }
}
