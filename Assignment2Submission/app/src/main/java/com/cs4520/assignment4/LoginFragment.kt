package com.cs4520.assignment4
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import com.cs4520.assignment4.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener{
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (username == "admin" && password == "admin") {
                binding.username.text.clear()
                binding.password.text.clear()

                findNavController().navigate(R.id.action_loginFragment_to_productListFragment)
            } else {
                Toast.makeText(activity, "Incorrect username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}