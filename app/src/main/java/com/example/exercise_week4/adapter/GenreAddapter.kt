import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_week4.R
import kotlinx.android.synthetic.main.item_genre.view.*

class GenreAddapter (val genres: List<GenreId>, val context: Context) :
    RecyclerView.Adapter<GenreAddapter.GenreViewHolder>() {
    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var genre : Button

        init {
            genre = itemView.findViewById(R.id.genre)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
      return GenreViewHolder(LayoutInflater.from(context).inflate(R.layout.item_genre, null, false))
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.genre.text = genres.get(position).name
    }
}