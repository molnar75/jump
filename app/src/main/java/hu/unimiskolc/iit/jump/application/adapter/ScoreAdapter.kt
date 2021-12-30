package hu.unimiskolc.iit.jump.application.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import hu.unimiskolc.iit.jump.application.R
import hu.unimiskolc.iit.jump.core.domain.Score
import java.text.SimpleDateFormat
import java.util.*

class ScoreAdapter(context: Context, private val scoreList: List<Score>, private val scorePosition: Int?) : BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return scoreList.size
    }

    override fun getItem(position: Int): Any {
        return scoreList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.score_adapter , parent, false)

        val dateTextView = rowView.findViewById(R.id.score_date) as TextView
        val valueTextView = rowView.findViewById(R.id.score_value) as TextView

        val score = getItem(position) as Score

        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd. HH:mm:ss")
        val date = simpleDateFormat.format(score.date)

        if (scorePosition != null && position == scorePosition) {
            dateTextView.setBackgroundResource(R.color.high_score_blue)
            valueTextView.setBackgroundResource(R.color.high_score_blue)
        }

        dateTextView.text = date
        valueTextView.text = score.value.toString()

        dateTextView.setTextColor(Color.BLACK)
        valueTextView.setTextColor(Color.BLACK)

        return rowView
    }
}