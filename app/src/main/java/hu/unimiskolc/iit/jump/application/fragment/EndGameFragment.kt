package hu.unimiskolc.iit.jump.application.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.unimiskolc.iit.jump.application.R
import hu.unimiskolc.iit.jump.application.databinding.EndGameFragmentBinding

class EndGameFragment : Fragment() {

    private var _binding: EndGameFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = EndGameFragment()
    }

    private lateinit var viewModel: EndGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EndGameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EndGameViewModel::class.java)
        // TODO: Use the ViewModel

        binding.endGameButton.setOnClickListener {
            findNavController().navigate(R.id.action_endGameFragment_to_startGameFragment)
        }
    }

}