def can_build(plat):
	return plat=="android" or plat=="iphone"

def configure(env):
	if (env['platform'] == 'android'):
		env.android_add_dependency("def appCenterSdkVersion = '1.11.4'")
		env.android_add_dependency('implementation "com.microsoft.appcenter:appcenter-analytics:${appCenterSdkVersion}"')
		env.android_add_dependency('implementation "com.microsoft.appcenter:appcenter-crashes:${appCenterSdkVersion}"')
		env.android_add_java_dir("android")
		env.disable_module()

	if env['platform'] == "iphone":
		env.AppendUnique(FRAMEWORKPATH='#modules/admob/ios/lib')
		env.AppendUnique(FRAMEWORKS=['-framework AppCenter','-framework AppCenterAnalytics','-framework AppCenterCrashes','-framework Foundation','-framework SystemConfiguration','-framework CoreTelephony','-framework UIKit','-framework WebKit','-framework SafariServices','-framework AuthenticationServices'])
		env.Append(LINKFLAGS=['-ObjC', '-framework', 'AppCenter','-framework', 'AppCenterAnalytics','-framework', 'AppCenterCrashes','-framework', 'Foundation','-framework', 'SystemConfiguration','-framework', 'CoreTelephony','-framework', 'UIKit','-framework', 'WebKit','-framework', 'SafariServices','-framework', 'AuthenticationServices'])
