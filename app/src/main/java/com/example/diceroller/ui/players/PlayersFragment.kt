package com.example.diceroller.ui.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.diceroller.R
import com.example.diceroller.databinding.FragmentPlayersBinding

class PlayersFragment : Fragment() {

    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!
    private lateinit var diceImage1 : ImageView
    private lateinit var diceImage2 : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val playersViewModel = ViewModelProvider(this)[PlayersViewModel::class.java]

        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPlayers
        playersViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.rollButton.setOnClickListener { rollDice() }

        diceImage1 = binding.dice1Image
        diceImage2 = binding.dice2Image

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun rollDice() {
        diceImage1.setImageResource(getRandomDiceImage())
        diceImage2.setImageResource(getRandomDiceImage())
    }

    private fun getRandomDiceImage() : Int {
        return when ((1..6).random()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}