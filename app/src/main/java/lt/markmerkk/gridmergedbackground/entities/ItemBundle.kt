package lt.markmerkk.gridmergedbackground.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemBundle(
    val gridSize: Int,
    val adapterType: AdapterType,
    val items: List<Item>,
) : Parcelable
