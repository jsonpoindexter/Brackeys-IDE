/*
 * Copyright 2021 Brackeys IDE contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brackeys.ui.feature.settings.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.brackeys.ui.R
import com.brackeys.ui.databinding.ActivitySettingsBinding
import com.brackeys.ui.feature.settings.viewmodel.SettingsViewModel
import com.brackeys.ui.utils.extensions.fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    private val viewModel: SettingsViewModel by viewModels()

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawableResource(R.color.colorBackground)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = supportFragmentManager
            .fragment<NavHostFragment>(R.id.nav_host).navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title = destination.label
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.fullscreenMode) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}