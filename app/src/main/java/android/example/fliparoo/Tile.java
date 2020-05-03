package android.example.fliparoo;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.GridLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;

public class Tile extends AppCompatButton {
    protected int row;
    protected int col;
    protected int frontTileImage;

    protected boolean isFlipped = false;
    protected Boolean isMatched = false;

    protected Drawable front;
    protected Drawable back;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Tile(Context context, int row, int col, int frontTileImage){
        super(context);

        this.row = row;
        this.col = col;
        this.frontTileImage = frontTileImage;

        front = context.getDrawable(frontTileImage);
        back = context.getDrawable(R.drawable.tile_back);
        setBackground(back);

        GridLayout.LayoutParams tempParams = new GridLayout.LayoutParams(GridLayout.spec(row), GridLayout.spec(col));

        tempParams.width = (int) getResources().getDisplayMetrics().density * 70;
        tempParams.height = (int) getResources().getDisplayMetrics().density * 70;

        setLayoutParams(tempParams);
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched)
    {
        isMatched = matched;
    }

    public int getFrontTileImage() {
        return frontTileImage;
    }

    public void flip()
    {
        if(isMatched)
        {
            return;
        }
        if(isFlipped)
        {
            setBackground(back);
            isFlipped = false;
        }
        else
        {
            setBackground(front);
            isFlipped = true;
        }
    }
}
