# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a CameraXApp Android project based on the Google CameraX Codelab. It's a single-activity application that demonstrates camera functionality using the modern CameraX library for capturing photos and recording videos.

**Package:** `com.android.example.cameraxapp`
**Language:** Kotlin
**Min SDK:** 21
**Target/Compile SDK:** 35
**JVM Target:** 11

## Build Commands

```bash
# Build the project
./gradlew build

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Clean build
./gradlew clean

# Run unit tests
./gradlew test

# Run instrumented tests (requires device/emulator)
./gradlew connectedAndroidTest

# Run specific test class
./gradlew test --tests com.android.example.cameraxapp.ExampleUnitTest

# Install debug APK on connected device
./gradlew installDebug
```

## Architecture

### Single Activity Architecture
The app uses a single-activity architecture with `MainActivity` as the entry point. All camera functionality is contained within this activity.

### CameraX Implementation
The app follows the standard CameraX lifecycle pattern:

1. **Permission Handling**: Runtime permissions for CAMERA, RECORD_AUDIO, and WRITE_EXTERNAL_STORAGE (API ≤ 28) are requested using `ActivityResultContracts`
2. **Camera Initialization**: `startCamera()` sets up the camera provider and binds use cases
3. **Use Cases**: Three CameraX use cases are bound to the camera lifecycle:
   - **Preview**: Real-time camera preview displayed in `PreviewView`
   - **ImageCapture**: Still photo capture functionality
   - **VideoCapture**: Video recording with audio support

### Key Components

- **MainActivity.kt** (app/src/main/java/com/android/example/cameraxapp/MainActivity.kt:38): Single activity containing all camera logic
  - `startCamera()` (line 178): Initializes CameraX and binds use cases to lifecycle
  - `takePhoto()` (line 67): Captures still images and saves to MediaStore
  - `captureVideo()` (line 110): Handles video recording start/stop with audio
  - Permission handling via `activityResultLauncher` (line 218)

- **View Binding**: Uses ViewBinding for type-safe view access (enabled in build.gradle.kts:36-38)

- **Threading**: Uses a single-thread executor (`cameraExecutor`) for camera operations, properly shutdown in `onDestroy()`

### Dependencies
Key CameraX libraries (from app/build.gradle.kts):
- `androidx.camera.core` - Core CameraX functionality
- `androidx.camera.camera2` - Camera2 implementation
- `androidx.camera.lifecycle` - Lifecycle integration
- `androidx.camera.video` - Video recording
- `androidx.camera.view` - PreviewView widget
- `androidx.camera.extensions` - Camera extensions support

## Layout Structure

**activity_main.xml**: Simple ConstraintLayout with:
- `PreviewView` (fullscreen camera preview)
- Two buttons aligned at bottom using a vertical guideline at 50%:
  - `image_capture_button` - Capture photo
  - `video_capture_button` - Start/stop video recording

## Permissions

The app requires runtime permissions declared in AndroidManifest.xml:
- `CAMERA` - Required for camera access
- `RECORD_AUDIO` - Required for video recording with audio
- `WRITE_EXTERNAL_STORAGE` - Only for API 28 and below

## Media Storage

- Photos saved to: `Pictures/CameraX-Image/`
- Videos saved to: `Movies/CameraX-Video/`
- File naming format: `yyyy-MM-dd-HH-mm-ss-SSS`
- All media saved via MediaStore API for scoped storage compliance

## Testing

- **Unit tests**: app/src/test/java/com/android/example/cameraxapp/ExampleUnitTest.kt
- **Instrumented tests**: app/src/androidTest/java/com/android/example/cameraxapp/ExampleInstrumentedTest.kt
- Test runner: `androidx.test.runner.AndroidJUnitRunner`
