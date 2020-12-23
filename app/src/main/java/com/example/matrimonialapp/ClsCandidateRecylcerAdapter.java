package com.example.matrimonialapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

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
    protected void onBindViewHolder(@NonNull final MyViewHolder holder, int position, @NonNull final ClsUserDetails model) {

        holder.name.setText(model.getName());
        holder.age.setText(model.getAge());
        holder.religion.setText(model.getReligion());
        Glide.with(holder.image.getContext()).load(model.getPictureUrl()).into(holder.image);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(holder.image.getContext(), "Name"+model.getName(), Toast.LENGTH_SHORT).show();
                        }
        });

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

        TextView name,age,religion;
        CircleImageView image;
        ConstraintLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.IdMyName);
            age = itemView.findViewById(R.id.IdMyAge);
            religion = itemView.findViewById(R.id.IdMyReligion);
            image=itemView.findViewById(R.id.IdMyPropic);
            layout = itemView.findViewById(R.id.IdConstrainMyROw);

            Log.d("Adapter: ","RecyclerView.ViewHolder");


        }
    }
}
