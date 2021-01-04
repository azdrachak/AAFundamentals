package com.github.azdrachak.aafundamentals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.azdrachak.aafundamentals.data.Actor
import com.github.azdrachak.aafundamentals.databinding.ViewHolderActorBinding

class MovieDetailsAdapter : RecyclerView.Adapter<MovieDetailsViewHolder>() {

    private var actors: List<Actor> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_actor, parent, false)
        return MovieDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieDetailsViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    fun updateActors(newActors: List<Actor>) {
        actors = newActors
        notifyDataSetChanged()
    }
}

class MovieDetailsViewHolder(listItem: View) : RecyclerView.ViewHolder(listItem) {
    private val binding = ViewHolderActorBinding.bind(listItem)

    fun bind(actor: Actor) {
        binding.photo.load(actor.picture)
        binding.name.text = actor.name
    }
}