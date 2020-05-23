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
import kotlinx.android.synthetic.main.fragment_top_rating.*

/**
 * A simple [Fragment] subclass.
 */
class TopRatingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = MovieAdapter(context!! , Movie.getMoviesTopRating(),R.id.action_topRatingFragment_to_overviewFragment)
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
