package com.example.exercise_week4.fragment

import GenreAddapter
import GenreId
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.exercise_week4.R
import com.example.movieapp.models.Movie
import kotlinx.android.synthetic.main.fragment_overview.*

/**
 * A simple [Fragment] subclass.
 */
class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview_geners.layoutManager = GridLayoutManager(context, 4)
        recyclerview_geners.hasFixedSize()
        var movie = arguments?.getParcelable<Movie>("movie")


        if (movie != null) {
            var url = "https://image.tmdb.org/t/p/w500/" + (movie.poster_path)
            Glide.with(this).load(url).into(avatarMovie)
            vote_average.text = movie.vote_average.toString()
            popularity.text = movie.popularity.toString() + " views"
            vote_count.text = movie.vote_count.toString() + " votes"
            adult.text = when(movie.adult){
                true -> "Adult: yes"
                else -> "Adult: no"
            }
            ratingbar.rating = movie.vote_average / 2
            language.text = "Language: " + movie.original_language
            release_date.text = movie.release_date
            nameMovie.text = movie.original_title
            overview.text = movie.overview

            var genres : List<GenreId> = movie.genre_ids.map { id -> when(id){
                28 -> GenreId(id, "Action")
                12 -> GenreId(id, "Adventure")
                16 -> GenreId(id, "Animation")
                35 -> GenreId(id, "Comedy")
                80 -> GenreId(id, "Crime")
                99 -> GenreId(id, "Documentary")
                18 -> GenreId(id, "Drama")
                10751 -> GenreId(id, "Family")
                14 -> GenreId(id, "Fantasy")
                36 -> GenreId(id, "History")
                27 -> GenreId(id, "Horror")
                10402 -> GenreId(id, "Music")
                9648 -> GenreId(id, "Mystery")
                10749 -> GenreId(id, "Romance")
                878 -> GenreId(id, "Science Fiction")
                10770 -> GenreId(id, "TV Movie")
                53 -> GenreId(id, "Thriller")
                10752 -> GenreId(id, "War")
                37 -> GenreId(id, "Western")
                else -> GenreId(id, "I don't know")
            } }

            recyclerview_geners.adapter = GenreAddapter(genres,context!!)
        }
        
        
    }

}
