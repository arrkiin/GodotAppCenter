#ifndef GODOT_APPCENTER_H
#define GODOT_APPCENTER_H

#include "reference.h"

class GodotAppCenter : public Reference {
    GDCLASS(GodotAppCenter, Reference);

    GodotAppCenter *instance;

protected:
    static void _bind_methods();

    GodotAdmob();
    ~GodotAdmob();
};

#endif