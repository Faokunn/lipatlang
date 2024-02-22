package com.example.gymrat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.gymrat.Models.LoginResponse
import com.example.gymrat.api.RetrofitClient
import com.example.gymrat.databinding.FragmentLoginBinding
import com.example.gymrat.storage.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.signInButton.setOnClickListener {

            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if(email.isEmpty()){
                binding.email.error = "Email Required"
                binding.email.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                binding.password.error = "Password Required"
                binding.password.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.userLogin(email,password)
                .enqueue(object: Callback<LoginResponse>{
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if(!response.body()?.error!!){

                            findNavController().navigate(R.id.action_loginFragment2_to_homiesFragment)
                        }
                        else{
                            Toast.makeText(context,response.body()?.message, Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                })
        }
        return binding.root
    }

}