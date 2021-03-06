package com.example.rickmortyapi.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickmortyapi.R
import com.example.rickmortyapi.adapter.GridCharactersAdapter
import com.example.rickmortyapi.viewModels.HomeViewModel
import com.example.rickmortyapi.databinding.FragmentHomeBindingImpl
import com.example.rickmortyapi.models.Personage
import com.example.rickmortyapi.models.SessionData
import com.google.gson.Gson

class HomeFragment : Fragment() {

    lateinit var adapter: GridCharactersAdapter
    lateinit var binding: FragmentHomeBindingImpl
    private lateinit var vm: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        binding.homeFragmentViewModel = vm
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.characterResponse.observe(binding.lifecycleOwner!!, Observer {
            vm.uiEventValue.value = 3
        })

        vm.uiEventValue.observe(binding.lifecycleOwner!!, Observer {
            when (it) {
                1 -> {
                    val bundle = Bundle()
                    bundle.putString("personage", Gson().toJson(vm.character.value))
                    val fragment = CharacterFragment()
                    fragment.arguments = bundle
                    this.activity?.supportFragmentManager?.beginTransaction()
                        ?.add(R.id.container, fragment)
                        ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ?.addToBackStack(null)
                        ?.commit()
                }
                3 -> {
                    vm.hide.value = 0
                    SessionData.isLoading = false
                    if (::adapter.isInitialized) {
                        adapter.notifyDataSetChanged()
                    } else {
                        adapter = GridCharactersAdapter(this, vm.characterResponse.value?.results!!)
                        binding.adapter = adapter
                    }
                }
            }
        })

        adapter = GridCharactersAdapter(this, ArrayList<Personage>())
        binding.adapter = adapter

    }
}
