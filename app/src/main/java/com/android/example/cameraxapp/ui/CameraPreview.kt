package com.android.example.cameraxapp.ui

import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

/**
 * Camera Preview Composable
 *
 * Wraps the CameraX PreviewView in a Composable using AndroidView.
 * This is the standard approach for integrating CameraX with Compose
 * until a native Compose PreviewView is available.
 *
 * @param modifier Modifier to be applied to the preview
 * @param onPreviewViewCreated Callback invoked when the PreviewView is created,
 *                              providing access to the PreviewView for camera binding
 */
@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    onPreviewViewCreated: (PreviewView) -> Unit
) {
    AndroidView(
        factory = { context ->
            PreviewView(context).apply {
                // Notify callback that PreviewView is ready
                onPreviewViewCreated(this)
            }
        },
        modifier = modifier
    )
}
