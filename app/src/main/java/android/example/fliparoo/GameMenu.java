package android.example.fliparoo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.Random;

public class GameMenu extends AppCompatActivity implements View.OnClickListener {

    private int numOfElements;
    private  Tile [] allButtons;
    private int [] allButtonsGraphicLocation; //random all the location
    private int [] allButtonsGraphics;
    private Tile selectButton1;
    private Tile selectButton2;
    private boolean isBusy = false;
    final String LEVEL = "LEVEL";
    final String FOR_TIMER = "forTimer";
    private int sizeOfMat;
    private int numOfSeconds;
    private EditText timer_txt;
    private int[] timerSeconds = {30, 45, 60};
    private Timer timer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);


        bindUI();
        initForTimer(numOfSeconds + 1, timer_txt);
        GridLayout theGridLayout = findViewById(R.id.grid_layout_for_all);
        sizeOfMat = getIntent().getIntExtra(LEVEL, 0);
        int numCol = sizeOfMat;
        int numRow = sizeOfMat;
        this.numOfElements = numCol * numRow;
        this.allButtons = new Tile[numOfElements];
        this.allButtonsGraphics = new int [numOfElements/2];
        if(numRow == 4){
            putAllButtonsGraphicForClassic();
        }else{
            putAllButtonsGraphicForAdvanced();
        }
        this.allButtonsGraphicLocation = new int [numOfElements];
        shuffleButtonGraphics();
        initMemoryButtons(numRow,numCol,theGridLayout);
    }

    public void putAllButtonsGraphicForClassic(){
        this.allButtonsGraphics[0] = R.drawable.tile0;
        this.allButtonsGraphics[1] = R.drawable.tile1;
        this.allButtonsGraphics[2] = R.drawable.tile2;
        this.allButtonsGraphics[3] = R.drawable.tile3;
        this.allButtonsGraphics[4] = R.drawable.tile4;
        this.allButtonsGraphics[5] = R.drawable.tile5;
        this.allButtonsGraphics[6] = R.drawable.tile6;
        this.allButtonsGraphics[7] = R.drawable.tile7;
    }


    public void putAllButtonsGraphicForAdvanced(){
        this.allButtonsGraphics[0] = R.drawable.tile0;
        this.allButtonsGraphics[1] = R.drawable.tile1;
        this.allButtonsGraphics[2] = R.drawable.tile2;
        this.allButtonsGraphics[3] = R.drawable.tile3;
        this.allButtonsGraphics[4] = R.drawable.tile4;
        this.allButtonsGraphics[5] = R.drawable.tile5;
        this.allButtonsGraphics[6] = R.drawable.tile6;
        this.allButtonsGraphics[7] = R.drawable.tile7;
        this.allButtonsGraphics[8] = R.drawable.tile8;
        this.allButtonsGraphics[9] = R.drawable.tile9;
        this.allButtonsGraphics[10] = R.drawable.tile10;
        this.allButtonsGraphics[11] = R.drawable.tile11;
        this.allButtonsGraphics[12] = R.drawable.tile12;
        this.allButtonsGraphics[13] = R.drawable.tile13;
        this.allButtonsGraphics[14] = R.drawable.tile14;
        this.allButtonsGraphics[15] = R.drawable.tile15;
        this.allButtonsGraphics[16] = R.drawable.tile16;
        this.allButtonsGraphics[17] = R.drawable.tile17;
    }

    public void onBackPressed(){
        timer.cancel();
        helperForMenu();
    }

    private boolean checkIfDone() {
        for (int i = 0; i < numOfElements; i++) {
            if (allButtons[i].isEnabled()) {
                return false;
            }
        }
        return true;
    }

    private void backToMenu() {
        Handler tempHandler = new Handler();
        tempHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                helperForMenu();
            }
        }, 3000);

    }

    protected void shuffleButtonGraphics(){
        Random rand = new Random();

        for (int i = 0; i < numOfElements ; i++ ){
            this.allButtonsGraphicLocation[i] = i % (numOfElements/2);
        }
        for (int i = 0; i < numOfElements ; i++ ){//swap location
            int temp = this.allButtonsGraphicLocation[i];
            if(numOfElements == 16){
                int swapIndex = rand.nextInt(16);
                allButtonsGraphicLocation[i] = allButtonsGraphicLocation[swapIndex];
                allButtonsGraphicLocation[swapIndex] = temp;
            }else{
                int swapIndex = rand.nextInt(24);
                allButtonsGraphicLocation[i] = allButtonsGraphicLocation[swapIndex];
                allButtonsGraphicLocation[swapIndex] = temp;
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initMemoryButtons(int numRow, int numCol, GridLayout theGridLayout){
        for (int row = 0; row < numRow ; row++){
            for(int col = 0 ; col <numCol ; col++){
                Tile tempButton = new Tile(this,row,col,allButtonsGraphics[allButtonsGraphicLocation[row *numCol +col]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                allButtons[row * numCol +col] = tempButton;
                theGridLayout.addView(tempButton);
            }
        }
    }

    private void initForTimer(long numOfSeconds, EditText timerText) {
        timer = new Timer(numOfSeconds * 1000, 1000, timerText, this);
        timer.start();
    }

    // sends a toast to screen to signal that time is up, then calls helper to bring user back to main menu
    public void timeOutForGame() {
        Toast.makeText(GameMenu.this, "Sorry, time is up!", Toast.LENGTH_LONG).show();
        helperForMenu();
    }

    // brings user back to main game menu
    private void helperForMenu(){
        Intent intent = new Intent(GameMenu.this,MainMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(isBusy){
            return;
        }
        Tile button = (Tile) view;
        if(button.isMatched){
            return;
        }
        if(selectButton1 == null){
            selectButton1 = button;
            selectButton1.flip();
            return;
        }
        if(selectButton1.getId() == button.getId()){
            return;
        }
        if(selectButton1.getFrontTileImage() == button.getFrontTileImage()){
            button.flip();
            button.setMatched(true);
            selectButton1.setMatched(true);

            selectButton1.setEnabled(false);
            button.setEnabled(false);
            selectButton1 = null;
            if(checkIfDone()){
                GameMenu.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(GameMenu.this, "Congratulations! You Won!", Toast.LENGTH_LONG).show();
                    }
                });
                timer.cancel();
                backToMenu();
            }
            return;

        }else{// are not the same
            selectButton2  = button;
            selectButton2.flip();
            isBusy = true;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectButton2.flip();
                    selectButton1.flip();
                    selectButton1 = null;
                    selectButton2 = null;
                    isBusy = false;
                }
            },500);
        }
    }

    private void bindUI() {
        timer_txt = (EditText)findViewById(R.id.timer_game);
        int level = getIntent().getIntExtra(FOR_TIMER, 0);
        numOfSeconds = timerSeconds[level];
    }
}
