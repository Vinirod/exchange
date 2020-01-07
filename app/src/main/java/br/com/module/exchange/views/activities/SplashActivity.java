package br.com.module.exchange.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import br.com.module.exchange.R;
import br.com.module.exchange.utils.VersionUtils;

public class SplashActivity extends AppCompatActivity {

    private View mWindow;

    private ImageView mImgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mWindow = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mWindow.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }

        VersionUtils.checkVersionAndEnableTransition(getWindow());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        Animation animation;
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_splash);

        mImgLogo = (ImageView) findViewById(R.id.idImgLogo);
        mImgLogo.startAnimation(animation);

        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    /**
     * Método para definir tempo de carregamento da tela
     */
    private void doWork() {
        for (int progress = 0; progress < 20; progress += 10) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para definir qual tela deve ser iniciada, conforme o estado do usuário (Se já acessou o app ou não).
     */
    private void startApp() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
