## 简介

* 这个库是一个简化`onActivityResult`写法的库

## 引入依赖

```groovy

```

## 使用方法

### 不传递参数方式

```java
ARProxy.with(MainActivity.this)
       .setToActivity(ToActivity.class)
       .setRequestCode(REQUEST_CODE)
       .start(new OnResultListener() {
             @Override
             public void onActivityResult(int requestCode, int resultCode, 
                                                 Intent data) {
                        
                    }
                });
```

### 传递参数方式

```java
Intent intent = new Intent(MainActivity.this, ToActivity.class);
intent.putExtra("name","simple");

ARProxy.with(MainActivity.this)
       .setIntent(intent)
       .setRequestCode(REQUEST_CODE)
       .start(new OnResultListener() {
             @Override
             public void onActivityResult(int requestCode, int resultCode, 
                                                 Intent data) {

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
                .setToActivity(LoginActivity.class)
                .setRequestCode(LOGIN_REQUEST_CODE)
                .start(new OnResultListener() {
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

## 版本迭代

