package lt.markmerkk.gridmergedbackground.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemBundle(val items: List<Item>): Parcelable
