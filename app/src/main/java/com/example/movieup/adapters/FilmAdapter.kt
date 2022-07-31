package com.example.movieup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieup.MyApp
import com.example.movieup.R
import com.example.movieup.network.models.FilmDC
import com.example.movieup.network.models.ResponseFilms
import kotlin.reflect.KFunction2

class FilmAdapter(
    val paginationRequest: KFunction2<Int, (ResponseFilms?) -> Unit, Unit>
) : RecyclerView.Adapter<FilmAdapter.MainHolder>() {

    // этот лист содержит список фильмов.
    private var mListMovies: MutableList<FilmDC> = mutableListOf()

    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val moviePoster: ImageView = view.findViewById(R.id.imageViewFilm)
        val movieTitle: TextView = view.findViewById(R.id.textViewTitle)
        val movieDescription: TextView = view.findViewById(R.id.textViewDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_film_recycler, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.moviePoster.load(mListMovies[position].multimedia.src)
        holder.movieTitle.text = mListMovies[position].display_title
        holder.movieDescription.text = mListMovies[position].summary_short

        /*
        * Если при скроле элемент, который отрисовывается заранее будет равен последнему, тогда выполнится функция HomeViewModel.
        * Данная функция получит новые фильмы с учетом offset, и если ответ от сервера будет [200-300), тогда новые фильмы отобразятся в recyclerView.
        */
        if (position == mListMovies.size - 1) {
            paginationRequest(mListMovies.size) {
                if (it != null) {
                    mListMovies.addAll(it.results)
                    notifyItemRangeInserted(mListMovies.lastIndex, it.results.size)
                    Toast.makeText(
                        MyApp.instance.applicationContext,
                        "Еще 20 фильмов подгружено",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun getItemCount(): Int = mListMovies.size

    fun addElements(list: List<FilmDC>) {
        mListMovies.addAll(list)
        notifyItemRangeInserted(mListMovies.lastIndex, list.size)
    }
}