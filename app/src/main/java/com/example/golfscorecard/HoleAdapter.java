package com.example.golfscorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.golfscorecard.databinding.HoleListItemBinding;

public class HoleAdapter extends RecyclerView.Adapter<HoleAdapter.myViewHolder> {
    private Hole[] holes;
    private Context context;

    public HoleAdapter(Hole[] holes, Context context){
        this.holes = holes;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HoleListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(context),
                        R.layout.hole_list_item,
                        parent,
                        false);
        return new myViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {
        final Hole hole = holes[position];
        holder.holeListItemBinding.setHole(hole);

        holder.holeListItemBinding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updatedStrokeCount = hole.getStrokeCount() + 1;
                hole.setStrokeCount(updatedStrokeCount);
                //notifyDataSetChanged();
                notifyItemChanged(position, hole); //adding the object (hole) will remove notifyItemChanged animation
            }
        });

        holder.holeListItemBinding.buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updatedStrokeCount = hole.getStrokeCount() - 1;
                if(updatedStrokeCount < 0) updatedStrokeCount = 0;
                hole.setStrokeCount(updatedStrokeCount);
                notifyItemChanged(position, hole);
            }
        });
    }

    @Override
    public int getItemCount() {
        return holes.length;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        //Binding Variables
        public HoleListItemBinding holeListItemBinding;

        // Constructor to do view lookups for each subview
        public myViewHolder(final HoleListItemBinding holeLayoutBinding){
           super(holeLayoutBinding.getRoot());
           holeListItemBinding = holeLayoutBinding;
        }
    }
}
