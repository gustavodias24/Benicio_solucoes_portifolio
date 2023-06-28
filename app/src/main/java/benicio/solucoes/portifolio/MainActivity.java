package benicio.solucoes.portifolio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import benicio.solucoes.portifolio.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding vb;
    private MediaPlayer somClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        somClick = MediaPlayer.create(getApplicationContext(), R.raw.sound_click);
        vb.btnConhecer.setOnClickListener(this);
        vb.btnQuemEu.setOnClickListener(this);
        vb.btnCpmoFunfa.setOnClickListener(this);
        vb.btnContato.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        somClick.start();
        if (v.getId() == R.id.btnContato){
            startActivity(new Intent(getApplicationContext(), ExibicaoActivity.class));
        }
        else if (v.getId() == R.id.btnConhecer){
            String linkPerfil = "https://play.google.com/store/apps/dev?id=7028667091030518152";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkPerfil)));
        }
        else if (v.getId() == R.id.btnQuemEu){
            Intent i = new Intent(getApplicationContext(), DescribeActivity.class);
            i.putExtra("type", 0);
            startActivity(i);
        }
        else if (v.getId() == R.id.btnCpmoFunfa){
            Intent i = new Intent(getApplicationContext(), DescribeActivity.class);
            i.putExtra("type", 1);
            startActivity(i);
        }
    }
}