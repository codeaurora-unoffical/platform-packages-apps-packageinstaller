/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.packageinstaller.permission.ui.handheld;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.android.packageinstaller.DeviceUtils;
import com.android.permissioncontroller.R;

/**
 * A class that contains a header with a row of buttons.
 */
public abstract class SettingsWithButtonHeader extends PermissionsFrameFragment  {

    private View mHeader;
    protected Drawable mIcon;
    protected CharSequence mLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);

        if (!DeviceUtils.isTelevision(getContext())) {
            mHeader = inflater.inflate(R.layout.button_header, root, false);
            getPreferencesContainer().addView(mHeader, 0);

            updateHeader(mHeader);
        }

        return root;
    }

    /**
     * Set the icon and label to use in the header.
     *
     * @param icon the icon
     * @param label the label
     */
    public void setHeader(@NonNull Drawable icon, @NonNull CharSequence label) {
        mIcon = icon;
        mLabel = label;
        updateHeader(mHeader);
    }

    /**
     * Updates the header to use the correct icon, title, and buttons.
     *
     * @param header the View that contains the components.
     */
    protected void updateHeader(@NonNull View header) {
        if (header != null) {
            ImageView appIcon = header.requireViewById(R.id.entity_header_icon);
            appIcon.setImageDrawable(mIcon);

            TextView appName = header.requireViewById(R.id.entity_header_title);
            appName.setText(mLabel);

            header.requireViewById(R.id.entity_header_summary).setVisibility(View.GONE);
            header.requireViewById(R.id.entity_header_second_summary).setVisibility(View.GONE);

            Button button1 = header.requireViewById(R.id.button1);
            button1.setText(R.string.launch_app);
            setButtonIcon(button1, R.drawable.ic_open);
            button1.setEnabled(false);

            Button button2 = header.requireViewById(R.id.button2);
            button2.setText(R.string.uninstall_app);
            setButtonIcon(button2, R.drawable.ic_delete);
            button2.setEnabled(false);

            Button button3 = header.requireViewById(R.id.button3);
            button3.setText(R.string.force_stop_app);
            setButtonIcon(button3, R.drawable.ic_force_stop);
            button3.setEnabled(false);

            header.requireViewById(R.id.button4).setVisibility(View.GONE);
        }
    }

    private void setButtonIcon(@NonNull Button button, @DrawableRes int iconResId) {
        button.setCompoundDrawablesWithIntrinsicBounds(null, getContext().getDrawable(iconResId),
                null, null);
    }
}
