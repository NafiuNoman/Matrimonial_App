package com.example.matrimonialapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ClsCandidateRecylcerAdapter extends FirebaseRecyclerAdapter<ClsUserDetails,ClsCandidateRecylcerAdapter.MyViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ClsCandidateRecylcerAdapter(@NonNull FirebaseRecyclerOptions<ClsUserDetails> options) {
        super(options);

        Log.d("Adapter: ","constructor Adaptor");
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull ClsUserDetails model) {

        holder.name.setText(model.getName());
        holder.age.setText(model.getAge());
        holder.salary.setText(model.getIncome());
        holder.gender.setText(model.getGender());

        Log.d("Adapter: ","onBindViewHolder");



    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.candidate_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);


        Log.d("Adapter: ","onCreateViewHolder");

        return myViewHolder ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,age,gender,salary;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.IdMyName);
            age = itemView.findViewById(R.id.IdMyAge);
            gender = itemView.findViewById(R.id.IdMyGender);
            salary = itemView.findViewById(R.id.IdMySalary);

            Log.d("Adapter: ","RecyclerView.ViewHolder");


        }
    }
}
