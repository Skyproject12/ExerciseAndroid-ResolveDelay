package com.example.tesjava;

import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tesjava.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgPreview;
    private DelayAsync delayAsync;
    private Button btnValue;
    private TextView tvText;
    private ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText = findViewById(R.id.tv_text);
        btnValue = findViewById(R.id.btn_setvalue);
        btnValue.setOnClickListener(this);
        names = new ArrayList<>();
        names.add("Narenda Wicaksono");
        names.add("Kevin");
        imgPreview = findViewById(R.id.img_preview);
        names.add("Yoza");
        // set image from drawable android
        imgPreview.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.front));
        Glide.with(this).load(R.drawable.front).into(imgPreview);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_setvalue) {
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < names.size(); i++) {
                name.append(names.get(i)).append("\n");
            }
            tvText.setText(name.toString());
            delayAsync = new DelayAsync();
            delayAsync.execute();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (delayAsync != null) {
            if (delayAsync.getStatus().equals(AsyncTask.Status.RUNNING)) {
                delayAsync.cancel(true);
            }
        }
    }

    // memperbaiki ketika delay lebih dari 5 detik
    private static class DelayAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("DelayAsync", "Done");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.d("DelayAsync", "Cancelled");
        }
    }
}
