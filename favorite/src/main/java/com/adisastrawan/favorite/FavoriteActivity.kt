package com.adisastrawan.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adisastrawan.favorite.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}