package com.mygroup.project.model.dto;

public interface View {
    interface MinimalGET { }
    interface ExtendedGET extends MinimalGET { }
    interface CompleteGET extends ExtendedGET { }

    interface AlternateUserGET { }

    interface LoginPOST { }
    interface RegisterPOST extends LoginPOST { }

    interface UserSettingsPUT { }
    interface PrivateLessonPOST { }
}
