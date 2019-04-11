package com.nextack.tenorsearcher.view.activities

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nextack.tenorsearcher.R
import com.nextack.tenorsearcher.application.TenorSearcherApplication
import com.nextack.tenorsearcher.utils.ToastUtils
import com.nextack.tenorsearcher.view.OnFragmentInteractionListener
import com.nextack.tenorsearcher.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnFragmentInteractionListener {

    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory
    @Inject
    lateinit var toastUtils: ToastUtils

    lateinit var mainViewModel: MainViewModel

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as TenorSearcherApplication).getApplicationComponent().inject(this)
        mainViewModel = ViewModelProviders.of(this, this.viewModeFactory).get(MainViewModel::class.java)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val navController: androidx.navigation.NavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        if(!navController.popBackStack()) {
            // Nothing to pop back
            finish()
        }
    }
}
