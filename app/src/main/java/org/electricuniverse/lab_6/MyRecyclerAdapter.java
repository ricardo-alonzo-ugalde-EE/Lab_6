package org.electricuniverse.lab_6;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>
{
    private List<Contact> mContactList;
    private Context mContext;
    private RecyclerView myRecyclerView;
    public MyRecyclerAdapter(List<Contact> myDataset, Context context, RecyclerView recyclerView)
    {
        mContactList = myDataset;
        mContext = context;
        myRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_recycler_adapter, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);
        return view_holder;
    }

    public void updateList(List<Contact> myDataset)
    {
        mContactList = myDataset;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {

        final Contact c = mContactList.get(position);
        holder.name_v.setText("Name: "+ c.getName());
        holder.last_name_v.setText("Last Name: "+ c.getLastName());
        holder.phone_v.setText("Phone: "+ c.getPhoneNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Chose option");
                builder.setMessage("Update or delete user?");
                builder.setPositiveButton( "Update", (dialog, which) ->
                {
                    goToUpdateActivity(c.getID());
                });
                builder.setNeutralButton( "Delete", (dialog, which) ->
                {
                    MyDBHelper dbHelper = new MyDBHelper(mContext);
                    dbHelper.deleteContact(c.getID(), mContext);
                    mContactList.remove(position);
                    myRecyclerView.removeViewAt(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mContactList.size());
                    notifyDataSetChanged();
                });
                builder.setNegativeButton("Cancel", (dialog, which) ->
                {
                    dialog.dismiss();
                });
                builder.create().show();
            }


        });


//        holder.itemView.setOnClickListener((v ->
//        {
//            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//
//
//            builder.setPositiveButton("Update", ((dialog, which) ->
//            {
//                goToUpdateActivity(c.getID());
//            }));
//            builder.setNeutralButton("Delete", ((dialog, which) ->
//            {
//                MyDBHelper dbHelper = new MyDBHelper(mContext);
//                dbHelper.deleteContact(c.getID(), mContext);
//                mContactList.remove(position);
//                myRecyclerView.removeViewAt(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position, mContactList.size());
//                notifyDataSetChanged();
//            }));
//            builder.setNegativeButton("Cancel", ((dialog, which) ->
//            {
//                dialog.dismiss();
//            }));
//            builder.create().show();
//
//        }));

    }

    private void goToUpdateActivity(long personID)
    {
        Intent intent = new Intent(mContext, UpdateContact.class);
        intent.putExtra("Contact_ID", personID);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount()
    {
      return mContactList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name_v;
        public TextView last_name_v;
        public TextView phone_v;
        public ViewHolder(View view)
        {
            super(view);
            name_v = (TextView)view.findViewById(R.id.first_name_view);
            last_name_v = (TextView)view.findViewById(R.id.last_name_view);
            phone_v = (TextView)view.findViewById(R.id.phone_number_view);

        }

    }
}



