package animotionsz.com.login3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Shrikant on 18-01-2018.
 */

public class SignUp extends AppCompatActivity {
    Button signup;
    EditText  name,pass,mob;
    DBHelper mydb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb=new DBHelper(this);
        setContentView(R.layout.signup);
        signup=(Button) findViewById(R.id.signup);
        name=(EditText)findViewById(R.id.etname);
        pass=(EditText)findViewById(R.id.etpass);
        mob=(EditText)findViewById(R.id.etmob);
        AddData();


    }
    public void AddData(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean x=mydb.insertData(name.getText().toString(),pass.getText().toString(),mob.getText().toString()) ;
                if (x==true){
                    Toast.makeText(getApplicationContext(),"Data inserted Successfully",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Data not inserted",Toast.LENGTH_SHORT).show();
                }
                name.setText("");
                pass.setText("");
                mob.setText("");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.update:
                updateData();

        }
        return super.onOptionsItemSelected(item);
    }
    public void updateData(){
        boolean x=mydb.updateData(name.getText().toString(),pass.getText().toString(),mob.getText().toString());
        if (x==true){
            Toast.makeText(SignUp.this, "updated...!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(SignUp.this, "not updated...!", Toast.LENGTH_SHORT).show();
        }
    }
}
