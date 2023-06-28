package benicio.solucoes.portifolio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import benicio.solucoes.portifolio.databinding.ActivityExibicaoBinding;

public class ExibicaoActivity extends AppCompatActivity {
    private ActivityExibicaoBinding vb;
    private MediaPlayer som;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        vb = ActivityExibicaoBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(vb.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Contato");

        Picasso.get().load(R.raw.photo_contact).into(vb.imageContato);
        som = MediaPlayer.create(getApplicationContext(), R.raw.sound_click);
        String urlZap = "https://wa.me/5591984044333?text=Ol%C3%A1%2C+vim+pelo+seu+porif%C3%B3lio.";
        vb.btnZap.setOnClickListener( zapView -> {
            som.start();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlZap)));
        });

        vb.btnMail.setOnClickListener( mailView -> {
            som.start();
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"gustavo024dias@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Olá, vim pelo seu porifólio.");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            som.start();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}