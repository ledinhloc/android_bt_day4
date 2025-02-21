package com.example.bt1_day4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Switch aSwitch ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int[] backgroundImages = {
                R.drawable.bg1,
                R.drawable.bg2,
                R.drawable.bg3
        };

        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        int lastBgIndex = sharedPreferences.getInt("last_bg_index", -1);

        Random random = new Random();
        int newIndex;
        do {
            newIndex = random.nextInt(backgroundImages.length);
        } while (newIndex == lastBgIndex);

        int randomImage = backgroundImages[newIndex];

        ConstraintLayout layout = findViewById(R.id.main);
        layout.setBackgroundResource(randomImage);

        // Lưu lại index của hình nền mới
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("last_bg_index", newIndex);
        editor.apply();

        aSwitch = findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    layout.setBackgroundResource(backgroundImages[0]);
                }
                else {
                    layout.setBackgroundResource(backgroundImages[1]);
                }
            }
        });

    }
}