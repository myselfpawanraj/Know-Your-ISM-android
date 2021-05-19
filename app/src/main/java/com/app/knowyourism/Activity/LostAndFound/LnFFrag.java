package com.app.knowyourism.Activity.LostAndFound;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.LnF.LnFResponse;
import com.app.knowyourism.Model.LnF.LostFound;
import com.app.knowyourism.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LnFFrag  extends DialogFragment {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.bt_close)
    ImageButton bt_close;
    private ImageButton imageButton;
    private ImageView imageView;
    private EditText heading;
    private TextInputEditText detail;
    private ImageButton send;
    private String path=null;
    private String downloadUrl=null;
    Contract contract;
    File file;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_notice,container,false);
        ButterKnife.bind(this,view);
        contract = (Contract) getActivity();

        imageButton=view.findViewById(R.id.bt_img);
        imageView=view.findViewById(R.id.image);
        heading=view.findViewById(R.id.head);
        detail=view.findViewById(R.id.detail);
        detail.setText("____ belonging to Mr./Mrs. ___ has been lost/found somewhere between ______. If found, please contact ______.\n (item description)");
        send=view.findViewById(R.id.send);

        send.setEnabled(false);
        bt_close.setOnClickListener(v->dismiss());

        imageButton.setOnClickListener(v -> {
            EasyImage.openChooserWithGallery(this,"Pic image", EasyImage.RequestCodes.PICK_PICTURE_FROM_GALLERY);
            EasyImage.configuration(Objects.requireNonNull(getContext())).allowsMultiplePickingInGallery();
        });

        send.setOnClickListener(v ->{
            String h = heading.getText().toString().trim();
            String d = detail.getText().toString().trim();

            if(h.isEmpty() || d.isEmpty()){
                Toast.makeText(getContext(), "Fields missing!", Toast.LENGTH_SHORT).show();
                return;
            }
            changeProgressBar();
            uploadData();
        });

        return view;
    }

    private void uploadData() {
        if(file == null){
            Toast.makeText(getContext(), "Null file", Toast.LENGTH_SHORT).show();
            return;
        }
        String h = heading.getText().toString().trim();
        String d = detail.getText().toString().trim();
        String user = "60a3733180f187001e99857a";
        user = user.replaceAll("\"", "");

        MultipartBody.Part filePart = MultipartBody.Part.createFormData("photo", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

        ResultApi.getService().postLnF(h, d,true , user, filePart).enqueue(new Callback< LostFound >() {
            @Override
            public void onResponse(@NonNull Call<LostFound> call, @NonNull Response<LostFound> response) {
                changeProgressBar();
                if (response.isSuccessful() && response.body() != null) {
                    Snackbar.make(getActivity().findViewById(R.id.coordinator_layout), "Successfully Uploaded", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(getActivity().findViewById(R.id.coordinator_layout), "Failed to Upload", Snackbar.LENGTH_LONG).show();
                }
                dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<LostFound> call, @NonNull Throwable t) {
                changeProgressBar();
                Snackbar.make(getActivity().findViewById(R.id.coordinator_layout), "Failed due to unexpected error", Snackbar.LENGTH_LONG).show();
                dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {
                imageView.setImageURI(Uri.fromFile(imageFiles.get(0)));
                file = imageFiles.get(0);
                send.setEnabled(true);
            }
        });
    }

    public void changeProgressBar() {
        if(progressBar.isShown())
        {
            progressBar.setVisibility(View.GONE);
        }
        else progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        contract.getData();
    }
}