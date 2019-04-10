package com.nextack.tenorsearcher.view.activities

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nextack.tenorsearcher.R
import com.nextack.tenorsearcher.application.TenorSearcherApplication
import com.nextack.tenorsearcher.view.OnFragmentInteractionListener

class MainActivity : AppCompatActivity(), OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as TenorSearcherApplication).getApplicationComponent().inject(this)

    }
}
