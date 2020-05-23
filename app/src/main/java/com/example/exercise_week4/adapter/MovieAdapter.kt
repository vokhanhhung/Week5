import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercise_week4.fragment.OverviewFragment
import com.example.exercise_week4.R
import com.example.exercise_week4.fragment.FavoriteFragment
import com.example.movieapp.models.Movie

class MovieAdapter(val context : Context, val movies :MutableList<Movie>,val action : Int) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(
        itemView: View) : RecyclerView.ViewHolder(itemView){
        var avatarMovie : ImageView
        var nameMovie : TextView
        var ratingMovie : RatingBar
        var favoriteMovie : ImageView
       // var nameMovie : TextView
        init {
           itemView.layoutParams = RecyclerView.LayoutParams(
               RecyclerView.LayoutParams.MATCH_PARENT,
               RecyclerView.LayoutParams.WRAP_CONTENT
           )
             avatarMovie = itemView.findViewById(R.id.avatarMovie)
             nameMovie = itemView.findViewById(R.id.nameMovie)
           ratingMovie = itemView.findViewById(R.id.ratingMovie)
           favoriteMovie = itemView.findViewById(R.id.favoriteMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, null, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {




        var movie = movies.get(position)
        var url = "https://image.tmdb.org/t/p/w500/" + movie.poster_path
        Glide.with(context).load(url).into(holder.avatarMovie)
        holder.nameMovie.text = movie.title
        holder.ratingMovie.rating = movie.vote_average/2
        if(action == R.id.action_favoriteFragment_to_overviewFragment) {
            holder.favoriteMovie.setBackgroundResource(R.drawable.delete_icon)
        }
        else if(action == R.id.action_nowPlayingFragment_to_overviewFragment) {
            holder.favoriteMovie.setBackgroundResource(R.drawable.favorite)
        }
        else holder.favoriteMovie.visibility = View.GONE

        holder.favoriteMovie.setOnClickListener {
               if(action == R.id.action_favoriteFragment_to_overviewFragment){
                   var builder = AlertDialog.Builder(context)
                   builder.setTitle("Remove movie")
                       .setMessage("Remove this movie from favorite list")
                       .setPositiveButton("OK", {dialog, which ->  movies.removeAt(holder.adapterPosition)
                       notifyItemRemoved(holder.adapterPosition)
                       })
                       .setNegativeButton("No", {dialog, which -> dialog.
                       dismiss()})

                   var alert = builder.create()
                   alert.show()

               }
               else {
                   var bundle = bundleOf("id" to holder.adapterPosition)
                   var action = R.id.action_nowPlayingFragment_to_favoriteFragment
                   var builder = AlertDialog.Builder(context)
                   builder.setTitle("Add a movie")
                       .setMessage("Do you want add this movie to  favorive list ?")
                       .setPositiveButton("OK", {dialog, which -> Navigation.findNavController(it).navigate(action, bundle)})
                       .setNegativeButton("No", {dialog, which -> dialog.dismiss()  })

                   var alert = builder.create()
                   alert.show()
               }


        }


        holder.avatarMovie.setOnClickListener {


                var bundle = bundleOf("movie" to movie)

                Navigation.findNavController(it).navigate(action,bundle)

        }
    }




}

