package io.github.luiisca.floating.views.data

import io.github.luiisca.floating.views.event.ActiveOverlayEventInterface
import io.github.luiisca.floating.views.event.ExpandedOverlayEventInterface

data class OverlayConfigData(
    val enableAnimations: Boolean = true,
    val main: ActiveOverlayEventInterface,
    val close: CloseOverlayData,
    val expanded: ExpandedOverlayEventInterface
)
