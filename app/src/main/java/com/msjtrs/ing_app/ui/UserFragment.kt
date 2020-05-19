package com.msjtrs.ing_app.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import com.msjtrs.ing_app.databinding.UserFragmentBinding
import com.msjtrs.ing_app.domain.PostProperty
import kotlinx.android.synthetic.main.user_fragment.*


class UserFragment : Fragment() {
    //private lateinit var googleMap: GoogleMap
    private lateinit var userGeoLocation: PostProperty
    private var userLatitude: Double = 0.0
    private var userLongitude: Double = 0.0

//    override fun onActivityCreated(savedInstanceState: Bundle?){
//        super.onActivityCreated(savedInstanceState)
//        map_view.onCreate(savedInstanceState)
//        map_view.onResume()
//        map_view.getMapAsync(this)
//    }

    @SuppressLint("LogNotTimber")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val userGeoLocation = UserFragmentArgs.fromBundle(requireArguments()).selectedProperty
        userLatitude = userGeoLocation.posterGeoLatitude.toDouble()
        userLongitude = userGeoLocation.posterGeoLongitude.toDouble()

        val application = requireNotNull(activity).application
        val binding = UserFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        val postProperty = UserFragmentArgs.fromBundle(arguments!!).selectedProperty

        val viewModelFactory = UserViewModelFactory(postProperty, application)

        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
        return binding.root
    }

//    override fun onMapReady(map: GoogleMap?) {
//        map?.let{
//            googleMap = it
//        }
//        val location = LatLng(userLatitude, userLongitude)
//        map?.addMarker(MarkerOptions().position(location).title("city"))
//        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 3f))
//    }
}