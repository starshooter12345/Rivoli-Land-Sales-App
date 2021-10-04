package com.project.landmanagementcode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.landsalesapp.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rentAdapter extends RecyclerView.Adapter<rentAdapter.Holder> {
    private Context context;
    private ArrayList<rentModel> arrayList;

    //database object
    rentDBhelper databaseHelper;
    private Holder holder;
    private int position;

    public rentAdapter(Context context, ArrayList<rentModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        databaseHelper=new rentDBhelper(context);
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ref,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, @SuppressLint("RecyclerView") final int position) {
        this.holder = holder;
        this.position = position;
        rentModel model = arrayList.get(position);
        //get for view
        final String id = model.getId();
        final String image=model.getImage();
        final String rtitle=model.getRtitle();
        final String rarea = model.getRarea();
        final String rental = model.getRental();
        final String rdes = model.getRdes();
        final String rseller=model.getRseller();
        final String addTimeStamp = model.getAddTimeStamp();
        final String updateTimeStamp = model.getUpdateTimeStamp();

                                                                        //Set views
        holder.profileId.setImageURI(Uri.parse(image));
        holder.rtitle.setText(rtitle);
        holder.rarea.setText(rarea);
        holder.rental.setText(rental);
        holder.rdes.setText(rdes);
        holder.rseller.setText(rseller);

        holder.editButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                editDialog(
                        "" +position,
                        ""+id,
                        ""+rtitle,
                        ""+rarea,
                        ""+rental,
                        ""+rdes,
                        ""+rseller,
                        ""+image,
                        ""+addTimeStamp,
                        ""+updateTimeStamp

                );
            }
        });
        //when long press on an item, show an alert dialog for deleting an item
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v){
                deleteDialog(
                        ""+id
                );
                return false;
            }
        });
    }

    private void deleteDialog(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Do you want to delete?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_delete);

        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                databaseHelper.deleteInfo(id);
                ((MainActivity)context).onResume();
                Toast.makeText(context,"Deleted successfully!",Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();

            }
        });

        builder.create().show();

    }

    private void editDialog(String position,final String id, final String rtitle,final String rarea,final String rental,final String rdes, final String rseller,final String image,final String addTimeStamp,final String updateTimeStamp){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Sure you want to update?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_edit);

        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                Intent intent = new Intent(context, EditRecordActivity.class);
                intent.putExtra("ID",id);
                intent.putExtra("RTITLE",rtitle);
                intent.putExtra("RAREA",rarea);
                intent.putExtra("RENTAL",rental);
                intent.putExtra("RDES",rdes);
                intent.putExtra("RSELLER",rseller);
                intent.putExtra("IMAGE",image);
                intent.putExtra("ADD_TIMESTAMP",addTimeStamp);
                intent.putExtra("UPDATE_TIMESTAMP",updateTimeStamp);
                intent.putExtra("editMode",true);
                context.startActivity(intent);

            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        builder.create().show();

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        public ImageSwitcher profileId;
        ImageView profileIv;
        TextView rtitle, rarea, rental, rdes, rseller;
        ImageButton editButton;

        public Holder(View itemView) {
            super(itemView);

            profileId = itemView.findViewById(R.id.profileIv);
            rtitle=itemView.findViewById(R.id.rtitle);
            rarea=itemView.findViewById(R.id.rarea);
            rental=itemView.findViewById(R.id.rental);
            rdes=itemView.findViewById(R.id.rdes);
            rseller=itemView.findViewById(R.id.rseller);
            editButton=itemView.findViewById(R.id.editBtn);

        }
    }

}
