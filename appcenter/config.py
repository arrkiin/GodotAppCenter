def can_build(plat):
	return plat=="android"

def configure(env):
	if (env['platform'] == 'android'):
		env.android_add_dependency('implementation "com.android.support:support-core-utils:28.0.0"')
		env.android_add_dependency("def appCenterSdkVersion = '1.11.4'")
		env.android_add_dependency('implementation "com.microsoft.appcenter:appcenter-analytics:${appCenterSdkVersion}"')
		env.android_add_dependency('implementation "com.microsoft.appcenter:appcenter-crashes:${appCenterSdkVersion}"')
		env.android_add_java_dir("android")
		env.disable_module()
