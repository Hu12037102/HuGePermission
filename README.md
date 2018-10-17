# HuGePermission
一个超级强大的运行时权限工具类，一行代码解决运行时权限
## Github上一些针对于运行时权限处理，单个权限都还好，但是多个权限，我感觉不是特别的完美！不能满足我的业务！所以自己动手封装了一个！
## 使用方法很简单，一行代码
</br>1、	Activity中使用运行时权限的Activity extend PermissionActivity；Fragment中使用运行时权限 Fragment extend PermissionFragment
</br>2、重写requestPermission(String...permission)方法
</br>	### 使用依赖
             
                allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
            dependencies {
	        compile 'com.github.Hu12037102:HuGePermission:1.1.0'
	}

```java
requestPermission(new OnPermissionsResult() {
            @Override
            public void onAllow(List<String> permissions) {
                Log.w("requestPermission--","全部同意");
            }

            @Override
            public void onNoAllow(List<String> permissions) {
                Log.w("requestPermission--","全部不同意");
            }

            @Override
            public void onForbid(List<String> permissions) {
                Log.w("requestPermission--","有被禁止");
            }

            @Override
            public void onLowVersion() {
                Log.w("requestPermission--","我是低版本");
            }
        }, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
                });
		```
	
            
