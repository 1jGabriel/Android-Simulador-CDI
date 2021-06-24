package com.br.basedroid.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br.basedroid.R
import com.br.basedroid.databinding.ActivityMainBinding
import com.br.basedroid.presentation.ui.SimulationViewAction
import com.br.basedroid.presentation.ui.SimulationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }
}
