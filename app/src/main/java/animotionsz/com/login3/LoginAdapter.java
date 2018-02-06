package animotionsz.com.login3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shrikant on 21-01-2018.
 */

public class LoginAdapter extends RecyclerView .Adapter<LoginAdapter.RecyclerViewHolder> {
    List<LoginDetails> loginDetails;

    LoginAdapter(List<LoginDetails> loginDetails) {


        this.loginDetails = loginDetails;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Mobile, Password;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.tvusername);
            Password = (TextView) itemView.findViewById(R.id.tvpassword);
            Mobile = (TextView) itemView.findViewById(R.id.tvmobile);
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        LoginDetails details = loginDetails.get(position);
        holder.Password.setText("Password :"+details.getPassword());
        holder.Mobile.setText("Mobile :"+details.getMobile());
        holder.Name.setText("Username :"+details.getUsername());


    }


    @Override
    public int getItemCount() {
       return loginDetails.size();
    }

}
