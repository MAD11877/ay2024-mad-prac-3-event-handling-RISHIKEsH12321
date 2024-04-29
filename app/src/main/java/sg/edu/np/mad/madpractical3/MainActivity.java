package sg.edu.np.mad.madpractical3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
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
        Intent recieve = getIntent();
        String num = recieve.getStringExtra("Random Number");


        User user = new User("John Doe", "MAD Dev", 1, false);
        TextView tvName = findViewById(R.id.promt);
        TextView tvDes = findViewById(R.id.lorem);
        Button btnFollow = findViewById(R.id.button1);
        Button btnMessage = findViewById(R.id.button2);
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.followed){
                    btnFollow.setText("Unfollow");
                    user.followed = false;
                    Toast.makeText(MainActivity.this,"Followed",Toast.LENGTH_SHORT).show();

                }
                else{
                    btnFollow.setText("Follow");
                    user.followed = true;
                    Toast.makeText(MainActivity.this,"Unfollowed",Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messageIntent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(messageIntent);
            }

        });

        tvName.setText(user.name + " " + num);
        tvDes.setText(user.description);

    }

}