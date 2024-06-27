
package com.adisastrawan.androidexpertproject.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adisastrawan.androidexpertproject.R
import com.adisastrawan.core.data.Resource
import com.adisastrawan.androidexpertproject.databinding.FragmentHomeBinding
import com.adisastrawan.core.domain.model.News
import com.adisastrawan.core.ui.NewsListAdapter
import com.adisastrawan.core.utils.OnAdapterItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnAdapterItemClickListener {
    private val viewModel : HomeViewModel by viewModels()
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NewsListAdapter(this)
        with(binding){
        searchBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_favorite -> {
                    val uri = Uri.parse("newsapp://favorite")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                    false
                }

                else -> {
                    false
                }
            }
        }
        searchView.setupWithSearchBar(searchBar)
        searchView
            .editText
            .setOnEditorActionListener { _, _, _ ->
                searchBar.setText(searchView.text)
                searchView.hide()
                false
            }
        }
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNews.adapter = adapter
        binding.rvNews.setHasFixedSize(true)
        viewModel.getAllNews().observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    //show loading
                    Log.d("HomeFragment", "onViewCreated: Loading")
                }
                is Resource.Success -> {
                    adapter.submitList(it.data)
                }
                is Resource.Error -> {
                    Log.d("HomeFragment", "onViewCreated: ${it.message}")
                }
            }
        }
    }

    override fun onHistoryItemClick(news: News) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(news)
        view?.findNavController()?.navigate(action)
    }
}