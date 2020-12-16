package com.app.knowyourism.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.knowyourism.Model.Restaurant.Location2;
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
import com.google.android.material.chip.Chip;

import java.util.List;

public class RestaurantResultAdapter extends RecyclerView.Adapter<RestaurantResultAdapter.ViewHolder> {
    List<Location2> items;
    Context context;

    public RestaurantResultAdapter(List<Location2> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.card_restaurant, parent, false );
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Location2 singleCheckIn = items.get( position );
        holder.textViewName.setText( singleCheckIn.getName() );
        holder.textViewDesc.setText( singleCheckIn.getDescription() );
        holder.textViewAmbience.setText( "Ambience: " + singleCheckIn.getRating().getAmbience() );
        holder.textViewService.setText( "Service: " +  singleCheckIn.getRating().getService() );
        holder.textViewFood.setText( "Food: " +  singleCheckIn.getRating().getFood() );
        holder.textViewFare.setText( "Fare: ₹" + singleCheckIn.getCosts().getRickshaw() );
        holder.chip.setText( singleCheckIn.getPropertyType() );
        holder.chip2.setText( "x2 ₹" + singleCheckIn.getCosts().getSpending().getFor2() );
        holder.chip4.setText( "x4 ₹" +  singleCheckIn.getCosts().getSpending().getFor4() );
        holder.chip6.setText( "x6 ₹" +  singleCheckIn.getCosts().getSpending().getFor6() );
        Glide
                .with(context)
                .load(singleCheckIn.getPhoto().getSmall())
                .placeholder(R.drawable.logo)
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
                textViewDesc = itemView.findViewById( R.id.textView4 ),
                textViewAmbience = itemView.findViewById( R.id.textViewAmbience ),
                textViewService = itemView.findViewById( R.id.textViewService),
                textViewFood = itemView.findViewById( R.id.textViewFood ),
                textViewFare = itemView.findViewById( R.id.textViewFare );
        ImageView imageView = itemView.findViewById( R.id.imageView );
        Chip chip = itemView.findViewById( R.id.chiptype ),
                chip2 = itemView.findViewById( R.id.chip2 ),
                chip4 = itemView.findViewById( R.id.chip4 ),
                chip6 = itemView.findViewById( R.id.chip6 );

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            mapView = itemView.findViewById( R.id.checkInMapView );
        }


        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapsInitializer.initialize(context);
            gMap = googleMap;
            googleMap.getUiSettings().setMapToolbarEnabled(false);
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
