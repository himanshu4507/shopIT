package com.example.shopit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class AuthSignUp : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_auth_sign_up, container, false)

        val navController= findNavController()

        val continuebutton:Button= view.findViewById(R.id.continuebtn)

        continuebutton.setOnClickListener{
            navController.navigate(R.id.action_authSignUp_to_OTPSignUp)
        }
        return view
    }


}