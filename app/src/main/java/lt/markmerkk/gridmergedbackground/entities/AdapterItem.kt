package lt.markmerkk.gridmergedbackground.entities

import lt.markmerkk.gridmergedbackground.adapters.BasicAdapterItem

class AdapterItem(
    override val id: Int,
    val item: Item,
) : BasicAdapterItem {
    override val title: String = item.title
}
