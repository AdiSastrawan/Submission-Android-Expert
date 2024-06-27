package com.adisastrawan.favorite

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adisastrawan.androidexpertproject.home.HomeFragmentDirections
import com.adisastrawan.core.data.Resource
import com.adisastrawan.core.domain.model.News
import com.adisastrawan.core.ui.NewsListAdapter
import com.adisastrawan.core.utils.OnAdapterItemClickListener
import com.adisastrawan.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity(), OnAdapterItemClickListener {
    private var _binding : ActivityFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel : FavoriteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.setHasFixedSize(true)
        val adapter = NewsListAdapter(this)
        viewModel.getAllNews().observe(this){
            when(it){
                is Resource.Loading -> {
                    //show loading
                    Log.d("HomeFragment", "onViewCreated: Loading")
                }
                is Resource.Success -> {
                    val mapToNews = it.data?.map {favorite->
                        News(
                            newsId = favorite.url,
                            title = favorite.title,
                            description = favorite.description,
                            image = favorite.image,
                        )
                    }
                    adapter.submitList(mapToNews)
                }
                is Resource.Error -> {
                    Log.d("HomeFragment", "onViewCreated: ${it.message}")
                }
            }
        }

    }

    override fun onHistoryItemClick(news: News) {

    }
}