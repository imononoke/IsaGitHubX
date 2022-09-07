package com.isa.githubx.page.webview

import android.os.Build
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.isa.githubx.common.AppWebView

@Composable
fun WebViewScreen(
    navController: NavHostController,
    url: String
) {
    val rememberWebProgress: MutableState<Int> = remember { mutableStateOf(-1) }

    AppWebView(
        modifier = Modifier.fillMaxSize(),
        url = url,
        onProgressChange = { progress ->
            rememberWebProgress.value = progress
        },
        initSettings = {settings->
            settings?.apply {
                //支持js交互
                javaScriptEnabled = true
                //....
            }
        }, onBack = { webView ->
            //可根据需求处理此处
            if (webView?.canGoBack() == true) {
                //返回上一级页面
                webView.goBack()
            } else {
                //关闭activity
//                finish()
            }
        },
        onReceivedError = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                println(">>>>>>${it?.description}")
            }
        }
    )

//    LinearProgressIndicator(
//        progress = rememberWebProgress * 1.0F / 100F,
//        color = Color.Red,
//        modifier = Modifier
//            .fillMaxWidth()
//            .height( if (rememberWebProgress == 100) 0.dp else 5.dp
//            )
//    )
}

private const val WEBVIEW_ROUTE_PREFIX = "webview"
internal const val KEY_URL = "url"

const val WEBVIEW_ROUTE =
    "$WEBVIEW_ROUTE_PREFIX?$KEY_URL={$KEY_URL}"

fun webRoute(
    url: String
) = "$WEBVIEW_ROUTE_PREFIX?$KEY_URL=$url"
