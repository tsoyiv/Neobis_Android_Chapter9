package com.example.neocafe.fragments.registration_fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.neocafe.R
import com.example.neocafe.databinding.FragmentInfoRegBinding
import com.example.neocafe.utils.PhoneNumberTextWatcher

class InfoRegFragment : Fragment() {

    private lateinit var binding: FragmentInfoRegBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoRegBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberMask()
        navigation()
        enablingButton()
    }

    private fun navigation() {
        binding.btnReturnRegMainPage.setOnClickListener {
            findNavController().navigate(R.id.action_infoRegFragment_to_startFragment)
        }
        binding.btnRegGetCode.setOnClickListener {
            findNavController().navigate(R.id.action_infoRegFragment_to_regCodeFragment)
        }
    }
    private fun enablingButton() {
        val editTextNumberPhone = binding.editTextRegNum
        val editTextName = binding.editTextName
        val buttonNext = binding.btnRegGetCode

        editTextNumberPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                val phoneNumber = editable.toString().replace("[^\\d]".toRegex(), "")
                val isValidPhoneNumber = phoneNumber.length == 12
                val isEmptyName = editTextName.text.isNotEmpty()

                buttonNext.isEnabled = isValidPhoneNumber && isEmptyName
                if (isValidPhoneNumber) {
                    buttonNext.setBackgroundResource(R.drawable.btn_active)
                } else {
                    buttonNext.setBackgroundResource(R.drawable.btn_not_active)
                }
            }
        })
    }
    private fun numberMask() {
        val phoneNumberEditText = binding.editTextRegNum
        phoneNumberEditText.addTextChangedListener(PhoneNumberTextWatcher(phoneNumberEditText))
    }
}