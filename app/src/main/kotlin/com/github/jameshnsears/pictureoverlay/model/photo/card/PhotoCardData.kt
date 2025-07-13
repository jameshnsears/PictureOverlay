package com.github.jameshnsears.pictureoverlay.model.photo.card

import com.github.jameshnsears.pictureoverlay.model.photo.PhotoCollectionEnum

data class PhotoCardData(
    val collection: PhotoCollectionEnum,
    val imageType: String,
    val imageUri: Any,
    val dateTime: String?,
    val latLong: DoubleArray?
)
