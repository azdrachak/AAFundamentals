package com.github.azdrachak.aafundamentals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.azdrachak.aafundamentals.data.Actor

class MovieDetailsAdapter : RecyclerView.Adapter<MovieDetailsViewHolder>() {

    private var actors: List<Actor> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailsViewHolder {
        return MovieDetailsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieDetailsViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    fun updateActors(newActors: List<Actor>) {
        actors = newActors
    }
}

class MovieDetailsViewHolder(listItem: View) : RecyclerView.ViewHolder(listItem) {
    private val photo: ImageView = listItem.findViewById(R.id.photo)
    private val name: TextView = listItem.findViewById(R.id.name)

    fun bind(actor: Actor) {
        photo.setImageResource(actor.photo)
        name.text = actor.name
    }
}