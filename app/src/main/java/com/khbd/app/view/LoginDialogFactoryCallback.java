package com.khbd.app.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import com.fasterxml.jackson.databind.JsonNode;

public interface LoginDialogFactoryCallback  {
    default void onView(@NonNull Context context, @NonNull AutoCompleteTextView username, @NonNull EditText password){};
    default void login_success(@NonNull Context context){};
    default void login_failure(Exception e){};
}