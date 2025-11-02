package com.android.example.cameraxapp

import androidx.camera.core.ImageCapture
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * ViewModel for Camera Screen
 *
 * Manages camera-related state using Compose State for reactive UI updates.
 * Survives configuration changes (e.g., screen rotation).
 */
class CameraViewModel : ViewModel() {

    /**
     * ImageCapture use case for taking photos
     */
    var imageCapture: ImageCapture? by mutableStateOf(null)

    /**
     * VideoCapture use case for recording videos
     */
    var videoCapture: VideoCapture<Recorder>? by mutableStateOf(null)

    /**
     * Current active recording session
     */
    var recording: Recording? by mutableStateOf(null)

    /**
     * Whether video is currently being recorded
     * UI automatically updates when this changes
     */
    var isRecording by mutableStateOf(false)

    /**
     * Whether video capture is available and ready
     */
    val isVideoCaptureEnabled: Boolean
        get() = videoCapture != null

    /**
     * Whether image capture is available and ready
     */
    val isImageCaptureEnabled: Boolean
        get() = imageCapture != null

    /**
     * Clean up resources when ViewModel is destroyed
     */
    override fun onCleared() {
        super.onCleared()
        // Recording and camera resources are cleaned up by MainActivity's onDestroy
        // This is just for future-proofing if we move more logic to ViewModel
    }
}
