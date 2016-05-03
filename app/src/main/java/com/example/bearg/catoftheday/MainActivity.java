package com.example.bearg.catoftheday;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView mCatView;
    private Button mCatButton;
    private String mDrawableName; // name of the file, either babygirl or rocko
    private String mCatName; // for displaying the toast, either "Baby Girl" or "Rocko"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCatView = (ImageView) findViewById(R.id.cat_view);
        mCatButton = (Button) findViewById(R.id.cat_button);

        mCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int outcome = RandomCat.getOutcome();
                mCatView.setImageBitmap(getBitmap(outcome));
                mCatButton.setEnabled(false);
                mCatButton.setVisibility(View.INVISIBLE);

                mCatName = (mDrawableName == "babygirl")? "Baby Girl" : "Rocko";
                String toastText = String.format("Wow! %s is the cat of the day!", mCatName);
                Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static Bitmap decodeSampledBitmapFromResource(
            Resources res, int resId, int reqWidth, int reqHeight) {

        // Decode with inJustDecodeBounds = true to check the dimensions
        // of the required Bitmap to be created from the image.
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize, which is used to determine how much to
        // scale the original down by. An image with resolution 2048x1536
        // with inSampleSize of 4 produces a bitmap of approx. 512x384.
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with the inSampleSize having been set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);

    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {

        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2
            // and keeps both height and width larger than the requested height
            // and width

            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {

                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    private Bitmap getBitmap(int outcome) {
        // use the file names as the possible values
        mDrawableName = (outcome == 0)? "babygirl" : "rocko";
        int resId = getResources().getIdentifier(
                mDrawableName, "drawable", getPackageName());
        mCatView = (ImageView) findViewById(R.id.cat_view);
        return decodeSampledBitmapFromResource(getResources(), resId,
                412, 731);
    }


}


