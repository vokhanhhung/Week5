package com.example.exercise_week4.fragment

import MovieAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise_week4.R
import com.example.movieapp.models.Movie
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {


    companion object{
        var movies = mutableListOf<Movie>()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        var id = arguments?.getInt("id")
        var movie = when(id){
            null -> null
            else -> Movie.getMovies().get(id!!)
        }
        if (movie!= null && !movies.contains(movie) ) movies.add(movie)
        setHasOptionsMenu(true)
         recycler_view.layoutManager = LinearLayoutManager(context)
         recycler_view.adapter = MovieAdapter(context!! , movies  ,R.id.action_favoriteFragment_to_overviewFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        recycler_view.layoutManager =  when(id){
            R.id.linear_layout ->LinearLayoutManager(context)
            else -> GridLayoutManager(context, 3)
        }
        return true

    }




}
