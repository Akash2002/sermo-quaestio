package com.example.akashveerappan.voquiz;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

//This is the class that helps the app build a custom list view for the user to learn the words
//This custom list view is called, by Android, the Recycler View. There are two text views in this
//recycler view: the english text and the latin text.

//Extend the custom nested class for the RecyclerView Adapter to implement 3 methods: create, bind, getCount
public class VocabRecyclerAdapter extends RecyclerView.Adapter<VocabRecyclerAdapter.ViewHolder> {

    //Arrays representing the english text and the latin text

    private ArrayList<String> latinList;
    private ArrayList<String> englishList;

    public VocabRecyclerAdapter(ArrayList<String> a, ArrayList<String> b) {
        latinList = a;
        englishList = b;
    }

    @NonNull
    @Override
    //upon instantiation, return the inflated custom view holder *referenced below
    public VocabRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false));
    }

    //when binding the view, in other words, updating the view, set the text for those respective text views
    //this method calls itself only some amount of times as Android *recycles* the list view (It is an advanced concept
    //that I don't get that much*

    @Override
    public void onBindViewHolder(@NonNull VocabRecyclerAdapter.ViewHolder holder, int position) {
        holder.latinText.setText(latinList.get(position));
        holder.englishText.setText(englishList.get(position));
    }

    // return the number of items there are in the list
    @Override
    public int getItemCount() {
        if (latinList.size() == englishList.size()) {
            return latinList.size();
        } else {
            return 0;
        }
    }


    //the custom view holder class that is returned the adapter is instantiated
    public class ViewHolder extends RecyclerView.ViewHolder {

        //The two text views: english and latin
        TextView latinText, englishText;

        //upon instantiation, setup the two text views
        public ViewHolder(View itemView) {
            super(itemView);
            latinText = itemView.findViewById(R.id.latinText);
            englishText = itemView.findViewById(R.id.englishText);
        }

    }
}
