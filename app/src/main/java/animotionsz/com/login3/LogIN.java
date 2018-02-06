package animotionsz.com.login3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIN extends AppCompatActivity {
EditText userName,passWord;
    DBHelper mydb;
    Button logIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mydb=new DBHelper(this);
        userName=(EditText) findViewById(R.id.etusername);
        passWord=(EditText) findViewById(R.id.etpass);
        logIN=(Button)findViewById(R.id.login);
        logIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }
    public void login(){
        String username = userName.getText().toString();
        String password = passWord.getText().toString();
        if (username.length() > 0 && password.length() > 0) {
            try {

                if (mydb.getSingleEntry(username, password)) {
                    Toast.makeText(getApplicationContext(),
                            "Successfully Logged In", Toast.LENGTH_LONG)
                            .show();
Intent intent =new Intent(LogIN.this,Display.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Invalid username or password",
                            Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Some problem occurred",
                        Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Username or Password is empty", Toast.LENGTH_LONG).show();
        }
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
            case R.id.delete:
                deleteData();

        }

        return super.onOptionsItemSelected(item);
    }
    public void deleteData(){
        boolean x=mydb.deleteData(userName.getText().toString());
        if (x==true){
            Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext()," not Deleted",Toast.LENGTH_SHORT).show();
        }
    }
}
