package com.example.shopit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class AuthEntry : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_auth_entry, container, false)

        // Get the NavController
        val navController = findNavController()

        val newhere:Button=view.findViewById(R.id.btn1)
        val alreadymember:Button=view.findViewById((R.id.btn2))

        alreadymember.setOnClickListener{
            navController.navigate(R.id.action_authEntry_to_authSignIn2)
        }
        newhere.setOnClickListener{
            navController.navigate(R.id.action_authEntry_to_authSignUp)
        }
        return view
    }

}