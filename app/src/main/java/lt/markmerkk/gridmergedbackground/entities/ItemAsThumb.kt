package lt.markmerkk.gridmergedbackground.entities

import lt.markmerkk.gridmergedbackground.adapters.ThumbItem

class ItemAsThumb(
    override val id: Int,
    val item: Item,
) : ThumbItem {
    override val title: String = item.title
}
