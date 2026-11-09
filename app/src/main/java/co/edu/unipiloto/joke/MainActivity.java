package co.edu.unipiloto.joke;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 33 &&
                checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
        }
    }

    public void onCalificar(View view) {
        iniciarServicio(R.string.titulo_estudiante, R.string.msg_estudiante, 5453);
    }

    public void onEntregar(View view) {
        iniciarServicio(R.string.titulo_profesor, R.string.msg_profesor, 5454);
    }

    private void iniciarServicio(int titleRes, int msgRes, int id) {
        Intent intent = new Intent(this, DelayedMessageService.class);
        intent.putExtra(DelayedMessageService.EXTRA_TITLE, getString(titleRes));
        intent.putExtra(DelayedMessageService.EXTRA_MESSAGE, getString(msgRes));
        intent.putExtra(DelayedMessageService.EXTRA_ID, id);
        startService(intent);
    }
}