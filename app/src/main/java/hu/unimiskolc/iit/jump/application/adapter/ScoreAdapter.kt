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
import java.util.*

class ScoreAdapter(private val context: Context, private val scoreList: List<Score>) : BaseAdapter() {
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

        val calendar = GregorianCalendar()
        calendar.time = score.date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val date = "$year. $month. $day."

        dateTextView.text = date
        valueTextView.text = score.value.toString()

        dateTextView.setTextColor(Color.BLACK)
        valueTextView.setTextColor(Color.BLACK)

        return rowView
    }
}