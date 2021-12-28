package hu.unimiskolc.iit.jump.application.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.unimiskolc.iit.jump.application.R
import hu.unimiskolc.iit.jump.application.databinding.EndGameFragmentBinding
import hu.unimiskolc.iit.jump.application.adapter.ScoreAdapter
import org.koin.android.ext.android.inject

class EndGameFragment : Fragment() {

    private var _binding: EndGameFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EndGameViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EndGameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        viewModel.getResult()
        viewModel.getHighScoreList().observe(viewLifecycleOwner, { scoreList ->
            if (scoreList != null) {

                val score = arguments?.getInt("score")
                viewModel.setScore(score ?: 0)

                binding.endGameButton.setOnClickListener {
                    findNavController().navigate(R.id.action_endGameFragment_to_startGameFragment)
                }

                val adapter = ScoreAdapter(requireContext(), scoreList)
                binding.scoreList.adapter = adapter
            }
        })
    }
}