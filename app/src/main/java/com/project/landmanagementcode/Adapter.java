package com.project.landmanagementcode;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.landsalesapp.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
	private Context context;
	private ArrayList<Model> arrayList;

	//database object
	DatabaseHelper databaseHelper;

	public Adapter(Context context, ArrayList<Model> arrayList) {
		this.context = context;
		this.arrayList = arrayList;
		databaseHelper=new DatabaseHelper(context);
	}


	@NonNull
	@Override
	public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.row,parent,false);
		return new Holder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull Holder holder, final int position) {
		Model model = arrayList.get(position);
		//get for view
		final String id = model.getId();
		final String image=model.getImage();
		final String landtitle=model.getLandtitle();
		final String extentinperches = model.getExtentinperches();
		final String priceperperch = model.getPriceperperch();
		final String landaddress = model.getLandaddress();
		final String landdescription=model.getLanddescription();
		final String sellername = model.getSellername();
		final String selleremail = model.getSelleremail();
		final String sellerphone=model.getSellerphone();
		final String addTimeStamp = model.getAddTimeStamp();
		final String updateTimeStamp = model.getUpdateTimeStamp();
		//buddhisha branch comment

		//set views
		holder.profileIv.setImageURI(Uri.parse(image));
		holder.landtitle.setText(landtitle);
		holder.extentinperches.setText(extentinperches);
		holder.priceperperch.setText(priceperperch);
		holder.landaddress.setText(landaddress);
		holder.landdescription.setText(landdescription);
		holder.sellername.setText(sellername);
		holder.sellerphone.setText(sellerphone);
		holder.selleremail.setText(selleremail);

		holder.editButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				editDialog(
						"" +position,
						""+id,
						""+landtitle,
						""+extentinperches,
						""+priceperperch,
						""+landaddress,
						""+landdescription,
						""+sellername,
						""+sellerphone,
						""+selleremail,
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

	private void editDialog(String position,final String id, final String landtitle,final String extentinperches,final String priceperperch,final String landaddress, final String landdescription,final String sellername,final String sellerphone,final String selleremail,final String image,final String addTimeStamp,final String updateTimeStamp){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Update");
		builder.setMessage("Sure you want to update?");
		builder.setCancelable(false);
		builder.setIcon(R.drawable.ic_action_edit);

		builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				Intent intent = new Intent(context, EditRecordActivity.class);
				intent.putExtra("ID",id);
				intent.putExtra("LANDTITLE",landtitle);
				intent.putExtra("EXTENTINPERCHES",extentinperches);
				intent.putExtra("PRICEPERPERCH",priceperperch);
				intent.putExtra("LANDADDRESS",landaddress);
				intent.putExtra("LANDDESCRIPTION",landdescription);
				intent.putExtra("SELLERNAME",sellername);
				intent.putExtra("SELLERPHONE",sellerphone);
				intent.putExtra("SELLEREMAIL",selleremail);
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

		ImageView profileIv;
		TextView landtitle, extentinperches, priceperperch, landaddress, landdescription, sellername, sellerphone, selleremail;
		ImageButton editButton;

		public Holder(View itemView) {
			super(itemView);

			profileIv = itemView.findViewById(R.id.profileIv);
			landtitle=itemView.findViewById(R.id.landtitle);
			extentinperches=itemView.findViewById(R.id.extentinperches);
			priceperperch=itemView.findViewById(R.id.priceperperch);
			landaddress=itemView.findViewById(R.id.landaddress);
			landdescription=itemView.findViewById(R.id.landdescription);
			sellername=itemView.findViewById(R.id.sellername);
			sellerphone=itemView.findViewById(R.id.sellerphone);
			selleremail=itemView.findViewById(R.id.selleremail);
			editButton=itemView.findViewById(R.id.editBtn);

		}
	}

}
