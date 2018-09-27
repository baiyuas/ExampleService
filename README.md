# Service 学习示例

主要战士在Service的基本使用


## Service生命周期

### Service生命周期中主要的几个方法

* onCreate
* onBind
* onUnbind
* onStartCommand
* onDestroy

### 执行顺序

调用`context.startService()`时候

```
onCreate()->onStartCommand()
```

<p style='text-indent: 2em;'>当多次调用`context.startService()`时候
只会调用一次`onCreate()`,每次都会执行`onStartCommand()`
方法,但是传入的参数`startId`从1开始自增,直到调用`stopService()`
后,在此启动Service才会从1开始
</p>

当调用`context.stopService`时候

```
onDestory()
```


unbindService必须先调用bindService否则会出现异常
在Activity或者Fragment中如果bindService一定要unbindService
否则引发ServiceConnection泄漏


