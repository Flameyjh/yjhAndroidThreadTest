package com.yjh.yjhandroidthreadtest

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import java.lang.Exception

/*
* 2. 使用AsyncTask: 本质是对异步消息处理机制的封装
* 如果要启动当前任务，只需要DownloadTask().excute()
* */

class DownloadTask(private val context: Context) : AsyncTask<Unit, Int, Boolean>() {

    val progressDialog = ProgressDialog(context)

    override fun onPreExecute() {
        progressDialog.show() //显示进度对话框
    }

    override fun doInBackground(vararg params: Unit?): Boolean = try { //子线程运行，excute()传入的参数将会到这里
        while (true) {
            val downloadPercent = doDownload() //这是一个虚构的方法
            publishProgress(downloadPercent)
            if (downloadPercent >= 100) {
                break
            }
        }
        true
    }catch (e: Exception){
        false
    }

    override fun onProgressUpdate(vararg values: Int?) { //主线程运行，操作UI
        //在这里更新下载进度
        progressDialog.setMessage("Download ${values[0]}%")
    }

    override fun onPostExecute(result: Boolean) { //下载完成之后调用
        progressDialog.dismiss() //关闭进度对话框
        //在这里提示下载结果
        if (result){
            Toast.makeText(context, "Download succeeded", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun doDownload(): Int {
        return 80
    }

}