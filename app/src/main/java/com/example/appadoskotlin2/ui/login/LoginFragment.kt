package com.example.appadoskotlin2.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.appadoskotlin2.R
import com.example.appadoskotlin2.databinding.FragmentLoginBinding
import com.example.appadoskotlin2.ui.diologs.LoginDialog
import com.example.appadoskotlin2.ui.diologs.RegisterDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.button.MaterialButton
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LoginFragment: Fragment() {
    private val RC_SIGN_IN = 123

    private lateinit var navController : NavController
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var firebaseAuth: FirebaseAuth? = null
    private var mGoogleSignIn: GoogleSignInClient? = null
    private var btn_google: MaterialButton? = null
    private var btn_register: MaterialButton? = null
    private var btn_login: MaterialButton? = null
    private var diolog_register: RegisterDialog? = null
    private var diolog_login: LoginDialog? = null

    //TODO("Configurar firebase y linkear la vista")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initView(root)

        initListeners()

        return root
    }

    private fun initListeners() {
        val loginActivity: LoginFragment = this
        btn_google?.setOnClickListener(View.OnClickListener {
            createRequest()
            signIn()
        })
        btn_register?.setOnClickListener(View.OnClickListener {
            diolog_register = RegisterDialog(firebaseAuth, loginActivity)
            fragmentManager?.let { it1 -> diolog_register!!.show(it1, "RegisterDiolog") }
        })
        btn_login?.setOnClickListener(View.OnClickListener {
            diolog_login = LoginDialog(firebaseAuth)
            fragmentManager?.let { it1 -> diolog_login!!.show(it1, "LoginDiolog") }
        })

    }

    fun initView(root: View){
        btn_google = root.findViewById(R.id.btn_google)
        btn_register = root.findViewById(R.id.btn_register)
        btn_login = root.findViewById(R.id.btn_login)
        context?.let { FirebaseApp.initializeApp(it) }
        firebaseAuth = FirebaseAuth.getInstance()
    }
    private fun signIn() {
        val signInIntent = mGoogleSignIn!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
        showHome()
    }
    private fun createRequest() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("786240463795-o92gljk39cmdd0pra162arebskntkdu5.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignIn = GoogleSignIn.getClient(this.activity, gso)
    }
    private fun showHome() {
        navController = Navigation.findNavController(this.requireView())
        navController.navigate(R.id.navigation_login_to_navigation_home)
        Toast.makeText(context, "Login completed successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}