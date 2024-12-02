package com.junseo.subwayalram.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment

object ActivityResultUtil {

    // ComponentActivity (Activity)용 런처 등록
    fun registerActivityResultCallback(
        activity: ComponentActivity,
        onResult: (result: ActivityResult) -> Unit
    ): ActivityResultLauncher<Intent> {
        return activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            onResult(result) // 결과를 호출자에게 전달
        }
    }

    // Fragment용 런처 등록
    fun registerActivityResultCallback(
        fragment: Fragment,
        onResult: (result: ActivityResult) -> Unit
    ): ActivityResultLauncher<Intent> {
        return fragment.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            onResult(result) // 결과를 호출자에게 전달
        }
    }

    // Intent 실행
    fun startForResult(
        launcher: ActivityResultLauncher<Intent>,
        context: Context,
        targetActivity: Class<out Activity>
    ) {
        val intent = Intent(context, targetActivity)
        launcher.launch(intent)
    }
}