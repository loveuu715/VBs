package vbs.vvi.com.bs.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.base.BaseActivity;

public class SceneManager {
    public static final String EXTRA_TRANSITION = "EXTRA_TRANSITION";
    public static final String TRANSITION_FADE_FAST = "FADE_FAST";
    public static final String TRANSITION_FADE_SLOW = "FADE_SLOW";
    public static final String TRANSITION_SLIDE_RIGHT = "SLIDE_RIGHT";
    public static final String TRANSITION_SLIDE_BOTTOM = "SLIDE_BOTTOM";
    public static final String TRANSITION_EXPLODE = "EXPLODE";
    public static final String TRANSITION_EXPLODE_BOUNCE = "EXPLODE_BOUNCE";

    public static void toScene(Context context, Class<? extends Activity> target, Bundle data) {
        Intent intent = new Intent();
        intent.setClass(context, target);
        if (data != null) {
            intent.putExtras(data);
        }
        context.startActivity(intent);
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).overridePendingTransition(R.anim.b_enter_anim, R.anim.b_exit_anim);
        }
    }

    public static void toSceneWithSlideUp(Context context, Class<? extends Activity> target, Bundle data) {
        Intent intent = new Intent();
        intent.setClass(context, target);
        if (data != null) {
            intent.putExtras(data);
        }
        context.startActivity(intent);
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).overridePendingTransition(R.anim.b_enter_anim, R.anim.b_exit_anim);
        }
    }

    public static void startSlideRightTransition(Activity from, Class<? extends Activity> target, Bundle data) {
        Intent intent = new Intent(from, target);
        if (data != null) {
            intent.putExtras(data);
        }
        intent.putExtra(SceneManager.EXTRA_TRANSITION, SceneManager.TRANSITION_SLIDE_RIGHT);
        startActivityWithOptions(from, intent);
        if (from instanceof BaseActivity) {
            ((BaseActivity) from).overridePendingTransition(R.anim.b_enter_anim, R.anim.b_exit_anim);
        }
    }

    public static void startSlideBottomTransition(Activity from, Class<? extends Activity> target, Bundle data) {
        Intent intent = new Intent(from, target);
        if (data != null) {
            intent.putExtras(data);
        }
        intent.putExtra(SceneManager.EXTRA_TRANSITION, SceneManager.TRANSITION_SLIDE_BOTTOM);
        startActivityWithOptions(from, intent);
        from.overridePendingTransition(R.anim.b_enter_anim, R.anim.b_exit_anim);
    }

    public static void startFadeInSlowTransition(Activity from, Class<? extends Activity> target, Bundle data) {
        Intent intent = new Intent(from, target);
        if (data != null) {
            intent.putExtras(data);
        }
        intent.putExtra(SceneManager.EXTRA_TRANSITION, SceneManager.TRANSITION_FADE_SLOW);
        startActivityWithOptions(from, intent);
        from.overridePendingTransition(R.anim.b_enter_anim, R.anim.b_exit_anim);
    }

    private static void startActivityWithOptions(Activity from, Intent intent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions transitionActivity = null;
            transitionActivity = ActivityOptions.makeSceneTransitionAnimation(from);
            from.startActivity(intent, transitionActivity.toBundle());
        } else {
            from.startActivity(intent);
            from.overridePendingTransition(R.anim.b_enter_anim, R.anim.b_exit_anim);
        }
    }

    public static void startScaleTransition(Activity from, Class<? extends Activity> target, View sharedElement, Bundle data) {
        Intent intent = new Intent();
        ActivityOptions options = null;
        if (data != null) {
            intent.putExtras(data);
        }
        intent.setClass(from, target);
        if (sharedElement != null && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            sharedElement.setTransitionName("sharedElementName");
            options = ActivityOptions.makeSceneTransitionAnimation(from, sharedElement, "sharedElementName");
            from.startActivity(intent, options.toBundle());
        } else {
            from.startActivity(intent);
            from.overridePendingTransition(R.anim.b_enter_anim, R.anim.b_exit_anim);
        }
    }
}
