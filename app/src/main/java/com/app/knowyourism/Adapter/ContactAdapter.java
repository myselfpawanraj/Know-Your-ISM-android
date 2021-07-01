package com.app.knowyourism.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.knowyourism.Activity.Clubs.Contract;
import com.app.knowyourism.Model.Club;
import com.app.knowyourism.Model.Contacts;
import com.app.knowyourism.Model.User;
import com.app.knowyourism.R;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactAdapter extends RecyclerView.Adapter< ContactAdapter.Holder > {
    private List< User > contacts = new ArrayList<>();
    private final Context context;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_contact_detail, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        User c = contacts.get(position);

        holder.name.setText(c.getName());
        holder.phone.setText(c.getPhone());
        holder.dept.setText(c.getDepartment());
        holder.email.setText(c.getInstituteEmail());
        holder.call.setOnClickListener(v-> makeCall(c.getPhone()));
        holder.send_mail.setOnClickListener(v-> sendMail(c.getInstituteEmail()));

        Glide
                .with(context)
                .load(c.getPhotoURI())
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .into(holder.icon);
    }
    void makeCall(String num) {
        if (num!=null && !num.trim().equals("0")) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num.trim()));
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "Number not available ", Toast.LENGTH_SHORT).show();
        }
    }

    void sendMail(String s) {
        if (s!=null && !s.trim().isEmpty()) {
            String uriText = "mailto:" + Uri.encode(s) +
                    "?subject=" + Uri.encode("Regarding the ...") +
                    "&body=" + Uri.encode("Dear Sir/ma'am\n\nthe body of the message\n\nYours truly,\n");

            Intent send = new Intent(Intent.ACTION_SENDTO, Uri.parse(uriText));
            context.startActivity(Intent.createChooser(send, "Send mail..."));
        } else {
            Toast.makeText(context, "Email not available ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setData(List<User> contactsList) {
        this.contacts.clear();
        this.contacts.addAll(contactsList);
        this.notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.contact_name)
        TextView name;
        @BindView(R.id.contact_img)
        ImageView icon;
        @BindView(R.id.contact_dept)
        TextView dept;
        @BindView(R.id.contact_phone)
        TextView phone;
        @BindView(R.id.contact_email)
        TextView email;
        @BindView(R.id.contact_call)
        ImageView call;
        @BindView(R.id.contact_send_mail)
        ImageView send_mail;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
