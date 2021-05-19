package com.app.knowyourism.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.knowyourism.Activity.ProfileActivity;
import com.app.knowyourism.Model.Location.Location;
import com.app.knowyourism.Model.Student;
import com.app.knowyourism.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LocationResultAdapter extends RecyclerView.Adapter<LocationResultAdapter.ViewHolder> {
    List<Location> items;
    Context context;

    public LocationResultAdapter(List<Location> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.card_location, parent, false );
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Location singleCheckIn = items.get( position );
        holder.textViewName.setText( singleCheckIn.getName() );
        holder.textViewDesc.setText( singleCheckIn.getDescription() );
        Glide
                .with(context)
                .load(singleCheckIn.getPhoto().getSmall())
                .placeholder(R.drawable.map)
                .into(holder.imageView);
        //Glide.with(context).load(singleCheckIn.getPhoto().getSmall()).into( holder.imageView );
        //Picasso.get().load().into( holder.imageView );

        float f = 0.0f;
        if (holder.mapView != null && Float.compare( singleCheckIn.getCoordinates().getLatitude(), f ) != 0 &&
                Float.compare( singleCheckIn.getCoordinates().getLatitude(), f ) != 0) {
            holder.mapView.setVisibility( View.VISIBLE );
            holder.initializeMapView();
            if (holder.gMap != null) {
                // The map is already ready to be used
                moveMap( holder.gMap, singleCheckIn.getCoordinates().getLatitude(), singleCheckIn.getCoordinates().getLatitude() );
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
        GoogleMap gMap;
        MapView mapView;
        TextView textViewName = itemView.findViewById( R.id.textView3 ),
                textViewDesc = itemView.findViewById( R.id.textView4 );
        ImageView imageView = itemView.findViewById( R.id.imageView );

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            mapView = itemView.findViewById( R.id.checkInMapView );
        }


        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapsInitializer.initialize(context);
            gMap = googleMap;
            googleMap.getUiSettings().setMapToolbarEnabled(true);
            if(items.get(getLayoutPosition())!=null )
                moveMap(gMap,( items.get(getLayoutPosition())).getCoordinates().getLatitude(), (items.get(getLayoutPosition())).getCoordinates().getLongitude());
        }
        public void initializeMapView() {
            if (mapView != null) {
                // Initialise the MapView
                mapView.onCreate(null);
                // Set the map ready callback to receive the GoogleMap object
                mapView.getMapAsync(this);
                mapView.onResume();

            }
        }
    }

    public void moveMap(GoogleMap gMap, double latitude, double longitude) {
        Log.v( "TAG", "mapMoved: " + gMap );
        LatLng latlng = new LatLng( latitude, longitude );
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom( latlng, 15);
        gMap.addMarker( new MarkerOptions().position( latlng ));
        gMap.moveCamera( cu );
    }
}
