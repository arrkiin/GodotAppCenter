#include "godotAppCenter.h"

@import AppCenter;
@import AppCenterAnalytics;
@import AppCenterCrashes;

#define CLASS_DB ClassDB

GodotAppCenter::GodotAppCenter(){
    ERR_FAIL_COND(instance != NULL);
    instance = this;
}

GodotAppCenter::~GodotAppCenter() {
    instance = NULL;
}

bool GodotAppCenter::isEnabled(){
    return [MSAppCenter isEnabled];
}

void GodotAppCenter::setEnabled(boolean value){
    [MSAppCenter setEnabled:value];
}

void GodotAppCenter::setUserId(String userId){
    NSString *userIdStr = [[[NSString alloc] initWithUTF8String:name.utf8().get_data()] autorelease];
    [MSAppCenter setUserId:userIdStr];
}

void GodotAppCenter::setLogLevel(int level){
    [MSAppCenter setLogLevel:(NSUInteger)level];
}

void GodotAppCenter::analyticsTrackSimpleEvent(String name) {
    NSString *eventName = [[[NSString alloc] initWithUTF8String:name.utf8().get_data()] autorelease];
    NSLog(@"AppCenter.Analytics: SimpleEvent( %@ )",eventName);
    [MSAnalytics trackEvent:eventName];
}

void GodotAppCenter::analyticsTrackComplexEvent(String name, String jsonProperties) {
    NSString *eventName = [[[NSString alloc] initWithUTF8String:name.utf8().get_data()] autorelease];

    NSString *jsonString = [[[NSString alloc] initWithData:data
                                                 encoding:NSUTF8StringEncoding] autorelease];
    NSLog(@"AppCenter.Analytics: ComplexEvent( %@ : %@ )",eventName,jsonString);

    NSError *error = nil;
    NSDictionary *properties = [[NSJSONSerialization JSONObjectWithData:data
                                                         options:kNilOptions 
                                                           error:&error] autorelease];

    if (!properties) {
        NSLog(@"Error parsing JSON: %@", error);
    } else {
        [MSAnalytics trackEvent:eventName withProperties:properties];
    }
}

void GodotAppCenter::_bind_methods() {
    CLASS_DB::bind_method("isEnabled",&GodotAppCenter::isEnabled);
    CLASS_DB::bind_method("setEnabled",&GodotAppCenter::setEnabled);
    CLASS_DB::bind_method("setUserId",&GodotAppCenter::setUserId);
    CLASS_DB::bind_method("setLogLevel",&GodotAppCenter::setLogLevel);
    CLASS_DB::bind_method("analyticsTrackSimpleEvent",&GodotAppCenter::analyticsTrackSimpleEvent);
    CLASS_DB::bind_method("analyticsTrackComplexEvent",&GodotAppCenter::analyticsTrackComplexEvent);
}