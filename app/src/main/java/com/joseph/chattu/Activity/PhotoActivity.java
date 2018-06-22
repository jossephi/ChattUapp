package com.joseph.chattu.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.joseph.chattu.Entidades.Save;
import com.joseph.chattu.R;

public class PhotoActivity extends AppCompatActivity {

    private ImageView imgView;
    private Button enviarFoto,hacerFoto;
    private static final int CAMERA_REQUEST=123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imgView = (ImageView) findViewById(R.id.imageView);
        enviarFoto = (Button) findViewById(R.id.btnEnviarFoto);
        hacerFoto = (Button) findViewById(R.id.btnHacerFoto);

        hacerFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });

        enviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //convertir imagen a bitmap
                imgView.buildDrawingCache();
                Bitmap bmap = imgView.getDrawingCache();

                //guardar imagen
                Save savefile = new Save();
                savefile.SaveImage(PhotoActivity.this, bmap);
                returnMain();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CAMERA_REQUEST && resultCode== Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(photo);

        }
    }

    private void returnMain(){
        startActivity(new Intent(PhotoActivity.this, MainActivity.class));
        finish();
    }

}
