package animotionsz.com.login3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Display extends AppCompatActivity {
    RecyclerView recyclerView;
    LoginAdapter adapter;
    DBHelper mydb;
    List<LoginDetails> datamodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mydb = new DBHelper(this);
        datamodel = new ArrayList<LoginDetails>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        datamodel=mydb.getData();
        adapter=new LoginAdapter(datamodel);
        RecyclerView.LayoutManager layoutManager = (new LinearLayoutManager(this));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
