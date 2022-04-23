package lt.markmerkk.gridmergedbackground

import android.view.View

fun <T> toViewClickListenerOrNull(
    item: T,
    action: ((T) -> Unit)?
): View.OnClickListener? {
    return if (action != null) {
        View.OnClickListener { action.invoke(item) }
    } else {
        null
    }
}

fun View.OnClickListener?.enableIf(
    isEnabled: Boolean,
): View.OnClickListener? {
    return if (isEnabled) {
        this
    } else {
        null
    }
}

fun View.OnClickListener?.enableIf(
    isEnabled: () -> Boolean,
): View.OnClickListener? {
    return if (isEnabled.invoke()) {
        this
    } else {
        null
    }
}
