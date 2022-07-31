package com.example.movieup.ui.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieup.adapters.FilmAdapter
import com.example.movieup.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val viewModel: HomeViewModel by viewModels()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = FilmAdapter(paginationRequest = viewModel::getOffsetFilms)

        viewModel.getFilms {
            binding.circularProgressIndicator.visibility = View.GONE
            adapter.addElements(it.results)
            binding.recyclerViewFilms.layoutManager = layoutManager
            binding.recyclerViewFilms.adapter = adapter
        }
    }
}