package com.example.rickmortyapi.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.rickmortyapi.R
import com.example.rickmortyapi.fragments.HomeFragment
import com.example.rickmortyapi.viewModels.HomeViewModel

class MainActivity : BaseActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        this.parentView = View.inflate(this, R.layout.activity_main, null)
        setContentView(parentView)

        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}