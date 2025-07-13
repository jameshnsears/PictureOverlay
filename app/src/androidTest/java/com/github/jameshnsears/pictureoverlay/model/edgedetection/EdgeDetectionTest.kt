package com.github.jameshnsears.pictureoverlay.model.edgedetection

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class EdgeDetectionTest : com.github.jameshnsears.pictureoverlay.utility.CommonTestUtility() {
    init {
        System.loadLibrary("opencv_java4")
    }

    @Test
    fun confirmCannyWorking() {
        val edgeDetectionCanny = com.github.jameshnsears.pictureoverlay.model.edgedetection.Canny()
        val originalImageAsMat = getImageAsMat(edgeDetectionCanny, "MediaStore/reichstag.jpg")

        val blurredImage =
            edgeDetectionCanny.applyGaussianBlurFilterToReduceNoise(originalImageAsMat)

        val cannyImage = edgeDetectionCanny.applyCanny(blurredImage)

        val cannyBitmap = edgeDetectionCanny.convertMatToBitmap(cannyImage)
        assertNotNull(cannyBitmap)

        val transparentCannyBitmap = edgeDetectionCanny.makeBitmapTransparent(cannyBitmap)

        val expectedBitmap = edgeDetectionCanny
            .convertMatToBitmap(
                getImageAsMat(edgeDetectionCanny, "EdgeDetection/reichstag.png")
            )

        // TODO modify threshold values

        assertTrue(transparentCannyBitmap.sameAs(expectedBitmap))
    }

    private fun getImageAsMat(edgeDetectionUtils: com.github.jameshnsears.pictureoverlay.model.edgedetection.EdgeDetectionUtils, path: String) =
        edgeDetectionUtils.convertOriginalImageToBitmap(
            this.javaClass.classLoader!!.getResourceAsStream(path)
        )
}
