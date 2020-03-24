## 简介

* 这是简化Activity获取返回值startActivityForResult 写法的库

## 引入依赖

* 从1.0.3版本开始使用androidx

```groovy
implementation 'com.simple:ActivityResultProxy:1.0.5'
```

## 使用方法

```java
ARProxy.with(MainActivity.this)
                .navTo(ToActivity.class)
                .putExtra("name","simple")
                .putExtra("age",26)
                .putExtra("man",true)
                .getResult(REQUEST_CODE, new OnResultListener() {
                    @Override
                    public void onActivityResult(int requestCode, int resultCode, Intent data) 										{
                      
                    }
                });
```

## 高级使用

App中经常碰到一些情况需要判断是否登录过，再做一些操作，这个时候对该库稍微封装一下即可实现这种需求。

当然封装一个全局的接口回调拦截器也是一种很好的做法。

* 封装一个LoginHelper

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
        
        ARProxy.with(activity)
                .navTo(LoginActivity.class)
                .getResult(LOGIN_REQUEST_CODE, new OnResultListener() {
                    @Override
                    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

* 使用

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

## 版本迭代

* v1.0.5：修复`连续多次调用不会回调的bug`
* v1.0.4：修复当`Host Activity finished`导致崩溃的bug
* v1.0.3：迁移到androidx，优化调用方式
* v1.0.2：修复bug
* v1.0.1：增加loginHelper封装样例
* v1.0.0：首次上传