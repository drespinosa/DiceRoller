package com.example.diceroller.ui.reset_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.diceroller.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {

    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel = ViewModelProvider(this)[ResetPasswordViewModel::class.java]

        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textResetPassword
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

//        binding.buttonNext.setOnClickListener {
//
//            }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}