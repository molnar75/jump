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
import hu.unimiskolc.iit.jump.core.domain.Score
import org.koin.android.ext.android.inject

class EndGameFragment : Fragment() {

    private var _binding: EndGameFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EndGameViewModel by inject()

    private var scorePosition: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EndGameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getResult()

        val score = (arguments?.getParcelable("score") as? Score)
        viewModel.setScore(score?.value ?: 0)

        viewModel.getHighScoreList().observe(viewLifecycleOwner, { scoreList ->
            if (scoreList != null) {

                val text: String = if (isScoreInTop(score, scoreList)) "Új TOP eredmény:  ${score?.value ?: 0}" else "Eredmény:  ${score?.value ?: 0}"

                binding.scoreText.text = text

                val adapter = ScoreAdapter(requireContext(), scoreList, scorePosition)
                binding.scoreList.adapter = adapter
            }
        })

        binding.endGameButton.setOnClickListener {
            findNavController().navigate(R.id.action_endGameFragment_to_startGameFragment)
        }
    }

    private fun isScoreInTop(score: Score?, scoreList: List<Score>): Boolean {
        var isIn = false

         if (score != null) {
            for (item in scoreList) {
                if(item.date == score.date && item.value == score.value) {
                    isIn = true
                    scorePosition = scoreList.indexOf(item)
                }
            }
        }

        return isIn
    }
}