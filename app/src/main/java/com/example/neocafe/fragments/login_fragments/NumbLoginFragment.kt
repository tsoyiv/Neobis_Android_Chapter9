package com.example.neocafe.fragments.login_fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.neocafe.R
import com.example.neocafe.databinding.FragmentNumbLoginBinding
import com.example.neocafe.utils.PhoneNumberTextWatcher

class NumbLoginFragment : Fragment() {

    private lateinit var binding : FragmentNumbLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNumbLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation()
        numberMask()
        enablingButton()
    }

    private fun enablingButton() {
        val editTextNumberPhone = binding.editTextNumberPhone
        val buttonNext = binding.btnLoginGetCode

        editTextNumberPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                val phoneNumber = editable.toString().replace("[^\\d]".toRegex(), "")
                val isValidPhoneNumber = phoneNumber.length == 12

                buttonNext.isEnabled = isValidPhoneNumber
                if (isValidPhoneNumber) {
                    buttonNext.setBackgroundResource(R.drawable.btn_active)
                } else {
                    buttonNext.setBackgroundResource(R.drawable.btn_not_active)
                }
            }
        })
    }

    private fun numberMask() {
        val phoneNumberEditText = binding.editTextNumberPhone
        phoneNumberEditText.addTextChangedListener(PhoneNumberTextWatcher(phoneNumberEditText))
    }

    private fun navigation() {
        binding.btnReturnMainPage.setOnClickListener {
            findNavController().navigate(R.id.action_numbLoginFragment_to_startFragment)
        }
        binding.btnLoginGetCode.setOnClickListener {
            findNavController().navigate(R.id.action_numbLoginFragment_to_codeLoginFragment)
        }
    }
}