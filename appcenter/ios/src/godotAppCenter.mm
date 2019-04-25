#include "godotAppCenter.h"
#import <AppCenter/AppCenter.h>
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

void GodotAppCenter::start() {
    
}

void GodotAppCenter::_bind_methods() {
    CLASS_DB::bind_method("start",&GodotAppCenter::start);
}