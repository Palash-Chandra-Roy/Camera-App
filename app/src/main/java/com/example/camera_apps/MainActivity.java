package com.example.camera_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    public static final int RC_PIC_CODE=101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView= (ImageView) findViewById(R.id.imageViewId);
        button=(Button)findViewById(R.id.buttonID);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


         button();

            }

        });
    }
    public void button(){

        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,RC_PIC_CODE);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);

        if(resultCode==RC_PIC_CODE){
            if (resultCode == RESULT_OK) {
                Bitmap bp=(Bitmap)data.getExtras().get("data");

                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(MainActivity.this,"Cancelled",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
