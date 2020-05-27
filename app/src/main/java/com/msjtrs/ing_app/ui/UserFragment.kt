package com.msjtrs.ing_app.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.msjtrs.ing_app.adapters.UserAdapter

import com.msjtrs.ing_app.databinding.UserFragmentBinding
import com.msjtrs.ing_app.domain.PostProperty
import kotlinx.android.synthetic.main.user_fragment.*


class UserFragment : Fragment(), OnMapReadyCallback {
    private val viewModel: UserViewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }


    private lateinit var googleMap: GoogleMap
    private lateinit var userGeoLocation: PostProperty
    private var userLatitude: Double = 0.0
    private var userLongitude: Double = 0.0

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)
        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)
    }

    @SuppressLint("LogNotTimber")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val userGeoLocation = UserFragmentArgs.fromBundle(requireArguments()).selectedProperty
        userLatitude = userGeoLocation.address.geo.lat.toDouble()
        userLongitude = userGeoLocation.address.geo.lng.toDouble()

        val application = requireNotNull(activity).application
        val binding = UserFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        val userProperty = UserFragmentArgs.fromBundle(arguments!!).selectedProperty

        val viewModelFactory = UserViewModelFactory(userProperty, application)

        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)

        binding.userPhotos.setOnClickListener{
            viewModel.displayPhotoProperties(userProperty)
        }

        viewModel.navigateToPhotoProperty.observe(viewLifecycleOwner, Observer {
            if(null != it){
                this.findNavController().navigate(UserFragmentDirections.navigateToPhotos(it))
                viewModel.displayPhotoPropertiesComplete()
            }
        })



        return binding.root
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let{
            googleMap = it
        }
        val location = LatLng(userLatitude, userLongitude)
        map?.addMarker(MarkerOptions().position(location).title("city"))
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 3f))
    }
}