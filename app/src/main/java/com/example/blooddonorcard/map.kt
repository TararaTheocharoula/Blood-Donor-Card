package com.example.blooddonorcard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class map : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap:GoogleMap

    val Aimodosia_Pagni = LatLng(36.0480815417613, 25.020767949058786)
    val Nosokomeio_Larisas = LatLng(39.83128790861986, 22.44284426794057)
    val EKEA = LatLng(38.3641724048732, 23.867012037237256)
    val EKEA_Aigaleo = LatLng(38.00210501354131, 23.67878206942495)
    val Aimodosia_Axepa = LatLng(40.80296826421746, 23.27780523545245)
    val O_Aimodotis = LatLng(38.41652950968339, 21.712466924308213)
    val Gefura_Zwis = LatLng(40.48619983202327, 21.761691899707188)
    val Nosokomeio_Metaxa = LatLng(38.17162351187458, 23.54525372387917)
    val Aimodotes_Agriniou = LatLng(38.768614977967864, 21.443088366706892)
    val Aimodosia_Mixalis_Pitenis =LatLng(40.0923297091755, 22.13522706938356)
    val SEANK = LatLng(40.72808005197474, 21.311252430391576)
    val Aimodosia_Vrontous = LatLng(40.35237411148354, 22.398898953861)
    val Mamatseio_Kozanis = LatLng(40.46948617642469, 21.83859619934645)
    val Aimodosia_Kerkuras = LatLng(39.81441228988899, 19.883029722805457)
    val Aimodosia_Amfissas = LatLng(38.64859261796256, 22.343967311261533)
    val Aimodosia_Argolidas = LatLng(37.81148783807922, 22.706516152418015)


    private var locationArrayList:ArrayList<LatLng>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val map = supportFragmentManager
            .findFragmentById(R.id.myMap) as SupportMapFragment
        map.getMapAsync(this)

        locationArrayList = ArrayList()

        locationArrayList!!.add(Aimodosia_Pagni)
        locationArrayList!!.add(Nosokomeio_Larisas)
        locationArrayList!!.add(EKEA)
        locationArrayList!!.add(EKEA_Aigaleo)
        locationArrayList!!.add(Aimodosia_Axepa)
        locationArrayList!!.add(O_Aimodotis)
        locationArrayList!!.add(Gefura_Zwis)
        locationArrayList!!.add(EKEA_Aigaleo)
        locationArrayList!!.add(Nosokomeio_Metaxa)
        locationArrayList!!.add(Aimodotes_Agriniou)
        locationArrayList!!.add(Aimodosia_Mixalis_Pitenis)
        locationArrayList!!.add(SEANK)
        locationArrayList!!.add(Aimodosia_Vrontous)
        locationArrayList!!.add(Mamatseio_Kozanis)
        locationArrayList!!.add(Aimodosia_Kerkuras)
        locationArrayList!!.add(Aimodosia_Amfissas)
        locationArrayList!!.add(Aimodosia_Argolidas)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        for (i in locationArrayList!!.indices){

            mMap.addMarker(MarkerOptions().position(locationArrayList!![i]).title("Marker"))
            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList!!.get(i)))

        }
    }
}