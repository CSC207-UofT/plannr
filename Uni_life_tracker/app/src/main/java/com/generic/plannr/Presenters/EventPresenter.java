package com.generic.plannr.Presenters;

import com.generic.plannr.UseCases.UserManager;

public class EventPresenter {

    public static String constructUserEvents(UserManager user) {
        StringBuilder result = new StringBuilder();
        // Print out all the courses the user currently has
        for (int i = 0; i < user.viewCourses().size(); i++) {
            result.append(i + 1).append(". ").append(user.viewCourses().get(i)).append("\n");
        }

        return result.toString();
    }
}
