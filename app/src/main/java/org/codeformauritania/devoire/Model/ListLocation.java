package org.codeformauritania.devoire.Model;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class ListLocation implements OnMapReadyCallback {

    private List<Marker> markerList = new ArrayList<>();
    private GoogleMap mMap;
    private LatLng agenceTevraghZeina = new LatLng(18.114630549682143, -15.991212274739345);
    private LatLng agenceKsar = new LatLng(18.10302589288889, -15.957566644804913);
    private LatLng agenceCarrefourMadrid = new LatLng(18.07973198461589, -15.965912862488732);
    private LatLng agenceDarNaim = new LatLng(18.106195513710773, -15.923646741869524);
    private LatLng agencePremier = new LatLng(18.13312832034129, -15.943229402766441);

    private Marker mAgenceTevraghZeina;
    private Marker mAgenceKsar;
    private Marker mAgenceCarrefourMadrid;
    private Marker mAgenceDarNaim;
    private Marker mAgencePremier;


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap=googleMap;
        mAgenceTevraghZeina = mMap.addMarker(new MarkerOptions().position(agenceTevraghZeina).title("agenceTevraghZeina").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mAgenceKsar = mMap.addMarker(new MarkerOptions().position(agenceKsar).title("agenceKsar").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mAgenceCarrefourMadrid = mMap.addMarker(new MarkerOptions().position(agenceCarrefourMadrid).title("agenceCarrefourMadrid").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mAgenceDarNaim = mMap.addMarker(new MarkerOptions().position(agenceDarNaim).title("agenceDarNaim").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mAgencePremier = mMap.addMarker(new MarkerOptions().position(agencePremier).title("agencePremier").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

    }
}
