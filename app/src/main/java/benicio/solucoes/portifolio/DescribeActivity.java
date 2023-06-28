package benicio.solucoes.portifolio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.Objects;

import benicio.solucoes.portifolio.databinding.ActivityDescribeBinding;

public class DescribeActivity extends AppCompatActivity {
    private ActivityDescribeBinding vb;
    private MediaPlayer som;
    private Bundle payload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb = ActivityDescribeBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        som = MediaPlayer.create(getApplicationContext(), R.raw.sound_click);
        payload = getIntent().getExtras();

        if (payload.getInt("type") == 0){
            config_descri();
        }else{
            config_duvidas();
        }

    }

    @SuppressLint("ResourceType")
    private void config_descri(){
        getSupportActionBar().setTitle("Quem é Benicio");
        vb.gifExibicao.setImageResource(R.raw.mexedo_pc);
        vb.textTitle.setText(R.string.title_conhecer);
        vb.textDescribe.setText(R.string.descri_conhecer);
    }
    @SuppressLint("ResourceType")
    private void config_duvidas(){
        getSupportActionBar().setTitle("Como funciona");
        vb.gifExibicao.setImageResource(R.raw.duvidas_gif);
        vb.textTitle.setText(R.string.title_duvidas);
        vb.textDescribe.setText(R.string.descri_duvidas);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            som.start();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}