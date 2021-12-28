package hu.unimiskolc.iit.jump.application.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.unimiskolc.iit.jump.application.R
import hu.unimiskolc.iit.jump.application.databinding.StartGameFragmentBinding

class StartGameFragment : Fragment() {

    private var _binding: StartGameFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: StartGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StartGameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(StartGameViewModel::class.java)
        // TODO: Use the ViewModel

        binding.startGameButton.setOnClickListener {
            findNavController().navigate(R.id.action_startGameFragment_to_gameFragment)
        }
    }

}