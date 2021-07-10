package com.ngocvu.example.view.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ngocvu.example.R
import com.ngocvu.example.utils.Prefs
import com.ngocvu.example.utils.isValidEmail
import com.ngocvu.example.view.state.ViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LogInFragment : Fragment() {

    private lateinit var viewModel: LogInViewModel
    private lateinit var navController: NavController
    private lateinit var prefs: Prefs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LogInViewModel::class.java)
        navController = Navigation.findNavController(view)
        prefs = Prefs(requireContext())
        initView()


    }
    fun initView() {

        btn_login.setOnClickListener {
            var email = et_email.text.toString()
            var password = et_password.text.toString()
            if(email.isValidEmail())
            {
                viewModel.login(email,password)
                viewModel.loginRes.observe(viewLifecycleOwner) { response ->
                    when(response) {
                        is ViewState.Loading -> {
                            login_fetch_progress.visibility = View.VISIBLE
                            btn_login.visibility = View.GONE
                        }
                        is ViewState.Success -> {
                            navController.navigate(R.id.action_logInFragment_to_startUpFragment)
                        }
                        is ViewState.Error -> {
                            Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
            else
            {
                Toast.makeText(context, "Wrong email format", Toast.LENGTH_SHORT).show()
            }


        }
    }



}