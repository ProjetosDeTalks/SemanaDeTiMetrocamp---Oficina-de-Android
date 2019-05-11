package br.com.caiodev.instagrambr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private TextView legendaFoto;
    private EditText textoDaLegenda;
    private Button gerarImagem;
    private ByteArrayOutputStream stream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciando as views do main_activity.xml com as variáveis criadas acima
        legendaFoto = findViewById(R.id.legendaFotoTextView);
        textoDaLegenda = findViewById(R.id.textoDaLegendaEditText);
        gerarImagem = findViewById(R.id.gerarImageButton);

        //Adicionando um Listener que "Ouvirá" eventos de alteração na caixa de texto(EditText)
        textoDaLegenda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Setando o texto na nossa TextView sempre que o usuário
                legendaFoto.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        gerarImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (!textoDaLegenda.getText().toString().isEmpty()) {

                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                                R.drawable.imagem);

                        stream = new ByteArrayOutputStream();

                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                        byte[] arrayDeBytes = stream.toByteArray();

                        //Limpando os recursos
                        stream.close();

                        startActivity(new Intent(getApplicationContext(), FotoActivity.class)
                                .putExtra("imagem", arrayDeBytes)
                                .putExtra("legenda", legendaFoto.getText().toString()));

                    } else {
                        Toast.makeText(getApplicationContext(), "Campo vazio", Toast.LENGTH_LONG)
                                .show();
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}