# yjhAndroidThreadTest
android: Android多线程编程。
1. 异步消息处理机制 Handler + Message + (MessageQueue + Looper): 解决在子线程中进行UI操作的问题 。
   具体操作：在子线程中发送message，在主线程中取出message并处理UI 
2. 使用AsyncTask。
