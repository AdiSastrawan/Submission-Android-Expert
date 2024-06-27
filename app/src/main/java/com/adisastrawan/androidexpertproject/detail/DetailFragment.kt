package com.adisastrawan.androidexpertproject.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.adisastrawan.androidexpertproject.R
import com.adisastrawan.androidexpertproject.databinding.FragmentDetailBinding
import com.adisastrawan.core.domain.model.Favorite
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private var isFavorite = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val news = args.news
        binding.tvToolbarTitle.text = news.title

        binding.newsWebview.apply {
            webViewClient = WebViewClient()
            val url = news.url
            if (url != null) {
                loadUrl(url)
            }
            settings.javaScriptEnabled = true
        }
        viewModel.isFavorite(news.newsId).observe(viewLifecycleOwner){
                Log.d("DetailFragment", "onViewCreated: $it")
            if (it){
                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
            }else{
                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
            }
            isFavorite = it
        }
        binding.btnBack.setOnClickListener{
            view.findNavController().popBackStack()
        }
        binding.fabFavorite.setOnClickListener{
            if (!isFavorite){
                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
            }else{
                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
            }
            val favorite = Favorite(id = news.newsId,title= news.title!!,url=news.url!!, description = news.description!!, image = news.image!!)
            lifecycleScope.launch {
                viewModel.setFavorite(favorite)
            }
            isFavorite = !isFavorite
        }
    }
}