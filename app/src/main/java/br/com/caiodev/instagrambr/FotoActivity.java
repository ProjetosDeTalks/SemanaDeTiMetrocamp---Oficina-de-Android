package br.com.caiodev.instagrambr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class FotoActivity extends AppCompatActivity {

    private ImageView imagem;
    private TextView legenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        imagem = findViewById(R.id.imagemComLegendaImageView);
        legenda = findViewById(R.id.legendaTextView);

        byte[] imagemIntent = getIntent().getByteArrayExtra("imagem");
        String legendaTextView = getIntent().getStringExtra("legenda");

        try {

            Bitmap bitmap = BitmapFactory
                    .decodeByteArray(imagemIntent, 0, imagemIntent.length);

            imagem.setImageBitmap(bitmap);
            legenda.setText(legendaTextView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}