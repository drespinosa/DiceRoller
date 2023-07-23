package com.example.diceroller.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diceroller.databinding.FragmentDashboardBinding
import com.example.diceroller.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment() : Fragment(), OnQueryTextListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DogsAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textLogin
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.searchDogs.setOnQueryTextListener(this)
        initRecyclerView()

        return root
    }

    private fun initRecyclerView() {
        adapter = DogsAdapter(dogImages)
        binding.recyclerDogs.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerDogs.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getCharacterByName("$query/images")
            val puppies = call.body()
            withContext(Dispatchers.Main){
                if(call.isSuccessful) {
                    val images: List<String> = puppies?.images ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                } else{
                    showErrorDialog()
                }
            }
        }
    }

    private fun showErrorDialog() {
        Toast.makeText(requireContext(), "Ha ocurrido un error, inténtelo de nuevo.", Toast.LENGTH_LONG).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByName(query.lowercase())
            return true
        }
        return false
    }


    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}