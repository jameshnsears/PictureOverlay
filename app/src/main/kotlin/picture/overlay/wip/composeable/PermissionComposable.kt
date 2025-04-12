package picture.overlay.wip.composeable

import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.core.net.toUri
import io.github.luiisca.floating.views.data.CloseOverlayData
import io.github.luiisca.floating.views.data.OverlayConfigData
import io.github.luiisca.floating.views.event.ActiveOverlayEventInterface
import io.github.luiisca.floating.views.event.ExpandedOverlayEventInterface
import io.github.luiisca.floating.views.helpers.OverlayHelper
import picture.overlay.composable.stopwatch.StopwatchCloseComposable
import picture.overlay.composable.stopwatch.StopwatchComposable
import picture.overlay.wip.OverlayPermissionViewModel

@Preview
@Composable
fun OverlayPermissionScreen(
    viewModel: OverlayPermissionViewModel = viewModel()
) {
    val context = LocalContext.current

    val permissionGranted = viewModel.overlayPermissionGranted.value

    // check if permission already granted
    LaunchedEffect(Unit) {
        viewModel.checkOverlayPermission(context)
    }

    // request permission if not granted
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        viewModel.checkOverlayPermission(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
            if (!Settings.canDrawOverlays(context)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    "package:${context.packageName}".toUri()
                )
                launcher.launch(intent)
            } else {
                viewModel.checkOverlayPermission(context)
            }
        },
            enabled = !permissionGranted
        ) {
            Text("Request Overlay Permission...")
        }

        Text(
            text = if (permissionGranted) "Overlay permission is granted ✅"
            else "Overlay permission is NOT granted ❌",
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (permissionGranted) {
                    val config = OverlayConfigData(
                        main = ActiveOverlayEventInterface(
                            composable = { StopwatchComposable() },
                            // Add other main float configurations here
                        ),

                        // TODO JS - Expanded is what happens when you click on the stopwatch
                        expanded = ExpandedOverlayEventInterface(
                            enabled = false,
                        )
                        /*
                        val expandedFloatConfig = ExpandedFloatConfig(
                            enabled = true,
                            tapOutsideToClose = true,
                            dimAmount = 0.5f,
                            composable = { close -> /* Expanded content */ }
                        )
                         */,

                        close = CloseOverlayData(
                            composable = { StopwatchCloseComposable() },
                            enabled = true,
                        ),
                        /*
                        val closeFloatConfig = CloseFloatConfig(
                            enabled = true,
                            composable = { /* Custom close button */ },
                            closeBehavior = CloseBehavior.MAIN_SNAPS_TO_CLOSE_FLOAT
                        )
                         */

                    )

                    // Launch a new stopwatch floating view
                    OverlayHelper.startFloatServiceIfPermitted(context, config)
                }
            },
            enabled = permissionGranted
        ) {
            Text("Display Overlay Control...")
        }
    }
}
