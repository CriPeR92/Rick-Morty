package com.example.rickmortyapi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rickmortyapi.R
import com.example.rickmortyapi.models.Personage
import com.example.rickmortyapi.viewModels.CharacterViewModel
import com.google.gson.Gson

class CharacterFragment : Fragment() {

    var character: Personage? = null
    private lateinit var vm: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        character = Gson().fromJson(this.arguments?.getString("user", null).toString(), Personage::class.java)
        vm = ViewModelProvider(this, UserViewModelFactory(user, savedUser, userViewModel)).get(
            UserFragmentViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding: FragmentUserBindingImpl = DataBindingUtil.inflate(inflater, R.layout.fragment_character, container, false)
        binding.lifecycleOwner = this
        binding.userFragmentViewModel = vm
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}