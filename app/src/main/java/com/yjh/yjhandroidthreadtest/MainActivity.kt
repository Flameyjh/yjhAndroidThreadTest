package com.yjh.yjhandroidthreadtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.TextView
import kotlin.concurrent.thread

/*
* Android多线程编程
* 1. 异步消息处理机制 Handler + Message + (MessageQueue + Looper): 解决在子线程中进行UI操作的问题
* 具体操作：在子线程中发送message，在主线程中取出message并处理UI
* */
class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var changeTextBtn: Button

    val updataText = 1

    val handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            //在这里可以进行UI操作
            when(msg.what){
                updataText -> textView.text = "Nice to meet you"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        changeTextBtn = findViewById(R.id.changeTextBtn)

        changeTextBtn.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updataText
                handler.sendMessage(msg) //将Message对象发送出去
            }
        }
    }
}