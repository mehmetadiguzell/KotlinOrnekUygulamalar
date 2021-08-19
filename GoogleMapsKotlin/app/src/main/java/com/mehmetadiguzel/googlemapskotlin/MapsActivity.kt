package com.mehmetadiguzel.googlemapskotlin

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*
import java.util.jar.Manifest

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        //Harita hazır olduğunda çalıştırılan fonk.
        mMap = googleMap
        mMap.setOnMapLongClickListener(dinleyici)

        //Latitude ->Enlem
        //Longitude ->Boylam

        //39.912107, 32.851357
        // casting =as -> bunun gibi

        locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = object :LocationListener{
            override fun onLocationChanged(location: Location) {
               //lokasyon,konum değişince yapılacak işlemler
               // println(location.latitude)
                //println(location.longitude)
                mMap.clear()
                val guncelKonum=LatLng(location.latitude,location.longitude)
                mMap.addMarker(MarkerOptions().position(guncelKonum).title("Güncel Konum"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(guncelKonum,15f))


                //geocoder -> enlem ve boylamı adrese döünüştürür.
                val  geocoder = Geocoder(this@MapsActivity, Locale.getDefault())
                try {
                  val adresListesi=  geocoder.getFromLocation(location.latitude,location.longitude,1)
                    if (adresListesi.size >0){
                        println(adresListesi.get(0).toString())
                    }


                }catch (e:Exception){
                    e.printStackTrace()
                }

            }

        }

        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            //izin verilmemiş

            //izin verilmemiş ise izin isteme,request->istek
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)


        }else{
            //izin verilmiş
                //provodier->sağlayıcı
                    //minTime ->Zaman
                    //minDistance    ->Uzaklık
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1f,locationListener)
            val sonBilinenKonum=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (sonBilinenKonum != null){
                val sonBilinenLat=LatLng(sonBilinenKonum.latitude,sonBilinenKonum.longitude)
                mMap.addMarker(MarkerOptions().position(sonBilinenLat).title("Son Konum"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sonBilinenLat,15f))
            }
        }

        /*
        val ankara = LatLng(39.912107,32.851357)
        mMap.addMarker(MarkerOptions().position(ankara).title("TBMM"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ankara,14f))
         */
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode==1){
            if (grantResults.size > 0){
                if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    //izin verildi
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1f,locationListener)
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)


    }

    val dinleyici = object :GoogleMap.OnMapLongClickListener {
        override fun onMapLongClick(p0: LatLng?) {
            mMap.clear()
            val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())
            if(p0 != null){
                var adres =""
                try {
                    val adresListesi=geocoder.getFromLocation(p0.latitude,p0.longitude,1)
                    if (adresListesi.size>0){
                        if(adresListesi.get(0).subThoroughfare !=null){
                            adres+=adresListesi.get(0).subThoroughfare
                            if (adresListesi.get(0).thoroughfare!=null){
                                adres+=adresListesi.get(0).thoroughfare
                            }
                        }
                    }

                }catch (e:Exception){
                    e.printStackTrace()
                }
                mMap.addMarker(MarkerOptions().position(p0).title(adres))
            }
        }


    }
}