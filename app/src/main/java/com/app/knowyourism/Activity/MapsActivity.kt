package com.app.knowyourism.Activity

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.* // ktlint-disable no-wildcard-imports
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.* // ktlint-disable no-wildcard-imports
import java.lang.ref.WeakReference
import java.util.* // ktlint-disable no-wildcard-imports
import com.app.knowyourism.R;

const val MY_PERMISSIONS_REQUEST_LOCATION = 99
private const val COLOR_BLACK_ARGB = -0x1000000
private const val POLYLINE_STROKE_WIDTH_PX = 2

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var mLastLocation: Location
    private lateinit var mCurrLocationMarker: Marker
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var mLocationCallback: LocationCallback
    private var ref = WeakReference<Activity>(this)
    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        Objects.requireNonNull(supportActionBar)?.setDisplayShowHomeEnabled(true)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = (supportFragmentManager
                .findFragmentById(R.id.map) as? SupportMapFragment?)
        mapFragment?.getMapAsync(this)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = resources.getString(R.string.campus_map)
        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireNotNull(ref.get()))
    }

    public override fun onPause() {
        super.onPause()
        // stop location updates when Activity is no longer active
        mFusedLocationClient.removeLocationUpdates(mLocationCallback)
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val ism = LatLng(23.814110, 86.441207)
        val ismGate = LatLng(23.809163, 86.442590)
        val jasper = LatLng(23.817035, 86.440934)
        val ruby = LatLng(23.813257, 86.444626)
        mMap.addMarker(MarkerOptions().position(ism).title(resources.getString(R.string.marker_heritage)))
        mMap.addMarker(MarkerOptions().position(ismGate).title(resources.getString(R.string.marker_main_entrance)))
        mMap.addMarker(MarkerOptions().position(jasper).title(resources.getString(R.string.marker_jasper)).alpha(0.5f))
        mMap.addMarker(MarkerOptions().position(ruby).title(resources.getString(R.string.marker_ruby)).alpha(0.5f))
        val sac = LatLng(23.817462, 86.437456)
        mMap.addMarker(MarkerOptions().position(sac).title(resources.getString(R.string.marker_sac)))
        val pm = LatLng(23.814902, 86.441178)
        mMap.addMarker(MarkerOptions().position(pm).title(resources.getString(R.string.marker_penman)))
        val hc = LatLng(23.811926, 86.439028)
        mMap.addMarker(MarkerOptions().position(hc).title(resources.getString(R.string.marker_hc)))
        val polyline1 = googleMap.addPolyline(PolylineOptions()
                .clickable(true)
                .add(
                        LatLng(23.821271, 86.435213),
                        LatLng(23.819827, 86.434614),
                        LatLng(23.818309, 86.436635),
                        LatLng(23.817824, 86.436289),
                        LatLng(23.815959, 86.439142),
                        LatLng(23.811823, 86.436986),
                        LatLng(23.810356, 86.437202),
                        LatLng(23.809208, 86.441401),
                        LatLng(23.808920, 86.442469),
                        LatLng(23.811918, 86.444478),
                        LatLng(23.811966, 86.447407),
                        LatLng(23.814787, 86.447845),
                        LatLng(23.816345, 86.442538),
                        LatLng(23.817181, 86.442852),
                        LatLng(23.818064, 86.440134),
                        LatLng(23.819137, 86.440809),
                        LatLng(23.819931, 86.439832),
                        LatLng(23.818626, 86.438828),
                        LatLng(23.821271, 86.435213)
                ))
        stylePolyline(polyline1)
        val width1 = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        val padding = (width1 * 0.12).toInt() // offset from edges of the map 12% of screen
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ism))
        val bound = LatLngBounds(LatLng(23.809756, 86.433533), LatLng(23.820778, 86.449679))
        val update = CameraUpdateFactory.newLatLngBounds(bound, width1, height, padding)
        mMap.moveCamera(update)
        locationRequest()
    }

    private fun locationRequest() {
        mLocationRequest = LocationRequest()

        mLocationRequest.let {
            it.interval = 60000 // two minute interval
            it.fastestInterval = 60000
            it.smallestDisplacement = 10f
            it.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        }
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val locationList: List<Location> = locationResult.locations
                if (locationList.isNotEmpty()) {
                    // The last location in the list is the newest
                    val location = locationList[locationList.size - 1]
                    mLastLocation = location
//                    mCurrLocationMarker.remove()

                    // Place current location marker
                    val latLng = LatLng(location.latitude, location.longitude)
                    val markerOptions = MarkerOptions()

                    markerOptions.let {
                        it.position(latLng)
                        it.title(resources.getString(R.string.marker_title))
                        it.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                    }
                    mCurrLocationMarker = mMap.addMarker(markerOptions)
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                // Location Permission already granted
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())
                mMap.isMyLocationEnabled = true
            } else {
                // Request Location Permission
                checkLocationPermission()
            }
        } else {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())
            mMap.isMyLocationEnabled = true
        }
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                val permission = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
                with(AlertDialog.Builder(this)) {
                    setTitle(resources.getString(R.string.dialog_title))
                    setMessage(resources.getString(R.string.dialog_message))
                    setPositiveButton("OK") { dialogInterface: DialogInterface?, i: Int ->
                        ActivityCompat.requestPermissions(this@MapsActivity, permission,
                                MY_PERMISSIONS_REQUEST_LOCATION)
                    }
                }.show()
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) { // If request is cancelled, the result arrays are empty.
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                // location-related task you need to do.
                val permission = Manifest.permission.ACCESS_FINE_LOCATION
                if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                    mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())
                    mMap.isMyLocationEnabled = true
                }
            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return true
    }

    private fun stylePolyline(polyline: Polyline) {
        polyline.apply {
            endCap = RoundCap()
            width = POLYLINE_STROKE_WIDTH_PX.toFloat()
            color = R.color.teal_700
            jointType = JointType.ROUND
        }
    }
}
