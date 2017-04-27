package volleyjsonparse.aminulaust.com.volleyjsonparse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowDetlais extends AppCompatActivity {

    TextView phone, dob,profession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detlais);
        phone=(TextView)findViewById(R.id.myPhone);
        dob=(TextView)findViewById(R.id.mydob);
        profession=(TextView)findViewById(R.id.myprofession);

        String _phone = getIntent().getStringExtra("MyPhone");
        String _dob = getIntent().getStringExtra("MyDob");
        String _profession = getIntent().getStringExtra("MyProfession");

        phone.setText(_phone);
        dob.setText(_dob+"\n\n");
        profession.setText(_profession);
    }
}
