package com.example.rickmortyapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortyapi.databinding.ItemCharacterBinding
import com.example.rickmortyapi.fragments.HomeFragment
import com.example.rickmortyapi.models.Personage
import com.example.rickmortyapi.models.SessionData
import com.example.rickmortyapi.viewModels.HomeViewModel


class GridCharactersAdapter(var fragment: HomeFragment, var list: ArrayList<Personage>) : RecyclerView.Adapter<GridCharactersAdapter.GridCharacterViewHolder>() {

    private lateinit var vm: HomeViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridCharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater)
        vm = ViewModelProvider(fragment).get(HomeViewModel::class.java)

        return GridCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridCharacterViewHolder, position: Int) {
        holder.binding.homeFragmentViewModel = vm
        holder.binding.personage = SessionData.characters[position]
    }

    override fun getItemCount(): Int {
        return SessionData.characters.size
    }

    class GridCharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)
}