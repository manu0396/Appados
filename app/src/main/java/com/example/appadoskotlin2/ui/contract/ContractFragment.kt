package com.example.appadoskotlin2.ui.contract

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.appadoskotlin2.R
import com.example.appadoskotlin2.data.Service
import com.example.appadoskotlin2.databinding.FragmentContractBinding
import com.example.appadoskotlin2.ui.adapters.AdapterContract

class ContractFragment : Fragment(), (Service) -> Unit {

    private lateinit var contractViewModel: ContractViewModel
    private var _binding: FragmentContractBinding? = null
    private lateinit var searchView: SearchView
    private lateinit var rvContract:RecyclerView
    private lateinit var adapter: AdapterContract
    //TODO("Inicialmente proporcionamos los servicios de manera local.
    // En el futuro hacerlo a traves de una API.")
    private lateinit var services: ArrayList<Service>
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contractViewModel =
            ViewModelProvider(this).get(ContractViewModel::class.java)

        _binding = FragmentContractBinding.inflate(inflater, container, false)
        val root: View = binding.root

        services = ArrayList()
        services.add(Service("Ofertado", "Fontaneria"))
        services.add(Service("Ofertado", "Mecanica"))
        services.add(Service("Ofertado", "Cuidado del hogar"))
        services.add(Service("Ofertado", "Electricidad"))
        services.add(Service("Ofertado", "Instalaciónes"))


        searchView = root.findViewById(R.id.svContract)
        rvContract = root.findViewById(R.id.rvContract)
        adapter = AdapterContract(this, services)
        rvContract.adapter = adapter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //TODO() Search on the available services


                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                //TODO() Change the text on SerchView to newText

                return false
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun invoke(service: Service) {
        //TODO() Implemenetar busqueda.

        Toast.makeText(context, "Buscando servicio: " + service.type, Toast.LENGTH_LONG).show()
    }
}