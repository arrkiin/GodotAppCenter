#include <core/class_db.h>
#include <core/engine.h>

#include "register_types.h"

void register_appcenter_types() {
    Engine::get_singleton()->add_singleton(Engine::Singleton("AppCenter", memnew(GodotAppCenter)));
}

void unregister_appcenter_types() {
}
