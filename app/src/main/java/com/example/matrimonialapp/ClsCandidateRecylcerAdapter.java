package com.example.matrimonialapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    boolean touch = true;

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



        holder.love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(touch == true)
                {
                    holder.love.setColorFilter(Color.RED);
                    touch=false;
                }
                else if(touch==false)
                {
                    holder.love.setColorFilter(Color.WHITE);
                    touch=true;

                }

            }
        });

        holder.layouthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(holder.layouthome.getContext(), "ok its ok", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(holder.layouthome.getContext(),Act_InfoDisplay.class);
                Toast.makeText(holder.layouthome.getContext(), "oooooo", Toast.LENGTH_SHORT).show();
                intent.putExtra("ObjInfo",model);
                holder.layouthome.getContext().startActivity(intent);

            }
        });






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
        ConstraintLayout layout,layouthome;
        ImageView love;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.IdMyName);
            age = itemView.findViewById(R.id.IdMyAge);
            religion = itemView.findViewById(R.id.IdMyReligion);
            image=itemView.findViewById(R.id.IdMyPropic);
            layout = itemView.findViewById(R.id.IdConstrainMyROw);
            love = itemView.findViewById(R.id.IdLove);

            layouthome = itemView.findViewById(R.id.layouthome);

            Log.d("Adapter: ","RecyclerView.ViewHolder");


        }


    }
}
