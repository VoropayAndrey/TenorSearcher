package com.nextack.tenorsearcher.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nextack.tenorsearcher.R
import com.nextack.tenorsearcher.application.TenorSearcherApplication
import com.nextack.tenorsearcher.repository.Repository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TenorSearcherApplication.getApplicationComponent().inject(this)

    }
}
