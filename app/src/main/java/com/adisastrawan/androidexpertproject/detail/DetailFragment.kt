package com.adisastrawan.androidexpertproject.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.adisastrawan.androidexpertproject.R
import com.adisastrawan.androidexpertproject.databinding.FragmentDetailBinding
import com.adisastrawan.core.domain.model.Favorite
import com.adisastrawan.core.domain.model.News
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModel()
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
        val news : News? = arguments?.getParcelable("news")
        binding.tvToolbarTitle.text = news?.title
        binding.tvToolbarSubtitle.text = news?.url
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_open_in_browser -> {
                    val uri = Uri.parse(news?.url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    true
                }

                else -> {
                    false
                }
            }
        }
        binding.newsWebview.apply {
            webViewClient = WebViewClient()
            val url = news?.url
            Log.d("DetailFragment", "onViewCreated: $url")
            if (url != null) {
                loadUrl(url)
            }
            settings.javaScriptEnabled = true
        }
        viewModel.isFavorite(news!!.newsId).observe(viewLifecycleOwner){
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
            val favorite = Favorite(id = news!!.newsId,title= news.title!!,url=news.url!!, description = news.description!!, image = news.image!!)
            lifecycleScope.launch {
                viewModel.setFavorite(favorite)
            }
            isFavorite = !isFavorite
        }
    }
}