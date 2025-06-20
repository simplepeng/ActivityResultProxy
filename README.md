## 简介

这是一个简化获取跳转Activity获取返回值的库

## 引入依赖

[![](https://jitpack.io/v/simplepeng/ActivityResultProxy.svg)](https://jitpack.io/#simplepeng/ActivityResultProxy)

```groovy
maven { url 'https://jitpack.io' }
```

从1.0.3版本开始使用androidx

```groovy
implementation 'com.github.simplepeng:ActivityResultProxy:v1.2.0'
```

## 使用方法

```java
ARProxy.navTo(this, ToActivity.class)
        .putExtra("name", "simple")
        .startActivityForResult(REQUEST_CODE, new ARProxy.OnResultListener() {
            @Override
            public void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {

            }
        });
```

或在`Kotlin`中

```kotlin
startActivityForResult(REQUEST_CODE, intent) { requestCode, resultCode, data ->
    
}
```

## 高级使用

App中经常碰到一些情况需要判断是否登录过，再做一些操作，这个时候对该库稍微封装一下即可实现这种需求。

当然封装一个全局的接口回调拦截器也是一种很好的做法。

封装一个LoginHelper

```java
public class LoginHelper {

    private static final int LOGIN_REQUEST_CODE = 120;
    private static boolean isLogin = false;//模拟未登录情况

    public static void isLogin(FragmentActivity activity, final OnLoginListener listener) {
        if (isLogin) {
            //模拟已经登录过，真实情况应该是从sp或者db中获取用户信息
            UserBean user = new UserBean();
            user.setName("isLogin - simple");
            user.setPassword("123456");
            listener.onLogin(user);
            return;
        }
        
        ARProxy.navTo(activity, LoginActivity.class)
                .startActivityForResult(LOGIN_REQUEST_CODE, new ARProxy.OnResultListener() {
                    @Override
                    public void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
                        if (requestCode != LOGIN_REQUEST_CODE || resultCode != Activity.RESULT_OK
                                || data == null) return;

                        UserBean user = data.getParcelableExtra("user");
                        if (user == null) return;
                        isLogin = true;
                        listener.onLogin(user);
                    }
                });
    }

    public interface OnLoginListener {
        void onLogin(UserBean user);
    }
}
```

使用

```java
LoginHelper.isLogin(MainActivity.this, new LoginHelper.OnLoginListener() {
            @Override
            public void onLogin(UserBean user) {
              
            }
        });
```

这样封装过，如果已经登录过就会直接调用回调接口中的`onLogin`方法，如果未登录就会跳转到登录页，并在成功登录后回调`onLogin`方法。具体例子可以查看`app`中的使用方法。

## 混淆

```
-keep com.simple.proxy.** { *; }
-keepnames class com.simple.proxy.** { *; }
```

## 感谢各位大佬打赏🙇🙇🙇！

您的支持是作者努力更新的动力。万水千山总是情，10.24我看行！

| ![](https://raw.githubusercontent.com/simplepeng/merge_pay_code/refs/heads/master/qrcode_alipay.jpg) | ![](https://raw.githubusercontent.com/simplepeng/merge_pay_code/refs/heads/master/qrcode_wxpay.png) | ![](https://raw.githubusercontent.com/simplepeng/merge_pay_code/refs/heads/master/qrcode_qqpay.png) |
| ------------------------------------------------------------ | ----- | ----- |

[打赏链接](https://simplepeng.com/merge_pay_code/) | [赞助列表](https://simplepeng.com/Sponsor/)

## 版本迭代

* v1.2.0：增加`kotlin`支持
* v1.1.1：解决内存泄漏的问题
* v1.1.0：去掉`result==Activity.RESULT_OK`的判断，有些时候可能需要用到`CANCELED`
* v1.0.9：`commitNow`替换为`commitNowAllowingStateLoss`，解决依附的Activity销毁重建后不能回调的bug
* v1.0.8：增加`navTo Intent`隐式意图跳转
* v1.0.7：修改api调用方式
* v1.0.6：修改api调用方式，模仿`RxPermission`的写法
* v1.0.5：修复`连续多次调用不会回调的bug`
* v1.0.4：修复当`Host Activity finished`导致崩溃的bug
* v1.0.3：迁移到androidx，优化调用方式
* v1.0.2：修复bug
* v1.0.1：增加loginHelper封装样例
* v1.0.0：首次上传