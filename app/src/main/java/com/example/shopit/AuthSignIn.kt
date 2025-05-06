package com.example.shopit

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AuthSignIn : Fragment() {

    private var isPasswordVisible = false // Track password visibility state

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth_sign_in, container, false)

        val passInputLayout: TextInputLayout = view.findViewById(R.id.pass)
        val passwordEditText: TextInputEditText = view.findViewById(R.id.passwordEditText)

        passInputLayout.setEndIconOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                // Show password
                passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                // Hide password
                passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            // Keep the cursor at the end after toggling
            passwordEditText.setSelection(passwordEditText.text?.length ?: 0)
        }

        return view
    }
}
