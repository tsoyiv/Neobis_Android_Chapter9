package com.example.neocafe.fragments.registration_fragments

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chaos.view.PinView
import com.example.neocafe.R
import com.example.neocafe.databinding.FragmentRegCodeBinding

class RegCodeFragment : Fragment() {

    private lateinit var binding: FragmentRegCodeBinding
    private lateinit var pinView: PinView
    private lateinit var resendButton: AppCompatButton
    private var timer: CountDownTimer? = null
    private val timerDuration: Long = 30000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation()
        pinViewImplementation()
        resendRequest()
    }

    private fun resendRequest() {
        resendButton = binding.resendCodeReg
        resendButton.setOnClickListener {
            startTimer()
            resendButton.text = "Отправить повторно"
            resendButton.setBackgroundResource(R.drawable.btn_not_active)
            resendButton.isEnabled = false
        }
    }

    private fun startTimer() {
        timer?.cancel()

        timer = object : CountDownTimer(timerDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                resendButton.text = "Отправить повторно ($secondsLeft)"
            }

            override fun onFinish() {
                resendButton.text = "Отправить повторно"
                resendButton.setBackgroundResource(R.drawable.btn_active)
                resendButton.isEnabled = true
            }
        }

        timer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }

    private fun pinViewImplementation() {
        pinView = binding.otpViewReg
        pinView.requestFocus()
        val buttonFinish = binding.btnRegFinish

        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(pinView, InputMethodManager.SHOW_IMPLICIT)

        pinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // TODO: Input the logic to handle text changes in the PinView

                val isValidPhoneNumber = pinView.text?.isNotEmpty()

                if (isValidPhoneNumber != null) {
                    buttonFinish.isEnabled = isValidPhoneNumber
                }
                if (isValidPhoneNumber == true) {
                    buttonFinish.setBackgroundResource(R.drawable.btn_active)
                } else {
                    buttonFinish.setBackgroundResource(R.drawable.btn_not_active)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun navigation() {
        binding.btnReturnRegInfo.setOnClickListener {
            findNavController().navigate(R.id.action_regCodeFragment_to_infoRegFragment)
        }
        binding.btnRegFinish.setOnClickListener {
            findNavController().navigate(R.id.action_regCodeFragment_to_regDataFragment)
        }
    }
}