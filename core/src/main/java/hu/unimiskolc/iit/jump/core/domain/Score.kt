package hu.unimiskolc.iit.jump.core.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Score(val id: Int, var date: Date, var value: Int): Parcelable