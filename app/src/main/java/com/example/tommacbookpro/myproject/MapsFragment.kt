package com.example.tommacbookpro.myproject

import android.app.Activity
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tommacbookpro.myproject.utility.Contextor
import com.github.nitrico.mapviewpager.MapViewPager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapsFragment : Fragment(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener  {

    override fun onMarkerClick(p0: Marker?) = false
    private lateinit var mMap: GoogleMap
    var fusedLocationClient: FusedLocationProviderClient? = null
    private lateinit var lastLocation: Location
    private var mvp: MapViewPager? = null
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.activity_maps_fragment, container, false)


        val mapFragment = this.childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



        return rootView
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        Log.e("sfdf", "Sfdsf" + mMap)
      /*  mMap.uiSettings.isZoomControlsEnabled = true*/
        mMap.setOnMarkerClickListener(this)


        setUpMap()
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(Contextor.getInstance().context,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.requireActivity() as Activity,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        mMap.isMyLocationEnabled = true
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        fusedLocationClient?.lastLocation?.addOnSuccessListener { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16f))
            }
        }

    }

    private fun placeMarkerOnMap(currentLatLng: LatLng) {
        val markerOptions = MarkerOptions().position(currentLatLng)

        val titleStr = getAddress(currentLatLng)  // add these two lines
        markerOptions.title(titleStr)

        mMap.addMarker(markerOptions)
    }
    private fun getAddress(latLng: LatLng): String {
        // 1
        val geocoder = Geocoder(Contextor.getInstance().context)
        val addresses: List<Address>?
        val address: Address?
        var addressText = ""

        try {
            // 2
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            // 3
            if (null != addresses && !addresses.isEmpty()) {
                address = addresses[0]
                for (i in 0 until address.maxAddressLineIndex) {
                    addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(i)
                }
            }
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }

        return addressText
    }
}

