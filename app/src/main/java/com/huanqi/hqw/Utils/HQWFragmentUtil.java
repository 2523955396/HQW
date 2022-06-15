package com.huanqi.hqw.Utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

/**
 * Fragment处理类 By焕奇灵动
 */
public class HQWFragmentUtil {

    /**
     * Fragment删除处理方法
     */
    public static void removeAllFragments(FragmentActivity context){
        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        List<Fragment> fragmentList = fragmentManager.getFragments();
        if(fragmentList == null || fragmentList.isEmpty()){
            return;
        }
        for (Fragment f : fragmentList) {
            transaction.remove(f);
        }
        transaction.commitNow();
        //transaction.commit();
    }
}
