package com.example.neocafe.fragments.registration_fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.neocafe.R
import com.example.neocafe.databinding.FragmentRegDataBinding
import java.util.*

class RegDataFragment : Fragment() {

    private lateinit var binding: FragmentRegDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation()
        pickDate()
    }

    private fun navigation() {
        binding.btnReturnIngoReg.setOnClickListener {
            findNavController().navigate(R.id.action_regDataFragment_to_infoRegFragment)
        }
        binding.skipData.setOnClickListener {
            //TODO skip data
        }
    }
    private fun callDialog() {
        val dialogBinding = layoutInflater.inflate(R.layout.custom_dialog_finish_reg, null)

        val myDialog = Dialog(requireContext())
        myDialog.setContentView(dialogBinding)

        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
    }
    private fun pickDate() {
        val datePicker = binding.editTextData
        datePicker.setOnClickListener {
            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    val dat = "$dayOfMonth.${monthOfYear + 1}.$year"
                    datePicker.setText(dat)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }
}