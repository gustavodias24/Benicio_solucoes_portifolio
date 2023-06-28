package benicio.solucoes.portifolio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import benicio.solucoes.portifolio.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;
    private Dialog dialog;

    private ActivityMainBinding vb;
    private MediaPlayer somClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        shared = getSharedPreferences("avaiacao", MODE_PRIVATE);
        editor = shared.edit();

        somClick = MediaPlayer.create(getApplicationContext(), R.raw.sound_click);
        vb.btnConhecer.setOnClickListener(this);
        vb.btnQuemEu.setOnClickListener(this);
        vb.btnCpmoFunfa.setOnClickListener(this);
        vb.btnContato.setOnClickListener(this);
        configurarAlertDialog();

        if(!shared.getBoolean("isAvalia", false)){
            dialog.show();
        }
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

    public void configurarAlertDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("AVALIAR O APP");
        b.setMessage("considere avaliar o nosso app com 5 estrelas ⭐⭐⭐⭐⭐");
        b.setCancelable(false);
        b.setPositiveButton("Ok", (dialog, which) -> {
            somClick.start();
            editor.putBoolean("isAvalia", true);
            editor.commit();
            String linkApp = "https://play.google.com/store/apps/details?id=benicio.solucoes.portifolio";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkApp)));
        });
        b.setNegativeButton("Agora não", (dialog, which) -> {
            somClick.start();
            dialog.dismiss();
        });

        dialog = b.create();
    }
}