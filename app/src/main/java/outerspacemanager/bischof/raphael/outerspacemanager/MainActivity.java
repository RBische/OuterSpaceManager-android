package outerspacemanager.bischof.raphael.outerspacemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnBuildings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BuildingsActivity.class));
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.ivExample);
        Glide
                .with(this)
                .load("http://www.raphaelbischof.fr/img/usine.jpg")
                .centerCrop()
                .placeholder(R.drawable.test)
                .crossFade()
                .into(imageView);
    }
}
