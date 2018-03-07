LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)



LOCAL_MODULE    := sib
LOCAL_SRC_FILES := sub.cpp
LOCAL_LDLIBS +=  -llog -ldl

include $(BUILD_SHARED_LIBRARY)
