package com.adisastrawan.favorite.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adisastrawan.core.data.Resource
import com.adisastrawan.core.domain.model.News
import com.adisastrawan.core.ui.NewsListAdapter
import com.adisastrawan.core.utils.OnAdapterItemClickListener
import com.adisastrawan.favorite.R
import com.adisastrawan.favorite.databinding.FragmentFavoriteBinding
import com.adisastrawan.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext

class FavoriteFragment : Fragment(),  OnAdapterItemClickListener {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalContext.loadKoinModules(favoriteModule)
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavorite.setHasFixedSize(true)
        binding.btnBack.setOnClickListener {
            requireActivity().finish()
        }
        val adapter = NewsListAdapter(this)
        viewModel.getAllNews().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val mapToNews = it.data?.map { favorite ->
                        News(
                            newsId = favorite.url,
                            title = favorite.title,
                            description = favorite.description,
                            image = favorite.image,
                            url = favorite.url
                        )
                    }
                    binding.rvFavorite.adapter = adapter
                    adapter.submitList(mapToNews)
                }

                is Resource.Error -> {
                    //hide loading
                    binding.progressBar.visibility = View.GONE
                    showToast(it.message.toString())
                    Log.d("FavoriteFragment", "onViewCreated: ${it.message}")
                }
            }
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onHistoryItemClick(news: News) {
        val bundle = Bundle()
        bundle.putParcelable("news",news)
        view?.findNavController()?.navigate(R.id.action_favoriteFragment2_to_detailFragment2,bundle)
    }

}