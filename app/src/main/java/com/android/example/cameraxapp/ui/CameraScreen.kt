package com.android.example.cameraxapp.ui

import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.example.cameraxapp.R
import com.android.example.cameraxapp.ui.theme.CameraXAppTheme

/**
 * Main Camera Screen Composable
 *
 * Displays a fullscreen camera preview with photo and video capture controls
 * at the bottom of the screen.
 *
 * @param onPreviewViewCreated Callback invoked when the PreviewView is created
 * @param onTakePhoto Callback invoked when the take photo button is clicked
 * @param onCaptureVideo Callback invoked when the video capture button is clicked
 * @param isRecording Whether video is currently being recorded
 * @param modifier Modifier to be applied to the screen
 */
@Composable
fun CameraScreen(
    onPreviewViewCreated: (PreviewView) -> Unit,
    onTakePhoto: () -> Unit,
    onCaptureVideo: () -> Unit,
    isRecording: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        // Fullscreen camera preview
        CameraPreview(
            modifier = Modifier.fillMaxSize(),
            onPreviewViewCreated = onPreviewViewCreated
        )

        // Bottom controls
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp),
            horizontalArrangement = Arrangement.spacedBy(100.dp)
        ) {
            // Photo capture button
            Button(
                onClick = onTakePhoto,
                modifier = Modifier.size(110.dp)
            ) {
                Text(stringResource(R.string.take_photo))
            }

            // Video capture button
            Button(
                onClick = onCaptureVideo,
                modifier = Modifier.size(110.dp)
            ) {
                Text(
                    text = if (isRecording) {
                        stringResource(R.string.stop_capture)
                    } else {
                        stringResource(R.string.start_capture)
                    }
                )
            }
        }
    }
}

/**
 * Preview for CameraScreen in Android Studio
 */
@Preview(showBackground = true)
@Composable
fun CameraScreenPreview() {
    CameraXAppTheme {
        CameraScreen(
            onPreviewViewCreated = { },
            onTakePhoto = { },
            onCaptureVideo = { },
            isRecording = false
        )
    }
}

/**
 * Preview for CameraScreen while recording
 */
@Preview(showBackground = true)
@Composable
fun CameraScreenRecordingPreview() {
    CameraXAppTheme {
        CameraScreen(
            onPreviewViewCreated = { },
            onTakePhoto = { },
            onCaptureVideo = { },
            isRecording = true
        )
    }
}
