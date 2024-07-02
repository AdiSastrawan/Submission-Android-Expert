
package com.adisastrawan.androidexpertproject.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf

import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adisastrawan.androidexpertproject.R
import com.adisastrawan.core.data.Resource
import com.adisastrawan.androidexpertproject.databinding.FragmentHomeBinding
import com.adisastrawan.core.domain.model.News
import com.adisastrawan.core.ui.NewsListAdapter
import com.adisastrawan.core.utils.OnAdapterItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), OnAdapterItemClickListener {
    private val viewModel : HomeViewModel by viewModel()
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NewsListAdapter
    private var query: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NewsListAdapter(this)
        with(binding){
        searchBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_favorite -> {
                    val uri = Uri.parse("androidexpertproject://favorite")
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
                Log.d("HomeFragment", "onViewCreated: ${searchView.text}")
                query = searchView.text.toString()
                binding.progressBar.visibility = View.VISIBLE
                getNews(query)
                false
            }
        }
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNews.adapter = adapter
        binding.rvNews.setHasFixedSize(true)
        getNews(query)

    }
    private fun showToast(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    private fun getNews(query: String?=null){
        viewModel.getAllNews(query).observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("HomeFragment", "onViewCreated: Loading")
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.submitList(it.data)
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(it.message.toString())
                    Log.d("HomeFragment", "onViewCreated: ${it.message}")
                }
            }
        }
    }
    override fun onHistoryItemClick(news: News) {
        val bundle = Bundle()
        bundle.putParcelable("news",news)
        view?.findNavController()?.navigate(R.id.action_homeFragment_to_detailFragment,bundle)
    }
}