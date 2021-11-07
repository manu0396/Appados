package com.example.appadoskotlin2.ui.publish

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appadoskotlin2.R
import com.example.appadoskotlin2.data.Service
import com.example.appadoskotlin2.databinding.FragmentPublishBinding
import com.example.appadoskotlin2.ui.adapters.AdapterContract
import com.example.appadoskotlin2.ui.adapters.AdapterPublish
import com.example.appadoskotlin2.ui.diologs.PublishDiolog
import com.example.appadoskotlin2.ui.utils.SimpleDividerItemDecoration

class PublishFragment : Fragment(), (Service) -> Unit, PublishDiolog.ConfirmationDialogListener {

    private lateinit var publishViewModel: PublishViewModel
    private var _binding: FragmentPublishBinding? = null
    private lateinit var rvPublish: RecyclerView
    private lateinit var adapter: AdapterPublish
    //TODO("Inicialmente proporcionamos los servicios de manera local.
    // En el futuro hacerlo a traves de una API.")
    private lateinit var user_description: String
    private lateinit var linearLayoutManager: LinearLayoutManager
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        user_description = savedInstanceState?.getString("user_des").toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        publishViewModel =
            ViewModelProvider(this).get(PublishViewModel::class.java)

        _binding = FragmentPublishBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val services = ArrayList<Service>()
        services.add(Service("Ofertar", "Carpinteria"))
        services.add(Service("Ofertar", "Fontaneria"))
        services.add(Service("Ofertar", "Electricidad"))
        rvPublish = root.findViewById(R.id.rvPublish)
        rvPublish.addItemDecoration(SimpleDividerItemDecoration(this.context, R.drawable.line_divider))
        linearLayoutManager = LinearLayoutManager(this.context)
        rvPublish.layoutManager = linearLayoutManager
        adapter = AdapterPublish(this, services)
        rvPublish.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun invoke(s: Service) {


        //TODO("Mostrar Alert Diolog pidiendiendo los detalles del servicio: descripción y precio")
        //TODO("Permitir que sea el usuario el que introduzca la descripcion")
        showdialog(s)
    }

    fun showdialog(s:Service){

        fragmentManager?.let {
            val confirmationDialogFragment = PublishDiolog
                .newInstance(
                    "Confirmar: " + s.type,
                    user_description
                )
            confirmationDialogFragment.setTargetFragment(this, 0)
           // confirmationDialogFragment.
            confirmationDialogFragment.show(it, "Confirm")
        }
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        //TODO("Almacenar en una BBDD remota la publicación del servicio.")
        //TODO("Rescatar los servicios publicados para usarlos en el ContractFragment")

        Toast.makeText(context, "Servicio publicado correctamente", Toast.LENGTH_LONG).show()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Toast.makeText(context, "Operación cancelada", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }
}