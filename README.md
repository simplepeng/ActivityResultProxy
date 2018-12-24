## 简介

* 这个库是一个简化`onActivityResult`写法的库

## 引入依赖

## 使用

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

## 版本迭代

